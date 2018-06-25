package ua.epam.groys.electives.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.AuthorizedUser;
import ua.epam.groys.electives.entities.Contract;
import ua.epam.groys.electives.entities.StudentData;
import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * StartStudentCommand a command of controller which forms beginning student
 * page.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class StartStudentCommand implements Command {
    /**
     * Describes a type of command as StartStudentCommand. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "loginStudent";
    /**
     * Describes a parameter value for setting to request student courses. Value
     * of field {@value #STUDENT_DATA}.
     */
    public static final String STUDENT_DATA = "studentData";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	DaoFactory daoFactory = DaoFactory.getDaoFactory();
	AuthorizedUser authorizedUser = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	List<Contract> contracts = daoFactory.getContractDao()
		.getStudentContracts(authorizedUser.getId());
	StudentData[] studentData = new StudentData[contracts.size()];
	for (int i = 0; i < studentData.length; ++i) {
	    studentData[i] = new StudentData();
	    studentData[i].setContract(contracts.get(i));
	    studentData[i].setCourse(daoFactory.getCourseDao().getById(
		    contracts.get(i).getIdCourse()));
	    studentData[i].setLecturer(daoFactory.getLecturerDao().getLecturer(
		    contracts.get(i).getIdCourse()));
	}
	request.setAttribute(STUDENT_DATA, studentData);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.STUDENT_MAIN_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return null;
    }

}
