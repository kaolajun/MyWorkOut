package com.nwpu.yanjin.myworkout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nwpu.yanjin.myworkout.Adapters.ActionAdapter;
import com.nwpu.yanjin.myworkout.Adapters.PartOfBodyAdapter;
import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.Utils.PartOfBody;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;

import java.util.List;

public class ActionFragment extends Fragment {

    private ActionViewModel actionViewModel;
    private RecyclerView recy_allAction;
    private PartOfBodyAdapter partOfBodyAdapter;
    public static ActionFragment newInstance() {
        return new ActionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        actionViewModel = ViewModelProviders.of(getActivity()).get(ActionViewModel.class);
        View view = inflater.inflate(R.layout.action_fragment, container, false);

        recy_allAction = view.findViewById(R.id.recyclerview_allAction);
        recy_allAction.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        partOfBodyAdapter = new PartOfBodyAdapter(getActivity(),actionViewModel,this);
        recy_allAction.setAdapter(partOfBodyAdapter);
        //防止scrollview和recyclerview冲突，同时要把scrollview换成Nestedscollview
        recy_allAction.setNestedScrollingEnabled(false);
//        actionViewModel.getAllActionsLive().observe(, new Observer<List<Action>>() {
//            @Override
//            public void onChanged(List<Action> actions) {
//                actionAdapter_aerobic.setAllActions(actions);
//                actionAdapter_chest.setAllActions(actions);
//                actionAdapter_back.setAllActions(actions);
//                actionAdapter_leg.setAllActions(actions);
//                actionAdapter_shoulder.setAllActions(actions);
//                actionAdapter_biceps.setAllActions(actions);
//                actionAdapter_triceps.setAllActions(actions);
//
//                partOfBodyAdapter.notifyDataSetChanged();
//
//            }
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO: Use the ViewModel
    }

}
