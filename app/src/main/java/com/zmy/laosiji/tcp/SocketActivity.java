package com.zmy.laosiji.tcp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.utils.ConstantUtil;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_CreateFail;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_CreateSucs;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_LocalClosed;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_ReceiveFail;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_ReceiveSucs;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_SendFail;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_SendSucs;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_ServerClosed;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_TimeOut;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_completeDown;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_startDown;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conn_startIng;
import static com.zmy.laosiji.tcp.TcpSocketManger.Conntect_State;
import static com.zmy.laosiji.tcp.TcpSocketManger.getInstance;

public class SocketActivity extends BaseActivity {

    @BindView(R.id.ed_socket)
    EditText edSocket;
    @BindView(R.id.btn_connect)
    Button btnConnect;
    @BindView(R.id.sendtext_socket)
    TextView sendtextSocket;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.recivetext_socket)
    TextView recivetextSocket;
    @BindView(R.id.btn_jiexi)
    Button btnJiexi;
    private ProgressDialog dialog;

    SocketRequest socketRequest = new SocketRequest() {
        @Override
        public void result(int what, byte[] obj) {
            switch (what) {
                case Conn_CreateSucs:
                    ConstantUtil.toast("连接成功");
                    ConstantUtil.log_e("连接成功");
                    btnConnect.setText("已连接");
                    break;
                case Conn_TimeOut:
                    ConstantUtil.toast("连接超时");
                    ConstantUtil.log_e("连接超时");
                    ConstantUtil.log_e("失败");
                    break;
                case Conn_CreateFail:
                    ConstantUtil.toast("连接失败");
                    ConstantUtil.log_e("连接失败");
                    btnConnect.setText("失败");
                    break;
                case Conn_ServerClosed:
                    ConstantUtil.toast("服务器关闭连接");
                    ConstantUtil.log_e("服务器关闭连接");
                    btnConnect.setText("已断开");
                    break;
                case Conn_LocalClosed:
                    ConstantUtil.toast("本地关闭连接");
                    ConstantUtil.log_e("本地关闭连接");
                    btnConnect.setText("已断开");
                    break;
                case Conn_SendSucs:
                    ConstantUtil.toast("发送成功");
                    ConstantUtil.log_e("发送成功");
                    break;
                case Conn_SendFail:
                    ConstantUtil.toast("发送失败");
                    ConstantUtil.log_e("发送失败");
                    break;
                case Conn_ReceiveSucs:
                    if (!TextUtils.isEmpty(Arrays.toString(obj))) {
                        recivetextSocket.setText(Arrays.toString(obj));
                    }
                    ConstantUtil.toast("接收成功");
                    ConstantUtil.log_e("接收成功");
                    break;
                case Conn_ReceiveFail:
                    ConstantUtil.toast("接收失败");
                    ConstantUtil.log_e("接收失败");
                    break;

                case Conn_startDown:
                    dialog.show();
                    break;
                case Conn_completeDown:
                    dialog.dismiss();
                    break;

                case Conn_startIng:
                    ConstantUtil.log_e(Arrays.toString(obj));
                    dialog.setProgress(35);
                    break;

                default:
                    break;
            }
        }
    };

    //歌曲路径
    private String filePath = Environment.getExternalStorageDirectory() + "/Music/a1.mp3";

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_socket);
        setTitle("socket测试");
        edSocket.setText("192.168.0.110:8888");
        if (Conntect_State) {
            btnConnect.setText("已连接");
            getInstance().setSocketRequest(socketRequest);
        }
        findViewById(R.id.btn_sendfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getInstance().sendFile(filePath);

                    //添加弹出的对话框
                    dialog = new ProgressDialog(SocketActivity.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("正在下载图片，请稍后···");
                    //将进度条设置为水平风格，让其能够显示具体的进度值
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setCancelable(false); //用了这个方法之后，直到图片下载完成，进度条才会消失（即使在这之前点击了屏幕）
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick({R.id.btn_connect, R.id.btn_send, R.id.btn_jiexi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                ConstantUtil.log_e("连接状态：" + Conntect_State);
                if (Conntect_State) {
                    getInstance().antoClose();
                } else {
                    getInstance().initConntect(edSocket.getText().toString(), socketRequest);
                }

                break;
            case R.id.btn_send:
                if (Conntect_State) {
                    byte[] ss = new byte[]{0x68, 0x72, 0x30, 0x66, 0x01, 0x00, 0x00, 0x68, 0x11, 0x04, 0x33, 0x33, 0x34, 0x33, (byte) 0xBB, 0x16};
                    getInstance().sendBytes(ss);
                    sendtextSocket.setText(Arrays.toString(ss));
                } else {
                    ConstantUtil.toast("请先连接服务器");
                }

                break;
            case R.id.btn_jiexi:
                break;
            default:
                break;
        }
    }

}