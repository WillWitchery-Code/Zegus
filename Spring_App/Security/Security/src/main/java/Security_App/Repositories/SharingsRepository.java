package Security_App.Repositories;
import Security_App.Models.PersonalInfo;
import Security_App.Models.Sharings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SharingsRepository extends MongoRepository<Sharings,String>{
    public Sharings getSharingsById(String id);
}
