package Security_App.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Security_App.Models.Rol;
import Security_App.Repositories.RolRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolRepository Repository_Rol;


    @GetMapping("")
    public List<Rol> index(){
        return this.Repository_Rol.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody  Rol infoRol){
        return this.Repository_Rol.save(infoRol);
    }
    @GetMapping("{id}")
    public Rol show(@PathVariable String id){
        Rol rolActual=this.Repository_Rol
                .findById(id)
                .orElse(null);
        return rolActual;
    }
    @PutMapping("{id}")
    public Rol update(@PathVariable String id,@RequestBody  Rol infoRol){
        Rol rolActual=this.Repository_Rol
                .findById(id)
                .orElse(null);
        if (rolActual!=null){
            rolActual.setName(infoRol.getName());
            return this.Repository_Rol.save(rolActual);
        }else{
            return  null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Rol rolActual=this.Repository_Rol
                .findById(id)
                .orElse(null);
        if (rolActual!=null){
            this.Repository_Rol.delete(rolActual);
        }
    }
}