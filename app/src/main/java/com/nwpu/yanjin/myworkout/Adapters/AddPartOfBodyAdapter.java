package com.nwpu.yanjin.myworkout.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.Database.ActionWithDate;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Utils.ActionWithAerobicTime;
import com.nwpu.yanjin.myworkout.Utils.PartOfBody;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;
import com.nwpu.yanjin.myworkout.ViewModel.ActionWithDateViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddPartOfBodyAdapter extends RecyclerView.Adapter<AddPartOfBodyAdapter.MyViewHolder> {

    private List<PartOfBody> partOfBodys = new ArrayList<>();
    private Context context;
    private AddActionAdapter  actionAdapter_aerobic,actionAdapter_chest,actionAdapter_back
                            ,actionAdapter_leg,actionAdapter_shoulder
                            ,actionAdapter_biceps,actionAdapter_triceps,actionAdapter_abs;
    private ActionViewModel actionViewModel;
    private LifecycleOwner owner;
    private List<Action> allActions;
    private List<ActionWithAerobicTime> ActionsSelected = new ArrayList<>();
    private List<Action> aerobicAction = new ArrayList<>();
    private List<Action> chestAction = new ArrayList<>();
    private List<Action> backAction = new ArrayList<>();
    private List<Action> legAction = new ArrayList<>();
    private List<Action> shoulderAction = new ArrayList<>();
    private List<Action> bicepsAction = new ArrayList<>();
    private List<Action> tricepsAction = new ArrayList<>();
    private List<Action> absAction = new ArrayList<>();

    public AddPartOfBodyAdapter(Context context,ActionViewModel actionViewModel,LifecycleOwner owner) {
        setPartOfBodys();
        this.context = context;
        this.actionViewModel = actionViewModel;
        this.owner = owner;
        this.allActions = actionViewModel.getAllActionsLive().getValue();


        actionAdapter_aerobic = new AddActionAdapter(PartOfBody.AEROBIC);
        actionAdapter_chest = new AddActionAdapter(PartOfBody.CHEST);
        actionAdapter_back = new AddActionAdapter(PartOfBody.BACK);
        actionAdapter_leg = new AddActionAdapter(PartOfBody.LEG);
        actionAdapter_shoulder = new AddActionAdapter(PartOfBody.SHOULDER);
        actionAdapter_biceps = new AddActionAdapter(PartOfBody.BICEPS);
        actionAdapter_triceps = new AddActionAdapter(PartOfBody.TRICEPS);
        actionAdapter_abs = new AddActionAdapter(PartOfBody.ABS);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = inflater.inflate(R.layout.partofbody_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PartOfBody partOfBody = partOfBodys.get(position);
        holder.tv_partOfBody.setText(partOfBody.getPartOfBodyName());

        holder.iv_partOfBody.setVisibility(View.GONE);
        holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        switch (partOfBody){
            case AEROBIC:
                holder.recy_action.setAdapter(actionAdapter_aerobic);
                break;
            case SHOULDER:
                holder.recy_action.setAdapter(actionAdapter_shoulder);
                break;
            case TRICEPS:
                holder.recy_action.setAdapter(actionAdapter_triceps);
                break;
            case BICEPS:
                holder.recy_action.setAdapter(actionAdapter_biceps);
                break;
            case CHEST:
                holder.recy_action.setAdapter(actionAdapter_chest);
                break;
            case BACK:
                holder.recy_action.setAdapter(actionAdapter_back);
                break;
            case LEG:
                holder.recy_action.setAdapter(actionAdapter_leg);
                break;
            case ABS:
                holder.recy_action.setAdapter(actionAdapter_abs);
                break;
        }

        actionViewModel.getAllActionsLive().observe(owner, new Observer<List<Action>>() {
            @Override
            public void onChanged(List<Action> actions) {
                actionAdapter_aerobic.setAllActions(actions);
                actionAdapter_chest.setAllActions(actions);
                actionAdapter_back.setAllActions(actions);
                actionAdapter_leg.setAllActions(actions);
                actionAdapter_shoulder.setAllActions(actions);
                actionAdapter_biceps.setAllActions(actions);
                actionAdapter_triceps.setAllActions(actions);
                actionAdapter_abs.setAllActions(actions);

                actionAdapter_aerobic.notifyDataSetChanged();
                actionAdapter_chest.notifyDataSetChanged();
                actionAdapter_back.notifyDataSetChanged();
                actionAdapter_leg.notifyDataSetChanged();
                actionAdapter_shoulder.notifyDataSetChanged();
                actionAdapter_biceps.notifyDataSetChanged();
                actionAdapter_triceps.notifyDataSetChanged();
                actionAdapter_abs.notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {
        return partOfBodys.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recy_action;
        private TextView tv_partOfBody;
        private ImageView iv_partOfBody;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recy_action = itemView.findViewById(R.id.recy_action);
            tv_partOfBody = itemView.findViewById(R.id.tv_partofbody);
            iv_partOfBody = itemView.findViewById(R.id.iv_partOfBody);
        }
    }

    private void setPartOfBodys(){
        partOfBodys.add(PartOfBody.AEROBIC);
        partOfBodys.add(PartOfBody.CHEST);
        partOfBodys.add(PartOfBody.BACK);
        partOfBodys.add(PartOfBody.LEG);
        partOfBodys.add(PartOfBody.SHOULDER);
        partOfBodys.add(PartOfBody.BICEPS);
        partOfBodys.add(PartOfBody.TRICEPS);
        partOfBodys.add(PartOfBody.ABS);

    }

    //返回被选中的动作

    public List<ActionWithAerobicTime> getSelectedActions(){
//        ActionsSelected.clear();
        ActionsSelected.addAll(actionAdapter_aerobic.getActions());
        ActionsSelected.addAll(actionAdapter_chest.getActions());
        ActionsSelected.addAll(actionAdapter_back.getActions());
        ActionsSelected.addAll(actionAdapter_leg.getActions());
        ActionsSelected.addAll(actionAdapter_shoulder.getActions());
        ActionsSelected.addAll(actionAdapter_biceps.getActions());
        ActionsSelected.addAll(actionAdapter_triceps.getActions());
        ActionsSelected.addAll(actionAdapter_abs.getActions());
        return ActionsSelected;
    }
}
