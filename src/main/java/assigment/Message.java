/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment;

import java.util.Random;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author phuti
 */
public class Message {
    //Variables to store messages
    private String messageID;
    private  int messageNum;
    private String recipient;
    private String messageText;

    private static final Random random=new Random();

private static final Set<String> usedMessageIDs = new HashSet<>();

    
    //Constructor
    public Message(int messageNum,String recipient,String messageText){
        
        this.messageNum = messageNum;
        this.recipient = recipient;
        this.messageText = messageText;
        
        this.messageID = generateMessageID();
    }
    //ID message method
    private static String generateMessageID(){
        String id;
        
        do{
            StringBuilder builder = new StringBuilder();
            
            for (int i=0;i <10;i++){
                builder.append(random.nextInt(10));
            }
            
            id = builder.toString();
            
        }while (!usedMessageIDs.add(id));
        
        return id;  
    }
    
    //Checking if recipient's cellphone number is vaild
    public boolean checkrecipientNum(){
        return recipient !=null
                && recipient.matches("^\\+27[1-9][0-9]{8}$");
    }
    //Check if characters are 250 long
    public boolean checkmessageLength(){
        return messageText !=null
                && messageText.length() <= 250;
    }
    
}
