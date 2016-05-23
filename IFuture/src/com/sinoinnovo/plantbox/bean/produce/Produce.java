package com.sinoinnovo.plantbox.bean.produce;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class Produce implements Serializable{

    private int PageIndex;
    private int RecordCount;
    private List<DataListBean> DataList;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int recordCount) {
        RecordCount = recordCount;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        DataList = dataList;
    }
}
