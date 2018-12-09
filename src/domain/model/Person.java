package domain.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Role role;

	public Person(String userid, String email, String password, String firstName, String lastName, Role role) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}
	
	public Person() {
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public void setPasswordHashed(String password) {
	    if (password.trim().isEmpty() || password==null) throw new IllegalArgumentException("No password given");
	    this.setPassword(this.hashPassword(password));
    }

    private String hashPassword(String password) {
	    try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
			//System.out.println((new BigInteger(1, crypt.digest())).toString(16));
            return (new BigInteger(1, crypt.digest())).toString(16);
        } catch (Exception e) {
	        throw new DomainException(e.getMessage());
        }
    }

    public boolean isPasswordCorrect(String password) {
	    if (password.trim().isEmpty() || password == null) return false;
	    return this.hashPassword(password).equals(this.password);
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}

	@Override
	public boolean equals(Object person) {
		if (person instanceof  Person) {
			if (this.getUserid().equals(((Person) person).getUserid())) return true;
			return false;
		} else return false;
	}
}
