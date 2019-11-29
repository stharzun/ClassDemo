package com.stharzun.demoapp;

public class User {
    String name, address, contact;
    int age;

    public User() {
    }

   /* public User(String n, String a, String c, int ag) {
        this.name = n;
        this.address = a;
        this.contact = c;
        this.age = ag;
    }
*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
