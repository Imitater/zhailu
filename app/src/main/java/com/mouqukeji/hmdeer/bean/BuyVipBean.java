package com.mouqukeji.hmdeer.bean;

public class BuyVipBean {

    /**
     * pay : {"payInfo":"{\"prepayid\":\"wx232052153218604aff9fe2ef4130011705\",\"appid\":\"wxebcaadacc2d9218c\",\"partnerid\":\"1521589321\",\"package\":\"Sign=WXPay\",\"noncestr\":\"7baf63b945c9d88942f8d91ed07870c2\",\"timestamp\":1550926335,\"paySign\":\"696EC9CDF8BBF1F1F350B7746D524E0A\"}"}
     */

    private PayBean pay;

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public static class PayBean {
        /**
         * payInfo : {"prepayid":"wx232052153218604aff9fe2ef4130011705","appid":"wxebcaadacc2d9218c","partnerid":"1521589321","package":"Sign=WXPay","noncestr":"7baf63b945c9d88942f8d91ed07870c2","timestamp":1550926335,"paySign":"696EC9CDF8BBF1F1F350B7746D524E0A"}
         */

        private String payInfo;

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }
    }
}
