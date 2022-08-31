package firm.eonix.lespetitssinge.repository;

import firm.eonix.lespetitssinge.model.Personn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Personn , Integer> {

    List<Personn> findByNameContaining(String name);

    @Query(value = "SELECT max(id) FROM Personn")
    int getMaxId();

    @Query(value = "SELECT * "
            + "FROM  Personn p "
            + "WHERE  p.Nom  like %?1% OR " +
            "  p.Prenom like %?2% ",
            nativeQuery = true)
    List<Personn> getPersonnByNameOrFirstName(String nom , String prenom);

//    @Query("Select c from Personn p where p.name like :name")
//    List<Personn> findByPlaceContaining(@Param("name") String place);

}
