//import org.junit.Before;
//import org.junit.Test;
//import System.*;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class LogTest {
//
//
//    @Test
//    public void testWriteToLog() throws IOException {
//        Log.getInstance().writeToLog("!!!TEST!!!");
//        String lastLine = "";
//        String sCurrentLine ="";
//        BufferedReader br=null;
//        try {
//            String path = System.getProperty("user.dir") + '/' + "logs/Football_Association_System_Log.log";
//            br = new BufferedReader(new FileReader(path));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        while ((sCurrentLine = br.readLine()) != null)
//        {
//            lastLine = sCurrentLine;
//        }
//        assertEquals(lastLine.substring(lastLine.length()-10),"!!!TEST!!!");
//
//    }
//}
