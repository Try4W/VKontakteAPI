package org.flycraft.vkontakteapi;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class VkMethod {

    private static final String API_REQUEST = "https://api.vk.com/method/{METHOD}?{PARAMS}&access_token={TOKEN}&v={VERSION}";

    private final String methodName;
    private final HashMap<String, String> arguments;

    public VkMethod(String methodName, HashMap<String, String> arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public VkMethod(String methodName) {
        this(methodName, new HashMap<>());
    }

    public void addArgument(String key, String value) {
        arguments.put(key, value);
    }

    public String getRequestUri(String accessToken, String version) {
        String request = API_REQUEST
                .replace("{METHOD}", methodName)
                .replace("{PARAMS}", buildArguments());
        if(Strings.isNullOrEmpty(accessToken)) {
            request = request.replace("&access_token={TOKEN}", "");
        } else {
            request = request.replace("{TOKEN}", accessToken);
        }
        if(Strings.isNullOrEmpty(version)) {
            request = request.replace("&v={VERSION}", "");
        } else {
            request = request.replace("{VERSION}", version);
        }
        return request;
    }

    private String buildArguments() {
        if (arguments.isEmpty()) return "";
        final StringBuilder argumentsBuild = new StringBuilder();
        arguments.keySet().stream().forEach(key -> {
            argumentsBuild.append(key).append('=').append(arguments.get(key)).append('&');
        });
        return StringUtils.chop(argumentsBuild.toString());
    }

}
