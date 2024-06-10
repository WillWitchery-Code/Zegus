package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PI_User {
    @Id
    private String _id;
    @DBRef
    private User user;
    @DBRef
    private PersonalInfo personal_info;

    public PI_User() {
    }
    public String get_id() {return _id;}
    public User getUser() {return user;}
    public PersonalInfo getPersonal_info() {return personal_info;}
    public void setUser(User user) {this.user = user;}
    public void setPersonal_info(PersonalInfo personal_info) {this.personal_info = personal_info;}

}
