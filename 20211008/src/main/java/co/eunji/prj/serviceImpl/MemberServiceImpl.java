package co.eunji.prj.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.eunji.prj.dao.DataSource;
import co.eunji.prj.service.MemberService;
import co.eunji.prj.service.MemberVO;
//전체조회하기, 한행만 조회, select, insert, delete, update 기능 6개가 기본!
public class MemberServiceImpl implements MemberService {
	private DataSource dao = DataSource.getInstance(); //driver load통해 연결하고
	private Connection conn;
	private PreparedStatement psmt;//conn통해 sql문 전달,실행, 결과는 밑에 rs가 받음 | preparedStatement DB와 연결해주는 비서역할
	private ResultSet rs; //select 구문의 결과를 받음, DBMS가 실행해서 그 결과만 리턴해줌.
	//이클립스에서 실행하는 것이 아님.!!
	
	@Override
	public List<MemberVO> selectMemberList() { //전체 리스트 가져오기, 리스트타입에 멤버보객체타입을 리턴
		List<MemberVO> list = new ArrayList<MemberVO>(); //사용할 변수 정의
		MemberVO vo; //사용할 변수 정의
		String sql = "select * from member";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql); //sql문을 전달함
			rs = psmt.executeQuery(); //sql문을 실행 하고 난 후, 결과를 rs에 받음
			while(rs.next()) {
				//rs의 값이 존재하면 여기서 값을 읽고 담아서 전달해준다.
				vo=new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setAuthor(rs.getString("author"));
				list.add(vo);//값을 list에다 저장. 저장될 값이 없으면 while문 빠져나가게 됨. rs.next()가 false가 될테니까
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(); //객체 닫는건 무조건 실행
		}
		return list; //받는곳에도 List<MemberVO> 타입이 있어야 한다.
	}

	@Override
	public MemberVO selectMember(MemberVO vo) { //한명 조회하기
		String sql = "select * from member where id = ?"; //?는 전달받은 인자로 검색하려고 할때
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId()); //물음표에 값을 담는 방법. 전달인자를 sql문에 넘겨주는 것. 물음표가 1개니까 1. 여러개일때는 물음표 위치가 2면 2입력
			rs = psmt.executeQuery();	
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setAuthor(rs.getString("author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo; //마지막에는 vo를 리턴해줌. 그러므로 받는쪽에서도 MemberVO객체가 필요함. vo를 받기위해서.
	}

	@Override
	public int insertMember(MemberVO vo) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		int n = 0;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getAddress());
			psmt.setString(6, vo.getAuthor());
			n = psmt.executeUpdate(); //성공하면 1, 실패하면 0. 한 행이 삽입되었으니 1이 날아옴.
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n; //값이 제대로 들어갔으면 1이 출력될 것
	}

	@Override
	public int updateMember(MemberVO vo) { //primary key인 id는 변경하지 않음
		int n = 0;
		String sql = "update member set name = ?, password = ?, tel = ?, address = ?, author = ? "
				+ "where id = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getTel());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getAuthor());
			psmt.setString(6, vo.getId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}

	@Override
	public int deleteMember(MemberVO vo) {
		int n = 0;
		String sql = "delete from member where id = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	private void close() { //사용한 객체를 반환한다. 메모리에서 clear시킴
		try {
			if(rs != null) rs.close(); //resultSet이 열려있으면 닫아라
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
