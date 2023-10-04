package com.company.appintegration.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.appintegration.Models.Result;
import com.company.appintegration.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final Context myContext;
    private List<Result> billerList;

    public UserAdapter(Context myContext, List<Result> myResult) {
        this.myContext = myContext;
        this.billerList = myResult;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(myContext);
        View myView = myLayoutInflater.inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        Result myResultModel = billerList.get(position);

        holder.id.setText("" + myResultModel.getId());
        holder.name.setText(myResultModel.getName());
        holder.categoryId.setText("" + myResultModel.getCategoryId());
    }

    @Override
    public int getItemCount() {
        if (billerList == null) return 0;
        return billerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView id, name, categoryId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.tv_id);
            name = itemView.findViewById(R.id.tv_name);
            categoryId = itemView.findViewById(R.id.tv_category_id);
        }
    }
}
