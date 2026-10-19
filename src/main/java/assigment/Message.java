/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment;

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
    
    //Constructor
    public Message(int messageNum,String recipient,String messageText){
        
        this.messageNum = messageNum;
        this.recipient = recipient;
        this.messageText = messageText;
    }
    
    //Checking if recipient's cellphone number is vaild
    public boolean checkrecipientNum(){
        return recipient !=null
                && recipient.matches("^\\+27[1-9][0-9]{8}$");
    }
    
    
    
    
    
    
    
}
