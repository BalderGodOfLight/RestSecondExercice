package firm.eonix.lespetitssinge.repository;

import firm.eonix.lespetitssinge.model.Personn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Personn , Integer> {

    List<Personn> findByNameContaining(String name);

    @Query(value = "SELECT max(id) FROM Personn")
    int getMaxId();

}
