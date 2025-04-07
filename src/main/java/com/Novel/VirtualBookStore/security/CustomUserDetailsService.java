//package com.Novel.VirtualBookStore.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.Novel.VirtualBookStore.Service.UserService;
//import com.Novel.VirtualBookStore.entity.User;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	UserService userService;
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  //we will do this using email
//		
//		User user  = userService.getUserByEmail(email);
//		if(user==null) {
//			throw new UsernameNotFoundException(email);
//		}
//		
//		String userRole="USER";
//		for(String str:user.getRole()) {  //generally it runs only for 3-4 times maximum hence O(1) operation
//			if(str.equals("ADMIN")) {
//				userRole="ADMIN";
//				break;
//			}
//		}
//		
//		  
//		
//		return new org.springframework.security.core.userdetails.User(
//	            user.getUsername(),
//	            user.getPassword(),
//	            AuthorityUtils.createAuthorityList("ROLE_"+userRole)
//	     );
//	}
//
//}
