package com.hkbank.observer;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.hkbank.pojo.SMSInfo;
import com.hkbank.utils.AdapterShare;
import com.hkbank.utils.CacheManager;
import com.hkbank.utils.SMSHandler;

/**
 * 通过观察数据库变更方式监控短信变更
 *
 * Created by junyuli on 8/16/16.
 */
public class SMSObserver extends ContentObserver {

    private ContentResolver resolver;
    private SMSHandler smsHandler;

    public SMSObserver(ContentResolver resolver, SMSHandler handler) {
        super(handler);
        this.resolver = resolver;
        this.smsHandler = handler;
    }

    /**
     * TODO this method fired twice
     * 这个方法在接收短信时被调用了二次, 这个问题在国内外均遇到过, 且并未得到很好的解决;
     *
     * @param selfChange
     */
    @Override
    public void onChange(boolean selfChange) {
        Log.i("SmsObserver onChange ", "SmsObserver 短信有改变, selfChange=" + selfChange);
        Cursor mCursor = resolver.query(
                Uri.parse("content://sms/inbox"),
                new String[]{"_id", "address", "read", "body", "thread_id"},
                "read=?",
                new String[]{"0"},
                "date desc");

        if (mCursor == null) {
            return;
        }

        CacheManager.getCache().clear();
        while (mCursor.moveToNext()) {
            SMSInfo smsInfo = new SMSInfo();

            int _inIndex = mCursor.getColumnIndex("_id");
            if (_inIndex != -1) {
                smsInfo.set_id(mCursor.getString(_inIndex));
            }

            int thread_idIndex = mCursor.getColumnIndex("thread_id");
            if (thread_idIndex != -1) {
                smsInfo.setThread_id(mCursor.getString(thread_idIndex));
            }

            int addressIndex = mCursor.getColumnIndex("address");
            if (addressIndex != -1) {
                smsInfo.setSmsAddress(mCursor.getString(addressIndex));
            }

            int bodyIndex = mCursor.getColumnIndex("body");
            if (bodyIndex != -1) {
                smsInfo.setSmsBody(mCursor.getString(bodyIndex));
            }

            int readIndex = mCursor.getColumnIndex("read");
            if (readIndex != -1) {
                smsInfo.setRead(mCursor.getString(readIndex));
            }

            Log.i(SMSObserver.class.getName(), smsInfo.toString());
            CacheManager.add(smsInfo);
        }

        AdapterShare.getAdapter("SMSListView").notifyDataSetChanged();
        mCursor.close();
    }

}
