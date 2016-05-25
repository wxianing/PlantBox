package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.bean.comment.Comments;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class CommentListAdapter extends BasicAdapter<Comments.DataListBean> {
    public CommentListAdapter(List<Comments.DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_comment_list_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.comments.setText(data.get(position).getContent());

        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.comment_conents)
        protected TextView comments;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
