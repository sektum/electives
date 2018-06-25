package ua.epam.groys.electives.dao;

import ua.epam.groys.electives.entities.AuthorizedUser;

/**
 * Interface represented method for work with Entity {@link AuthorizedUser}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public interface AuthorizeUserDao {
    /**
     * Method makes user authorization using data hub.
     * @param name user login
     * @param pwd user password
     * @return AuthorizedUser object is successful or null if not
     */
    abstract AuthorizedUser authorize(String name, String pwd);
}
