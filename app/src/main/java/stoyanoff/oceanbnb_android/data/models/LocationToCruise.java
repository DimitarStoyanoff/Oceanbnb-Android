package stoyanoff.oceanbnb_android.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by L on 23/09/2017.
 */

public class LocationToCruise implements Serializable {

    @SerializedName("LocationsToCruisesId")
    private int locationsToCruisesId;
    @SerializedName("LocationId")
    private int locationId;
    @SerializedName("LocationName")
    private String locationName;
    @SerializedName("Lattitude")
    private double lattitude;
    @SerializedName("Longitude")
    private double longitude;
    @SerializedName("CruiseId")
    private int cruiseId;
    @SerializedName("IsDeleted")
    private boolean isDeleted;
    @SerializedName("DateInserted")
    private Date dateInserted;
    @SerializedName("DateModified")
    private Date dateModified;

    public int getLocationsToCruisesId() {
        return locationsToCruisesId;
    }

    public void setLocationsToCruisesId(int locationsToCruisesId) {
        this.locationsToCruisesId = locationsToCruisesId;
    }

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

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
