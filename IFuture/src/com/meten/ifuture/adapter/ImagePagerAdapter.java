package com.meten.ifuture.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.meten.ifuture.R;
import com.meten.ifuture.widget.RecyclingPagerAdapter;

/**
 * ImagePagerAdapter
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter implements ViewPager.OnPageChangeListener {

    private Context context;
    private int[] imageUrls;
    private boolean isInfiniteLoop;
    //圆点的父布局
    private LinearLayout dotLL;

    public ImagePagerAdapter(Context context, int[] imageUrls, LinearLayout dotLL) {
        this.context = context;
        this.imageUrls = imageUrls;
        isInfiniteLoop = false;
        this.dotLL = dotLL;

    }

    @Override
    public int getCount() {
        return isInfiniteLoop ? Integer.MAX_VALUE : imageUrls.length;
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % imageUrls.length : position;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageResource(imageUrls[getPosition(position)]);
//        x.image().bind(holder.imageView, UrlUitls.BASE_IMAGE_URL + iamgeUrls.get(getPosition(position)).getImages());
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imageUrls.length; i++) {
            if (getPosition(position) == i) {
                ((ImageView) dotLL.getChildAt(i)).setImageResource(R.drawable.dot_press);
            } else {
                ((ImageView) dotLL.getChildAt(i)).setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static class ViewHolder {
        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    public void refreshData(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        this.notifyDataSetChanged();
        addDotView();
    }

    /**
     * 动态添加小圆点
     */
    private void addDotView() {
        for (int i = 0; i < imageUrls.length; i++) {
            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.rightMargin = 10;
            dotView.setLayoutParams(param);
            if (i == 0) {
                dotView.setImageResource(R.drawable.dot_press);
            } else {
                dotView.setImageResource(R.drawable.dot_normal);
            }
            dotLL.addView(dotView);
        }
    }
}
