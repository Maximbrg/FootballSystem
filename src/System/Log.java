package System;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Log {
    //Implements a singleton logger instance
    private static final Log instance = new Log();

    //Retrieve the execution directory. Note that this is whereever this process was launched
    public String logName = "Football_Association_System_Log";
    protected String env = System.getProperty("user.dir");
    private static File logFile;


    public static Log getInstance(){
        return instance;
    }
    //<editor-fold desc="constructor">
    private Log(){
        if (instance != null){
            //Prevent Reflection
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
        }
        this.createLogFile();
    }
    //</editor-fold>
    //<editor-fold desc="methods">
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
        logName =  logName + ".log";
        Log.logFile = new File(logsFolder.getName(), logName);
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

    /**
     * write a string to the log file of the system
     * @param message to be write in the log file
     */
    public void writeToLog(String message){
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

    /**
     * present the log file as string
     * @return
     */
    public String getLog() throws IOException {
        String strLine="";
        String logToString="";
        try{
            String path = System.getProperty("user.dir") + '/' + "logs/Football_Association_System_Log.log";
            FileInputStream fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            /* read log line by line */
            strLine = br.readLine();
            while (strLine != null) {

                /* parse strLine to obtain what you want */

                logToString+= strLine;
                logToString+="\n";
                strLine = br.readLine();
            }
            fstream.close();

        } catch (Exception e) {

            System.err.println("Error: " + e.getMessage());
        }

        return logToString;
    } //UC-28
    //</editor-fold>
}