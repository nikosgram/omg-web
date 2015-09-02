package me.nikosgram.omg.web;

public class ResponseBuilder {
    private String response = null;
    private long delay = -1;
    private int responseCode = -1;
    private String responseMethod = null;

    private ResponseBuilder() {}

    public static ResponseBuilder create() {
        return new ResponseBuilder();
    }

    public ResponseBuilder response(String response) {
        this.response = response;
        return this;
    }

    public ResponseBuilder delay(long delay) {
        this.delay = delay;
        return this;
    }

    public ResponseBuilder responseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public ResponseBuilder responseMethod(String responseMethod) {
        this.responseMethod = responseMethod;
        return this;
    }

    public String response() {
        return response;
    }

    public long delay() {
        return delay;
    }

    public int responseCode() {
        return responseCode;
    }

    public String responseMethod() {
        return responseMethod;
    }

    public ResponseData build() {
        return new ResponseData(response, delay, responseCode, responseMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseBuilder)) return false;

        ResponseBuilder that = (ResponseBuilder) o;

        if (delay != that.delay) return false;
        if (responseCode != that.responseCode) return false;
        if (!response.equals(that.response)) return false;
        return responseMethod.equals(that.responseMethod);
    }

    @Override
    public int hashCode() {
        int result = response.hashCode();
        result = 31 * result + (int) (delay ^ (delay >>> 32));
        result = 31 * result + responseCode;
        result = 31 * result + responseMethod.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResponseBuilder{" +
                "response='" + response + '\'' +
                ", delay=" + delay +
                ", responseCode=" + responseCode +
                ", responseMethod='" + responseMethod + '\'' +
                '}';
    }
}
