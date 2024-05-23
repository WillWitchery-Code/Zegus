package Security_App.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Rol_Permits {
    @Id
    private String _id;
    @DBRef
    private Rol rol;
    @DBRef
    private Permits permit;

    public Rol_Permits() {
    }
    public String get_id() {
        return _id;
    }
    public Rol getRol() {
        return rol;
    }
    public Permits getPermit() {
        return permit;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public void setPermiso(Permits permiso) {
        this.permit = permiso;
    }
}
