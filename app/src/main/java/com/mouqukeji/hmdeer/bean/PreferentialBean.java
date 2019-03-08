package com.mouqukeji.hmdeer.bean;

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
         * coupon_id : 2
         * cate_name : 校园
         * cate_id : 10
         * type : 1
         * num : 2
         * start_time : 2019-02-26
         * prefix : 259200
         * is_use : 0
         * end_time : 2019-03-01
         */

        private String coupon_id;
        private String cate_name;
        private String cate_id;
        private String type;
        private String num;
        private String start_time;
        private String prefix;
        private String is_use;
        private String end_time;

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

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
