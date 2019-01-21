package com.mouqu.zhailu.zhailu.bean;

import java.util.List;

public class ItemsCategoryBean {

    /**
     * type : [{"gtype_id":"6","before_icon":"","after_icon":"","type_name":"文件"},{"gtype_id":"7","before_icon":"","after_icon":"","type_name":"数码产品"},{"gtype_id":"8","before_icon":"","after_icon":"","type_name":"日用品"},{"gtype_id":"9","before_icon":"","after_icon":"","type_name":"服饰"},{"gtype_id":"10","before_icon":"","after_icon":"","type_name":"食品"},{"gtype_id":"11","before_icon":"","after_icon":"","type_name":"其他"}]
     * category : {"cate_name":"帮忙取","cate_id":"11","price":"3.00","km_price":"1.00","kg_price":"1.00","city":"杭州市","basekg":"1","basekm":"1"}
     * default_address : null
     */

    private CategoryBean category;
    private Object default_address;
    private List<TypeBean> type;

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public Object getDefault_address() {
        return default_address;
    }

    public void setDefault_address(Object default_address) {
        this.default_address = default_address;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class CategoryBean {
        /**
         * cate_name : 帮忙取
         * cate_id : 11
         * price : 3.00
         * km_price : 1.00
         * kg_price : 1.00
         * city : 杭州市
         * basekg : 1
         * basekm : 1
         */

        private String cate_name;
        private String cate_id;
        private String price;
        private String km_price;
        private String kg_price;
        private String city;
        private String basekg;
        private String basekm;

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getKm_price() {
            return km_price;
        }

        public void setKm_price(String km_price) {
            this.km_price = km_price;
        }

        public String getKg_price() {
            return kg_price;
        }

        public void setKg_price(String kg_price) {
            this.kg_price = kg_price;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBasekg() {
            return basekg;
        }

        public void setBasekg(String basekg) {
            this.basekg = basekg;
        }

        public String getBasekm() {
            return basekm;
        }

        public void setBasekm(String basekm) {
            this.basekm = basekm;
        }
    }

    public static class TypeBean {
        /**
         * gtype_id : 6
         * before_icon :
         * after_icon :
         * type_name : 文件
         */

        private String gtype_id;
        private String before_icon;
        private String after_icon;
        private String type_name;

        public String getGtype_id() {
            return gtype_id;
        }

        public void setGtype_id(String gtype_id) {
            this.gtype_id = gtype_id;
        }

        public String getBefore_icon() {
            return before_icon;
        }

        public void setBefore_icon(String before_icon) {
            this.before_icon = before_icon;
        }

        public String getAfter_icon() {
            return after_icon;
        }

        public void setAfter_icon(String after_icon) {
            this.after_icon = after_icon;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
