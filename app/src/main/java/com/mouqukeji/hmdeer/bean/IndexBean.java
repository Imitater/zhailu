package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class IndexBean {

    private List<CategoriesBean> categories;
    private List<BannersBean> banners;
    private List<NoticesBean> notices;
    private List<LatestsBean> latests;

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

    public List<LatestsBean> getLatests() {
        return latests;
    }

    public void setLatests(List<LatestsBean> latests) {
        this.latests = latests;
    }

    public static class CategoriesBean {
        /**
         * id : 11
         * pid : 10
         * cate_name : 帮忙取
         * cate_photo : https://api.hmdeer.com/static/icon/icon_bangmangqu1.png
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
         * banner : https://api.hmdeer.com/static/image/qingchun.png
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
         * url : https://mp.weixin.qq.com/s/VMXFlvOKpYJCn5rKWC5FPQ
         * thumb : https://api.hmdeer.com/static/image/activity1.png
         * description : 宅鹿闲置,能低级淘物品,也能任性送,解释新朋友,同学关系更进一步
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

    public static class LatestsBean {
        /**
         * id : 1
         * url :
         * icon : https://api.hmdeer.com/static/icon/2.png
         * title : VIP特权
         * subhead : 购买VIP，享免费下单
         */

        private String id;
        private String url;
        private String icon;
        private String title;
        private String subhead;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }
    }
}
