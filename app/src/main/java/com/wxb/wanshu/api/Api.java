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
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * https://github.com/JustWayward/BookReader
 *
 * @author yuyh.
 * @date 2016/8/3.
 */
public class Api {

    public static Api instance;

    private ApiService service;

    public Api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new Api(okHttpClient);
        return instance;
    }

    public Observable<HomeData> getHomeData(String key) {
        return service.getHomeData(key);
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

    public Observable<BookDetails> getBookDetail(int novel_id,int client_id,int user_id) {
        return service.getBookDetail(novel_id,client_id,user_id);
    }

    public Observable<BookList> getSearchResult(String keyword, int page) {
        return service.getSearchResult(keyword, page);
    }

    public Observable<BookRewardData> getBookReward(int novel_id, int page) {
        return service.getRewardRank(novel_id, page);
    }

    public Observable<BookselfList> getBookshelfList(int page, int pageSize) {
        return service.getBookshelfList(page, pageSize);
    }

    public Observable<BookList> getRankBookList(String type, int page) {
//        return service.getRankBookList(type, page);
        return service.getRankBookList();
    }

    public Observable<BookList> getBoutiqueList(int type, int page) {
        return service.getBoutiqueList(type, page);
//        return service.getRankBookList();
    }

    public Observable<BookList> getShortStoryList(int category_id, int page) {
        return service.getShortStoryList(category_id, page);
//        return service.getRankBookList();
    }

    public Observable<BookList> getFinishedList(String sort, int status, int page) {
        return service.getFinishedList(sort, status, page);
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
    public Observable<ClientData> clientLaunch(String device_id, int type, String version, String os) {
        return service.clientLaunch(device_id, type, version, os);
    }

    public Observable<BookMenu> getBookMixAToc(int bookId) {
        return service.getBookMixAToc(bookId);
    }

    public Observable<ChapterRead> getChapterRead(int novel_id, int chapter) {
        return service.getChapterRead(novel_id, chapter);
    }
}
