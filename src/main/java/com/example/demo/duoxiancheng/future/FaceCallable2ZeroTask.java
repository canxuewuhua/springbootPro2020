package com.example.demo.duoxiancheng.future;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Callable;

public class FaceCallable2ZeroTask implements Callable<List<FacePlusPlusResponseDTO>> {

    @Autowired
    private FacePlusPlusAccountService facePlusPlusAccountService;

    private String userId;

    public FaceCallable2ZeroTask(FacePlusPlusAccountService facePlusPlusAccountService, String userId){
        this.facePlusPlusAccountService = facePlusPlusAccountService;
        this.userId = userId;
    }

    @Override
    public List<FacePlusPlusResponseDTO> call() throws Exception {
        List<FacePlusPlusResponseDTO> facePlusPlusResult = facePlusPlusAccountService.facePlusPlusFilterRecordBy2ZeroFace(userId);
        return facePlusPlusResult;
    }
}
