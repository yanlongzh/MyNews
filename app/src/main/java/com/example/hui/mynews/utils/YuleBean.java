package com.example.hui.mynews.utils;

import java.util.List;

/**
 * Created by yanlongzh on 2017/1/5.
 */

public class YuleBean {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"3bcb1b73b833149e0b343d90c6ab1716","title":"百度音乐携手SNH48，共同寻找最懂他的人","date":"2017-01-06 18:20","category":"娱乐","author_name":"东方娱乐网","url":"http://mini.eastday.com/mobile/170106182010443.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_3_mwpm_03200403.jpeg"},{"uniquekey":"e2cea8c16ed3f56e11aabc31ab4a4069","title":"《王的爱》众主创读剧本 任时完、允儿超有爱","date":"2017-01-06 18:20","category":"娱乐","author_name":"腾讯娱乐","url":"http://mini.eastday.com/mobile/170106182010450.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170106/20170106182010_3d4def7d5e2f720bb57c5607bed6fb38_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170106/20170106182010_3d4def7d5e2f720bb57c5607bed6fb38_2_mwpm_03200403.jpeg"},{"uniquekey":"565dd4cc283805adc1c27aa9af7152c1","title":"知情人否认Angelababy抠像演出 称拍了3个多月","date":"2017-01-06 18:20","category":"娱乐","author_name":"搜狐新闻","url":"http://mini.eastday.com/mobile/170106182010436.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170106/20170106182010_94dd9fb5856aa4f05d65bdf5cc8af842_1_mwpm_03200403.jpeg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"3bcb1b73b833149e0b343d90c6ab1716","title":"百度音乐携手SNH48，共同寻找最懂他的人","date":"2017-01-06 18:20","category":"娱乐","author_name":"东方娱乐网","url":"http://mini.eastday.com/mobile/170106182010443.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_3_mwpm_03200403.jpeg"},{"uniquekey":"e2cea8c16ed3f56e11aabc31ab4a4069","title":"《王的爱》众主创读剧本 任时完、允儿超有爱","date":"2017-01-06 18:20","category":"娱乐","author_name":"腾讯娱乐","url":"http://mini.eastday.com/mobile/170106182010450.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170106/20170106182010_3d4def7d5e2f720bb57c5607bed6fb38_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170106/20170106182010_3d4def7d5e2f720bb57c5607bed6fb38_2_mwpm_03200403.jpeg"},{"uniquekey":"565dd4cc283805adc1c27aa9af7152c1","title":"知情人否认Angelababy抠像演出 称拍了3个多月","date":"2017-01-06 18:20","category":"娱乐","author_name":"搜狐新闻","url":"http://mini.eastday.com/mobile/170106182010436.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170106/20170106182010_94dd9fb5856aa4f05d65bdf5cc8af842_1_mwpm_03200403.jpeg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {

            public DataBean(String title, String date, String url,String thumbnail_pic_s) {
                this.title = title;
                this.date = date;
                this.url = url;
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            /**
             * uniquekey : 3bcb1b73b833149e0b343d90c6ab1716
             * title : 百度音乐携手SNH48，共同寻找最懂他的人
             * date : 2017-01-06 18:20
             * category : 娱乐
             * author_name : 东方娱乐网
             * url : http://mini.eastday.com/mobile/170106182010443.html
             * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_1_mwpm_03200403.jpeg
             * thumbnail_pic_s02 : http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_2_mwpm_03200403.jpeg
             * thumbnail_pic_s03 : http://05.imgmini.eastday.com/mobile/20170106/20170106182010_ad18cbfff72a20ada6de151e52baaabd_3_mwpm_03200403.jpeg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}