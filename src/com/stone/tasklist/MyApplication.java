package com.stone.tasklist;

import android.app.Application;

import com.stone.tasklist.db.Dao;
import com.stone.tasklist.db.DbHelper;
import com.stone.tasklist.db.impl.DaoImpl;
import com.stone.tasklist.utils.Keys;
import com.stone.tasklist.utils.PrefsUtils;

/**
 * Created by Administrator on 2015/1/6.
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE;
    public Dao dao;

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initDao();
    }

    private void initDao() {
        DbHelper dbHelper = new DbHelper(this, Keys.DB_NAME, null, Keys.DB_VERSION);
        if(PrefsUtils.get(this, Keys.PREFS_KEY_IS_FIRST_LAUNCH, true)) {
            dbHelper.createDatabase();
            PrefsUtils.put(this, Keys.PREFS_KEY_IS_FIRST_LAUNCH, false);
        }
        dao = new DaoImpl(dbHelper);

    }
}
