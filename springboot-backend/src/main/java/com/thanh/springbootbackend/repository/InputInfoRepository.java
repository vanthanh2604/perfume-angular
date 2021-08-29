package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.InputInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputInfoRepository extends CrudRepository<InputInfo, Long> {
    @Query("SELECT inp FROM InputInfo inp WHERE inp.input.id=?1")
    List<InputInfo>findAllByInputId(Long id);
    // Lấy tổng số lượng sản phẩm đã nhập
    @Query(nativeQuery=true,value="select sum(amount) from input_info")
    int sumByAmount();
}
