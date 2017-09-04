package com.app.abby.perfectweather.model.RecyclerDecor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.app.abby.perfectweather.model.data.CityBean;
import com.app.abby.perfectweather.util.Util;

import java.util.List;

/**
 * Created by Abby on 8/30/2017.
 */

public class GroupedDecoration extends RecyclerView.ItemDecoration{

    private List<CityBean> mDatas;
    private int mTitleHeight;
    private int mTextSize;
    private Context mContext;
    private Paint mPaint;

    private static int COLOR_TITLE_BG = Color.parseColor("#FFDFDFDF");
    private static int COLOR_TITLE_FONT = Color.parseColor("#FF000000");

    private Rect mBounds;

    public GroupedDecoration(Context context, List<CityBean> datas){
        mDatas=datas;
        mContext=context;
        mPaint=new Paint();
        mBounds=new Rect();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16, context.getResources().getDisplayMetrics());
        mTextSize=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTextSize);
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){

        super.getItemOffsets(outRect,view,parent,state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        if (position > -1) {
            if (position == 0) {
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                try {
                    if (null != mDatas.get(position).getTag() && !mDatas.get(position).getTag().equals(mDatas.get(position - 1).getTag())) {
                        outRect.set(0, mTitleHeight, 0, 0);//draw the title when dif from the former one
                    } else {
                        outRect.set(0, 0, 0, 0);
                    }
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }

            }
        }


    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            //position may be -1 when reset
            c.drawLine(left,child.getBottom()-params.bottomMargin,right,child.getBottom()-params.bottomMargin,mPaint);

            try {
                if (position > -1) {
                    if (position == 0) {
                        //pos==0 means it needs a title
                        drawTitleArea(c, left, right, child, params, position);

                    } else {
                        if (null != mDatas.get(position).getTag() && !mDatas.get(position).getTag().equals(mDatas.get(position - 1).getTag())) {

                            drawTitleArea(c, left, right, child, params, position);
                        } else {

                        }
                    }
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }

        }
    }


    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {

        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight, right, child.getTop() - params.topMargin, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
/*
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;*/

        mPaint.getTextBounds(mDatas.get(position).getTag(), 0, mDatas.get(position).getTag().length(), mBounds);
        c.drawText(mDatas.get(position).getTag(), child.getPaddingLeft(), child.getTop() - params.topMargin - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
    }

}
