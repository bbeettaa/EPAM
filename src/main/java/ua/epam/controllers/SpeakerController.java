package ua.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.ReportService;
import ua.epam.dao.sevices.UserService;
import ua.epam.services.IStreamEventPool;
import ua.epam.services.implementations.SuggestReportPool;

@Controller
@RequestMapping("/speaker")
public class SpeakerController {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    IStreamEventPool eventsPool;
    @Autowired
    SuggestReportPool reportPool;
    @Autowired
    ReportService reportService;

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                        @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                        @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        model.addAttribute("entities",
                eventService.get(page, size, pattern, sortOrder, sortType));

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) eventService.count(pattern) / size));

        return "speaker/speakerMenu";
    }

    @GetMapping("/suggestions")
    public String suggestedReports(Model model) {
        model.addAttribute("entities",  reportPool.getSuggestions(getCurrentUserId()));
        model.addAttribute("eventService",  eventService);
        return "report/suggestionsMenu";
    }

    @PostMapping("/suggestions/{name}")
    public String suggestedReportsDelete(Model model,@PathVariable("name") String name) {
        reportPool.declineByName(name,getCurrentUserId());
        return "redirect:/speaker/suggestions";
    }

    private int getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getByLogin(((UserDetails) principal).getUsername()).getId();
    }

}