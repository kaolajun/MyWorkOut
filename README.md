# MyWorkOut
基于JetPack开发的一个记录每天健身过程的App，可以自己添加健身动作，并且可以记录每天的健身的动作和对应的时间。

## 黑暗模式
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

## 使用ROOM操作数据库

## 使用ViewModel实现数据驱动
