package com.example.slproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slproject.GlideApp;
import com.example.slproject.R;
import com.example.slproject.entity.TaskSelectionBean;

import java.util.ArrayList;

public class HomeTaskSelectionAdapter extends RecyclerView.Adapter<HomeTaskSelectionAdapter.ViewHolder> {
    private ArrayList<TaskSelectionBean> list;
    private OnItemClickListener onItemClickListener;
    private int thisPosition;

    public int getThisPosition() {
        return thisPosition;
    }

    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
        notifyDataSetChanged();
    }

    public void setTaskSelectionBean(ArrayList<TaskSelectionBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_taskselection_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskSelectionBean taskSelectionBean = list.get(position);
        GlideApp.with(holder.itemView).load(taskSelectionBean.getDrawable()).into(holder.mTaskselectionImg);

        holder.mTaskselectionTitle.setText(taskSelectionBean.getTitle());
        if (holder!=null){
            if (position==getThisPosition()){
                holder.mTaskselection.setBackground(holder.itemView.getResources().getDrawable(R.drawable.home_select_background));
            }else {
                holder.mTaskselection.setBackground(holder.itemView.getResources().getDrawable(R.drawable.home_unselect_background));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mTaskselectionImg;
        private TextView mTaskselectionTitle;
        private LinearLayout mTaskselection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTaskselection = itemView.findViewById(R.id.item_taskselection);
            mTaskselectionImg = itemView.findViewById(R.id.taskselectionImg);
            mTaskselectionTitle = itemView.findViewById(R.id.taskselectionTitle);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
