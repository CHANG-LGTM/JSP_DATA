package com.company;

import lombok.Getter;
import lombok.Setter;

// MyFileDTO 클래스는 파일 정보를 저장하는 DTO(Data Transfer Object) 클래스입니다.
@Getter
@Setter
public class MyFileDTO {
    
    // 파일 고유 ID
    private String idx;
    
    // 파일 제목
    private String title;
    
    // 파일 카테고리
    private String cate;
    
    // 원본 파일 이름
    private String ofile;
    
    // 서버에 저장된 파일 이름
    private String sfile;
    
    // 파일 등록 날짜
    private String postdata;
}
