package ua.epam.groys.electives.mytags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.epam.groys.electives.entities.StudentData;

/**
 * Class describe tag for creating table of students courses.
 * @author Sektum
 * version 1.0 20/06/18
 */
public class StudentTable extends TagSupport {
    private static final long serialVersionUID = -2104961797473796800L;
    private static final Logger LOGGER = Logger.getLogger(StudentTable.class);
    /**
     * Message to user of error in process creation of table.
     * {@value #STUDENT_TABLE_ERROR}.
     */
    private static final String STUDENT_TABLE_ERROR = "Student table error";
    private StudentData[] studentData;
    private String noCourses;
    private String lecturerName;
    private String courseName;
    private String courseProgress;
    private String mark;
    private String buttonInfo;
    private String buttonUnsubscribe;

    public void setStudentData(StudentData[] studentData) {
	this.studentData = studentData;
    }

    public void setNoCourses(String noCourses) {
	this.noCourses = noCourses;
    }

    public void setLecturerName(String lecturerName) {
	this.lecturerName = lecturerName;
    }

    public void setCourseName(String courseName) {
	this.courseName = courseName;
    }

    public void setCourseProgress(String courseProgress) {
	this.courseProgress = courseProgress;
    }

    public void setMark(String mark) {
	this.mark = mark;
    }

    public void setButtonInfo(String buttonInfo) {
	this.buttonInfo = buttonInfo;
    }

    public void setButtonUnsubscribe(String buttonUnsubscribe) {
	this.buttonUnsubscribe = buttonUnsubscribe;
    }

    public int doStartTag() {
	StringBuilder tableBuilder = new StringBuilder();
	try {
	    if (studentData == null || studentData.length == 0) {
		tableBuilder.append("<h3>" + noCourses + "</h3>");
	    } else {
		tableBuilder.append("<table border=\"2\" cellpadding=\"8\">");
		tableBuilder.append("<tr><td>№</td><td>" + courseName
			+ "</td><td>" + lecturerName + "</td><td>"
			+ courseProgress + "</td><td>" + mark + "</td></tr>");
		for (int i = 0; i < studentData.length; ++i) {
		    tableBuilder.append("<tr><td>" + (i + 1) + "</td>");
		    tableBuilder.append("<td>"
			    + studentData[i].getCourse().getName() + "</td>");
		    tableBuilder.append("<td>"
			    + studentData[i].getLecturer().getFullName()
			    + "</td>");
		    tableBuilder.append("<td>"
			    + studentData[i].getContract().getFinishedPercent()
			    + "%</td>");
		    if (studentData[i].getContract().getMark() == null) {
			tableBuilder.append("<td>---</td>");
		    } else {
			tableBuilder.append("<td>"
				+ studentData[i].getContract().getMark()
				+ "</td>");
		    }
		    tableBuilder
			    .append("<form name=\"LecturerTableForm\" action=\"controller\" "
				    + "method=\"post\"><input type=\"hidden\" name=\"courseId\" "
				    + "value=\""
				    + studentData[i].getContract().getId()
				    + "\" />");
		    tableBuilder
			    .append("<td><button name=\"command\" value=\"courseInfo\">"
				    + buttonInfo + "</button></td>");
		    tableBuilder
			    .append("<td><button name=\"command\" value=\"unsubscribe\">"
				    + buttonUnsubscribe
				    + "</button></td></form></tr>");
		}
		tableBuilder.append("</table>");
	    }
	    pageContext.getOut().write(tableBuilder.toString());
	} catch (Exception e) {
	    LOGGER.error("Problem in student table(tag)", e);
	    try {
		pageContext.getOut().write(STUDENT_TABLE_ERROR);
	    } catch (IOException e1) {
		LOGGER.error("Problem in student table(tag) error message", e1);
	    }
	}
	return SKIP_BODY;
    }
}
