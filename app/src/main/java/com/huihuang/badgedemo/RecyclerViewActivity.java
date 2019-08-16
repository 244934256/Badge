package com.huihuang.badgedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huihuang.badgemodule.Badge;
import com.huihuang.badgemodule.DataSupport;
import com.huihuang.badgemodule.QBadgeView;

import java.util.List;



public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.huihuang.badgemodule.R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(com.huihuang.badgemodule.R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter());
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
        private List<String> data;

        public RecyclerAdapter() {
            data = new DataSupport().getData();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(RecyclerViewActivity.this).inflate(com.huihuang.badgemodule.R.layout.item_view, parent, false));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(data.get(position));
            holder.badge.setBadgeNumber(position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView textView;
            Badge badge;

            public Holder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(com.huihuang.badgemodule.R.id.tv_content);
                badge = new QBadgeView(RecyclerViewActivity.this).bindTarget(itemView.findViewById(com.huihuang.badgemodule.R.id.root));
                badge.setBadgeGravity(Gravity.CENTER | Gravity.END);
                badge.setBadgeTextSize(14, true);
                badge.setBadgePadding(6, true);
                badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (dragState == STATE_SUCCEED) {
                            Toast.makeText(RecyclerViewActivity.this, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
