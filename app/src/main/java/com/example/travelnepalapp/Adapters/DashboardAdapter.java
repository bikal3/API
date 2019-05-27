package com.example.travelnepalapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelnepalapp.Models.DashboardModel;
import com.example.travelnepalapp.R;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private Context context;
    private List<DashboardModel> dashboardModelList;

    public DashboardAdapter(Context context, List<DashboardModel> dashboardModelList) {
        this.context = context;
        this.dashboardModelList = dashboardModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(context).inflate(R.layout.dashboardlayout,viewGroup,false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    DashboardModel dashboardModel= dashboardModelList.get(i);
    viewHolder.tv_imagename.setText(dashboardModel.getPlacename());
    }

    @Override
    public int getItemCount() {
        return dashboardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image;
        private TextView tv_imagename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image=itemView.findViewById(R.id.iv_mainimage);
            tv_imagename=itemView.findViewById(R.id.tv_placename);

        }
    }
}
