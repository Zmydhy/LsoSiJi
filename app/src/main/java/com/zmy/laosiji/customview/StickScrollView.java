package com.zmy.laosiji.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.zmy.laosiji.utils.ConstantUtil;

/**
 * Created by Michael on 2017/11/27.
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
 */

public class StickScrollView extends ViewGroup {

    private int width;
    private int height;

    public StickScrollView(Context context) {
        super(context);
    }

    public StickScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 测量所有子 View
        // 将所有子 View 的高度叠加
        // 为自己的测量高度赋值

        width = MeasureSpec.getSize(widthMeasureSpec); // 记录每个字 View 的宽度度
        height = MeasureSpec.getSize(heightMeasureSpec); // 记录每个字 View 的宽度度

        int childCount = getChildCount();
        int totalHeight = height * childCount; // 计算 StickScrollView 的高度

        for (int i = 0; i < childCount; i++) { // 遍历所有子 View 调用其 measure 方法，并将 MeasureSpec 传入
            getChildAt(i).measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        }
        ConstantUtil.log_e("width:"+widthMeasureSpec+",height"+height+",totalHeight"+totalHeight);
        setMeasuredDimension(width, totalHeight); // 由所以子 View 的高度确定自己的高度
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 将所有子 View 都放入合适的位置

        int nextTop = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(0, nextTop, width, nextTop + height);
            nextTop += height;
            ConstantUtil.log_e("nextTop"+nextTop+",height"+height+",l"+l+",t"+t+",r"+r+",b"+b);
        }
    }

    private float perScrollY; // 记录上一次的触摸位置
    private float startY; // 记录一次完整的滑动的起始位置
    private int oldScrollY; // 记录一次完整的滑动开始时 ViewGroup 内容的偏移量
    private Scroller scroller = new Scroller(getContext());

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 事件来临时拿到触摸点
        // 新事件来临时根据触摸点偏移量进行滑动

        float y = event.getY();
        float scrollY = y - perScrollY;
        float scrollIndex = y - startY;


        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                scrollBy(0, (int) -(scrollY));
                perScrollY = y;
                ConstantUtil.log_e("MOVE_perScrollY:"+perScrollY);
                break;
            case MotionEvent.ACTION_DOWN:
                perScrollY = startY = event.getY();
                oldScrollY = getScrollY();
                ConstantUtil.log_e("DOWN_y:"+y+",DOWN_getScrollY():"+getScrollY());
                break;
            case MotionEvent.ACTION_UP:
                int scrollTo = 0;
                if (getScrollY() < 0)
                    scrollTo = oldScrollY - getScrollY();
                else if (getScrollY() > (getChildCount() - 1) * height)
                    scrollTo = -(getScrollY() - (getChildCount() - 1) * height);
                else if (scrollIndex > height / 2) {
                    scrollTo = (getScrollY() / height) * height - getScrollY();
                } else if (scrollIndex < height / 2 && scrollIndex > 0) {
                    scrollTo = oldScrollY - getScrollY();
                } else if (scrollIndex < (-height / 2)) {
                    scrollTo = (getScrollY() / height + 1) * height - getScrollY();
                } else if (scrollIndex > (-height / 2) && scrollIndex < 0)
                    scrollTo = (int) scrollIndex;
                ConstantUtil.log_e("UP_y:"+y+"UP_getScrollY():"+getScrollY());
                scroller.startScroll(0, getScrollY(), 0, scrollTo);
                invalidate();
                break;
                default:
                    break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (null != scroller && scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
