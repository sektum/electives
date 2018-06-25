package ua.epam.groys.electives.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import java.sql.Statement;

import ua.epam.groys.electives.dao.ContractDao;
import ua.epam.groys.electives.entities.Contract;

/**
 * Class implements {@link ContractDao} and {@link CommonJdbcDao} extends with
 * parameter type {@link Contract}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class ContractJdbcDao extends CommonJdbcDao<Contract> implements
	ContractDao {
    private static final Logger LOGGER = Logger
	    .getLogger(ContractJdbcDao.class);
    /**
     * Contains object with information about database table
     * {@link Contract.ContractTableInfo}.
     */
    private final Contract.ContractTableInfo contractTableInfo;

    /**
     * Constructor create object of {@link Contract.ContractTableInfo} and set
     * it to {@link CommonJdbcDao}.
     */
    public ContractJdbcDao() {
	contractTableInfo = new Contract.ContractTableInfo();
	super.setTableInfo(contractTableInfo);
    }

    @Override
    public List<Contract> getCourseContracts(Integer courseId) {
	List<Contract> list = new ArrayList<Contract>();
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery((new StringBuilder(
		    "SELECT * FROM ").append(contractTableInfo.getTableName())
		    .append(" WHERE ").append(contractTableInfo.getTableName())
		    .append(".")
		    .append(contractTableInfo.getTableFieldsName()[3])
		    .append("=").append(String.valueOf(courseId))).toString());
	    while (rs.next()) {
		if (rs.getObject(3) == null) {
		    list.add(new Contract(rs.getInt(1), rs.getString(2), null,
			    rs.getInt(4), rs.getInt(5), rs.getShort(6)));
		} else {
		    list.add(new Contract(rs.getInt(1), rs.getString(2), rs
			    .getInt(3), rs.getInt(4), rs.getInt(5), rs
			    .getShort(6)));
		}
	    }
	} catch (SQLException e) {
	    LOGGER.error(
		    "Get course contracts coureseId="
			    + String.valueOf(courseId), e);
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
	    LOGGER.debug("Get course contracts size="
		    + String.valueOf(list.size()) + "  courseId="
		    + String.valueOf(courseId));
	}
	return list;
    }

    @Override
    public List<Contract> getStudentContracts(Integer studentId) {
	List<Contract> list = new ArrayList<Contract>();
	Contract contract;
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery((new StringBuilder(
		    "SELECT * FROM ").append(contractTableInfo.getTableName())
		    .append(" WHERE ").append(contractTableInfo.getTableName())
		    .append(".")
		    .append(contractTableInfo.getTableFieldsName()[4])
		    .append("=").append(String.valueOf(studentId)).toString()));
	    while (rs.next()) {
		contract = new Contract();
		contract.setValues(rs.getObject(1), rs.getObject(2),
			rs.getObject(3), rs.getObject(4), rs.getObject(5),
			rs.getObject(6));
		list.add(contract);
	    }
	} catch (SQLException e) {
	    LOGGER.error(
		    "Get student contracts coureseId="
			    + String.valueOf(studentId), e);
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
	    LOGGER.debug("Get student contracts size="
		    + String.valueOf(list.size()) + "  studentId="
		    + String.valueOf(studentId));
	}
	return list;
    }
}