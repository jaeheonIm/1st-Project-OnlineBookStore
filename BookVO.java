package com.mystudy.onliebookstore_last;


public class BookVO {

	private String m_id;
	private String g_no;
	private String g_name;
	private String g_pub;
	private int g_price;
	private int cl_qty;
	private int sum;
	private String finalPrice;
	private String b_price;
	private String b_receive;
	private String b_phone;
	private String b_name;
	private int b_no;
	
	public BookVO(String m_id, String b_receive, String b_name, String b_phone, String b_price) { 
		this.m_id = m_id;
		this.b_receive = b_receive;
		this.b_name = b_name;
		this.b_phone = b_phone;
		this.b_price = b_price;
	}
	
	public BookVO(int b_no, String b_receive, String b_name, String b_phone, String b_price) {
		this.b_no = b_no;
		this.b_receive = b_receive;
		this.b_name = b_name;
		this.b_phone = b_phone;
		this.b_price = b_price;
	}

	public BookVO(int b_no, String m_id, String b_receive, String b_name, String b_phone, String b_price) {
		this.b_no = b_no;
		this.m_id = m_id;
		this.b_receive = b_receive;
		this.b_name = b_name;
		this.b_phone = b_phone;
		this.b_price = b_price;
	}
	public BookVO(String m_id, String g_no, int cl_qty) {
		this.m_id = m_id;
		this.g_no = g_no;
		this.cl_qty = cl_qty;
	}
	
	public BookVO(String g_no, String g_name, String g_pub, int g_price) {
		this.g_no = g_no;
		this.g_name = g_name;
		this.g_pub = g_pub;
		this.g_price = g_price;
	}
	
	public BookVO(String g_no, String g_name, String g_pub, int g_price, int cl_qty) {
		this.g_no = g_no;
		this.g_name = g_name;
		this.g_pub = g_pub;
		this.g_price = g_price;
		this.cl_qty = cl_qty;
		computeSum();
	}
	
	public BookVO(String g_no, String g_name, String g_pub, int g_price, int cl_qty, int sum) {
		this.g_no = g_no;
		this.g_name = g_name;
		this.g_pub = g_pub;
		this.g_price = g_price;
		this.cl_qty = cl_qty;
		this.sum = sum;
	}
	
	public BookVO(String m_id, String g_no, String g_name, String g_pub, int g_price, int cl_qty, int sum) {
		this.m_id = m_id;
		this.g_no = g_no;
		this.g_name = g_name;
		this.g_pub = g_pub;
		this.g_price = g_price;
		this.cl_qty = cl_qty;
		this.sum = sum;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	
	public BookVO(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getG_no() {
		return g_no;
	}

	public void setG_no(String g_no) {
		this.g_no = g_no;
	}

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public String getG_pub() {
		return g_pub;
	}

	public void setG_pub(String g_pub) {
		this.g_pub = g_pub;
	}

	public int getG_price() {
		return g_price;
	}

	public void setG_price(int g_price) {
		this.g_price = g_price;
	}
	
	public int getCl_qty() {
		return cl_qty;
	}

	public void setCl_qty(int cl_qty) {
		this.cl_qty = cl_qty;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}


	@Override
	public String toString() {
		return "BookVO [g_no=" + g_no + ", g_name=" + g_name + ", g_pub=" + g_pub + ", g_price=" + g_price + "]";
	}
	
	public void computeSum() {
		sum = (cl_qty * g_price);
	}
	
	public String getB_receive() {
		return b_receive;
	}


	public void setB_receive(String b_receive) {
		this.b_receive = b_receive;
	}


	public String getB_phone() {
		return b_phone;
	}


	public void setB_phone(String b_phone) {
		this.b_phone = b_phone;
	}


	public String getB_name() {
		return b_name;
	}


	public void setB_name(String b_name) {
		this.b_name = b_name;
	}


	public void setB_price(String b_price) {
		this.b_price = b_price;
	}

	public String getB_price() {
		return b_price;
	}


	
}
