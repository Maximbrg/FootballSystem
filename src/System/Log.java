package System;

public class Log {
    private static Log ourInstance = new Log();

    public static Log getInstance() {
        return ourInstance;
    }

    private Log() {
    }
}
