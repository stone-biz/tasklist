package com.stone.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.stone.tasklist.entity.TaskItem;

import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class TasklistAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<TaskItem> mData;

    public TasklistAdapter(Context ctx, List<TaskItem> data) {
        mContext = ctx;
        mData = data;
        if(mContext!=null) {
            mInflater = LayoutInflater.from(mContext);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.lv_item, parent, false);
            holder.tvContent = (TextView)convertView.findViewById(R.id.tasklist_content);
            holder.btnFinish = (Button)convertView.findViewById(R.id.tasklist_finish);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final TaskItem item = mData.get(position);
        holder.tvContent.setText(item.getContent());
        holder.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        if(item.getState() == 0) {

        } else {

        }

        return convertView;
    }

    class ViewHolder {
        TextView tvContent;
        Button btnFinish;
    }
}
