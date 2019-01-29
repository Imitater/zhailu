package com.mouqukeji.hmdeer.bean;

public class HelpUniversalInfoBean {


    /**
     * detail : {"order_id":"197","cate_id":"15","order_sn":"2019012515130549555697","progress":"2","create_time":"1548400385","pay_fee":"1.00","task_id":"200","cate_name":"万能帮","task_price":"1.00","coupon":"0.00","delivery_time":"2019-01-25 15:13","end_address":"中天·官河锦庭(西3门)","end_detail":"qqqqq","end_name":"12138","end_telephone":"12138","demand":"红米"}
     */

    private DetailBean detail;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * order_id : 197
         * cate_id : 15
         * order_sn : 2019012515130549555697
         * progress : 2
         * create_time : 1548400385
         * pay_fee : 1.00
         * task_id : 200
         * cate_name : 万能帮
         * task_price : 1.00
         * coupon : 0.00
         * delivery_time : 2019-01-25 15:13
         * end_address : 中天·官河锦庭(西3门)
         * end_detail : qqqqq
         * end_name : 12138
         * end_telephone : 12138
         * demand : 红米
         */

        private String order_id;
        private String cate_id;
        private String order_sn;
        private String progress;
        private String create_time;
        private String pay_fee;
        private String task_id;
        private String cate_name;
        private String task_price;
        private String coupon;
        private String delivery_time;
        private String end_address;
        private String end_detail;
        private String end_name;
        private String end_telephone;
        private String demand;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getTask_price() {
            return task_price;
        }

        public void setTask_price(String task_price) {
            this.task_price = task_price;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getEnd_address() {
            return end_address;
        }

        public void setEnd_address(String end_address) {
            this.end_address = end_address;
        }

        public String getEnd_detail() {
            return end_detail;
        }

        public void setEnd_detail(String end_detail) {
            this.end_detail = end_detail;
        }

        public String getEnd_name() {
            return end_name;
        }

        public void setEnd_name(String end_name) {
            this.end_name = end_name;
        }

        public String getEnd_telephone() {
            return end_telephone;
        }

        public void setEnd_telephone(String end_telephone) {
            this.end_telephone = end_telephone;
        }

        public String getDemand() {
            return demand;
        }

        public void setDemand(String demand) {
            this.demand = demand;
        }
    }
}
