package ua.epam.groys.electives.entities;

/**
 * Class extends {@link User}, represented entity of authorized user.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class AuthorizedUser extends User {
    /**
     * Field show who is user {@link Lecturer} or {@Student}.
     */
    private boolean isLecturer;

    /**
     * Constructor of authorized user object.
     * 
     * @param id
     *            index of user.
     * @param fullName
     *            name of user.
     * @param pwd
     *            password of user.
     * @param isLecturer
     *            type of user.
     */
    public AuthorizedUser(Integer id, String fullName, String pwd,
	    boolean isLecturer) {
	super(id, fullName, pwd);
	this.isLecturer = isLecturer;
    }

    /**
     * Constructor of authorized user object
     * 
     * @param name
     *            user name.
     * @param pwd
     *            user password.
     */
    public AuthorizedUser(String name, String pwd) {
	super(-1, name, pwd);
    }

    /**
     * Return type of user.
     * 
     * @return true if user {@link Lecturer} or false if {@Student}.
     */
    public boolean isLecturer() {
	return isLecturer;
    }

    /**
     * Sets type of user.
     * 
     * @param isLecturer
     *            type of user
     */
    public void setLecturer(boolean isLecturer) {
	this.isLecturer = isLecturer;
    }
}
