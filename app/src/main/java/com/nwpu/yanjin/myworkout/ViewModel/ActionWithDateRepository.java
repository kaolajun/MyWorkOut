package com.nwpu.yanjin.myworkout.ViewModel;

import android.content.Context;
import android.os.AsyncTask;

import com.nwpu.yanjin.myworkout.Database.ActionWithDate;
import com.nwpu.yanjin.myworkout.Database.ActionWithDateDao;
import com.nwpu.yanjin.myworkout.Database.ActionWithDateDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ActionWithDateRepository {
    private LiveData<List<ActionWithDate>> allActionWithDatesLive;
    private ActionWithDateDao actionWithDateDao;

    public ActionWithDateRepository(Context context) {
        ActionWithDateDatabase actionWithDateDatabase = ActionWithDateDatabase.getInstance(context);
        actionWithDateDao = actionWithDateDatabase.getActionWithDateDao();
        allActionWithDatesLive = actionWithDateDao.getAllActionWithDates();
    }

    LiveData<List<ActionWithDate>> getAllActionWithDatesLive(){
        return allActionWithDatesLive;
    }

    void insertActionWithDate(ActionWithDate... actionWithDates){
        new insertActionWithDates(actionWithDateDao).execute(actionWithDates);
    }
    void updateActionWithDates(ActionWithDate... actionWithDates){
        new UpdateActionWithDates(actionWithDateDao).execute(actionWithDates);
    }
    void deleteActionWithDates(ActionWithDate... actionWithDates){
        new DeleteActionWithDates(actionWithDateDao).execute(actionWithDates);
    }

    static class insertActionWithDates extends AsyncTask<ActionWithDate,Void,Void>{
        private ActionWithDateDao actionWithDateDao;

        public insertActionWithDates(ActionWithDateDao actionWithDateDao) {
            this.actionWithDateDao = actionWithDateDao;
        }

        @Override
        protected Void doInBackground(ActionWithDate... actionWithDates) {
            actionWithDateDao.insertActionWithDates(actionWithDates);
            return null;
        }
    }

    static class UpdateActionWithDates extends AsyncTask<ActionWithDate,Void,Void>{
        private ActionWithDateDao actionWithDateDao;

        public UpdateActionWithDates(ActionWithDateDao actionWithDateDao) {
            this.actionWithDateDao = actionWithDateDao;
        }

        @Override
        protected Void doInBackground(ActionWithDate... actionWithDates) {
            actionWithDateDao.updateActionWithDates(actionWithDates);
            return null;
        }
    }

    static class DeleteActionWithDates extends AsyncTask<ActionWithDate,Void,Void>{
        private ActionWithDateDao actionWithDateDao;

        public DeleteActionWithDates(ActionWithDateDao actionWithDateDao) {
            this.actionWithDateDao = actionWithDateDao;
        }

        @Override
        protected Void doInBackground(ActionWithDate... actionWithDates) {
            actionWithDateDao.deleteActionWithDates(actionWithDates);
            return null;
        }
    }
}
