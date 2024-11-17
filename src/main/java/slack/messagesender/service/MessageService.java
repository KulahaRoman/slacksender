package slack.messagesender.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import slack.messagesender.configuration.SlackBotConfiguration;
import slack.messagesender.dto.SendMessageDTO;
import slack.messagesender.model.Form;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MessageService {
    private final SlackBotConfiguration slackConfig;

    public MessageService(SlackBotConfiguration slackConfig) {
        this.slackConfig = slackConfig;
    }

    public void sendForm(Form form) throws Exception {
        String message = "Feedback:\n" +
                "Author: " +
                form.getName() +
                "\n" +
                "Email: " +
                form.getEmail() +
                "\n" +
                "Message: " +
                form.getMessage();

        var content = new SendMessageDTO();
        content.setChannelID(slackConfig.getChannelID());
        content.setMessage(message);

        var contentJSON = new ObjectMapper().writeValueAsString(content);

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(slackConfig.getSendMessageURL()))
                .setHeader("Authorization", "Bearer " + slackConfig.getToken())
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(contentJSON))
                .build();
        try (var httpClient = HttpClient.newHttpClient()) {
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() != 200) {
                throw new RuntimeException("Server return failure code.");
            }
        } catch (Exception exc) {
            throw new RuntimeException("Failed to send form: " + exc.getMessage());
        }
    }
}
