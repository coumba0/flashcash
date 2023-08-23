package coumba0.flashcash.repository;

import coumba0.flashcash.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value="SELECT u FROM User u LEFT JOIN FETCH u.links WHERE u.mail=:mail")
    public Optional<User> findUserByMail(String mail);

}
