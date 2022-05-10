package Class7;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeWork7 {

	public static void main(String[] args) {
		System.out.println("=====HomeWork======");
		orddish orddish = new orddish();
		orddish.exec();
		;
	}

}

class orddish {
	List<Dish> dishList = new ArrayList<Dish>();
	List<Order> orderList = new ArrayList<Order>();
	List<Cust> custList = new ArrayList<Cust>();

	// ����
	public void exec() {
		setdish();
		boolean system = true;
		while (system) {
			System.out.println("--------------------");
			System.out.println(" �I�\�t��");
			Scanner scanner = new Scanner(System.in);
			System.out.println("1.�I�\�t�ΡA�Ы�1");
			System.out.println("2.�q��d�ߡA�Ы�2");
			System.out.println("3.�\�I���@�A�Ы�3");
			System.out.println("4.�\�I�έp�A�Ы�4");
			System.out.println("5.�I�\�ק�A�Ы�5");
			System.out.println("0.�����\��A�Ы�0");

			System.out.print("�п�J�n���檺�\��:");
			if (!scanner.hasNextInt()) {
				System.out.println("��J���~�A���s�}�l\n");
				continue;
			}
			int functionFlag = Integer.parseInt(scanner.next());
			System.out.println("--------------------");
			switch (functionFlag) {
			case 1:
				orderSystem();
				break;
			case 2:
				showorder();
				break;
			case 3:
				adminMenu();
				break;
			case 4:
			    System.out.println("�t�ζ}�o��");
				break;
			case 5:
			    changeOrderValue();
				break;
			case 0:
				system = false;
				System.out.println("�I�\�t�ε���");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("�z�|����������\��A�Э��s�}�l");
				break;
			}
		}

	}

	public void setdish() {
		dishList.add(new Dish("1", "�ư���", 180));
		dishList.add(new Dish("2", "���L��", 220));
		dishList.add(new Dish("3", "������", 120));
		dishList.add(new Dish("4", "�@����", 150));
		dishList.add(new Dish("5", "������", 290));
	}
	

	public void display() {
		for (int dish_index = 0; dish_index < dishList.size(); dish_index++) {
			System.out.println("�s�� : " + dishList.get(dish_index).getId() + " �\�I : "
					+ dishList.get(dish_index).getName() + " ���� : " + dishList.get(dish_index).getPrice());
		}

	}

	public void showorder() {
		for (int dish_index = 0; dish_index < orderList.size(); dish_index++) {
			System.out.println("�Ȥ�: " + Getcustnm(orderList.get(dish_index).getuID()) + " �\�I: "
					+ orderList.get(dish_index).getDishid() +" ���\ - "+GetDishnm(orderList.get(dish_index).getDishid())
					+" �ƶq: "+ orderList.get(dish_index).getOrdernum()
					+ " ����: "	+ orderList.get(dish_index).getOrdertotalprice());
		}

	}

	public int getserialid(int id) {
		AtomicInteger val = new AtomicInteger(id);
		int i = val.addAndGet(1);
		return i;
	}

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
//	     System.out.println("TIME:::"+dateString);
		return dateString;
	}

	public static String Getnum() {
		String t = getStringDate();
		int x = (int) (Math.random() * 900) + 100;
		String serial = t + x;
		return serial;
	}

	public String GetDishnm(String dishid) {
		String ls_return = "";

		for (int key = 0; key < dishList.size(); key++) {
			if (dishid.equals(dishList.get(key).getId())) {
				ls_return = dishList.get(key).getName();
				break;
			}
		}

		return ls_return;
	}

	public String Getcustnm(String  id) {
		String ls_return = "";

		for (int key = 0; key < custList.size(); key++) {
			if (id.equals(custList.get(key).getCusID())) {
				ls_return = custList.get(key).getCusname();
				break;
			}
		}

		return ls_return;
	}
	
	public int Getmaxdishid() {
		int i = 0;

		for (int key = 0; key < dishList.size(); key++) {
			int id = Integer.valueOf(dishList.get(key).getId());
			if (i < id) {
				i = id;
			}
		}
		return i;
	}
	
	public int Getmaxordid() {
		int i = 0;

		for (int key = 0; key < orderList.size(); key++) {
			int id = Integer.valueOf(orderList.get(key).getOrderID());
			if (i < id) {
				i = id;
			}
		}
		return i;
	}
	
	public String Getordid(String id) {
		String ls_return = "";

		for (int key = 0; key < orderList.size(); key++) {
			if (id.equals(orderList.get(key).getOrderID())) {
				ls_return = orderList.get(key).getOrderID();
				break;
			}
		}

		return ls_return;
	}
	
	  public void changeOrderValue() {
		    int cnt = 0;
		    System.out.println("�п�J�z�n�ק窱�A���q��ID");
		    Scanner sc = new Scanner(System.in);
		    String id = sc.next();
		    String orderid = Getordid(id);
		    if (orderid == null) {
		      System.out.println("�S����eid���q��A���ˬd��J");
		    } else {
		      System.out.println("�w����eid�q��" + orderid); 
				System.out.println("====1.�ק��\�I�A�Ы�1======");
				System.out.println("====2.�ק�ƶq�A�Ы�2====");
				System.out.println("====0.�����\��A�Ы�0====");
				String m =  sc.next();		
				
				switch (m) {
				case "1": 
					System.out.println("�п�J�z�n�ק諸�\�IID");
					cnt = 0;
					id = sc.next();
					for (int key = 0; key < orderList.size(); key++) {
						if (orderid.equals(orderList.get(key).getOrderID())) {
							orderList.get(key).setDishid(id);
							cnt ++;
							break;
						}
					}
					if (cnt == 0) {
						System.out.println("���~�I�d�L���\�IID");
					}
					break;
				case "2": 
					System.out.println("�п�J�z�n�ק諸�\�I�ƶq");
					cnt = 0;
				    int num = sc.nextInt();
					for (int key = 0; key < orderList.size(); key++) {
						if (orderid.equals(orderList.get(key).getOrderID())) {
							orderList.get(key).setOrdernum(num);;
							cnt ++;
							break;
						}
					}
					if (cnt == 0) {
						System.out.println("���~�I�d�L���\�IID");
					}
					
					break;
				case "0":
					System.out.println("�ק�q�浲���I");
					break;

				default:
					System.out.println("��J���~�A�Э��s��J�G");
					adminMenu();
				}
		      System.out.println("�ק令�\�F�I�I�I");
		    }

		  }
	

	public void addDish() {
		System.out.println("�п�J�z�n�s�W���\�I:(����:�\�I�W��/���)");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String[] info = str.split("/");
		//
		if (info.length < 2) {
			System.out.println("���~�Э��s��J�I");
			addDish();
		} else {
			int ii = getserialid(Getmaxdishid());
			dishList.add(new Dish(Integer.toString(ii), info[0], Double.parseDouble(info[1])));
			System.out.println("�s�W���\�F");
		}
	}

	public void deleteDish() {
		System.out.println("�п�J�z�n�R�����\�IID");
		int cnt = 0;
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		for (Dish index : dishList) {
			if (str.equals(index.getId())) {
				cnt = cnt +1;
				dishList.remove(index);
				break;
			} 			 
		}
		if (cnt == 0) {
			System.out.println("���~�I�d�L���\�IID");
		}
	}

	public void modifyDishPrice() {
		System.out.println("�п�J�z�n�ܧ���檺�\�IID");
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		String str = sc.next();
		for (Dish index : dishList) {
			if (str.equals(index.getId())) {
				cnt = cnt +1;
				System.out.println("�п�J�z�n�ܧ󪺻���");
				String price = sc.next();
				index.setPrice(Double.parseDouble(price));
				break;
			}
		}
		if (cnt == 0) {
			System.out.println("���~�I�d�L���\�IID");
		}
	}	
	
	public void adminMenu() {
		System.out.println("=========�ק�MENU=======");
		System.out.println("====1.�s�W�\�I�A�Ы�1======");
		System.out.println("====2.�ק����A�Ы�2====");
		System.out.println("====3.�R���\�I�A�Ы�3==");
		System.out.println("====0.�����\��A�Ы�0====");
		Scanner scanner = new Scanner(System.in);
		String m = scanner.next();
		switch (m) {
		case "1":
			addDish();
			adminMenu();
			break;
		case "2":
			modifyDishPrice();
			adminMenu();
			break;
		case "3":
			deleteDish();
			adminMenu();
			break;
		case "0":
			System.out.println("�ק�MENU�����I");
			break;

		default:
			System.out.println("��J���~�A�Э��s��J�G");
			adminMenu();
		}
	}

	private void orderSystem() {
		System.out.println("--------------------");
		System.out.println("1.1 �z�i�J�I�\����");
		boolean orderDrinkSystem = true;

		// �m�W
		System.out.print("�п�J�U�ȩm�W(���}�Ы�0):");
		Scanner scanner = new Scanner(System.in);
		String ls_custnm = scanner.next();
		if ("0".equals(ls_custnm)) {
			System.out.println("�����I�\");
			orderDrinkSystem = false;
			return;
		}
		// �q��
		System.out.print("�п�J�U�ȹq��(���}�Ы�0):");
		String ls_tel = scanner.next();
		if ("0".equals(ls_tel)) {
			System.out.println("�����I�\");
			orderDrinkSystem = false;
			return;
		}
//		// �~�a�٤���
//		System.out.print("�~�a�Ы�1�A���νЫ�2(���}�Ы�0):");
//		String ls_takeaway = scanner.next();
//		if ("0".equals(ls_takeaway)) {
//			System.out.println("�����I�\");
//			orderDrinkSystem = false;
//		}
		String ls_custid = Getnum();
		System.out.println("�H�U�OMENU�A�ЮھڤU��M��i���I��");
		display();
		while (orderDrinkSystem) {
			System.out.print("�п���\�I�s��(�����Ы�0):");
			String ls_dishid = scanner.next();
			if ("0".equals(ls_dishid)) {
				System.out.println("�����I�\");
				orderDrinkSystem = false;
				break;
			}

			boolean isTheProdNumber = false;
			for (int key = 0; key < dishList.size(); key++) {
//				System.out.println("key:" + key + "-" + dishList.get(key).getId());
				if (ls_dishid.equals(dishList.get(key).getId())) {
					System.out.println("��J�\�I�s��:" + dishList.get(key).getId() + " " + dishList.get(key).getName());
					isTheProdNumber = true;
					break;
				}
			}
			if (!isTheProdNumber) {
				System.out.println("�п�ܥ��T���\�I");
				continue;
			}

			System.out.print("�п�J�\�I���ƶq(���}�Ы�0):");
			int num = scanner.nextInt();
			if (num == 0) {
				System.out.println("�����I�\");
				orderDrinkSystem = false;
				break;
			} 
			String ls_ordid = Integer.toString(Getmaxordid() +1) ;
			custList.add(new Cust(ls_custid, ls_custnm, ls_tel));
			orderList.add(new Order(ls_ordid, ls_dishid, num, ls_custid,
					dishList.get(Integer.valueOf(ls_dishid) - 1).getPrice()));
			for (Order index : orderList) {
				System.out.println(index.toString());
			}

		}
	}
}

class Dish {
	private String id; // ID
	private String name; // ��W
	private double price; // ����

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Dish() {
	}

	public Dish(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String printDish() {
		return "�\�I : " + getName() + " ���� : " + getPrice();
	}
}

class Order {
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
		return "�q����� [�q��s��=" + OrderID + ", �\�I=" + dishid + ", �ƶq=" + Ordernum + ", �Ȥ�s��=" + uID + ", ���B="
				+ Orderprice * Ordernum + "]";
	}
}

class Cust {
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
		return "�Ȥ�M�� [�Ȥ�s��=" + cusID + ", �Ȥ�m�W=" + cusname + ", �Ȥ�q��=" + custel + "]";
	}

}