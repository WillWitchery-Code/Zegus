package Security_App.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import Security_App.Models.Permits;
import Security_App.Models.Rol_Permits;

public interface Rol_PermitsRepository extends MongoRepository<Rol_Permits,String>{ 
    @Query("{'rol.$id': ObjectId(?0),'permit.$id': ObjectId(?1)}")
    Permits getPermit_Rol(String id_rol,String id_permit);
}
