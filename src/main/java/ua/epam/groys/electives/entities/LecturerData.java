package ua.epam.groys.electives.entities;

import ua.epam.groys.electives.mytags.LecturerTable;

import java.util.List;

/**
 * Class represent entity which contains data for forming table
 * {@link LecturerTable}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class LecturerData {
    /**
     * Course object.
     */
    private Course course;
    /**
     * All contracts which sign on course.
     */
    private List<Contract> contracts;
    /**
     * All students which sing on course.
     */
    private List<Student> students;

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

    public List<Contract> getContracts() {
	return contracts;
    }

    public void setContracts(List<Contract> contracts) {
	this.contracts = contracts;
    }

    public List<Student> getStudents() {
	return students;
    }

    public void setStudents(List<Student> students) {
	this.students = students;
    }
}
