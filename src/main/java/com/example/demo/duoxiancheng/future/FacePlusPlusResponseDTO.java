package com.example.demo.duoxiancheng.future;

import lombok.Data;

@Data
public class FacePlusPlusResponseDTO {

    private Long createTime;

    private Long userId;

    private String transDate;

    public FacePlusPlusResponseDTO(){}

    public FacePlusPlusResponseDTO(Long createTime, Long userId, String transDate) {
        this.createTime = createTime;
        this.userId = userId;
        this.transDate = transDate;
    }
}
