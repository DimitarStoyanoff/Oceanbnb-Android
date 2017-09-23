package stoyanoff.oceanbnb_android.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by L on 23/09/2017.
 */

public class Cruise implements Serializable {

    @SerializedName("CruiseId")
    private int cruiseId;
    @SerializedName("CruiseName")
    private String cruiseName;
    @SerializedName("ShipId")
    private int shipId;
    @SerializedName("IsDeleted")
    private boolean isDeleted;
    @SerializedName("LocationsList")
    private List<Location> locationList;

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
}
