package co.eunji.prj.service;

import java.util.List;

public interface MemberService {
	//데이터베이스에서 가장 기본적인것 crud(생성insert,조회select,업데이트,삭제)
	//인터페이스는 crud를 미리 만들어놓는것
	List<MemberVO> selectMemberList(); //전체 목록 가져오기
	MemberVO selectMember(MemberVO vo); //원하는 한명 가져오기
	int insertMember(MemberVO vo); //한명의 데이터 삽입. DB에서 "1행이 삽입되었습니다"의 값이 int타입 1이므로
	int updateMember(MemberVO vo);	//한명의 데이터 변경
	int deleteMember(MemberVO vo); //한명의 데이터 삭제
}
//preparedstatement= connection객체 통해서 sql명령을 전달하고 결과를 돌려받는 객체
//DB의 select구문은 ResultSet객체를 통해서 값을 받는다.
//DB의 insert구문은 결과를 int타입으로 리턴시켜줌. 그래서 위에도 다 int타입으로 명시해둔것 (insert성공=성공갯수만큼,실패=0)
//DB의 update구문도 마찬가지로 결과를 int타입으로 리턴(update성공=성공한만큼의갯수,실패=0)
//DB의 delete구문도 마찬가지로 int타입으로 결과 돌려받음(delete성공=삭제한만큼, 실패=0)
//셀렉트 명령은 preparedstatement객체가 가지고 있는 executeQuery()메소드 통해서 실행시킬 수 있음
//나머지 insert,update,delete구문은 exeuteUpdate()메소드로 실행

//1.connection객체를 연결
//2.연결된 객체를 통해서 preparedstatement를 만듦. 3.preparedstatement=conncetion sql구문전달
//4.preparedstatement값 받아와서 실행. select result set
//5.가져온 값은 next()통해 읽고 get타입("컬럼명")
//읽을때는 rs.get타입("컬럼명")
// APP <---> VO <---> DBMS :이런구조임
// List<MemberVO> selectMemberList();
// MemberVO selectMember(MemberVO vo); 그래서 이런게 필요

//제일먼저 드라이브 로드(데이터 소스), 커넥션 객체 통해서 연결하고, 커넥션 객체를 통해 preparedstatement객체를 생성. 생성과 동시에
//DBMS에게 sql구문 정리. 내가 보낸 sql구문 실행시켜 주세요하는게 executeQuery사용. 그 결과를 resultset에 rs라는 변수 만들어서
//거기다가 값을 저장. 다음 값들을 보고 싶으면 next()사용. 근데 뒤에 값이 존재하면 읽어지는데 없으면 eof를 리턴시키는데 
//리턴값이 -1되니까 false값이 됨. 그래서 while구문을 이용해서 rs.next()가 값이 있으면 실행하고 없으면 while빠져나가서 finlall로 

