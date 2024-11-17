package slack.messagesender.service;

import org.springframework.stereotype.Service;
import slack.messagesender.model.Form;

@Service
public class MessageService {
    public void sendForm(Form form) throws Exception {
        // here we implement needed logic of processing form fields
        System.out.println("User submitted form: " + form);
    }
}
