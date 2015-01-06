package com.stone.tasklist.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class Tasklist  implements Parcelable {

    private String date;
    private List<TaskItem> taskItems = new ArrayList<TaskItem>();

    public Tasklist() {}

    public Tasklist(String date) {
        this.date = date;
    }

    public Tasklist(Parcel source) {
        date = source.readString();
         source.readTypedList(taskItems, TaskItem.CREATOR);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addTaskItem(TaskItem ti) {
        if(ti!=null) {
            taskItems.add(ti);
        }
    }
    public void addTaskItems(List<TaskItem> items) {
        if(items != null && items.size() > 0) {
            taskItems.addAll(items);
        }
    }

    public String getDate() {
        return date;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(taskItems);
    }

    public static final Creator<Tasklist> CREATOR = new Creator<Tasklist>() {
        @Override
        public Tasklist createFromParcel(Parcel source) {
            return new Tasklist(source);
        }

        @Override
        public Tasklist[] newArray(int size) {
            return new Tasklist[size];
        }
    };
}
