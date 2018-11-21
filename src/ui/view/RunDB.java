package ui.view;

import domain.model.*;

import java.sql.*;
import java.util.Properties;

public class RunDB {
    public static void main(String[] args) throws SQLException {
        Launcher addPerson = new Launcher();
        addPerson.start();
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX35?currentSchema=r0722885";
        properties.setProperty("user", "local_r0722885");
        properties.setProperty("password", "ù0C)px$,tMp(Kà");
        //Secret.setPass(properties);	// implements line 17 and 18
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");

        ShopService s = new ShopService(properties);

        Connection connection = DriverManager.getConnection(url,properties);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery( "SELECT * FROM person" );

        while(result.next()){
            String firstName = result.getString("firstname");
            String lastName = result.getString("lastname");
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            try {	// validation of data stored in database
                Person person = new Person(userid, email, password, firstName, lastName);
                System.out.println(person.toString());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        statement.close();
        connection.close();
    }
}
