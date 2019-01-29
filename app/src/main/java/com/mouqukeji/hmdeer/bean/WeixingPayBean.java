package com.mouqukeji.hmdeer.bean;

public class WeixingPayBean {

    /**
     * pay : {"payInfo":"{\"prepayid\":\"wx23191323911865078f40e2d52824554644\",\"appid\":\"wxebcaadacc2d9218c\",\"partnerid\":\"1521589321\",\"package\":\"Sign=WXPay\",\"noncestr\":\"d2041e5c4c143f1362b6fee8720d5863\",\"timestamp\":1548242003,\"paySign\":\"802C363BA2CF14B0F93BE036B088EC28\"}"}
     * orders : {"task_id":"128","progress":"1","cate_id":"11"}
     */

    private PayBean pay;
    private OrdersBean orders;

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public static class PayBean {
        /**
         * payInfo : {"prepayid":"wx23191323911865078f40e2d52824554644","appid":"wxebcaadacc2d9218c","partnerid":"1521589321","package":"Sign=WXPay","noncestr":"d2041e5c4c143f1362b6fee8720d5863","timestamp":1548242003,"paySign":"802C363BA2CF14B0F93BE036B088EC28"}
         */

        private String payInfo;

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }
    }

    public static class OrdersBean {
        /**
         * task_id : 128
         * progress : 1
         * cate_id : 11
         */

        private String task_id;
        private String progress;
        private String cate_id;

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }
    }
}
