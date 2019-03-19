package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class AllOrderBean {


    /**
     * tasks : [{"order_sn":"2019030912012957565056","task_id":"656","cate_id":"12","cate_name":"帮忙买","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-3-9 12:01","progress":"8","order_id":"600","makeup_fee":"11.00","express_pay_type":"1","makeup_id":"21","server_id":"29","picture":"http://picture.mouqukeji.com/icon_1533892019-21-9"},{"order_sn":"2019030819160250549849","task_id":"645","cate_id":"12","cate_name":"帮忙买","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-3-8 19:16","progress":"8","order_id":"589","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"17","server_id":"29","picture":"http://picture.mouqukeji.com/icon_5702072019-21-8"},{"order_sn":"2019030816560553979954","task_id":"632","cate_id":"11","cate_name":"帮忙取","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-3-8 16:56","progress":"3","order_id":"576","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"29","picture":""},{"order_sn":"2019030813253097485710","task_id":"624","cate_id":"14","cate_name":"帮忙送","pay_fee":"2.00","task_price":"2.00","delivery_time":"立即配送","progress":"3","order_id":"568","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"29","picture":""},{"order_sn":"2019030813230910053971","task_id":"623","cate_id":"14","cate_name":"帮忙送","pay_fee":"6.00","task_price":"6.00","delivery_time":"立即配送","progress":"3","order_id":"567","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"29","picture":""},{"order_sn":"2019030812013749544848","task_id":"621","cate_id":"15","cate_name":"万能帮","pay_fee":"0.00","task_price":"0.00","delivery_time":"2019-3-8 12:1","progress":"3","order_id":"565","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"29","picture":""},{"order_sn":"2019030812004510050515","task_id":"619","cate_id":"13","cate_name":"帮忙寄","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-03-09 1:00","progress":"3","order_id":"563","makeup_fee":"0.00","express_pay_type":"2","makeup_id":"0","server_id":"23","picture":""},{"order_sn":"2019030812001198555651","task_id":"618","cate_id":"12","cate_name":"帮忙买","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-3-8 12:0","progress":"8","order_id":"562","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"16","server_id":"29","picture":"http://picture.mouqukeji.com/icon_7164972019-21-8"},{"order_sn":"2019030720365252984910","task_id":"614","cate_id":"12","cate_name":"帮忙买","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-3-7 20:36","progress":"8","order_id":"558","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"14","server_id":"29","picture":"http://picture.mouqukeji.com/icon_8167002019-21-7"},{"order_sn":"2019030714574899545457","task_id":"594","cate_id":"11","cate_name":"帮忙取","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-3-7 14:57","progress":"3","order_id":"538","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"29","picture":""}]
     * pages : 1
     */

    private int pages;
    private List<TasksBean> tasks;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<TasksBean> getTasks() {
        return tasks;
    }

    public void setTasks(List<TasksBean> tasks) {
        this.tasks = tasks;
    }

    public static class TasksBean {
        /**
         * order_sn : 2019030912012957565056
         * task_id : 656
         * cate_id : 12
         * cate_name : 帮忙买
         * pay_fee : 2.00
         * task_price : 2.00
         * delivery_time : 2019-3-9 12:01
         * progress : 8
         * order_id : 600
         * makeup_fee : 11.00
         * express_pay_type : 1
         * makeup_id : 21
         * server_id : 29
         * picture : http://picture.mouqukeji.com/icon_1533892019-21-9
         */

        private String order_sn;
        private String task_id;
        private String cate_id;
        private String cate_name;
        private String pay_fee;
        private String task_price;
        private String delivery_time;
        private String progress;
        private String order_id;
        private String makeup_fee;
        private String express_pay_type;
        private String makeup_id;
        private String server_id;
        private String picture;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getTask_price() {
            return task_price;
        }

        public void setTask_price(String task_price) {
            this.task_price = task_price;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getMakeup_fee() {
            return makeup_fee;
        }

        public void setMakeup_fee(String makeup_fee) {
            this.makeup_fee = makeup_fee;
        }

        public String getExpress_pay_type() {
            return express_pay_type;
        }

        public void setExpress_pay_type(String express_pay_type) {
            this.express_pay_type = express_pay_type;
        }

        public String getMakeup_id() {
            return makeup_id;
        }

        public void setMakeup_id(String makeup_id) {
            this.makeup_id = makeup_id;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
