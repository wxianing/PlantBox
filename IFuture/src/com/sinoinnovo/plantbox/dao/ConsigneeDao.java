package com.sinoinnovo.plantbox.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.sinoinnovo.plantbox.dao.helper.DatabaseHelper;
import com.sinoinnovo.plantbox.dao.model.Consignee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class ConsigneeDao {
    private Context context;
    private Dao<Consignee, Integer> consigDaoOpe;
    private DatabaseHelper helper;

    public ConsigneeDao(Context context) {
        this.context = context;
        helper = DatabaseHelper.getHelper(context);
        try {
            consigDaoOpe = helper.getDao(Consignee.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Consignee con) {
        try {
            consigDaoOpe.create(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //根据Id查询
    public Consignee queryById(int id) {
        try {
            return consigDaoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询所有的数据
    public List<Consignee> queryByAll() {
        List<Consignee> list = null;
        try {
            list = consigDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
