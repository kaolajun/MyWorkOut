package com.nwpu.yanjin.myworkout.Widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.Database.Weight;
import com.nwpu.yanjin.myworkout.ViewModel.WeightViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

public class MyInputWeightDialog extends Dialog {
    private Button btn_sure,btn_cancle;
    private EditText et_weightInput;
    private WeightViewModel weightViewModel;

    public MyInputWeightDialog(@NonNull Context context,WeightViewModel weightViewModel) {
        super(context);
        this.weightViewModel = weightViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_inputweight);
        final SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

        //设置宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int) (size.x * 0.8);       //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);

        btn_sure = findViewById(R.id.btn_inputweight_sure);
        btn_cancle = findViewById(R.id.btn_inputweight_cancel);
        et_weightInput = findViewById(R.id.et_weight);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateFormat.format(new Date());

                //控件text判空方法
                String text = et_weightInput.getText().toString();
                if (!TextUtils.isEmpty(text)) {

                    Weight weight = new Weight(date,Double.parseDouble(text));
                    List<Weight> weights = weightViewModel.getAllWeightLive().getValue();
                    if (weights.size() == 0) {
                        weightViewModel.insertWeight(weight);
                    } else {
                        Log.i("date1", weights.get(0).getDate());
                        //数据为倒序，即最新的体重数据排第一个
                        if (weights.get(0).getDate().equals(date)) {
                            weightViewModel.updateWeight(weight);
                        } else {
                            weightViewModel.insertWeight(weight);
                        }
                    }
                    dismiss();
                }else{
                    Toast.makeText(getContext(),"诶还没输入呢~",Toast.LENGTH_SHORT).show();
                }



            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
