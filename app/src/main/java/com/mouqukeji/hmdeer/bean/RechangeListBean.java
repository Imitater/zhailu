package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class RechangeListBean {

    /**
     * balance : {"recharges":[{"money":"100.00","create_time":"2019-01-23 17:24:51","pay_type":"1"}],"pages":1}
     */

    private BalanceBean balance;

    public BalanceBean getBalance() {
        return balance;
    }

    public void setBalance(BalanceBean balance) {
        this.balance = balance;
    }

    public static class BalanceBean {
        /**
         * recharges : [{"money":"100.00","create_time":"2019-01-23 17:24:51","pay_type":"1"}]
         * pages : 1
         */

        private int pages;
        private List<RechargesBean> recharges;

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RechargesBean> getRecharges() {
            return recharges;
        }

        public void setRecharges(List<RechargesBean> recharges) {
            this.recharges = recharges;
        }

        public static class RechargesBean {
            /**
             * money : 100.00
             * create_time : 2019-01-23 17:24:51
             * pay_type : 1
             */

            private String money;
            private String create_time;
            private String pay_type;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }
        }
    }
}
