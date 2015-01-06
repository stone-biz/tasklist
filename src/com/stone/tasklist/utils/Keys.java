package com.stone.tasklist.utils;

/**
 * Created by Administrator on 2015/1/6.
 */
public interface Keys {
    //SharedPreferences的Key
    String PREFS_KEY_IS_FIRST_LAUNCH = "prefs_key_is_first_launch";


    //数据库
    String DB_NAME = "db_tasklist.db";
    int DB_VERSION = 1;
    String DB_TABLE_TASKITEM = "taskitem";

    //数据库表
    String DB_TB_TASKITEM_ID = "id";
    String DB_TB_TASKITEM_CONTENT = "content";
    String DB_TB_TASKITEM_DATE = "date";
    String DB_TB_TASKITEM_STATE = "state";
}
