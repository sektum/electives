package ua.epam.groys.electives.entities;
/**
 * Class represent entity which contains data for forming table {@link StudentData}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class StudentData {
    /**
     * Student contract object.
     */
    private Contract contract;
    /**
     * Student course.
     */
    private Course course;
    /**
     * Student lecturer of course.
     */
    private Lecturer lecturer;

    public Contract getContract() {
	return contract;
    }

    public void setContract(Contract contract) {
	this.contract = contract;
    }

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

    public Lecturer getLecturer() {
	return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
	this.lecturer = lecturer;
    }
}
