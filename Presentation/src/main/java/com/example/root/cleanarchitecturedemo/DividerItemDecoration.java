package com.example.root.cleanarchitecturedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by root on 11/8/17.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable divider;

    public DividerItemDecoration(Context context, int resid){
        divider= ContextCompat.getDrawable(context,resid);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();

        int childCount=parent.getChildCount();
        for (int i=0;i<childCount-1;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams=(RecyclerView.LayoutParams)child.getLayoutParams();
            int top=child.getBottom()+layoutParams.bottomMargin;
            int bottom=top+divider.getIntrinsicHeight();
            divider.setBounds(left,top,right,bottom);
            divider.draw(c);
        }
    }
}
