package stoyanoff.oceanbnb_android.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by L on 23/09/2017.
 */

public class CruiseUser implements Serializable {

    @SerializedName("UsersToCruisesId")
    private int usersToCruisesId;
    @SerializedName("UserId")
    private int userId;
    @SerializedName("UserName")
    private String userName;
    @SerializedName("Email")
    private String email;
    @SerializedName("Gender")
    private String gender;
    @SerializedName("City")
    private String city;
    @SerializedName("Description")
    private String description;
    @SerializedName("ProfilePhoto")
    private String profilePhoto;
    @SerializedName("IsDeleted")
    private boolean isDeleted;
    @SerializedName("DateInserted")
    private Date dateInserted;
    @SerializedName("DateModified")
    private Date dateModified;

    public int getUsersToCruisesId() {
        return usersToCruisesId;
    }

    public void setUsersToCruisesId(int usersToCruisesId) {
        this.usersToCruisesId = usersToCruisesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
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
