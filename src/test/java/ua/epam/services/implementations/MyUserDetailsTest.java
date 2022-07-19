package ua.epam.services.implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import ua.epam.models.Role;
import ua.epam.models.entities.User;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

class MyUserDetailsTest {

    MyUserDetails myUserDetails;
    User user = new User(1, "login", "pass", "email", "name", "surname", Role.USER.name());

    @BeforeEach
    public void setUp() {
        myUserDetails = new MyUserDetails(user);
    }

    @Test
    void getAuthorities() {
        user.setRole(Role.SPEAKER.name());
        myUserDetails = new MyUserDetails(user);

        Collection<? extends GrantedAuthority> list = myUserDetails.getAuthorities();

        assertEquals(new ArrayList<String>() {{
            add("ROLE_"+Role.SPEAKER.name());
        }}.toString(), list.toString());
    }

    @Test
    void getPassword() {
        String pass = "new password 123";
        user.setPassword(pass);
        myUserDetails = new MyUserDetails(user);

        String actual = myUserDetails.getPassword();

        Assertions.assertEquals(pass, actual);
    }

    @Test
    void getUsername() {
        String login = "new login";
        user.setLogin(login);
        myUserDetails = new MyUserDetails(user);

        String actual = myUserDetails.getUsername();

        Assertions.assertEquals(login, actual);
    }

    @Test
    void isAccountNonExpired() {
        Assertions.assertTrue(myUserDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        Assertions.assertTrue(myUserDetails.isAccountNonLocked());

    }

    @Test
    void isCredentialsNonExpired() {
        Assertions.assertTrue(myUserDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        Assertions.assertTrue(myUserDetails.isEnabled());
    }

    @Test
    void testToString() {
        String expected= String.format("MyUserDetails{, user=%s}",user.toString());
        assertEquals(expected,myUserDetails.toString());
    }
}