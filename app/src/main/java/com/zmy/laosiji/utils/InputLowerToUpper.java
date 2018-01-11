package com.zmy.laosiji.utils;

import android.text.method.ReplacementTransformationMethod;

/**
 * 小写转为大写
 * 使用方法如下
 *         editetext.setTransformationMethod(new InputLowerToUpper());
 */
public class InputLowerToUpper extends ReplacementTransformationMethod {

    @Override
    protected char[] getOriginal() {
        return Constant.LOWERLETTER;
    }
    @Override
    protected char[] getReplacement() {
        return Constant.UPPERLETTER;
    }

}
