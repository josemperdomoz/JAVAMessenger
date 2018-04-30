
package javachat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Usuario
 */
public class MessageLoader {
    
    private final String address = "https://rest.rhyv.space";
    /*
    public ArrayList<Message> loadMessages(String from, String to){
        
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date date = null;
        Date date2 = null;
        Date date3 = null;
        Date date4 = null;
        
        try {
            date = sdf.parse("2018-04-25 06:09:29");
            date2 = sdf.parse("2018-04-25 06:09:29");
            date3 = sdf.parse("2018-04-25 06:09:29");
            date4 = sdf.parse("2018-04-25 06:09:29");
            
            
        } catch (ParseException ex) {
            Logger.getLogger(MessageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ArrayList<Message> messages = new ArrayList();
        messages.add(new Message("Markku","Hello", date4));
        messages.add(new Message("Me","Hello", date2));
        messages.add(new Message("Markku","Hello", date3));
        messages.add(new Message("Me","Hello", date));
        return messages;
    } 
*/ 
    /*
     public ArrayList<Message> loadMessages(String from, String to){
         ArrayList<Message> messages = new ArrayList();
         SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
         String result ="";
         
         // Implement GET API request 
         
         try {
            URL url = new URL( address + "/getmessages.php?from=" + from + "&to=" + to);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn = ( HttpURLConnection ) conn; // we cast it as HTTP connection
            httpConn.setAllowUserInteraction( false );
            httpConn.setInstanceFollowRedirects( true ); // useful if we want to migrate a server   
            httpConn.setRequestMethod( "GET" ); // sometimes it might not work this setRequestMethod
            //httpConn.setRequestMethod(to);
            httpConn.connect();

            InputStream is = httpConn.getInputStream(); // get data
            
            if ( is == null )
                return null;
             result = inputStreamToString(is);
             System.out.println("Result: " +  result);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
         return messages;

     }
     */
     
      public ArrayList<Message> loadMessages(String name, String from, String to) {
          String res = loadFromWeb(from, to);
          ArrayList<Message> messages = new ArrayList(); // you allocate memory for messages 
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          try{
              JSONArray json = new JSONArray(res);
              for(int i = 0; i < json.length(); i++){
                  String message = json.getJSONObject(i).getString("message");
                  Date date = sdf.parse(json.getJSONObject(i).getString("time"));
                  messages.add( new Message(message, name, date));
              }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException ex) { 
            Logger.getLogger(MessageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
          return messages;
      }
      
      public String loadFromWeb(String from, String to){
         String result ="";
         
         // Implement GET API request 
         
         try {
            URL url = new URL( address + "/getmessages.php?from=" + from + "&to=" + to);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn = ( HttpURLConnection ) conn; // we cast it as HTTP connection
            httpConn.setAllowUserInteraction( false );
            httpConn.setInstanceFollowRedirects( true ); // useful if we want to migrate a server   
            httpConn.setRequestMethod( "GET" ); // sometimes it might not work this setRequestMethod
            //httpConn.setRequestMethod(to);
            httpConn.connect();

            InputStream is = httpConn.getInputStream(); // get data
            
            if ( is == null )
                return null;
             result = inputStreamToString(is);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
         return result;

     }

    private String inputStreamToString(InputStream is){
        String result = "";
        StringBuilder str = new StringBuilder(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
         try {
             result = reader.readLine();
             while(result != null){
                 str.append(result);
                 result = reader.readLine();
             }
                 
         } catch (IOException ex) {
             Logger.getLogger(MessageLoader.class.getName()).log(Level.SEVERE, null, ex);
         }
         return str.toString();
    }
    
    
    public boolean sendMessage(String from, String to, String message){
                try {
            String urlParameters  = "from=" + from + "&to=" +  to +  "&message=" + message;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );            
            URL url = new URL( address + "/add.php");
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn = ( HttpURLConnection ) conn;
            httpConn.setDoOutput( true );
            httpConn.setAllowUserInteraction( false );
            httpConn.setInstanceFollowRedirects( true ); // If redirection is done good , it is forwarded on the server
            httpConn.setRequestMethod( "POST" );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
               wr.write( postData );
            }            

            InputStream is = httpConn.getInputStream();
            if ( is == null )
                return false;
        } catch( Exception e ) {
            e.printStackTrace();
        }
         return true;                
    }
    
    
    
    
        
}

