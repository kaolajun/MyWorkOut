package com.nwpu.yanjin.myworkout.ViewModel;

import android.app.Application;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.Database.ActionWithDate;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ActionWithDateViewModel extends AndroidViewModel {
    private ActionWithDateRepository actionWithDateRepository;

    public ActionWithDateViewModel(@NonNull Application application) {
        super(application);
        actionWithDateRepository = new ActionWithDateRepository(application);
    }

    public void insertActionWithDates(ActionWithDate... actionWithDates){
        actionWithDateRepository.insertActionWithDate(actionWithDates);
    }
    public void updateActionWithDates(ActionWithDate... actionWithDates){
        actionWithDateRepository.updateActionWithDates(actionWithDates);
    }
    public void deleteActionWithDates(ActionWithDate... actionWithDates){
        actionWithDateRepository.insertActionWithDate(actionWithDates);
    }

    public LiveData<List<ActionWithDate>> getAllActionWithDatesLive(){
        return actionWithDateRepository.getAllActionWithDatesLive();
    }
}
