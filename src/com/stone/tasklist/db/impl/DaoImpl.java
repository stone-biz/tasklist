package com.stone.tasklist.db.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stone.tasklist.db.Dao;
import com.stone.tasklist.db.DbHelper;
import com.stone.tasklist.entity.TaskItem;
import com.stone.tasklist.entity.Tasklist;
import com.stone.tasklist.utils.DateUtils;
import com.stone.tasklist.utils.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class DaoImpl implements Dao{

    private DbHelper mDbHelper;

    public DaoImpl(DbHelper dbHelper) {
        this.mDbHelper = dbHelper;
    }

    @Override
    public void addTaskItem(TaskItem taskitem) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            ContentValues cvs = new ContentValues();
            cvs.put(Keys.DB_TB_TASKITEM_ID, taskitem.getId());
            cvs.put(Keys.DB_TB_TASKITEM_DATE, taskitem.getDate());
            cvs.put(Keys.DB_TB_TASKITEM_CONTENT,taskitem.getContent());
            cvs.put(Keys.DB_TB_TASKITEM_STATE, taskitem.getState());
            db.insert(Keys.DB_TABLE_TASKITEM, null, cvs);
        } finally {
            if(db != null)
                db.close();
        }
    }

    @Override
    public void deleteTaskItem(TaskItem taskItem) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            db.delete(Keys.DB_TABLE_TASKITEM, "id=", new String[]{String.valueOf(taskItem.getId())});
        } finally {
            if(db != null)
                db.close();
        }
    }

    @Override
    public void updateTaskItem(TaskItem taskItem) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            ContentValues cvs = new ContentValues();
            cvs.put(Keys.DB_TB_TASKITEM_DATE, taskItem.getDate());
            cvs.put(Keys.DB_TB_TASKITEM_CONTENT,taskItem.getContent());
            cvs.put(Keys.DB_TB_TASKITEM_STATE, taskItem.getState());
            db.update(Keys.DB_TABLE_TASKITEM,cvs,"id=",new String[]{String.valueOf(taskItem.getId())});
        } finally {
            if(db != null)
                db.close();
        }
    }

    @Override
    public Tasklist getTasklist(String date) {
        SQLiteDatabase db = null;
        Tasklist tasklist = new Tasklist(date);
        Cursor cursor = null;
        try {
            String sql = "select * from " + Keys.DB_TABLE_TASKITEM + " where date='" + date + "'";
            db = mDbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, null);

            int idColumnetIndex = cursor.getColumnIndex("id");
            int dateColumnetIndex = cursor.getColumnIndex("date");
            int contentColumnetIndex = cursor.getColumnIndex("content");
            int stateColumnetIndex = cursor.getColumnIndex("state");
            while(cursor.moveToNext()) {
                tasklist.addTaskItem(new TaskItem(cursor.getInt(idColumnetIndex),
                        cursor.getString(dateColumnetIndex),cursor.getString(contentColumnetIndex),
                        cursor.getInt(stateColumnetIndex)));
            }
        } finally {
            if(cursor != null)
                cursor.close();;
            if(db != null)
                db.close();
        }
        return tasklist;
    }

    @Override
    public void deleteTasklistByDate(String date) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            db.delete(Keys.DB_TABLE_TASKITEM, "date=", new String[]{date});
        } finally {
            if(db != null)
                db.close();
        }
    }

    @Override
    public void updateStateById(int id, int state) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            ContentValues cvs = new ContentValues();
            cvs.put(Keys.DB_TB_TASKITEM_STATE, state);
            db.update(Keys.DB_TABLE_TASKITEM,cvs,"id=",new String[]{String.valueOf(id)});
        } finally {
            if(db != null)
                db.close();
        }
    }

    @Override
    public List<Tasklist> getFiveDaysTask() {
        ArrayList<Tasklist> fiveTasklist = new ArrayList<Tasklist>();
        List<String> fiveDays = getFiveDaysString();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mDbHelper.getReadableDatabase();
            for(int i=0;i<fiveDays.size();i++) {
                String dayString = fiveDays.get(i);
                String sql = "select * from " + Keys.DB_TABLE_TASKITEM + " where date='" + dayString + "'";
                cursor = db.rawQuery(sql, null);
                int idColumnetIndex = cursor.getColumnIndex("id");
                int dateColumnetIndex = cursor.getColumnIndex("date");
                int contentColumnetIndex = cursor.getColumnIndex("content");
                int stateColumnetIndex = cursor.getColumnIndex("state");
                Tasklist tasklist = new Tasklist(dayString);
                while(cursor.moveToNext()) {
                    tasklist.addTaskItem(new TaskItem(cursor.getInt(idColumnetIndex),
                            cursor.getString(dateColumnetIndex),cursor.getString(contentColumnetIndex),
                            cursor.getInt(stateColumnetIndex)));

                }
                if(cursor != null) {
                    cursor.close();
                }
                fiveTasklist.add(tasklist);
            }
        } finally {
            if(db != null)
                db.close();
        }
        return fiveTasklist;
    }

    private List<String> getFiveDaysString() {
        ArrayList<String> results = new ArrayList<String>();
        Calendar date = Calendar.getInstance();
        for(int i=-2;i<=2;i++) {
            results.add(DateUtils.getOtherDate(date,i));
        }
        return results;
    }
}
