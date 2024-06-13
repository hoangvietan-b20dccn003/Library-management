package btl.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import btl.Entities.*;
import btl.Repository.*;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepo;

    public Person save(Person person) {
        return personRepo.save(person);
    }
    
    public List<Person> getAll() {
        return personRepo.findAll();
    }

    public void delete(Person person) {
        personRepo.delete(person);
    }
    
    public Person findByPersonId(long id) {
    	return personRepo.findByPersonId(id);
    }
    
    public void activeOff(long id) {
        personRepo.activeOff(id);
    }
    
    public void activeOn(long id) {
        personRepo.activeOn(id);
    }
    
    public Person findActiveUser(long id) {
    	return personRepo.findActivePerson(id);
    }
    
//    public Person changePassword(Person person) {
//    	return personRepo.changePassword(person);
//    }
}