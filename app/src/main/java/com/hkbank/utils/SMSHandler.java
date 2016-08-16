package com.hkbank.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by junyuli on 8/15/16.
 */
public class SMSHandler extends Handler {

    private Context context;

    public SMSHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {

    }

    /**
     * 是否是事件平台发送过来的短信
     * @param msg
     * @return
     */
    public static boolean isItump(String msg) {
        return msg.indexOf("ITUMP") != -1;
    }

    /**
     * 获取短信内的事件ID
     * @param msg
     * @return
     */
    public static String getEventId(String msg) {
        String eventId = "";

        if( msg != null && msg.indexOf("事件ID") != -1 ) {
            eventId = msg.substring(msg.indexOf("事件ID"));
        }

        return eventId;
    }

}
