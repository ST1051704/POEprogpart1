/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment;

import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

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
    
private static int totalMessagesSent = 0;
private boolean handled = false;
private static final java.util.List<Message> storedMessages = new java.util.ArrayList<>();

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
public String getMessageID(){
    return messageID;
}

public String createMessageHash() {

    if (messageText == null || messageText.trim().isEmpty()) {
        return "";
    }

    // Separate the message into words
    String[] words = messageText.trim().split("\\s+");

    // Get the first and last words
    String firstWord = words[0];
    String lastWord = words[words.length - 1];

    // Remove punctuation
    firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
    lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

    // Get first two digits of the message ID
    String firsttwoDigits = messageID.substring(0, 2);

    // Generate the message hash
    return firsttwoDigits + ":" + messageNum + ":"
            + (firstWord + lastWord).toUpperCase();
}

// This method marks a message as sent
public String sentMessage() {

    if (handled) {
        return "This message has already been handled.";
    }
    totalMessagesSent++;
    handled = true;
    return "Message successfully sent.";
}
// This method keeps messages ready for JSON storage
public String storeMessage() {
    if (handled) {
        return "This message has already been handled.";
    }
    try {
        Path file = Path.of("messages.json");

        String existing = Files.exists(file)
                ? Files.readString(file, StandardCharsets.UTF_8).trim()
                : "[]";

        if (!existing.startsWith("[")
                || !existing.endsWith("]")) {
            return "Invalid JSON storage file.";
        }

        String record = "{"
                + "\"messageID\":\"" + escapeJson(messageID) + "\","
                + "\"messageNumber\":" + messageNum + ","
                + "\"messageHash\":\"" + escapeJson(createMessageHash()) + "\","
                + "\"recipient\":\"" + escapeJson(recipient) + "\","
                + "\"message\":\"" + escapeJson(messageText) + "\""
                + "}";

        String contents = existing.substring(
                1, existing.length() - 1).trim();

        String updated = "["
                + (contents.isEmpty() ? "" : contents + ",")
                + record + "]";

        Files.writeString(file, updated, StandardCharsets.UTF_8);

        storedMessages.add(this);
        handled = true;

        return "Message successfully stored.";

    } catch (IOException e) {
        return "Failed to store message: " + e.getMessage();
    }
}

private static String escapeJson(String text) {

    if (text == null) {
        return "";
    }

    return text.replace("\\", "\\\\")
               .replace("\"", "\\\"")
               .replace("\n", "\\n")
               .replace("\r", "\\r")
               .replace("\t", "\\t");
}

// This method disregards a message
public String disregardMessage() {
    if (handled) {
        return "This message has already been handled.";
    }
    handled = true;
    return "Message disregarded.";
}
// Returns the total number of sent messages
public static int returnTotalMessages() {

    return totalMessagesSent;
}


public static void main(String[] args){
    //Creating first message
    Message first = new Message(1,"+27873145678","Hello Raisebe");
    
    //Second message 
    Message second = new Message(2,"+27784567896","How are you?");
    
    //Display the generate IDs
    System.out.println("First message: " + first.getMessageID());
    
    System.out.println("Second message: " + second.getMessageID());
    
     // Check the length of the IDs
    System.out.println("First ID length: " + first.getMessageID().length());

    System.out.println("Second ID length: " + second.getMessageID().length());
 // Check that both IDs are different
    System.out.println("IDs are different: "
            + !first.getMessageID().equals(second.getMessageID()));
}
}
