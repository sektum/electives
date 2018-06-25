package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.Contract;
import ua.epam.groys.electives.entities.Course;
import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * StudentCourseInfo a command of controller which forms page with student
 * course information.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class StudentCourseInfo implements Command {
    /**
     * Describes a type of command as StudentCourseInfo. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "courseInfo";
    /**
     * Describes a parameter value for getting from request student course
     * index. Value of field {@value #CONTRACT_ID}.
     */
    public static final String CONTRACT_ID = "courseId";
    /**
     * Describes a parameter value for setting to request student course data.
     * Value of field {@value #COURSE_DATA}.
     */
    public static final String COURSE_DATA = "courseData";
    /**
     * Describes a parameter value for setting to request student contract data.
     * Value of field {@value #CONTRACT_DATA}.
     */
    public static final String CONTRACT_DATA = "contractData";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page;
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	DaoFactory facadeFactory = DaoFactory.getDaoFactory();
	Contract contract = facadeFactory.getContractDao().getById(contractId);
	Course course = facadeFactory.getCourseDao().getById(
		contract.getIdCourse());
	request.setAttribute(CONTRACT_DATA, contract);
	request.setAttribute(COURSE_DATA, course);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.STUDENT_COURSE_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
