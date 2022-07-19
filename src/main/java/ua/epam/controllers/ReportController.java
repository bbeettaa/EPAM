package ua.epam.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.Role;
import ua.epam.models.entities.Report;
import ua.epam.dao.sevices.ReportService;
import ua.epam.services.IMailService;
import ua.epam.services.implementations.MyUserDetails;
import ua.epam.services.implementations.SuggestReportPool;

import javax.validation.Valid;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    SuggestReportPool reportPool;
    @Autowired
    IMailService mailService;

    private static final Logger logger
            = LoggerFactory.getLogger(ReportService.class);

    @GetMapping("/new/{eventId}")
    public String newPerson(@PathVariable("eventId") int eventId, Model model) {
        Report report = new Report();
        report.setEventId(eventId);

        if (getRole().equals(Role.SPEAKER))
            report.setSpeakerName(getCurrentUserLogin());

        model.addAttribute("entity", report);
        return "report/createReport";
    }

    @PostMapping("/new/{eventId}")
    public String create(@PathVariable("eventId") int eventId, @ModelAttribute("entity") @Valid Report entity, BindingResult bindingResult) {
        if (reportService.isError(entity, bindingResult))
            return "report/createReport";

        if (getRole().equals(Role.SPEAKER)) {
            entity.setSpeaker(userService.get(getCurrentUserId()));
            entity.setSpeakerId(entity.getSpeaker().getId());
            if (!reportPool.addSuggestion(entity)) {
                bindingResult.addError(new FieldError("entity", "name",
                        entity.getName(), false, null, null, "Name already existed"));
                return "report/createReport";
            }
        }
        if (getRole().equals(Role.MODERATOR)) {
            reportService.save(entity);
            new Thread(()->mailService.sendRegistrationReport(
                    userService.get(entity.getSpeakerId()),
                    entity
                    )).start();
        }
        return "redirect:/event/" + eventId;
    }

    @GetMapping("{id}/edit")
    public String editReport(Model model, @PathVariable("id") int id) {
        Report report = reportService.get(id);
        report.setSpeakerName(report.getSpeaker().getLogin());
        model.addAttribute("entity", report);
        return "report/updateReport";
    }

    @PostMapping("{id}/edit")
    public String updateReport(@ModelAttribute("entity") @Valid Report report, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (reportPool.isNameExist(report.getName())) {
            bindingResult.addError(new FieldError("entity", "name",
                    report.getName(), false, null, null, "Name already existed"));
        }

        if (reportService.isError(report, bindingResult))
            return "report/updateReport";

        reportService.update(id, report);
        return "redirect:/event/" + report.getEventId();
    }

    @PostMapping("/{id}/remove")
    public String deleteReport(@PathVariable("id") int id, Model model) {
        Report report = reportService.get(id);
        reportService.delete(id, report.getEventId());
        new Thread(() -> mailService.sendRemoveReport(
                report.getSpeakerId(),
                eventService.get(report.getEventId()).getName(),
                report.getName())).start();
        return "redirect:/event/" + report.getEventId();
    }

    private String getCurrentUserLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }

    private Role getRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails)
            return Enum.valueOf(Role.class, ((MyUserDetails) principal)
                    .getAuthorities()
                    .toArray()[0]
                    .toString()
                    .replace("ROLE_", ""));

        return Role.UNREGISTERED;
    }

    private int getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getByLogin(((UserDetails) principal).getUsername()).getId();
    }

}

