package com.mystudy.onliebookstore_last;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnlineBookStoreDAO {
	
//	private static final String DRIVER = "oracle.jdbc.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@192.168.18.30:1521:xe";
//	private static final String USER = "book";
//	private static final String PASSWORD = "bookpw";
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "PROJECT";
	private static final String PASSWORD = "PROJECT";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//1.(기본세팅)=========================================================================
	//static 초기화 구문
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생] JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	//클로징 처리에 의한 자원 반납
	private void close(Connection conn, PreparedStatement pstmt) {
		try {
	
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//클로징 처리에 의한 자원 반납(ResultSet)
	private void close(Connection conn, PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn,pstmt);
	}
	
	//2.(도서관리)======================================================================
	//전체 도서 정보 확인하기
	public List<BookVO> selectAll() {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT G_NO, G_NAME, G_PUB, G_PRICE ");
			sql.append("FROM GOODS ");
			sql.append("ORDER BY G_NO ");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getString("G_NO"), 
						rs.getString("G_NAME"), 
						rs.getString("G_PUB"), 
						rs.getInt("G_PRICE"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	//도서 이름으로검색하기
	public List<BookVO> selectBookName(String g_name) {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT G_NO, G_NAME, G_PUB, G_PRICE ");
			sql.append("FROM GOODS ");
			sql.append("WHERE G_NAME LIKE ? ");
			sql.append("ORDER BY G_NO ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, "%" + g_name + "%");
	
			rs = pstmt.executeQuery();

			list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getString("G_NO"), 
						rs.getString("G_NAME"), 
						rs.getString("G_PUB"), 
						rs.getInt("G_PRICE"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	// 도서번호로 검색하기
	public List<BookVO> selectBookNo(String g_no) {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT G_NO, G_NAME, G_PUB, G_PRICE ");
			sql.append("FROM GOODS ");
			sql.append("WHERE G_NO LIKE ? ");
			sql.append("ORDER BY G_NO ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, "%" + g_no + "%");
	
			rs = pstmt.executeQuery();

			list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getString("G_NO"), 
						rs.getString("G_NAME"), 
						rs.getString("G_PUB"), 
						rs.getInt("G_PRICE"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	//도서번호 불러오기
	public boolean searchBookNo(String g_no) {
		boolean b = false;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT G_NO ");
			sql.append("FROM GOODS ");
			sql.append("WHERE G_NO = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, g_no);
			rs = pstmt.executeQuery();

			List<BookVO> list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getString("G_NO"));
				list.add(vo);
				if (list != null) {
					b = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	return b;
	}

	//도서명 불러오기
	public boolean searchBookName(String g_name) {
		boolean b = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT G_NAME ");
			sql.append("FROM GOODS ");
			sql.append("WHERE G_NAME LIKE ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + g_name + "%");
			rs = pstmt.executeQuery();

			List<BookVO> list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getString("G_NAME"));
				list.add(vo);
				if (list != null) {
					b = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return b;
	}
	//3-1.(회원관리)======================================================================
	//전체 회원 정보 확인하기
	public List<MemberVO> selectAllMember() {
		List<MemberVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ID, M_PW, M_NAME, M_JOOMIN, M_ADDRESS, M_PHONE ");
			sql.append("FROM MEMBER ");
			sql.append("ORDER BY M_NAME ");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			list =  new ArrayList<MemberVO>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO(
						rs.getString("M_ID"), 
						rs.getString("M_PW"), 
						rs.getString("M_NAME"), 
						rs.getString("M_JOOMIN"), 
						rs.getString("M_ADDRESS"), 
						rs.getString("M_PHONE"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	//회원정보 - 주소확인
	public List<MemberVO> selectAdd(String m_id) {
		List<MemberVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ADDRESS FROM MEMBER ");
			sql.append(" WHERE M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
	
			rs = pstmt.executeQuery();
					
			list =  new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				list.add(vo);
				vo.setM_address(rs.getString("M_ADDRESS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}

	//회원정보 - 이름확인
	public List<MemberVO> selectName(String m_id) {
		List<MemberVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_NAME FROM MEMBER ");
			sql.append(" WHERE M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
	
			rs = pstmt.executeQuery();
					
			list =  new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				list.add(vo);
				vo.setM_name(rs.getString("M_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//회원정보 - 전화번호확인
	public List<MemberVO> selectPhone(String m_id) {
		List<MemberVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_PHONE FROM MEMBER ");
			sql.append(" WHERE M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
	
			rs = pstmt.executeQuery();
					
			list =  new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				list.add(vo);
				vo.setM_phone(rs.getString("M_PHONE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//================================================================================================
	//3-2.(회원관리 추가)
	//로그인
	public boolean login (String id, String pwd) {

		boolean b = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
            sql.append("SELECT M_ID, M_PW FROM MEMBER ");
            sql.append("WHERE M_ID = ?");
            sql.append("    AND M_PW = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO(
						rs.getString("M_ID"), 
						rs.getString("M_PW")
						);
				list.add(vo);
				if(list.toString() != null) {
					b = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	close(conn, pstmt, rs);
        }
		return b;
	}
	
	//회원 가입
	public ArrayList<String> insertMember (String m_id, String m_pw, String m_name, 
					 String m_joomin, String m_address, String m_phone) {
				
		ArrayList <String> member = new ArrayList<>();
				try {
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					
					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO MEMBER");
					sql.append("             (M_ID, M_PW, M_NAME, M_JOOMIN ,M_ADDRESS, M_PHONE) ");
					sql.append(" VALUES (?, ?, ?, ?, ?, ?) ");
					
					pstmt = conn.prepareStatement(sql.toString());
			
					int i = 1;
					
					pstmt.setString(i++, m_id);
					pstmt.setString(i++, m_pw);
					pstmt.setString(i++, m_name);
					pstmt.setString(i++, m_joomin);
					pstmt.setString(i++, m_address);
					pstmt.setString(i++, m_phone);
					
					pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(conn, pstmt);
				}
				return member;
				
			}
	
	//회원 상세 정보 조회
	public List<MemberVO> getMember (String m_id) {
		List<MemberVO> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ID, M_PW, M_NAME, M_JOOMIN, M_ADDRESS, M_PHONE ");
	        sql.append("  FROM MEMBER ");
	        sql.append("WHERE M_ID = ?");
	        
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			list = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO (
						rs.getString("M_ID"), 
						rs.getString("M_PW"), 
						rs.getString("M_NAME"), 
						rs.getString("M_JOOMIN"), 
						rs.getString("M_ADDRESS"), 
						rs.getString("M_PHONE"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	//회원 정보 수정
	public List<String> updateMember(String pwd, String add, String phone, String id){
		List<String> list = null;
	    int i = 1;
        try{
        	conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
        	StringBuilder sql = new StringBuilder();
        	sql.append("UPDATE MEMBER ");
            sql.append("      SET M_PW=?");
            sql.append("		     , M_ADDRESS=?");
            sql.append("		     , M_PHONE=?");
            sql.append(" WHERE M_ID = ?");

            pstmt =conn.prepareStatement(sql.toString());
            
            pstmt.setString(i++, pwd);
            pstmt.setString(i++, add);
            pstmt.setString(i++, phone);
            pstmt.setString(i++, id);
            
            pstmt.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        } finally{
        	close(conn, pstmt);
        }
        return list;
    }   
	
	 //회원탈퇴
	 public int deleteMember (String id) {
		 int result = 0;
		 try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM MEMBER WHERE M_ID = ?");
			
			pstmt =conn.prepareStatement(sql.toString());
           
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
       	close(conn, pstmt);
       }
		 return result;
	 }
	 
	//ID정보조회하기
	public boolean selectId(String m_id) {
		
		boolean b = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ID ");
			sql.append("FROM MEMBER ");
			sql.append("WHERE M_ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO(rs.getString("M_ID"));
				list.add(vo);
				if(list.toString() != null) {
					b = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return b;
	}
	
	//비번값 수정
	public List<String> updatePw(String id, String pwd){
		List<String> list = null;
	

	    try{
	    	conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE MEMBER ");
	    	sql.append("      SET M_PW = ? ");
	    	sql.append(" WHERE M_ID = ?");
			
	        pstmt =conn.prepareStatement(sql.toString());
	        
	        pstmt.setString(1, pwd);
	        pstmt.setString(2, id);
	        
	        pstmt.executeUpdate();
	        
	    }catch(SQLException ex){
	        ex.printStackTrace();
	    } finally{
	    	close(conn, pstmt);
	    }
	    return list;
	}   
	
	//패스워드정보 조회하기
	public String selectPw(String m_id) {
		String str = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_PW ");
			sql.append("FROM MEMBER ");
			sql.append("WHERE M_ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				str = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return str;
	}
	
	//주소 수정
	public List<String> updateAdd(String id, String add){
		List<String> list = null;
	

	    try{
	    	conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE MEMBER ");
	    	sql.append("      SET M_ADDRESS = ? ");
	    	sql.append(" WHERE M_ID = ?");
			
	        pstmt =conn.prepareStatement(sql.toString());
	        
	        pstmt.setString(1, add);
	        pstmt.setString(2, id);
	        
	        pstmt.executeUpdate();
	        
	    }catch(SQLException ex){
	        ex.printStackTrace();
	    } finally{
	    	close(conn, pstmt);
	    }
	    return list;
	}   
	
	//폰번호 수정
	public List<String> updatePhone(String id, String phone){
		List<String> list = null;
	

	    try{
	    	conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE MEMBER ");
	    	sql.append("      SET M_PHONE = ? ");
	    	sql.append(" WHERE M_ID = ?");
			
	        pstmt =conn.prepareStatement(sql.toString());
	        
	        pstmt.setString(1, phone);
	        pstmt.setString(2, id);
	        
	        pstmt.executeUpdate();
	        
	    }catch(SQLException ex){
	        ex.printStackTrace();
	    } finally{
	    	close(conn, pstmt);
	    }
	    return list;
	}   
	
	
	//1-3. 회원 이름 조회
	public List<String> getName (String id) {
		List<String> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_NAME ");
	        sql.append("  FROM MEMBER ");
	        sql.append("WHERE M_ID = ?");
	        
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			list = new ArrayList<String>();
			if (rs.next()) {
				String str = 
					    rs.getString("M_NAME");
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//1-3. 회원 주민 번호 조회
	public List<String> getJoomin (String id) {
		List<String> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_JOOMIN ");
	        sql.append("  FROM MEMBER ");
	        sql.append("WHERE M_ID = ?");
	        
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			list = new ArrayList<String>();
			if (rs.next()) {
				String str =
					    rs.getString("M_JOOMIN");
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 1-3. 회원 주소 조회
	public List<String> getAddress (String id) {
		List<String> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ADDRESS ");
	        sql.append("  FROM MEMBER ");
	        sql.append("WHERE M_ID = ?");
	        
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			list = new ArrayList<String>();
			if (rs.next()) {
				String str = 
						rs.getString("M_ADDRESS");
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 1-3. 회원 핸드폰 번호 조회
	public List<String> getPhone (String id) {
		List<String> list = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_PHONE ");
	        sql.append("  FROM MEMBER ");
	        sql.append("WHERE M_ID = ?");
	        
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			list = new ArrayList<String>();
			if (rs.next()) {
				String str = 
					    rs.getString("M_PHONE");
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//4.(카트관리)======================================================================
	//장바구니 확인
	public List<BookVO> selectCart(String m_id) {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  G_NO, G_NAME, G_PUB, G_PRICE, CL_QTY, (G_PRICE * CL_QTY) AS SUM ");
			sql.append(" FROM CART ");	
			sql.append(" WHERE M_ID = ? ");	
			sql.append(" ORDER BY G_NO ");	
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			
			rs = pstmt.executeQuery();
			
			list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getString("G_NO"), 
						rs.getString("G_NAME"), 
						rs.getString("G_PUB"), 
						rs.getInt("G_PRICE"),
						rs.getInt("CL_QTY"),
						rs.getInt("SUM")
						);
			
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	//최종 합계액 확인
	public String sumPrice(String m_id) {
		String list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT SUM(G_PRICE * CL_QTY) ");
			sql.append("FROM CART  ");
			sql.append("WHERE M_ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list = rs.getString(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}
	
	//장바구니에 있는 도서 수량 업데이트
	public List<BookVO> selectQty(String m_id, String g_no, int cl_qty) {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CART ");
			sql.append("  SET CL_QTY = ? ");
			sql.append("WHERE G_NO = ? ");
			sql.append("AND M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, cl_qty);
			pstmt.setString(2, g_no);
			pstmt.setString(3, m_id);

			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return list;
	}
		
	//장바구니에 있는 도서 삭제
	public void deleteCartBook(String m_id, String g_no) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE CART ");
			sql.append(" WHERE G_NO = ? ");
			sql.append(" AND M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, g_no);
			pstmt.setString(2, m_id);
	
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	//장바구니 전체 삭제
	public void deleteCartBook(String m_id) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE CART ");
			sql.append(" WHERE M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
	
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	//책 담기
	public void insertBook (String m_id, String g_no, int cl_qty) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO CART (G_NO, G_NAME, G_PUB, G_PRICE, CL_QTY, M_ID) ");
			sql.append("SELECT G.*, ? , ? ");
			sql.append("FROM GOODS G ");
			sql.append("WHERE G.G_NO = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
	
			pstmt.setInt(1, cl_qty);
			pstmt.setString(2, m_id);
			pstmt.setString(3, g_no);
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	//회원장바구니에 있는 책 종류 조회하기
	public boolean selectCartBook(String m_id, String g_no) {
		boolean b = false;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  G_NO ");
			sql.append(" FROM CART ");	
			sql.append(" WHERE M_ID = ? ");	
			sql.append(" AND G_NO = ? ");	
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			pstmt.setString(2, g_no);
			
			rs = pstmt.executeQuery();
			
			List<BookVO> list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(rs.getString("G_NO"));
				list.add(vo);
				//System.out.println(rs.getString("G_NO"));
				if(list != null) b = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return b;
	}
	
	//5.(주문관리)======================================================================
	//결제정보입력하기
	public void insertPay(String m_id, String p_dept, String p_no, int p_month) {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO PAY (M_ID, P_NO, P_DEP, P_MONTH) ");
			sql.append("VALUES (?, ?, ?, ?)" );
			
			pstmt = conn.prepareStatement(sql.toString());
	
			pstmt.setString(1, m_id);
			pstmt.setString(2, p_no);
			pstmt.setString(3, p_dept);
			pstmt.setInt(4, p_month);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	//결제정보조회하기
	public List<MemberVO> selectPay(String m_id) {
		List<MemberVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT P_DEP, P_NO, P_MONTH ");
			sql.append("FROM PAY ");
			sql.append("WHERE M_ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			list =  new ArrayList<MemberVO>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO(
						rs.getString("P_DEP"), 
						rs.getString("P_NO"), 
						rs.getInt("P_MONTH")
						);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	//결제정보조회하기
	public boolean selectPayId(String m_id) {
		
		boolean b = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ID ");
			sql.append("FROM PAY ");
			sql.append("WHERE M_ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO(rs.getString("M_ID"));
				list.add(vo);
				if(list.toString() != null) {
					b = true;
				}
				//System.out.println(vo.getM_id());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return b;
	}
	
	//결제정보삭제하기
	public void deletePay(String m_id) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE PAY ");
			sql.append(" WHERE M_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
	
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	//주문내역에담기 - BUY 
	public void insertBuy(String m_id, String b_name, String b_phone, String b_receive, String b_price) {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO BUY (B_NO, M_ID, B_NAME, B_PHONE, B_RECEIVE, B_PRICE) ");
			sql.append("VALUES ((SELECT NVL(MAX(B_NO),0) + 1 FROM BUY), ?, ?, ?, ?, ?)" );
			
			pstmt = conn.prepareStatement(sql.toString());
	
			pstmt.setString(1, m_id);
			pstmt.setString(2, b_name);
			pstmt.setString(3, b_phone);
			pstmt.setString(4, b_receive);
			pstmt.setString(5, b_price);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
	}	
	
	//주문내역 불러오기 - BUY (아이디로찾기)
	public List<BookVO> selectBuy(String m_id) {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT B_NO, B_RECEIVE, B_NAME, B_PHONE, B_PRICE ");
			sql.append("FROM BUY ");
			sql.append("WHERE M_ID = ? ");
			sql.append("ORDER BY B_NO ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, m_id);
	
			
			rs = pstmt.executeQuery();
			
			list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getInt("B_NO"), 
						rs.getString("B_RECEIVE"), 
						rs.getString("B_NAME"), 
						rs.getString("B_PHONE"), 
						rs.getString("B_PRICE"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	return list;
	}
	
	//주문내역 불러오기 - BUY (아이디로찾기, 주문번호로찾기)
		public List<BookVO> selectBuyPrice(String m_id, int b_no) {
			List<BookVO> list = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT B_NO, B_RECEIVE, B_NAME, B_PHONE, B_PRICE ");
				sql.append("FROM BUY ");
				sql.append("WHERE M_ID = ? ");
				sql.append("AND B_NO = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, m_id);
				pstmt.setInt(2, b_no);
		
				
				rs = pstmt.executeQuery();
				
				list =  new ArrayList<BookVO>();
				
				while (rs.next()) {
					BookVO vo = new BookVO(
							rs.getInt("B_NO"), 
							rs.getString("B_RECEIVE"), 
							rs.getString("B_NAME"), 
							rs.getString("B_PHONE"), 
							rs.getString("B_PRICE"));
					list.add(vo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			
		return list;
		}
	
	//주문내역 전체 불러오기 - BUY 
	public List<BookVO> selectBuyAll() {
		List<BookVO> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM BUY ");
			sql.append("ORDER BY B_NO DESC ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			list =  new ArrayList<BookVO>();
			
			while (rs.next()) {
				BookVO vo = new BookVO(
						rs.getInt("B_NO"), 
						rs.getString("M_ID"), 
						rs.getString("B_NAME"), 
						rs.getString("B_PHONE"), 
						rs.getString("B_RECEIVE"), 
						rs.getString("B_PRICE"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	return list;
	}
	
	//6.(게시판관리)======================================================================
	//문의글 전체 확인
	public List<String> inquiryAll(){
		List<String> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT BR_NO, BR_ID, BR_PW, BR_CONT, BR_DATE ");
			sb.append("  FROM INQUIRY ");
			sb.append(" ORDER BY BR_NO ");
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<String>();
			
			while (rs.next()) {
				String str = 
					rs.getString("BR_NO") + " / " +
					rs.getString("BR_PW") + " / " +
					rs.getString("BR_ID")+ " / " +
					rs.getString("BR_CONT") + " / " +
				    rs.getString("BR_DATE");
				list.add(str);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}

	
	//문의글 남기기
	public List<String> insetInquiry (String br_id, String br_pw ,String content){
		List<String> list = null;
				
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO INQUIRY ");
			sb.append("       (BR_NO, BR_ID, BR_PW , BR_CONT, BR_DATE) ");
			sb.append("VALUES ((SELECT NVL((MAX(BR_NO)), 0) + 1 FROM INQUIRY), ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI'))");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, br_id);
			pstmt.setString(2, br_pw);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return list;
	}
		
	
	//내가 남긴 문의글 확인
	public List<String> selectInquiry (String br_id, String br_pw) {
		List<String> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT BR_NO, BR_ID, BR_CONT, BR_DATE ");
			sb.append(" FROM INQUIRY ");
			sb.append(" WHERE BR_ID = ? AND BR_PW = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, br_id);
			pstmt.setString(2, br_pw);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<String>();
			while (rs.next()) {
				String str = 
					rs.getString("BR_NO") + " / " +
					rs.getString("BR_ID")+ " / " +
					rs.getString("BR_CONT") + " / " +
					rs.getString("BR_DATE");
				list.add(str);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//문의글 수정
	public List<String> updateInquiry (int br_no, String br_cont){
		List<String> list = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE INQUIRY ");
			sb.append("   SET BR_CONT = ? ");
			sb.append(" WHERE BR_NO = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, br_cont);
			pstmt.setInt(2, br_no);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return list;
	}
	
	//문의글 삭제
	public int deleteInquiry (int br_no) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM INQUIRY WHERE BR_NO = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, br_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	//인덱스 번호로 내가 남긴 문의글 확인
	public boolean boardindex (int num) {
		boolean b = false;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT BR_NO ");
			sb.append(" FROM INQUIRY ");
			sb.append(" WHERE BR_NO = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO(
					rs.getString("BR_NO"));

				list.add(vo);
				if(list.toString() != null) {
					b = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return b;
	}

	//ID정보조회하기 (관리자)
	public boolean selectSystemId(String id) {
		
		boolean b = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT M_ID ");
			sql.append("FROM MEMBER ");
			sql.append("WHERE M_ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO(rs.getString("M_ID"));
				list.add(vo);
				if(list.toString() != null) {
					b = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return b;
	}
	
	
	//패스워드정보 조회하기 (관리자)
	public boolean selectSystemPw(String pw) {
		boolean b = false;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT M_PW ");
				sql.append("FROM MEMBER ");
				sql.append("WHERE M_PW = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, pw);
				
				rs = pstmt.executeQuery();
				
				ArrayList<MemberVO> list = new ArrayList<MemberVO>();
				
				while (rs.next()) {
					MemberVO vo = new MemberVO(rs.getString("M_PW"));
					list.add(vo);
					if(list.toString() != null) {
						b = true;
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}

			return b;
		}
		

}