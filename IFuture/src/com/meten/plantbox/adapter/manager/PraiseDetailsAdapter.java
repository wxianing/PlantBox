package com.meten.plantbox.adapter.manager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.adapter.CustomBaseAdapter;
import com.meten.plantbox.model.student.Complain;
import com.meten.plantbox.utils.DateUtils;
import com.meten.plantbox.view.CircularImage;

/**
 * Created by Cmad on 2015/3/21.
 */
public class PraiseDetailsAdapter extends CustomBaseAdapter<Complain> {
    /**
     * CustomBaseAdapter
     *
     * @param context
     */
    public PraiseDetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            convertView = listContainer.inflate(R.layout.manager_praise_details,null);
            holder = new Holder();
            holder.headImg = (CircularImage) convertView.findViewById(R.id.head_img);
            holder.tvName = (TextView) convertView.findViewById(R.id.name_tv);
            holder.tvDate = (TextView) convertView.findViewById(R.id.date_tv);
            holder.headImg.setHasBorder(true);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        Complain c = listData.get(position);
        holder.tvName.setText(c.getFromCnName());
        holder.headImg.setImageUrl(c.getFromUserPhoto());
        holder.tvDate.setText(DateUtils.getDateToString(c.getCreateTime(),"yyyy.MM.dd"));
        return convertView;
    }

    private class Holder {
        CircularImage headImg;
        TextView tvName;
        TextView tvDate;
    }
}
