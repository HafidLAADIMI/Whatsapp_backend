package whatsapp.backend.whatsapp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Message {
    private String id;
    private String senderId;
    private String receiverId;
    private String message;
    private Status status;
    private Date timestamp;
}
