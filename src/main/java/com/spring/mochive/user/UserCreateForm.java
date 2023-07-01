package com.spring.mochive.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	
	//회원 가입을 위한 폼 클래스, 회원가입란에 적는 내용물들 검증하는 용도의 클래스 파일
	//회원가입 폼 클래스 : 사용자로부터 회원가입 입력정보를 받아 저장하는 클래스
	
	@Size(min = 3, max = 10)
	@NotEmpty(message = "이름 입력은 필수항목입니다.")
	private String name; //id는 그럼?안만드나? 흠 계속 id가 없네
	
	@NotEmpty(message = "비밀번호 입력은 필수항목입니다.")
	private String pwd1;
	
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String pwd2;
	
	@Size(min = 3, max = 25)
	@NotEmpty(message = "닉네임 입력은 필수항목입니다.")
	private String nickname;
	
	@NotEmpty(message = "연락처 입력은 필수항목입니다")
	private String phone;
	
	@NotEmpty(message = "이메일 입력은 필수항목입니다.")
	@Email
	private String email;
	

	//id에 대한 유효성 검증은 진앵하지 않는 이유 
	//답 : UserCreateForm 클래스 (유효성 검증 클래스)에서는 회원가입 입력값 유효성 검증하는 용도로만 사용된다면!!
	//굳이굳이 이 클래스에서는 id 필드에 대한 유효성 검증을 추가할 필요는 없음
}
