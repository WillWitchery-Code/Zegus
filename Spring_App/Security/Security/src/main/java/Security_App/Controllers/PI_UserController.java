package Security_App.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import Security_App.Models.PI_User;
import Security_App.Models.PersonalInfo;
import Security_App.Models.User;
import Security_App.Repositories.PI_UserRepository;
import Security_App.Repositories.PersonalInfoRepository;
import Security_App.Repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/user_pi")

public class PI_UserController {
    @Autowired
    private UserRepository Repository_User;
    @Autowired
    private PersonalInfoRepository Repository_PI;
    @Autowired
    private PI_UserRepository Repository_PI_User;

    @GetMapping("")
    public List<PI_User> index(){
        return this.Repository_PI_User.findAll();
    }

    /**
     * User - PersonalInfo
     * @param id_user
     * @param id_pi
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("user/{id_user}/pi/{id_pi}")
    public PI_User create(@PathVariable String id_user,@PathVariable String id_pi){
        PI_User nuevo=new PI_User();
        User elUser=this.Repository_User.findById(id_user).get();
        PersonalInfo elPI=this.Repository_PI.findById(id_pi).get();
        if (elUser!=null && elPI!=null){
            nuevo.setPersonal_info(elPI);
            nuevo.setUser(elUser);
            return this.Repository_PI_User.save(nuevo);
        }else{
            return null;
        }
    }

    @GetMapping("user/{id_user}/pi/{id_pi}")
    public PI_User getPI_User(@PathVariable String id_user, @PathVariable String id_pi){
        List<PI_User> pi_users = this.Repository_PI_User.findAll();
        for (PI_User pi_user : pi_users) {
            if (pi_user.getUser().get_id().equals(id_user) && pi_user.getPersonal_info().get_id().equals(id_pi)) {
                return pi_user;
            }
        }
        return null;
    }
}
