package com.sist.model;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.dao.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// 브라우저로 값을 전송하는 클래스
@Controller

public class EmpModel {
 
	@RequestMapping("js/js_8.do")
	public void empListData(HttpServletRequest request, HttpServletResponse response)
	{
		// 데이터 값을 받는다
		List<EmpVO> list = EmpDAO.empListData();
		
		// 자바스크립트와 연동
		// json 연결
		JSONArray arr = new JSONArray(); // List => [{},{},{}....] => JsonArray
		for(EmpVO vo:list) // vo => {}
		{
			JSONObject obj = new JSONObject();
			obj.put("empno", vo.getEmpno());
			obj.put("ename", vo.getEname());
			obj.put("job", vo.getJob());
			obj.put("dbday", vo.getDbday());
			obj.put("dname", vo.getDvo().getDname());
			obj.put("loc", vo.getDvo().getLoc());
			
			arr.add(obj);
		}
		
		// 브라우저로 전송
		try
		{
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		}
		catch (Exception ex) {}
		System.out.println(arr.toJSONString()); 
	}
}
