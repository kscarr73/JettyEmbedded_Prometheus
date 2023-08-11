package com.progbits.jetty.reference.rest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author scarr
 */
public class HttpHelper {

    @SuppressWarnings("java:S3740")
    public static String getResourceAsString(Class loader, String resourcePath) {
        String retString = null;

        try (InputStream is = loader.getResourceAsStream(resourcePath)) {
            if (is != null) {
                try (Scanner lclScanner = new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A")) {
                    retString = lclScanner.next();
                }
            }
        } catch (IOException ex) {
            // Nothing to do here
        }

        return retString;
    }

}
