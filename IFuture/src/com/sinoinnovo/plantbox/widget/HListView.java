package com.sinoinnovo.plantbox.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class HListView extends ViewGroup {
    private ListAdapter listAdapter;

    public HListView(Context context) {
        super(context);
    }

    public HListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //@Override
    public ListAdapter getAdapter() {
        return listAdapter;
    }

    //@Override
    public void setAdapter(ListAdapter adapter) {
        this.listAdapter = adapter;
    }


    private View measureChild(View view) {
        LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
        }

        view.measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(getHeight(),
                MeasureSpec.AT_MOST));
        return view;

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        if (listAdapter == null) {
            return;
        }
        Log.e("", "children count==" + getChildCount());//�տ�ʼ��0��

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View child = listAdapter.getView(i, null, null);
            child = measureChild(child);
            addView(child);
        }

        //����viewlayout��viewGroup��
        int childLeft = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            child.layout(childLeft, 0, childWidth + childLeft, child.getMeasuredHeight());
            //childLeft = child.getLeft();
            //childLeft += childWidth;
            //������õ�д����
            childLeft += childWidth + child.getPaddingRight();
        }
    }

}
