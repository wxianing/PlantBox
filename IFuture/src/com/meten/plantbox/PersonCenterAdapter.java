package com.meten.plantbox;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.adapter.BasicAdapter;
import com.meten.plantbox.bean.PersonCenter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11 0011.
 */
public class PersonCenterAdapter extends BasicAdapter<PersonCenter> {


    public PersonCenterAdapter(List<PersonCenter> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_person_center, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.imageView.setImageResource(data.get(position).getResId());
        vh.nameText.setText(data.get(position).getResName());

        return convertView;
    }

    private static class ViewHolder {
        private ImageView imageView;
        private TextView nameText;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageView);
            nameText = (TextView) view.findViewById(R.id.name_textview);
        }
    }
}
