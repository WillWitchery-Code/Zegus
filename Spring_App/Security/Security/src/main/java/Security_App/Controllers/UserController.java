package Security_App.Controllers;
import Security_App.Models.Rol;
import Security_App.Models.User;
import Security_App.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import Security_App.Repositories.RolRepository;

//security 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//Decorators
@CrossOrigin
@RestController
@RequestMapping("/users")
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
public class UserController {
    @Autowired
    private UserRepository Repository_User;


    
    @GetMapping("")
    public List<User> index(){
        return this.Repository_User.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User UsersInfo){
        UsersInfo.setPassword(convertirSHA256(UsersInfo.getPassword()));
        return this.Repository_User.save(UsersInfo);
    }

    @GetMapping("{id}")
    public User show(@PathVariable String id){
        User ActualUser=this.Repository_User
            .findById(id)
            .orElse(null);
        return ActualUser;
    }

    @PutMapping("{id}")
    public User update(@PathVariable String id,@RequestBody User UsersInfo){
        User ActualUser=this.Repository_User
            .findById(id)
            .orElse(null);
        if (ActualUser!=null){
            ActualUser.setUsername(UsersInfo.getUsername());
            ActualUser.setPassword(convertirSHA256(UsersInfo.getPassword()));
            return this.Repository_User.save(ActualUser);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        User ActualUser=this.Repository_User
            .findById(id)
            .orElse(null);
        if (ActualUser!=null){
            this.Repository_User.delete(ActualUser);
        }
    }

        /**
    * Relation (1 to n) rol - user
    * @param id
    * @param id_rol
    * @return
    */
    @PutMapping("{id}/rol/{id_rol}")
    public User asignRol_Usuario(@PathVariable String id,@PathVariable String id_rol){
        User actualUser=this.Repository_User.findById(id).orElseThrow(RuntimeException::new);
        // Rol rolActual=this.Repository_Rol.findById(id_rol).orElseThrow(RuntimeException::new);
        //actualUser.setRol(rolActual);
        return this.Repository_User.save(actualUser);
        }

    
    @PostMapping("/validate")
    public User validate(@RequestBody User infoUser, final HttpServletResponse response) throws IOException {
        User actualUser=this.Repository_User.getUserByUserName(infoUser.getUsername());
        if (actualUser!=null && actualUser.getPassword().equals(convertirSHA256(infoUser.getPassword()))) {
            actualUser.setPassword("");
            return actualUser;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
        }
    }

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}