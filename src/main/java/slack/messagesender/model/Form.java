package slack.messagesender.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Form {
    @NotBlank(message = "Name can't be empty.")
    private String name;

    @Email(message = "This should be correct e-mail address.")
    @NotBlank(message = "E-mail can't be empty.")
    private String email;

    @NotBlank(message = "Message can't be empty.")
    private String message;
}
