package com.nwpu.yanjin.myworkout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nwpu.yanjin.myworkout.Adapters.TodayActionAdapter;
import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.Database.ActionWithDate;
import com.nwpu.yanjin.myworkout.Utils.ActionWithAerobicTime;
import com.nwpu.yanjin.myworkout.Utils.TodayActionDisplay;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;
import com.nwpu.yanjin.myworkout.ViewModel.ActionWithDateViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private FloatingActionButton floatingActionButton;
    private CalendarView calendarView;
    private ActionWithDateViewModel actionWithDateViewModel;
    private String date;
    private RecyclerView recy_todayActions;
    private TodayActionAdapter todayActionAdapter;
    private ActionViewModel actionViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recy_todayActions = view.findViewById(R.id.recyclerView_todayActions);
        recy_todayActions.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        todayActionAdapter = new TodayActionAdapter();
        recy_todayActions.setAdapter(todayActionAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        calendarView = view.findViewById(R.id.calendarView);
        floatingActionButton = view.findViewById(R.id.floatingActionButton_addAction);

        Calendar today = Calendar.getInstance();
        actionWithDateViewModel = ViewModelProviders.of(getActivity()).get(ActionWithDateViewModel.class);
        actionViewModel =  ViewModelProviders.of(getActivity()).get(ActionViewModel.class);

        date = String.valueOf(today.get(Calendar.YEAR)) + String.valueOf(today.get(Calendar.MONTH)) + String.valueOf(today.get(Calendar.DAY_OF_MONTH));

        Log.i("日期",date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //date的格式
                date = String.valueOf(year)+String.valueOf(month)+String.valueOf(dayOfMonth);

                boolean isInDatabase = false;
                List<TodayActionDisplay> actionWithAerobicTimes = new ArrayList<>();
                for (ActionWithDate actionWithDate : actionWithDateViewModel.getAllActionWithDatesLive().getValue()){
                    if (actionWithDate.getDate().equals(date)){
                        isInDatabase = true;
                        if (!TextUtils.isEmpty(actionWithDate.getActionsWithDateName())){
                            String[] todayActions = actionWithDate.getActionsWithDateName().split("\\+",0);

                            for (String s : todayActions){
                                String[] actionWithAerobicTime = s.split(":",0);

                                actionWithAerobicTimes.add(new TodayActionDisplay(actionWithAerobicTime[0],Integer.valueOf(actionWithAerobicTime[1])));
                            }
                        }
                        todayActionAdapter.setActionWithAerobicTimes(actionWithAerobicTimes);
                        todayActionAdapter.notifyDataSetChanged();

                    }
                }
                if (!isInDatabase){
                    todayActionAdapter.setActionWithAerobicTimes(actionWithAerobicTimes);
                    todayActionAdapter.notifyDataSetChanged();

                }

            }
        });
        actionWithDateViewModel.getAllActionWithDatesLive().observe(this, new Observer<List<ActionWithDate>>() {
            @Override
            public void onChanged(List<ActionWithDate> actionWithDates) {

                final List<Action> actions1 = actionViewModel.getAllActionsLive().getValue();
                for (ActionWithDate actionWithDate : actionWithDates){
                    List<TodayActionDisplay> actionWithAerobicTimes = new ArrayList<>();
                    if (actionWithDate.getDate().equals(date)){
                        if (!TextUtils.isEmpty(actionWithDate.getActionsWithDateName())){
                            String[] todayActions = actionWithDate.getActionsWithDateName().split("\\+",0);
                            for (String s : todayActions){
                                String[] actionWithAerobicTime = s.split(":",0);
                                actionWithAerobicTimes.add(new TodayActionDisplay(actionWithAerobicTime[0],Integer.valueOf(actionWithAerobicTime[1])));
                            }
                        }
                        todayActionAdapter.setActionWithAerobicTimes(actionWithAerobicTimes);
                        todayActionAdapter.notifyDataSetChanged();


                    }
                }
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date", date);

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_mainFragment_to_todayActionFragment,bundle);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}
