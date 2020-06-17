package com.nwpu.yanjin.myworkout.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Utils.ActionWithAerobicTime;
import com.nwpu.yanjin.myworkout.Utils.TodayActionDisplay;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodayActionAdapter extends RecyclerView.Adapter<TodayActionAdapter.MyActionWithDateViewHolder> {

    private List<TodayActionDisplay> actionWithAerobicTimes = new ArrayList<>();

    @NonNull
    @Override
    public MyActionWithDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todayaction_item,parent,false);

        return new MyActionWithDateViewHolder(itemView);
    }

    public void setActionWithAerobicTimes(List<TodayActionDisplay> actionWithAerobicTimes){

        this.actionWithAerobicTimes = actionWithAerobicTimes;
    }

    @Override
    public void onBindViewHolder(@NonNull MyActionWithDateViewHolder holder, int position) {
        final TodayActionDisplay actionWithAerobicTime = actionWithAerobicTimes.get(position);
        holder.tv_actionName.setText(actionWithAerobicTime.getActionName());


        if (actionWithAerobicTime.getAerobicTime() == 0){
            holder.tv_aerobicTime.setVisibility(View.GONE);
        }else{
            holder.tv_aerobicTime.setText(String.valueOf(actionWithAerobicTime.getAerobicTime() + "min"));
        }
    }

    @Override
    public int getItemCount() {
        return actionWithAerobicTimes.size();
    }

    static class MyActionWithDateViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_actionName,tv_aerobicTime;
        public MyActionWithDateViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_actionName = itemView.findViewById(R.id.tv_action);
            tv_aerobicTime = itemView.findViewById(R.id.tv_aerobictime);
        }
    }
}
