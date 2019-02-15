package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class RechargePageBean {

    private List<RechargeBean> recharge;

    public List<RechargeBean> getRecharge() {
        return recharge;
    }

    public void setRecharge(List<RechargeBean> recharge) {
        this.recharge = recharge;
    }

    public static class RechargeBean {
        /**
         * re_id : 1
         * recharge_fee : 10.00
         * is_reward : 0
         * reward_fee : 0.00
         */

        private String re_id;
        private String recharge_fee;
        private String is_reward;
        private String reward_fee;

        public String getRe_id() {
            return re_id;
        }

        public void setRe_id(String re_id) {
            this.re_id = re_id;
        }

        public String getRecharge_fee() {
            return recharge_fee;
        }

        public void setRecharge_fee(String recharge_fee) {
            this.recharge_fee = recharge_fee;
        }

        public String getIs_reward() {
            return is_reward;
        }

        public void setIs_reward(String is_reward) {
            this.is_reward = is_reward;
        }

        public String getReward_fee() {
            return reward_fee;
        }

        public void setReward_fee(String reward_fee) {
            this.reward_fee = reward_fee;
        }
    }
}
