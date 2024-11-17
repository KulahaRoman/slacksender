package slack.messagesender.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class SlackBotConfiguration {
    private String token;
    private String channelID;
    private String sendMessageURL;

    public SlackBotConfiguration(@Value("${slack.token}") String token,
                                 @Value("${slack.channel.id}") String channelID,
                                 @Value("${slack.endpoint.send-message}") String sendMessageURL) {
        this.token = token;
        this.channelID = channelID;
        this.sendMessageURL = sendMessageURL;
    }
}
