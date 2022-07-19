package ua.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.dao.entities.subscribes.ISubscribesDao;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.SubscriptionsService;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.entities.Subscriptions;
import ua.epam.services.IStreamEventPool;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    SubscriptionsService subscriptionsService;
    @Autowired
    IStreamEventPool eventsPool;

    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                        @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                        @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        model.addAttribute("entities",
                eventService.getWithSubscribes(page, size, getCurrentUserId(), pattern, sortOrder, sortType));

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) eventService.count(pattern) / size));

        return "user/userMenu";
    }

    @GetMapping("/subscriptions")
    public String subscribes(Model model,
                             @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                             @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") ISubscribesDao.SortOrder sortOrder,
                             @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                             @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;


        model.addAttribute("entities",
                subscriptionsService.get(page, size, getCurrentUserId(), pattern, sortOrder, sortType));

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double)subscriptionsService.subscriptionsCount( getCurrentUserId()) / size));
        model.addAttribute("pageName", "subscriptions");

        return "user/subscribesEventMenu";
    }

    @PostMapping("/{id}/subscribe")
    public String subscribe(HttpServletRequest request, Model model, @PathVariable("id") int eventId,
                            @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                            @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                            @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        subscriptionsService.create(new Subscriptions(0, getCurrentUserId(), eventId));

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", ++page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) eventService.count(pattern) / size));

        return "redirect:/user/?page=" + page + "&size=" + size + "&pattern=" + pattern + "&sortOrder=" + sortOrder + "&sortType=" + sortType;
    }

    @PostMapping("subscriptions/{id}/unsubscribe")
    public String subscriptionsSubscribe(HttpServletRequest request, Model model, @PathVariable("id") int eventId,
                                         @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                                         @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                                         @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        subscriptionsService.delete(eventId, getCurrentUserId());

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", ++page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) eventService.count(pattern) / size));

        return "redirect:/user/subscriptions?page=" + page + "&size=" + size + "&pattern=" + pattern + "&sortOrder=" + sortOrder + "&sortType=" + sortType;
    }

    @PostMapping("{id}/unsubscribe")
    public String unsubscribe(HttpServletRequest request, Model model, @PathVariable("id") int eventId,
                              @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                              @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IEventDao.SortOrder sortOrder,
                              @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        subscriptionsService.delete(eventId, getCurrentUserId());

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder.toString());
        model.addAttribute("sortType", sortType.toString());
        model.addAttribute("page", ++page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) eventService.count(pattern) / size));

        return "redirect:/user/?page=" + page + "&size=" + size + "&pattern=" + pattern + "&sortOrder=" + sortOrder + "&sortType=" + sortType;
    }

    @PostMapping("{id}/connect")
    public String connect(HttpServletRequest request, Model model, @PathVariable("id") int eventId) {
        //subscriptionsService.delete(eventId, getCurrentUserId());
        eventsPool.connect(eventId);
        return "user/eventPage";
    }

    private int getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getByLogin(((UserDetails) principal).getUsername()).getId();
    }

}
