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
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.bean.AmountRecordType;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.HotNovelList;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.bean.NovelRank;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.bean.ReaderSigninData;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.bean.UploadPictureBean;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static android.R.attr.data;

/**
 * https://github.com/JustWayward/BookReader
 *
 * @author yuyh.
 * @date 2016/8/3.
 */
public class BookApi {

    public static BookApi instance;

    private BookApiService service;

    public BookApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(BookApiService.class);
    }

    public static BookApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new BookApi(okHttpClient);
        return instance;
    }

    public Observable<HomeData> getHomeData(int sex_type) {
        return service.getHomeData(sex_type);
    }

    public Observable<RewardType> getRewardType() {
        return service.getRewardType();
    }

    public Observable<RechargeAmount> getRechargeAmount() {
        return service.getRechargeAmount();
    }

    public Observable<UserInfo> getUserInfo() {
        return service.getUserInfo();
    }

    public Observable<HotNovelList> getHotNovelList() {
        return service.getHotNovelList();
    }

    public Observable<NovelCategory> getNovelCategory() {
        return service.getNovelCategory();
    }

    public Observable<AmountRecordType> getAmountRecordType() {
        return service.getAmountRecordType();
    }

    public Observable<ReaderSigninData> getReaderSigninData() {
        return service.getReaderSigninData();
    }

    public Observable<UserOrder> getOrderList(int page) {
        return service.getOrderList(page);
    }

    public Observable<ReadHistoryList> getReadHistoryList(int page) {
        return service.getReadHistoryList(page);
    }

    public Observable<AmountRecordList> getAmountRecordList(int page) {
        return service.getAmountRecordList(page);
    }

    public Observable<NotificationList> getNotificationList(int page) {
        return service.getNotificationList(page);
    }

    public Observable<BookList> getSelectBookList(int sex_type, String category_id, String complete_status, int page, String kw) {
        return service.getSelectBookList(sex_type, category_id, complete_status, page, kw);
    }

    public Observable<BookDetails> getBookDetail(int novel_id) {
        return service.getBookDetail(novel_id);
    }

    public Observable<BookRewardData> getBookReward(int novel_id, int page) {
        return service.getRewardRank(novel_id,page);
    }

    public Observable<BookselfList> getBookshelfList(int page, int pageSize) {
        return service.getBookshelfList(page, pageSize);
    }

    public Observable<NovelRank> getRankBookList(int type, int sex_type, int page) {
        return service.getRankBookList(type, sex_type, page);
    }

    public Observable<Base> addBookshelfList(int novel_id) {
        return service.addBookshelfList(novel_id);
    }

    public Observable<Base> delBookshelfList(String novel_ids) {
        return service.delBookshelfList(novel_ids);
    }

    public Observable<UploadPictureBean> uploadSinglePicture(MultipartBody.Part part) {
        return service.uploadSinglePicture(part);
    }

    public Observable<Base> sendComment(String data) {
        return service.sendComment(data);
    }

    public Observable<Base> rewardGift(String type, int number, int novel_id, String chapter_id) {
        return service.rewardGift(type, number, novel_id, chapter_id);
    }

    //    public Observable<HotWord> getHotWord() {
//        return service.getHotWord();
//    }
//
//    public Observable<AutoComplete> getAutoComplete(String query) {
//        return service.autoComplete(query);
//    }
//
//    public Observable<SearchDetail> getSearchResult(String query) {
//        return service.searchBooks(query);
//    }
//
//    public Observable<BooksByTag> searchBooksByAuthor(String author) {
//        return service.searchBooksByAuthor(author);
//    }
//
//    public Observable<BookDetail> getBookDetail(String bookId) {
//        return service.getBookDetail(bookId);
//    }
//
//    public Observable<HotReview> getHotReview(String book) {
//        return service.getHotReview(book);
//    }
//
//    public Observable<RecommendBookList> getRecommendBookList(String bookId, String limit) {
//        return service.getRecommendBookList(bookId, limit);
//    }
//
//    public Observable<BooksByTag> getBooksByTag(String tags, String start, String limit) {
//        return service.getBooksByTag(tags, start, limit);
//    }
//
    public Observable<BookMenu> getBookMixAToc(int bookId) {
        return service.getBookMixAToc(bookId);
    }

    public Observable<ChapterRead> getChapterRead(int novel_id, int chapter) {
        return service.getChapterRead(novel_id, chapter);
    }
//
//    public synchronized Observable<List<BookSource>> getBookSource(String view, String book) {
//        return service.getABookSource(view, book);
//    }
//
//    public Observable<RankingList> getRanking() {
//        return service.getRanking();
//    }
//
//    public Observable<Rankings> getRanking(String rankingId) {
//        return service.getRanking(rankingId);
//    }
//
//    public Observable<BookLists> getBookLists(String duration, String sort, String start, String limit, String tag, String gender) {
//        return service.getBookLists(duration, sort, start, limit, tag, gender);
//    }
//
//    public Observable<BookListTags> getBookListTags() {
//        return service.getBookListTags();
//    }
//
//    public Observable<BookListDetail> getBookListDetail(String bookListId) {
//        return service.getBookListDetail(bookListId);
//    }
//
//    public synchronized Observable<CategoryList> getCategoryList() {
//        return service.getCategoryList();
//    }
//
//    public Observable<CategoryListLv2> getCategoryListLv2() {
//        return service.getCategoryListLv2();
//    }
//
//    public Observable<BooksByCats> getBooksByCats(String gender, String type, String major, String minor, int start, @Query("limit") int limit) {
//        return service.getBooksByCats(gender, type, major, minor, start, limit);
//    }
//
//    public Observable<DiscussionList> getBookDisscussionList(String block, String duration, String sort, String type, String start, String limit, String distillate) {
//        return service.getBookDisscussionList(block, duration, sort, type, start, limit, distillate);
//    }
//
//    public Observable<Disscussion> getBookDisscussionDetail(String disscussionId) {
//        return service.getBookDisscussionDetail(disscussionId);
//    }
//
//    public Observable<CommentList> getBestComments(String disscussionId) {
//        return service.getBestComments(disscussionId);
//    }
//
//    public Observable<CommentList> getBookDisscussionComments(String disscussionId, String start, String limit) {
//        return service.getBookDisscussionComments(disscussionId, start, limit);
//    }
//
//    public Observable<BookReviewList> getBookReviewList(String duration, String sort, String type, String start, String limit, String distillate) {
//        return service.getBookReviewList(duration, sort, type, start, limit, distillate);
//    }
//
//    public Observable<BookReview> getBookReviewDetail(String bookReviewId) {
//        return service.getBookReviewDetail(bookReviewId);
//    }
//
//    public Observable<CommentList> getBookReviewComments(String bookReviewId, String start, String limit) {
//        return service.getBookReviewComments(bookReviewId, start, limit);
//    }
//
//    public Observable<BookHelpList> getBookHelpList(String duration, String sort, String start, String limit, String distillate) {
//        return service.getBookHelpList(duration, sort, start, limit, distillate);
//    }
//
//    public Observable<BookHelp> getBookHelpDetail(String helpId) {
//        return service.getBookHelpDetail(helpId);
//    }
//
//    public Observable<Login> login(String platform_uid, String platform_token, String platform_code) {
//        LoginReq loginReq = new LoginReq();
//        loginReq.platform_code = platform_code;
//        loginReq.platform_token = platform_token;
//        loginReq.platform_uid = platform_uid;
//        return service.login(loginReq);
//    }
//
//    public Observable<DiscussionList> getBookDetailDisscussionList(String book, String sort, String type, String start, String limit) {
//        return service.getBookDetailDisscussionList(book, sort, type, start, limit);
//    }
//
//    public Observable<HotReview> getBookDetailReviewList(String book, String sort, String start, String limit) {
//        return service.getBookDetailReviewList(book, sort, start, limit);
//    }
//
//    public Observable<DiscussionList> getGirlBookDisscussionList(String block, String duration, String sort, String type, String start, String limit, String distillate) {
//        return service.getBookDisscussionList(block, duration, sort, type, start, limit, distillate);
//    }

}
