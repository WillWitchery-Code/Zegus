package Security_App.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import Security_App.Models.Rol;

public interface RolRepository extends MongoRepository<Rol, String>{
  
}
