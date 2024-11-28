package whatsapp.backend.whatsapp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Component
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private  String username;
    private String number;
    private String profile;
//    private byte[] profile;
//    private String profileType;
//    public String getFileBase64() {
//        return this.profile != null ? Base64.getEncoder().encodeToString(this.profile) : null;
//    }

}

