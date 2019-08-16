package com.huihuang.badgedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huihuang.badgemodule.Badge;
import com.huihuang.badgemodule.DataSupport;
import com.huihuang.badgemodule.QBadgeView;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.huihuang.badgemodule.R.layout.activity_list_view);
        listview = (ListView) findViewById(com.huihuang.badgemodule.R.id.listview);
        listview.setAdapter(new ListAdapter());
    }

    class ListAdapter extends BaseAdapter {
        private List<String> data;

        public ListAdapter() {
            data = new DataSupport().getData();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(ListViewActivity.this).inflate(com.huihuang.badgemodule.R.layout.item_view, parent, false);
                holder.textView = (TextView) convertView.findViewById(com.huihuang.badgemodule.R.id.tv_content);
                holder.badge = new QBadgeView(ListViewActivity.this).bindTarget(convertView.findViewById(com.huihuang.badgemodule.R.id.imageview));
                holder.badge.setBadgeTextSize(12, true);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView.setText(data.get(position));
            holder.badge.setBadgeNumber(position);
            holder.badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                @Override
                public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                    if (dragState == STATE_SUCCEED) {
                        Toast.makeText(ListViewActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return convertView;
        }

        class Holder {
            TextView textView;
            Badge badge;
        }
    }
}
