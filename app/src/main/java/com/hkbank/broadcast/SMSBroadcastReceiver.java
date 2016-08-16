package com.hkbank.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.hkbank.pojo.SMSInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by junyuli on 8/15/16.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        SmsMessage msg = null;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            for (Object p : pdusObj) {
                msg = SmsMessage.createFromPdu((byte[]) p);

                String msgTxt =msg.getMessageBody();//得到消息的内容

                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                String senderNumber = msg.getOriginatingAddress();

                SMSInfo info = new SMSInfo();
                info.setSmsBody(msgTxt);
                info.setSmsAddress(senderNumber);
                info.set_id(senderNumber);

                String toastMsg = String.format("发送人：[%s];  短信内容： [%s]; 接受时间：[%s];", senderNumber, msgTxt, receiveTime);
                Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show();
                Log.i(SMSBroadcastReceiver.class.getName(), toastMsg);

                return;
            }
            return;
        }
    }

}
