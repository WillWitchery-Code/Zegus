package Security_App.Controllers;
import Security_App.Models.User;
import Security_App.Repositories.PI_UserRepository;
import Security_App.Repositories.PersonalInfoRepository;
import Security_App.Repositories.UserRepository;
import Security_App.Models.PersonalInfo;


//security
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.ResponseEntity;
//Decorators
@CrossOrigin
@RestController
@RequestMapping("/users")
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
public class UserController {
    @Autowired
    private UserRepository Repository_User;
    @Autowired
    private PersonalInfoRepository Repository_PI;

    @GetMapping("")
    public List<User> index(){
        return this.Repository_User.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping("/add")
    public User create(@RequestBody User UsersInfo, final HttpServletResponse response) throws IOException {
        User existingUser = this.Repository_User.getUserByUserName(UsersInfo.getUsername());

        if (existingUser != null) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "User with this username already exists");
            return null;
        }
        PersonalInfo newPersonalInfo = new PersonalInfo();
        newPersonalInfo = Repository_PI.save(newPersonalInfo);
        UsersInfo.setID_personal_info(newPersonalInfo);



        UsersInfo.setPassword(convertirSHA256(UsersInfo.getPassword()));
        response.sendError(HttpServletResponse.SC_CREATED, "User created");
        return this.Repository_User.save(UsersInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable String id) {
        Optional<User> user = Repository_User.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable String id, @RequestBody User userDetails) {
        Optional<User> user = Repository_User.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setPassword(convertirSHA256(userDetails.getPassword()));
            existingUser.setID_personal_info(userDetails.getID_personal_info());
            Repository_User.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
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

