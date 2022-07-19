package ua.epam.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.Role;
import ua.epam.models.entities.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDetailsServiceImplTest {
    @Autowired
    UserDetailsService userDetailsService;
    @MockBean
    UserService userService;

    User user;

    @BeforeEach
    private void setUp() {
        user = new User(1, "login", "pass", "email", "name", "surname", Role.USER.name());

    }

    @Test
    void loadUserByUsername() {
        when(userService.getByLogin("login")).thenReturn(user);
        UserDetails actual = userDetailsService.loadUserByUsername("login");

        assertEquals(new MyUserDetails(user).toString(), actual.toString());
    }

    @Test
    void loadUserByUsername_exception() {
        when(userService.getByLogin("login")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("login"));
    }
}