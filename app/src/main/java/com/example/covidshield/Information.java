package com.example.covidshield;

public class Information {
    private String address;
    private String bed;
    private String city;
    private String contact_no;
    private String fullname;
    private String id;
    private String injection;
    private String oxygen;
    private String username;

    public Information() {
    }

    public Information(String address, String bed, String city, String contact_no, String fullname, String id, String injection, String oxygen, String username) {
        this.address = address;
        this.bed = bed;
        this.city = city;
        this.contact_no = contact_no;
        this.fullname = fullname;
        this.id = id;
        this.injection = injection;
        this.oxygen = oxygen;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInjection() {
        return injection;
    }

    public void setInjection(String injection) {
        this.injection = injection;
    }

    public String getOxygen() {
        return oxygen;
    }

    public void setOxygen(String oxygen) {
        this.oxygen = oxygen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
