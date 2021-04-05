package com.example.demo.duoxiancheng.future;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FacePlusPlusAccountService {

    /**
     *  根据useId 查询faceRequestLogDAO中face请求数据 执行五分钟去重
     */
    public List<FacePlusPlusResponseDTO> facePlusPlusFilterRecordBy2ZeroFace(String userId) throws Exception {
        List<FacePlusPlusResponseDTO> facePlusPlusResult = new ArrayList<>();

        //List<FacePlusPlusResponseDTO> facePlusPlusResponseDTOS = faceRequestLogDAO.getFacePlusPlusRiskRecordListBy2ZeroAndSourceById(facePlusPlusRequestBDTO);

        return facePlusPlusResult;
    }
}
