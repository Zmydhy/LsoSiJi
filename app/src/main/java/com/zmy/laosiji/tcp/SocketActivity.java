package com.zmy.laosiji.tcp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.moudle.activity.DialogActivity;
import com.zmy.laosiji.utils.ByteUtil;
import com.zmy.laosiji.utils.Constant;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.utils.MUtils;
import com.zmy.laosiji.utils.StringUtils;
import com.zmy.laosiji.widgets.tdialog.TDialog;
import com.zmy.laosiji.widgets.tdialog.base.BindViewHolder;
import com.zmy.laosiji.widgets.tdialog.listener.OnBindViewListener;
import com.zmy.laosiji.widgets.tdialog.listener.OnViewClickListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

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
import static com.zmy.laosiji.utils.DateUtil.currentDatetime;

/**
 * 编码格式为gbk可以中文
 */
@RuntimePermissions
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
    private StringBuffer stringBuffer = new StringBuffer();
    private StringBuffer stringBuffers = new StringBuffer();
    private String sendText = "";
    private LinearLayout linearLayouts;


    SocketRequest socketRequest = new SocketRequest<Object>() {
        @Override
        public void result(int what, Object obj) {
            switch (what) {
                case Conn_CreateSucs:
                    ConstantUtil.toast("连接成功");
                    ConstantUtil.log_e("连接成功");
                    btnConnect.setText("已连接");
                    btnConnect.setClickable(true);
                    btnConnect.setTextColor(getResources().getColor(R.color.black));
                    break;
                case Conn_TimeOut:
                    ConstantUtil.toast("连接超时");
                    ConstantUtil.log_e("连接超时");
                    ConstantUtil.log_e("超时");
                    btnConnect.setClickable(true);
                    btnConnect.setTextColor(getResources().getColor(R.color.black));
                    break;
                case Conn_CreateFail:
                    ConstantUtil.toast("连接失败");
                    ConstantUtil.log_e("连接失败");
                    btnConnect.setText("失败");
                    btnConnect.setClickable(true);
                    btnConnect.setTextColor(getResources().getColor(R.color.black));
                    break;
                case Conn_ServerClosed:
                    ConstantUtil.toast("服务器关闭连接");
                    ConstantUtil.log_e("服务器关闭连接");
                    btnConnect.setText("已断开");
                    btnConnect.setClickable(true);
                    btnConnect.setTextColor(getResources().getColor(R.color.black));
                    break;
                case Conn_LocalClosed:
                    ConstantUtil.toast("本地关闭连接");
                    ConstantUtil.log_e("本地关闭连接");
                    btnConnect.setText("已断开");
                    btnConnect.setTextColor(getResources().getColor(R.color.black));
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
                    if (!TextUtils.isEmpty(Arrays.toString((byte[])obj))) {
                        String content = currentDatetime()+"\n"+ ByteUtil.toStringHex(ByteUtil.bytesToHexString((byte[]) obj));
                        stringBuffers.append(content+"\n");
                        recivetextSocket.setText(stringBuffers);
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
                    ConstantUtil.log_e(Arrays.toString(new Integer[]{(Integer) obj}));
                    dialog.setProgress(((Integer) obj).intValue());
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
        linearLayouts = findViewById(R.id.lin_socket);
        setTitle("socket测试");
        edSocket.setText("129.1.5.162:8888");
        MUtils.setTvCursor(edSocket);
        if (Conntect_State) {
            btnConnect.setText("已连接");
        }
        getInstance().setSocketRequest(socketRequest);
        findViewById(R.id.btn_sendfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setUpDialog();
                    getInstance().sendFile(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        linearLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出评论框
                sendDialog(v);
            }
        });

    }

    @OnClick({R.id.btn_connect, R.id.btn_send, R.id.btn_jiexi,R.id.btn_sendclear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                if (Conntect_State) {
                    getInstance().antoClose();
                    sendtextSocket.setText("");
                    recivetextSocket.setText("");
                    stringBuffer.delete(0,stringBuffer.length());
                    stringBuffers.delete(0,stringBuffers.length());
                } else {
                    btnConnect.setText("连接中");
                    btnConnect.setTextColor(getResources().getColor(R.color.item_border_color));
                    btnConnect.setClickable(false);
                    getInstance().initConntect(edSocket.getText().toString());

                }

                break;
            case R.id.btn_send:
                if (Conntect_State && !TextUtils.isEmpty(sendText)) {
                    //先将字符串转为十六进制字符串，然后将十六进制字符串转为byte[]发送
                    getInstance().sendBytes(ByteUtil.hexStringToByte(ByteUtil.str2HexStr(sendText)));
                    sendText = "";
                } else if(!Conntect_State){
                    ConstantUtil.toast("请先连接服务器");
                } else{
                    ConstantUtil.toast("请输入发送的内容");
                }

                break;
            case R.id.btn_jiexi:
                recivetextSocket.setText("");
                stringBuffers.delete(0,stringBuffers.length());
                break;

            case R.id.btn_sendclear:
                sendtextSocket.setText("");
                stringBuffer.delete(0,stringBuffer.length());
                break;
            default:
                break;
        }
    }

    private void setUpDialog(){
        //添加弹出的对话框
        dialog = new ProgressDialog(SocketActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("正在上传文件，请稍后···");
        //将进度条设置为水平风格，让其能够显示具体的进度值
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false); //用了这个方法之后，直到图片下载完成，进度条才会消失（即使在这之前点击了屏幕）
    }
     @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPermsion(){
    }


    //评价 弹出输入框
    public void sendDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_evaluate)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .setAnimation(R.style.EnterExitAnimation)
                .addOnClickListener(R.id.btn_evluate,R.id.edSocket)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        final EditText editText = viewHolder.getView(R.id.editText);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) SocketActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                            }
                        });
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {

                        switch (view.getId()) {
                            case R.id.btn_evluate:
                                EditText editText = viewHolder.getView(R.id.editText);
                                if(TextUtils.isEmpty(editText.getText().toString().trim())){
                                    ConstantUtil.toast("输入内容为空");
                                }else{
                                    String content = currentDatetime()+"\n"+ editText.getText().toString().trim();
                                    sendText = editText.getText().toString().trim();
                                    stringBuffer.append(content+"\n");
                                    sendtextSocket.setText(stringBuffer);
                                    tDialog.dismiss();
                                }
                                break;
                            case R.id.edSocket:
                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

}
