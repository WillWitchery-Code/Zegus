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
    private PersonalInfo id_personal_info;


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

    public PersonalInfo getID_personal_info() {return id_personal_info;}
    public void setID_personal_info(PersonalInfo piUser) {this.id_personal_info = piUser;}
}
        