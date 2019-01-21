package com.mouqu.zhailu.zhailu.bean;

import java.util.List;

public class IndexBean {

    private List<CategoriesBean> categories;
    private List<BannersBean> banners;
    private List<NoticesBean> notices;

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<NoticesBean> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticesBean> notices) {
        this.notices = notices;
    }

    public static class CategoriesBean {
        /**
         * id : 11
         * pid : 10
         * cate_name : 帮忙取
         * cate_photo : http://test.mouqukeji.com/static/icon/icon_bangmangqu.png
         */

        private String id;
        private String pid;
        private String cate_name;
        private String cate_photo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getCate_photo() {
            return cate_photo;
        }

        public void setCate_photo(String cate_photo) {
            this.cate_photo = cate_photo;
        }
    }

    public static class BannersBean {
        /**
         * id : 1
         * banner : http://picture.mouqukeji.com/baojie.jpg
         * url :
         * type : 1
         */

        private String id;
        private String banner;
        private String url;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class NoticesBean {
        /**
         * id : 1
         * url :
         * thumb : http://picture.mouqukeji.com/baojie.jpg
         * description : 活动1活动1活动1活动1活动1
         */

        private String id;
        private String url;
        private String thumb;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
