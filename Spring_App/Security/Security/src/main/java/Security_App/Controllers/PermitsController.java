package Security_App.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import Security_App.Models.Permits;
import Security_App.Repositories.PermitsRepository;


@CrossOrigin
@RestController
@RequestMapping("/permits")
public class PermitsController {
    @Autowired
    private PermitsRepository Repository_Permit;

    @GetMapping("")
    public List<Permits> index(){
        return this.Repository_Permit.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    
    @PostMapping
    public Permits create(@RequestBody Permits infoPermit){
        return this.Repository_Permit.save(infoPermit);
    }
    @GetMapping("{id}")
    public Permits show(@PathVariable String id){
        Permits ActualPermit=this.Repository_Permit
                .findById(id)
                .orElse(null);
        return ActualPermit;
    }
    @PutMapping("{id}")
    public Permits update(@PathVariable String id,@RequestBody  Permits infoPermit){
        Permits actualPermit=this.Repository_Permit
                .findById(id)
                .orElse(null);
        if(actualPermit!=null){
            actualPermit.setMethod(infoPermit.getMethod());
            actualPermit.setUrl(infoPermit.getUrl());
            return this.Repository_Permit.save(actualPermit);
        }else{
            return null;
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permits actualPermit=this.Repository_Permit
                .findById(id)
                .orElse(null);
        if (actualPermit!=null){
            this.Repository_Permit.delete(actualPermit);
        }
    }
    
}
