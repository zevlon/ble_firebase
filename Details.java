package com.example.zevlon.myapplication;

public class Details {
    String id;
    String name;
    String mother_name;
    String dob;
    String age;
    String gender;

public Details(){

}

    public Details(String id, String name, String mother_name, String dob, String age, String gender) {
        this.id = id;
        this.name = name;
        this.mother_name = mother_name;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public String getDob() {
        return dob;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
