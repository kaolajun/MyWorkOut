package com.nwpu.yanjin.myworkout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nwpu.yanjin.myworkout.Adapters.AddPartOfBodyAdapter;
import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.Database.ActionWithDate;
import com.nwpu.yanjin.myworkout.Utils.ActionWithAerobicTime;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;
import com.nwpu.yanjin.myworkout.ViewModel.ActionWithDateViewModel;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TodayActionFragment extends Fragment {

    private ActionViewModel actionViewModel;
    private RecyclerView recy_allAction;
    private AddPartOfBodyAdapter addpartOfBodyAdapter;
    private FloatingActionButton floatingActionButton;
    private ActionWithDateViewModel actionWithDateViewModel;
    private List<ActionWithAerobicTime> allActionsSelected;
    private String date;
    private StringBuilder actionSelectedString = new StringBuilder();
    public static ActionFragment newInstance() {
        return new ActionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.todayaction_fragment, container, false);

        actionWithDateViewModel = ViewModelProviders.of(getActivity()).get(ActionWithDateViewModel.class);
        actionViewModel = ViewModelProviders.of(getActivity()).get(ActionViewModel.class);

        addpartOfBodyAdapter = new AddPartOfBodyAdapter(getActivity(),actionViewModel,this);
        recy_allAction = view.findViewById(R.id.recyclerview_allAction);
        recy_allAction.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        recy_allAction.setAdapter(addpartOfBodyAdapter);
        //防止scrollview和recyclerview冲突，同时要把scrollview换成Nestedscollview
        recy_allAction.setNestedScrollingEnabled(false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton_checkaddaciton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击以后返回选中的动作
                allActionsSelected = addpartOfBodyAdapter.getSelectedActions();
                date = getArguments().getString("date");
                if (allActionsSelected != null){
                    for (ActionWithAerobicTime actionWithAerobicTime : allActionsSelected){
                        Log.i("选中的动作",actionWithAerobicTime.getAction().getActionName());
                        String actionName = actionWithAerobicTime.getAction().getActionName();
                        String aerobicTime = String.valueOf(actionWithAerobicTime.getAerobicTime());
                        actionSelectedString.append(actionName).append(":").append(aerobicTime).append("+");
                    }
                    Log.i("选中的动作数", String.valueOf(allActionsSelected.size()));

                    List<ActionWithDate> actionWithDates = actionWithDateViewModel.getAllActionWithDatesLive().getValue();

                    if (actionWithDates.size() == 0){
                        Log.i("选中的动作", date);
                        actionWithDateViewModel.insertActionWithDates(new ActionWithDate(date,actionSelectedString.toString()));
                    }else{
                        boolean isInDatabase = false;
                        //此日期登记过则更新
                        for (ActionWithDate actionWithDate : actionWithDates ){
                            if (actionWithDate.getDate().equals(date)){
                                isInDatabase = true;
                                actionWithDateViewModel.updateActionWithDates(new ActionWithDate(date,actionSelectedString.toString()));
                            }
                        }
                        //如果此日期没有登记过，则插入
                        if (!isInDatabase){
                            actionWithDateViewModel.insertActionWithDates(new ActionWithDate(date,actionSelectedString.toString()));
                        }
                    }
                }
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_todayActionFragment_to_mainFragment);
            }
        });

        actionWithDateViewModel.getAllActionWithDatesLive().observe(this, new Observer<List<ActionWithDate>>() {
            @Override
            public void onChanged(List<ActionWithDate> actionWithDates) {

            }
        });

//        allActionsSelected = addpartOfBodyAdapter.getSelectedActions();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO: Use the ViewModel

    }
}
