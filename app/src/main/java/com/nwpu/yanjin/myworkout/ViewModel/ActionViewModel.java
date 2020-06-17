package com.nwpu.yanjin.myworkout.ViewModel;

import android.app.Application;

import com.nwpu.yanjin.myworkout.Database.Action;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ActionViewModel extends AndroidViewModel {

    private ActionRepository actionRepository;
    public ActionViewModel(@NonNull Application application) {
        super(application);
        actionRepository = new ActionRepository(application);
    }

    public void insertActions(Action... actions){
        actionRepository.insertActions(actions);
    }

    public void updateActions(Action... actions){
        actionRepository.updateActions(actions);
    }

    public void deleteActions(Action... actions){
        actionRepository.deleteActions(actions);
    }

    public LiveData<List<Action>> getAllActionsLive(){
        return actionRepository.getAllActionsLive();
    }

}
