package com.recyclerview.ty.recyclerviewdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.recyclerview.ty.recyclerviewdemo.adapter.RecycleLinearLayoutAdapter;
import com.recyclerview.ty.recyclerviewdemo.weight.DividerLinearLayoutItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView id_recyclerview;
    private RecycleLinearLayoutAdapter recycleAdapter;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moniData();
        initView();
//        sendNotification();
    }

    private void moniData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            list.add(i + "");
        }
    }

    private void initView() {
        id_recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
        recycleAdapter = new RecycleLinearLayoutAdapter(MainActivity.this, list);
        //ListView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //GridView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        //瀑布流
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);

//设置布局管理器
        id_recyclerview.setLayoutManager(layoutManager);
//设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//设置Adapter
        id_recyclerview.setAdapter(recycleAdapter);
        //设置分隔线
        id_recyclerview.addItemDecoration(new DividerLinearLayoutItemDecoration(this,
                DividerLinearLayoutItemDecoration.VERTICAL_LIST));
//        id_recyclerview.addItemDecoration(new DividerGridLayoutItemDecoration(this));
//设置增加或删除条目的动画
        id_recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private void sendNotification() {
//        Intent intent = new Intent(this, OnCallActivity.class);
        //发送广播
//        String mac = data.getString("mac");
        Intent intent = new Intent();
        intent.setAction("com.udp.GCM_BROADCAST");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        //设置为手机响铃
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("有人呼叫")
                .setContentText("")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setDefaults(NotificationCompat.DEFAULT_LIGHTS|NotificationCompat.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = notificationBuilder.build();
        //三色灯及声音震动提醒，持续
        notification.flags = Notification.FLAG_INSISTENT|Notification.FLAG_SHOW_LIGHTS|Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0 /* ID of notification */, notification);
    }
}
