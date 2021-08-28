package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.Output;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutputRepository extends CrudRepository<Output,Long> {
    @Query("SELECT op FROM Output op ORDER BY op.id  DESC")
    List<Output> findAllDesc();
    @Query("SELECT outp FROM Output outp WHERE outp.id=?1 and outp.deleteFlag=0")
    Output findByIdOp(Long id);

    @Query(nativeQuery=true,value="SELECT * FROM Output op ORDER BY op.id desc limit 1")
    Output findByOpcuoi();
// Tổng doanh thu
    @Query(nativeQuery=true,value="select SUM(op.output_price*amount)" +
            "from  output_info as op join output as o on op.output_id=o.id")
    Double totalByOutput();
// Tổng đơn bán hàng
    @Query(nativeQuery=true,value="SELECT count(id) from output")
    int countOutput();
    // Doanh thu theo sản phẩm

}
