package com.example.ylq17.myapplication.Modle;

/**
 * Created by ylq17 on 2016/4/11.
 */
public class DemoItem {
    Class<?> className;
    String strName;

    public DemoItem(Class<?> className,
            String strName) {
        this.className = className;
        this.strName = strName;
    }
    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }
}
