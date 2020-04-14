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
    public static final String ACCOUNT_SID = "AC4be37c9bdd2cde7cd53222d9e22ec54d";
    public static final String AUTH_TOKEN = "f47c75222dc88d016802a6a06566dafd";

    public static void envoyer(int num,String mess) 
    {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        Message message;
        message= Message
                .creator(new PhoneNumber("+216"+num), new PhoneNumber("+12138066854"), mess).create();
                
    }

    
}