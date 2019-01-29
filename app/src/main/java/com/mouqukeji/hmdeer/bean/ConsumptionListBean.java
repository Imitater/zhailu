package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class ConsumptionListBean {
    /**
     * buy : {"consume":[{"create_time":"2019-01-25 10:06:15","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:08:07","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:11:01","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:20:41","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:23:02","type":"2","money":"3.00"},{"create_time":"2019-01-25 11:53:19","type":"2","money":"5.00"},{"create_time":"2019-01-25 12:07:26","type":"2","money":"4.00"},{"create_time":"2019-01-25 12:10:30","type":"2","money":"6.00"},{"create_time":"2019-01-25 12:51:39","type":"2","money":"5.00"},{"create_time":"2019-01-25 13:10:21","type":"2","money":"3.00"},{"create_time":"2019-01-25 13:13:20","type":"2","money":"5.00"},{"create_time":"2019-01-25 14:57:16","type":"2","money":"1.00"},{"create_time":"2019-01-25 15:13:08","type":"2","money":"1.00"},{"create_time":"2019-01-25 15:15:42","type":"2","money":"1.00"}],"total":"155.00","pages":3}
     */
    private BuyBean buy;

    public BuyBean getBuy() {
        return buy;
    }

    public void setBuy(BuyBean buy) {
        this.buy = buy;
    }

    public static class BuyBean {
        /**
         * consume : [{"create_time":"2019-01-25 10:06:15","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:08:07","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:11:01","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:20:41","type":"2","money":"3.00"},{"create_time":"2019-01-25 10:23:02","type":"2","money":"3.00"},{"create_time":"2019-01-25 11:53:19","type":"2","money":"5.00"},{"create_time":"2019-01-25 12:07:26","type":"2","money":"4.00"},{"create_time":"2019-01-25 12:10:30","type":"2","money":"6.00"},{"create_time":"2019-01-25 12:51:39","type":"2","money":"5.00"},{"create_time":"2019-01-25 13:10:21","type":"2","money":"3.00"},{"create_time":"2019-01-25 13:13:20","type":"2","money":"5.00"},{"create_time":"2019-01-25 14:57:16","type":"2","money":"1.00"},{"create_time":"2019-01-25 15:13:08","type":"2","money":"1.00"},{"create_time":"2019-01-25 15:15:42","type":"2","money":"1.00"}]
         * total : 155.00
         * pages : 3
         */

        private String total;
        private int pages;
        private List<ConsumeBean> consume;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<ConsumeBean> getConsume() {
            return consume;
        }

        public void setConsume(List<ConsumeBean> consume) {
            this.consume = consume;
        }

        public static class ConsumeBean {
            /**
             * create_time : 2019-01-25 10:06:15
             * type : 2
             * money : 3.00
             */

            private String create_time;
            private String type;
            private String money;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
