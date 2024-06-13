package btl.Repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import btl.Entities.*;

@Repository
public class PersonRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person save(Person person) {
        String sql = "INSERT INTO person (id_person, username, password, role, active, email) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, person.getPersonId(), person.getUsername(), person.getPassword(),
        		person.getRole(), person.getActive(), person.getEmail());
        return person;
    }
    
    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        List<Person> persons = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Person(
                        rs.getLong("id_person"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getLong("active"),
                        rs.getString("email")
                ));
        return persons;
    }
    
    @SuppressWarnings("deprecation")
	public Person findByPersonId(long id) {
        String sql = "SELECT * FROM person WHERE id_person = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Person(
                        rs.getLong("id_person"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getLong("active"),
                        rs.getString("email")
                ));
    }

    public void delete(Person person) {
        String sql = "DELETE FROM sold WHERE id_person = ?";
        jdbcTemplate.update(sql, person.getPersonId());
        sql = "DELETE FROM person WHERE id_person = ?";
        jdbcTemplate.update(sql, person.getPersonId());
    }
    
    public void activeOff(long id) {
    	String sql = "UPDATE person SET active = 0 WHERE id_person > ?";
        jdbcTemplate.update(sql, id);
    }

    public void activeOn(long id) {
    	String sql = "UPDATE person SET active = 1 WHERE id_person = ?";
        jdbcTemplate.update(sql, id);
    }
    
    @SuppressWarnings("deprecation")
	public Person findActivePerson(long id) {
        String sql = "SELECT * FROM person WHERE active = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
	        new Person(
	                rs.getLong("id_person"),
	                rs.getString("username"),
	                rs.getString("password"),
	                rs.getString("role"),
	                rs.getLong("active"),
                    rs.getString("email")
	        ));	
    }
    
    public Person changePassword(Person person) {
        String sql = "UPDATE person SET username = ? WHERE id_person = ?";
        jdbcTemplate.update(sql, person.getUsername(), person.getPersonId());
        sql = "UPDATE employee SET password = ? WHERE id_person = ?";
        jdbcTemplate.update(sql, person.getPassword(), person.getPersonId());
        return person;
    }
    
}