package com.meten.plantbox.bean.shoplist;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16 0016.
 * "PageIndex": 1,
 * "RecordCount": 4,
 */
public class ShopListBean implements Serializable {
    private int PageIndex;
    private int RecordCount;
    private List<SHopDataList> DataList;

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

    public List<SHopDataList> getDataList() {
        return DataList;
    }

    public void setDataList(List<SHopDataList> dataList) {
        DataList = dataList;
    }
}
