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

@Service
public class OutputInfoService implements IOutputInfoService {
 @Autowired
 private OutInfoRepository outInfoRepository;
 @Autowired
 private PerfumeRepositorry perfumeRepositorry;
 @Autowired
 private OutputRepository outputRepository;
 @Override
 public List<OutputInfo> get_ALl_OutputInfo_by_idInput(Long id) {
  return outInfoRepository.findAllByOutputId(id);
 }

 @Override
 public void saveOuputInfo(OutputInfo outputInfo) {
  outInfoRepository.save(outputInfo);
 }

 @Override
 public void addOutputInfo(List<InputInfoModel> list, Output output) {
     double total=0;
  for (InputInfoModel item:list) {
    OutputInfo outputInfo =new OutputInfo();
    outputInfo.setAmount(item.getAmount());
    outputInfo.setOutputPrice(item.getPrice());
    outputInfo.setOutput(output);
    Perfume perfume= perfumeRepositorry.findByCode(item.getPerfume_code());
    outputInfo.setPerfume(perfume);
    outInfoRepository.save(outputInfo);
   //======cập nhật lại số lượng sản phẩm==========
   perfume.setAmount(perfume.getAmount()-item.getAmount());
   perfumeRepositorry.save(perfume);
   //======total=========
   total=total+(item.getAmount()*item.getPrice());
  }
  output.setTotal(total);
  outputRepository.save(output);
 }
}