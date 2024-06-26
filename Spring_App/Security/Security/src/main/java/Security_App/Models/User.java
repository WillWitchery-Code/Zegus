package Security_App.Models;
import Security_App.Repositories.PersonalInfoRepository;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "users")
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
public class User {
    @Id
    private String _id;


    private String username;
    private String password;

    @DBRef 
    private Rol rol;
    @DBRef
    private PersonalInfo personalInfo;
    @DBRef
    private User_Media user_media;


    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }
            // Getters - Sters 
    public String get_id() {return _id;}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol){
        this.rol = rol;
    }

    public PersonalInfo getpersonalInfo() {return personalInfo;}
    public void setpersonalInfo(PersonalInfo personalInfo) {this.personalInfo = personalInfo;}

    public User_Media getuser_media() {return user_media;}
    public void setuser_media(User_Media user_media) {this.user_media = user_media;}
}
        