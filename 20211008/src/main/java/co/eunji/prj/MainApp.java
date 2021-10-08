package co.eunji.prj;

//import java.util.ArrayList;
//import java.util.List;

import co.eunji.prj.exe.MainMenu;
//import co.eunji.prj.service.MemberService;
//import co.eunji.prj.service.MemberVO;
////import co.eunji.prj.dao.DataSource;
//import co.eunji.prj.serviceImpl.MemberServiceImpl; 

public class MainApp {
	public static void main(String[] args) { 
//		DataSource dao = DataSource.getInstance(); //싱글톤 클래스를부르려면 new로는 못하니까 그 클래스 참조하는 객체 설정하고
//		dao.getConnection();  //그 객체를 통해 리턴값이 싱글톤인 메소드를 불러옴
//		System.out.println("hello ~~~~");
		
//		MemberService memberService = new MemberServiceImpl(); //전체 리스트 조회
//		List<MemberVO> members = new ArrayList<MemberVO>();
//		
//		MemberVO vo = new MemberVO();//insert
//		vo.setId("kim");
//		vo.setPassword("4567");
//		vo.setName("김치국");
//		vo.setTel(null);
//		vo.setAddress(null);
//		vo.setAuthor("USER");
//		int n = memberService.insertMember(vo);
//		if(n != 0) {
//			System.out.println("잘 입력되었습니다.");
//		}else {
//			System.out.println("입력이 실패했습니다.");
//		}
//		
//		System.out.println("============================");
//		members = memberService.selectMemberList();
//		for (MemberVO member : members) {
//			member.toString();
//		}
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.run();
		
	}
}
