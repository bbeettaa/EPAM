package ua.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.epam.models.Role;
import ua.epam.dao.sevices.UserService;

@Controller
@RequestMapping("")
public class MenuController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String menu(@RequestParam(value = "name",required = false) String name, Model model){
        model.addAttribute("name",name);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Role role = Enum.valueOf(Role.class,userService.getRoleByLoginPassword(
                    ((UserDetails) principal).getUsername(),
                    ((UserDetails) principal).getPassword()
            ));

            switch (role){
                case ADMIN:
                    return "redirect:/admin";
                case MODERATOR:
                    return "redirect:/moderator";
                case SPEAKER:
                    return "redirect:/speaker";
                case USER:
                    return "redirect:/user";

            }
            /*String email = ((UserDetails) principal).getUsername();
            User user = userService.findUserByLogin(email).get();
            product.setUser(user);
            product.setDeleted(false);
            productService.updateProduct(product);
            logger.info("edit product with id = " + product.getId());*/
        }

        return "redirect:/login";
    }

}
