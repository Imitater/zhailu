package com.mouqukeji.hmdeer.bean;

public class MyVipBean {

    /**
     * vip : {"vip_id":"1","vip_num":"0","vip_term":"1970-01-04","vip_name":"体验卡","vip_image":""}
     */

    private VipBean vip;

    public VipBean getVip() {
        return vip;
    }

    public void setVip(VipBean vip) {
        this.vip = vip;
    }

    public static class VipBean {
        /**
         * vip_id : 1
         * vip_num : 0
         * vip_term : 1970-01-04
         * vip_name : 体验卡
         * vip_image :
         */

        private String vip_id;
        private String vip_num;
        private String vip_term;
        private String vip_name;
        private String vip_image;

        public String getVip_id() {
            return vip_id;
        }

        public void setVip_id(String vip_id) {
            this.vip_id = vip_id;
        }

        public String getVip_num() {
            return vip_num;
        }

        public void setVip_num(String vip_num) {
            this.vip_num = vip_num;
        }

        public String getVip_term() {
            return vip_term;
        }

        public void setVip_term(String vip_term) {
            this.vip_term = vip_term;
        }

        public String getVip_name() {
            return vip_name;
        }

        public void setVip_name(String vip_name) {
            this.vip_name = vip_name;
        }

        public String getVip_image() {
            return vip_image;
        }

        public void setVip_image(String vip_image) {
            this.vip_image = vip_image;
        }
    }
}
