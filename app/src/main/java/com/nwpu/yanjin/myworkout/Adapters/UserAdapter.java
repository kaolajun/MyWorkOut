package com.nwpu.yanjin.myworkout.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nwpu.yanjin.myworkout.Database.Weight;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Userfunction;
import com.nwpu.yanjin.myworkout.ViewModel.WeightViewModel;
import com.nwpu.yanjin.myworkout.Widget.MyInputTargetWeightDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<Userfunction> functions = new ArrayList<>();
    private MyInputTargetWeightDialog myInputTargetWeightDialog;
    private WeightViewModel weightViewModel;

    public UserAdapter(WeightViewModel weightViewModel) {
        setFunctions();
        this.weightViewModel = weightViewModel;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.user_item,parent,false);
        return new MyViewHolder(itemView);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Userfunction userfunction = functions.get(position);
        holder.tv_function.setText(userfunction.getFunctionName());
        holder.tv_introduce.setText(userfunction.getFunctionIntroduce());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (userfunction.getId()){
                    case 1:
                        Toast.makeText(v.getContext(),"设置目标体重",Toast.LENGTH_SHORT).show();
                        myInputTargetWeightDialog = new MyInputTargetWeightDialog(v.getContext(),weightViewModel);
                        myInputTargetWeightDialog.show();
                        myInputTargetWeightDialog.setCanceledOnTouchOutside(false);
                        break;
                    case 2:
                        Toast.makeText(v.getContext(),"功能待定",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return functions.size();
    }



    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_function,tv_introduce;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_function = itemView.findViewById(R.id.tv_function);
            tv_introduce = itemView.findViewById(R.id.tv_introduce);
        }
    }

    private void setFunctions(){
        functions.add(new Userfunction("设置目标体重","有目标才有动力",1));
        functions.add(new Userfunction("功能待定","功能待定",2));
    }
}
