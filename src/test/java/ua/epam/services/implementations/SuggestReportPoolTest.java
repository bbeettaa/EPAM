package ua.epam.services.implementations;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.ReportService;
import ua.epam.models.Role;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;
import ua.epam.services.IMailService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SuggestReportPoolTest {

    @Autowired
    private SuggestReportPool reportPool;
    @MockBean
    private IMailService mailService;
    @MockBean
    private ReportService reportService;

    Report report1;
    Report report2;
    Report report3;
    Report report4;

    @BeforeEach
    public void setUp() {
        User speaker1 = new User(11, "user1", "pass", "email@2", "name", "surname", Role.SPEAKER.name());

        report1 = new Report(1, "report1", 1, 11, null, speaker1);
        report2 = new Report(2, "report2", 1, 11, null, speaker1);
        report3 = new Report(3, "report3", 2, 12, null, null);
        report4 = new Report(4, "report4", 2, 13, null, null);
    }

    @AfterEach
    public void clear() {
        reportPool.clear();
    }

    @Test
    void addSuggestion() {
        List<Report> expected = new ArrayList<>();
        expected.add(report1);
        expected.add(report2);

        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));
        assertTrue(reportPool.addSuggestion(report3));

        assertEquals(expected.toString(), reportPool.getSuggestions(11).toString());
    }

    @Test
    void addSuggestion_nameRepeated() {
        List<Report> expected = new ArrayList<>();
        expected.add(report1);
        expected.add(report2);

        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));
        assertFalse(reportPool.addSuggestion(report2));

        assertEquals(expected.toString(), reportPool.getSuggestions().toString());
    }

    @Test
    void testGetSuggestions() {
        List<Report> expected = new ArrayList<>();
        expected.add(report4);
        expected.add(report3);
        expected.add(report1);
        expected.add(report2);

        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));
        assertTrue(reportPool.addSuggestion(report3));
        assertTrue(reportPool.addSuggestion(report4));

        assertEquals(expected.toString(), reportPool.getSuggestions().toString());
    }

    @Test
    void declineByName() {
        when(mailService.sendDeclineSuggestion(anyInt(), anyString(), anyString())).thenReturn(true);
        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));

        reportPool.declineByName(report1.getName(), report1.getSpeakerId());

        assertEquals(new ArrayList<Report>() {{
            add(report2);
        }}.toString(), reportPool.getSuggestions().toString());
    }

    @Test
    void testDeclineByName() {
        when(mailService.sendDeclineSuggestion(anyInt(), anyString(), anyString())).thenReturn(true);
        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));

        reportPool.declineByName(report1.getName());

        assertEquals(new ArrayList<Report>() {{
            add(report2);
        }}.toString(), reportPool.getSuggestions().toString());
    }

    @Test
    void acceptByName() {
        when(mailService.sendAcceptSuggestion(anyInt(), anyString(), anyString())).thenReturn(true);
        when(reportService.save(any())).thenReturn(true);
        when(reportService.save(any())).thenReturn(true);
        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));

        reportPool.acceptByName(report1.getName());

        assertEquals(new ArrayList<Report>() {{
            add(report2);
        }}.toString(), reportPool.getSuggestions().toString());
    }

    @Test
    void isNameExist_true() {
        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));

        assertTrue(reportPool.isNameExist(report1.getName()));
    }

    @Test
    void isNameExist_false() {
        assertTrue(reportPool.addSuggestion(report1));
        assertTrue(reportPool.addSuggestion(report2));

        assertFalse(reportPool.isNameExist(report3.getName()));
    }
}
