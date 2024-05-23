package Security_App.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import Security_App.Models.Permits;

public interface PermitsRepository extends MongoRepository<Permits, String>{
    @Query("{'url':?0,'method':?1}")
    Permits getPermit(String url, String method);
}
