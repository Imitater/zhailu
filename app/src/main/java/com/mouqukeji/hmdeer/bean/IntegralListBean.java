package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class IntegralListBean {

    private List<IntegralBean> integral;

    public List<IntegralBean> getIntegral() {
        return integral;
    }

    public void setIntegral(List<IntegralBean> integral) {
        this.integral = integral;
    }

    public static class IntegralBean {
        /**
         * integral : 2
         * type : 1
         * create_time : 1551082010
         */

        private String integral;
        private String type;
        private String create_time;

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
