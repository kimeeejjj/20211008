package co.eunji.prj.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {		//DB연결해보자, 싱글톤방식으로. 연결객체만들기
	private static DataSource dataSource = new DataSource();//DataSuorce클래스의 유일한 객체 dataSource를 private로 만듦
	private Connection conn;
	private String driver; //내 이클립스에 연결할 드라이버는 뭐냐 -> driver = oracle.jdbc.OracleDriver (src/main/resources밑 db.properties파일에 저장되어있음)
	private String url; //드라이버의 주소 -> src/main/resources밑 db.properties파일에 저장
	private String user;//사용자이름 - 나는 eunji, src/main/resources밑 db.properties파일에 저장
	private String password; //사용자 비밀번호 - 나는 1234 , src/main/resources밑 db.properties파일에 저장
	
	
	private DataSource() {
		dbconfig(); //생성될때 이 값들을 전부 불러와버리자
	}	//외부에서 생성할 수 없도록 생성자를 private으로
	
	public static DataSource getInstance() { //외부에서 사용하려면 DataSuorce클래스의 유일한 객체가 private이므로 getInstance()메소드를 사용해야 함
		return dataSource; //외부에서 이 메소드를 불러오면 DataSuorce클래스의 유일한 객체인 dataSource를 리턴해주겠다.
	} //여기까지가 싱글톤클래스 기본
	
	private void dbconfig() { //외부properties파일을 읽어서 값을 저장한다.
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath(); //파일을 가져옴, getClass는 자기가 갖고 있는 클래스에서 객체를 가져오는것
		//리소시즈에 있는 파일 읽어서 리소스라는 타입으로 저장
		try {
			properties.load(new FileReader(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");  //내외부 변수명 동일하게 해야 편함
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public Connection getConnection() {
		
		try {
			Class.forName(driver);  //ojdbc를 연결(사용할 드라이버를 로드시킴)
			conn = DriverManager.getConnection(url,user,password); //connection객체는e드라이버매니저를 통해서 세가지값들을 넘김
			System.out.println("DB 연결성공");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결실패");
		}
		return conn;
	}
	
}
