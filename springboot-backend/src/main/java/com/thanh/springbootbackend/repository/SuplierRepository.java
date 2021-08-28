package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Suplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierRepository extends CrudRepository<Suplier,Long> {
}
