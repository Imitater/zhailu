package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class AllOrderBean {


    /**
     * tasks : [{"order_sn":"2019022713152056499856","task_id":"494","cate_id":"11","cate_name":"帮忙取","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-2-27 13:15","progress":"1","order_id":"438","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022713151349579910","task_id":"493","cate_id":"11","cate_name":"帮忙取","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-2-27 13:15","progress":"1","order_id":"437","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022713090953495452","task_id":"492","cate_id":"11","cate_name":"帮忙取","pay_fee":"2.00","task_price":"2.00","delivery_time":"2019-2-27 13:9","progress":"1","order_id":"436","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022710185749971025","task_id":"491","cate_id":"12","cate_name":"帮忙买","pay_fee":"1.00","task_price":"2.00","delivery_time":"2019-2-27 10:18","progress":"2","order_id":"435","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022710002910057499","task_id":"490","cate_id":"11","cate_name":"帮忙取","pay_fee":"0.00","task_price":"2.00","delivery_time":"2019-2-27 10:0","progress":"2","order_id":"434","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022709584250535749","task_id":"489","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"2.00","delivery_time":"2019-2-27 9:58","progress":"1","order_id":"433","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022709474650545448","task_id":"488","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-2-27 9:47","progress":"1","order_id":"432","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022709474099100505","task_id":"487","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-2-27 9:47","progress":"1","order_id":"431","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022709472056985451","task_id":"486","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-2-27 9:47","progress":"1","order_id":"430","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"},{"order_sn":"2019022709471450515051","task_id":"485","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-2-27 9:47","progress":"1","order_id":"429","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0","server_id":"0"}]
     * pages : 6
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
         * order_sn : 2019022713152056499856
         * task_id : 494
         * cate_id : 11
         * cate_name : 帮忙取
         * pay_fee : 2.00
         * task_price : 2.00
         * delivery_time : 2019-2-27 13:15
         * progress : 1
         * order_id : 438
         * makeup_fee : 0.00
         * express_pay_type : 1
         * makeup_id : 0
         * server_id : 0
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
    }
}
