package Class7;

public class Order_detail {
	private String ordno;
	private String serial;
	private String prodId;
	private String prodNm;
	private int num;
	private double price;
	private double amount;
	public Order_detail() {
	}

	public Order_detail(String ordno,String serial,String prodId,String prodNm, int num,double price) {
		this.ordno = ordno;
		this.serial = serial;
		this.prodId = prodId;
		this.prodNm = prodNm;
		this.num = num;
		this.price = price;
		this.amount = num * price;
	}

	public String getOrdno() {
		return ordno;
	}

	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}

	public String getProdId() {
		return prodId;
	}
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getNum() {
		return num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return " 序號:"+serial+"  ,餐點:" + prodId +"號餐-"+prodNm+" * " + num + " ,金額小計:"+amount;
	}
}