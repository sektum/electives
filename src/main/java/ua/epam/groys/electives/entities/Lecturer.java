package ua.epam.groys.electives.entities;

/**
 * Class extends {@link Entity}, represent entity lecturer.
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class Lecturer extends User {

    public Lecturer() {
    }

    public Lecturer(Integer id, String full_name, String pwd) {
	super(id, full_name, pwd);
    }

    public Lecturer(Integer id, String full_name) {
	super(id, full_name);
    }

    public Lecturer(Lecturer lecturer) {
	super(lecturer);
    }

    /**
     * Inner class extends {@link TableInfo} with type parameter
     * {@link Lecturer}. Represent information and operation of database table
     * Lecturer.
     * 
     * @author Sektum
     * @version 1.0 20/06/18
     */
    public static class LecturerTableInfo extends TableInfo<Lecturer> {
	/**
	 * Empty constructor put to super constructor object class of
	 * {@link Lecturer}.
	 */
	public LecturerTableInfo() {
	    super(Lecturer.class);
	}
    }
}
