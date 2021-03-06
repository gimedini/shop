package web.shop.mall.domain;

import java.util.Date;

public class OrderListVO {
	private String orderId;
	private String userId;
	private String orderRec;
	private String userAddr1;
	private String userAddr2;
	private String userAddr3;
	private String orderPhon;
	private int amount;
	private Date orderDate;
	
	private int orderDetailsNum;
	private int gdsNum;
	private int cartStock;
	
	private String gdsName;
	private String gdsThumbImg;
	private int gdsPrice;
	
	private String delivery;
	
	public OrderListVO() {
		// TODO Auto-generated constructor stub
	}

	public OrderListVO(String orderId, String userId, String orderRec, String userAddr1, String userAddr2,
			String userAddr3, String orderPhon, int amount, Date orderDate, int orderDetailsNum, int gdsNum,
			int cartStock, String gdsName, String gdsThumbImg, int gdsPrice, String delivery) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderRec = orderRec;
		this.userAddr1 = userAddr1;
		this.userAddr2 = userAddr2;
		this.userAddr3 = userAddr3;
		this.orderPhon = orderPhon;
		this.amount = amount;
		this.orderDate = orderDate;
		this.orderDetailsNum = orderDetailsNum;
		this.gdsNum = gdsNum;
		this.cartStock = cartStock;
		this.gdsName = gdsName;
		this.gdsThumbImg = gdsThumbImg;
		this.gdsPrice = gdsPrice;
		this.delivery = delivery;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderRec() {
		return orderRec;
	}

	public void setOrderRec(String orderRec) {
		this.orderRec = orderRec;
	}

	public String getUserAddr1() {
		return userAddr1;
	}

	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}

	public String getUserAddr2() {
		return userAddr2;
	}

	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}

	public String getUserAddr3() {
		return userAddr3;
	}

	public void setUserAddr3(String userAddr3) {
		this.userAddr3 = userAddr3;
	}

	public String getOrderPhon() {
		return orderPhon;
	}

	public void setOrderPhon(String orderPhon) {
		this.orderPhon = orderPhon;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderDetailsNum() {
		return orderDetailsNum;
	}

	public void setOrderDetailsNum(int orderDetailsNum) {
		this.orderDetailsNum = orderDetailsNum;
	}

	public int getGdsNum() {
		return gdsNum;
	}

	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}

	public int getCartStock() {
		return cartStock;
	}

	public void setCartStock(int cartStock) {
		this.cartStock = cartStock;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getGdsThumbImg() {
		return gdsThumbImg;
	}

	public void setGdsThumbImg(String gdsThumbImg) {
		this.gdsThumbImg = gdsThumbImg;
	}

	public int getGdsPrice() {
		return gdsPrice;
	}

	public void setGdsPrice(int gdsPrice) {
		this.gdsPrice = gdsPrice;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "OrderListVO [orderId=" + orderId + ", userId=" + userId + ", orderRec=" + orderRec + ", userAddr1="
				+ userAddr1 + ", userAddr2=" + userAddr2 + ", userAddr3=" + userAddr3 + ", orderPhon=" + orderPhon
				+ ", amount=" + amount + ", orderDate=" + orderDate + ", orderDetailsNum=" + orderDetailsNum
				+ ", gdsNum=" + gdsNum + ", cartStock=" + cartStock + ", gdsName=" + gdsName + ", gdsThumbImg="
				+ gdsThumbImg + ", gdsPrice=" + gdsPrice + ", delivery=" + delivery + "]";
	}
	
	
	
}
