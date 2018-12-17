package utils;

public class Logger {

    public static void success(String content) {
        System.out.println(content + " ...OK");
    }

    public static void info(String content) {
        System.out.println("INFO : " + content);
    }

    public static void error(String content) {
        System.out.println("ERROR : " + content);
    }

}