package Security_App.Repositories;

import Security_App.Models.Permits;
import Security_App.Models.PersonalInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonalInfoRepository extends MongoRepository<PersonalInfo,String> {

    public PersonalInfo getPersonalInfoByName(String name);

}
