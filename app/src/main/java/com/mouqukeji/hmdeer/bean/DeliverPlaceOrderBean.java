package com.mouqukeji.hmdeer.bean;

public class DeliverPlaceOrderBean {

    /**
     * order_id : 656
     * balance : 19975.00
     * order : {"task_id":"712","progress":"2","cate_id":"11"}
     */

    private String order_id;
    private String balance;
    private PlaceOrderBean.OrderBean order;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public PlaceOrderBean.OrderBean getOrder() {
        return order;
    }

    public void setOrder(PlaceOrderBean.OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * task_id : 712
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
