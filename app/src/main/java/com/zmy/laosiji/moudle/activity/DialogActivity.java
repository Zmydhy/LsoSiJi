package com.zmy.laosiji.moudle.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.widgets.tdialog.TDialog;
import com.zmy.laosiji.widgets.tdialog.base.BindViewHolder;
import com.zmy.laosiji.widgets.tdialog.base.TBaseAdapter;
import com.zmy.laosiji.widgets.tdialog.listener.OnBindViewListener;
import com.zmy.laosiji.widgets.tdialog.listener.OnViewClickListener;

import java.util.Arrays;

public class DialogActivity extends BaseActivity {

    private String[] data = {"java", "android", "NDK", "c++", "python", "ios", "Go", "Unity3D", "Kotlin", "Swift", "js"};
    private String[] sharePlatform = {"微信", "朋友圈", "短信", "微博", "QQ空间", "Google", "FaceBook", "微信", "朋友圈", "短信", "微博", "QQ空间"};



    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dialog,"弹框展示");
    }

    public void useTDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_click)
                .setWidth(1000)
                .setHeight(800)
                .setTag("DialogTest")
                .setDimAmount(0.2f)
                .setGravity(Gravity.CENTER)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder bindViewHolder) {
                        bindViewHolder.setText(R.id.tv_content, "FUCK!!!");
                        bindViewHolder.setTextColor(R.id.tv_content, getResources().getColor(R.color.btn_blue_pressed));
                        bindViewHolder.setAlpha(R.id.conms, 0.5f);
                    }
                })
                .addOnClickListener(R.id.btn_right, R.id.tv_title)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {

                    }
                })
                .create()
                .show();
    }

    public void upgradeDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_version_upgrde)
                .setScreenWidthAspect(this, 0.7f)
                .addOnClickListener(R.id.tv_cancel, R.id.tv_confirm)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_cancel:
                                tDialog.dismiss();
                                break;
                            case R.id.tv_confirm:
                                Toast.makeText(DialogActivity.this, "开始下载新版本apk文件", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();

    }

    public void tipsDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_vb_convert)
                .setScreenWidthAspect(this, 0.85f)
                .setCancelOutside(false)
                .addOnClickListener(R.id.tv_jiuyuan_desc, R.id.tv_cancel, R.id.tv_confirm)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_jiuyuan_desc:
                                Toast.makeText(DialogActivity.this, "进入说明界面", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.tv_cancel:
                                tDialog.dismiss();
                                break;
                            case R.id.tv_confirm:
                                Toast.makeText(DialogActivity.this, "操作11", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void loadingDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_loading)
                .setHeight(300)
                .setWidth(300)
                .create()
                .show();
    }

    public void homeBannerDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_home_ad)
                .setScreenHeightAspect(this, 0.7f)
                .setScreenWidthAspect(this, 0.8f)
                .setAnimation(R.style.EnterExitAnimation)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        //可对图片进行修改
                    }
                })
                .addOnClickListener(R.id.iv_close)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void updateHead(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_change_avatar)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .addOnClickListener(R.id.tv_open_camera, R.id.tv_open_album, R.id.tv_cancel)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_open_camera:
                                Toast.makeText(DialogActivity.this, "打开相机", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_open_album:
                                Toast.makeText(DialogActivity.this, "打开相册", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_cancel:

                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void listDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setHeight(600)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .setAdapter(new TBaseAdapter<String>(R.layout.item_simple_text, Arrays.asList(data)) {

                    @Override
                    protected void onBind(BindViewHolder holder, int position, String s) {
                        holder.setText(R.id.tv, s);
                    }
                })
                .setOnAdapterItemClickListener(new TBaseAdapter.OnAdapterItemClickListener<String>() {
                    @Override
                    public void onItemClick(BindViewHolder holder, int position, String s, TDialog tDialog) {
                        Toast.makeText(DialogActivity.this, "click:" + s, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void bottomListDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setScreenHeightAspect(this, 0.5f)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .setAnimation(R.style.EnterExitAnimation)
                .setAdapter(new TBaseAdapter<String>(R.layout.item_simple_text, Arrays.asList(data)) {
                    @Override
                    protected void onBind(BindViewHolder holder, int position, String s) {
                        holder.setText(R.id.tv, s);
                    }
                })
                .setOnAdapterItemClickListener(new TBaseAdapter.OnAdapterItemClickListener<String>() {
                    @Override
                    public void onItemClick(BindViewHolder holder, int position, String s, TDialog tDialog) {
                        Toast.makeText(DialogActivity.this, "click:" + s, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    //评价 弹出输入框
    public void evaluateDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_evaluate)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .setAnimation(R.style.EnterExitAnimation)
                .addOnClickListener(R.id.btn_evluate)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        final EditText editText = viewHolder.getView(R.id.editText);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) DialogActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                            }
                        });
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        EditText editText = viewHolder.getView(R.id.editText);
                        String content = editText.getText().toString().trim();
                        Toast.makeText(DialogActivity.this, "评价内容:" + content, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    //底部分享
    public void shareDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setListLayoutRes(R.layout.dialog_share_recycler, new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .setAnimation(R.style.EnterExitAnimation)
                .setAdapter(new TBaseAdapter<String>(R.layout.item_share, Arrays.asList(sharePlatform)) {
                    @Override
                    protected void onBind(BindViewHolder holder, int position, String s) {
                        holder.setText(R.id.tv, s);
                    }
                })
                .setOnAdapterItemClickListener(new TBaseAdapter.OnAdapterItemClickListener<String>() {
                    @Override
                    public void onItemClick(BindViewHolder holder, int position, String item, TDialog tDialog) {
                        Toast.makeText(DialogActivity.this, item, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

}
