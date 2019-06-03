package com.example.travelnepalapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelnepalapp.Models.DashboardModel;

import java.util.List;

public class DashboardRecyclerView extends RecyclerView.Adapter<DashboardRecyclerView.DashboardViewHolder> {
    Context context;
    List<DashboardModel> dashboardList;

    public DashboardRecyclerView(Context context, List<DashboardModel> dashboardList) {
        this.context = context;
        this.dashboardList = dashboardList;
    }


    @NonNull

    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboardlayout, viewGroup, false);

        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder dashboardViewHolder, int i) {
        final DashboardModel dashboardModel = dashboardList.get(i);
        dashboardViewHolder.tvplacename.setText(dashboardModel.getPlacename());
        dashboardViewHolder.mainimage.setImageResource(dashboardModel.getImage());

    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {
        public ImageView mainimage;
        TextView tvplacename;

        public DashboardViewHolder(@NonNull View iview) {
            super(iview);
            mainimage = iview.findViewById(R.id.iv_mainimage);
            tvplacename = iview.findViewById(R.id.tv_placename);
        }
    }
}
