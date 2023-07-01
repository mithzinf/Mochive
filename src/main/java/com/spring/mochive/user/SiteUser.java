package com.spring.mochive.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
	
	//회원(사용자)를 관리할 SiteUser 엔티티 프라잘 작성

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // unique = true : 유일한 값만 저장 == 중복값 저장 금지
    //이름, 닉네임, 전화번호, 이메일 이정도?
    
    private String pwd;
    
    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String nickname;
    
    @Column(unique = true)
    private String phone;
    
    @Column(unique = true)
    private String email;
    
    
}
