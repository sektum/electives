/**
 * 
 */
package ua.epam.groys.electives.dao;

import ua.epam.groys.electives.entities.Lecturer;

/**
 * Interface describe method for work with entity {@link LecturerDao}
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public interface LecturerDao extends CommonDao<Lecturer> {
    /**
     * Returns object of entity {@link Lecturer} by key {@link courseId}.
     * 
     * @param courseId
     *            key of entity {@link Course}.
     * @return object of entity {@link Lecturer} if operation successful or null
     *         if not.
     */
    Lecturer getLecturer(Integer courseId);
}
