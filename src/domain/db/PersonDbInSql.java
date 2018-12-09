package domain.db;

import domain.model.Person;
import domain.model.Role;

import java.sql.*;
import java.util.*;

//Admin ww = aqsz

public class PersonDbInSql implements PersonDb {
	private Properties properties;
	private String url;

	public PersonDbInSql(Properties properties) {
		this.properties=properties;
		this.url=properties.getProperty("url");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DbException(e);
		}
	}
	
	@Override
    public Person get(String personId) throws DbException {
		if(personId == null){
			throw new DbException("No id given");
		}
		//String query = "SELECT * FROM person WHERE userid = 'personId'";
		String query = "SELECT * FROM person WHERE userid = ?";
		Person person = null;
		try (Connection connection = DriverManager.getConnection(url,properties);
             PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setString(1, personId);

			ResultSet result = statement.executeQuery();

			while(result.next()) {
				String firstName = result.getString("firstname");
				String lastName = result.getString("lastname");
				String userid = result.getString("userid");
				String email = result.getString("email");
				String password = result.getString("password");
				Role role = Role.valueOf(result.getString("role"));
				person = (makePerson(userid, email, password, firstName, lastName, role));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return person;
	}
	
	@Override
    public List<Person> getAll(){
		List<Person> persons = new ArrayList<>();
		String query = "SELECT * FROM person";

		try (Connection connection = DriverManager.getConnection(url,properties);
			PreparedStatement statement = connection.prepareStatement(query)) {

			ResultSet result = statement.executeQuery();

			while(result.next()){
				String firstName = result.getString("firstname");
				String lastName = result.getString("lastname");
				String userid = result.getString("userid");
				String email = result.getString("email");
				String password = result.getString("password");
				Role role = Role.valueOf(result.getString("role"));
				persons.add(makePerson(userid, email, password, firstName, lastName, role));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return persons;
	}

	@Override
    public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (this.getAll().contains(person.getUserid())) {
			throw new DbException("User already exists");
		}
		//String query = "insert into person(userid, email, password, firstname, lastname) values(\'" + person.getUserid() + "\', \'" + person.getEmail() +
		//		"\', \'" + person.getPassword() + "\', \'" + person.getFirstName() + "\', \'" + person.getLastName() + "\')";
        String query = "insert into person(userid, email, password, firstname, lastname, role) values(?, ?, ?, ?, ?, ?)";


		try (Connection connection = DriverManager.getConnection(url,properties);
			 PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setString(1, person.getUserid());
		    statement.setString(2, person.getEmail());
		    statement.setString(3, person.getPassword());
		    statement.setString(4, person.getFirstName());
		    statement.setString(5, person.getLastName());
		    statement.setString(6, person.getRole().toString());

			statement.execute();

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

	}
	
	@Override
    public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!this.getAll().contains(person)){
			throw new DbException("No person found");
		}
		/*String query = "update person set email = '" + person.getEmail() +
				"', password = '" + person.getPassword() +
				"', firstname = '" + person.getFirstName() +
				"', lastname = '" + person.getLastName() + "'  where userid='" + person.getUserid() + "'";*/
		String query = "update person set email = ?, password = ?, firstname = ?, lastname = ?, role = ? where userid = ?";
		try (Connection connection = DriverManager.getConnection(url,properties);
			 PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setString(1, person.getEmail());
		    statement.setString(2, person.getPassword());
		    statement.setString(3, person.getFirstName());
		    statement.setString(4, person.getLastName());
			statement.setString(5, person.getRole().toString());
		    statement.setString(6, person.getUserid());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	@Override
    public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		//String query = "delete from person where userid='" + personId + "'";
		String query = "delete from person where userid = ?";
		try (Connection connection = DriverManager.getConnection(url,properties);
			 PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setString(1, personId);

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
    public int getNumberOfPersons() {
		return this.getAll().size();
	}

	private Person makePerson(String userid, String email, String password, String firstName, String lastName, Role role) {
		try {
			Person person = new Person(userid, email, password, firstName, lastName, role);
			return (person);
		}
		catch (IllegalArgumentException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
}
