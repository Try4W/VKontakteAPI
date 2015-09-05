package org.flycraft.vkontakteapi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {

    public static String invokeHttp(String request) throws IOException {
        URL url = new URL(request);
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return reader.readLine();
    }

}
