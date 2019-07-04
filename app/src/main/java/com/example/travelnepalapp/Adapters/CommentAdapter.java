package com.example.travelnepalapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelnepalapp.Models.CommentModel;
import com.example.travelnepalapp.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private Context context;
    private List<CommentModel> commentList;

    public CommentAdapter(Context context, List<CommentModel> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.recycler_comment, viewGroup, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder viewHolder, int i) {
        CommentModel commentModel= commentList.get(i);
        viewHolder.review.setText(commentModel.getComment());


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userimage;
        private TextView review,username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userimage= itemView.findViewById(R.id.rc_comment_image);
            review= itemView.findViewById(R.id.rc_comment_review);
            username= itemView.findViewById(R.id.rc_comment_username);

        }
    }
}
