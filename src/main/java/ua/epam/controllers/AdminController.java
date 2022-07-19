package ua.epam.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.user.IUserDao;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.entities.User;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    private static final Logger logger
            = LoggerFactory.getLogger(AdminController.class);

    @GetMapping()
    public String index(Model model, Locale locale,
                        @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                        @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IUserDao.SortOrder sortOrder,
                        @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        page--;

        model.addAttribute("persons", userService.get(page, size, pattern,
                sortOrder, sortType));

        model.addAttribute("pattern", pattern);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("sortType", sortType);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("count", (int) Math.ceil((double) userService.count(pattern) / size));

        return "admin/userList";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", userService.get(id));
        return "admin/personInfo";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new User());
        return "admin/createPerson";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid User person, BindingResult bindingResult) {
        if (userService.isError(person, bindingResult))
            return "admin/createPerson";

        userService.save(person);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userService.get(id));
        return "admin/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("person") @Valid User person, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (userService.isError(person, bindingResult))
            return "admin/edit";

        userService.update(id, person);
        return "redirect:/admin";
    }


    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") int id, Model model,
                         @RequestParam(value = "pattern", required = false, defaultValue = "") String pattern,
                         @RequestParam(value = "sortOrder", required = false, defaultValue = "ID") IUserDao.SortOrder sortOrder,
                         @RequestParam(value = "sortType", required = false, defaultValue = "ASC") IEntityDao.SortType sortType,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        userService.delete(id);


        return "redirect:/admin?page=" + page + "&size=" + size + "&pattern=" + pattern + "&sortOrder=" + sortOrder + "&sortType=" + sortType;
    }
}
