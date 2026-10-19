/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assigment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phuti
 */
public class LoginTest {
    private Login login = null;
    
    @Before
    public void setUp() {
        login = new Login();
        login.setNames("Kyle", "Smith");
        login.setUserName("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
    }
    
   @Test
    public void invalidUsername() {
        String expected = "Username is not correctly formatted; please ensure that your "
                + "username contains an underscore and is no more than five characters in length.";

        login.setUserName("kyle!!!!!!!");
        assertFalse(login.checkUserName());
        assertEquals(expected, login.getUsernameStatus());
        assertEquals(expected, login.registerUser());
    }
     @Test
       public void validUsername() {
        assertTrue(login.checkUserName());
        assertEquals("Username successfully captured.", login.getUsernameStatus());
    }
    
   @Test
    public void validPassword() {
        assertTrue(login.checkPasswordComplexity());
        assertEquals("Password successfully captured.", login.getPasswordStatus());
    }
   @Test
    public void invalidPassword() {
        String expected = "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and a special character.";

        login.setPassword("password");
        assertFalse(login.checkPasswordComplexity());
        assertEquals(expected, login.getPasswordStatus());
        assertEquals(expected, login.registerUser());
    }
    @Test
    public void validCellPhone() {
        assertTrue(login.checkCellPhoneNumber());
        assertEquals("Cell number successfully captured.", login.getCellPhoneStatus());
    }

    @Test
    public void invalidCellPhone() {
        String expected = "Cell number is incorrectly formatted or does not contain an international "
                + "code; please correct the number and try again.";

        login.setCellPhoneNumber("08966553");
        assertFalse(login.checkCellPhoneNumber());
        assertEquals(expected, login.getCellPhoneStatus());
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void successfulLogin() {
        assertEquals("User registered successfully.", login.registerUser());
        login.setLoginDetails("kyl_1", "Ch&&sec@ke99!");
        assertTrue(login.loginUser());
        assertEquals("Welcome Kyle, Smith it is great to see you again.", login.returnLoginStatus());
    }

    @Test
    public void failedLogin() {
        login.registerUser();
        login.setLoginDetails("kyl_1", "wrong");
        assertFalse(login.loginUser());
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus());

        // A wrong username must also fail, even with the correct password.
        login.setLoginDetails("wrong", "Ch&&sec@ke99!");
        assertFalse(login.loginUser());
    }
}
