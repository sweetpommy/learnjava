package Class7;

public class Cust {

	private String cusID;
	private String cusname;
	private String custel;

	public Cust() {
		// TODO Auto-generated constructor stub
	}

	public Cust(String cusID, String cusname, String custel) {
		super();
		this.cusID = cusID;
		this.cusname = cusname;
		this.custel = custel;
	}

	public String getCusID() {
		return cusID;
	}

	public void setCusID(String cusID) {
		this.cusID = cusID;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getCustel() {
		return custel;
	}

	public void setCustel(String custel) {
		this.custel = custel;
	}

	@Override
	public String toString() {
		return "客戶清單 [客戶編號=" + cusID + ", 客戶姓名=" + cusname + ", 客戶電話=" + custel + "]";
	}
}
