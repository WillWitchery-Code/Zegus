package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()

public class Sharings {

    @Id
    private String id;

    private String content;
    private String s_username;

    public Sharings(){
        this.content = content;
    }

    public String getContent(String s_username) {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String _id) {
        this.id = _id;
    }

    public String getSUsername() {
        return s_username;
    }

    public void setSUsername(String s_username) {
       this.s_username = s_username;
    }
}



