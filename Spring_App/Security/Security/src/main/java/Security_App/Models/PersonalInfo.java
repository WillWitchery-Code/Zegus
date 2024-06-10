package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()

public class PersonalInfo {
    @Id
    private String _id;
    private String name;
    private String e_mail;

    public PersonalInfo(){
        this.name = name;
        this.e_mail = e_mail;
    }


    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
}
