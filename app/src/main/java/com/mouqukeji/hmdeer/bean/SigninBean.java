package com.mouqukeji.hmdeer.bean;

public class SigninBean {

    /**
     * user : {"user_id":"29","token":"b454e701d7b4965482fe5851306efe67e412a5c4","did":"860863033962663"}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * user_id : 29
         * token : b454e701d7b4965482fe5851306efe67e412a5c4
         * did : 860863033962663
         */

        private String user_id;
        private String token;
        private String did;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }
    }
}
