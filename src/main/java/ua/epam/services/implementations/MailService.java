package ua.epam.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ua.epam.dao.sevices.SubscriptionsService;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;
import ua.epam.services.IMailService;

@Service
public class MailService implements IMailService {
    @Autowired
    JavaMailSender sender;
    @Autowired
    UserService userService;
    @Autowired
    SubscriptionsService subscriptionsService;

    private static final Logger logger
            = LoggerFactory.getLogger(MailService.class);

    @Override
    public boolean sendGreeting(User user) {
        try {
            sendMail(user.getEmail(), "Greeting",
                    String.format("Welcome %s %s, to site 'online conferences'. Let`s begin first conference 'http://localhost:8081/'"
                            , user.getName(), user.getSurname()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean sendEventChangeMessages(Event event) {
        for (Integer id : subscriptionsService.getSubscribers(event.getId())) {
            try {
                User user = userService.get(id);

                sendMail(user.getEmail(), String.format("Something changed at the conference %s", event.getName()),
                        String.format("Hello %s %s, conference '%s' has been changed."
                                , user.getName(), user.getSurname(), event.getName()));
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public void sendEventRemovedMessages(Event event) {
        new Thread(() -> {
            for (Integer id : subscriptionsService.getSubscribers(event.getId())) {
                try {
                    User user = userService.get(id);

                    sendMail(user.getEmail(), String.format("Conference '%s' has been removed.", event.getName()),
                            String.format("Hello %s %s, conference '%s' has changed."
                                    , user.getName(), user.getSurname(), event.getName()));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public boolean sendAcceptSuggestion(int userID, String eventName, String reportName) {
        try {
            User user = userService.get(userID);

            sendMail(user.getEmail(), "Your report has been accepted.", String.format(
                    "Hello %s %s, your report '%s' has been accepted at conference '%s'."
                    , user.getName(), user.getSurname(), reportName, eventName));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean sendDeclineSuggestion(int userID, String eventName, String reportName) {
        try {
            User user = userService.get(userID);

            sendMail(user.getEmail(), "Your report has been declined.", String.format(
                    "Hello %s %s, your report '%s' has been declined at conference '%s'."
                    , user.getName(), user.getSurname(), reportName, eventName));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean sendRemoveReport(int userId, String eventName, String reportName) {
        try {
            User user = userService.get(userId);
            sendMail(user.getEmail(), "Your report has been removed.", String.format(
                    "Hello %s %s, your report '%s' has been removed at conference '%s'."
                    , user.getName(), user.getSurname(), reportName, eventName));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean sendRegistrationReport(User user, Report report) {
        try {
            sendMail(user.getEmail(), "Report has been registered for you.",
                    String.format("Welcome %s %s, report '%s' has been registered for you."
                            , user.getName(), user.getSurname(), report.getName()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }
}

