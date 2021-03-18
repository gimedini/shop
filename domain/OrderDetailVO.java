package web.shop.mall.domain;

public class OrderDetailVO {
	private int orderDetailsNum;
	private String orderId;
	private int gdsNum;
	private int cartStock;
	
	public OrderDetailVO() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetailVO(int orderDetailsNum, String orderId, int gdsNum, int cartStock) {
		super();
		this.orderDetailsNum = orderDetailsNum;
		this.orderId = orderId;
		this.gdsNum = gdsNum;
		this.cartStock = cartStock;
	}

	public int getOrderDetailsNum() {
		return orderDetailsNum;
	}

	public void setOrderDetailsNum(int orderDetailsNum) {
		this.orderDetailsNum = orderDetailsNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	@Override
	public String toString() {
		return "OrderDetailVO [orderDetailsNum=" + orderDetailsNum + ", orderId=" + orderId + ", gdsNum=" + gdsNum
				+ ", cartStock=" + cartStock + "]";
	}
	
	
}
