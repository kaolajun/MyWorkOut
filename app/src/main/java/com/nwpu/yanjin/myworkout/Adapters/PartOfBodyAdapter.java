package com.nwpu.yanjin.myworkout.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Utils.PartOfBody;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PartOfBodyAdapter extends RecyclerView.Adapter<PartOfBodyAdapter.MyViewHolder> {

    private List<PartOfBody> partOfBodys = new ArrayList<>();
    private Context context;
    private ActionViewModel actionViewModel;
    private LifecycleOwner lifecycleOwner;
    private ActionAdapter  actionAdapter_aerobic,actionAdapter_chest,actionAdapter_back
                            ,actionAdapter_leg,actionAdapter_shoulder
                            ,actionAdapter_biceps,actionAdapter_triceps,actionAdapter_abs;
    public PartOfBodyAdapter(Context context, ActionViewModel actionViewModel, LifecycleOwner owner) {
        setPartOfBodys();
        this.context = context;
        this.actionViewModel = actionViewModel;
        this.lifecycleOwner = owner;
        actionAdapter_aerobic = new ActionAdapter(context,PartOfBody.AEROBIC,this.actionViewModel);
        actionAdapter_chest = new ActionAdapter(context,PartOfBody.CHEST,this.actionViewModel);
        actionAdapter_back = new ActionAdapter(context,PartOfBody.BACK,this.actionViewModel);
        actionAdapter_leg = new ActionAdapter(context,PartOfBody.LEG,this.actionViewModel);
        actionAdapter_shoulder = new ActionAdapter(context,PartOfBody.SHOULDER,this.actionViewModel);
        actionAdapter_biceps = new ActionAdapter(context,PartOfBody.BICEPS,this.actionViewModel);
        actionAdapter_triceps = new ActionAdapter(context,PartOfBody.TRICEPS,this.actionViewModel);
        actionAdapter_abs = new ActionAdapter(context,PartOfBody.ABS,this.actionViewModel);

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
        switch (partOfBody){
            case AEROBIC:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_aerobic);
                break;
            case SHOULDER:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_shoulder);
                break;
            case TRICEPS:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_triceps);
                break;
            case BICEPS:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_biceps);
                break;
            case CHEST:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_chest);
                break;
            case BACK:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_back);
                break;
            case LEG:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_leg);
                break;
            case ABS:
                holder.recy_action.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                holder.recy_action.setAdapter(actionAdapter_abs);
                break;
        }

        actionViewModel.getAllActionsLive().observe(lifecycleOwner, new Observer<List<Action>>() {
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

        holder.tv_partOfBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("partOfBody",partOfBody.getPartOfBodyName());

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_actionFragment_to_addActionFragment,bundle);

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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recy_action = itemView.findViewById(R.id.recy_action);
            tv_partOfBody = itemView.findViewById(R.id.tv_partofbody);
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
}
