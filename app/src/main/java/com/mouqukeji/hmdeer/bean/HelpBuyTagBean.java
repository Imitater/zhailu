package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class HelpBuyTagBean {


    private List<TagsBean> tags;

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * id : 1
         * tag : 小笼包
         */

        private String id;
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
