package com.meten.plantbox.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class TestBean {


    /**
     * code : 0
     * enumcode : 0
     * msg : success
     * data : {"ProductEntitys":[{"ProductBatchs":[{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}],"Id":1,"ProductId":2,"ProductModel":"sample string 3","Inventory":1,"SalePrice":1,"CostPrice":1,"CreateTime":"2016-05-19 09:24:19","AdvancePrice":1,"Grade":1,"NeedBatchs":1},{"ProductBatchs":[{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}],"Id":1,"ProductId":2,"ProductModel":"sample string 3","Inventory":1,"SalePrice":1,"CostPrice":1,"CreateTime":"2016-05-19 09:24:19","AdvancePrice":1,"Grade":1,"NeedBatchs":1},{"ProductBatchs":[{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}],"Id":1,"ProductId":2,"ProductModel":"sample string 3","Inventory":1,"SalePrice":1,"CostPrice":1,"CreateTime":"2016-05-19 09:24:19","AdvancePrice":1,"Grade":1,"NeedBatchs":1}],"Pictures":["sample string 1","sample string 2","sample string 3"],"MinAdvancePrice":1,"MinSalePrice":2,"Id":3,"SortId":4,"ProductName":"sample string 5","ProductCode":"sample string 6","Introduce":"sample string 7","CycleDays":1,"CreateUserId":1,"CreateUserName":"sample string 8","CreateTime":"2016-05-19 09:24:19","Status":1,"ThumbImg":"sample string 9","IsDeleted":1,"TotalRead":1,"TotalCollect":1,"Hits":1,"ReleaseTime":"2016-05-19 09:24:19","NeedBatchs":1,"AdvertImg":"sample string 10","BuyMode":"sample string 11","Mobile":"sample string 12","Notice":"sample string 13"}
     */

    private int code;
    private int enumcode;
    private String msg;
    /**
     * ProductEntitys : [{"ProductBatchs":[{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}],"Id":1,"ProductId":2,"ProductModel":"sample string 3","Inventory":1,"SalePrice":1,"CostPrice":1,"CreateTime":"2016-05-19 09:24:19","AdvancePrice":1,"Grade":1,"NeedBatchs":1},{"ProductBatchs":[{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}],"Id":1,"ProductId":2,"ProductModel":"sample string 3","Inventory":1,"SalePrice":1,"CostPrice":1,"CreateTime":"2016-05-19 09:24:19","AdvancePrice":1,"Grade":1,"NeedBatchs":1},{"ProductBatchs":[{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}],"Id":1,"ProductId":2,"ProductModel":"sample string 3","Inventory":1,"SalePrice":1,"CostPrice":1,"CreateTime":"2016-05-19 09:24:19","AdvancePrice":1,"Grade":1,"NeedBatchs":1}]
     * Pictures : ["sample string 1","sample string 2","sample string 3"]
     * MinAdvancePrice : 1.0
     * MinSalePrice : 2.0
     * Id : 3
     * SortId : 4
     * ProductName : sample string 5
     * ProductCode : sample string 6
     * Introduce : sample string 7
     * CycleDays : 1
     * CreateUserId : 1
     * CreateUserName : sample string 8
     * CreateTime : 2016-05-19 09:24:19
     * Status : 1
     * ThumbImg : sample string 9
     * IsDeleted : 1
     * TotalRead : 1
     * TotalCollect : 1
     * Hits : 1
     * ReleaseTime : 2016-05-19 09:24:19
     * NeedBatchs : 1
     * AdvertImg : sample string 10
     * BuyMode : sample string 11
     * Mobile : sample string 12
     * Notice : sample string 13
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEnumcode() {
        return enumcode;
    }

    public void setEnumcode(int enumcode) {
        this.enumcode = enumcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private double MinAdvancePrice;
        private double MinSalePrice;
        private int Id;
        private int SortId;
        private String ProductName;
        private String ProductCode;
        private String Introduce;
        private int CycleDays;
        private int CreateUserId;
        private String CreateUserName;
        private String CreateTime;
        private int Status;
        private String ThumbImg;
        private int IsDeleted;
        private int TotalRead;
        private int TotalCollect;
        private int Hits;
        private String ReleaseTime;
        private int NeedBatchs;
        private String AdvertImg;
        private String BuyMode;
        private String Mobile;
        private String Notice;
        /**
         * ProductBatchs : [{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"},{"BatchId":1,"ProductId":2,"ProductEntityId":1,"Numbers":1,"Months":1,"Days":3,"CreateTime":"2016-05-19 09:24:19"}]
         * Id : 1
         * ProductId : 2
         * ProductModel : sample string 3
         * Inventory : 1
         * SalePrice : 1.0
         * CostPrice : 1.0
         * CreateTime : 2016-05-19 09:24:19
         * AdvancePrice : 1.0
         * Grade : 1
         * NeedBatchs : 1
         */

        private List<ProductEntitysBean> ProductEntitys;
        private List<String> Pictures;

        public double getMinAdvancePrice() {
            return MinAdvancePrice;
        }

        public void setMinAdvancePrice(double MinAdvancePrice) {
            this.MinAdvancePrice = MinAdvancePrice;
        }

        public double getMinSalePrice() {
            return MinSalePrice;
        }

        public void setMinSalePrice(double MinSalePrice) {
            this.MinSalePrice = MinSalePrice;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getSortId() {
            return SortId;
        }

        public void setSortId(int SortId) {
            this.SortId = SortId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getProductCode() {
            return ProductCode;
        }

        public void setProductCode(String ProductCode) {
            this.ProductCode = ProductCode;
        }

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
        }

        public int getCycleDays() {
            return CycleDays;
        }

        public void setCycleDays(int CycleDays) {
            this.CycleDays = CycleDays;
        }

        public int getCreateUserId() {
            return CreateUserId;
        }

        public void setCreateUserId(int CreateUserId) {
            this.CreateUserId = CreateUserId;
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getThumbImg() {
            return ThumbImg;
        }

        public void setThumbImg(String ThumbImg) {
            this.ThumbImg = ThumbImg;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public int getTotalRead() {
            return TotalRead;
        }

        public void setTotalRead(int TotalRead) {
            this.TotalRead = TotalRead;
        }

        public int getTotalCollect() {
            return TotalCollect;
        }

        public void setTotalCollect(int TotalCollect) {
            this.TotalCollect = TotalCollect;
        }

        public int getHits() {
            return Hits;
        }

        public void setHits(int Hits) {
            this.Hits = Hits;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String ReleaseTime) {
            this.ReleaseTime = ReleaseTime;
        }

        public int getNeedBatchs() {
            return NeedBatchs;
        }

        public void setNeedBatchs(int NeedBatchs) {
            this.NeedBatchs = NeedBatchs;
        }

        public String getAdvertImg() {
            return AdvertImg;
        }

        public void setAdvertImg(String AdvertImg) {
            this.AdvertImg = AdvertImg;
        }

        public String getBuyMode() {
            return BuyMode;
        }

        public void setBuyMode(String BuyMode) {
            this.BuyMode = BuyMode;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getNotice() {
            return Notice;
        }

        public void setNotice(String Notice) {
            this.Notice = Notice;
        }

        public List<ProductEntitysBean> getProductEntitys() {
            return ProductEntitys;
        }

        public void setProductEntitys(List<ProductEntitysBean> ProductEntitys) {
            this.ProductEntitys = ProductEntitys;
        }

        public List<String> getPictures() {
            return Pictures;
        }

        public void setPictures(List<String> Pictures) {
            this.Pictures = Pictures;
        }

        public static class ProductEntitysBean {
            private int Id;
            private int ProductId;
            private String ProductModel;
            private int Inventory;
            private double SalePrice;
            private double CostPrice;
            private String CreateTime;
            private double AdvancePrice;
            private int Grade;
            private int NeedBatchs;
            /**
             * BatchId : 1
             * ProductId : 2
             * ProductEntityId : 1
             * Numbers : 1
             * Months : 1
             * Days : 3
             * CreateTime : 2016-05-19 09:24:19
             */

            private List<ProductBatchsBean> ProductBatchs;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getProductId() {
                return ProductId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public String getProductModel() {
                return ProductModel;
            }

            public void setProductModel(String ProductModel) {
                this.ProductModel = ProductModel;
            }

            public int getInventory() {
                return Inventory;
            }

            public void setInventory(int Inventory) {
                this.Inventory = Inventory;
            }

            public double getSalePrice() {
                return SalePrice;
            }

            public void setSalePrice(double SalePrice) {
                this.SalePrice = SalePrice;
            }

            public double getCostPrice() {
                return CostPrice;
            }

            public void setCostPrice(double CostPrice) {
                this.CostPrice = CostPrice;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public double getAdvancePrice() {
                return AdvancePrice;
            }

            public void setAdvancePrice(double AdvancePrice) {
                this.AdvancePrice = AdvancePrice;
            }

            public int getGrade() {
                return Grade;
            }

            public void setGrade(int Grade) {
                this.Grade = Grade;
            }

            public int getNeedBatchs() {
                return NeedBatchs;
            }

            public void setNeedBatchs(int NeedBatchs) {
                this.NeedBatchs = NeedBatchs;
            }

            public List<ProductBatchsBean> getProductBatchs() {
                return ProductBatchs;
            }

            public void setProductBatchs(List<ProductBatchsBean> ProductBatchs) {
                this.ProductBatchs = ProductBatchs;
            }

            public static class ProductBatchsBean {
                private int BatchId;
                private int ProductId;
                private int ProductEntityId;
                private int Numbers;
                private int Months;
                private int Days;
                private String CreateTime;

                public int getBatchId() {
                    return BatchId;
                }

                public void setBatchId(int BatchId) {
                    this.BatchId = BatchId;
                }

                public int getProductId() {
                    return ProductId;
                }

                public void setProductId(int ProductId) {
                    this.ProductId = ProductId;
                }

                public int getProductEntityId() {
                    return ProductEntityId;
                }

                public void setProductEntityId(int ProductEntityId) {
                    this.ProductEntityId = ProductEntityId;
                }

                public int getNumbers() {
                    return Numbers;
                }

                public void setNumbers(int Numbers) {
                    this.Numbers = Numbers;
                }

                public int getMonths() {
                    return Months;
                }

                public void setMonths(int Months) {
                    this.Months = Months;
                }

                public int getDays() {
                    return Days;
                }

                public void setDays(int Days) {
                    this.Days = Days;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String CreateTime) {
                    this.CreateTime = CreateTime;
                }
            }
        }
    }
}
