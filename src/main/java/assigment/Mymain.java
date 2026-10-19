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
                    System.out.println("Message sending will be added next.");
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
}
