package Deperecated;

import org.json.simple.JSONObject;

public class DataHolder {

    private JSONObject currentOpen;
    private JSONObject currentHigh;
    private JSONObject currentLow;
    private JSONObject currentClose;
    private JSONObject currentVolume;

    public DataHolder() {
        currentOpen = new JSONObject();
        currentHigh = new JSONObject();
        currentLow = new JSONObject();
        currentClose = new JSONObject();
        currentVolume = new JSONObject();
    }

    public JSONObject getCurrentOpen() {
        return currentOpen;
    }

    public void setCurrentOpen(String timeStamp, String value) {
        currentOpen.put(timeStamp,value);
    }

    public JSONObject getCurrentHigh() {
        return currentHigh;
    }

    public void setCurrentHigh(JSONObject currentHigh) {
        this.currentHigh = currentHigh;
    }

    public JSONObject getCurrentLow() {
        return currentLow;
    }

    public void setCurrentLow(JSONObject currentLow) {
        this.currentLow = currentLow;
    }

    public JSONObject getCurrentClose() {
        return currentClose;
    }

    public void setCurrentClose(JSONObject currentClose) {
        this.currentClose = currentClose;
    }

    public JSONObject getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(JSONObject currentVolume) {
        this.currentVolume = currentVolume;
    }

}
