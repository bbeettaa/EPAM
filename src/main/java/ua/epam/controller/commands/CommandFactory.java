package ua.epam.controller.commands;

import ua.epam.controller.commands.admin.*;
import ua.epam.controller.commands.common.LoginCommand;
import ua.epam.controller.commands.common.LogoutCommand;
import ua.epam.controller.commands.common.RegistrationCommand;
import ua.epam.controller.commands.common.RegistrationPage;
import ua.epam.controller.commands.moderator.event.SortEvent;
import ua.epam.controller.commands.moderator.*;
import ua.epam.controller.commands.moderator.event.*;
import ua.epam.controller.commands.moderator.report.*;
import ua.epam.controller.commands.moderator.event.UpdateEventPage;
import ua.epam.controller.commands.moderator.report.suggestion.AcceptSuggestion;
import ua.epam.controller.commands.moderator.report.suggestion.DeclineSuggestion;
import ua.epam.controller.commands.moderator.report.suggestion.ModeratorSuggestReportsPage;
import ua.epam.controller.commands.speaker.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private static final Map<String, ICommand> commands = new HashMap<>();

    private CommandFactory() {
    }

    /**
     * Singleton.
     */
    public static CommandFactory commandFactory() {
        if (factory == null) factory = new CommandFactory();
        return factory;
    }

    static {
        // common commands
        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationPage());
        commands.put("registrationCommand", new RegistrationCommand());

        // admin commands
        commands.put("addUser", new AddUserCommand());
        commands.put("deleteUser", new DeleteUserCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("findUser", new FindUserCommand());
        commands.put("allUsers", new ShowAllUsersCommand());
        commands.put("updateUserPage", new GetUpdatePageCommand());

        // moderator commands
        commands.put("moderatorMenu", new ModeratorMenu());
        commands.put("moderatorAllSuggestReports", new ModeratorSuggestReportsPage());
        commands.put("acceptSuggestion", new AcceptSuggestion());
        commands.put("declineSuggestion", new DeclineSuggestion());

        commands.put("createEventMenu", new CreateEventPage());
        commands.put("createEvent", new CreateEvent());
        commands.put("deleteEvent", new DeleteEventCommand());
        commands.put("deleteReport", new ReportDeleteCommand());
        commands.put("infoEvent", new EventInfoPage());
        commands.put("updateEvent", new UpdateEventCommand());
        commands.put("updateEventPage", new UpdateEventPage());
        commands.put("createReport", new CreateReportCommand());
        commands.put("updateReportPage", new ReportUpdatePage());
        commands.put("updateReport", new UpdateReportCommand());
        commands.put("createReportPage", new CreateReportPage());
        commands.put("searchNameEvent", new SearchEvent());

        //common
        commands.put("sortEvent", new SortEvent());

        //speaker
        commands.put("speakerCabinet", new SpeakerCabinetPage());
        commands.put("speakerEventInfo", new SpeakerEventInfoPage());
        commands.put("suggestReport", new SuggestReportPage());
        commands.put("suggestReportCommand", new SuggestReportCommand());
        commands.put("speakerUpdateReportPage", new SpeakerUpdateReportPage());
        commands.put("speakerUpdateReportCommand", new SpeakerUpdateReportCommand());
        commands.put("allSuggestReports", new SpeakerAllSuggestReports());
        commands.put("deleteSuggestReport", new SpeakerDeleteSuggestReportCommand());

        // client commands
/*        commands.put("account", new AccountCommand());
        commands.put("personal_data", new PersonalDataCommand());
        commands.put("user_profile", new UserProfileCommand());
        commands.put("transactions", new TransactionCommand());
        commands.put("save_profile", new SaveUserProfileCommand());*/
    }

    public ICommand getCommand(HttpServletRequest req) {
        Object sessionAttribute = req.getAttribute("action");
        String requestAttribute = req.getParameter("action");

        if (Objects.equals(requestAttribute, "registration") || Objects.equals(requestAttribute, "registrationCommand"))
            return commands.get(requestAttribute);

        String action = (Objects.isNull(requestAttribute) || sessionAttribute == "login")
                ? String.valueOf(sessionAttribute) : requestAttribute;
        return commands.get(action);
    }
}
