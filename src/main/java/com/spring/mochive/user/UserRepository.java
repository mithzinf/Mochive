package com.spring.mochive.user;

import org.springframework.data.jpa.repository.JpaRepository;

//순서 : SiteUser(DTO) -> UserRepository(리포지터리) -> UserService(서비스) -> 컨트롤러
//User 리포지터리 : 인터페이스로 만들어!

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	
	// <SiteUser, Long> 은 JpaRepository를 상속받는 'UserRepository' 인터페이스의 매개변수, 엔티티 클래스와 기본키 타입을 정의,지정하는 역할을 함
	
	//UserRepository 인터페이스 : SiteUser 엔티티에 대한 데이터 접근 작업을 정의하고, Jparepository가 제공하는 기본적인 crud 기능을 상속받아 사용할 수 있다
	
	
	

}
