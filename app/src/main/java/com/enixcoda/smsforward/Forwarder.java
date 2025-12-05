package com.enixcoda.smsforward;

import android.telephony.SmsManager;
import android.util.Log;

public class Forwarder {
    static final int MAX_SMS_LENGTH = 120;

    public static void sendSMS(String number, String content) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, content, null, null);
    }

    public static void forwardViaSMS(String senderNumber, String senderName, String forwardContent, String forwardNumber) {
        String sender = senderName == null ? senderNumber : senderName + " (" + senderNumber + ")";
        String forwardPrefix = String.format("From %s:\n", sender);

        try {
            if ((forwardPrefix + forwardContent).getBytes().length > MAX_SMS_LENGTH) {
                // there is a length limit in SMS, if the message length exceeds it, separate the meta data and content
                sendSMS(forwardNumber, forwardPrefix);
                sendSMS(forwardNumber, forwardContent);
            } else {
                // if it's not too long, combine meta data and content to save money
                sendSMS(forwardNumber, forwardPrefix + forwardContent);
            }
        } catch (RuntimeException e) {
            Log.w(Forwarder.class.toString(), e.toString());
        }
    }

    public static void forwardViaTelegram(String senderNumber, String senderName, String message, String targetTelegramID, String telegramToken) {
        new ForwardTaskForTelegram(senderNumber, senderName, message, targetTelegramID, telegramToken).execute();
    }

    public static void forwardViaRocketChat(String senderNumber, String senderName, String message, String baseUrl, String userId, String token, String channel) {
        new ForwardTaskForRocketChat(senderNumber, senderName, message, baseUrl, userId, token, channel).execute();
    }

    public static void forwardViaTwilio(String senderNumber, String senderName, String accountSid, String authToken, String fromNumber, String toNumber, String message) {
        String sender = senderName == null ? senderNumber : senderName + " (" + senderNumber + ")";
        ForwardTaskForTwilio forwardTaskForTwilio = new ForwardTaskForTwilio(accountSid, authToken, fromNumber, toNumber, "From " + sender + ":\n" + message);
        forwardTaskForTwilio.sendTwilioSms();
    }

    public static void forwardViaWeb(String senderNumber, String senderName, String message, String endpoint) {
        new ForwardTaskForWeb(senderNumber, senderName, message, endpoint).send();
    }

    public static void forwardViaEmail(String senderNumber, String senderName, String message, EmailPreferences emailPref) {
        String sender = senderName == null ? senderNumber : senderName + " (" + senderNumber + ")";
        new ForwardTaskForEmail(
                emailPref.getSmtpHost(),
                emailPref.getSmtpPort(),
                emailPref.getSmtpUser(),
                emailPref.getSmtpPassword(),
                emailPref.getFromEmail(),
                emailPref.getToEmail(),
                "Forwarded SMS message from " + sender,
                message
        ).send();
    }
}
