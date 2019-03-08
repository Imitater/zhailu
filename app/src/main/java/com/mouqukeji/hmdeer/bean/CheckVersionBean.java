package com.mouqukeji.hmdeer.bean;


public class CheckVersionBean {

    /**
     * versionInfo : {"version_code":"1.0.1","platform":"android","apk_url":"https://api.hmdeer.com/download/20190307164330/user_hmdeer.apk","update_info":"","is_compel":"1","apk_size":"11539576","apk_md5":"7dadae59afc1bc0518b980024e951f0a"}
     */

    private VersionInfoBean versionInfo;

    public VersionInfoBean getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfoBean versionInfo) {
        this.versionInfo = versionInfo;
    }

    public static class VersionInfoBean {
        /**
         * version_code : 1.0.1
         * platform : android
         * apk_url : https://api.hmdeer.com/download/20190307164330/user_hmdeer.apk
         * update_info :
         * is_compel : 1
         * apk_size : 11539576
         * apk_md5 : 7dadae59afc1bc0518b980024e951f0a
         */

        private String version_code;
        private String platform;
        private String apk_url;
        private String update_info;
        private String is_compel;
        private String apk_size;
        private String apk_md5;

        public String getVersion_code() {
            return version_code;
        }

        public void setVersion_code(String version_code) {
            this.version_code = version_code;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getApk_url() {
            return apk_url;
        }

        public void setApk_url(String apk_url) {
            this.apk_url = apk_url;
        }

        public String getUpdate_info() {
            return update_info;
        }

        public void setUpdate_info(String update_info) {
            this.update_info = update_info;
        }

        public String getIs_compel() {
            return is_compel;
        }

        public void setIs_compel(String is_compel) {
            this.is_compel = is_compel;
        }

        public String getApk_size() {
            return apk_size;
        }

        public void setApk_size(String apk_size) {
            this.apk_size = apk_size;
        }

        public String getApk_md5() {
            return apk_md5;
        }

        public void setApk_md5(String apk_md5) {
            this.apk_md5 = apk_md5;
        }
    }
}
