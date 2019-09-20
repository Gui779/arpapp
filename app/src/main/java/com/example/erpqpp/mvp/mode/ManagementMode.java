package com.example.erpqpp.mvp.mode;

import java.util.List;

public class ManagementMode {

    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"user_id":9,"user_name":"哈哈哈","tel":"18711111111","address":"地址"},{"user_id":8,"user_name":"zhan三","tel":"13178965412","address":"中山"},{"user_id":7,"user_name":"小赵","tel":"17710446666","address":"北京西城区西直门大街6号院"},{"user_id":6,"user_name":"test","tel":"17710448855","address":""},{"user_id":5,"user_name":"封熊","tel":"17610080000","address":"中国"},{"user_id":4,"user_name":"木语轩","tel":"17710448877","address":"北京西城区西直门大街6号院"},{"user_id":3,"user_name":"张来全","tel":"17710448888","address":"北京西城区西直门大街6号院"},{"user_id":2,"user_name":"邓光","tel":"13574036666","address":"鑫坡小区"},{"user_id":1,"user_name":"梁晓青","tel":"13511111111","address":"4楼国风"}],"count":9}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"user_id":9,"user_name":"哈哈哈","tel":"18711111111","address":"地址"},{"user_id":8,"user_name":"zhan三","tel":"13178965412","address":"中山"},{"user_id":7,"user_name":"小赵","tel":"17710446666","address":"北京西城区西直门大街6号院"},{"user_id":6,"user_name":"test","tel":"17710448855","address":""},{"user_id":5,"user_name":"封熊","tel":"17610080000","address":"中国"},{"user_id":4,"user_name":"木语轩","tel":"17710448877","address":"北京西城区西直门大街6号院"},{"user_id":3,"user_name":"张来全","tel":"17710448888","address":"北京西城区西直门大街6号院"},{"user_id":2,"user_name":"邓光","tel":"13574036666","address":"鑫坡小区"},{"user_id":1,"user_name":"梁晓青","tel":"13511111111","address":"4楼国风"}]
         * count : 9
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * user_id : 9
             * user_name : 哈哈哈
             * tel : 18711111111
             * address : 地址
             */

            private int user_id;
            private String user_name;
            private String tel;
            private String address;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
