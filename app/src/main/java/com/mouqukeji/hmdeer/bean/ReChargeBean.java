package com.mouqukeji.hmdeer.bean;

public class ReChargeBean {

    /**
     * payInfo : {"prepayid":"wx23145649101017e798590e600147117820","appid":"wxebcaadacc2d9218c","partnerid":"1521589321","package":"Sign=WXPay","noncestr":"6645dc6cef55533d80eaab4a2a35b989","timestamp":1548226609,"paySign":"532EC1C51F1A3816B9A636EF9E2511E0"}
     */

    private String payInfo;

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }
}
