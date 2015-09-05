package org.flycraft.vkontakteapi.auth;

public class AccessData {

    private final String accessToken;
    private final String expiresIn;
    private final String userId;

    public AccessData(String accessToken, String expiresIn, String userId) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getUserId() {
        return userId;
    }
}
