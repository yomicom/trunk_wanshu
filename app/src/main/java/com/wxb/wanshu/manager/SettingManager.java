package com.wxb.wanshu.manager;

import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.BookMark;
import com.wxb.wanshu.utils.ScreenUtils;
import com.wxb.wanshu.utils.SharedPreferencesUtil;
import com.wxb.wanshu.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiming on 2017/11/23.
 */

public class SettingManager {


    private volatile static SettingManager manager;

    public static SettingManager getInstance() {
        return manager != null ? manager : (manager = new SettingManager());
    }

    /**
     * 保存书籍阅读字体大小
     *
     * @param bookId     需根据bookId对应，避免由于字体大小引起的分页不准确
     * @param fontSizePx
     * @return
     */
    public void saveFontSize(String bookId, int fontSizePx) {
        // 书籍对应
        SharedPreferencesUtil.getInstance().putInt(getFontSizeKey(bookId), fontSizePx);
    }

    /**
     * 保存全局生效的阅读字体大小
     *
     * @param fontSizePx
     */
    public void saveFontSize(int fontSizePx) {
        saveFontSize("", fontSizePx);
    }

    public void saveChapterFontSize(int fontSizePx) {
        SharedPreferencesUtil.getInstance().putInt(getChapterFontSizeKey(""), fontSizePx);
    }

    public int getReadFontSize(String bookId) {
        return SharedPreferencesUtil.getInstance().getInt(getFontSizeKey(bookId), ScreenUtils.dpToPxInt(16));
    }

    public int getChapterFontSize() {
        return SharedPreferencesUtil.getInstance().getInt(getChapterFontSizeKey(""), ScreenUtils.dpToPxInt(21));
    }

    public int getReadFontSize() {
        return getReadFontSize("");
    }

    private String getFontSizeKey(String bookId) {
        return bookId + "-readFontSize";
    }

    //获得首页大标题字号
    private String getChapterFontSizeKey(String bookId) {
        return bookId + "-readChapterFontSize";
    }

    public int getReadBrightness() {
        return ScreenUtils.getScreenBrightness();
    }

    /**
     * 保存阅读界面屏幕亮度
     *
     * @param percent 亮度比例 0~100
     */
    public void saveReadBrightness(int percent) {
        if (percent > 100) {
            ToastUtils.showToast("saveReadBrightnessErr CheckRefs");
            percent = 100;
        }
        SharedPreferencesUtil.getInstance().putInt(getLightnessKey(), percent);
    }

    private String getLightnessKey() {
        return "readLightness";
    }

    public synchronized void saveReadProgress(String bookId, int currentChapter, int m_mbBufBeginPos, int m_mbBufEndPos) {
        SharedPreferencesUtil.getInstance()
                .putInt(getChapterKey(bookId), currentChapter)
                .putInt(getStartPosKey(bookId), m_mbBufBeginPos)
                .putInt(getEndPosKey(bookId), m_mbBufEndPos);
    }

    public synchronized void saveBookUpdateTime(String bookId, String update_time) {
        SharedPreferencesUtil.getInstance().putString(getChapterUpdateTime(bookId), update_time);
    }

    /**
     * 获取上次阅读改书更新时间
     *
     * @param bookId
     * @return
     */
    public String getReadBookUpdateTime(String bookId) {
        return SharedPreferencesUtil.getInstance().getString(getChapterUpdateTime(bookId), "");
    }

    /**
     * 获取上次阅读章节及位置
     *
     * @param bookId
     * @return
     */
    public int[] getReadProgress(String bookId) {
        int lastChapter = SharedPreferencesUtil.getInstance().getInt(getChapterKey(bookId), 1);
        int startPos = SharedPreferencesUtil.getInstance().getInt(getStartPosKey(bookId), 0);
        int endPos = SharedPreferencesUtil.getInstance().getInt(getEndPosKey(bookId), 0);

        return new int[]{lastChapter, startPos, endPos};
    }

    public void removeReadProgress(String bookId) {
        SharedPreferencesUtil.getInstance()
                .remove(getChapterKey(bookId))
                .remove(getStartPosKey(bookId))
                .remove(getEndPosKey(bookId));
    }

    public void removeReadUpdateTime(String bookId) {
        SharedPreferencesUtil.getInstance()
                .remove(getChapterUpdateTime(bookId));
    }

    private String getChapterKey(String bookId) {
        return bookId + "-chapter";
    }

    private String getChapterUpdateTime(String bookId) {
        return bookId + "-update_time";
    }

    private String getStartPosKey(String bookId) {
        return bookId + "-startPos";
    }

    private String getEndPosKey(String bookId) {
        return bookId + "-endPos";
    }


    public boolean addBookMark(String bookId, BookMark mark) {
        List<BookMark> marks = SharedPreferencesUtil.getInstance().getObject(getBookMarksKey(bookId), ArrayList.class);
        if (marks != null && marks.size() > 0) {
            for (BookMark item : marks) {
                if (item.chapter == mark.chapter && item.startPos == mark.startPos) {
                    return false;
                }
            }
        } else {
            marks = new ArrayList<>();
        }
        marks.add(mark);
        SharedPreferencesUtil.getInstance().putObject(getBookMarksKey(bookId), marks);
        return true;
    }

    public List<BookMark> getBookMarks(String bookId) {
        return SharedPreferencesUtil.getInstance().getObject(getBookMarksKey(bookId), ArrayList.class);
    }

    public void clearBookMarks(String bookId) {
        SharedPreferencesUtil.getInstance().remove(getBookMarksKey(bookId));
    }

    private String getBookMarksKey(String bookId) {
        return bookId + "-marks";
    }

    /**
     * 阅读页背景主题
     *
     * @param theme
     */
    public void saveReadTheme(int theme) {
        SharedPreferencesUtil.getInstance().putInt("readTheme", theme);
    }

    public int getReadTheme() {
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
            return ThemeManager.NIGHT;
        }
        return SharedPreferencesUtil.getInstance().getInt("readTheme", 0);
    }

    /**
     * 第一次进入书城
     */
    public void saveFirstEnter() {
        SharedPreferencesUtil.getInstance().putBoolean("FirstEnter", false);
    }

    public boolean getFirstEnter() {
        return SharedPreferencesUtil.getInstance().getBoolean("FirstEnter", true);
    }

    /**
     * 第一次进入书架
     */
    public void saveFirstEnterBookshelf() {
        SharedPreferencesUtil.getInstance().putBoolean("FirstEnterBookshelf", false);
    }

    public boolean getFirstEnterBookshelf() {
        return SharedPreferencesUtil.getInstance().getBoolean("FirstEnterBookshelf", true);
    }

    /**
     * 阅读页亮屏时间
     *
     * @param time
     */
    public void setScreenLight(int time) {//系统时间 0 /5/10/20/永不 -1
        SharedPreferencesUtil.getInstance().putInt("screen_light", time);
    }

    public int getScreenLight() {
        return SharedPreferencesUtil.getInstance().getInt("screen_light", 0);
    }

    /**
     * 是否可以使用音量键翻页
     *
     * @param enable
     */
    public void saveVolumeFlipEnable(boolean enable) {
        SharedPreferencesUtil.getInstance().putBoolean("volumeFlip", enable);
    }

    public boolean isVolumeFlipEnable() {
        return SharedPreferencesUtil.getInstance().getBoolean("volumeFlip", false);
    }

    public void saveAutoBrightness(boolean enable) {
        SharedPreferencesUtil.getInstance().putBoolean("autoBrightness", enable);
    }

    public boolean isAutoBrightness() {
        return SharedPreferencesUtil.getInstance().getBoolean("autoBrightness", false);
    }


    /**
     * 保存用户选择的性别
     *
     * @param sex male female
     */
    public void saveUserChooseSex(String sex) {
        SharedPreferencesUtil.getInstance().putString("userChooseSex", sex);
    }

    /**
     * 获取用户选择性别
     *
     * @return
     */
    public String getUserChooseSex() {
        return SharedPreferencesUtil.getInstance().getString("userChooseSex", Constant.Gender.MALE);
    }

    public boolean isUserChooseSex() {
        return SharedPreferencesUtil.getInstance().exists("userChooseSex");
    }

    public boolean isNoneCover() {
        return SharedPreferencesUtil.getInstance().getBoolean("isNoneCover", false);
    }

    public void saveNoneCover(boolean isNoneCover) {
        SharedPreferencesUtil.getInstance().putBoolean("isNoneCover", isNoneCover);
    }

    public boolean showGuide1() {
        return SharedPreferencesUtil.getInstance().getBoolean("showGuide_1", true);
    }

    public void saveGuide1() {
        SharedPreferencesUtil.getInstance().putBoolean("showGuide_1", false);
    }

    public boolean showGuide2() {
        return SharedPreferencesUtil.getInstance().getBoolean("showGuide2", true);
    }

    public void saveGuide2() {
        SharedPreferencesUtil.getInstance().putBoolean("showGuide2", false);
    }
}
