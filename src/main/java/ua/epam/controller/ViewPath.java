package ua.epam.controller;

/**
 * Path to view files (.jsp)
 */
public class ViewPath {
    private ViewPath() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ADMIN_MENU = "WEB-INF/view/admin/admin_menu.jsp";
    public static final String ALL_USERS_COMMAND = "controller?action=allUsers";
    public static final String FIND_USER_COMMAND = "controller?action=findUser";
    public static final String UPDATE_USER = "WEB-INF/view/common/update.jsp";
    public static final String LOGIN_MENU = "WEB-INF/view/common/login.jsp";
    public static final String LOGIN_COMMAND = "controller?action=login";
    public static final String REGISTRATION_PAGE = "WEB-INF/view/common/registrationUser.jsp";
    public static final String ERROR_PAGE = "WEB-INF/view/common/errorPage.jsp";

    public static final String MODERATOR_MENU = "WEB-INF/view/moderator/moderator_menu.jsp";
    public static final String MODERATOR_MENU_COMMAND = "controller?action=moderatorMenu";
    public static final String CREATE_EVENT = "WEB-INF/view/moderator/createEvent.jsp";
    public static final String EVENT_INFO = "WEB-INF/view/moderator/eventInfo.jsp";
    public static final String EVENT_INFO_COMMAND = "controller?action=infoEvent";
    public static final String EVENT_UPDATE = "WEB-INF/view/moderator/updateEventPage.jsp";
    public static final String REPORT_UPDATE_PAGE = "WEB-INF/view/moderator/updateReport.jsp";
    public static final String REPORT_CREATE_PAGE = "WEB-INF/view/moderator/createReport.jsp";
    public static final String MODERATOR_SUGGEST_REPORTS_PAGE = "WEB-INF/view/moderator/moderatorSuggestReports.jsp";
    public static final String MODERATOR_SUGGEST_REPORTS_COMMAND = "controller?action=moderatorAllSuggestReports";

    public static final String SPEAKER_MENU_PAGE = "WEB-INF/view/speaker/speakerMenu.jsp";
    public static final String SPEAKER_MENU_COMMAND = "controller?action=speakerCabinet";
    public static final String SPEAKER_EVENT_INFO = "WEB-INF/view/speaker/speakerEventInfo.jsp";
    public static final String SPEAKER_EVENT_INFO_COMMAND = "controller?action=speakerEventInfo";
    public static final String SPEAKER_SUGGEST_REPORT = "WEB-INF/view/speaker/suggestReport.jsp";
    public static final String SPEAKER_REPORT_UPDATE = "WEB-INF/view/speaker/speakerReportUpdatePage.jsp";
    public static final String SPEAKER_SUGGEST_REPORT_PAGE = "WEB-INF/view/speaker/allSuggestionReportPage.jsp";
    public static final String SPEAKER_ALL_SUGGEST_REPORT_COMMAND = "controller?action=allSuggestReports";

    public static final String USER_MENU_PAGE = "WEB-INF/view/user/userMenu.jsp";
    public static final String USER_MENU_COMMAND = "controller?action=userMenu";
    public static final String USER_EVENT_INFO = "WEB-INF/view/user/userEventInfo.jsp";
    public static final String USER_EVENT_INFO_COMMAND = "controller?action=speakerEventInfo";
    public static final String USER_SUBSCRIBES_MENU_PAGE = "WEB-INF/view/user/subscribesEventMenu.jsp";
    public static final String USER_SUBSCRIBES_MENU_COMMAND = "controller?action=subscribes";
    public static final String USER_EVENT_STREAM = "WEB-INF/view/user/event.jsp";

}
