package com.nwpu.yanjin.myworkout.ViewModel;

import android.content.Context;
import android.os.AsyncTask;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.Database.ActionDao;
import com.nwpu.yanjin.myworkout.Database.ActionDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ActionRepository {
    private LiveData<List<Action>> allActionsLive;
    private ActionDao actionDao;

    public ActionRepository(Context context) {
        ActionDatabase actionDatabase = ActionDatabase.getInstance(context);
        actionDao = actionDatabase.getActionDao();
        allActionsLive = actionDao.getAllActionsLive();
    }

    LiveData<List<Action>> getAllActionsLive(){
        return allActionsLive;
    }

    void insertActions(Action... actions){
        new InsertAsyncTask(actionDao).execute(actions);
    }

    void updateActions(Action... actions){
        new UpdateAsyncTask(actionDao).execute(actions);
    }

    void deleteActions(Action... actions){
        new DeleteAsyncTask(actionDao).execute(actions);
    }

    static class InsertAsyncTask extends AsyncTask<Action,Void,Void>{
        private ActionDao actionDao;

        public InsertAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Action... actions) {
            actionDao.InsertActions(actions);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Action,Void,Void>{
        private ActionDao actionDao;

        public UpdateAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Action... actions) {
            actionDao.UpdateActions(actions);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Action,Void,Void>{
        private ActionDao actionDao;

        public DeleteAsyncTask(ActionDao actionDao) {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Action... actions) {
            actionDao.DeleteActions(actions);
            return null;
        }
    }

}
