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
package com.wxb.wanshu.api;

import com.wxb.wanshu.base.ChapterRead;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.bean.AmountRecordType;
import com.wxb.wanshu.bean.AppVersion;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.bean.ClientData;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.HotNovelList;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.bean.ReaderSigninData;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.bean.UploadPictureBean;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Completable;
import rx.Observable;

/**
 * https://github.com/JustWayward/BookReader
 *
 * @author yuyh.
 * @date 2016/8/3.
 */
public interface ApiService {

    /**
     * 获取书城首页数据
     *
     * @return
     */
    @GET("/novel/recommendList")
    Observable<HomeData> getHomeData(@Query("key") String key);

    /**
     * 打赏礼物类型
     *
     * @return
     */
    @GET("/novel/rewardType")
    Observable<RewardType> getRewardType();

    /**
     * 获取充值金额
     *
     * @return
     */
    @GET("/order/getRechargeAmount")
    Observable<RechargeAmount> getRechargeAmount();

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("/index/userInfo")
    Observable<UserInfo> getUserInfo();

    /**
     * 热门小说
     *
     * @return
     */
    @GET("/novel/hots")
    Observable<HotNovelList> getHotNovelList();

    /**
     * 小说类型列表
     *
     * @return
     */
    @GET("/novel/category")
    Observable<NovelCategory> getNovelCategory();

    /**
     * 书币明细类型
     *
     * @return
     */
    @GET("/order/billType")
    Observable<AmountRecordType> getAmountRecordType();

    /**
     * 签到
     *
     * @return
     */
    @GET("/read/signin")
    Observable<ReaderSigninData> getReaderSigninData();

    /**
     * 获取充值记录
     *
     * @return
     */
    @GET("/order/list")
    Observable<UserOrder> getOrderList(@Query("page") int page);

    /**
     * 获取阅读历史
     *
     * @return
     */
    @GET("/client/readLog")
    Observable<ReadHistoryList> getReadHistoryList(@Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 删除阅读历史
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/client/readLog")
    Observable<ReadHistoryList> delReadHistoryList(@Field("log_ids") String logs_ids, @Field("delete_all") int delete_all);

    /**
     * 获取书币明细
     *
     * @return
     */
    @GET("/order/consume")
    Observable<AmountRecordList> getAmountRecordList(@Query("page") int page);

    /**
     * 获取我的通知
     *
     * @return
     */
    @GET("/message/index")
    Observable<NotificationList> getNotificationList(@Query("page") int page);

    /**
     * 获取小说精选列表
     *
     * @return
     */
    @GET("/novel/getListByCategory")
    Observable<BookList> getSelectBookList(@Query("category_id") String category_id, @Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 获取猜你喜欢更多
     *
     * @return
     */
    @GET("/novel/mayLikeBooks")
    Observable<BookList> getLikeBooks(@Query("novel_id") String category_id, @Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 精品馆频道-更多列表
     *
     * @return
     */
    @GET("/novel/boutiqueList")
    Observable<BookList> getBoutiqueList(@Query("type") int type, @Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 短篇频道-更多列表
     *
     * @return
     */
    @GET("/novel/shortStoryList")
    Observable<BookList> getShortStoryList(@Query("category_id") int category_id, @Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 完本频道-更多列表
     *
     * @param sort   排序方式
     * @param status 完结状态
     * @param page
     * @return
     */
    @GET("/novel/finishedList")
    Observable<BookList> getFinishedList(@Query("sort") String sort, @Query("status") int status, @Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 获取排行榜列表
     *
     * @return
     */
    @GET("/novel/rankList")
    Observable<BookList> getRankBookList(@Query("type") String type, @Query("page") int page, @Query("pagesize") int pageSize);
//    @GET("/novel/finishedList?sort=time")
//    Observable<BookList> getRankBookList();

    /**
     * 获取书籍详情
     *
     * @return
     */
    @GET("/novel/detail")
    Observable<BookDetails> getBookDetail(@Query("novel_id") String novel_id, @Query("user_id") int user_id);

    /**
     * 获取书籍打赏
     *
     * @return
     */
    @GET("/novel/rewardRank")
    Observable<BookRewardData> getRewardRank(@Query("novel_id") String novel_id, @Query("page") int page);

    /**
     * 获取书架
     *
     * @return
     */
    @GET("/client/bookshelf")
    Observable<BookselfList> getBookshelfList();

    /**
     * 添加书架
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/client/bookshelf")
    Observable<Base> addBookshelfList(@Field("novel_ids") String novel_id, @Field("is_delete") int is_delete);

    /**
     * 删除书架
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/client/bookshelf")
    Observable<Base> delBookshelfList(@Field("novel_ids") String novel_ids, @Field("is_delete") int is_delete);

    /**
     * 上传图片
     *
     * @param body
     * @return
     */
    @Multipart
    @POST("/index/upload")
    Observable<UploadPictureBean> uploadSinglePicture(@Part MultipartBody.Part body);

    /**
     * 反馈意见
     *
     * @return
     */
    @POST("/index/feedback")
    Observable<Base> sendComment(@Query("data") String data);

    /**
     * 打赏礼物
     *
     * @return
     */
    @POST("/order/reward")
    Observable<Base> rewardGift(@Query("type") String type, @Query("number") int number,
                                @Query("novel_id") int novel_id, @Query("chapter_id") String chapter_id);

    /**
     * 上报设备
     * <p>
     * device_id：设备唯一标示
     * type：设备类型，1android，2ios
     * version：app版本
     * os：操作系统信息
     *
     * @return
     */
    @POST("/client/launch")
    Observable<ClientData> clientLaunch(@Query("device_id") String device_id, @Query("type") int type,
                                        @Query("version") String version, @Query("os") String os);

    /**
     * 清空系统消息
     *
     * @return
     */
    @POST("/message/flush")
    Observable<Base> flushMessage(@Query("token") String token);

    //    /**
//     * 获取正版源(若有) 与 盗版源
//     * @param view
//     * @param book
//     * @return
//     */
//    @GET("/atoc")
//    Observable<List<BookSource>> getABookSource(@Query("view") String view, @Query("book") String book);
//
//    /**
//     * 只能获取正版源
//     *
//     * @param view
//     * @param book
//     * @return
//     */
//    @GET("/btoc")
//    Observable<List<BookSource>> getBBookSource(@Query("view") String view, @Query("book") String book);
//

    /**
     * 小说目录
     *
     * @param novel_id
     * @return
     */
    @GET("/novel/catalogs")
    Observable<BookMenu> getBookMixAToc(@Query("novel_id") String novel_id);

    /**
     * 小说目录
     *
     * @return
     */
    @GET("/client/androidCommonVersion")
    Observable<AppVersion> getVersion();

    //    @GET("/mix-toc/{bookId}")
//    Observable<BookRead> getBookRead(@Path("bookId") String bookId);
//
//    @GET("/btoc/{bookId}")
//    Observable<BookMenu> getBookBToc(@Path("bookId") String bookId, @Query("view") String view);

    /**
     * 小说内容
     * <p>
     * novel_id:小说id,如2
     * user_id:用户id，默认为0
     * sort:章节序号，如1表示第一章，优先该字段。当该字段非0的时候，chapter_id和next参数无效.
     * chapter_id:章节id，默认值为0，表示最近读的一章
     * next:上/下一章，1表示下一章，-1表示上一章
     * preview:是否预读，1表示是，0表示否。预读情况下只是单纯返回章节内容，不涉及数据更新和统计，需要app端调用/novel/reportRead接口主动上报read数据。
     *
     * @return
     */
    @GET("/novel/read")
    Observable<ChapterRead> getChapterRead(@Query("novel_id") String novel_id, @Query("sort") int sort,
                                           @Query("next") int next, @Query("preview") int preview);

    /**
     * 搜索小说
     *
     * @param keyword
     * @param page
     * @return
     */
    @GET("/novel/search")
    Observable<BookList> getSearchResult(@Query("keyword") String keyword, @Query("page") int page, @Query("pagesize") int pagesize);

    /**
     * 获取分类
     *
     * @return
     */
    @GET("/novel/categories")
    Observable<NovelCategory> getCategoryList();

    /**
     * 上报阅读
     *
     * @param novel_id
     * @param chapter
     * @return
     */
    @FormUrlEncoded
    @POST("/novel/reportRead")
    Observable<Base> reportRead(@Field("novel_id") String novel_id, @Field("sort") int chapter);


//    /**
//     * 获取二级分类
//     *
//     * @return
//     */
//    @GET("/cats/lv2")
//    Observable<CategoryListLv2> getCategoryListLv2();
//
//    /**
//     * 按分类获取书籍列表
//     *
//     * @param gender male、female
//     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
//     * @param major  玄幻
//     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
//     * @param limit  50
//     * @return
//     */
//    @GET("/book/by-categories")
//    Observable<BooksByCats> getBooksByCats(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);
//
//
//    /**
//     * 获取综合讨论区帖子列表
//     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
//     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
//     *
//     * @param block      ramble:综合讨论区
//     *                   original：原创区
//     * @param duration   all
//     * @param sort       updated(默认排序)
//     *                   created(最新发布)
//     *                   comment-count(最多评论)
//     * @param type       all
//     * @param start      0
//     * @param limit      20
//     * @param distillate true(精品)
//     * @return
//     */
//    @GET("/post/by-block")
//    Observable<DiscussionList> getBookDisscussionList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);
//
//    /**
//     * 获取综合讨论区帖子详情
//     *
//     * @param disscussionId->_id
//     * @return
//     */
//    @GET("/post/{disscussionId}")
//    Observable<Disscussion> getBookDisscussionDetail(@Path("disscussionId") String disscussionId);
//
//    /**
//     * 获取神评论列表(综合讨论区、书评区、书荒区皆为同一接口)
//     *
//     * @param disscussionId->_id
//     * @return
//     */
//    @GET("/post/{disscussionId}/comment/best")
//    Observable<CommentList> getBestComments(@Path("disscussionId") String disscussionId);
//
//    /**
//     * 获取综合讨论区帖子详情内的评论列表
//     *
//     * @param disscussionId->_id
//     * @param start              0
//     * @param limit              30
//     * @return
//     */
//    @GET("/post/{disscussionId}/comment")
//    Observable<CommentList> getBookDisscussionComments(@Path("disscussionId") String disscussionId, @Query("start") String start, @Query("limit") String limit);
//
//    /**
//     * 获取书评区帖子列表
//     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
//     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
//     *
//     * @param duration   all
//     * @param sort       updated(默认排序)
//     *                   created(最新发布)
//     *                   helpful(最有用的)
//     *                   comment-count(最多评论)
//     * @param type       all(全部类型)、xhqh(玄幻奇幻)、dsyn(都市异能)...
//     * @param start      0
//     * @param limit      20
//     * @param distillate true(精品) 、空字符（全部）
//     * @return
//     */
//    @GET("/post/review")
//    Observable<BookReviewList> getBookReviewList(@Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);
//
//    /**
//     * 获取书评区帖子详情
//     *
//     * @param bookReviewId->_id
//     * @return
//     */
//    @GET("/post/review/{bookReviewId}")
//    Observable<BookReview> getBookReviewDetail(@Path("bookReviewId") String bookReviewId);
//
//    /**
//     * 获取书评区、书荒区帖子详情内的评论列表
//     *
//     * @param bookReviewId->_id
//     * @param start             0
//     * @param limit             30
//     * @return
//     */
//    @GET("/post/review/{bookReviewId}/comment")
//    Observable<CommentList> getBookReviewComments(@Path("bookReviewId") String bookReviewId, @Query("start") String start, @Query("limit") String limit);
//
//    /**
//     * 获取书荒区帖子列表
//     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
//     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
//     *
//     * @param duration   all
//     * @param sort       updated(默认排序)
//     *                   created(最新发布)
//     *                   comment-count(最多评论)
//     * @param start      0
//     * @param limit      20
//     * @param distillate true(精品) 、空字符（全部）
//     * @return
//     */
//    @GET("/post/help")
//    Observable<BookHelpList> getBookHelpList(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);
//
//    /**
//     * 获取书荒区帖子详情
//     *
//     * @param helpId->_id
//     * @return
//     */
//    @GET("/post/help/{helpId}")
//    Observable<BookHelp> getBookHelpDetail(@Path("helpId") String helpId);
//
//    /**
//     * 第三方登陆
//     *
//     * @param platform_uid
//     * @param platform_token
//     * @param platform_code  “QQ”
//     * @return
//     */
//    @POST("/user/login")
//    Observable<Login> login(@Body LoginReq loginReq);
//
//    @GET("/user/followings/{userid}")
//    Observable<Following> getFollowings(@Path("userid") String userId);
//
//    /**
//     * 获取书籍详情讨论列表
//     *
//     * @param book  bookId
//     * @param sort  updated(默认排序)
//     *              created(最新发布)
//     *              comment-count(最多评论)
//     * @param type  normal
//     *              vote
//     * @param start 0
//     * @param limit 20
//     * @return
//     */
//    @GET("/post/by-book")
//    Observable<DiscussionList> getBookDetailDisscussionList(@Query("book") String book, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit);
//
//    /**
//     * 获取书籍详情书评列表
//     *
//     * @param book  bookId
//     * @param sort  updated(默认排序)
//     *              created(最新发布)
//     *              helpful(最有用的)
//     *              comment-count(最多评论)
//     * @param start 0
//     * @param limit 20
//     * @return
//     */
//    @GET("/post/review/by-book")
//    Observable<HotReview> getBookDetailReviewList(@Query("book") String book, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit);
//
//    @GET("/post/original")
//    Observable<DiscussionList> getBookOriginalList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);
}