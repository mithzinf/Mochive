package com.spring.mochive.user;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user") //주소에 /user가 올 때라고 지정표 매뉴얼 정함
public class UserController {
	//이미 사용자 엔티티, 서비스, 폼까지 준비되어있어야지 -> 컨트롤러 개발 차례로 넘어갈 수 있는거야 알겠지?
	//컨트롤러 : 사용자가 입력하는 도메인 주소에 따라서 끼릭끼릭 절루 가라 이리가라 하는 지정표 매뉴얼같은 거
	
	 private final UserService userService;
	 //1. UserService를..주입함..왜죵?ㅋㅋ 이유 좀 알아야쓰겄어..
	 
	 //2. 질문 : RequestMapping과 GetMapping 차이점..하 시발 어카냐 나
	 @GetMapping("/signup")
	 public String signup(UserCreateForm userCreateForm) {
		 return "signup_form";
	 }
	
	 //Valid, BindingResult : 프레임워크 내의 validation 계열에 내장되어 있는 기능
	 @PostMapping("/signup")
	 public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		 if(bindingResult.hasErrors()) {
			 return "signup_form"; //signup_form : 회원가입 템플릿 파일인가바...
		 }
		 
		 if(!userCreateForm.getPwd1().equals(userCreateForm.getPwd2())) {
			 //UserCreateForm에서 겟터, 세터를 적은 이유.. getPwd1, getPwd2 메서드를 쓰기위해..오로지..
			 bindingResult.rejectValue("pwd2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다");
			 return "signup_form";
		 }
		 
		 userService.create(userCreateForm.getName(), userCreateForm.getPwd1(),
				 userCreateForm.getPhone(), userCreateForm.getEmail());
	 
	 
	 return "redirect:/";
	 
	 }
}
