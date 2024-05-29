package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document()
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
public class User {
    @Id
    private String _id;
    private String username;
    private String password;

    @DBRef 
    private Rol rol;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
            // Getters - Sters 
    public String get_id() {
        return _id;
    }
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
    public void setRol(Rol rol){
        this.rol = rol;
    }
    public Rol getRol() {
        return rol;
    }
}
        