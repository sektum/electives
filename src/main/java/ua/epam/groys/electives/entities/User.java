package ua.epam.groys.electives.entities;

/**
 * Class extends {@link Entity}, represent entity user.
 * @author Sektum
 * @version 1.0 20/06/18
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class extends {@link Entity}, represent basic class for entity
 * {@link Lecturer} and {@link Student}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class User extends Entity {
    private String fullName;
    private String pwd;

    public User() {
    }

    public User(Integer id, String fullName, String pwd) {
	super(id);
	this.fullName = fullName;
	setPwd(pwd);
    }

    public User(Integer id, String fullName) {
	super(id);
	this.fullName = fullName;
    }

    public User(User user) {
	super(user.getId());
	this.fullName = user.fullName;
	this.pwd = user.pwd;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
	return fullName;
    }

    /**
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
	return pwd;
    }

    /**
     * @param pwd
     *            the pwd to set
     */
    public void setPwd(String pwd) {
	MessageDigest c = null;
	try {
	    c = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
	byte[] h = c.digest(pwd.getBytes());
	StringBuffer pass = new StringBuffer();

	for (int i = 0; i < h.length; ++i)
	    pass.append(Integer.toHexString(0xFF & h[i]));
	this.pwd = pass.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((fullName == null) ? 0 : fullName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	User other = (User) obj;
	if (fullName == null) {
	    if (other.fullName != null)
		return false;
	} else if (!fullName.equals(other.fullName))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if ((pwd == null) || (other.pwd == null)) {
	    return true;
	} else if (!pwd.equals(other.pwd))
	    return false;
	return true;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return (new StringBuilder("User").append("[id=").append(id)
		.append(", fullName=").append(fullName).append(", pwd=")
		.append(pwd)).toString();
    }

    @Override
    public void setValues(Object... obts) {
	this.id = obts[0] != null ? ((Long) obts[0]).intValue() : null;
	this.fullName = (String) obts[1];
	this.pwd = (String) obts[2];

    }

    @Override
    public Object[] getFieldsValue() {
	// Fourth field(isLecturer) is not used in table
	Object[] values = new Object[3];
	values[0] = this.id;
	values[1] = this.fullName;
	values[2] = this.pwd;
	return values;
    }
}