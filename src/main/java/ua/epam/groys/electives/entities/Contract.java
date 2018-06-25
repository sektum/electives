package ua.epam.groys.electives.entities;

/**
 * Class extends {@link Entity}, represent entity contract.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class Contract extends Entity {
    /**
     * Field of Database table Contract which contains comment from lecturer.
     */
    private String comment;
    /**
     * Field of Database table Contract which contains mark of course from
     * lecturer.
     */
    private Integer mark;
    /**
     * Field of Database table Contract which contains index of course index key
     * which study student.
     */
    private Integer idCourse;
    /**
     * Field of Database table Contract which contains index of student index
     * key.
     */
    private Integer idStudent;
    /**
     * Field of Database table Contract which contains progress of course.
     */
    private Short finishedPercent;

    /**
     * Empty constructor.
     */
    public Contract() {
    }

    /**
     * Constructor object entity Contract.
     * 
     * @param id
     *            index of contract.
     * @param comment
     *            of lecturer.
     * @param mark
     *            for course.
     * @param idCourse
     *            key index of {@link Course}.
     * @param idStudent
     *            key index of {@link Student}.
     * @param finished
     *            progress of course study.
     */
    public Contract(Integer id, String comment, Integer mark, Integer idCourse,
	    Integer idStudent, Short finished) {
	this.id = id;
	this.comment = comment;
	this.mark = mark;
	this.idCourse = idCourse;
	this.idStudent = idStudent;
	this.finishedPercent = finished;
    }

    /**
     * Constructor make clone of input contract object.
     * 
     * @param contr
     *            clone contract.
     */
    public Contract(Contract contr) {
	this.id = contr.id;
	this.comment = contr.comment;
	this.mark = contr.mark;
	this.idCourse = contr.idCourse;
	this.idStudent = contr.idStudent;
	this.finishedPercent = contr.finishedPercent;
    }

    /**
     * Inner class extends {@link TableInfo} with type parameter
     * {@link Contract}. Represent information and operation of database table
     * Contract.
     * 
     * @author Sektum
     * @version 1.0 20/06/18
     */
    public static class ContractTableInfo extends TableInfo<Contract> {

	/**
	 * Empty constructor put to super constructor object class of
	 * {@link Contract}.
	 */
	public ContractTableInfo() {
	    super(Contract.class);
	}
    }

    /**
     * Get index of contract.
     * 
     * @return index of contract.
     */
    public Integer getId() {
	return id;
    }

    /**
     * Set index of contract.
     * 
     * @param id
     *            index of contract
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Get comment of contract.
     * 
     * @return comment of contract.
     */
    public String getComment() {
	return comment;
    }

    /**
     * Set comment of contract.
     * 
     * @param comment
     *            .
     */
    public void setComment(String comment) {
	this.comment = comment;
    }

    /**
     * Get mark of contract.
     * 
     * @return mark of contract.
     */
    public Integer getMark() {
	return mark;
    }

    /**
     * Set mark of contract.
     * 
     * @param mark
     *            of contract
     */
    public void setMark(Integer mark) {
	if (mark != null) {
	    if (mark > 12) {
		mark = 12;
	    }
	    if (mark < -1) {
		mark = 0;
	    }
	    if (finishedPercent != 100) {
		mark = null;
	    }
	}
	this.mark = mark;
    }

    /**
     * Get index key of entity {@link Course} of contract.
     * 
     * @return index key of entity {@link Course}
     */
    public Integer getIdCourse() {
	return idCourse;
    }

    /**
     * Set index key of entity {@link Course} of contract.
     * 
     * @param idCourse
     *            index key of entity {@link Course} of contract.
     */
    public void setIdCourse(Integer idCourse) {
	this.idCourse = idCourse;
    }

    /**
     * Get index key of entity {@link Student} of contract.
     * 
     * @return index key of entity {@link Student}
     */
    public Integer getIdStudent() {
	return idStudent;
    }

    /**
     * Set index key of entity {@link Student} of contract.
     * 
     * @param idStudent
     *            index key of entity {@link Student} of contract
     */
    public void setIdStudent(Integer idStudent) {
	this.idStudent = idStudent;
    }

    /**
     * Check for course finished.
     * 
     * @return true if course id finished or false if not
     */
    public boolean isFinished() {
	return finishedPercent == 100 ? true : false;
    }

    /**
     * Get progress of finishing course.
     * 
     * @return progress of finishing course.
     */
    public Short getFinishedPercent() {
	return finishedPercent;
    }

    /**
     * Set progress of finishing course.
     * 
     * @param finished
     *            of finishing course
     */
    public void setFinishedPercent(Short finished) {
	if (finished > 100) {
	    finished = 100;
	}
	if (finished < 0) {
	    finished = 0;
	}
	this.finishedPercent = finished;
    }

    /**
     * Set progress of finishing course.
     * 
     * @param finished
     *            of finishing course.
     */
    public void setFinishedPercent(Integer finished) {
	if (finished > 100) {
	    finished = 100;
	}
	if (finished < 0) {
	    finished = 0;
	}
	this.finishedPercent = finished.shortValue();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((comment == null) ? 0 : comment.hashCode());
	result = prime * result
		+ ((finishedPercent == null) ? 0 : finishedPercent.hashCode());
	result = prime * result
		+ ((idCourse == null) ? 0 : idCourse.hashCode());
	result = prime * result
		+ ((idStudent == null) ? 0 : idStudent.hashCode());
	result = prime * result + ((mark == null) ? 0 : mark.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Contract other = (Contract) obj;
	if (comment == null) {
	    if (other.comment != null)
		return false;
	} else if (!comment.equals(other.comment))
	    return false;
	if (finishedPercent == null) {
	    if (other.finishedPercent != null)
		return false;
	} else if (!finishedPercent.equals(other.finishedPercent))
	    return false;
	if (idCourse == null) {
	    if (other.idCourse != null)
		return false;
	} else if (!idCourse.equals(other.idCourse))
	    return false;
	if (idStudent == null) {
	    if (other.idStudent != null)
		return false;
	} else if (!idStudent.equals(other.idStudent))
	    return false;
	if (mark == null) {
	    if (other.mark != null)
		return false;
	} else if (!mark.equals(other.mark))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Contract [id=" + id + ", comment=" + comment + ", mark=" + mark
		+ ", idCourse=" + idCourse + ", idStudent=" + idStudent
		+ ", finished=" + finishedPercent + "]";
    }

    @Override
    public void setValues(Object... obts) {
	this.id = obts[0] != null ? ((Long) obts[0]).intValue() : null;
	this.comment = (String) obts[1];
	this.mark = obts[2] != null ? ((Long) obts[2]).intValue() : null;
	this.idCourse = obts[3] != null ? ((Long) obts[3]).intValue() : null;
	this.idStudent = obts[4] != null ? ((Long) obts[4]).intValue() : null;
	this.finishedPercent = ((Integer) obts[5]).shortValue();
    }

    @Override
    public Object[] getFieldsValue() {
	// Fourth field(isLecturer) is not used in table
	Object[] values = new Object[6];
	values[0] = this.id;
	values[1] = this.comment;
	values[2] = this.mark;
	values[3] = this.idCourse;
	values[4] = this.idStudent;
	values[5] = this.finishedPercent;
	return values;
    }
}
