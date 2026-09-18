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
    while
}
}
