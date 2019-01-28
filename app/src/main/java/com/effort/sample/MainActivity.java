package com.effort.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.effort.constraintlayout.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private final List<String> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20"};
        itemsList.addAll(Arrays.asList(items));

        final RecyclerView rvBusStations = findViewById(R.id.recyclerView);
        rvBusStations.addItemDecoration(new DividerItemDecoration(this,
                LinearLayout.VERTICAL));
        rvBusStations.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));


        Adapter adapter = new Adapter();
        rvBusStations.setAdapter(adapter);
        adapter.setBusStations(itemsList);

        adapter.notifyDataSetChanged();

        rvBusStations.post(new Runnable() {
            @Override
            public void run() {
                if (!rvBusStations.hasNestedScrollingParent(ViewCompat.TYPE_NON_TOUCH)) {
                    rvBusStations.startNestedScroll(View.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_NON_TOUCH);
                }
                rvBusStations.smoothScrollToPosition(itemsList.size());
            }
        });
    }

    private static class Adapter extends RecyclerView.Adapter<Adapter.StationHolder> {

        private List<String> busStations;

        public Adapter() {
            this.busStations = new ArrayList<>();
        }

        public void setBusStations(List<String> busStations) {
            this.busStations.clear();
            this.busStations.addAll(busStations);
        }

        public List<String> getBusStations() {
            return this.busStations;
        }

        @NonNull
        @Override
        public StationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StationHolder(LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull StationHolder holder, int position) {
            holder.bind(busStations.get(position));
        }

        @Override
        public int getItemCount() {
            return busStations.size();
        }

        public void clearEntries() {
            this.busStations.clear();
        }

        static class StationHolder extends RecyclerView.ViewHolder {

            private TextView tvStationName;

            public StationHolder(View itemView) {
                super(itemView);
            }

            public void bind(String busStation) {
                ((TextView) this.itemView).setText(busStation);
            }
        }

    }
}
