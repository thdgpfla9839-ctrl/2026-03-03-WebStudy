package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
// Model 구분자 => 유일하게 컨트롤러와 연결된 부분
// 데이터 보내는 곳
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * MVC 구조
 * :  1. 브라우저에서 사용자 요청이 오면 URL 주소창을 이용한다
 *    2. 컨트롤러가 요청값을 받은 후 모델에 등록된 메소드를 찾아 요청 수행을 시킨다
 *       => 이 때 메소드를 찾는 과정이 자동호출이 되는데 그때 이용하는게 '어노테이션'이다 => @RequestMapping
 *    3. 완료된 내용(화면 출력과 관련된 결과값)을 jsp로 전송한다
 *    
 *      => 완료된 내용(출력)을 JSP로 전송 
 *    --------------- 구조 : SpringFramework / Spring-Boot 
 *                              |                  |
 *                              --------------------
 *                              @RequestMapping 
 *                                 |- @GetMapping
 *                                 |- @PostMapping
 *                              @Autowired / @Before @After 
 *                              @Component / @Repository / @Service
 */

@Controller
public class FoodModel {

	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response)
	{
		// 이렇게 처리하는 과정을 '비지니스 모델'이라고 한다
		// Model을 처리하는 애들 => Model, VO, DAO, Service + 자바 클래스는 모델이다
		// 1. 사용자 요청 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		// 2. 현재 페이지 저장
		int curpage=Integer.parseInt(page);
		
		// 3. 출력할 데이터 목록 가져오기
		List<FoodVO> list = FoodDAO.foodListData((curpage*12)-12);
		
		// 4. 총 페이지 
		int totalpage =FoodDAO.foodTotalPage();
		
		// 5. 블록별 처리
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1 11 21 31 ...
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 10 20 30 40 ...
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		return "../food/list.jsp";
	}
	
	@RequestMapping("food/list_ajax.do")
	public void foodListData_ajax(HttpServletRequest request, HttpServletResponse response)
	{
		        // 1. 사용자 요청 받기
				String page=request.getParameter("page");
				if(page==null)
					page="1";
				
				// 2. 현재 페이지 저장
				int curpage=Integer.parseInt(page);
				
				// 3. 출력할 데이터 목록 가져오기
				List<FoodVO> list = FoodDAO.foodListData((curpage*12)-12);
				
				// 4. 총 페이지 
				int totalpage =FoodDAO.foodTotalPage();
				
				// 5. 블록별 처리
				final int BLOCK=10;
				int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1 11 21 31 ...
				int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 10 20 30 40 ...
				if(endPage>totalpage)
					endPage=totalpage;
				
				JSONArray arr = new JSONArray(); // List를 자바스크립트에서 인식
				int i=0;
				for(FoodVO vo:list)
				{
					// vo와 일치하게 만드는게 JSONobject {}
					JSONObject obj = new JSONObject();
					obj.put("no", vo.getNo());
					obj.put("poster", vo.getPoster());
					obj.put("name", vo.getName());
					
					if(i==0)
					{
						obj.put("curpage", curpage);
						obj.put("totalpage", totalpage);
						obj.put("startPage", startPage);	
						obj.put("endPage", endPage);	
					}
					arr.add(obj);
					i++;
				}
				
				try
				{
					response.setContentType("text/plain;charset=UTF-8");
					PrintWriter out=response.getWriter(); // 브라우저를 찾아서 out에 입력하면 해당 브라우저를 읽어온다
					out.write(arr.toJSONString());
				}
				catch (Exception ex) {}
	}		
	
	@RequestMapping("food/detail.do")
	// "food/detail.do"이 중복이 되면 안 된다
	public String foodDetailData(HttpServletRequest request, HttpServletResponse response)
	{
		String no =request.getParameter("no");
		FoodVO vo =FoodDAO.foodDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		String[] address = vo.getAddress().split(" ");
		request.setAttribute("addr", address[2]);

		System.out.println(address[2]);
		request.setAttribute("rcount", 0);
		return "../food/detail.jsp";
	}
}