package Security_App.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import Security_App.Models.Permits;
import Security_App.Models.Rol_Permits;
import Security_App.Models.Rol;
import Security_App.Repositories.PermitsRepository;
import Security_App.Repositories.RolRepository;
import Security_App.Repositories.Rol_PermitsRepository;

@CrossOrigin
@RestController
@RequestMapping("/permit_rol")

public class Rol_PermitsController {
    @Autowired
    private PermitsRepository Repository_Permit;
    @Autowired
    private RolRepository Repository_Rol;
    @Autowired
    private Rol_PermitsRepository Repository_Permits_Rol;

    @GetMapping("")
    public List<Rol_Permits> index(){
        return this.Repository_Permits_Rol.findAll();
    }

    /**
     * Rol - Permit
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permits/{id_permiso}")
    public Rol_Permits create(@PathVariable String id_rol,@PathVariable String id_permiso){
        Rol_Permits nuevo=new Rol_Permits();
        Rol elRol=this.Repository_Rol.findById(id_rol).get();
        Permits elPermiso=this.Repository_Permit.findById(id_permiso).get();
        if (elRol!=null && elPermiso!=null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.Repository_Permits_Rol.save(nuevo);
        }else{
            return null;
        }
    }

    @GetMapping("{id}")
    public Rol_Permits show(@PathVariable String id){
        Rol_Permits Actualp_m=this.Repository_Permits_Rol
                .findById(id)
                .orElse(null);
        return Actualp_m;
    }

    /**
     * Rol y Permit
     * @param id
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}/permit/{id_permit}")
    public Rol_Permits update(@PathVariable String id,@PathVariable String id_rol,@PathVariable String id_permiso){
        Rol_Permits actualPermitRol=this.Repository_Permits_Rol
                .findById(id)
                .orElse(null);
        Rol elRol=this.Repository_Rol.findById(id_rol).get();
        Permits elPermiso=this.Repository_Permit.findById(id_permiso).get();
        if(actualPermitRol!=null && elPermiso!=null && elRol!=null){
            actualPermitRol.setPermiso(elPermiso);
            actualPermitRol.setRol(elRol);
            return this.Repository_Permits_Rol.save(actualPermitRol);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Rol_Permits actualPermitRol=this.Repository_Permits_Rol
                .findById(id)
                .orElse(null);
        if (actualPermitRol!=null){
            this.Repository_Permits_Rol.delete(actualPermitRol);
        }
    }

    @GetMapping("validate_permit/rol/{id_rol}")
    public Permits getPermits(@PathVariable String id_rol,@RequestBody Permits infoPermit){
        Permits Permit_=this.Repository_Permit.getPermit(infoPermit.getUrl(), infoPermit.getMethod());
        Rol Rol_=this.Repository_Rol.findById(id_rol).get();
        if (Permit_!=null && Rol_!=null){
            return this.Repository_Permits_Rol.getPermit_Rol(Rol_.get_id(),Permit_.get_id());
        }else{
            return null;
        }
    }


} 