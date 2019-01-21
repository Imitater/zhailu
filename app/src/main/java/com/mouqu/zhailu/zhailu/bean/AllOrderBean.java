package com.mouqu.zhailu.zhailu.bean;

import java.util.List;

public class AllOrderBean {


    /**
     * tasks : [{"order_sn":"20181221173523","task_id":"11","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"8","order_id":"11","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"20181221173523","task_id":"9","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"7","order_id":"9","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"201812211735230","task_id":"7","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"5","order_id":"7","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"201812211735238","task_id":"5","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"3","order_id":"5","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"201812211735236","task_id":"3","cate_id":"13","cate_name":"帮忙寄","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"1","order_id":"3","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"201812211735245","task_id":"1","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"1","order_id":"1","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"20181221173523","task_id":"10","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"8","order_id":"10","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"20181221173523","task_id":"8","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"6","order_id":"8","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"201812211735239","task_id":"6","cate_id":"11","cate_name":"帮忙取","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"4","order_id":"6","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"},{"order_sn":"201812211735237","task_id":"4","cate_id":"14","cate_name":"帮忙送","pay_fee":"1.00","task_price":"1.00","delivery_time":"13:00~15:00","progress":"2","order_id":"4","makeup_fee":"0.00","express_pay_type":"1","makeup_id":"1"}]
     * pages : 2
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
         * order_sn : 20181221173523
         * task_id : 11
         * cate_id : 11
         * cate_name : 帮忙取
         * pay_fee : 1.00
         * task_price : 1.00
         * delivery_time : 13:00~15:00
         * progress : 8
         * order_id : 11
         * makeup_fee : 0.00
         * express_pay_type : 1
         * makeup_id : 1
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
