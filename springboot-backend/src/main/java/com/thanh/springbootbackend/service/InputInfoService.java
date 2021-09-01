package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.InputInfo;
import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.repository.InputInfoRepository;
import com.thanh.springbootbackend.repository.InputRepository;
import com.thanh.springbootbackend.repository.PerfumeRepositorry;
import com.thanh.springbootbackend.service.serviceI.IInputInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputInfoService implements IInputInfoService {
    @Autowired
    private InputInfoRepository inputInfoRepository;
    @Autowired
    private PerfumeRepositorry perfumeRepositorry;
    @Autowired
    private InputRepository inputRepository;
    @Override
    public List<InputInfo> get_ALl_InputInfo_by_idInput(Long id) {
        return inputInfoRepository.findAllByInputId(id);
    }

    @Override
    public void addInputInfo(List<InputInfoModel> infoDto, Input input) {
        double total=0;
        for (InputInfoModel item:infoDto) {
            InputInfo inputInfo=new InputInfo();
            inputInfo.setAmount(item.getAmount());
            inputInfo.setInputPrice(item.getPrice());
            inputInfo.setInput(input);
            Perfume perfume= perfumeRepositorry.findPerfumeByIdAndFlag(item.getId());
            inputInfo.setPerfume(perfume);
            inputInfoRepository.save(inputInfo);
            //======cập nhật lại số lượng và giá sản phẩm==========
            perfume.setAmount(perfume.getAmount()+item.getAmount());
            perfume.setPrice(item.getPrice());
            perfumeRepositorry.save(perfume);

            total=total+(item.getAmount()*item.getPrice());
        }
        input.setTotal(total);
        inputRepository.save(input);
}
}
