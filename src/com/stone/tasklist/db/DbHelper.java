package com.stone.tasklist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stone.tasklist.utils.Keys;

/**
 * Created by Administrator on 2015/1/6.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SQL = "CREATE TABLE " + Keys.DB_TABLE_TASKITEM + "(id INTEGER primary key, date VARCHAR,content VARCHAR, state INTEGER)";
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String INSERT_SQL = "INSERT INTO " +  Keys.DB_TABLE_TASKITEM + " (date,content,state) VALUES('0','xx',0)";
        String DELETE_SQL = "DELETE FROM " + Keys.DB_TABLE_TASKITEM + " WHERE date='0'" ;
        db.execSQL(INSERT_SQL);
        db.execSQL(DELETE_SQL);
    }
}
