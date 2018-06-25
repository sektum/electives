package ua.epam.groys.electives.entities;

/**
 * Class extends {@link Entity}, represent entity course.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class Course extends Entity {
    /**
     * Name of course.
     */
    private String name;
    /**
     * Identificator of lecturer of this course.
     */
    private Integer idLecturer;

    /**
     * Empty constructor.
     */
    public Course() {
    }

    /**
     * Constructor set values of course object.
     * 
     * @param id
     *            identificator of course.
     * @param name
     *            of course.
     * @param idLecturer
     *            identificator of lector.
     */
    public Course(Integer id, String name, Integer idLecturer) {
	this.id = id;
	this.name = name;
	this.idLecturer = idLecturer;
    }

    /**
     * Constructor make copy of input course values.
     * 
     * @param course values for object.
     */
    public Course(Course course) {
	this.id = course.id;
	this.name = course.name;
	this.idLecturer = course.idLecturer;
    }


    /**
     * Inner class extends {@link TableInfo} with type parameter
     * {@link Course}. Represent information and operation of database table
     * Course.
     * 
     * @author Sektum
     * @version 1.0 20/06/18
     */
    public static class CourseTableInfo extends TableInfo<Course> {
	/**
	 * Empty constructor put to super constructor object class of
	 * {@link Course}.
	 */
	public CourseTableInfo() {
	    super(Course.class);
	}
    }

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the idLecturer
     */
    public Integer getId_lecturer() {
	return idLecturer;
    }

    /**
     * @param idLecturer
     *            the idLecturer to set
     */
    public void setId_lecturer(Integer idLecturer) {
	this.idLecturer = idLecturer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Course [id=" + id + ", name=" + name + ", idLecturer="
		+ idLecturer + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result
		+ ((idLecturer == null) ? 0 : idLecturer.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Course other = (Course) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (idLecturer == null) {
	    if (other.idLecturer != null)
		return false;
	} else if (!idLecturer.equals(other.idLecturer))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public void setValues(Object... obts) {
	this.id = obts[0] != null ? ((Long) obts[0]).intValue() : null;
	this.name = (String) obts[1];
	this.idLecturer = obts[2] != null ? ((Long) obts[2]).intValue() : null;

    }

    @Override
    public Object[] getFieldsValue() {
	// Fourth field(isLecturer) is not used in database table
	Object[] values = new Object[3];
	values[0] = this.id;
	values[1] = this.name;
	values[2] = this.idLecturer;
	return values;
    }
}
