/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wxb.wanshu.bean;

import java.util.List;

/**
 * 小说目录
 * @author lfh.
 * @date 2016/8/7.
 */
public class BookMenu extends Base {

    /**
     * data : {"title":"我的极品美女上司","is_free":0,"free_time":0,"last_chapter":771,"chapters":[{"chapter_id":660584,"name":"第001章 夏日的夜晚","fee":0,"is_buy":0,"is_last_read":0,"is_vip":0,"is_whole":0,"whole_fee":0,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=2109&chapter=1"},{"chapter_id":660585,"name":"第002章 那个周末","fee":0,"is_buy":0,"is_last_read":0,"is_vip":0,"is_whole":0,"whole_fee":0,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=2109&chapter=2"},{"chapter_id":660586,"name":"第003章 吃了一惊","fee":0,"is_buy":0,"is_last_read":0,"is_vip":0,"is_whole":0,"whole_fee":0,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=2109&chapter=3"}]}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 我的极品美女上司
         * is_free : 0
         * free_time : 0
         * last_chapter : 771
         * chapters : [{"chapter_id":660584,"name":"第001章 夏日的夜晚","fee":0,"is_buy":0,"is_last_read":0,"is_vip":0,"is_whole":0,"whole_fee":0,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=2109&chapter=1"},{"chapter_id":660585,"name":"第002章 那个周末","fee":0,"is_buy":0,"is_last_read":0,"is_vip":0,"is_whole":0,"whole_fee":0,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=2109&chapter=2"},{"chapter_id":660586,"name":"第003章 吃了一惊","fee":0,"is_buy":0,"is_last_read":0,"is_vip":0,"is_whole":0,"whole_fee":0,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=2109&chapter=3"}]
         */

        public String title;
        public int is_free;
        public int free_time;
        public int last_chapter;
        public List<ChaptersBean> chapters;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getLast_chapter() {
            return last_chapter;
        }

        public void setLast_chapter(int last_chapter) {
            this.last_chapter = last_chapter;
        }

        public List<ChaptersBean> getChapters() {
            return chapters;
        }

        public void setChapters(List<ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        public static class ChaptersBean {
            /**
             * chapter_id : 660584
             * name : 第001章 夏日的夜晚
             * fee : 0
             * is_buy : 0
             * is_last_read : 0
             * is_vip : 0
             * is_whole : 0
             * whole_fee : 0
             * link : https://mp.weixin.qq.com/s/-ZbNEaM7GWOnvfpQl2mVtw
             */

            public int chapter_id;
            public String chapter_name;
            public int fee;
            public int chapter_num;
            public int is_buy;
            public int is_last_read;
            public int is_vip;
            public int is_whole;
            public int whole_fee;
            public int is_free;
            public int free_time;
            public String link;

            public int getChapter_num() {
                return chapter_num;
            }

            public void setChapter_num(int chapter_num) {
                this.chapter_num = chapter_num;
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

            public int getChapter_id() {
                return chapter_id;
            }

            public void setChapter_id(int chapter_id) {
                this.chapter_id = chapter_id;
            }

            public String getChapter_name() {
                return chapter_name;
            }

            public void setChapter_name(String chapter_name) {
                this.chapter_name = chapter_name;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getIs_buy() {
                return is_buy;
            }

            public void setIs_buy(int is_buy) {
                this.is_buy = is_buy;
            }

            public int getIs_last_read() {
                return is_last_read;
            }

            public void setIs_last_read(int is_last_read) {
                this.is_last_read = is_last_read;
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

            public int getWhole_fee() {
                return whole_fee;
            }

            public void setWhole_fee(int whole_fee) {
                this.whole_fee = whole_fee;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
