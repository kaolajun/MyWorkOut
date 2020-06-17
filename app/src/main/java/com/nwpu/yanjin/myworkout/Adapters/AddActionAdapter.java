package com.nwpu.yanjin.myworkout.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Utils.ActionWithAerobicTime;
import com.nwpu.yanjin.myworkout.Utils.ActionWithBoolean;
import com.nwpu.yanjin.myworkout.Utils.PartOfBody;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;
import com.nwpu.yanjin.myworkout.ViewModel.ActionWithDateViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddActionAdapter extends RecyclerView.Adapter<AddActionAdapter.MyActionViewHolder> {

    private List<ActionWithBoolean> actionWithBooleans = new ArrayList<>();
    private List<ActionWithAerobicTime> actionWithAerobicTimes = new ArrayList<>();

//    private List<Boolean> isSelecteds = new ArrayList<>();
    private PartOfBody partOfBody;
    public AddActionAdapter(PartOfBody partOfBody) {
        this.partOfBody = partOfBody;
//        this.isSelecteds.add(false);
    }

    //筛选符合要求的动作放入recyclerview
    public void setAllActions(List<Action> allActions){
//        //在recyclerview数据改变时，因为是读取全部数据，直接add会把总数据添加到旧数据上，造成旧数据叠加重复，所以要先清空再添加
            actionWithBooleans.clear();
            for (Action action : allActions){
                if (action.getPartOfBody().equals(partOfBody.getPartOfBodyName())){
                    actionWithBooleans.add(new ActionWithBoolean(action,false));
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

    //返回的动作不是有氧的aerobictime为零
    public List<ActionWithAerobicTime> getActions() {
        List<ActionWithAerobicTime> actionsWithAerobic = new ArrayList<>();
        for (ActionWithBoolean actionWithBoolean : actionWithBooleans ){
            //被选中且不是有氧运动
            if (actionWithBoolean.getSelected() && !actionWithBoolean.getAction().getPartOfBody().equals(PartOfBody.AEROBIC.getPartOfBodyName())){
                actionsWithAerobic.add(new ActionWithAerobicTime(actionWithBoolean.getAction(),0));
            }else if (actionWithBoolean.getSelected() && actionWithBoolean.getAction().getPartOfBody().equals(PartOfBody.AEROBIC.getPartOfBodyName())){ //被选中且为有氧动作
                Log.i("aerobictime",actionWithBoolean.getAction().getActionName());
                for (ActionWithAerobicTime actionWithAerobicTime : actionWithAerobicTimes){
                        Log.i("iiiiii",String.valueOf(actionWithAerobicTime.getAerobicTime()));
                    if (actionWithAerobicTime.getAction().equals(actionWithBoolean.getAction())){
                        actionsWithAerobic.add(actionWithAerobicTime);
                        Log.i("aerobictime",String.valueOf(actionWithAerobicTime.getAerobicTime()));
                    }
                }
            }

        }


        return actionsWithAerobic;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyActionViewHolder holder, final int position) {
        final ActionWithBoolean actionWithBoolean = actionWithBooleans.get(position);
//        final Boolean isSelected = isSelecteds.get(position);
        if (actionWithBoolean.getSelected()){
            holder.tv_action.setTextColor(Color.parseColor("#FF9900"));
            holder.iv_action.setImageResource(R.drawable.ic_remove_black_24dp);
        }else {
            holder.tv_action.setTextColor(Color.parseColor("#000000"));
            holder.iv_action.setImageResource(R.drawable.ic_add_black_24dp_orange);
        }

        holder.tv_action.setText(actionWithBoolean.getAction().getActionName());

        if(TextUtils.isEmpty(actionWithBoolean.getAction().getMuscle())){
            holder.tv_muscle.setVisibility(View.GONE);
        }else{
            holder.tv_muscle.setText(actionWithBoolean.getAction().getMuscle());
        }
        //不是有氧就不用设定时间
        if (!actionWithBoolean.getAction().getPartOfBody().equals(PartOfBody.AEROBIC.getPartOfBodyName())){
            holder.et_aerobicTime.setVisibility(View.GONE);
            holder.tv_min.setVisibility(View.GONE);
        }



        holder.iv_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aerobicTime = holder.et_aerobicTime.getText().toString();
                if (actionWithBoolean.getSelected()){
                    //如果取消的是有氧动作
                    if (actionWithBoolean.getAction().getPartOfBody().equals(PartOfBody.AEROBIC.getPartOfBodyName())){
                        actionWithAerobicTimes.remove(new ActionWithAerobicTime(actionWithBoolean.getAction(),Integer.valueOf(aerobicTime)));
                    }
                        actionWithBoolean.setSelected(false);
                        holder.tv_action.setTextColor(Color.parseColor("#000000"));
                        holder.iv_action.setImageResource(R.drawable.ic_add_black_24dp_orange);

                }else {
                    //如果添加的是有氧动作
                    if (actionWithBoolean.getAction().getPartOfBody().equals(PartOfBody.AEROBIC.getPartOfBodyName())){
                        if (TextUtils.isEmpty(aerobicTime)){
                            Toast.makeText(v.getContext(),"请输入有氧时间",Toast.LENGTH_SHORT).show();
                        }else{
                            actionWithAerobicTimes.add(new ActionWithAerobicTime(actionWithBoolean.getAction(),Integer.valueOf(aerobicTime)));
                            actionWithBoolean.setSelected(true);
                            holder.tv_action.setTextColor(Color.parseColor("#FF9900"));
                            holder.iv_action.setImageResource(R.drawable.ic_remove_black_24dp);
                        }
                    }else{
                        actionWithBoolean.setSelected(true);
                        holder.tv_action.setTextColor(Color.parseColor("#FF9900"));
                        holder.iv_action.setImageResource(R.drawable.ic_remove_black_24dp);
                    }



                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return actionWithBooleans.size();
    }

    static class MyActionViewHolder extends RecyclerView.ViewHolder{
        TextView tv_action,tv_muscle,tv_min;
        ImageView iv_action;
        EditText et_aerobicTime;
        public MyActionViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_min = itemView.findViewById(R.id.tv_min);
            tv_action = itemView.findViewById(R.id.tv_action);
            tv_muscle = itemView.findViewById(R.id.tv_muscle);
            iv_action = itemView.findViewById(R.id.iv_action);
            et_aerobicTime = itemView.findViewById(R.id.et_aerobictime);
        }
    }
}
