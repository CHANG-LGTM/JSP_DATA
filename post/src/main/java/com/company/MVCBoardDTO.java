package com.company;

import lombok.Getter;
import lombok.Setter;

// Lombok을 사용하여 getter와 setter 메소드를 자동으로 생성하는 클래스
@Getter  // 클래스에 포함된 모든 필드에 대한 getter 메소드 자동 생성
@Setter  // 클래스에 포함된 모든 필드에 대한 setter 메소드 자동 생성
public class MVCBoardDTO {

    private String idx;         // 게시물 고유 번호 (게시물 식별자)
    private String name;        // 게시물 작성자 이름
    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private java.sql.Date postdate; // 게시물 작성일
    private String ofile;       // 원본 파일명 (업로드된 파일의 원래 이름)
    private String sfile;       // 저장된 파일명 (서버에 저장된 파일 이름)
    private int downcount;      // 다운로드 횟수
    private String pass;        // 게시물 비밀번호 (비밀번호 보호 게시물)
    private int visitcount;     // 게시물 조회수

}
