package stoyanoff.oceanbnb_android.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by L on 23/09/2017.
 */

public class Ship implements Serializable {

    @SerializedName("ShipId")
    private int shipId;
    @SerializedName("ShipName")
    private String shipName;
    @SerializedName("YearBuilt")
    private int yearBuilt;
    @SerializedName("PassengerCapacity")
    private int passengerCapacity;
    @SerializedName("CrewCount")
    private int crewCount;
    @SerializedName("WeightInTons")
    private int weightInTons;
    @SerializedName("LengthInMeters")
    private int lengthInMeters;
    @SerializedName("BeamInMeters")
    private int beamInMeters;
    @SerializedName("IsDeleted")
    private boolean isDeleted;

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getCrewCount() {
        return crewCount;
    }

    public void setCrewCount(int crewCount) {
        this.crewCount = crewCount;
    }

    public int getWeightInTons() {
        return weightInTons;
    }

    public void setWeightInTons(int weightInTons) {
        this.weightInTons = weightInTons;
    }

    public int getLengthInMeters() {
        return lengthInMeters;
    }

    public void setLengthInMeters(int lengthInMeters) {
        this.lengthInMeters = lengthInMeters;
    }

    public int getBeamInMeters() {
        return beamInMeters;
    }

    public void setBeamInMeters(int beamInMeters) {
        this.beamInMeters = beamInMeters;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
