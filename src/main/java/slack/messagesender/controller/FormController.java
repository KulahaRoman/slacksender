package slack.messagesender.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import slack.messagesender.model.Form;
import slack.messagesender.service.MessageService;

@Controller
public class FormController {
    private final MessageService messageService;

    public FormController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/form")
    public String getForm(Model model) {
        model.addAttribute("form", new Form());
        return "form";
    }

    @PostMapping("/form")
    public String sendForm(@Valid @ModelAttribute("form") Form form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        try {
            messageService.sendForm(form);
        } catch (Exception exc) {
            return "fail";
        }

        return "success";
    }
}
