package com.care.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

//DB 테이블과 매핑되는 JPA 엔티티
@Entity
//Lombok이 자동으로 getter / setter 생성
@Getter @Setter
//id만 가지고 equals / hashCode 비교?
@EqualsAndHashCode(of = "id")
//객체를 쉽게 생성할 수 있는 빌더패턴 지원
@Builder
//모든 필드를 매개변수로 받는 생성자 생성
@AllArgsConstructor
//파라미터 없는 기본 생성자 생성
@NoArgsConstructor

//사용자(회원)정보를 담는 DB 테이블과 연결된 JPA 엔티티 클래스
public class Account {

    //기본키(PK)
    @Id
    //ID 값을 자동으로 생성하게 설정
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    //이메일
    private String email;

    @Column(unique = true)
    //닉네임
    private String nickname;

    //비밀번호
    private String password;

    //이메일 인증여부
    private boolean emailVerified;

    //이메일 인증을 위한 토큰(랜덤 문자열)
    private String emailCheckToken;

    //회원가입 날짜
    private LocalDateTime joinedAt;

    //자기소개
    private String bio;

    //개인 블로그 , 링크
    private String url;

    //직업
    private String occupation;

    //지역
    private String location;

    //큰 데이터를 저장하기 위해 사용(대용량 문자열 등)
    @Lob
    @Basic(fetch = FetchType.EAGER)
    //프로필 이미지
    private String profileImage;

    //스터디 생성 시 이메일로 알림받을지 여부
    private boolean studyCreatedByEmail;

    //스터디 생성 시 웹으로 알림받을지 여부
    private boolean studyCreatedByWeb;

    //스터디 신청결과를 이메일로 알림받을지 여부
    private boolean studyEnrollmentResultByEmail;

    //스터디 신청결과를 웹으로 알림받을지 여부
    private boolean studyEnrollmentResultByWeb;

    //스터디 내용 변경 시 이메일로 알림받을지 여부
    private boolean studyUpdatedByEmail;

    //스터디 내용 변경 시 웹으로 알림받을지 여부
    private boolean studyUpdatedByWeb;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }
}
