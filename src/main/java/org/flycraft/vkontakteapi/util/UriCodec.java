package org.flycraft.vkontakteapi.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class UriCodec {

    public static String decode(String s, boolean convertPlus, Charset charset, boolean throwOnFailure) {
        if (s.indexOf('%') == -1 && (!convertPlus || s.indexOf('+') == -1)) {
            return s;
        }
        StringBuilder result = new StringBuilder(s.length());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (c == '%') {
                do {
                    int d1, d2;
                    if (i + 2 < s.length()
                            && (d1 = hexToInt(s.charAt(i + 1))) != -1
                            && (d2 = hexToInt(s.charAt(i + 2))) != -1) {
                        out.write((byte) ((d1 << 4) + d2));
                    } else if (throwOnFailure) {
                        throw new IllegalArgumentException("Invalid % sequence at " + i + ": " + s);
                    } else {
                        byte[] replacement = "\ufffd".getBytes(charset);
                        out.write(replacement, 0, replacement.length);
                    }
                    i += 3;
                } while (i < s.length() && s.charAt(i) == '%');
                result.append(new String(out.toByteArray(), charset));
                out.reset();
            } else {
                if (convertPlus && c == '+') {
                    c = ' ';
                }
                result.append(c);
                i++;
            }
        }
        return result.toString();
    }

    /**
     * Like {@link Character#digit}, but without support for non-ASCII
     * characters.
     */
    private static int hexToInt(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        } else if ('a' <= c && c <= 'f') {
            return 10 + (c - 'a');
        } else if ('A' <= c && c <= 'F') {
            return 10 + (c - 'A');
        } else {
            return -1;
        }
    }

}
