package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.common.ERole;
import com.thanh.springbootbackend.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
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
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(ERole name);
}
