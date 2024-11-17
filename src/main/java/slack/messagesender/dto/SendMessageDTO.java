package slack.messagesender.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendMessageDTO {
    @JsonProperty("channel")
    private String channelID;
    @JsonProperty("text")
    private String message;
}
