package com.example.doan_android_2021.utlis;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Timer;
import java.util.TimerTask;

public class AutoScroll {
    private final RecyclerView rv;
    private final LinearLayoutManager layoutManager;

    private Timer timer;
    private TimerTask timerTask;
    private int position;

    public AutoScroll(RecyclerView rv, LinearLayoutManager layoutManager) {
        this.rv = rv;
        this.layoutManager = layoutManager;
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    stopAutoScrollBanner();
                } else if (newState == 0) {
                    position = layoutManager.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();
                }
            }
        });
    }

    public void runAutoScrollBanner() {
        if (timer == null && timerTask == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (position == Integer.MAX_VALUE) {
                        position = Integer.MAX_VALUE / 2;
                        rv.scrollToPosition(position);
                        rv.smoothScrollBy(5, 0);
                    } else {
                        position++;
                        rv.smoothScrollToPosition(position);
                    }
                }
            };
            timer.schedule(timerTask, 3000, 3000);
        }
    }

    public void stopAutoScrollBanner() {
        if (timer != null && timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = layoutManager.findFirstCompletelyVisibleItemPosition();
        }
    }
}
