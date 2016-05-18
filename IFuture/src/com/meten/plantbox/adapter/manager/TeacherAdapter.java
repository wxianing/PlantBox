package com.meten.plantbox.adapter.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.adapter.CustomBaseAdapter;
import com.meten.plantbox.constant.Constant;
import com.meten.plantbox.model.Teacher;
import com.meten.plantbox.view.CircularImage;

/**
 * Created by Cmad on 2015/3/10.
 */
public class TeacherAdapter extends CustomBaseAdapter<Teacher> {
    private int type;
    public TeacherAdapter(Context context) {
        super(context);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            convertView = listContainer.inflate(R.layout.praise_item,null);
            holder = new Holder();
            holder.headImg = (CircularImage) convertView.findViewById(R.id.head_img);
            holder.tvName = (TextView) convertView.findViewById(R.id.name_tv);
            holder.tvRole = (TextView) convertView.findViewById(R.id.role_tv);
            holder.tvPraiseCount = (TextView) convertView.findViewById(R.id.praise_count_tv);
            convertView.setTag(holder);
            if(type == Constant.MANAGER){
                holder.tvPraiseCount.setTextColor(context.getResources().getColor(R.color.complain_textcolor));
                Drawable drawable = context.getResources().getDrawable(R.drawable.ic_complain_pressed);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                holder.tvPraiseCount.setCompoundDrawables(drawable,null,null,null);
            }
        }else{
            holder = (Holder) convertView.getTag();
        }

        Teacher teacher = listData.get(position);
        holder.headImg.setImageUrl(teacher.getPhoto());
        holder.tvName.setText(teacher.getName());
        holder.tvRole.setText(teacher.getTeacherRoleName());
        holder.tvPraiseCount.setText(teacher.getNum()+"");

        return convertView;
    }

    private class Holder {
        CircularImage headImg;
        TextView tvName;
        TextView tvRole;
        TextView tvPraiseCount;
    }
}