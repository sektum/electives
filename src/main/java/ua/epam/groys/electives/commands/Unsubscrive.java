package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.dao.ContractDao;
import ua.epam.groys.electives.dao.DaoFactory;

/**
 * Unsubscrive a command of controller which delete course from student submit
 * courses. course information.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class Unsubscrive implements Command {
    /**
     * Describes a type of command as Unsubscrive. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "unsubscribe";
    /**
     * Describes a parameter value for getting from request student contract
     * index. Value of field {@value #CONTRACT_ID}.
     */
    public static final String CONTRACT_ID = "courseId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	ContractDao contractDao = DaoFactory.getDaoFactory().getContractDao();
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	contractDao.remove(contractId);
	return (new StartStudentCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
