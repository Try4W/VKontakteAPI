package org.flycraft.vkontakteapi;

import com.google.common.util.concurrent.RateLimiter;
import org.flycraft.vkontakteapi.util.HttpUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class VkApi {

    private static final String API_REQUEST = "https://api.vk.com/method/{METHOD}?{PARAMS}&access_token={TOKEN}&v=5.21";

    private static final Logger logger = LoggerFactory.getLogger(VkApi.class);

    private final RateLimiter requestLimiter = RateLimiter.create(2.0);

    private final String accessToken;
    private final String version;

    public VkApi(String accessToken, Double version) {
        this.accessToken = accessToken;
        this.version = String.valueOf(version);
        if (accessToken == null || accessToken.isEmpty()) {
            throw new Error("Need access token");
        }
    }

    public VkApi(String accessToken) {
        this(accessToken, null);
    }

    public JSONObject invoke(VkMethod vkMethod) throws IOException {
        requestLimiter.acquire();
        return invoke(vkMethod, accessToken, version);
    }

    public static JSONObject invoke(VkMethod vkMethod, String version) throws IOException {
        return invoke(vkMethod, null, version);
    }

    private static JSONObject invoke(VkMethod vkMethod, String accessToken, String version) throws IOException {
        return invokeUri(vkMethod.getRequestUri(accessToken, version));
    }

    private static JSONObject invokeUri(String requestUri) throws IOException {
        logger.debug("Invoking url: " + requestUri);
        if(!requestUri.contains("&v=")) logger.warn("Invoking method without api version argument. It is not safe!");
        try {
            String jsonResponse = HttpUtils.invokeHttp(requestUri);
            logger.debug("Json response: " + jsonResponse);
            if(jsonResponse.contains("\"error_msg\":\"Too many requests per second\"")) {
                logger.error("Vk api returns error: " + jsonResponse);
            }
            return new JSONObject(jsonResponse);
        } catch (JSONException e) {
            logger.error("Can't read json response");
            e.printStackTrace();
        }
        return null;
    }

}