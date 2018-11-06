package com.wxb.wanshu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiming on 2017/11/29.
 */

public class HomeData extends Base{

    private List<DataBeanX> data;

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * type : big_cover
         * data : [{"novel_id":1,"name":"偏爱成狂：总裁掳妻请绕道","cover":"http://laidian-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-94da23b4cfbe26025d6c482fa5ef975d.jpg","description":"他，是宛城家喻户晓的傅氏首席，倨傲，清贵，却偏偏对一个女人痴心不改，宁做最卑微的情种。四年前，她说真正爱的是另一个男人，并且怀了他的孩子。四年后，再相逢，他不惜一切代价，要让她得到应有的惩罚，可罚来罚去，才发现最痛不欲生的那个人，还是他\u2026\u2026","word_num":1234567,"author":"猫尔","category_name":"时空穿越","is_complete":true},{"novel_id":2,"name":"千金归来：老公，请走开","cover":"http://laidian-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":33345,"author":"栀想","category_name":"总裁豪门","is_complete":false},{"novel_id":1,"name":"偏爱成狂：总裁掳妻请绕道","cover":"http://laidian-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-94da23b4cfbe26025d6c482fa5ef975d.jpg","description":"他，是宛城家喻户晓的傅氏首席，倨傲，清贵，却偏偏对一个女人痴心不改，宁做最卑微的情种。四年前，她说真正爱的是另一个男人，并且怀了他的孩子。四年后，再相逢，他不惜一切代价，要让她得到应有的惩罚，可罚来罚去，才发现最痛不欲生的那个人，还是他\u2026\u2026","word_num":1234567,"author":"猫尔","category_name":"时空穿越","is_complete":true},{"novel_id":2,"name":"千金归来：老公，请走开","cover":"http://laidian-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":33345,"author":"栀想","category_name":"总裁豪门","is_complete":false},{"novel_id":1,"name":"偏爱成狂：总裁掳妻请绕道","cover":"http://laidian-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-94da23b4cfbe26025d6c482fa5ef975d.jpg","description":"他，是宛城家喻户晓的傅氏首席，倨傲，清贵，却偏偏对一个女人痴心不改，宁做最卑微的情种。四年前，她说真正爱的是另一个男人，并且怀了他的孩子。四年后，再相逢，他不惜一切代价，要让她得到应有的惩罚，可罚来罚去，才发现最痛不欲生的那个人，还是他\u2026\u2026","word_num":1234567,"author":"猫尔","category_name":"时空穿越","is_complete":true}]
         */

        private String type;
        private String name;
        private List<DataBean> data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static class DataBean extends Book {

        }
    }
}
