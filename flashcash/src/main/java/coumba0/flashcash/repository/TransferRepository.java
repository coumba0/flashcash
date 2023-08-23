package coumba0.flashcash.repository;

import coumba0.flashcash.model.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Long> {
    @Query("SELECT a FROM Transfer a WHERE a.id= :id")
    List<Transfer> findTransferByUserId(Integer id);
}
