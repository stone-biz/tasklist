package com.stone.tasklist.biz;

import android.app.Activity;

import com.stone.tasklist.MyApplication;
import com.stone.tasklist.entity.Tasklist;

import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class LoadTask extends Thread {

    private Callback mCallback;

    public LoadTask(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void run() {
        try {
            final List<Tasklist> data = MyApplication.getInstance().dao.getFiveDaysTask();
            if(mCallback != null) {
                if(Activity.class.isInstance(mCallback)) {
                    Activity.class.cast(mCallback).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.onLoadFinised(data);
                        }
                    });
                }
            }
        } catch (Exception e){
            if(mCallback != null)
                mCallback.onLoadFaild();
        }
    }

    public interface Callback {
        void onLoadFinised(List<Tasklist> data);
        void onLoadFaild();
    }

}
