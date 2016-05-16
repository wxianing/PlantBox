package com.meten.plantbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.bean.message.Child;
import com.meten.plantbox.bean.message.Group;


import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<Group> list;
    private LayoutInflater inflater;

    public ExpandableAdapter(List<Group> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildList().size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupVieHolder gvh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_grou_layout, parent, false);
            gvh = new GroupVieHolder(convertView);
            convertView.setTag(gvh);

        } else {
            gvh = (GroupVieHolder) convertView.getTag();
        }
        gvh.title.setText(list.get(groupPosition).getTitle());
        gvh.count.setText("2/" + list.get(groupPosition).getChildList().size());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Group group = list.get(groupPosition);
        Child ch = group.getChildList().get(childPosition);
        ChildViewHolder cvh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child_layout, parent, false);
            cvh = new ChildViewHolder(convertView);
            convertView.setTag(cvh);

        } else {
            cvh = (ChildViewHolder) convertView.getTag();
        }
        cvh.icon.setBackgroundResource(ch.getIcon());
        cvh.nameTv.setText(ch.getName());

        return convertView;
    }

    private static class ChildViewHolder {
        private TextView nameTv;
        private ImageView icon;

        public ChildViewHolder(View convertView) {
            nameTv = (TextView) convertView.findViewById(R.id.item_child_name);
            icon = (ImageView) convertView.findViewById(R.id.item_child_icon);
        }
    }

    public class GroupVieHolder {
        private TextView title;
        private TextView count;

        public GroupVieHolder(View convertView) {
            title = (TextView) convertView.findViewById(R.id.item_group_title);
            count = (TextView) convertView.findViewById(R.id.count);
        }
    }


    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
