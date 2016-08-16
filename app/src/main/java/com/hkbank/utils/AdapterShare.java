package com.hkbank.utils;

import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junyuli on 8/16/16.
 */
public class AdapterShare {

    private static final Map<String, BaseAdapter> share = new HashMap<>();

    private AdapterShare() {

    }

    public static void setAdapter(String key, BaseAdapter adapter) {
        share.put(key, adapter);
    }

    public static BaseAdapter getAdapter(String key) {
        return share.get(key);
    }

}
