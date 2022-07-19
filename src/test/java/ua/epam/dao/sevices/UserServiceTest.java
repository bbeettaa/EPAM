package ua.epam.dao.sevices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.user.IUserDao;
import ua.epam.models.Role;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private IUserDao userDao;
    @MockBean
    private MessageSource messageSource;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void setUp() {
        user1 = new User(1, "login", "pass", "email", "name", "surname", Role.USER.name());
        user2 = new User(2, "login2", "pass2", "email2", "name2", "surname2", Role.USER.name());
        user3 = new User();
    }

    @Test
    void get() {
        when(userDao.read(anyInt())).thenReturn(user1);

        User actual =  userService.get(1);

        assertEquals(user1.toString(),actual.toString());
    }

    @Test
    void get_withPaging() {
        List<User> users = new ArrayList<User>() {{
            add(user1);
            add(user2);
        }};
        when(userDao.read(anyInt(), anyInt(), anyString(), any(), any())).thenReturn(users);

        List<User> actual =  userService.get(0,10,"", IUserDao.SortOrder.ID, IEntityDao.SortType.ASC);

        assertEquals(users.toString(),actual.toString());
    }

    @Test
    void save_true() {
        when(userDao.create(user1)).thenReturn(true);
        assertTrue(userService.save(user1));
    }

    @Test
    void save_false() {
        when(userDao.create(user1)).thenReturn(false);
        assertFalse(userService.save(user1));
    }

    @Test
    void delete() {
        userService.delete(user1.getId());
        verify(userDao).delete(user1.getId());
    }

    @Test
    void update_true() {
        when(userDao.update(2,user1)).thenReturn(true);
        assertTrue(userService.update(2,user1));
    }

    @Test
    void update_false() {
        when(userDao.update(2,user1)).thenReturn(false);
        assertFalse(userService.update(2,user1));
    }

    @Test
    void count() {
        when(userDao.count(anyString())).thenReturn(1);
        int actual = userService.count("hello");
        assertEquals(1,actual);
    }

    @Test
    void getRoleByLoginPassword() {
        when(userDao.getByLoginPassword(anyString(),anyString())).thenReturn(user1);
        String actual = userService.getRoleByLoginPassword("hello","world");
        assertEquals(user1.getRole(),actual);
    }

    @Test
    void getRoleByLoginPassword_null() {
        when(userDao.getByLoginPassword(anyString(),anyString())).thenReturn(null);
        String actual = userService.getRoleByLoginPassword("hello","world");
        assertEquals(Role.UNREGISTERED.toString(),actual);
    }

    @Test
    void isEmailExisted_true() {
        when(userDao.getByEmail(anyString())).thenReturn(user1);
        boolean actual = userService.isEmailExisted(user1.getEmail(), 2);
        assertTrue(actual);
    }

    @Test
    void isEmailExisted_false() {
        when(userDao.getByEmail(anyString())).thenReturn(user1);
        boolean actual = userService.isEmailExisted(user1.getEmail(), user1.getId());
        assertFalse(actual);
    }

    @Test
    void isLoginExisted_true() {
        when(userDao.getByLogin(anyString())).thenReturn(user1);
        boolean actual = userService.isLoginExisted(user1.getLogin(), 2);
        assertTrue(actual);
    }

    @Test
    void isLoginExisted_false() {
        when(userDao.getByLogin(anyString())).thenReturn(user1);
        boolean actual = userService.isLoginExisted(user1.getLogin(), user1.getId());
        assertFalse(actual);
    }

    @Test
    void isError_bindingResultError_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User","User");
        bindingResult.addError(new FieldError("User","login","msg"));

        boolean actual = userService.isError(user1,bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_emailExist_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User","User");
        when(userDao.getByLogin(anyString())).thenReturn(user1);
        when(userDao.getByEmail(anyString())).thenReturn(user2);

        boolean actual = userService.isError(user1,bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_email_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User","User");
        when(userDao.getByLogin(anyString())).thenReturn(user1);
        when(userDao.getByEmail(anyString())).thenReturn(user2);

        boolean actual = userService.isError(user1,bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_loginExist_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User","User");
        when(userDao.getByLogin(anyString())).thenReturn(user2);
        when(userDao.getByEmail(anyString())).thenReturn(user1);

        boolean actual = userService.isError(user1,bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User","User");
        when(userDao.getByLogin(anyString())).thenReturn(user1);
        when(userDao.getByEmail(anyString())).thenReturn(user1);

        boolean actual = userService.isError(user1,bindingResult);

        assertFalse(actual);
    }
}