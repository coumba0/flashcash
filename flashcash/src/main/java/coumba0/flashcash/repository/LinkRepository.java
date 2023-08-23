package coumba0.flashcash.repository;

import coumba0.flashcash.model.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {
    @Query(value = "SELECT c FROM Link c WHERE c.user1.mail= :mail")
    List<Link> findLinksByUser1Email(String mail);
}
