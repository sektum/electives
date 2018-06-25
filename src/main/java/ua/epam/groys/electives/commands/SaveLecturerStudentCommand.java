package ua.epam.groys.electives.commands;

import ua.epam.groys.electives.dao.ContractDao;
import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.Contract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SaveLecturerStudentCommand a command of controller which save data of student
 * course to database.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class SaveLecturerStudentCommand implements Command {
    /**
     * Describes a type of command as SaveLecturerStudentCommand. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "saveLecturerStudent";
    /**
     * Describes a parameter value for getting from request student mark. Value
     * {@value #MARK}.
     */
    public static final String MARK = "mark";
    /**
     * Describes a parameter value for getting from request student course
     * finished process. Value of field {@value #COURSE_FINISHED}.
     */
    public static final String COURSE_FINISHED = "courseFinished";
    /**
     * Describes a parameter value for getting from request lecturer comment for
     * student. Value of field {@value #COMMENT}.
     */
    public static final String COMMENT = "comment";
    /**
     * Describes a parameter value for getting from request student contract
     * index in database. Value of field {@value #CONTRACT_ID}.
     */
    public static final String CONTRACT_ID = "contractId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	ContractDao contractDao = DaoFactory.getDaoFactory().getContractDao();
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	Contract contract = contractDao.getById(contractId);
	Integer mark;
	try {
	    mark = Integer.valueOf(request.getParameter(MARK));
	} catch (NumberFormatException n) {
	    mark = null;
	}
	Integer courseProgress;
	try {
	    courseProgress = Integer.valueOf(request
		    .getParameter(COURSE_FINISHED));
	} catch (NumberFormatException n) {
	    courseProgress = 0;
	}
	String comment = request.getParameter(COMMENT);
	contract.setFinishedPercent(courseProgress);
	contract.setMark(mark);
	contract.setComment(comment);
	contractDao.update(contract);

	return (new StartLecturerCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
