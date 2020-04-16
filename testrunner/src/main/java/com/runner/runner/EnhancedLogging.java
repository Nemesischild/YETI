package com.runner.runner;

public class EnhancedLogging {

    private EnhancedLogging() {
    }

    private String message;

    private static boolean isDebug;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void debug(String message) {


       setDebug(java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0);


        if (isDebug) {

            System.out.println("DEBUG: " + message);
        }
    }

    public static void testlog(String message) {

        EnhancedAssertion.addlogInfo(true, message.replace("\n", "<br />"), false, true);

    }

    public static void logOutput (String sMessage){
        System.out.println(sMessage);

    }

}
