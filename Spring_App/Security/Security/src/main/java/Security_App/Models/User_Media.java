package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.client.gridfs.model.GridFSFile;

import java.io.File;

@Data
@Document(collection = "user_media")
public class User_Media {
    @Id
    private String _id;

    private File profile_picture;
}
