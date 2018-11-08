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
     * data : {"novel":{"id":"2","name":"千金归来：老公，请走开","owner_id":"13","author":"栀想","cover":"novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","complete_status":"0","complete_time":null,"category_id":"3","sex_type":"20","word_num":"33345","sort":"0","chapter_num":"5","free_chapter_num":"0","create_time":"0","update_time":"0","length_type":"1","view_count":"0","is_onsale":"1"},"chapters":[{"id":"1","novel_id":"2","volume_id":"1","name":"第1章 三不画","sort":"1","word_num":"1574","create_time":"1541494361","update_time":"1541494361","publish_time":"1541494361","view_count":"0","has_been_read":false,"is_current_read":false,"is_free":false},{"id":"2","novel_id":"2","volume_id":"2","name":"第2章 死之谜","sort":"2","word_num":"2808","create_time":"1541494361","update_time":"1541494361","publish_time":"1541494361","view_count":"0","has_been_read":false,"is_current_read":false,"is_free":false},{"id":"3","novel_id":"2","volume_id":"2","name":"第3章 魂再现","sort":"3","word_num":"2662","create_time":"1541494361","update_time":"1541494361","publish_time":"1541494361","view_count":"0","has_been_read":false,"is_current_read":false,"is_free":false}]}
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
         * novel : {"id":"2","name":"千金归来：老公，请走开","owner_id":"13","author":"栀想","cover":"novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","complete_status":"0","complete_time":null,"category_id":"3","sex_type":"20","word_num":"33345","sort":"0","chapter_num":"5","free_chapter_num":"0","create_time":"0","update_time":"0","length_type":"1","view_count":"0","is_onsale":"1"}
         * chapters : [{"id":"1","novel_id":"2","volume_id":"1","name":"第1章 三不画","sort":"1","word_num":"1574","create_time":"1541494361","update_time":"1541494361","publish_time":"1541494361","view_count":"0","has_been_read":false,"is_current_read":false,"is_free":false},{"id":"2","novel_id":"2","volume_id":"2","name":"第2章 死之谜","sort":"2","word_num":"2808","create_time":"1541494361","update_time":"1541494361","publish_time":"1541494361","view_count":"0","has_been_read":false,"is_current_read":false,"is_free":false},{"id":"3","novel_id":"2","volume_id":"2","name":"第3章 魂再现","sort":"3","word_num":"2662","create_time":"1541494361","update_time":"1541494361","publish_time":"1541494361","view_count":"0","has_been_read":false,"is_current_read":false,"is_free":false}]
         */

        public NovelBean novel;
        public List<ChaptersBean> chapters;

        public NovelBean getNovel() {
            return novel;
        }

        public void setNovel(NovelBean novel) {
            this.novel = novel;
        }

        public List<ChaptersBean> getChapters() {
            return chapters;
        }

        public void setChapters(List<ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        public static class NovelBean {
            /**
             * id : 2
             * name : 千金归来：老公，请走开
             * owner_id : 13
             * author : 栀想
             * cover : novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg
             * description : 深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。“凌萱？”“陆董，我叫凌若，您是不是认错人了？”复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了……
             * complete_status : 0
             * complete_time : null
             * category_id : 3
             * sex_type : 20
             * word_num : 33345
             * sort : 0
             * chapter_num : 5
             * free_chapter_num : 0
             * create_time : 0
             * update_time : 0
             * length_type : 1
             * view_count : 0
             * is_onsale : 1
             */

            public String id;
            public String name;
            public String owner_id;
            public String author;
            public String cover;
            public String description;
            public String complete_status;
            public Object complete_time;
            public String category_id;
            public String sex_type;
            public String word_num;
            public String sort;
            public int chapter_num;
            public String free_chapter_num;
            public String create_time;
            public String update_time;
            public String length_type;
            public String view_count;
            public String is_onsale;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(String owner_id) {
                this.owner_id = owner_id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getComplete_status() {
                return complete_status;
            }

            public void setComplete_status(String complete_status) {
                this.complete_status = complete_status;
            }

            public Object getComplete_time() {
                return complete_time;
            }

            public void setComplete_time(Object complete_time) {
                this.complete_time = complete_time;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getSex_type() {
                return sex_type;
            }

            public void setSex_type(String sex_type) {
                this.sex_type = sex_type;
            }

            public String getWord_num() {
                return word_num;
            }

            public void setWord_num(String word_num) {
                this.word_num = word_num;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public int getChapter_num() {
                return chapter_num;
            }

            public void setChapter_num(int chapter_num) {
                this.chapter_num = chapter_num;
            }

            public String getFree_chapter_num() {
                return free_chapter_num;
            }

            public void setFree_chapter_num(String free_chapter_num) {
                this.free_chapter_num = free_chapter_num;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getLength_type() {
                return length_type;
            }

            public void setLength_type(String length_type) {
                this.length_type = length_type;
            }

            public String getView_count() {
                return view_count;
            }

            public void setView_count(String view_count) {
                this.view_count = view_count;
            }

            public String getIs_onsale() {
                return is_onsale;
            }

            public void setIs_onsale(String is_onsale) {
                this.is_onsale = is_onsale;
            }
        }

        public static class ChaptersBean {
            /**
             * id : 1
             * novel_id : 2
             * volume_id : 1
             * name : 第1章 三不画
             * sort : 1
             * word_num : 1574
             * create_time : 1541494361
             * update_time : 1541494361
             * publish_time : 1541494361
             * view_count : 0
             * has_been_read : false
             * is_current_read : false
             * is_free : false
             */

            public String id;
            public String novel_id;
            public String volume_id;
            public String name;
            public int sort;
            public String word_num;
            public String create_time;
            public String update_time;
            public String publish_time;
            public String view_count;
            public boolean has_been_read;
            public boolean is_current_read;
            public boolean is_free;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNovel_id() {
                return novel_id;
            }

            public void setNovel_id(String novel_id) {
                this.novel_id = novel_id;
            }

            public String getVolume_id() {
                return volume_id;
            }

            public void setVolume_id(String volume_id) {
                this.volume_id = volume_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getWord_num() {
                return word_num;
            }

            public void setWord_num(String word_num) {
                this.word_num = word_num;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public String getView_count() {
                return view_count;
            }

            public void setView_count(String view_count) {
                this.view_count = view_count;
            }

            public boolean isHas_been_read() {
                return has_been_read;
            }

            public void setHas_been_read(boolean has_been_read) {
                this.has_been_read = has_been_read;
            }

            public boolean isIs_current_read() {
                return is_current_read;
            }

            public void setIs_current_read(boolean is_current_read) {
                this.is_current_read = is_current_read;
            }

            public boolean isIs_free() {
                return is_free;
            }

            public void setIs_free(boolean is_free) {
                this.is_free = is_free;
            }
        }
    }
}
