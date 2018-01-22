package com.zmy.laosiji.widgets.animatedpieview.callback;


import android.support.annotation.NonNull;

import com.zmy.laosiji.widgets.animatedpieview.data.IPieInfo;

/**
 * Created by 大灯泡 on 2017/11/10.
 */

public interface OnPieSelectListener<T extends IPieInfo> {
    /**
     * 选中的回调
     *
     * @param pieInfo   数据实体
     * @param isScaleUp 是否浮起
     */
    void onSelectPie(@NonNull T pieInfo, boolean isScaleUp);
}
