package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class VipListBean {

    private List<VipsBean> vips;

    public List<VipsBean> getVips() {
        return vips;
    }

    public void setVips(List<VipsBean> vips) {
        this.vips = vips;
    }

    public static class VipsBean {
        /**
         * vip_id : 2
         * price : 9.90
         * cost_price : 9.90
         * num : 7
         * vip_image :
         * is_discount : 0
         * prefix : 604800
         */

        private String vip_id;
        private String price;
        private String cost_price;
        private String num;
        private String vip_image;
        private String is_discount;
        private String prefix;

        public String getVip_id() {
            return vip_id;
        }

        public void setVip_id(String vip_id) {
            this.vip_id = vip_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getVip_image() {
            return vip_image;
        }

        public void setVip_image(String vip_image) {
            this.vip_image = vip_image;
        }

        public String getIs_discount() {
            return is_discount;
        }

        public void setIs_discount(String is_discount) {
            this.is_discount = is_discount;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
    }
}
