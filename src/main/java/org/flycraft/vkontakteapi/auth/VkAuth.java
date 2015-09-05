package org.flycraft.vkontakteapi.auth;

public class VkAuth {

    private final long clientId;
    private String scope;

    private AccessData accessData;

    private static final String AUTH_URL = "https://oauth.vk.com/authorize?client_id={APP_ID}&scope={PERMISSIONS}&redirect_uri={REDIRECT_URI}&display={DISPLAY}&response_type={RESPONSE_TYPE}&v={API_VERSION}";

    public VkAuth(long clientId, String scope) {
        this.clientId = clientId;
        this.scope = scope;
    }

    public String getAuthUri() {
        return AUTH_URL
                .replace("{APP_ID}", String.valueOf(clientId))
                .replace("{PERMISSIONS}", "photos,messages")
                .replace("{REDIRECT_URI}", "https://oauth.vk.com/blank.html")
                .replace("{DISPLAY}", "page")
                .replace("{RESPONSE_TYPE}", "token")
                .replace("{API_VERSION}", "5.37");
    }

    public void generateAccessData(String uriAnswer) {
        String requestResult = uriAnswer.substring(uriAnswer.indexOf("#") + 1);
        String[] arguments = requestResult.split("&");
        String accessToken = arguments[0].replace("access_token=", "");
        String expiresIn = arguments[1].replace("expires_in=", "");
        String userId = arguments[2].replace("user_id=", "");
        accessData = new AccessData(accessToken, expiresIn, userId);
    }

    public AccessData getAccessData() {
        if(accessData == null) throw new RuntimeException("First you need to generateAccessData");
        return accessData;
    }

}
