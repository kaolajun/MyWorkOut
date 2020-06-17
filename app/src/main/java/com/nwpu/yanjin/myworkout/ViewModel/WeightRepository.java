package com.nwpu.yanjin.myworkout.ViewModel;

import android.content.Context;
import android.os.AsyncTask;

import com.nwpu.yanjin.myworkout.Database.Weight;
import com.nwpu.yanjin.myworkout.Database.WeightDao;
import com.nwpu.yanjin.myworkout.Database.WeightDataBase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WeightRepository {
    private LiveData<List<Weight>> allWeightLive;
    private WeightDao weightDao;

    public WeightRepository(Context context) {
        WeightDataBase weightDataBase = WeightDataBase.getInstance(context.getApplicationContext());
        weightDao = weightDataBase.getWeightDao();
        allWeightLive = weightDao.getAllWeightLive();
    }

    LiveData<List<Weight>> getAllWeightLive() {
        return allWeightLive;
    }

    void insertWeight(Weight... weights){
        new InsertWeight(weightDao).execute(weights);
    }
    void updateWeight(Weight... weights){
        new UpdateWeight(weightDao).execute(weights);
    }
    void deleteWeight(Weight... weights){
        new DeleteWeight(weightDao).execute(weights);
    }


    static class InsertWeight extends AsyncTask<Weight,Void,Void>{
        private WeightDao weightDao;

        InsertWeight(WeightDao weightDao) {
            this.weightDao = weightDao;
        }

        @Override
        protected Void doInBackground(Weight... weights) {
            weightDao.InsertWeights(weights);
            return null;
        }
    }

    static class UpdateWeight extends AsyncTask<Weight,Void,Void>{
        private WeightDao weightDao;

        UpdateWeight(WeightDao weightDao) {
            this.weightDao = weightDao;
        }

        @Override
        protected Void doInBackground(Weight... weights) {
            weightDao.UpdateWeights(weights);
            return null;
        }
    }

    static class DeleteWeight extends AsyncTask<Weight,Void,Void>{
        private WeightDao weightDao;

        DeleteWeight(WeightDao weightDao) {
            this.weightDao = weightDao;
        }

        @Override
        protected Void doInBackground(Weight... weights) {
            weightDao.DeleteWeights(weights);
            return null;
        }
    }
}
