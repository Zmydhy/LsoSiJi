package com.zmy.laosiji.moudle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zmy.laosiji.R;
import com.zmy.laosiji.widgets.AdImageView;
import com.zmy.laosiji.widgets.AdImageViewVersion1;

import java.util.ArrayList;
import java.util.List;

public class ZhihuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);

        mRecyclerView = findViewById(R.id.id_recyclerview);

        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }

        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new CommonAdapter<String>(ZhihuActivity.this,
                R.layout.item_zhihu,
                mockDatas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                if (position > 0 && position % 7 == 0) {

                    holder.setVisible(R.id.id_iv_ad1, false);
                    holder.setVisible(R.id.id_tv_title, false);
                    holder.setVisible(R.id.id_tv_desc, false);
                    holder.setVisible(R.id.id_iv_ad, true);
                }else  if (position > 0 && position % 3 == 0) {
                    holder.setVisible(R.id.id_iv_ad1, true);
                    holder.setVisible(R.id.id_tv_title, false);
                    holder.setVisible(R.id.id_tv_desc, false);
                    holder.setVisible(R.id.id_iv_ad, false);
                } else {
                    holder.setVisible(R.id.id_tv_title, true);
                    holder.setVisible(R.id.id_tv_desc, true);
                    holder.setVisible(R.id.id_iv_ad, false);
                    holder.setVisible(R.id.id_iv_ad1, false);
                }
            }

        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /**
                 * ceshixiakeshifenzhong  chadian xianioale jingpinxiaolvguan de touguapaizhilv
                 * crash  to  app    make me  in a  new world   thanks you   my enligsh is poor
                 *  nei she zhong chu  xiao sao  touteng     nanshou  ya   hahh   haopiaoliang  a
                 *  ge bi  renqi  paoguolai chi wo de dadiao   wu han jingjixi lulian yue pao  chou
                 *  cha  de zhens  hsung
                 *   gongsigaocengbialingshaofu caodehenshuangde yibi
                 */
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdImageViewVersion1 adImageView = view.findViewById(R.id.id_iv_ad);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                    AdImageView adImage = view.findViewById(R.id.id_iv_ad1);
                    if (adImage.getVisibility() == View.VISIBLE) {
                        adImage.setDx(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });
    }

}
