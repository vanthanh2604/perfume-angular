package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.InputInfo;
import com.thanh.springbootbackend.entity.OutputInfo;

import com.thanh.springbootbackend.model.RevenuePerfumeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OutInfoRepository
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
public interface OutInfoRepository extends CrudRepository<OutputInfo,Long> {

    /**
     * find all by outputId
     * @param id
     * @return
     */
    @Query("SELECT op FROM OutputInfo op WHERE op.output.id=?1")
        List<OutputInfo> findAllByOutputId(Long id);

    /**
     * count by perfume outPut
     * @return
     */
    @Query(nativeQuery=true, value="select SUM(opi.amount)" +
            "  from  output_info as opi left join output as o on opi.output_id=o.id")
    int countByPerfumeOutPut ();

    /**
     * profit
     * @return
     */
    @Query(nativeQuery=true, value="select sum((opi.amount*opi.output_price)-(opi.amount*p.price))\n" +
            "   from  output_info as opi right join perfume as p on opi.perfume_id=p.id;")
    Double profit ();

    /**
     * revenue by perfume
     * @return
     */
    @Query(nativeQuery=true, value=" select per.id,per.perfume_name ,avg(op.output_price),SUM(op.output_price*op.amount) as revenue,\n" +
            "  SUM((op.output_price*op.amount)-(per.price*op.amount)) as profit, Sum(op.amount) as amount\n" +
            "           from perfume as per join output_info as op on per.id=op.perfume_id\n" +
            "            GROUP BY op.perfume_id;")
    List<?> revenue_by_perfume ();

    /**
     * revenue by month
     * @return
     */
    @Query(nativeQuery=true, value="select month(o.date_output) as  \"Tháng\" , sum(opi.amount) as \"Số lượng bán\",sum(opi.output_price * opi.amount) as \"Tổng tiền xuất\",sum((opi.output_price * opi.amount)-(per.price * opi.amount)) as \"Lợi nhuận\"\n" +
            "from output_info opi join output o on opi.output_id=o.id left join perfume per on opi.perfume_id=per.id where year(o.date_output)=year(now()) \n" +
            "group by month(o.date_output), year(o.date_output);")
    List<?> revenue_by_month ();

    /**
     * sum by amount
     * @return
     */
    @Query(nativeQuery=true,value="select sum(amount) from output_info")
    int sumByAmount();
}
