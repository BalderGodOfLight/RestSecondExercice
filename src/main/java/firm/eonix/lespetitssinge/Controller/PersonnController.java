package firm.eonix.lespetitssinge.Controller;


import firm.eonix.lespetitssinge.model.Personn;
import firm.eonix.lespetitssinge.service.PersonnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/eonix")
public class PersonnController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonnController.class);

    @Autowired
    private PersonnService service ;

    @RequestMapping(value = "/Persons", method = RequestMethod.GET)
   public  ResponseEntity getAllPersonn() {
        List<Personn>  personns = service.getAllPersonn();
        if ( personns.isEmpty()) {
            return new ResponseEntity<>(
                    "no data in the database",
                    HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().body(personns);
    }

    @RequestMapping(value = "/searchByName", method = RequestMethod.GET)
    public  ResponseEntity getAllPersonsByName(
            @RequestParam(name ="prenom") String firstName,
            @RequestParam(name = "nom") String name )  {
    
        List<Personn>  personns = service.getAllPersonnByNameAndFirstName(name , firstName);
        if ( personns.isEmpty()) {
            return new ResponseEntity<>(
                    "no data in the database",
                    HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().body(personns);
    }

    @RequestMapping(value = "/findPersonnById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personn> findAPersonn(@RequestParam(name = "id") int id) {
        System.out.println(" controller id request " + id);
        Personn personn = this.service.findPersonnById(id);
        System.out.println("controller search " + personn.toString());
        if (personn != null) {
            return ResponseEntity.ok(personn);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @RequestMapping(value = {"/createPersonn"},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPersonn(@RequestBody Personn personnTocreate) {

            boolean validDAO = service.createPersonn(personnTocreate);
            if (validDAO) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(409).build();
            }
    }

    @RequestMapping(value = {"/updatePersonn/"},
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePersonn(@RequestBody  Personn personnTochange) {

        Boolean ifExistAlready = this.service.findIfPersonnExistById(personnTochange.id);
        if (ifExistAlready) {
            boolean validDAO = service.updatePersonn(personnTochange);
            if (validDAO) {
                return ResponseEntity.ok().body(personnTochange);
            } else {
                return ResponseEntity.status(409).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not existing Personn");
        }
    }


    @RequestMapping(value = {"deletePersonn"},
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePersonn(@RequestParam(name = "id") int id) {

        Boolean ifExistAlready = this.service.findIfPersonnExistById(id);
        if (ifExistAlready) {
            boolean valid = service.deletePersonn(id);
            if (valid) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(409).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not existing Personn");
        }
    }










}
