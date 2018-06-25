package ua.epam.groys.electives.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.epam.groys.electives.dao.CommonDao;
import ua.epam.groys.electives.entities.Entity;

import com.mysql.jdbc.PreparedStatement;

/**
 * Class implements {@link CommonDao} and describe all common operation with
 * database tables.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class CommonJdbcDao<T extends Entity> implements CommonDao<T> {
    private static final Logger LOGGER = Logger.getLogger(CommonJdbcDao.class);
    private Entity.TableInfo<?> tableInfo;

    /**
     * Empty constructor
     */
    CommonJdbcDao() {
    }

    /**
     * Constructor with information about table of database.
     * 
     * @param tableInfo
     *            information of used database table
     */
    CommonJdbcDao(Entity.TableInfo<?> tableInfo) {
	this.tableInfo = tableInfo;
    }

    /**
     * Set information about database table.
     * 
     * @param tableInfo
     *            information about database table.
     */
    void setTableInfo(Entity.TableInfo<?> tableInfo) {
	this.tableInfo = tableInfo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
	List<T> list = new ArrayList<T>();
	Object[] obts = new Object[tableInfo.getTableFieldsName().length];
	Entity t = null;
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery("SELECT * FROM "
		    + tableInfo.getTableName());
	    while (rs.next()) {
		try {
		    t = tableInfo.getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		    LOGGER.error(
			    "Get all (new Instance)" + tableInfo.getTableName(),
			    e);
		}
		for (int i = 0; i < obts.length; ++i) {
		    obts[i] = rs.getObject(i + 1);
		}
		t.setValues(obts);
		list.add((T) t);
	    }
	} catch (SQLException e) {
	    LOGGER.error("Get all " + tableInfo.getTableName(), e);
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get all " + tableInfo.getTableName() + " size="
		    + String.valueOf(list.size()));
	}
	return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Integer id) {
	T entity = null;
	Object[] obts = new Object[tableInfo.getTableFieldsName().length];
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery((new StringBuilder(
		    "SELECT * FROM ").append(tableInfo.getTableName())
		    .append(" WHERE ")
		    .append(tableInfo.getTableFieldsName()[0]).append("=")
		    .append(String.valueOf(id))).toString());
	    if (rs.next()) {
		try {
		    entity = (T) tableInfo.getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		    LOGGER.error(
			    "Get (new Instance) " + tableInfo.getTableName()
				    + " " + tableInfo.getTableFieldsName()[0]
				    + "=" + String.valueOf(id), e);
		}
		for (int i = 0; i < tableInfo.getTableFieldsName().length; ++i) {
		    obts[i] = rs.getObject(i + 1);
		}
		entity.setValues(obts);
	    }
	} catch (SQLException e) {
	    LOGGER.error(
		    "Get " + tableInfo.getTableName() + " "
			    + tableInfo.getTableFieldsName()[0] + "="
			    + String.valueOf(id), e);
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    if (entity == null) {
		LOGGER.debug("Get " + tableInfo.getTableName() + " "
			+ tableInfo.getTableFieldsName()[0] + "="
			+ String.valueOf(id));
	    }
	}
	return entity;
    }

    private String getInsertQuery() {
	StringBuilder query = new StringBuilder("INSERT INTO ");
	StringBuilder questionKol = new StringBuilder();
	query.append(tableInfo.getTableName()).append(" (");
	for (int i = 1; i < tableInfo.getTableFieldsName().length - 1; ++i) {
	    query.append(tableInfo.getTableFieldsName()[i]).append(", ");
	    questionKol.append("?, ");
	}
	query.append(
		tableInfo.getTableFieldsName()[tableInfo.getTableFieldsName().length - 1])
		.append(") VALUES(").append(questionKol).append("?)");
	return query.toString();
    }

    @Override
    public T insert(T entity) {
	Connection conn = DaoJdbcConnection.getConnection();
	try (PreparedStatement statement = (PreparedStatement) conn
		.prepareStatement(getInsertQuery(),
			Statement.RETURN_GENERATED_KEYS)) {
	    for (int i = 1; i < tableInfo.getTableFieldsName().length; ++i) {
		statement.setObject(i, entity.getFieldsValue()[i]);
	    }
	    statement.execute();
	    ResultSet key = statement.getGeneratedKeys();
	    if (key.next()) {
		entity.setId(key.getInt(1));
	    }
	} catch (SQLException e) {
	    LOGGER.error(
		    "Insert " + tableInfo.getTableName() + " "
			    + entity.toString(), e);
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
	    LOGGER.debug("Insert " + tableInfo.getTableName() + " "
		    + entity.toString());
	}
	return entity;
    }

    @Override
    public List<T> insertAll(List<T> list) {
	Connection conn = DaoJdbcConnection.getConnection();
	try (PreparedStatement statement = (PreparedStatement) conn
		.prepareStatement(getInsertQuery(),
			Statement.RETURN_GENERATED_KEYS)) {
	    for (T entity : list) {
		for (int i = 1; i < tableInfo.getTableFieldsName().length; ++i) {
		    statement.setObject(i, entity.getFieldsValue()[i]);
		}
		statement.addBatch();
	    }
	    statement.executeBatch();
	    ResultSet key = statement.getGeneratedKeys();
	    for (int i = 0; key.next(); ++i) {
		list.get(i).setId(key.getInt(1));
	    }
	} catch (SQLException e) {
	    LOGGER.error(
		    "Insert All " + tableInfo.getTableName() + " "
			    + list.toString(), e);
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
	    LOGGER.debug("Insert All " + tableInfo.getTableName() + " "
		    + list.toString());
	}
	return list;
    }

    @Override
    public boolean remove(Integer id) {
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    query.executeUpdate((new StringBuilder("DELETE FROM ")
		    .append(tableInfo.getTableName()).append(" WHERE ")
		    .append(tableInfo.getTableFieldsName()[0]).append("=")
		    .append(String.valueOf(id))).toString());
	} catch (SQLException e) {
	    LOGGER.error(
		    "Remove " + tableInfo.getTableName() + " "
			    + tableInfo.getTableFieldsName()[0] + "="
			    + String.valueOf(id), e);
	    return false;
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Remove " + tableInfo.getTableName() + " "
		    + tableInfo.getTableFieldsName()[0] + "="
		    + String.valueOf(id));
	}
	return true;
    }

    @Override
    public int removeAll(List<T> list) {
	int rows;
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    StringBuilder stringBuilder = new StringBuilder("DELETE FROM "
		    + tableInfo.getTableName() + " WHERE "
		    + tableInfo.getTableFieldsName()[0] + " in (");
	    for (T entity : list) {
		stringBuilder.append(entity.getId()).append(",");
	    }
	    stringBuilder.setCharAt(stringBuilder.length() - 1, ')');
	    rows = query.executeUpdate(stringBuilder.toString());
	} catch (SQLException e) {
	    LOGGER.error(
		    "Delete " + tableInfo.getTableName() + " "
			    + list.toString(), e);
	    return -1;
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
		    + rows);
	}
	return rows;
    }

    private String getUpdateQuery() {
	StringBuilder query = new StringBuilder("UPDATE ");
	query.append(tableInfo.getTableName()).append(" SET ");
	for (int i = 1; i < tableInfo.getTableFieldsName().length - 1; ++i) {
	    query.append(tableInfo.getTableFieldsName()[i]).append("=?,");
	}
	query.append(
		tableInfo.getTableFieldsName()[tableInfo.getTableFieldsName().length - 1])
		.append("=? WHERE ").append(tableInfo.getTableFieldsName()[0])
		.append("=?");
	return query.toString();
    }

    @Override
    public boolean update(T entity) {
	Connection conn = DaoJdbcConnection.getConnection();
	try (PreparedStatement statement = (PreparedStatement) conn
		.prepareStatement(getUpdateQuery())) {
	    for (int i = 1; i < tableInfo.getTableFieldsName().length; ++i) {
		statement.setObject(i, entity.getFieldsValue()[i]);
	    }
	    statement.setInt(entity.getFieldsValue().length, entity.getId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOGGER.error(
		    "Update " + tableInfo.getTableName() + " "
			    + entity.toString(), e);
	    return false;
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Update " + tableInfo.getTableName() + " "
		    + entity.toString());
	}
	return true;
    }

    @Override
    public int updateAll(List<T> list) {
	int rows;
	Connection conn = DaoJdbcConnection.getConnection();
	try (PreparedStatement statement = (PreparedStatement) conn
		.prepareStatement(getUpdateQuery())) {
	    for (T entity : list) {
		for (int i = 1; i < tableInfo.getTableFieldsName().length; ++i) {
		    statement.setObject(i, entity.getFieldsValue()[i]);
		}
		statement
			.setInt(entity.getFieldsValue().length, entity.getId());
		statement.addBatch();
	    }
	    rows = statement.executeBatch().length;
	} catch (SQLException e) {
	    LOGGER.error(
		    "Update " + tableInfo.getTableName() + " "
			    + list.toString(), e);
	    return -1;
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Update " + tableInfo.getTableName() + " "
		    + list.toString());
	}
	return rows;
    }
}
