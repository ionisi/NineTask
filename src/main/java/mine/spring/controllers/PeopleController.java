package mine.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import mine.spring.models.User;
import mine.spring.service.UserService;
import mine.spring.util.UserValidator;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public PeopleController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", userService.index());
        return "people/index";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "people/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @RequestParam("id") int id) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        userService.update(id, user);
        return "redirect:/people";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/people";
    }
}
