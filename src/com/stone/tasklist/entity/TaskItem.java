package com.stone.tasklist.entity;

import android.os.Parcel;
import android.os.Parcelable;

import static android.os.Parcelable.Creator;

/**
 * Created by Administrator on 2015/1/6.
 */
public class TaskItem implements Parcelable{
    private int id;
    private String date; // 格式为: yyyy/MM/dd
    private String content;
    private int state = 0; //0-未完成, 1-已完成

    public TaskItem() {}

    public TaskItem(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public TaskItem(String date, String content, int state) {
        this.date = date;
        this.content = content;
        this.state = state;
    }

    public TaskItem(int id, String date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

    public TaskItem(int id, String date, String content, int state) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.state = state;
    }

    public TaskItem(Parcel source) {
        id = source.readInt();
        date = source.readString();
        content = source.readString();
        state = source.readInt();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(date);
        dest.writeString(content);
        dest.writeInt(state);
    }

    public static final Creator<TaskItem> CREATOR = new Creator<TaskItem>() {
        @Override
        public TaskItem createFromParcel(Parcel source) {
            return new TaskItem(source);
        }

        @Override
        public TaskItem[] newArray(int size) {
            return new TaskItem[size];
        }
    };
}
