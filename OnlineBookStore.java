package com.mystudy.onliebookstore_last;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineBookStore {

	private Scanner scan = new Scanner(System.in);
	
	private String sendAdd;
	private String sendName;
	private String sendPhone;
	private String p_dept ;
	private String p_no ;
	private int p_month ;
	private String sum;
	String m_id;
	
	OnlineBookStoreDAO dao = new OnlineBookStoreDAO();
	
	//0.(기본세팅)=================================================================
	//인덱스정보
	private void indexInfo() {
		System.out.println("=================================================");
		System.out.println("번 호 /        책 제 목       /    출 판 사    / 가 격");
		System.out.println("=================================================");
	}
	
	//인덱스장바구니
	private void indexCart() {
		System.out.println("=============================================================");
		System.out.println("번 호 /        책 제 목       /    출 판 사    / 가 격 / 수 량 / 총 액");
		System.out.println("=============================================================");
	}
	
	//인덱스 주문내역
	private void indexBuy() {
		System.out.println("=============================================");
		System.out.println("주문번호 / 아이디 / 전화번호 / 배송지 / 수취인 / 결제액");
		System.out.println("=============================================");
	}

	//인덱스 멤버
	private void indexMem() {
		System.out.println("================================================================");
		System.out.println("아이디 \t 이름 \t 생년월일 \t       주소        \t    전화번호");
		System.out.println("================================================================");
	}
	
	//인덱스 멤버
	private void indexMemManager() {
		System.out.println("================================================================");
		System.out.println("아이디 \t 비밀번호 \t 이름 \t 생년월일 \t       주소        \t    전화번호");
		System.out.println("================================================================");
	}
	
	//인덱스 게시판
	private void indexBoard() {
		System.out.println("==========================================");
		System.out.println("순번 / 작성자 / 비밀번호 /     내 용      / 작성일시");
		System.out.println("==========================================");
	}
		
	//1.(BOOK)========================================================
		
	//도서 전체 조회
	private void bookMenuAll () {
		indexInfo();
		List<BookVO> selectAll = dao.selectAll();
		for (BookVO vo : selectAll) {
			System.out.println(
					   vo.getG_no() + " / " + 
					   vo.getG_name() + " / "  + 
					   vo.getG_pub() + " / "  + 
					   vo.getG_price());
		}
		System.out.println();
	}
	
	//도서 번호로 조회
	private void bookMenuNo() {
		System.out.print(">>>>> 도서번호를 입력하세요 : ");
		String g_no = scan.nextLine();
		if (dao.searchBookNo(g_no) == false) {
			System.out.println("입력하신 도서번호의 상품이 없습니다.");
		}
		else {
			indexInfo();
			List<BookVO> list = dao.selectBookNo(g_no);
			for (BookVO vo : list) {
				System.out.println(vo.getG_no() + " / " + 
						   vo.getG_name() + " / "  + 
						   vo.getG_pub() + " / "  + 
						   vo.getG_price());
			}
			System.out.println();
		}
	}
		
	//도서명으로 도서 정보 조회
	private void bookMenuName() {
		System.out.print(">>>>> 도서명을 입력하세요 : ");
		String g_name = scan.nextLine();
		if (dao.searchBookName(g_name) == false) {
			System.out.println("입력하신 도서명의 상품이 없습니다.");
		}
		else {
			indexInfo();
			List<BookVO> list = dao.selectBookName(g_name);
			for (BookVO vo : list) {
				System.out.println(vo.getG_no() + " / " + 
						   vo.getG_name() + " / "  + 
						   vo.getG_pub() + " / "  + 
						   vo.getG_price());
			}
			System.out.println();	
		}
	}
	
	//2.(MEMBER)======================================================
	//id정보 중복값을 체크하여 입력하기
	private boolean checkIdEqual(String m_id) {
		if (dao.selectId(m_id) == false) {
			System.out.println("> 사용가능한 아이디입니다.");
			return true;
		}
		else {
			System.out.println("> 이미 존재하는 아이디입니다. 새로운 값을 입력하세요.");
		}
		return false;
	}
		
	//전화번호 형식에 맞게 입력하기 (정규표현식 패턴 사용)
		public boolean vaildPhoneNumber(String number) {
			Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
			Matcher matcher = pattern.matcher(number);
			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		}
		
		
	//로그인 서비스
	private void LoginService () {
		while (true) {
			System.out.print("ID를 입력해주세요 : ");
			m_id = scan.nextLine();
			if (m_id.equals("999")) {
				break;
			}
			System.out.print("Password를 입력해주세요 : ");
			String pwd = scan.nextLine();
	
			if (dao.login(m_id, pwd) == true) {
				System.out.println("\n***** " + m_id + " 님이 로그인 되었습니다. *****\n");
				break;
			}
			else {
				System.out.println(":::등록된 회원정보가 없습니다. ID/PW를 확인하세요. (종료 : 999) ");
			}
		}
	}

	//회원 가입 서비스
	public void JoinService () {
		String id;
		//중복값 체크 
		while(true) {
			System.out.print("회원 가입할 ID를 입력해주세요 (10자리 이하) : ");
			id = scan.nextLine();
			if (id.length() <= 10) {
				if(checkIdEqual(id) == true) {
					break;
				}
			} else {
				System.out.println("10자리 이하의 아이디만 사용 가능합니다");
			}
		}
		System.out.print("Password 를 입력해주세요 : ");
		String pwd = scan.nextLine();
		System.out.print("이름을 입력해주세요 : ");
		String name = scan.nextLine();
		System.out.print("주민등록번호 앞 6자리를 입력해주세요 : ");
		String joomin =  scan.nextLine();
		while (true) {
			if(joomin.length() != 6) {
				System.out.println(">> 올바른 6자리 값을 입력하세요.");
				System.out.print("주민등록번호 앞 6자리를 입력해주세요 : ");
				joomin =  scan.nextLine();
			}
			else {
				break;
				}
		}
		System.out.print("주소를 입력해주세요 : ");
		String address = scan.nextLine();
		
		while(true) {
			System.out.print("핸드폰 번호를 입력해주세요 ( - 포함 ) : ");
			String phone = scan.nextLine();
			if (vaildPhoneNumber(phone)) {
				dao.insertMember(id, pwd, name, joomin, address, phone);
				System.out.println("\n***** " + id +" 님 가입을 축하드립니다. *****\n");
				break;
			} else {
				System.out.println("형식에 맞게 입력해주세요");
				System.out.println();
				continue;
			}
		}
	}	
	
	//1-3. 회원 정보 조회
	private void MemberInfo() {

		while (true) {
			dao.selectPw(m_id);
			System.out.print(">> PASSWORD를 입력하세요 : ");
			String oldPw = scan.nextLine();
			String checkPw = dao.selectPw(m_id);		
			if (checkPw.equals(oldPw)) {
			    
				indexMem();
			    List<MemberVO> getMember = dao.getMember(m_id);
				for (MemberVO vo : getMember) {
					System.out.println(
							   vo.getM_id() + " / " + 
							   vo.getM_name() + " / "  + 
							   vo.getM_joomin() + " / "  + 
							   vo.getM_address() + " / "  +
							   vo.getM_phone());
			    
				} 	break;
			}
			 else {
				 System.out.println("잘못된 값입니다. 다시 확인하세요.");
			}
		}
	} 
		
	//회원 정보 수정
	private void MemberInfoUpdate() {
		while (true) {
			System.out.println("1.암호변경 \t 2.주소변경 \t 3.핸드폰번호변경");
			System.out.print(">>>> 선택: ");
			String temp = scan.nextLine();
			if(temp.equals("1")) {
				while (true) {
					dao.selectPw(m_id);
					System.out.print("기존 비밀번호를 입력하세요: ");
					String oldPw = scan.nextLine();
					String checkPw = dao.selectPw(m_id);		
					if (checkPw.equals(oldPw)) {
						System.out.print("변경할 암호를 입력하세요 : ");
						String newPw = scan.nextLine();	
						dao.updatePw(m_id, newPw);
						System.out.println("::: 암호변경 완료");
						break;
					} 
					else {
						System.out.println("잘못된 값입니다. 다시 확인하세요.");
					}
				}
			} else if (temp.equals("2")) {
				while (true) {
					dao.selectPw(m_id);
					System.out.print("PASSWORD를 입력하세요: ");
					String oldPw = scan.nextLine();
					String checkPw = dao.selectPw(m_id);		
					if (checkPw.equals(oldPw)) {
						System.out.print("변경할 주소를 입력하세요 : ");
						String add = scan.nextLine();	
						dao.updateAdd(m_id, add);
						System.out.println("::: 주소변경 완료");
						break;
					} 
					else {
						System.out.println("잘못된 값입니다. 다시 확인하세요.");
					}
				}
			} else if (temp.equals("3")) {
				while (true) {
					dao.selectPw(m_id);
					System.out.print("PASSWORD를 입력하세요: ");
					String oldPw = scan.nextLine();
					String checkPw = dao.selectPw(m_id);		
					if (checkPw.equals(oldPw)) {
						System.out.print("변경할 전화번호를 입력하세요 : ");
						String phone = scan.nextLine();	
						dao.updatePhone(m_id, phone);
						System.out.println("::: 번호변경 완료");
						break;
					} 
					else {
						System.out.println("잘못된 값입니다. 다시 확인하세요.");
					}
				}			
			} else {
				System.out.println("[입력값오류] 올바른 값의 숫자를 입력해주세요");
				continue;
			}
			System.out.println("\n***** 회원님의 정보 수정이 완료되었습니다. *****\n");
			break;
		}	
	}

	//회원 탈퇴 (회원 정보 삭제)
	private void DeleteMemberInfo () {
		while (true) {
			dao.selectPw(m_id);
			System.out.print("PASSWORD를 입력하세요: ");
			String oldPw = scan.nextLine();
			String checkPw = dao.selectPw(m_id);
			if (checkPw.equals(oldPw)) {
				while(true) {
					System.out.print("정말 탈퇴 하시겠습니까? (Y/N) : ");
					String yesNo = scan.nextLine();
					if (yesNo.equalsIgnoreCase("Y")) {
						dao.deleteMember(m_id);		
						System.out.println("\n::: 탈퇴가 완료되었습니다.");
						break;
					} else if (yesNo.equalsIgnoreCase("N")) {
						System.out.println("\n::: 탈퇴취소");
						break;
					} else {
						System.out.println("::: Y/N 중 선택해주세요");
						continue;
					}
				} break;
			} else if (!checkPw.equals(oldPw)) {
				System.out.println("잘못된 값입니다. 다시 확인하세요.");
			}
		}
	} 	

	//3.(CART)========================================================
	//장바구니 도서 담기
	private void insertBook(String m_id) {
		bookMenuAll();
		while (true) {
			int qty;
			System.out.print("장바구니에 담을 도서의 상품번호를 입력해주세요(종료 999): ");
			String g_no = scan.nextLine();
			
			if (dao.selectCartBook(m_id, g_no) == true) {
				System.out.println("::: 이미 담겨져 있는 상품입니다.");
				continue;
			}
			else if (g_no.equals("999")) {
				System.out.println(">>>>>> 장바구니를 종료합니다. ");
				break;
			}
			else if (dao.searchBookNo(g_no) == false){
				System.out.println("::: 올바르지 않은 상품번호입니다.");
				continue;
			}
		
			System.out.print(">> 담을 수량을 입력해주세요: ");
			//scan.nextLine();
			while (true) {
				try {
					qty = Integer.parseInt(scan.nextLine()); 
					if (qty >= 1 && qty <= 99) {
					dao.insertBook(m_id, g_no, qty);
					break;
					}
					else { 
						System.out.println("[입력값 오류] 장바구니 수량 범위 (1~99) 초과");
						break;
					}
				} catch (Exception e) {
					System.out.println("[입력값 오류] 올바른 숫자 값을 입력하세요.");		
					break;
				} 
			}
		}
	}
	
	//현재장바구니 확인하기
	private void dispCart(String m_id) {
		indexCart();
		List<BookVO> list = dao.selectCart(m_id);
		for (BookVO vo : list) {
			System.out.println(
				vo.getG_no() + " / " + 
				vo.getG_name() + " / "  + 
				vo.getG_pub() + " / "  + 
				vo.getG_price() + " / "  + 
				vo.getCl_qty() + "개 / " +
				vo.getSum() + "원"
				);
					    
		}
		dispSum(m_id);
		System.out.println();
	}
		
	//장바구니 수량 수정하기
	private void reviceQty(String m_id) {
		dispCart(m_id);
		int qty;
		while(true) {			
			
			System.out.print("수량을 변경할 도서번호를 입력하세요(종료 999): ");
			String g_no = scan.nextLine();
			
			if (dao.selectCartBook(m_id, g_no) == true) {
				System.out.print(">> 변경할 수량을 입력하세요: ");
				while (true) {
					try {
						qty = Integer.parseInt(scan.nextLine());
						if (qty >= 1 && qty <= 99) {
							dao.selectQty(m_id, g_no, qty);
							System.out.println(">> 상품번호 " + g_no + " 수정되었습니다. ");
							break;
						} else { 
							System.out.println("[입력값 오류] 장바구니 수량 범위 (1~99) 초과");
							break;
						}
						
					} catch (Exception e) {
						System.out.println("[입력값 오류] 올바른 숫자 값을 입력하세요.");		
						break;
					} 
				}
			}
			else if (g_no.equals("999")) {
				System.out.println(">>>>>> 장바구니를 종료합니다. ");
				break;
			}
			else if (dao.selectCartBook(m_id, g_no) == false){
				System.out.println("::: 장바구니에 존재하지 않는 상품입니다.");
			}
			System.out.println();
			
		}
	}
		
	//장바구니 도서 삭제하기
	private void deleteCartBook(String m_id) {
		dispCart(m_id);
		while(true) {			
			
			System.out.print("삭제할 도서번호를 입력하세요(종료 999):: ");
			String g_no = scan.nextLine();
			
			if (dao.selectCartBook(m_id, g_no) == true) {
				dao.deleteCartBook(m_id, g_no);
				System.out.println(">> 상품번호 " + g_no + " 삭제되었습니다. ");
			}
			else if (g_no.equals("999")) {
				System.out.println(">>>>>> 장바구니를 종료합니다. ");
				break;
			}
			else if (dao.selectCartBook(m_id, g_no) == false){
				System.out.println("::: 장바구니에 존재하지 않는 상품입니다.");
			}
		}
		System.out.println();
	}
		
	//장바구니 전체 삭제하기
	private void deleteCart(String m_id) {
		dao.deleteCartBook(m_id);
	}
		
	//4.(ORDER)=======================================================
	//주소입력하기(주문)
	private void insertAdd(String m_id) {
		List<MemberVO> list = dao.selectAdd(m_id);
		
		System.out.print("배송지를 입력하세요. (회원정보와 일치하면 0번을 입력하세요.): "); 
		sendAdd = scan.nextLine();		
		if (sendAdd.equals("0")) {
			for (MemberVO vo : list) {
				sendAdd = vo.getM_address();
				System.out.println("배송지 확인 : " + sendAdd);		
			}
		}
		else {
			System.out.println("배송지 확인 : " + sendAdd);		
		}
		System.out.println();
	}
	 	
	//이름입력하기(주문)
	private void insertName(String m_id) {
		List<MemberVO> list = dao.selectName(m_id);
		
		System.out.print("수취인명을 입력하세요. (회원정보와 일치하면 0번을 입력하세요.): "); 
		sendName = scan.nextLine();		
		if (sendName.equals("0")) {
			for (MemberVO vo : list) {
				sendName = vo.getM_name();
				System.out.println("수취인명 확인 : " + sendName);		
			}
		}
		else {
			System.out.println("수취인명 확인 : " + sendName);		
		}
		System.out.println();
	}
		
	//전화번호입력하기(주문)
	private void insertPhone(String m_id) {
		List<MemberVO> list = dao.selectPhone(m_id);
		
		System.out.print("전화번호를 입력하세요. (회원정보와 일치하면 0번을 입력하세요.): "); 
		sendPhone = scan.nextLine();		
		if (sendPhone.equals("0")) {
			for (MemberVO vo : list) {
				sendPhone = vo.getM_phone();
				System.out.println("전화번호 확인 : " + sendPhone);		
			}
		}
		else {
			System.out.println("전화번호 확인 : " + sendPhone);		
		}
		System.out.println();
	}
		
	//배송정보입력하기
	private void InsertOrderInfo(String m_id) {
		insertAdd(m_id);
		insertName(m_id);
		insertPhone(m_id);
		dispOrderInfo(m_id);
		while (true) {
			System.out.print(">> 위 정보로 주문을 진행하시겠습니까? (Y/N): ");
			String temp = scan.nextLine();
			if (temp.equalsIgnoreCase("y")) {
				System.out.println(">>> 결제페이지로 넘어갑니다.");
				System.out.println("===================================");
				break;
			} 
			else if (temp.equalsIgnoreCase("n")) {
				insertAdd(m_id);
				insertName(m_id);
				insertPhone(m_id);
				dispOrderInfo(m_id);
			}
			else {
				System.out.println("[입력값오류] Y/N 중에 하나를 입력하세요.");
			}
		}
		insertPay(m_id);
		deleteCartAnswer(m_id);
	}
	
	//배송정보 확인하기
	private void dispOrderInfo(String m_id) {
		System.out.println(">>> 주문정보");
		System.out.println("주소 : " + sendAdd);
		System.out.println("수취인 : " + sendName);
		System.out.println("전화번호 : " + sendPhone);
		System.out.println();
		}
	
	//회원별 주문확인하기
	private void dispOrder(String m_id) {
		List<BookVO> list = dao.selectBuy(m_id);
		for (BookVO vo : list) {
			System.out.println("===============================");
			System.out.println("1.주문번호: " + vo.getB_no());
			System.out.println("2.주소 : " + vo.getB_receive());
			System.out.println("3.수취인 : " + vo.getB_name());
			System.out.println("4.전화번호 : " + vo.getB_phone());
			System.out.println("5.결제액 : " + vo.getB_price() +"원");
			System.out.println("===============================");
		}
	}
	
	//총액확인하기
	private void dispSum(String m_id) {
		System.out.print("::: 총액 확인 : ");
		sum = dao.sumPrice(m_id);
		if(sum == null) {
			System.out.println("0원");
		}
		else {
			System.out.println(sum + "원");
		}
	}
		
	//장바구니 비울지 물어보기
	private void deleteCartAnswer(String m_id) {
		while (true) {
			System.out.print("장바구니에 담긴 상품을 삭제하시겠습니까? (Y/N): ");
			String answer = scan.nextLine();
			if (answer.equalsIgnoreCase("Y")) {
				deleteCart(m_id);
				System.out.print("::: 장바구니 비움");
				break;
			} 
			else if (answer.equalsIgnoreCase("N")) {
				System.out.print("::: 장바구니 유지");
				break;
			}
			else {
				System.out.println("[입력값오류] Y/N 중에 하나를 입력하세요.");
			}
			System.out.println();
		}
	}
	
	//주문내역 담기
	private void insertBuy(String m_id, String b_name, String b_phone, String b_receive, String b_price) {
		dao.insertBuy(m_id, b_name, b_phone, b_receive, b_price);
	}
	
	//5.(PAY)===================================================
	//결제정보입력하기
	private void insertPayment(String m_id) {
		String temp = null;
		
		System.out.print("> 카드사를 입력해주세요: ");
		p_dept = scan.nextLine();
		
		System.out.print("> 카드번호를 입력해주세요: ");
		p_no = scan.nextLine();
		
		while (true) {
			System.out.print("> 할부개월을 입력해주세요: ");
			try {
				p_month = Integer.parseInt(scan.nextLine()); 
				if (p_month >= 1 && p_month <= 12) {
					break;					
				}
				else {
					System.out.println("[입력범위오류] : 1~12 사이의 숫자값를 입력하세요.");
					continue;
				}
			} catch (Exception e) {
				System.out.println("[입력범위오류] : 1~12 사이의 숫자값를 입력하세요.");
				continue;
			}
		}

		while(true) {
			System.out.println("::: 결제 정보: " + p_dept + " / " + p_no + " / " + p_month + "개월");
			System.out.print("위 정보로 결제를 진행하시겠습니까? (Y/N): " );
			temp = scan.nextLine();
			if (temp.equalsIgnoreCase("Y")) {
				System.out.println("::: 결제완료");
				dao.insertPay(m_id, p_dept, p_no, p_month);
				break;
			} else if (temp.equalsIgnoreCase("N")) {
						System.out.println("::: 결제취소");
						insertPayment(m_id);
						break;
			} else {
					System.out.println("[입력값오류] Y/N 중에 하나를 입력하세요.");
					System.out.println();
					continue;
			}	
		}
	}
		
	//결제정보 중복값을 체크하여 입력하기
	private void insertPay(String m_id) {
		System.out.println(">> 결제정보 입력");
		String temp = null;
		if (dao.selectPayId(m_id) == false) {
			insertPayment(m_id);
			insertBuy(m_id, sendName, sendPhone, sendAdd, dao.sumPrice(m_id));
		}
		else {
			findPay(m_id);
			
			while(true) {
				System.out.print("기존 결제 정보가 존재합니다. 기존 정보로 결제하시겠습니까? (Y/N): ");
				temp = scan.nextLine();
				
				if(temp.equalsIgnoreCase("n")) {
					deletePay(m_id);
					insertPayment(m_id);
					insertBuy(m_id, sendName, sendPhone, sendAdd, dao.sumPrice(m_id));
					break;
				} 
				else if (temp.equalsIgnoreCase("y")) {
					System.out.println("::: 결제완료 ! 감사합니다.");
					insertBuy(m_id, sendName, sendPhone, sendAdd, dao.sumPrice(m_id));
					break;
					}
				else {
					while (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("N")) {
						System.out.println("[입력값오류] Y/N 중에 하나를 입력하세요.");
						System.out.print("기존 결제 정보가 존재합니다. 기존 정보로 결제하시겠습니까? (Y/N): ");
						temp = scan.nextLine();
						
						if(temp.equalsIgnoreCase("n")) {
							deletePay(m_id);
							insertPayment(m_id);
							insertBuy(m_id, sendName, sendPhone, sendAdd, dao.sumPrice(m_id));
							break;
						} 
						else if (temp.equalsIgnoreCase("y")) {
							System.out.println("::: 결제완료 ! 감사합니다.");
							insertBuy(m_id, sendName, sendPhone, sendAdd, dao.sumPrice(m_id));
							break;
							}
					}
				}
			}
		}
		System.out.println();
	}

	//기존 결제정보 조회
	private void findPay(String m_id) {
		dao.selectPayId(m_id);
		List<MemberVO> list = dao.selectPay(m_id);
		for (MemberVO vo : list) {
			System.out.println("::: 결제 정보 : " + vo.getP_dept() + " / " + vo.getP_no() + " / " + vo.getP_month() + "개월");
		}
	}	
		
	//결제정보 삭제하기
	private void deletePay(String m_id) {
		dao.deletePay(m_id);
	}
	
	//6.(BOARD)=======================================================
	//문의글 작성하기
	private void writeQnA(String m_id) {
		System.out.println("\n>>> 문의 글 작성");
		while (true) {
			dao.selectPw(m_id);
			System.out.print(">> PASSWORD를 입력하세요: ");
			String oldPw = scan.nextLine();
			String checkPw = dao.selectPw(m_id);		
			if (checkPw.equals(oldPw)) {
				System.out.println("------------- 문의하실 내용을 입력해주세요 -------------- ");
				String cont = scan.nextLine();
				dao.insetInquiry(m_id, oldPw, cont);
				System.out.println("::: 작성완료");
				break;
			}
			else {
				 System.out.println("비밀번호가 일치하지 않습니다");
			}
		}
	}

	//내가 남긴 문의글 확인하기
	private void checkMyInquiry(String m_id) {
		System.out.println("\n>>> 문의 글 확인하기");
		while (true) {
			dao.selectPw(m_id);
			System.out.print(">> PASSWORD를 입력하세요: ");
			String oldPw = scan.nextLine();
			String checkPw = dao.selectPw(m_id);		
			if (checkPw.equals(oldPw)) {
				System.out.println("\n<나의 문의 확인하기>");
				indexBoard();
				List<String> list = dao.selectInquiry(m_id, oldPw);
				for (String str : list) {
					System.out.println(str);
				}
				System.out.println(">>>>>> 끝\n");
				break;
			}
			else {
				System.out.println("잘못된 값입니다. 다시 확인하세요.");
			}
		}
	}
	
		
	//문의글 수정하기
	private void reviseMyInquiry(String m_id) {
		System.out.println("\n>>> 문의 글 수정하기");
		while (true) {
			dao.selectPw(m_id);
			System.out.print(">> PASSWORD를 입력하세요: ");
			String oldPw = scan.nextLine();
			String checkPw = dao.selectPw(m_id);		
			if (checkPw.equals(oldPw)) {
				System.out.println("\n<나의 문의 확인하기>");
				indexBoard();
				List<String> list = dao.selectInquiry(m_id, oldPw);
				for (String str : list) {
					System.out.println(str);
					}
				System.out.println();
				while(true) {
					try {
						System.out.print("수정할 문의글 순번을 선택해주세요 >> ");
						int index = Integer.parseInt(scan.nextLine());
						if(dao.boardindex(index) != false) {
							System.out.println("------------- 문의하실 내용을 입력해주세요 -------------- ");
							String cont1 = scan.nextLine();
							dao.updateInquiry(index, cont1);
							System.out.println("::: 수정완료");
							System.out.println();
							break;
						} else {
							System.out.println("::: 존재하지 않는 문의글 입니다");
						}
					} catch (Exception e) {
						System.out.println("[입력값오류] 숫자를 입력해주세요");
						continue;
					}
				} break;
				} else {
				System.out.println("잘못된 값입니다. 다시 확인하세요.");
			}
		}
	}
		
	//문의글 삭제하기
	private void deleteMyInquiry(String m_id) {
		System.out.println("\n>>> 문의 글 삭제하기");
		while (true) {
			dao.selectPw(m_id);
			System.out.print(">> PASSWORD를 입력하세요: ");
			String oldPw = scan.nextLine();
			String checkPw = dao.selectPw(m_id);		
			if (checkPw.equals(oldPw)) {
				System.out.println("\n<나의 문의 확인하기>");
				System.out.println("순번 / 작성자 / 내용 / 작성일시");
				System.out.println("==================================");
				List<String> list = dao.selectInquiry(m_id, oldPw);
				for (String str : list) {
					System.out.println(str);
					}
				System.out.println();
				
				while(true) {
					try {
						System.out.print("삭제할 문의글 순번을 선택해주세요 >> ");
						int num = Integer.parseInt(scan.nextLine());
						if (dao.boardindex(num) != false) {
							while(true) {
								System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
								String yesNo = scan.nextLine();
								if (yesNo.equalsIgnoreCase("Y")) {
									dao.deleteInquiry(num);
									System.out.println("::: 삭제완료");
									break;
								} else if (yesNo.equalsIgnoreCase("N")) {
									System.out.println("::: 삭제취소\n");
									break;
								} else {
									System.out.println("::: Y/N 중 입력해주세요");
									System.out.println();
									continue;
								}
							} break;	
						} else {
							System.out.println("::: 존재하지 않는 문의글 입니다");
						}
					} catch (Exception e) {
						System.out.println("[입력값오류] 숫자를 입력해주세요");
						continue;
					}
				} break;
			} else {
				System.out.println("잘못된 값입니다. 다시 확인하세요.");
			}
		}
	}
		
	//7.(MANAGER)======================================================
	//전체 주문내역 보기(관리자)
	private void checkAllOrder() {
		indexBuy();
		List<BookVO> list = dao.selectBuyAll();
		for (BookVO vo : list) {
			System.out.print(vo.getB_no() + " / " );
			System.out.print(vo.getM_id() + " / " );
			System.out.print(vo.getB_name() + " / " );
			System.out.print(vo.getB_phone() + " / " );
			System.out.print(vo.getB_receive() + " / " );
			System.out.print(vo.getB_price());
		System.out.println();
		}
	}
	
	//문의글 전체 확인하기(꽌리자)
	private void checkAllInquiry() {
		indexBoard();
		List<String> list = dao.inquiryAll();
		for (String str : list) {
			System.out.println(str);
		}
	}
	
	//전체 회원정보 보기(관리자)
	private void checkAllMember() {
		indexMemManager();
		List<MemberVO> list = dao.selectAllMember();
		for (MemberVO vo : list) {
			System.out.print(vo.getM_id() + " / " );
			System.out.print(vo.getM_pw() + " / " );
			System.out.print(vo.getM_name() + " / " );
			System.out.print(vo.getM_joomin() + " / " );
			System.out.print(vo.getM_address() + " / " );
			System.out.print(vo.getM_phone());
			System.out.println();
		}
	}
	
	//관리자페이지
	private void selectSystem() {
		while(true) {
			System.out.println("**** 관리자 페이지 접속 ****");
			System.out.print("관리자 아이디 입력: ");
			String id = scan.nextLine();
			
			System.out.print("관리자 비밀번호 입력: ");
			String pw = scan.nextLine();
			
			if(dao.selectSystemId(id) == true && dao.selectSystemPw(pw) == true) {	
				dispManager(id);
				break;
			} 
			else {
				System.out.println("::: ID/PW 값을 확인하세요");
			}
		}
	}
	
	//(최종기능구현)=====================================================
	//관리자페이지 접속
	private void dispManager(String id) {
		System.out.print("\n***** 메뉴를 선택하세요 *****\n");
		System.out.println("1. 전체 주문내역 확인");
		System.out.println("2. 전체 문의글 확인");
		System.out.println("3. 전체 회원정보 확인");
		System.out.println("0. [관리자 메뉴] 종료");
		
		while(true) {
			try {
				System.out.print("\n> 번호를 입력 하세요 : ");
				int select = Integer.parseInt(scan.nextLine());
				switch (select) {
					case 1: checkAllOrder(); dispManager(id); break;
					case 2: checkAllInquiry(); dispManager(id); break;
					case 3: checkAllMember(); dispManager(id); break;
					case 0: System.out.println("::: 종료"); break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); selectCart(id); 
				} break;
			} catch (Exception e) {
				System.out.println("::: 숫자를 입력하세요");
				continue;
			}
		}		
	}
	
	
	//장바구니+주문관리페이지
	private void selectCart(String id) {
		System.out.print("\n***** 메뉴를 선택하세요 *****\n");
	 	System.out.println("1. 현재 장바구니 확인");
        System.out.println("2. 장바구니 도서 추가");
        System.out.println("3. 장바구니 수량 변경");
        System.out.println("4. 장바구니 도서 삭제");
        System.out.println("5. 장바구니 주문 하기");
        System.out.println("6. 과거 주문 내역 조회");
        System.out.println("0. [장바구니 메뉴] 종료");
        
        while (true) {
	        try {
		        System.out.print("\n> 번호를 입력 하세요 : ");
		        int select = Integer.parseInt(scan.nextLine());
		        switch (select) {
			        case 1: dispCart(id); selectCart(id); break;
					case 2: insertBook(id); selectCart(id); break;
					case 3: reviceQty(id); selectCart(id); break;
					case 4: deleteCartBook(id); selectCart(id); break;
					case 5: InsertOrderInfo(id); selectCart(id); break;
					case 6: dispOrder(id); selectCart(id); break;
					case 0: System.out.println("::: 종료");break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); selectCart(id); break;
		        } break;
	        } catch (Exception e) {
	        	System.out.println("::: 숫자를 입력 하세요");
	        	continue;
	        }
       }      	
	}
	
	private void MemberManage() {
		System.out.print("\n***** 메뉴를 선택하세요 *****\n");
		System.out.println("1. 회원 정보 조회");
		System.out.println("2. 회원 정보 수정");
		//System.out.println("3. 회원 탈퇴");
		System.out.println("0. [회원정보 메뉴] 종료");
		
		while(true) {
			try {
				System.out.print("\n> 번호를 입력 하세요 : ");
				int select = Integer.parseInt(scan.nextLine());
				switch (select) {
					case 1: MemberInfo(); MemberManage(); break;
					case 2: MemberInfoUpdate(); MemberManage(); break;
					//case 3: DeleteMemberInfo(); break;
					case 0: System.out.println("::: 종료");break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); MemberManage(); break;
				} break;
			} catch (Exception e) {
				System.out.println("::: 숫자를 입력하세요");
				continue;
			}
		}		
	}
	
	//도서조회 관련
	private void BookMenuSelect() {
	    System.out.print("\n***** 메뉴를 선택하세요 *****\n");
		System.out.println("1. 전체 도서 조회");
		System.out.println("2. 도서 번호로 조회");
		System.out.println("3. 도서명으로 조회");
	    System.out.println("0. [도서조회 메뉴] 종료");
	    
	    while(true) {
		    try {
			    System.out.print("\n> 번호를 입력 하세요 : ");
				int select = Integer.parseInt(scan.nextLine());
			    switch (select) {
			        case 1: bookMenuAll(); BookMenuSelect(); break;
					case 2: bookMenuNo(); BookMenuSelect(); break;
					case 3: bookMenuName(); BookMenuSelect(); break;
					case 0: System.out.println("::: 종료");break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); BookMenuSelect(); break;
			    } break;
		    } catch (Exception e) {
		    	System.out.println("::: 숫자를 입력하세요");
		    	continue;
		    }
	    }    	
	}
	
	//회원메뉴구현
	private void loginNext() {
		System.out.println("\n>>> 아래 메뉴를 선택하여 주세요.");
		System.out.print("\n***** 메뉴를 선택하세요 *****\n");
		System.out.println("1. 회 원 정 보");
		System.out.println("2. 장 바 구 니");
	    System.out.println("3. 도 서 조 회");
	    System.out.println("4. 게  시  판");
	    System.out.println("5. 회 원 탈 퇴");
	    System.out.println("0. 로 그 아 웃");
	    
	    while(true) {
		    try {
			    System.out.print("\n> 번호를 입력 하세요 : ");
		        int select = Integer.parseInt(scan.nextLine());
		        switch (select) {
		        	case 1: MemberManage(); loginNext() ; break;
			        case 2: selectCart(m_id); loginNext(); break;
					case 3: BookMenuSelect(); loginNext();  break;
					case 4: board(m_id); loginNext();  break;
					case 5: DeleteMemberInfo(); break;
					case 0: System.out.println("::: 로그아웃 완료"); break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); loginNext(); break;
		        } break;
		    } catch (Exception e) {
		    	System.out.println("::: 숫자를 입력하세요");
		    	continue;
		    }
	    }    	
	}
	
	//게시판메뉴 구현
	private void board (String m_id) {
		System.out.print("\n***** 메뉴를 선택하세요 *****\n");
		System.out.println("1. 문의 글 작성");
        System.out.println("2. 문의 글 확인");
        System.out.println("3. 문의 글 수정");
        System.out.println("4. 문의 글 삭제");
        System.out.println("0. [게시판 메뉴] 종료");
        
        while(true) {
		    try {
		        System.out.print("\n> 번호를 입력 하세요 : ");
		        int select = Integer.parseInt(scan.nextLine());
		        switch (select) {
		        	case 1:  writeQnA(m_id); board(m_id); break;
			        case 2:  checkMyInquiry(m_id); board(m_id); break;
					case 3:  reviseMyInquiry(m_id); board(m_id);  break;
					case 4:  deleteMyInquiry(m_id);  board(m_id); break;
					case 0: System.out.println("::: 종료"); break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); board(m_id); break;
		        } break;
		    } catch (Exception e) {
		    	System.out.println("::: 숫자를 입력하세요");
		    	continue;
		    }
	    }    
	}
	
//====================================================================================
	//최종기능합치기
	public void start() {
		System.out.println("\n::::: 온라인 서점 방문을 환영합니다 :::::");
		System.out.println(">> 아래 메뉴를 선택하세요");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 관리자페이지");
		System.out.println("0. 프로그램 종료");
		
		while(true) {
			try {	
				System.out.print("\n> 번호를 입력 하세요 : ");
				int select = Integer.parseInt(scan.nextLine());
		
				switch (select) {
					case 1: JoinService(); start(); break;
					case 2: LoginService(); loginNext(); start(); break;
					case 3: selectSystem(); start(); break;
					case 0: System.out.println("::: 이용해주셔서 감사합니다!"); break;
					default: System.out.println("::: 올바른 번호를 입력하세요"); start(); break;
				} break;
			} catch (Exception e) {
				System.out.println("::: 숫자를 입력하세요");
				continue;
			}
		}	
	}
//=======================================================================================
	
}
 
