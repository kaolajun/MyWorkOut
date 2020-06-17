package com.nwpu.yanjin.myworkout.Widget;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nwpu.yanjin.myworkout.Database.Weight;
import com.nwpu.yanjin.myworkout.MainActivity;
import com.nwpu.yanjin.myworkout.R;
import com.nwpu.yanjin.myworkout.ViewModel.WeightViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MyInputTargetWeightDialog extends Dialog {
    private Button btn_sure,btn_cancle;
    private EditText et_weightInput;
    private TextView tv_title;
    private WeightViewModel weightViewModel;
    public MyInputTargetWeightDialog(@NonNull Context context,WeightViewModel weightViewModel) {
        super(context);
        this.weightViewModel = weightViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_inputweight);

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
        tv_title = findViewById(R.id.textView_inputweight);
        tv_title.setText("请输入目标体重");
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //控件text判空方法
                String text = et_weightInput.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    SharedPreferences.Editor editor = MainActivity.targetWeightSharedPreferences.edit();
                    editor.putFloat("targetWeight",Double.valueOf(text).floatValue());
                    editor.commit();
                    //取出又放回，只是为了调用onchange方法，更新linechart视图
                    Weight weight = weightViewModel.getAllWeightLive().getValue().get(0);
                    weightViewModel.updateWeight(weight);
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
