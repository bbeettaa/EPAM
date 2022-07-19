package ua.epam.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/moderator")
public class ModeratorController {
    @Autowired
    EventService eventService;
    @Autowired
    ReportService reportService;
    @Autowired
    IStreamEventPool eventPool;
    @Autowired
    UserService userService;
    @Autowired
    SuggestReportPool reportPool;

    private static final Logger logger
            = LoggerFactory.getLogger(ModeratorController.class);

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                        @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                        @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        model.addAttribute("entities", eventService.get(page, size, pattern,
                sortOrder, sortType));

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) eventService.count(pattern) / size));

        return "moderator/moderatorMenu";
    }

    @PostMapping("/{id}/start")
    public String start(@PathVariable("id") int id, Model model) {
        eventPool.start(id);
        return "redirect:/event/"+id;
    }

    @PostMapping("/{id}/stop")
    public String stop(@PathVariable("id") int id, Model model) {
        eventPool.stop(id);
        return "redirect:/event/"+id;
    }

    @GetMapping("/suggestions")
    public String suggestedReports(Model model) {
        model.addAttribute("entities",  reportPool.getSuggestions());
        model.addAttribute("eventService",  eventService);


        return "report/suggestionsMenu";
    }

    @PostMapping("/suggestions/decline/{name}")
    public String suggestedReportsDecline(Model model,@PathVariable("name") String name) {
        reportPool.declineByName(name);
        return "redirect:/moderator/suggestions";
    }

    @PostMapping("/suggestions/accept/{name}")
    public String suggestedReportsAccept(Model model,@PathVariable("name") String name) {
        reportPool.acceptByName(name);
        return "redirect:/moderator/suggestions";
    }
}
