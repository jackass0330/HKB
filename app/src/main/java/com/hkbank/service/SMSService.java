package com.hkbank.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.hkbank.observer.SMSObserver;
import com.hkbank.utils.SMSHandler;

/**
 * 监控短信service
 *
 * Created by junyuli on 8/16/16.
 */
public class SMSService extends Service {

    private ContentResolver resolver;
    private SMSObserver observer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "SMS监听service启动", Toast.LENGTH_LONG).show();

        resolver = getContentResolver();
        observer = new SMSObserver(resolver, new SMSHandler(this));

        /* 注册观察者监听收件箱 */
        resolver.registerContentObserver(Uri.parse("content://sms/"), true, observer);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "SMS监听service关闭", Toast.LENGTH_LONG).show();

        resolver.unregisterContentObserver(observer);
    }
}
