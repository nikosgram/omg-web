package me.nikosgram.omg.web;

public class ResponseData {
    private final String response;
    private final long delay;
    private final int responseCode;
    private final String responseMessage;

    protected ResponseData(String response, long delay, int responseCode, String responseMessage) {
        this.response = response;
        this.delay = delay;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponse() {
        return response;
    }

    public long getDelay() {
        return delay;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseData)) return false;

        ResponseData that = (ResponseData) o;

        if (getDelay() != that.getDelay()) return false;
        if (getResponseCode() != that.getResponseCode()) return false;
        if (!getResponse().equals(that.getResponse())) return false;
        return getResponseMessage().equals(that.getResponseMessage());
    }

    @Override
    public int hashCode() {
        int result = getResponse().hashCode();
        result = 31 * result + (int) (getDelay() ^ (getDelay() >>> 32));
        result = 31 * result + getResponseCode();
        result = 31 * result + getResponseMessage().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "response='" + response + '\'' +
                ", delay=" + delay +
                ", responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
