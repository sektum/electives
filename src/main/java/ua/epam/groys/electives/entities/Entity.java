package ua.epam.groys.electives.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class which presents common field and operation of program entity.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public abstract class Entity {
    /**
     * Index key of entity in the database table.
     */
    Integer id;

    /**
     * Empty Constructor.
     */
    public Entity() {
    }

    /**
     * Constructor sets key index to entity.
     * 
     * @param id
     *            key index of entity.
     */
    public Entity(Integer id) {
	this.id = id;
    }

    /**
     * Gets key index of entity.
     * 
     * @return key index of entity.
     */
    public Integer getId() {
	return id;
    }

    /**
     * Sets key index of entity.
     * 
     * @param id
     *            key index of entity.
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Methods sets all fields values of entity.
     * 
     * @param obts
     *            fields values
     */
    public abstract void setValues(Object... obts);

    /**
     * Gets array of entity fields value.
     * 
     * @return array of entity values.
     */
    public abstract Object[] getFieldsValue();

    /**
     * Class describe information about entity database table.
     * 
     * @author Sektum
     * @version 1.0 20/06/18
     * @param <E>
     *            type of entity
     */
    public static class TableInfo<E extends Entity> {
	/**
	 * Entity class object.
	 */
	private Class<E> cl;
	/**
	 * Name of database table.
	 */
	String tableName;
	/**
	 * Array of database table names.
	 */
	String[] tableVieldsName;

	/**
	 * Sets entity class object.
	 * @param cl entity class object.
	 */
	@SuppressWarnings("unchecked")
	public TableInfo(Class<?> cl) {
	    this.cl = (Class<E>) cl;
	}

	/**
	 * Gets entity class object.
	 * @return entity class object.
	 */
	public Class<E> getEntityClass() {
	    return cl;
	}

	/**
	 * Gets database table name.
	 * @return database table name
	 */
	public String getTableName() {
	    if (tableName == null) {
		tableName = cl.getSimpleName();
	    }
	    return tableName;
	}

	/**
	 * Gets names of database table fields from entity class fields.
	 * @return array of database table fields name.
	 */
	public String[] getTableFieldsName() {
	    if (tableVieldsName == null) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < cl.getDeclaredFields().length; ++i) {
		    list.add(cl.getDeclaredFields()[i].getName());
		}
		tableVieldsName = (String[]) getTableFields(list,
			cl.getSuperclass()).toArray(new String[list.size()]);
	    }
	    return tableVieldsName;
	}

	/**
	 * Gets entity fields name.
	 * @param list fields from privies father classes.
	 * @param c current class object.
	 * @return list of class fields names.
	 */
	private List<String> getTableFields(List<String> list, Class<?> c) {
	    if (c != null) {
		ArrayList<String> listL = new ArrayList<>();
		for (int i = 0; i < c.getDeclaredFields().length; ++i) {
		    if (!list.contains(c.getDeclaredFields()[i].getName())) {
			listL.add(c.getDeclaredFields()[i].getName());
		    }
		}
		listL.addAll(list);
		list = getTableFields(listL, c.getSuperclass());
	    }
	    return list;
	}
    }
}
