package com.mouqukeji.hmdeer.bean;

public class DetailIntegralBean {

    /**
     * goods : {"title":"1元优惠券","goods_id":"1","price":"0.00","integral":"15","picture":"https://api.hmdeer.com/static/image/integral_1.png","cid":"1","vip_id":"0","type":"1","num":"1","prefix":"259200","coupon_type":"1","cate_id":"10"}
     */

    private GoodsBean goods;

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * title : 1元优惠券
         * goods_id : 1
         * price : 0.00
         * integral : 15
         * picture : https://api.hmdeer.com/static/image/integral_1.png
         * cid : 1
         * vip_id : 0
         * type : 1
         * num : 1
         * prefix : 259200
         * coupon_type : 1
         * cate_id : 10
         */

        private String title;
        private String goods_id;
        private String price;
        private String integral;
        private String picture;
        private String cid;
        private String vip_id;
        private String type;
        private String num;
        private String prefix;
        private String coupon_type;
        private String cate_id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getVip_id() {
            return vip_id;
        }

        public void setVip_id(String vip_id) {
            this.vip_id = vip_id;
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

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(String coupon_type) {
            this.coupon_type = coupon_type;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }
    }
}
