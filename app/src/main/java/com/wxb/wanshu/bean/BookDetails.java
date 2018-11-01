package com.wxb.wanshu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiming on 2017/12/1.
 */

public class BookDetails extends Base {

    /**
     * data : {"novel_id":1955,"title":"爱人的唇印","category":"总裁豪门","is_free":0,"free_time":0,"author":"一澄几许","show_author":0,"cover":"http://cdn-novel.weituibao.com/0-temp-201708-17-1502955104583.jpg","word_num":1000983,"chapter_num":593,"complete_status":1,"summary":"<p>他喜欢她，爱她，宠她，让她感受童话般的美好爱情，她认为自己找到了一生的真爱，她穿上美丽的婚纱，等待成为他的新娘，殊不知这场婚礼却已经悄然易主，她从幸福的巅峰跌入了令人嘲笑的深渊，而这一切皆源自当年那一句话.....她用自己的鲜血和伤痛偿还了他的仇怨而他却再也找不到拥有她时的那份快乐，他和她在真相大白时该何去何从？<\/p>","simple_chapters":[{"chapter_name":"第1章 三不画","chapter_id":660584},{"chapter_name":"第2章 死之谜","chapter_id":660584},{"chapter_name":"第3章 魂再现","chapter_id":660584},{"chapter_name":"第4章 地缝","chapter_id":660584}],"latest_chapter":{"is_buy":0,"is_vip":0,"is_whole":0,"fee":20,"chapter_name":"第928章 离开","chapter_id":660584,"whole_fee":0},"recommend":[{"novel_id":1166,"title":"豪门秘婚小娇妻","cover":"http://cdn-novel.weituibao.com/0-temp-201708-17-1502955453832.jpg"},{"novel_id":2108,"title":"一孕成婚","cover":"http://www.yueloo.com/files/article/image/63/63650/63650l.jpg"},{"novel_id":1176,"title":"野性难驯：惹火娇妻","cover":"http://cdn-novel.weituibao.com/novel-1176-cover-1317.jpg"},{"novel_id":2128,"title":"职场俏佳人","cover":"http://www.tcss88.com/imagedata/2017/09/16/59bbfa503b725711924739.jpg"},{"novel_id":1172,"title":"腹黑总裁，御用嫂子","cover":"http://cdn-novel.weituibao.com/0-temp-201708-17-1502955039831.jpg"},{"novel_id":1177,"title":"坏总裁的宠溺夜袭","cover":"http://cdn-novel.weituibao.com/novel-1177-cover-5132.jpg"}],"catalog_url":"http://www.baidu.com","reward_amount":500,"read":125,"is_whole":0,"whole_fee":0,"is_collect":0,"update_time":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * novel_id : 1955
         * title : 爱人的唇印
         * category : 总裁豪门
         * is_free : 0
         * free_time : 0
         * author : 一澄几许
         * show_author : 0
         * cover : http://cdn-novel.weituibao.com/0-temp-201708-17-1502955104583.jpg
         * word_num : 1000983
         * chapter_num : 593
         * complete_status : 1
         * summary : <p>他喜欢她，爱她，宠她，让她感受童话般的美好爱情，她认为自己找到了一生的真爱，她穿上美丽的婚纱，等待成为他的新娘，殊不知这场婚礼却已经悄然易主，她从幸福的巅峰跌入了令人嘲笑的深渊，而这一切皆源自当年那一句话.....她用自己的鲜血和伤痛偿还了他的仇怨而他却再也找不到拥有她时的那份快乐，他和她在真相大白时该何去何从？</p>
         * simple_chapters : [{"chapter_name":"第1章 三不画","chapter_id":660584},{"chapter_name":"第2章 死之谜","chapter_id":660584},{"chapter_name":"第3章 魂再现","chapter_id":660584},{"chapter_name":"第4章 地缝","chapter_id":660584}]
         * latest_chapter : {"is_buy":0,"is_vip":0,"is_whole":0,"fee":20,"chapter_name":"第928章 离开","chapter_id":660584,"whole_fee":0}
         * recommend : [{"novel_id":1166,"title":"豪门秘婚小娇妻","cover":"http://cdn-novel.weituibao.com/0-temp-201708-17-1502955453832.jpg"},{"novel_id":2108,"title":"一孕成婚","cover":"http://www.yueloo.com/files/article/image/63/63650/63650l.jpg"},{"novel_id":1176,"title":"野性难驯：惹火娇妻","cover":"http://cdn-novel.weituibao.com/novel-1176-cover-1317.jpg"},{"novel_id":2128,"title":"职场俏佳人","cover":"http://www.tcss88.com/imagedata/2017/09/16/59bbfa503b725711924739.jpg"},{"novel_id":1172,"title":"腹黑总裁，御用嫂子","cover":"http://cdn-novel.weituibao.com/0-temp-201708-17-1502955039831.jpg"},{"novel_id":1177,"title":"坏总裁的宠溺夜袭","cover":"http://cdn-novel.weituibao.com/novel-1177-cover-5132.jpg"}]
         * catalog_url : http://www.baidu.com
         * reward_amount : 500
         * read : 125
         * is_whole : 0
         * whole_fee : 0
         * is_collect : 0
         * update_time : null
         */

        private int novel_id;
        private String title;
        private String category;
        private int is_free;
        private int free_time;
        private String author;
        private int show_author;
        private String cover;
        private int word_num;
        private int chapter_num;
        private int complete_status;
        private String summary;
        private LatestChapterBean latest_chapter;
        private String catalog_url;
        private int reward_amount;
        private int read;
        private int is_whole;
        private int whole_fee;
        private int is_collect;
        private Object update_time;
        private List<SimpleChaptersBean> simple_chapters;
        private List<RecommendBean> recommend;

        public int getNovel_id() {
            return novel_id;
        }

        public void setNovel_id(int novel_id) {
            this.novel_id = novel_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public int getFree_time() {
            return free_time;
        }

        public void setFree_time(int free_time) {
            this.free_time = free_time;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getShow_author() {
            return show_author;
        }

        public void setShow_author(int show_author) {
            this.show_author = show_author;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getWord_num() {
            return word_num;
        }

        public void setWord_num(int word_num) {
            this.word_num = word_num;
        }

        public int getChapter_num() {
            return chapter_num;
        }

        public void setChapter_num(int chapter_num) {
            this.chapter_num = chapter_num;
        }

        public int getComplete_status() {
            return complete_status;
        }

        public void setComplete_status(int complete_status) {
            this.complete_status = complete_status;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public LatestChapterBean getLatest_chapter() {
            return latest_chapter;
        }

        public void setLatest_chapter(LatestChapterBean latest_chapter) {
            this.latest_chapter = latest_chapter;
        }

        public String getCatalog_url() {
            return catalog_url;
        }

        public void setCatalog_url(String catalog_url) {
            this.catalog_url = catalog_url;
        }

        public int getReward_amount() {
            return reward_amount;
        }

        public void setReward_amount(int reward_amount) {
            this.reward_amount = reward_amount;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public int getIs_whole() {
            return is_whole;
        }

        public void setIs_whole(int is_whole) {
            this.is_whole = is_whole;
        }

        public int getWhole_fee() {
            return whole_fee;
        }

        public void setWhole_fee(int whole_fee) {
            this.whole_fee = whole_fee;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public List<SimpleChaptersBean> getSimple_chapters() {
            return simple_chapters;
        }

        public void setSimple_chapters(List<SimpleChaptersBean> simple_chapters) {
            this.simple_chapters = simple_chapters;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class LatestChapterBean  implements Serializable{
            /**
             * is_buy : 0
             * is_vip : 0
             * is_whole : 0
             * fee : 20
             * chapter_name : 第928章 离开
             * chapter_id : 660584
             * whole_fee : 0
             */

            private int is_buy;
            private int is_vip;
            private int is_whole;
            private int fee;
            private String chapter_name;
            private int chapter_id;
            private int whole_fee;

            public int getIs_buy() {
                return is_buy;
            }

            public void setIs_buy(int is_buy) {
                this.is_buy = is_buy;
            }

            public int getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(int is_vip) {
                this.is_vip = is_vip;
            }

            public int getIs_whole() {
                return is_whole;
            }

            public void setIs_whole(int is_whole) {
                this.is_whole = is_whole;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public String getChapter_name() {
                return chapter_name;
            }

            public void setChapter_name(String chapter_name) {
                this.chapter_name = chapter_name;
            }

            public int getChapter_id() {
                return chapter_id;
            }

            public void setChapter_id(int chapter_id) {
                this.chapter_id = chapter_id;
            }

            public int getWhole_fee() {
                return whole_fee;
            }

            public void setWhole_fee(int whole_fee) {
                this.whole_fee = whole_fee;
            }
        }

        public static class SimpleChaptersBean  implements Serializable{
            /**
             * chapter_name : 第1章 三不画
             * chapter_id : 660584
             */

            private String chapter_name;
            private int chapter_id;

            public String getChapter_name() {
                return chapter_name;
            }

            public void setChapter_name(String chapter_name) {
                this.chapter_name = chapter_name;
            }

            public int getChapter_id() {
                return chapter_id;
            }

            public void setChapter_id(int chapter_id) {
                this.chapter_id = chapter_id;
            }
        }

        public static class RecommendBean  implements Serializable{
            /**
             * novel_id : 1166
             * title : 豪门秘婚小娇妻
             * cover : http://cdn-novel.weituibao.com/0-temp-201708-17-1502955453832.jpg
             */

            private int novel_id;
            private String title;
            private String cover;

            public int getNovel_id() {
                return novel_id;
            }

            public void setNovel_id(int novel_id) {
                this.novel_id = novel_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
    }
}
