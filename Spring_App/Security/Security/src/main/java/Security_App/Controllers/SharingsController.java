package Security_App.Controllers;

import Security_App.Models.Sharings;
import Security_App.Models.User;
import Security_App.Repositories.SharingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sharings")


public class SharingsController {

    @Autowired
    private SharingsRepository Repository_Sharing;

    @GetMapping("")
    public List<Sharings> index(){
        return this.Repository_Sharing.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping
    public Sharings create(@RequestBody Sharings infoSharing, String s_username){

        infoSharing.setSUsername(s_username);
        return this.Repository_Sharing.save(infoSharing);

    }
    @GetMapping("{id}")
    public Sharings show(@PathVariable String id){
        Sharings ActualSharing=this.Repository_Sharing
                .findById(id)
                .orElse(null);
        return ActualSharing;
    }
    @PutMapping("{id}")
    public Sharings update(@PathVariable String id,@RequestBody  Sharings infoSharing){
        Sharings actualSharing=this.Repository_Sharing
                .findById(id)
                .orElse(null);
        if(actualSharing!=null){
            actualSharing.setContent(infoSharing.getContent());
            return this.Repository_Sharing.save(actualSharing);
        }else{
            return null;
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Sharings actualSharing=this.Repository_Sharing
                .findById(id)
                .orElse(null);
        if (actualSharing!=null){
            this.Repository_Sharing.delete(actualSharing);
        }
    }

}
