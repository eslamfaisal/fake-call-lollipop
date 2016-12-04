package com.ztech.fakecalllollipop;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.NotificationCompat;

import java.io.InputStream;

public class FakeSMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context);

        ContentResolver contentResolver = context.getContentResolver();

        String contactImage = intent.getStringExtra("contactImage");

        String name = intent.getStringExtra("name");

        String number = intent.getStringExtra("number");

        String message = intent.getStringExtra("message");

        if (contactImage != null && !contactImage.equals("")) {

            Uri contactImageUri = Uri.parse(contactImage);

            try {

                InputStream contactImageStream = contentResolver.openInputStream(contactImageUri);

                Bitmap icon = BitmapFactory.decodeStream(contactImageStream);

                icon.setHasMipMap(true);

                nBuilder.setLargeIcon(FakeSMSReceiver.getCircleBitmap(icon));

            } catch (Exception e) {

            }


        }

        if (name == null) {

            nBuilder.setContentTitle(number);

        } else {

            nBuilder.setContentTitle(name);

        }

        nBuilder.setDefaults(Notification.DEFAULT_ALL);

        nBuilder.setSmallIcon(R.drawable.sms, 1);

        nBuilder.setContentText(message);

        nBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent showSMS = new Intent(Intent.ACTION_VIEW);

        showSMS.setType(CallLog.Calls.CONTENT_TYPE);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, showSMS, PendingIntent.FLAG_CANCEL_CURRENT);

        nBuilder.setColor(Color.rgb(0, 172, 193));

        nBuilder.addAction(R.drawable.ic_reply, "Reply", pendingIntent);

        nBuilder.addAction(R.drawable.ic_mark_read, "Read", pendingIntent);

        nBuilder.addAction(R.drawable.ic_call, "Call", pendingIntent);

        nm.notify(2001, nBuilder.build());

    }

    private static Bitmap getCircleBitmap(Bitmap bitmap) {

        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;

        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);

        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

}
