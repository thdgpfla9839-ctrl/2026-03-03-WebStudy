package com.sist.dao;
/*
 *  마이바티스는 오토커밋이 해제가 돼있음 => 트랜잭션 때문에
 *  primary가 중복이 없어 => 그래서 아이디와 태그명 중복되면 찾기 어렵
 */
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class BoardDAO {
   private static SqlSessionFactory ssf;
   static 
   {
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
		   // xml => 파싱 (프레임워크 => XML을 제공 => 파싱)
		   // XML / 크롤링 
		   // => 읽어가는 방법 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   /*
    *   <select id="boardListData" resultType="BoardVO" parameterType="int">
		    SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit
		    FROM jspboard
		    ORDER BY no DESC
		    OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		  </select>
   */
    public static List<BoardVO> boardListData(int start)
    {
    	SqlSession session=ssf.openSession();
    	List<BoardVO> list=session.selectList("boardListData",start);
    	session.close();
    	return list;
    }
   /*
		  <select id="boardRowCount" resultType="int">
		    SELECT COUNT(*) FROM jspboard
		  </select>
    */
    public static int boardRowCount()
    {
    	SqlSession session=ssf.openSession();
    	int count=session.selectOne("boardRowCount");
    	session.close();
    	return count;
    }
    /*
     *    <insert id="boardInsert" parameterType="BoardVO">
		    <!-- sequence : 자동 증가 번호 
		         #{} = setString() => ''
		         ${} = 일반문장열 => ''(X) = column/table
		    -->
		    <selectKey keyProperty="no" resultType="int" order="BEFORE">
		      SELECT NVL(MAX(no)+1,1) as no FROM jspboard
		    </selectKey>
		    INSERT INTO jspboard VALUES(
		      #{no},
		      #{name},
		      #{subject},
		      #{content},
		      #{pwd},
		      SYSDATE,
		      0
		    )
		  </insert>
     */
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session = ssf.openSession(true); // 커밋을 날릴 때는 반드시 괄호 안에 true를 쓴다
		session.insert("boardInsert",vo);
		session.close();

	}
	/*
    resultType    : 리턴형 => 없는 void => insert/update/delete
    parameterType : 매개변수 => 매개변수가 없는 경우 
    public BoardVO boardDetailData(int no)
    
    SQL문장의 반복 = <sql>
    */
	
	//             리절트타입     아이디       파라미터타입부분
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session=ssf.openSession(true);
		session.update("hitIncrement",no);
		BoardVO vo=session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	
	// 게시글 삭제 => 삭제 시 비밀번호 입력
	// 우리는 서비스처리를 안 해놔서 이렇게 코드함 -> boolean 때문에
	public static boolean boardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		SqlSession session = ssf.openSession(true); // delete니까 커밋이 날라가야함  => delete / insert는 데이터가 변경되니까 반드시 커밋을 날려줘야함
		String db_pwd = session.selectOne("boardGetPassword",no); // mapper에서 받는 값을 스트링으로 지정했으니까 여기서 스트링으로 받아야 함
		if(db_pwd.equals(pwd)) // 디비에 비번이랑 내가 넘기려는 비번이랑 일치하냐
		{
			bCheck=true;
			session.delete("boardDelete",no);
			// session.commit(); 만약 위에 true를 안 줄거면 여기에 이 문장을 추가해서 오토커밋을 해줄 수 있다
		}
		session.close();
		return bCheck;
		
	} // 해당 기능 디비 완성 => 모델에 가서 기능을 처리해준다
	
	// 게시글 수정 => 게시글 수정하기 기능과 이전에 썼던 내용도 불러오게 하는 기능 만들기
	public static BoardVO boardUpdateData(int no)
	{
		// 마이바티스와 연결
		SqlSession session = ssf.openSession(); // 여기는 커밋 필요가 없어서 true 안 함
		BoardVO vo = session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	} // 위 데이터를 받아서 넘기는 건 모델에 가서 처리하자
	
	// 게시글 수정 후 실제 수정글이 저장되게 하는 기능
	public static boolean boardUpdate(BoardVO vo)
	{
		boolean bCheck=false;
		SqlSession session = ssf.openSession(true);
		String db_pwd = session.selectOne("boardGetPassword",vo.getNo()); 
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck=true;
			session.delete("boardUpdate",vo);
			
		}
		session.close();
		return bCheck;
		
	}
}
