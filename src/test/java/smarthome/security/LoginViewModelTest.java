package smarthome.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewModelTest {

    @Test
    void testSetAndGetPasswordMethodsReturnsExpectedPassword() {
        LoginViewModel loginViewModel= new LoginViewModel();
        loginViewModel.setPassword("!+HIS.IS#PA$$W00RD$0");
        String expected= "!+HIS.IS#PA$$W00RD$0";
        String result= loginViewModel.getPassword();
        assertEquals(expected,result);
    }

    @Test
    void testGetPasswordReturnsNull() {
        LoginViewModel loginViewModel= new LoginViewModel();
        String result=loginViewModel.getPassword();
        assertNull(result);
    }

    @Test
    void testSetAndGetPasswordDoesNotReturnIncorrectPassword() {
        LoginViewModel loginViewModel= new LoginViewModel();
        loginViewModel.setPassword("!+HIS.IS#PA$$W00RD$0");
        String expected ="incorrectPassword";
        String result=loginViewModel.getPassword();
        assertNotEquals(expected,result);
    }

    @Test
    void testSetAndGetUsernameReturnsExpectedResult() {
        LoginViewModel loginViewModel= new LoginViewModel();
        loginViewModel.setUsername("userName");
        String expected ="userName";
        String result=loginViewModel.getUsername();
        assertNotNull(expected,result);
    }

    @Test
    void testSetAndGetUsernameDoesNotReturnIncorrectUsername() {
        LoginViewModel loginViewModel= new LoginViewModel();
        loginViewModel.setUsername("userName");
        String expected ="invalidName";
        String result=loginViewModel.getUsername();
        assertNotEquals(expected,result);
    }

    @Test
    void testSetAndGetUsernameDoesNotReturnNullResult() {
        LoginViewModel loginViewModel= new LoginViewModel();
        loginViewModel.setUsername("userName");
        String result=loginViewModel.getUsername();
        assertNotNull(result);
    }

    @Test
    void testGetUsernameReturnsNull() {
        LoginViewModel loginViewModel= new LoginViewModel();
        String result=loginViewModel.getUsername();
        assertNull(result);
    }
}