package com.stone.tasklist.db;

import com.stone.tasklist.entity.TaskItem;
import com.stone.tasklist.entity.Tasklist;

import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public interface Dao {
    void addTaskItem(TaskItem taskitem);
    void deleteTaskItem(TaskItem taskItem);
    void updateTaskItem(TaskItem taskItem);


    Tasklist getTasklist(String date);
    void deleteTasklistByDate(String date);

    void updateStateById(int id, int state);

    List<Tasklist> getFiveDaysTask();
}
