/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Nasri
 */
public class SMS {
    public static final String ACCOUNT_SID = "AC889a68b162bfd792dbf3dc31a3d4deb4";
    public static final String AUTH_TOKEN = "c22f4b086ca32d21c70717b711d9bb3e";
    
    

    public static void envoyer(int num,String mess) 
    {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        Message message;
        message= Message.creator(new PhoneNumber("+216"+num), new PhoneNumber("+13237160973"), mess).create();
                
                
    }

    
}