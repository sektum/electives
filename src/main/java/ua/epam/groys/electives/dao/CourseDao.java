package ua.epam.groys.electives.dao;

import java.util.List;

import ua.epam.groys.electives.entities.Course;

/**
 * Interface describes method for work with entity {@link Course}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public interface CourseDao extends CommonDao<Course> {
    /**
     * Method gets list of {@link Course} by key {@link lectureId}.
     * 
     * @param lectureId
     *            key of entity {@link Lecturer} in data hub.
     * @return list of {@link Course}.
     */
    List<Course> getLecturerCourses(Integer lectureId);
}
