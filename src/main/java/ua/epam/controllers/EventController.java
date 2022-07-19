package ua.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.models.entities.Event;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.ReportService;
import ua.epam.services.IMailService;
import ua.epam.services.IStreamEventPool;

import javax.validation.Valid;

@Controller
@RequestMapping("/event")

public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    ReportService reportService;
    @Autowired
    IStreamEventPool eventPool;
    @Autowired
    IMailService mailService;

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model) {
        Event event = eventService.get(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("statistic", eventPool.getStatistic(id));
        model.addAttribute("event", event);
        model.addAttribute("entities", reportService.get(0, 100, event.getId()));
        model.addAttribute("user",  ((UserDetails) principal).getUsername());

        return "event/eventInfo";
    }

    @GetMapping("/new")
    public String createEvent(Model model) {
        model.addAttribute("entity", new Event());
        return "event/createEvent";
    }

    @PostMapping("/new")
    public String createEvent(@ModelAttribute("entity") @Valid Event event, BindingResult bindingResult) {
        if (eventService.isError(event, bindingResult))
            return "event/createEvent";

        eventService.save(event);
        return "redirect:/moderator";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("entity", eventService.get(id));
        return "event/updateEvent";
    }

    @PostMapping("{id}/edit")
    public String update(@ModelAttribute("entity") @Valid Event event, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (eventService.isError(event, bindingResult))
            return "event/createEvent";

        new Thread(()->mailService.sendEventChangeMessages(eventService.get(event.getId()))).start();
        eventService.update(id, event);
        return "redirect:/event/"+event.getId();
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") int id, Model model,
                         @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                         @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                         @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        //new Thread(()->mailService.sendEventRemovedMessages(eventService.get(id))).start();
        mailService.sendEventRemovedMessages(eventService.get(id));
        eventService.delete(id);

        return "redirect:/moderator?page=" + page + "&size=" + size + "&pattern=" + pattern + "&sortOrder=" + sortOrder + "&sortType=" + sortType;
    }

}
