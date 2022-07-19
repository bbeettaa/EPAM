package ua.epam.services;

import org.springframework.stereotype.Service;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;

@Service
public interface IMailService {
    boolean sendGreeting(User user);
    boolean sendEventChangeMessages(Event event);
    void sendEventRemovedMessages(Event event);
    boolean sendAcceptSuggestion(int eventId, String eventName, String reportName);
    boolean sendDeclineSuggestion(int userID, String eventName, String reportName);
    boolean sendRemoveReport(int eventId, String eventName, String reportName);
    boolean sendRegistrationReport(User user, Report report);
}
