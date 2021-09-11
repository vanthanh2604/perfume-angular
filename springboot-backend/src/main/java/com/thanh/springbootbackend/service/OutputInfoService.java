package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.entity.OutputInfo;
import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.repository.OutInfoRepository;
import com.thanh.springbootbackend.repository.OutputRepository;
import com.thanh.springbootbackend.repository.PerfumeRepositorry;
import com.thanh.springbootbackend.service.serviceI.IOutputInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OutputInfoService
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

@Service
public class OutputInfoService implements IOutputInfoService {
    @Autowired
    private OutInfoRepository outInfoRepository;
    @Autowired
    private PerfumeRepositorry perfumeRepositorry;
    @Autowired
    private OutputRepository outputRepository;

    /**
     * get all output detail by id output
     * @param id
     */
    @Override
    public List<OutputInfo> getALlOutputInfoByIdInput(Long id) {
        return outInfoRepository.findAllByOutputId(id);
    }

    /**
     * create output info
     * @param outputInfo
     */
    @Override
    public void saveOuputInfo(OutputInfo outputInfo) {
        outInfoRepository.save(outputInfo);
    }

    /**
     * create output info
     * @param list
     * @param output
     */
    @Override
    public void addOutputInfo(List<InputInfoModel> list, Output output) {
        double total = 0;
        for (InputInfoModel item : list) {
            OutputInfo outputInfo = new OutputInfo();
            outputInfo.setAmount(item.getAmount());
            outputInfo.setOutputPrice(item.getPrice());
            outputInfo.setOutput(output);
            Perfume perfume = perfumeRepositorry.findPerfumeByIdAndFlag(item.getId());
            outputInfo.setPerfume(perfume);
            outInfoRepository.save(outputInfo);
            //======cập nhật lại số lượng sản phẩm==========
            perfume.setAmount(perfume.getAmount() - item.getAmount());
            perfumeRepositorry.save(perfume);
            total = total + (item.getAmount() * item.getPrice());
        }
        output.setTotal(total);
        outputRepository.save(output);
    }
}
