package com.zmy.laosiji.tcp;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.zmy.laosiji.rxhttp.RetrofitFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Michael on 2018/1/7.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * 使用线程池实现socket的收发
 */

public class TcpSocketManger {
    // 变量
    private String address;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ExecutorService cachedThreadPool;
    private ExecutorService singleThreadPool;
    private ExecutorService singleThreadPoolsend;
    // 回调
    private SocketRequest socketRequest;
    private static TcpSocketManger tcpSocketManger;

    // 常量
    public final static int Conn_CreateSucs = 0x00;
    public final static int Conn_TimeOut = 0x01;
    public final static int Conn_CreateFail = 0x02;
    public final static int Conn_ServerClosed = 0x03;
    public final static int Conn_LocalClosed = 0x04;
    public final static int Conn_SendSucs = 0x05;
    public final static int Conn_SendFail = 0x06;
    public final static int Conn_ReceiveSucs = 0x07;
    public final static int Conn_ReceiveFail = 0x08;
    public static boolean Conntect_State = false;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (socketRequest != null) {
                socketRequest.result(msg.what, (byte[]) msg.obj);
            }
            super.handleMessage(msg);
        }
    };
    Runnable runnableSocket = new Runnable() {
        @Override
        public void run() {
            String[] ads = address.split(":");
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(ads[0], Integer.parseInt(ads[1])));
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                Conntect_State = true;
                handler.sendEmptyMessage(Conn_CreateSucs);
                //创建接收发送线程池
                cachedThreadPool.execute(receciveRunableSocket);
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(Conn_CreateFail);
            }
        }
    };

    Runnable receciveRunableSocket = new Runnable() {
        @Override
        public void run() {
            byte[] buffer = new byte[8192];
            byte[] buff = null;
            while (inputStream != null) {
                try {
                    int length = 0;
                    length = inputStream.read(buffer);
                    if (length <= 0) {
                        // 服务器已断开
                        handler.sendEmptyMessage(Conn_ServerClosed);
                        // 置空数据
                        buffer = null;
                        buff = null;
                        // 关闭连接
                        closeConnection();
                        return;
                    } else {
                        buff = new byte[length];
                        System.arraycopy(buffer, 0, buff, 0, length);
                        Message msg = new Message();
                        msg.what = Conn_ReceiveSucs;
                        msg.obj = buff;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    handler.sendEmptyMessage(Conn_ReceiveFail);
                }

            }
        }
    };


    private TcpSocketManger() {
        this.singleThreadPool = Executors.newSingleThreadExecutor();
        this.singleThreadPoolsend = Executors.newSingleThreadExecutor();
        this.cachedThreadPool = Executors.newCachedThreadPool();
    }

    public static TcpSocketManger getInstance() {
        if (tcpSocketManger == null) {
            synchronized (RetrofitFactory.class) {
                if (tcpSocketManger == null) {
                    tcpSocketManger = new TcpSocketManger();
                }
            }
        }
        return tcpSocketManger;
    }

    public void setSocketRequest(SocketRequest socketRequest) {
        if (this.socketRequest != null) {
            this.socketRequest =null;
        }
        this.socketRequest = socketRequest;

    }

    /**
     * 连接
     */
    public void initConntect(String address, SocketRequest socketRequest) {
        this.address = address;
        this.socketRequest = socketRequest;
        if (socket != null) {
            closeConnection();
        }
        synchronized (this) {
            if (socket == null) {
                singleThreadPool.execute(runnableSocket);
            }
        }
    }


    private void closeConnection() {

        if (inputStream != null) {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {

            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
                outputStream = null;
            } catch (IOException e) {

            }
        }

        if (socket != null) {
            try {
                socket.close();
                socket = null;
            } catch (IOException e) {

            }
        }
        Conntect_State = false;
    }

    public void antoClose() {
        closeConnection();
        // 已从本地关闭连接
        handler.sendEmptyMessage(Conn_LocalClosed);
    }

    /**
     * @param buffer 需要发送的自己数组
     * @return 返回发送结果
     */
    public void sendBytes(byte[] buffer) {
        singleThreadPoolsend.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.write(buffer);
                    outputStream.flush();
                    handler.sendEmptyMessage(Conn_SendSucs);
                } catch (IOException e) {
                    handler.sendEmptyMessage(Conn_SendFail);
                }
            }
        });

    }


}
