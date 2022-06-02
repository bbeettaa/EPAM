package ua.epam;


import org.apache.log4j.Logger;
import ua.epam.dao.EventRepo;
import ua.epam.dao.ReportRepo;
import ua.epam.dao.SubscribeRepo;
import ua.epam.dao.UserRepo;
import ua.epam.dao.utils.SpeakerRepo;

import java.util.concurrent.atomic.AtomicReference;

public class AppContext {
    private AppContext() {
        throw new IllegalStateException("Utility class");
    }


    public static final AtomicReference<SubscribeRepo> SUBSCRIBES_REPO = new AtomicReference<>(new SubscribeRepo());
    public static final AtomicReference<SpeakerRepo> SPEAKER_REPO = new AtomicReference<>(new SpeakerRepo());
    public static final AtomicReference<ReportRepo> REPORTS_REPO = new AtomicReference<>(new ReportRepo());
    public static final AtomicReference<EventRepo> EVENT_REPO = new AtomicReference<>(new EventRepo());
    public static final AtomicReference<UserRepo> USER_REPO = new AtomicReference<>(new UserRepo());
    public static final Logger LOGGER = Logger.getLogger(AppContext.class);
}
