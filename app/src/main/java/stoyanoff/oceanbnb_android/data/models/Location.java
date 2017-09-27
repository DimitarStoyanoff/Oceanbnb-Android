package stoyanoff.oceanbnb_android.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by L on 23/09/2017.
 */

public class Location implements Serializable {

    @SerializedName("LocationId")
    private int locationId;
    @SerializedName("LocationName")
    private String locationName;
    @SerializedName("Lattitude")
    private double latitude;
    @SerializedName("Longitude")
    private double longitude;
    @SerializedName("IsDeleted")
    private boolean isDeleted;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
