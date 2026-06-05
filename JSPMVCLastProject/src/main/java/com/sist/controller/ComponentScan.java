package com.sist.controller;
import java.util.*;
import java.io.*;

public class ComponentScan {
    // xml을 읽어오기
	
	public static List<String> componentScan(String path, String pack)
	{
		List<String> list = new ArrayList<String>();
		try
		{
			path = path+File.separator+pack.replace(".", File.separator);
			// ~ WEB-INF/까지 읽고 그 뒤에는 com.sist.model이 오게끔
			// 근데 저건 경로가 아니니 .을 \\로 바꾸려고 하고 있음
			
			File dir = new File(path);
			File[] files = dir.listFiles();
			for(File f:files)
			{
				System.out.println(f.getName());
				String name = f.getName();
				String ext = name.substring(name.lastIndexOf(".")+1);
				if(ext.equals("class")) //확장자가 클래스만 가져온다 => '.class'
				{
					// 우리는 클래스 이름만 필요해서
					// MainModel.class에서 . 앞에까지만 가져온다
					String clsName = name.substring(0,name.lastIndexOf("."));
					String packname = pack+"."+clsName;
					list.add(packname);
					System.out.println(packname);
					// com.sist.modelReplyModel 이런식으로 결과값 출력됨 실행은 디스패쳐파일에서한다
				}
			}
		}
		catch (Exception ex) {}
		return list;
	}
}
