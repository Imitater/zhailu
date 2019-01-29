package com.mouqukeji.hmdeer.bean;

public class ZhiFuBoPayBean {


    /**
     * pay : {"payInfo":"alipay_sdk=alipay-sdk-php-20161101&app_id=2018121262524443&biz_content=%7B%22body%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%222019012317241749985798%22%2C%22total_amount%22%3A%220.01%22%2C%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fmq.mouqukeji.com%2Fapi%2Falipay%2Fnotifypay&sign_type=RSA2&timestamp=2019-01-23+19%3A13%3A55&version=1.0&sign=nvRd4bBI%2Fn8%2FJrkBhIwhvCmoLHSvAURX8qhUPAOJ17B50ljvEBZTEismIUTlWjB9P8JWM4cqR534f1DqhAFQejomh4b1B38rDz%2FiT2f%2B9uIplS4tdb3XYpr5lduv7emwopBgzA584EpGeHYRUKnQW9%2FrdqefruYh15Hjms%2FZKPxAayclHDsocq%2FHJeWZ1p58Tm5%2BXApjOpJab4r%2FzNxL10tHPM2fooFs40z1DF3GpkSFgwiYcxXrFg5b8yAzWJw75aJMAKqdKs98hYSxuZpkbDWsRL3fxdtzsXF2R2PRSSGQTLNrXHloQOUAZjp%2BcbxBjKr7QYh50PDhCxXLQp%2B76g%3D%3D"}
     * orders : {"task_id":"128","progress":"1","cate_id":"11"}
     */

    private PayBean pay;
    private OrdersBean orders;

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public static class PayBean {
        /**
         * payInfo : alipay_sdk=alipay-sdk-php-20161101&app_id=2018121262524443&biz_content=%7B%22body%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%222019012317241749985798%22%2C%22total_amount%22%3A%220.01%22%2C%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fmq.mouqukeji.com%2Fapi%2Falipay%2Fnotifypay&sign_type=RSA2&timestamp=2019-01-23+19%3A13%3A55&version=1.0&sign=nvRd4bBI%2Fn8%2FJrkBhIwhvCmoLHSvAURX8qhUPAOJ17B50ljvEBZTEismIUTlWjB9P8JWM4cqR534f1DqhAFQejomh4b1B38rDz%2FiT2f%2B9uIplS4tdb3XYpr5lduv7emwopBgzA584EpGeHYRUKnQW9%2FrdqefruYh15Hjms%2FZKPxAayclHDsocq%2FHJeWZ1p58Tm5%2BXApjOpJab4r%2FzNxL10tHPM2fooFs40z1DF3GpkSFgwiYcxXrFg5b8yAzWJw75aJMAKqdKs98hYSxuZpkbDWsRL3fxdtzsXF2R2PRSSGQTLNrXHloQOUAZjp%2BcbxBjKr7QYh50PDhCxXLQp%2B76g%3D%3D
         */

        private String payInfo;

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }
    }

    public static class OrdersBean {
        /**
         * task_id : 128
         * progress : 1
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
