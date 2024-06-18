package Security_App.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import Security_App.Models.User_Media;

public interface User_MediaRepository extends MongoRepository<User_Media, String> {
}