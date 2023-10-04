package com.streams.model;

public class Trader {
    public String getName() {
        return name;
    }

    private final String name;

    public String getCity() {
        return city;
    }

    private final String city;
    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
