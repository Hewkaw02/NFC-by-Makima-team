package com.team.makimainu.CustomListView;

public class DataModel {

    String name;
    String phone;
    String Key_Point;
//    String feature;

    public DataModel(String name, String phone, String Key_Point /*, String feature */) {
        this.name=name;
        this.phone=phone;
        this.Key_Point=Key_Point;
//        this.feature=feature;

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getKey_Point() {
        return Key_Point;
    }

//    public String getFeature() {
//        return feature;
//    }

}
