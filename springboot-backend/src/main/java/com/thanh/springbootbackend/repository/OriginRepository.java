package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Origin;
import com.thanh.springbootbackend.entity.OutputInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends CrudRepository<Origin,Long> {
}
