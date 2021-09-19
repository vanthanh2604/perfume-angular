package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * UserRepository
 * Version 1.0
 *
 * Date: 01-09-2021
 *
 * Copyright
 *
 * Modification Logs:
 *  DATE                 AUTHOR          DESCRIPTION
 *  -----------------------------------------------------------------------
 *   01-09-2021         ThanhNV80            Create
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * find by username
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * exists by username
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);
}
