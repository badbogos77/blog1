package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	//스프링이 로그인 요청을 가로챌때, Username, password 변수 2개를 가로채는데
	//password 부분 처리는 알아서 함
	//Username이 db에 있는지만 확인해줌
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LoadUserByUserName Service");
			User principal = userRepository.findByUsername(username)
					.orElseThrow(()-> {
						return new UsernameNotFoundException("해당 사용자를 찾을수없습니다");
					});
			
			System.out.println("PrincipalDetail(principal)"+ principal);
			return new PrincipalDetail(principal);
	}

}
