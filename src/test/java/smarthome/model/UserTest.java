package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
}