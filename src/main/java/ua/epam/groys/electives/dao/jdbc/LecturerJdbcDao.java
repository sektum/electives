/**
 * 
 */
package ua.epam.groys.electives.dao.jdbc;

import org.apache.log4j.NDC;

import ua.epam.groys.electives.dao.LecturerDao;
import ua.epam.groys.electives.entities.Course;
import ua.epam.groys.electives.entities.Lecturer;

/**
 * Class implements {@link LecturerDao} and extends {@link CommonJdbcDao} with
 * parameter type {@link Lecturer}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class LecturerJdbcDao extends CommonJdbcDao<Lecturer> implements
	LecturerDao {

    /**
     * Constructor create object of {@link Lecturer.LecturerTableInfo} and set
     * it to {@link CommonJdbcDao}.
     */
    public LecturerJdbcDao() {
	super(new Lecturer.LecturerTableInfo());
    }

    @Override
    public Lecturer getLecturer(Integer courseId) {
	Lecturer lecturer = null;
	NDC.push("Get lecturer by course id");
	Course course = (new CourseJdbcDao()).getById(courseId);
	lecturer = getById(course.getId_lecturer());
	NDC.pop();
	return lecturer;
    }
}