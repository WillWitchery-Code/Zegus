package Security_App.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import Security_App.Models.PersonalInfo;
import Security_App.Models.PI_User;

public interface PI_UserRepository extends MongoRepository<PI_User,String>{
    @Query("{'user.$id': ObjectId(?0), 'personal_info.$id': ObjectId(?1)}")
    PersonalInfo getPersonalInfo(String id_user, String id_pi);
}
