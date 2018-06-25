package ua.epam.groys.electives.entities;

/**
 * Class extends {@link Entity}, represent entity student.
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class Student extends User {
    //
    public Student() {
    }

    public Student(Integer id, String fullName, String pwd) {
	super(id, fullName, pwd);
    }

    public Student(Integer id, String fullName) {
	super(id, fullName);
    }

    public Student(Student student) {
	super(student);
    }
    /**
     * Inner class extends {@link TableInfo} with type parameter
     * {@link Student}. Represent information and operation of database table
     * Student.
     * 
     * @author Sektum
     * @version 1.0 20/06/18
     */
    public static class StudentTableInfo extends TableInfo<Student> {
	/**
	 * Empty constructor put to super constructor object class of
	 * {@link Lecturer}.
	 */
	public StudentTableInfo() {
	    super(Student.class);
	}
    }
}
