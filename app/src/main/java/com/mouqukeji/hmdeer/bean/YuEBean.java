package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class YuEBean {


    /**
     * payInfo : []
     * orders : {"task_id":"128","progress":"2","cate_id":"11"}
     */

    private OrdersBean orders;
    private List<?> payInfo;

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public List<?> getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(List<?> payInfo) {
        this.payInfo = payInfo;
    }

    public static class OrdersBean {
        /**
         * task_id : 128
         * progress : 2
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
