package com.nwpu.yanjin.myworkout.Adapters;

import android.content.Context;
import android.print.PrinterId;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Utils.PartOfBody;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.MyActionViewHolder> {

    private List<Action> actions = new ArrayList<>();
    private PartOfBody partOfBody;
    private ActionViewModel actionViewModel;
    private Context context;
    public ActionAdapter(Context context,PartOfBody partOfBody,ActionViewModel actionViewModel) {
        this.partOfBody = partOfBody;
        this.actionViewModel = actionViewModel;
        this.context = context;
    }

    //筛选符合要求的动作放入recyclerview
    public void setAllActions(List<Action> allActions){
        //在recyclerview数据改变时，因为是读取全部数据，直接add会把总数据添加到旧数据上，造成旧数据叠加重复，所以要先清空再添加
        actions.clear();
        for (Action action : allActions){
            if (action.getPartOfBody().equals(partOfBody.getPartOfBodyName())){
                actions.add(action);
            }
        }
    }

    @NonNull
    @Override
    public MyActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = inflater.inflate(R.layout.action_item,parent,false);
        return new MyActionViewHolder(itemView);

//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyActionViewHolder holder, int position) {
        final Action action = actions.get(position);
        holder.tv_action.setText(action.getActionName());
        if(TextUtils.isEmpty(action.getMuscle())){
            holder.tv_muscle.setVisibility(View.GONE);
        }else{
            holder.tv_muscle.setText(action.getMuscle());
        }
        holder.iv_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionViewModel.deleteActions(action);
                Toast.makeText(context,"已删除",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    static class MyActionViewHolder extends RecyclerView.ViewHolder{
        TextView tv_action,tv_muscle,tv_min;
        ImageView iv_action;
        EditText et_aerobicTime;
        public MyActionViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_action = itemView.findViewById(R.id.tv_action);
            tv_muscle = itemView.findViewById(R.id.tv_muscle);
            tv_min = itemView.findViewById(R.id.tv_min);

            iv_action = itemView.findViewById(R.id.iv_action);
            et_aerobicTime = itemView.findViewById(R.id.et_aerobictime);
            //这个视图中不应有设置有氧时间
            et_aerobicTime.setVisibility(View.GONE);
            tv_min.setVisibility(View.GONE);

        }
    }
}
