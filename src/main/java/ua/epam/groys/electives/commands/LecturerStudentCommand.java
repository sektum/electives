package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.Contract;
import ua.epam.groys.electives.entities.Student;
import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * LecturerStudentCommand a command of controller which forms page of student.
 * course for user-lecturer.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class LecturerStudentCommand implements Command {
    /**
     * Describes a type of command as LecturerStudentCommand. Value of field
     * {@value #TYPE_COMMAND}.
     */
    public static final String TYPE_COMMAND = "lecturerStudent";
    /**
     * Describes a parameter value for setting request data about student
     * contract. Value of field {@value #CONTRACT_DATA}.
     */
    public static final String CONTRACT_DATA = "contractData";
    /**
     * Describes a parameter value for setting request data about student. Value
     * of field {@value #STUDENT_DATA}.
     */
    public static final String STUDENT_DATA = "studentData";
    /**
     * Describes a parameter value for getting from request student contract
     * index. Value of field {@value #CONTRACT_ID}.
     */
    public static final String CONTRACT_ID = "contractId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page;
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	DaoFactory facadeFactory = DaoFactory.getDaoFactory();
	Contract contract = facadeFactory.getContractDao().getById(contractId);
	Student student = facadeFactory.getStudentDao().getById(
		contract.getIdStudent());
	request.setAttribute(CONTRACT_DATA, contract);
	request.setAttribute(STUDENT_DATA, student);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.LECTURER_STUDENT_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return TYPE_COMMAND;
    }

}
