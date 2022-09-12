package com.automation;

import java.util.Locale;

public class RunnerUtils {
    private static String OS = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return OS.contains("nux");
    }

    public static String getChromeDriver() {
        String path = "";

        if (isWindows()) {

            path = "chromedriver.exe";
        }else{
            path="chromedriver";
        }

        return path;
    }
}
