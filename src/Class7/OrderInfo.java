package Class7;

import java.util.List;

public class OrderInfo {
//	  public static void main(String[] args) {
//	    OrderInfo aa = new OrderInfo();
//	    aa.setName("123");
//	    aa.setMobile("123");
//	    List<OrderDetail> list = new ArrayList<OrderDetail>();
//	    list.add(new OrderDetail("排骨飯",1));
//	    list.add(new OrderDetail("雞腿飯",2));
//	    aa.setList(list);
//	    aa.setOrderNo("00001");
//	    System.out.println();
//	  }

	private String orderNo;
	private String name;
	private String mobile;
	private List<Order_detail> list;
	private double total_amt;
	
	public OrderInfo() { 
	}	
	public OrderInfo(String name, String mobile) {
		this.name = name;
		this.mobile = mobile;
	}	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Order_detail> getList() {
		return list;
	}

	public void setList(List<Order_detail> list) {
		this.list = list;
	}

	public double getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}
	@Override
	public String toString() {
		return " 訂單編號=" + orderNo + ", 客戶編號=" + name+ ", 餐點=" + list    + " ";
	}
}