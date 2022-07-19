package ua.epam.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.dao.sevices.ReportService;
import ua.epam.dao.sevices.SubscriptionsService;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.Role;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;
import ua.epam.services.IMailService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MailServiceTest {

    @Autowired
    private IMailService mailService;
    @MockBean
    private JavaMailSender sender;
    @MockBean
    SubscriptionsService subscriptionsService;
    @MockBean
    UserService userService;

    User user;
    Event event;
    SimpleMailMessage mailMessage;

    @BeforeEach
    public void setUp() {
        user = new User(1, "login", "pass", "email", "name", "surname", Role.USER.name());
        event = new Event(1, "nameEv", new Date(1L), new Time(1L), 11, 12, false, false);
        mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@baeldung.com");
        mailMessage.setTo(user.getEmail());

        when(userService.get(user.getId())).thenReturn(user);
    }

    @Test
    void sendGreeting_true() {
        mailMessage.setSubject("Greeting");
        mailMessage.setText(
                String.format("Welcome %s %s, to site 'online conferences'. Let`s begin first conference 'http://localhost:8081/'"
                        , user.getName(), user.getSurname()));

        doNothing().when(sender).send(mailMessage);
        assertTrue(mailService.sendGreeting(user));
    }

    @Test
    void sendGreeting_false() {
        mailMessage.setSubject("Greeting");
        mailMessage.setText(
                String.format("Welcome %s %s, to site 'online conferences'. Let`s begin first conference 'http://localhost:8081/'"
                        , user.getName(), user.getSurname()));

        doThrow(new MailSendException("")).when(sender).send(mailMessage);

        assertFalse(mailService.sendGreeting(user));
    }

    @Test
    void sendEventChangeMessages_true() {
        mailMessage.setSubject(String.format("Something changed at the conference %s", event.getName()));
        mailMessage.setText(
                String.format("Hello %s %s, conference '%s' has been changed."
                        , user.getName(), user.getSurname(), event.getName()));

        doNothing().when(sender).send(mailMessage);
        when(subscriptionsService.getSubscribers(event.getId())).thenReturn(new ArrayList<Integer>() {{
            add(user.getId());
        }});

        assertTrue(mailService.sendEventChangeMessages(event));
    }

    @Test
    void sendEventRemovedMessages_false() {
        mailMessage.setSubject(String.format("Something changed at the conference %s", event.getName()));
        mailMessage.setText(
                String.format("Hello %s %s, conference '%s' has been changed."
                        , user.getName(), user.getSurname(), event.getName()));

        doNothing().when(sender).send(mailMessage);
        when(subscriptionsService.getSubscribers(event.getId())).thenReturn(new ArrayList<Integer>() {{
            add(user.getId());
        }});

        doThrow(new MailSendException("")).when(sender).send(mailMessage);

        assertFalse(mailService.sendEventChangeMessages(event));
    }

    @Test
    void sendEventRemovedMessages() {
        mailMessage.setSubject(String.format("Conference '%s' has been removed.", event.getName()));
        mailMessage.setText(
                String.format("Hello %s %s, conference '%s' has changed."
                        , user.getName(), user.getSurname(), event.getName()));

        doNothing().when(sender).send(mailMessage);

        when(subscriptionsService.getSubscribers(event.getId())).thenReturn(new ArrayList<Integer>() {{
            add(user.getId());
        }});

        mailService.sendEventRemovedMessages(event);
    }

    @Test
    void sendEventRemovedMessages_ex() {
        when(subscriptionsService.getSubscribers(event.getId())).thenReturn(new ArrayList<Integer>() {{
            add(user.getId());
        }});
        doThrow(new MailSendException("")).when(sender).send(new SimpleMailMessage());
        mailService.sendEventRemovedMessages(event);
    }

    @Test
    void sendAcceptSuggestion_true() {
        String eventName = "eventName";
        String reportName = "reportName";
        mailMessage.setSubject("Your report has been accepted.");
        mailMessage.setText(
                String.format("Hello %s %s, your report '%s' has been accepted at conference '%s'."
                        , user.getName(), user.getSurname(), reportName, eventName));

        doNothing().when(sender).send(mailMessage);

        assertTrue(mailService.sendAcceptSuggestion(event.getId(), eventName, reportName));
    }

    @Test
    void sendAcceptSuggestion_false() {
        String eventName = "eventName";
        String reportName = "reportName";
        mailMessage.setSubject("Your report has been accepted.");
        mailMessage.setText(
                String.format("Hello %s %s, your report '%s' has been accepted at conference '%s'."
                        , user.getName(), user.getSurname(), reportName, eventName));

        doThrow(new MailSendException("")).when(sender).send(mailMessage);

        assertFalse(mailService.sendAcceptSuggestion(event.getId(), eventName, reportName));
    }

    @Test
    void sendDeclineSuggestion_true() {
        String eventName = "eventName";
        String reportName = "reportName";
        mailMessage.setSubject("Your report has been declined.");
        mailMessage.setText(
                String.format("Hello %s %s, your report '%s' has been declined at conference '%s'."
                        , user.getName(), user.getSurname(), reportName, eventName));

        doNothing().when(sender).send(mailMessage);

        assertTrue(mailService.sendDeclineSuggestion(event.getId(), eventName, reportName));
    }

    @Test
    void sendDeclineSuggestion_false() {
        String eventName = "eventName";
        String reportName = "reportName";
        mailMessage.setSubject("Your report has been declined.");
        mailMessage.setText(
                String.format("Hello %s %s, your report '%s' has been declined at conference '%s'."
                        , user.getName(), user.getSurname(), reportName, eventName));

        doNothing().when(sender).send(mailMessage);
        doThrow(new MailSendException("")).when(sender).send(mailMessage);

        assertFalse(mailService.sendDeclineSuggestion(event.getId(), eventName, reportName));
    }

    @Test
    void sendRemoveReport_true() {
        String eventName = "eventName";
        String reportName = "reportName";
        mailMessage.setSubject("Your report has been removed.");
        mailMessage.setText(
                String.format("Hello %s %s, your report '%s' has been removed at conference '%s'."
                        , user.getName(), user.getSurname(), reportName, eventName));

        doNothing().when(sender).send(mailMessage);

        assertTrue(mailService.sendRemoveReport(event.getId(), eventName, reportName));
    }

    @Test
    void sendRemoveReport_false() {
        String eventName = "eventName";
        String reportName = "reportName";
        mailMessage.setSubject("Your report has been removed.");
        mailMessage.setText(
                String.format("Hello %s %s, your report '%s' has been removed at conference '%s'."
                        , user.getName(), user.getSurname(), reportName, eventName));

        doThrow(new MailSendException("")).when(sender).send(mailMessage);

        assertFalse(mailService.sendRemoveReport(event.getId(), eventName, reportName));
    }

    @Test
    void sendRegistrationReport_true() {
        Report report = new Report(1, "rep", 1, user.getId(), user.getName(), user);
        mailMessage.setSubject("Report has been registered for you.");
        mailMessage.setText(
                String.format("Welcome %s %s, report '%s' has been registered for you."
                        , user.getName(), user.getSurname(), report.getName()));

        doNothing().when(sender).send(mailMessage);

        assertTrue(mailService.sendRegistrationReport(user, report));
    }

    @Test
    void sendRegistrationReport_false() {
        doThrow(new MailSendException("")).when(sender).send(new SimpleMailMessage());
        assertFalse(mailService.sendRegistrationReport(user, null));
    }
}