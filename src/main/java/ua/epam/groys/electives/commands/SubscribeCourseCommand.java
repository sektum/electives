package ua.epam.groys.electives.commands;

import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.AuthorizedUser;
import ua.epam.groys.electives.entities.Contract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * StudentCourseInfo a command of controller which submit student to course.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class SubscribeCourseCommand implements Command {
    /**
     * Describes a type of command as SubscribeCourseCommand. Value of field
     * {@value #TYPE_COMMAND}.
     */
    public static final String TYPE_COMMAND = "subscribeCourse";
    /**
     * Describes a parameter value for getting from request student course
     * index. Value of field {@value #COURSE_ID}.
     */
    public static final String COURSE_ID = "courseId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	Integer courseId = Integer.valueOf(request.getParameter(COURSE_ID));
	DaoFactory facadeFactory = DaoFactory.getDaoFactory();
	AuthorizedUser user = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	Contract contract = new Contract(-1, "", null, courseId, user.getId(),
		(short) 0);
	facadeFactory.getContractDao().insert(contract);
	return (new StartStudentCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	return TYPE_COMMAND;
    }

}
