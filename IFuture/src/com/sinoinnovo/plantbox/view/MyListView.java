package com.sinoinnovo.plantbox.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }

    public String androidPost() {
        String rs = null;
        String path = "url/Android_JDBC_SH/AndroidLoginAction";
        HttpPost hp = new HttpPost(path);
        //获取客户端，用来向服务器发出请求
        DefaultHttpClient hc = new DefaultHttpClient();
        try { //Default Constructor taking a name and a value
            BasicNameValuePair nm = new BasicNameValuePair("name", "name");
            BasicNameValuePair pa = new BasicNameValuePair("password", "password");
            List list = new ArrayList();
            list.add(nm);
            list.add(pa);
            //构建向服务器发送的实体
            HttpEntity entity = new UrlEncodedFormEntity(list);
            hp.setEntity(entity);
            //提交请求，获取服务器的响应
            HttpResponse response = hc.execute(hp);
            if (
                    response.getStatusLine().getStatusCode() == 200) {
                //获取响应实体
                entity = response.getEntity();
                rs = EntityUtils.toString(entity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
