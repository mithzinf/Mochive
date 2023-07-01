package com.spring.mochive.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	//본격 회원가입 메소드..
	public SiteUser create(String name, String pwd, String phone, String email) {
		
		SiteUser user = new SiteUser();
		user.setName(name);
		user.setEmail(email);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //시큐리티의 BCryptPasswordEncoder 클래스 객체를 새로 생성한 방법 : 비밀번호 암호화해주는 시큐리티 내장된 클래스 기능
		user.setPwd(passwordEncoder.encode(pwd)); //이어서) BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 암호화해줌
		this.userRepository.save(user);
		return user;
	}
	
	//비밀번호 암호화 방법이 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();만 있는 것은 아님
	//위의 방식은 만약 암호화 방식을 변경하게ㅐ 되면 BCryptPasswordEncoder를 사용한 모든 프로그램을 일일이 찾아 수정해야 하기 때매..
	
	//비밀번호 암호화 제일 쉬운 방법 : == PasswordEncoder 빈(bean)을 만드는 가장 쉬운 방법 
	//(골뱅이)configuration이 적용된 SecurityConfig파일에 (골뱅이)Bean 메서드를 생성하는 것임
	
	//나중에 이 방법 써볼까나?
	
	
	
}
