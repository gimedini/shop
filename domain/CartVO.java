package web.shop.mall.domain;

import java.util.Date;

public class CartVO {
	private int cartNum;
	private String userId;
	private int gdsNum;
	private int cartStock;
	private Date addDate;
	
	public CartVO() {
		// TODO Auto-generated constructor stub
	}

	public CartVO(int cartNum, String userId, int gdsNum, int cartStock, Date addDate) {
		super();
		this.cartNum = cartNum;
		this.userId = userId;
		this.gdsNum = gdsNum;
		this.cartStock = cartStock;
		this.addDate = addDate;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Override
	public String toString() {
		return "CartVO [cartNum=" + cartNum + ", userId=" + userId + ", gdsNum=" + gdsNum + ", cartStock=" + cartStock
				+ ", addDate=" + addDate + "]";
	}
	
	
}
