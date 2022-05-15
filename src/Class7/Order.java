package Class7;

public class Order {

	private String OrderID;
	private String dishid; // ID
	private int Ordernum;
	private String uID;
	private Double Orderprice;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderID, String dishid, int ordernum, String uID, Double orderprice) {
		super();
		OrderID = orderID;
		this.dishid = dishid;
		Ordernum = ordernum;
		this.uID = uID;
		Orderprice = orderprice;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public Double getOrderprice() {
		return Orderprice;
	}

	public Double getOrdertotalprice() {
		return Orderprice * Ordernum;
	}

	public void setOrderprice(Double orderprice) {
		Orderprice = orderprice;
	}

	public String getDishid() {
		return dishid;
	}

	public void setDishid(String dishid) {
		this.dishid = dishid;
	}

	public int getOrdernum() {
		return Ordernum;
	}

	public void setOrdernum(int ordernum) {
		Ordernum = ordernum;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	@Override
	public String toString() {
		return "訂單明細 [訂單編號=" + OrderID + ", 餐點=" + dishid + ", 數量=" + Ordernum + ", 客戶編號=" + uID + ", 金額="
				+ Orderprice * Ordernum + "]";
	}
}
