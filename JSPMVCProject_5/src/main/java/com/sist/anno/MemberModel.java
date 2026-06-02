package com.sist.anno;
@Controller
public class MemberModel {
	
	@RequestMapping("member/login.do")
  public void login() 
  {
	System.out.println("login() Call");  
  }

	@RequestMapping("member/join.do")
  public void join() 
  {
	System.out.println("join() Call");  
  }
}
