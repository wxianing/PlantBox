package com.meten.plantbox.bean.nearby;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class NearByData implements Serializable {
    private List<NearByDataList> DataList;
    private int RecordCount;
    private int PageIndex;

    public List<NearByDataList> getDataList() {
        return DataList;
    }

    public void setDataList(List<NearByDataList> dataList) {
        DataList = dataList;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int recordCount) {
        RecordCount = recordCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }
}
