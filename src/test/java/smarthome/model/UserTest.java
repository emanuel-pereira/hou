package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void getPermissions() {
        User user = new User("aUserName", "pa$$w0r5", "regularUser", "read,write");
        String expected = "read,write";
        String result = user.getPermissions();
        assertEquals(expected, result);

    }

    @Test
    void getPermissionsList() {
        User user = new User("aUserName", "pa$$w0r5", "regularUser", "read,write");
        String expected = "regularUser";
        String result = user.getRoles();
        assertEquals(expected, result);
    }

    @Test
    void seeIfFetIdOfNonPersistedUserReturnsZeroAsId() {
        User user = new User("aUserName", "pa$$w0r5", "regularUser", "read,write");
        long expected = 0;
        long result = user.getId();
        assertEquals(expected, result);
    }

    @Test
    void getRoleListReturnsExpectedListOfRoles() {
        User user = new User("aUserName", "pa$$w0r5", "regularUser,basic,readOnly", "read");
        List<String> expected = Arrays.asList("regularUser", "basic", "readOnly");
        List<String> result = user.getRoleList();
        assertEquals(expected, result);
    }

    @Test
    void getPermissionListReturnsExpectedListOfRoles() {
        User user = new User("aUserName", "pa$$w0r5", "regularUser,basic,readOnly", "read,write");
        List<String> expected = Arrays.asList("read", "write");
        List<String> result = user.getPermissionList();
        assertEquals(expected, result);
    }

    @Test
    void getRoleListReturnsEmptyListForUserInstanceWithEmptyConstructor() {
        User user = new User();
        List<String> expected = new ArrayList<>();
        List<String> result = user.getRoleList();
        assertEquals(expected, result);
    }

    @Test
    void getPermissionsListReturnsEmptyListForUserInstanceWithEmptyConstructor() {
        User user = new User();
        List<String> expected = new ArrayList<>();
        List<String> result = user.getPermissionList();
        assertEquals(expected, result);
    }

    @Test
    void getUserNameOfNullReturnsNull() {
        User user = new User();
        String result = user.getUsername();
        assertNull(result);
    }


    @Test
    void getUserPasswordOfNullReturnsNull() {
        User user = new User();
        String result = user.getPassword();
        assertNull(result);
    }

    @Test
    void getActiveOfNullReturnsNull() {
        User user = new User();
        int expected = 0;
        int result = user.getActive();
        assertEquals(expected, result);
    }
}