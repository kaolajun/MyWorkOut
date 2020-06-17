package com.nwpu.yanjin.myworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences targetWeightSharedPreferences;

    public static SharedPreferences nightModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        NavController navController = Navigation.findNavController(this,R.id.fragment2);

        targetWeightSharedPreferences = getSharedPreferences("weight",MODE_PRIVATE);

        nightModel = getSharedPreferences("NightModel", Context.MODE_PRIVATE); //实例化数据库
        //判断是否为黑暗模式
        if (nightModel.contains("isNightModel") && nightModel.getBoolean("isNightModel",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        //底部工具条配置
//        AppBarConfiguration configuration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();  //右上角没有返回箭头

        //把bottomview添加到navigation
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        //在Activity中创建controller,用NavigationUI获得返回按键的逻辑
//        NavigationUI.setupActionBarWithNavController(this,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller = Navigation.findNavController(this,R.id.fragment2);
        return controller.navigateUp();
//        return super.onSupportNavigateUp();
    }
}
