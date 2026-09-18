/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment;

/**
 *
 * @author phuti
 */
public class Login {
    
    public static final String USERNAME_SUCCESS = "Username successfully captured.";
    public static final String USERNAME_ERROR = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    public static final String PASSWORD_SUCCESS = "Password successfully captured.";
    public static final String PASSWORD_ERROR = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    public static final String CELL_PHONE_SUCCESS = "Cell number successfully captured.";
    public static final String CELL_PHONE_ERROR = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    public static final String REGISTRATION_SUCCESS = "User registered successfully.";
    public static final String LOGIN_ERROR = "Username or password incorrect, please try again.";

    private String firstNam;
    private String lastNam;
    private String userNam;
    private String passWord;
    private String cellPhonenum;
    private String loginUsernam;
    private String loginPassword;
    private boolean registered = false;
    
    //The methods store the user inputs from the main 
    public void setNames(String newfirstNam, String newlastNam){
        firstNam = newfirstNam;
        lastNam = newlastNam;
        registered = false;
    }
    
    public void setUserName(String newUserNam) {
        userNam = newUserNam;
        registered = false;
    }
    public void setPassword(String newpassWord) {
        passWord = newpassWord;
        registered = false;
    }

    public void setCellPhoneNumber(String newcellPhonenum) {
        cellPhonenum = newcellPhonenum;
        registered = false;
    }

    // Keep attempted credentials separated from the registered ones.
    public void setLoginDetails(String enteredUsernam, String enteredPassword) {
        loginUsernam = enteredUsernam;
        loginPassword = enteredPassword;
    }

    public boolean checkUserName() {
        return userNam != null
                && userNam.indexOf('_') >= 0
                && userNam.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        int index = 0;
        char character = '\0';
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (passWord == null || passWord.length() < 8) {
            return false;
        }

        // Examine each character and remember which requirements are found.
        while (index < passWord.length()) {
            character = passWord.charAt(index);

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)
                    && !Character.isWhitespace(character)
                    && !Character.isISOControl(character)) {
                hasSpecial = true;
            }

            index = index + 1;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        return cellPhonenum != null
                && cellPhonenum.matches("^\\+27[1-9][0-9]{8}$");
    }

    // Convert each true/false validation result into a message.
    public String getUsernameStatus() {
        if (checkUserName()) {
            return USERNAME_SUCCESS;
        }
        return USERNAME_ERROR;
    }

    public String getPasswordStatus() {
        if (checkPasswordComplexity()) {
            return PASSWORD_SUCCESS;
        }
        return PASSWORD_ERROR;
    }

    public String getCellPhoneStatus() {
        if (checkCellPhoneNumber()) {
            return CELL_PHONE_SUCCESS;
        }
        return CELL_PHONE_ERROR;
    }

    public String registerUser() {
        registered = false;

        if (!checkUserName()) {
            return USERNAME_ERROR;
        }

        if (!checkPasswordComplexity()) {
            return PASSWORD_ERROR;
        }

        if (!checkCellPhoneNumber()) {
            return CELL_PHONE_ERROR;
        }

        registered = true;
        return REGISTRATION_SUCCESS;
    }

    public boolean isRegistered() {
        return registered;
    }

    public boolean loginUser() {
        return registered
                && userNam.equals(loginUsernam)
                && passWord.equals(loginPassword);
    }

    public String returnLoginStatus() {
        if (loginUser()) {
            return "Welcome " + firstNam + ", " + lastNam
                    + " it is great to see you again.";
        }
        return LOGIN_ERROR;
    }
}
