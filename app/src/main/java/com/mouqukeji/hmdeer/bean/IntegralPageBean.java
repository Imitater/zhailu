package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class IntegralPageBean {

    /**
     * total : 3
     * unclaimed : null
     * goods : [{"title":"1元优惠券","price":"0.00","integral":"15","picture":"https://api.hmdeer.com/static/image/integral_1.png","type":"1","goods_id":"1"},{"title":"2元优惠券","price":"0.00","integral":"30","picture":"https://api.hmdeer.com/static/image/integral_2.png","type":"1","goods_id":"2"},{"title":"vip体验卡","price":"0.00","integral":"60","picture":"https://api.hmdeer.com/static/image/integral_vip.png","type":"2","goods_id":"3"},{"title":"双肩背包","price":"0.00","integral":"400","picture":"https://api.hmdeer.com/static/image/integral_beibao.png","type":"3","goods_id":"4"},{"title":"手机","price":"6288.00","integral":"888","picture":"https://api.hmdeer.com/static/image/integral_mobile.png","type":"3","goods_id":"5"},{"title":"自行车","price":"0.00","integral":"500","picture":"https://api.hmdeer.com/static/image/integral_bike.png","type":"3","goods_id":"6"}]
     */

    private int total;
    private Object unclaimed;
    private List<GoodsBean> goods;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getUnclaimed() {
        return unclaimed;
    }

    public void setUnclaimed(Object unclaimed) {
        this.unclaimed = unclaimed;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * title : 1元优惠券
         * price : 0.00
         * integral : 15
         * picture : https://api.hmdeer.com/static/image/integral_1.png
         * type : 1
         * goods_id : 1
         */

        private String title;
        private String price;
        private String integral;
        private String picture;
        private String type;
        private String goods_id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }
    }
}
