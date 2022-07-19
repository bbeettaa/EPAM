package ua.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.epam.dao.sevices.UserService;
import ua.epam.models.entities.User;
import ua.epam.services.IMailService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    IMailService mailService;

    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("person", new User());
        return "registration";
    }

    @PostMapping("")
    public String create(@ModelAttribute("person") @Valid User person, BindingResult bindingResult) {
        if (userService.isError(person, bindingResult))
            return "registration";

        userService.save(person);
        new Thread(()->mailService.sendGreeting(person)).start();
        return "redirect:/";
    }
}
