package com.nwpu.yanjin.myworkout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import lecho.lib.hellocharts.formatter.AxisValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleLineChartValueFormatter;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.nwpu.yanjin.myworkout.Adapters.UserAdapter;
import com.nwpu.yanjin.myworkout.Database.Weight;
import com.nwpu.yanjin.myworkout.ViewModel.WeightViewModel;
import com.nwpu.yanjin.myworkout.Widget.*;

import static com.nwpu.yanjin.myworkout.MainActivity.nightModel;

public class UserFragment extends Fragment {

    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private ImageView iv_portrait;
    private TextView tv_weight;
    private TextView tv_nightModel;
    private Switch sw_night;
    private WeightViewModel weightViewModel;
    private MyInputWeightDialog myInputWeightDialog;
    private LineChartView lcv_weight;
//    List<String> date = new ArrayList<>();//X轴的标注
//    List<Boolean> weight = new ArrayList<>();//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    private List<PointValue> mTargetPointValues = new ArrayList<PointValue>();
    //折线图的Y轴最小和最大值
    private List<PointValue> mMinPointValues = new ArrayList<PointValue>();
    private List<PointValue> mMaxPointValues = new ArrayList<PointValue>();

    private static double targetWeight = 0;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("display","hi1");
        weightViewModel = ViewModelProviders.of(getActivity()).get(WeightViewModel.class);
        userAdapter = new UserAdapter(weightViewModel);
        View view = inflater.inflate(R.layout.user_fragment,container,false);

        iv_portrait = view.findViewById(R.id.iv_portraint);
        tv_weight = view.findViewById(R.id.tv_weight);
        sw_night = view.findViewById(R.id.sw_night);
        tv_nightModel = view.findViewById(R.id.tv_introduce);

        tv_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInputWeightDialog = new MyInputWeightDialog(getActivity(),weightViewModel);
                myInputWeightDialog.show();
                myInputWeightDialog.setCanceledOnTouchOutside(false);
            }
        });
        iv_portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sw_night.setChecked(nightModel.getBoolean("isNightModel",false));
        if (sw_night.isChecked()){
            tv_nightModel.setText("夜间模式开启");
        }else {
            tv_nightModel.setText("夜间模式关闭");
        }
        sw_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SharedPreferences.Editor editor = nightModel.edit();
                    editor.putBoolean("isNightModel",true);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                }else {
                    SharedPreferences.Editor editor = nightModel.edit();
                    editor.putBoolean("isNightModel",false);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        lcv_weight = view.findViewById(R.id.lcv_weight);
        recyclerView = view.findViewById(R.id.recy_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(userAdapter);

        targetWeight = MainActivity.targetWeightSharedPreferences.getFloat("targetWeight",0);
//        //加载视图时加载改变的数据
//        tv_weight = view.findViewById(R.id.tv_weight);
//        List<Weight> weights = weightViewModel.getAllWeightLive().getValue();
//        if (weights != null && !weights.isEmpty()){
//            int i = weights.get(weights.size() - 1).getWeight();
//            tv_weight.setText("当前体重" + ":" + String.valueOf(i));
//        }else{
//            tv_weight.setText("开始设置你的体重吧");
//        }

        weightViewModel.getAllWeightLive().observe(getActivity(), new Observer<List<Weight>>() {
            @Override
            public void onChanged(List<Weight> weights) {
                targetWeight = MainActivity.targetWeightSharedPreferences.getFloat("targetWeight",0);
                Log.i("display","hi2");
                if (!weights.isEmpty()){
                    //第一个weight数据是最新的
                    double i = weights.get(0).getWeight();
                    Log.i("display","hi3");
                    tv_weight.setText("当前体重" + ":" + String.valueOf(i) + "kg");
                    Log.i("display","hi4");
                }else{
                    tv_weight.setText("开始设置你的体重吧");
                }

                //最大体重
                double maxWeight = 0;
                double minWeight = 200;
                for (Weight w : weights){
                    if (w.getWeight() > maxWeight){
                        maxWeight = w.getWeight();
                    }
                    if (w.getWeight() < minWeight){
                        minWeight = w.getWeight();
                    }
                }
                mMinPointValues.clear();
                mMinPointValues.add(new PointValue(0,(float) maxWeight + 10));
                if (targetWeight != 0) {
                    mMaxPointValues.clear();
                    mMaxPointValues.add(new PointValue(0, (float) targetWeight - 10));
                }else {
                    mMaxPointValues.clear();
                    mMaxPointValues.add(new PointValue(0,(float) minWeight - 10));
                }
                getAxisXLables(weights);//获取x轴的标注
                getAxisPoints(weights);//获取坐标点
                initLineChart();//初始化
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel

    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(List<Weight> weights) {
        mAxisXValues.clear();
        for (int i = 0;i < weights.size();i ++) {
            mAxisXValues.add(new AxisValue(weights.size()-1-i).setLabel(weights.get(i).getDate()));

        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(List<Weight> weights) {
        mPointValues.clear();
        mTargetPointValues.clear();
        for (int i = 0;i < weights.size();i ++) {
            //注意此处x不能为i，否则会倒序
            mPointValues.add(new PointValue(weights.size()-1-i,(float)weights.get(i).getWeight()));  //当前体重
            if (targetWeight != 0){
                mTargetPointValues.add(new PointValue(weights.size()-1-i,(float) targetWeight));   //目标体重
            }

            Log.i("float",String.valueOf((float)weights.get(i).getWeight()));
        }
    }

    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）

        Line lineTarget = new Line(mTargetPointValues).setColor(Color.parseColor("#22DDB8"));

        //只用一个点，只是用来设置上下限
        Line lineMin = new Line(mMinPointValues);
        Line lineMax = new Line(mMaxPointValues);

        lineMin.setHasPoints(false);
        lineMax.setHasPoints(false);

        lineTarget.setHasLabels(true);
        lineTarget.setHasPoints(false);

        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setFormatter(new SimpleLineChartValueFormatter(2));  //小数点后两位

        //一共有四条线：体重曲线，目标曲线，Y轴最大最小值
        lines.clear();
        lines.add(line);
        lines.add(lineTarget);
        lines.add(lineMin);
        lines.add(lineMax);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        axisX.setName("体重变化图");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("体重");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边

        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移


        lcv_weight.setInteractive(true);
        lcv_weight.setZoomType(ZoomType.HORIZONTAL);
        lcv_weight.setMaxZoom((float) 2);//最大放大比例
        lcv_weight.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lcv_weight.setLineChartData(data);
        lcv_weight.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lcv_weight.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lcv_weight.setCurrentViewport(v);
    }

}
