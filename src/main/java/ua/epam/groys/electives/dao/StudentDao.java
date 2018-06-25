/**
 * 
 */
package ua.epam.groys.electives.dao;

import java.util.List;

import ua.epam.groys.electives.entities.Contract;
import ua.epam.groys.electives.entities.Student;

/**
 * Interface describes methods for work with entity {@link Student}
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public interface StudentDao extends CommonDao<Student> {
    /**
     * Return object of entity {@link Student} from data hub by
     * {@link contractId}.
     * 
     * @param contractId
     *            key field of entity {@link Contract}.
     * @return object of entity {@link Student} if successful or null if not.
     */
    Student getStudent(Integer contractId);

    /**
     * Return list of entities {@link Student} by list of entities
     * {@link Contract}
     * 
     * @param contracts
     *            list of entities {@link Contract}
     * @return list entities of {@link Student}
     */
    List<Student> getAllStudent(List<Contract> contracts);
}
