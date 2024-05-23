package Security_App.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import Security_App.Models.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'e_mail': ?0}")
    public User getUserByEmail(String e_mail);
}
