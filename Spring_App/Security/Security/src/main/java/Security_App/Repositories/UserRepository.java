package Security_App.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import Security_App.Models.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'username': ?0}")
    public User getUserByUserName(String username);
}
