package me.uquark.barrymore;

public class Logger {
    public static void log(String text) {
        System.out.println(text);
    }

    public static void info(String text) {
        log("[INFO] " + text);
    }

    public static void warning(String text) {
        log("[WARNING] " + text);
    }

    public static void error(String text) {
        log("[ERROR] " + text);
    }
}
