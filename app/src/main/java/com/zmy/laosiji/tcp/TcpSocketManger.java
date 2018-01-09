package com.zmy.laosiji.tcp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.zmy.laosiji.rxhttp.RetrofitFactory;
import com.zmy.laosiji.utils.ConstantUtil;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public final static int Conn_startDown = 0x09;
    public final static int Conn_completeDown = 0x10;
    public final static int Conn_startIng = 0x11;
    public static boolean Conntect_State = false;

    DataOutputStream dataOut = null;
    DataInputStream dataInput = null;
    FileInputStream fileInput = null;
    FileOutputStream fileOut = null;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (socketRequest != null) {
                socketRequest.result(msg.what, msg.obj);
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
        this.singleThreadPoolsend = Executors.newCachedThreadPool();
        this.cachedThreadPool = Executors.newCachedThreadPool();
    }

    public static TcpSocketManger getInstance() {
        if (tcpSocketManger == null) {
            synchronized (TcpSocketManger.class) {
                if (tcpSocketManger == null) {
                    tcpSocketManger = new TcpSocketManger();
                }
            }
        }
        return tcpSocketManger;
    }

    public void setSocketRequest(SocketRequest socketRequest) {
        if (this.socketRequest != null) {
            this.socketRequest = null;
        }
        this.socketRequest = socketRequest;

    }

    /**
     * 连接
     */
    public void initConntect(String address) {
        this.address = address;
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


    public void sendFile(String filePath) throws Exception {
        new upTask().execute(filePath);
    }


    class upTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                //获取本地文件
                File file = new File(strings[0]);
                if (file.exists()) {
                    fileInput = new FileInputStream(file);
                    dataInput = new DataInputStream(new BufferedInputStream(fileInput));
                    dataOut = new DataOutputStream(outputStream);
                    //获取文件名和长度
                    dataOut.writeUTF(file.getName());
                    dataOut.flush();
                    dataOut.writeLong((long) file.length());
                    dataOut.flush();
                    // 开始传输文件
                    ConstantUtil.log_e("-----开始传输文件------");
                    int bufferSize = 1024 * 8;
                    byte[] buf = new byte[bufferSize];
                    int length = 0;
                    long progress = 0;
                    while ((length = dataInput.read(buf, 0, buf.length)) != -1) {
                        dataOut.write(buf, 0, length);
                        dataOut.flush();
                        progress += length;
                        //    时刻将当前进度更新给onProgressUpdate方法
                        //    得到当前图片下载的进度
                        int progress_value = (int) ((progress / (float) file.length()) * 100);
                        publishProgress(progress_value);
                    }
                    dataInput.close();
                    ConstantUtil.log_e("-----发送完成------");
                } else {
                    ConstantUtil.toast("文件不存在");
                }
            } catch (Exception e) {

            }
            return "null";
        }

        //在界面上显示进度条
        @Override
        protected void onPreExecute() {
            handler.sendEmptyMessage(Conn_startDown);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Message msg = new Message();
            msg.what = Conn_startIng;
            msg.obj = values[0];
            handler.sendMessage(msg);

        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            handler.sendEmptyMessage(Conn_completeDown);
        }
    }

}
