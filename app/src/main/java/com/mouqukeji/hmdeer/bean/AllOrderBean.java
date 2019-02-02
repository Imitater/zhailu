package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class AllOrderBean {


    /**
     * tasks : [{"order_sn":"2019013109205656534848","task_id":"236","cate_id":"12","cate_name":"帮忙买","pay_fee":"6.00","task_price":"6.00","delivery_time":"2019-01-31 9:20","progress":"1","order_id":"233","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013016253510210149","task_id":"235","cate_id":"11","cate_name":"帮忙取","pay_fee":"6.00","task_price":"6.00","delivery_time":"2019-01-30 16:25","progress":"2","order_id":"232","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013016122254979955","task_id":"234","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-01-30 16:12","progress":"1","order_id":"231","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013016121097535050","task_id":"233","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-01-30 16:12","progress":"1","order_id":"230","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013015465749989910","task_id":"232","cate_id":"11","cate_name":"帮忙取","pay_fee":"6.00","task_price":"6.00","delivery_time":"2019-01-30 15:46","progress":"1","order_id":"229","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013015463054535355","task_id":"231","cate_id":"11","cate_name":"帮忙取","pay_fee":"4.00","task_price":"4.00","delivery_time":"2019-01-30 15:46","progress":"1","order_id":"228","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013015390456535310","task_id":"230","cate_id":"11","cate_name":"帮忙取","pay_fee":"27.00","task_price":"27.00","delivery_time":"2019-01-31 16:50","progress":"1","order_id":"227","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013015274549484910","task_id":"229","cate_id":"11","cate_name":"帮忙取","pay_fee":"24.00","task_price":"24.00","delivery_time":"2019-01-31 16:50","progress":"1","order_id":"226","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013015263054535751","task_id":"228","cate_id":"11","cate_name":"帮忙取","pay_fee":"18.00","task_price":"18.00","delivery_time":"2019-01-31 16:50","progress":"1","order_id":"225","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"},{"order_sn":"2019013015194852565710","task_id":"227","cate_id":"11","cate_name":"帮忙取","pay_fee":"17.00","task_price":"17.00","delivery_time":"2019-01-31 16:50","progress":"1","order_id":"224","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"0"}]
     * pages : 17
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
         * order_sn : 2019013109205656534848
         * task_id : 236
         * cate_id : 12
         * cate_name : 帮忙买
         * pay_fee : 6.00
         * task_price : 6.00
         * delivery_time : 2019-01-31 9:20
         * progress : 1
         * order_id : 233
         * makeup_fee : 0.00
         * express_pay_type : 1
         * makeup_id : 0
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
    }
}
