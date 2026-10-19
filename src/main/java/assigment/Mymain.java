/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment;
/**
 *
 * @author phuti
 */
import java.util.Scanner;
public class Mymain {
    
     public static void main(String[] args) {
    String firstNam;
    String lastNam;
    String logUsernam;
    String logPassword;
    Login account = new Login();
    Scanner input = new Scanner(System.in);
    
    System.out.println("Create your Account");
    System.out.println("Enter your first name: ");
    firstNam = input.nextLine();
    System.out.println("Enter your last name: ");
    lastNam = input.nextLine();
    account.setNames(firstNam,lastNam);
    
    //loops which will repeat only if the information turns out wrong 
    while (!account.checkUserName()){
        System.out.println("Enter a username that contains a _ and no more than 5 characters: ");
        account.setUserName(input.nextLine());
        System.out.println(account.getUsernameStatus());
    }
    while (!account.checkPasswordComplexity()){
        System.out.print("Enter a password with 8 characters with 8 characters, a capital letter , a number and a special characters: ");
        account.setPassword(input.nextLine());
        System.out.println(account.getPasswordStatus());
    }
    while (!account.checkCellPhoneNumber()){
        System.out.print("Enter your cellphone number starting with +27: " );
        account.setCellPhoneNumber(input.nextLine());
        System.out.println(account.getCellPhoneStatus());
    }
    System.out.println(account.registerUser());
    
    //This is what happens when the login is successful after registration
    if (account.isRegistered()) {
    System.out.print("Enter your login username: ");
    logUsernam = input.nextLine();

    System.out.print("Enter your login password: ");
    logPassword = input.nextLine();

    account.setLoginDetails(logUsernam, logPassword);
    System.out.println(account.returnLoginStatus());
    
    if(account.loginUser()){
        quickchatMenu(input);
    }
}

    }
     //Quickchat menu
     public static void quickchatMenu(Scanner input){
         
         boolean running = true;
         
         System.out.println("Welcome to Quick chat.");
         
         while(running){
            System.out.println("\nQuickChat Menu");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            
            System.out.println("Choose an option: ");
            String choice = input.nextLine();
            
            switch(choice) {
                
                case "1":
                    enterMessages(input);
                    break;
                 case "2":
                    System.out.println("Will come soon");
                    break;
                 case "3":
                    System.out.println("Goodbye:)");
                    running = false;
                    break;
                 default:
                     System.out.println("Invalid option. Please try again");
            }
         }
     }
     public static void enterMessages(Scanner input){
         int totalMessages;
         
         //Prompt user to enter how many messages they wanna send 
         System.out.print("How many messages do u wanna send?");
          try {
        totalMessages = Integer.parseInt(input.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number.");
        return;
    }

    if (totalMessages <= 0) {
        System.out.println("Enter a number greater than zero.");
        return;
    }

    // Loop for the number of messages selected
    for (int i = 0; i < totalMessages; i++) {

        System.out.println("\nMessage " + (i + 1));

        System.out.print("Enter recipient cellphone number: ");
        String recipient = input.nextLine();

        System.out.print("Enter your message: ");
        String messageText = input.nextLine();

        // Create a Message object
        Message message = new Message(
                i, recipient, messageText
        );

        // Check recipient number
        if (!message.checkrecipientNum()) {
            System.out.println(
                "Recipient number is incorrectly formatted."
            );
            continue;
        }

        // Check message length
        if (!message.checkmessageLength()
                || messageText.trim().isEmpty()) {
            System.out.println(
                "Message must contain 1 to 250 characters."
            );
            continue;
        }

        // Display the message details
        System.out.println("Message ID: "
                + message.getMessageID());

        System.out.println("Message Hash: "
                + message.createMessageHash());

        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
        
        // Ask the user what to do with the message
boolean actionSelected = false;

while (!actionSelected) {

    System.out.println("\nWhat would you like to do?");
    System.out.println("1) Send Message");
    System.out.println("2) Store Message");
    System.out.println("3) Disregard Message");

    System.out.print("Choose an option: ");
    String action = input.nextLine();

    switch (action) {

        case "1":
            System.out.println(message.sentMessage());

            System.out.println("Total messages sent: "
                    + Message.returnTotalMessages());

            actionSelected = true;
            break;

        case "2":
            System.out.println(message.storeMessage());
            System.out.println(
                "JSON file saving will be added next."
            );

            actionSelected = true;
            break;

        case "3":
            System.out.println(message.disregardMessage());

            actionSelected = true;
            break;

        default:
            System.out.println(
                "Invalid option. Please choose 1, 2 or 3."
            );
        
      }
     }
    }
  }
}
