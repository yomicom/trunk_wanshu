package com.wxb.wanshu.manager;

import org.simple.eventbus.EventBus;

/**
 * Created by qiming on 2017/11/23.
 */

public class EventManager {

    public static void refreshCollectionList() {
        EventBus.getDefault().post(new RefreshCollectionListEvent());
    }

    public static void refreshCollectionIcon() {
        EventBus.getDefault().post(new RefreshCollectionIconEvent());
    }

    public static void refreshSubCategory(String minor, String type) {
        EventBus.getDefault().post(new SubEvent(minor, type));
    }

    private static class RefreshCollectionListEvent {
    }

    private static class RefreshCollectionIconEvent {
    }

    private static class SubEvent {

        public String minor;

        public String type;

        public SubEvent(String minor, String type) {
            this.minor = minor;
            this.type = type;
        }
    }
}
