package com.nwpu.yanjin.myworkout.ViewModel;

import android.app.Application;

import com.nwpu.yanjin.myworkout.Database.Weight;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WeightViewModel extends AndroidViewModel {
    private WeightRepository weightRepository;


    public WeightViewModel(@NonNull Application application) {
        super(application);
        weightRepository = new WeightRepository(application);
    }
    public void insertWeight(Weight... weights){
        weightRepository.insertWeight(weights);
    }
    public void updateWeight(Weight... weights){
        weightRepository.updateWeight(weights);
    }
    public void deleteWeight(Weight... weights){
        weightRepository.deleteWeight(weights);
    }
    public LiveData<List<Weight>> getAllWeightLive(){
        return weightRepository.getAllWeightLive();
    }
}
