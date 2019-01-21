package com.mouqu.zhailu.zhailu.bean;

import java.util.List;

public class PreferentialBean {

    private List<CouponsBean> coupons;

    public List<CouponsBean> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsBean> coupons) {
        this.coupons = coupons;
    }

    public static class CouponsBean {
        /**
         * cate_name : 帮忙取
         * cate_id : 11
         * type : 1
         * num : 5
         * start_time : 1970-01-01
         * prefix : 0
         * is_use : 0
         * end_time : 1970-01-01
         */

        private String cate_name;
        private String cate_id;
        private String type;
        private String num;
        private String start_time;
        private String prefix;
        private String is_use;
        private String end_time;

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getIs_use() {
            return is_use;
        }

        public void setIs_use(String is_use) {
            this.is_use = is_use;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
