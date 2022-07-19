package ua.epam.dao.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.epam.dao.entities.report.IReportDao;
import ua.epam.models.Role;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ReportService {
    @Autowired
    private IReportDao reportDao;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    public List<Report> get(int page, int count, int eventId) {
        List<Report> reports = reportDao.read(page, count, eventId);
        for (Report report : reports)
            setSpeaker(report);
        return reports;
    }

    public Report get(int id) {
        return setSpeaker(reportDao.read(id));
    }

    public boolean save(Report report) {
        report.setSpeakerId(userService.getByLogin(report.getSpeakerName()).getId());
        return reportDao.create(report);
    }

    public void delete(int id,int eventId) {
        reportDao.delete(id);
    }

    public boolean update(int id, Report report) {
        User speaker = userService.getByLogin(report.getSpeakerName());
        report.setSpeakerId(speaker.getId());
        return reportDao.update(id, report);
    }

    public int count(String pattern) {
        return reportDao.count(pattern);
    }

    private Report setSpeaker(Report report) {
        User speaker = userService.get(report.getSpeakerId());
        report.setSpeaker(speaker);
        return report;
    }

    public boolean isNameExisted(String name, int id) {
        Report report = getByName(name);
        return nonNull(report) && report.getId() != id;
    }

    public Report getByName(String name) {
        return reportDao.getByName(name);
    }

    public boolean isError(Report report, BindingResult bindingResult) {
        boolean error = bindingResult.hasErrors();

        if (this.isNameExisted(report.getName(), report.getId())) {
            bindingResult.addError(new FieldError("entity", "name",
                    report.getName(), false, null, null,
                    messageSource.getMessage("error.report.name.exist",null, LocaleContextHolder.getLocale())));
            error = true;
        }

        User speaker = userService.getByLogin(report.getSpeakerName());
        if(!report.getSpeakerName().isEmpty()) {
            if (isNull(speaker)) {
                bindingResult.addError(new FieldError("entity", "speakerName",
                        report.getSpeakerName(), false, null, null,
                        messageSource.getMessage("error.user.not.exist",null, LocaleContextHolder.getLocale())));
                error = true;
            }

            if (nonNull(speaker) && !Objects.equals(speaker.getRole(), Role.SPEAKER.toString())) {
                bindingResult.addError(new FieldError("entity", "speakerName",
                        report.getSpeakerName(), false, null, null,
                        messageSource.getMessage("error.user.not.speaker",null, LocaleContextHolder.getLocale())));
                error = true;
            }
        }
        return error;
    }

}
