package Security_App.Controllers;


import Security_App.Models.PersonalInfo;
import Security_App.Repositories.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/personal_info")
public class PersonalInfoController {
    @Autowired
    private PersonalInfoRepository Repository_PersonalInfo;

    @PostMapping
    public PersonalInfo create(@RequestBody PersonalInfo infoPersonal){
        return this.Repository_PersonalInfo.save(infoPersonal);
    }

    @GetMapping("{id}")
    public PersonalInfo show(@PathVariable String id){
        PersonalInfo personalInfoActual=this.Repository_PersonalInfo
                .findById(id)
                .orElse(null);
        return personalInfoActual;
    }

    @PutMapping("{id}")
    public PersonalInfo update(@PathVariable String id,@RequestBody PersonalInfo infoPersonal){
        PersonalInfo personalInfoActual=this.Repository_PersonalInfo
                .findById(id)
                .orElse(null);
        if (personalInfoActual!=null){
            personalInfoActual.setName(infoPersonal.getName());
            personalInfoActual.setE_mail(infoPersonal.getE_mail());
            return this.Repository_PersonalInfo.save(personalInfoActual);
        }else{
            return  null;
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PersonalInfo personalInfoActual=this.Repository_PersonalInfo
                .findById(id)
                .orElse(null);
        if (personalInfoActual!=null){
            this.Repository_PersonalInfo.delete(personalInfoActual);
        }
    }
}
