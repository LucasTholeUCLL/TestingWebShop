package ui.view;

import domain.model.Person;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Launcher {
    private Person person;
    public Launcher(){

    }

    public void start() {
        try {
            int choice = Integer.parseInt(JOptionPane.showInputDialog("WebShop person database:\n \n- 1: Add new person\n- 2: Show overview persons"));
            if (choice == 1) {
                this.person = new Person();
                this.addPerson();
            } else if (choice == 2) {
                //exit launcher
            } else {
                start();
            }
        } catch (Exception e) {
            start();
        }
    }

    private void addPerson() {
        try {
            if (person.getUserid() == null || person.getUserid().trim().isEmpty()) person.setUserid(JOptionPane.showInputDialog("Enter the userid for the new person:"));
            if (person.getEmail() == null || person.getEmail().trim().isEmpty())  person.setEmail(JOptionPane.showInputDialog("Enter the email for the new person:"));
            String password = (JOptionPane.showInputDialog("Enter the password for the new person:"));
            person.setPassword(password);
            if (person.getFirstName() == null || person.getFirstName().trim().isEmpty()) person.setFirstName(JOptionPane.showInputDialog("Enter the first name for the new person:"));
            if (person.getLastName() == null || person.getLastName().trim().isEmpty()) person.setLastName(JOptionPane.showInputDialog("Enter the last name for the new person:"));

            String query = "insert into person(userid, email, password, firstname, lastname) values(\'" + person.getUserid() + "\', \'" + person.getEmail() +
                    "\', \'" + password + "\', \'" + person.getFirstName() + "\', \'" + person.getLastName() + "\')";

            Properties properties = new Properties();
            String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX35?currentSchema=r0722885";
            properties.setProperty("user", "local_r0722885");
            properties.setProperty("password", "ù0C)px$,tMp(Kà");
            //Secret.setPass(properties);	// implements line 17 and 18
            properties.setProperty("ssl", "true");
            properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
            properties.setProperty("sslmode","prefer");

            Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();
            statement.execute(query);

            JOptionPane.showMessageDialog(null, "Person:\n" + person.toString() + "\nwas successfully added to the databank!");

            start();

            System.out.println(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e.getMessage());
            addPerson();
        }

    }
}
