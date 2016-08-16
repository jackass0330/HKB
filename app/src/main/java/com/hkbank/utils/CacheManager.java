package com.hkbank.utils;

import com.hkbank.pojo.SMSInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junyuli on 8/15/16.
 */
public class CacheManager {

    private static final List<SMSInfo> list = new ArrayList<>();

    private CacheManager() {

    }

    public static void add(SMSInfo info) {
        list.add(info);
    }

    public static SMSInfo get(int pos) {
        return list.get(pos);
    }

    public static List<SMSInfo> getCache() {
        return list;
    }

}
