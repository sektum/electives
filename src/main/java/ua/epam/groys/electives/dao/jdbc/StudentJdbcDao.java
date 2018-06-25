package ua.epam.groys.electives.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import ua.epam.groys.electives.dao.StudentDao;
import ua.epam.groys.electives.entities.Contract;
import ua.epam.groys.electives.entities.Student;

/**
 * Class implements {@link StudentDao} and extends {@link CommonJdbcDao} with
 * parameter type {@link Student}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class StudentJdbcDao extends CommonJdbcDao<Student> implements
	StudentDao {
    private static final Logger LOGGER = Logger.getLogger(StudentJdbcDao.class);
    /**
     * Contains object with information about database table
     * {@link Student.StudentTableInfo}.
     */
    private final Student.StudentTableInfo tableInfo;

    /**
     * Constructor create object of {@link Student.StudentTableInfo} and set it
     * to {@link CommonJdbcDao}.
     */
    public StudentJdbcDao() {
	tableInfo = new Student.StudentTableInfo();
	super.setTableInfo(tableInfo);
    }

    @Override
    public Student getStudent(Integer contractId) {
	Student student = null;
	NDC.push("Get student by contract id");
	Contract contract = (new ContractJdbcDao()).getById(contractId);
	student = getById(contract.getIdStudent());
	NDC.pop();
	return student;
    }

    @Override
    public List<Student> getAllStudent(List<Contract> contracts) {
	List<Student> students = new ArrayList<>(contracts.size());
	for (int i = 0; i < contracts.size(); ++i) {
	    students.add(null);
	}
	Student student = null;
	int studentsNumb = 0;
	Object[] obts = new Object[3];
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    // Query not return duplicates ( in(1, 1, 2, 1)) and not guarantee
	    // same order
	    StringBuilder stringBuilder = new StringBuilder();
	    /*
	     * = new StringBuilder("SELECT * FROM " + tableInfo.getTableName() +
	     * " WHERE " + tableInfo.getTableFieldsName()[0] + " in ("); for
	     * (Contract contract : contracts) {
	     * stringBuilder.append(contract.getIdStudent()).append(","); }
	     * stringBuilder.setCharAt(stringBuilder.length() - 1, ')');
	     */
	    stringBuilder.append("SELECT * FROM " + tableInfo.getTableName());
	    ResultSet rs = query.executeQuery(stringBuilder.toString());
	    lab1: while (rs.next()) {
		try {
		    student = tableInfo.getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		    LOGGER.error("Can't create object student contracts="
			    + contracts.toString(), e);
		    return null;
		}
		for (int j = 0; j < contracts.size(); ++j) {
		    if ((students.get(j) == null)
			    && (contracts.get(j).getIdStudent().equals(rs
				    .getInt(1)))) {
			for (int i = 0; i < obts.length; ++i) {
			    obts[i] = rs.getObject(i + 1);
			}
			student.setValues(obts);
			students.set(j, student);
			studentsNumb++;
			if (studentsNumb == contracts.size()) {
			    break lab1;
			}
		    }
		}
	    }
	} catch (SQLException e) {
	    LOGGER.error("Get all student " + tableInfo.getTableName() + " "
		    + contracts.toString(), e);
	    return null;
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Delete " + tableInfo.getTableName() + " rows count="
		    + contracts.size());
	}
	return students;
    }
}