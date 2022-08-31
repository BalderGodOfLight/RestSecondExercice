package firm.eonix.lespetitssinge.service;

import firm.eonix.lespetitssinge.model.Personn;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonnService {


    List<Personn> getAllPersonn();

    Personn findPersonnById(Integer id);

    List<Personn> getAllPersonnByNameAndFirstName(String name , String firstName);

    List<Personn> getAllPersonnLikeName(String name);

    boolean createPersonn(Personn personnTocreate);

    Boolean findIfPersonnExistById(Integer id);



    boolean updatePersonn(Personn personnTochange);

    boolean deletePersonn(int id);
}
