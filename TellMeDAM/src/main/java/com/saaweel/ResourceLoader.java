package com.saaweel;

import java.net.URL;

/**
 * Utility class which manages the access to this project's assets.
 * Helps keeping the assets files structure organized.
 */
public class ResourceLoader {

    private ResourceLoader() {
    }

    public static URL loadURL(String path) {
        return ResourceLoader.class.getResource(path);
    }

    public static String load(String path) {
        return loadURL(path).toString();
    }
}