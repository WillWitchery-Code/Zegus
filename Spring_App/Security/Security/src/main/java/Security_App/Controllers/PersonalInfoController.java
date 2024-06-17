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
        if (personalInfoActual != null) {
            if (infoPersonal.getName() != "") {
                personalInfoActual.setName(infoPersonal.getName());
            }
            if (infoPersonal.getLast_name() != "") {
                personalInfoActual.setLast_name(infoPersonal.getLast_name());
            }
            if (infoPersonal.getDob() != "") {
                personalInfoActual.setDob(infoPersonal.getDob());
            }
            if (infoPersonal.getE_mail() != "") {
                personalInfoActual.setE_mail(infoPersonal.getE_mail());
            }
            if (infoPersonal.getPhone_number() != "") {
                personalInfoActual.setPhone_number(infoPersonal.getPhone_number());
            }
            if (infoPersonal.getProfession() != "") {
                personalInfoActual.setProfession(infoPersonal.getProfession());
            }
            if (infoPersonal.getEducation() != "") {
                personalInfoActual.setEducation(infoPersonal.getEducation());
            }
            if (infoPersonal.getDescription() != "") {
                personalInfoActual.setDescription(infoPersonal.getDescription());
            }
            if (infoPersonal.getLink1() != "") {
                personalInfoActual.setLink1(infoPersonal.getLink1());
            }
            if (infoPersonal.getLink2() != "") {
                personalInfoActual.setLink2(infoPersonal.getLink2());
            }
            if (infoPersonal.getLink3() != "") {
                personalInfoActual.setLink3(infoPersonal.getLink3());
            }
            if (infoPersonal.getLink4() != "") {
                personalInfoActual.setLink4(infoPersonal.getLink4());
            }
            if (infoPersonal.getCountry() != "") {
                personalInfoActual.setCountry(infoPersonal.getCountry());
            }
            if (infoPersonal.getCity() != "") {
                personalInfoActual.setCity(infoPersonal.getCity());
            }
            if (infoPersonal.getAddress() != "") {
                personalInfoActual.setAddress(infoPersonal.getAddress());
            }
            if (infoPersonal.getZip_code() != "") {
                personalInfoActual.setZip_code(infoPersonal.getZip_code());
            }


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
