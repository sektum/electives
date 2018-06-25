package ua.epam.groys.electives.dao;

import java.util.List;

/**
 * Interface represents basic methods for work with data hub.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 * @param <T>
 *            type of data entity
 */
public interface CommonDao<T> {
    /**
     * Returns list of entities from data hub.
     * 
     * @return list of entities
     */
    List<T> getAll();

    /**
     * Returns {@link T} object by it {@link id} from data hub.
     * 
     * @param id
     *            key of data in data hub.
     * @return T object if {@link id} key contains data in data hub or null if
     *         not.
     */
    T getById(Integer id);

    /**
     * Inserts data from {@link object} to data hub.
     * 
     * @param entity
     *            data which inserts.
     * @return {@link object} if successful if not return null.
     */
    T insert(T object);

    /**
     * Inserts all entities which in {@link list} to data hub.
     * 
     * @param list
     *            collection of entities.
     * @return {@link list} if insert was successful or null if not.
     */
    List<T> insertAll(List<T> list);

    /**
     * Removes entity from data hub by {@link id}.
     * 
     * @param id
     *            key of entity in data hub.
     * @return true if entity was removed successful or false if not.
     */
    boolean remove(Integer id);

    /**
     * Removes all entities which in {@link list} from data hub.
     * 
     * @param list
     *            collection of entities which need to remove from data hub.
     * @return number of removed entities.
     */
    int removeAll(List<T> list);

    /**
     * Updates entity in data hub by {@link object}.
     * 
     * @param object
     *            update entity
     * @return true if operation is successful or false if not.
     */
    boolean update(T object);

    /**
     * Updates entities in data hub which in collection {@link list}.
     * 
     * @param list
     *            update entities collection
     * @return true if operation is successful or false if not.
     */
    int updateAll(List<T> list);
}
