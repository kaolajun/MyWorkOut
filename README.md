# MyWorkOut
基于JetPack开发的一个记录每天健身过程的App，可以自己添加健身动作，并且可以记录每天的健身的动作和对应的时间。

## 下载APK
+ [百度云分享链接](https://pan.baidu.com/s/1z96q_PtgiK6eOJTXvx8xfw) 提取码：w21f
## 一、功能
### 1.新建自己的健身动作
### 2.存储当日的动作和运动时间
### 3.设置目标体重，并保存当日体重，在折线图中表示
### 4.黑暗模式
+ 基于AppCompatDelegate.setDefaultNightMode实现黑暗模式，用SharedPreference存储当前系统选择的模式。
```java
        nightModel = getSharedPreferences("NightModel", Context.MODE_PRIVATE); //实例化数据库
        //判断是否为黑暗模式
        if (nightModel.contains("isNightModel") && nightModel.getBoolean("isNightModel",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
```

## 二、未完成的功能
### 1.“我的”页面，头像功能
### 2.其他有想法的功能
+ 记录跑步的路径
+ 每日步数自动添加到日历中

## 三、使用ROOM操作数据库

## 四、使用ViewModel实现数据驱动


