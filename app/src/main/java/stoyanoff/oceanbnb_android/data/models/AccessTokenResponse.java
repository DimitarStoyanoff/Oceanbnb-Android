package stoyanoff.oceanbnb_android.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by L on 23/09/2017.
 */

public class AccessTokenResponse implements Serializable {

    @SerializedName("access_token")
    private String token;
    @SerializedName("token_type")
    private String type;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("userName")
    private String userName;
    @SerializedName("error_description")
    private String errorDescription;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
