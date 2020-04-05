package System;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    //Implements a singleton logger instance
    private static final Log instance = new Log();

    //Retrieve the execution directory. Note that this is whereever this process was launched
    public String logname = "Football_Association_System_Log";
    protected String env = System.getProperty("user.dir");
    private static File logFile;
    public static Log getInstance(){
        return instance;
    }



    public void createLogFile(){
        //Determine if a logs directory exists or not.
        File logsFolder = new File(env + '/' + "logs");
        if(!logsFolder.exists()){
            //Create the directory
            System.err.println("INFO: Creating new logs directory in " + env);
            logsFolder.mkdir();
        }
        //Get the current date and time
        Calendar cal = Calendar.getInstance();

        //Create the name of the file from the path and current time
        logname =  logname + ".log";
        Log.logFile = new File(logsFolder.getName(),logname);
        try{
            if(logFile.createNewFile()){
                //New file made
                System.err.println("INFO: Creating new log file");
            }
        }catch(IOException e){
            System.err.println("ERROR: Cannot create log file");
            System.exit(1);
        }
    }
    private Log(){
        if (instance != null){
            //Prevent Reflection
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
        }
        this.createLogFile();
    }

    public static void writeToLog(String message){
        try{
            FileWriter out = new FileWriter(Log.logFile, true);
            Date date =new Date();
            date.toString();
            String callerClassName = new Exception().getStackTrace()[1].getClassName().substring(7);

            message = date +" "+callerClassName+": "+message+"\n";
            out.write(message.toCharArray());
            System.err.println(message);

            out.close();
        }catch(IOException e){
            System.err.println("ERROR: Could not write to log file");
        }
    }
}