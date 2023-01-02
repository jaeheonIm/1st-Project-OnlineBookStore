package com.mystudy.onliebookstore_last;



public class MemberVO {
	private int p_month;
	private int  b_no;
	private String m_id;
	private String m_address;
	private String m_name;
	private String m_phone;
	private String p_dept;
	private String p_no;
	private String m_pw;
	private String m_joomin;
	private String b_qty;
	private String b_receive;
	private String b_price;
	private String b_name;
	
	public MemberVO() {}
	
	public MemberVO(String m_id) {
		this.m_id = m_id;
	}
	
	public MemberVO(String m_id, String m_pw) {
		this.m_id = m_id;
		this.m_pw = m_pw;
	}
	
	
	public MemberVO(String m_id, String m_pw, String m_name, String m_joomin, String m_address, String m_phone) {
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_joomin = m_joomin;
		this.m_address = m_address;
		this.m_phone = m_phone;
	}
	
	public MemberVO(String m_address, String m_name, String m_phone) {	
		this.m_address = m_address;
		this.m_name = m_name;
		this.m_phone = m_phone;
	}
	
	public MemberVO(String m_id, String p_dept, String p_no, int p_month) {	
		this.m_id = m_id;
		this.p_dept = p_dept;
		this.p_no = p_no;
		this.p_month = p_month;
	}
	
	public MemberVO(String p_dept, String p_no, int p_month) {	
		this.p_dept = p_dept;
		this.p_no = p_no;
		this.p_month = p_month;
	}
	
	public String getM_address() {
		return m_address;
	}

	public void setM_address(String m_address) {
		this.m_address = m_address;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getP_dept() {
		return p_dept;
	}

	public void setP_dept(String p_dept) {
		this.p_dept = p_dept;
	}

	public String getP_no() {
		return p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}

	public int getP_month() {
		return p_month;
	}

	public void setP_month(int p_month) {
		this.p_month = p_month;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_joomin() {
		return m_joomin;
	}

	public void setM_joomin(String m_joomin) {
		this.m_joomin = m_joomin;
	}

	public String getB_qty() {
		return b_qty;
	}

	public void setB_qty(String b_qty) {
		this.b_qty = b_qty;
	}

	public String getB_receive() {
		return b_receive;
	}

	public void setB_receive(String b_receive) {
		this.b_receive = b_receive;
	}

	public String getB_price() {
		return b_price;
	}

	public void setB_price(String b_price) {
		this.b_price = b_price;
	}
	
	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	
	
	
	
	

}