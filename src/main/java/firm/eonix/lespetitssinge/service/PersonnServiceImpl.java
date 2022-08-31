package firm.eonix.lespetitssinge.service;

import firm.eonix.lespetitssinge.model.Personn;
import firm.eonix.lespetitssinge.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class PersonnServiceImpl implements PersonnService {

    @Autowired
    private PersonRepository repository ;


    @Override
    public List<Personn> getAllPersonn() {
        return repository.findAll();
    }

    @Override
    public Personn findPersonnById(Integer id) {
      Optional<Personn> optionalPersonn =  this.repository.findById(id);
      if(optionalPersonn.isPresent()){
          return optionalPersonn.get();
      }
      else{
          return null ;
      }
    }

    @Override
    public List<Personn> getAllPersonnByNameAndFirstName(String name, String firstName) {
        System.out.println(name);
        return repository.getPersonnByNameOrFirstName(name, firstName);
    }



    public List<Personn> getAllPersonnLikeName(String name) {
        return this.repository.findByNameContaining(name);
    }

    @Override
    public boolean createPersonn(Personn personnTocreate) {
        // code inutile car auto-increment model et database mais debug Mysql trop long
        int maxId = 0;
        try{
            maxId = this.repository.getMaxId();
        }catch(Exception e){
           e.printStackTrace();
           System.out.println(e.getMessage());
        }
        if(personnTocreate.id == null){
            maxId++;
            personnTocreate.id = maxId ;
            System.out.println(personnTocreate.id);
        }
        Boolean ifExistAlready = findIfPersonnExistById(personnTocreate.id);
        System.out.println(ifExistAlready);
        if (ifExistAlready) {
            return false ;
        }
        System.out.println("repere 4 ");
        this.repository.save(personnTocreate);
        Optional<Personn> personsearch = this.repository.findById(personnTocreate.id);
        if(personsearch.isPresent()){
            return true ;
        }
        return false;
    }

    @Override
    public Boolean findIfPersonnExistById(Integer id) {
        return  this.repository.existsById(id);
    }

    @Override
    public boolean updatePersonn(Personn personnTochange) {
        try {
           Personn personsearch =  this.repository.saveAndFlush(personnTochange);
           if(personsearch.equals(personnTochange)){
               return true ;
           }
           else{
               return false ;
           }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false ;
        }

    }

    @Override
    @Transactional
    public boolean deletePersonn(int id) {
        Personn personn = this.findPersonnById(id);
        if(personn == null){
            return false ;
        }
        else {
            this.repository.delete(personn);
            return true ;
        }

    }
}
