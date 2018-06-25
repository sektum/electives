package ua.epam.groys.electives.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.Course;
import ua.epam.groys.electives.entities.Lecturer;
import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * AllCoursesCommand is one of the controller command which allow an application
 * to show to user all courses.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class AllCoursesCommand implements Command {
    /**
     * Describes a type of command as AllCoursesCommand value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "allCourses";
    /**
     * Field value uses as parameter for sending data about courses and
     * lecturers to web-page value of field {@value #COURSES_DATA}.
     */
    public static final String COURSES_DATA = "coursesData";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	DaoFactory daoFactory = DaoFactory.getDaoFactory();
	HashMap<String, ArrayList<Course>> coursesData = new HashMap<>();
	ArrayList<Lecturer> lectures = (ArrayList<Lecturer>) daoFactory
		.getLecturerDao().getAll();
	ArrayList<Course> courses;
	for (Lecturer lecturer : lectures) {
	    courses = (ArrayList<Course>) daoFactory.getCourseDao()
		    .getLecturerCourses(lecturer.getId());
	    coursesData.put(lecturer.getFullName(), courses);
	}
	request.setAttribute(COURSES_DATA, coursesData);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.STUDENT_ALL_COURSES_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
