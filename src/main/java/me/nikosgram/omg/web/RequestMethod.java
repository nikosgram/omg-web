package me.nikosgram.omg.web;

public enum RequestMethod {
    GET("GET"),
    POST("POST");

    private final String type;

    RequestMethod(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
