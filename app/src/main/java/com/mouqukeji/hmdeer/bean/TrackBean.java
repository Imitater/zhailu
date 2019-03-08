package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class TrackBean {

    /**
     * count : 1
     * results : [{"createtime":1550563306050,"locatetime":1550569335305,"name":"user-123","tid":2764537}]
     */

    private int count;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * createtime : 1550563306050
         * locatetime : 1550569335305
         * name : user-123
         * tid : 2764537
         */

        private long createtime;
        private long locatetime;
        private String name;
        private int tid;

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public long getLocatetime() {
            return locatetime;
        }

        public void setLocatetime(long locatetime) {
            this.locatetime = locatetime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }
    }
}
