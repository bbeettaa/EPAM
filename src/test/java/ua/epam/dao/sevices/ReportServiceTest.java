package ua.epam.dao.sevices;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.epam.dao.entities.report.IReportDao;
import ua.epam.models.Role;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    private ReportService reportService;
    @MockBean
    private IReportDao reportDao;
    @MockBean
    private UserService userService;
    @MockBean
    private MessageSource messageSource;

    User user1;
    User user2;
    User user3;

    Report report1;
    Report report2;
    Report report3;

    @BeforeEach
    public void setUp() {
        user1 = new User(1, "login", "pass", "email", "name", "surname", Role.SPEAKER.name());
        user2 = new User(2, "login2", "pass2", "email2", "name2", "surname2", Role.SPEAKER.name());
        user3 = new User(3, "login3", "pass3", "email3", "name3", "surname3", Role.USER.name());
        when(userService.get(user1.getId())).thenReturn(user1);
        when(userService.get(user2.getId())).thenReturn(user2);

        report1 = new Report(1, "report1", 1, 11, "123", null);
        report2 = new Report(2, "report2", 1, 21, "14", null);
        report3 = new Report(3, "report3", 2, 1, "23", null);
    }


    @Test
    void get_list() {
        List<Report> list = new ArrayList<Report>() {{
            add(report1);
            add(new Report(1, "report1", 1, 1, null, null));
            add(new Report(2, "report2", 1, 2, null, null));
        }};
        when(reportDao.read(anyInt(), anyInt(), anyInt())).thenReturn(list);

        List<Report> actual = reportService.get(1, 2, 3);

        assertEquals(new ArrayList<Report>() {{
            add(report1);
            add(new Report(1, "report1", 1, 1, null, user1));
            add(new Report(2, "report2", 1, 2, null, user2));
        }}.toString(), actual.toString());
    }

    @Test
    void get() {
        when(reportDao.read(anyInt())).thenReturn(new Report(2, "report2", 1, 2, null, null));
        Report actual = reportService.get(3);
        assertEquals(new Report(2, "report2", 1, 2, null, user2).toString(), actual.toString());
    }

    @Test
    void save_true() {
        when(reportDao.create(any())).thenReturn(true);
        when(userService.getByLogin(any())).thenReturn(user1);
        boolean actual = reportService.save(report1);
        assertTrue(actual);
    }

    @Test
    void save_false() {
        when(reportDao.create(any())).thenReturn(false);
        when(userService.getByLogin(any())).thenReturn(user2);
        boolean actual = reportService.save(report1);
        assertFalse(actual);
    }

    @Test
    void delete() {
        reportService.delete(1, 2);
        verify(reportDao).delete(1);
    }

    @Test
    void update_true() {
        when(reportDao.update(anyInt(), any())).thenReturn(true);
        when(userService.getByLogin(any())).thenReturn(user2);
        boolean actual = reportService.update(2, report1);
        assertTrue(actual);
    }

    @Test
    void count() {
        when(reportDao.count(anyString())).thenReturn(1);
        int actual = reportService.count("2131");
        assertEquals(1, actual);
    }

    @Test
    void isNameExisted_true() {
        when(reportDao.getByName(anyString())).thenReturn(report1);
        boolean actual = reportService.isNameExisted("name", 123);
        assertTrue(actual);
    }

    @Test
    void isNameExisted_false() {
        when(reportDao.getByName(anyString())).thenReturn(null);
        boolean actual = reportService.isNameExisted("name", 123);
        assertFalse(actual);
    }

    @Test
    void getByName() {
        when(reportDao.getByName(anyString())).thenReturn(report1);
        Report actual = reportService.getByName("name");
        assertEquals(report1.toString(), actual.toString());
    }

    @Test
    void isError_bindingResultError_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        bindingResult.addError(new FieldError("User", "login", "msg"));

        boolean actual = reportService.isError(report1, bindingResult);

        assertTrue(actual);
    }


    @Test
    void isError_nameError_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(reportDao.getByName(any())).thenReturn(report2);

        boolean actual = reportService.isError(report1, bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_loginError_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(userService.getByLogin(any())).thenReturn(null);

        boolean actual = reportService.isError(report3, bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_speakerName_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(userService.getByLogin(any())).thenReturn(null);

        boolean actual = reportService.isError(report1, bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_notSpeaker_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(userService.getByLogin(anyString())).thenReturn(user3);

        boolean actual = reportService.isError(report1, bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(userService.getByLogin(any())).thenReturn(user1);

        boolean actual = reportService.isError(report1, bindingResult);

        assertFalse(actual);
    }
}