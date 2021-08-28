package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Input;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputRepository extends CrudRepository<Input,Long> {
    @Query("SELECT inp FROM Input inp WHERE inp.deleteFlag=0 ORDER BY inp.id  DESC")
    List<Input> findAllDesc();
    @Query("SELECT inp FROM Input inp WHERE inp.id=?1 and inp.deleteFlag=0")
    Input findByIdIp(Long id);

    @Query(nativeQuery=true,value="SELECT * FROM Input inp ORDER BY inp.id desc limit 1")
    Input findByIdIpcuoi();

    //Tổng tiền nhập kho
    @Query(nativeQuery=true, value="select SUM(total) from input")
    Double totalByInput();
}