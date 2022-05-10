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

	// 執行
	public void exec() {
		setdish();
		boolean system = true;
		while (system) {
			System.out.println("--------------------");
			System.out.println(" 點餐系統");
			Scanner scanner = new Scanner(System.in);
			System.out.println("1.點餐系統，請按1");
			System.out.println("2.訂單查詢，請按2");
			System.out.println("3.餐點維護，請按3");
			System.out.println("4.餐點統計，請按4");
			System.out.println("5.點餐修改，請按5");
			System.out.println("0.結束功能，請按0");

			System.out.print("請輸入要執行的功能:");
			if (!scanner.hasNextInt()) {
				System.out.println("輸入錯誤，重新開始\n");
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
			    System.out.println("系統開發中");
				break;
			case 5:
			    changeOrderValue();
				break;
			case 0:
				system = false;
				System.out.println("點餐系統結束");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("您尚未選取到任何功能，請重新開始");
				break;
			}
		}

	}

	public void setdish() {
		dishList.add(new Dish("1", "排骨飯", 180));
		dishList.add(new Dish("2", "雞腿飯", 220));
		dishList.add(new Dish("3", "蝦捲飯", 120));
		dishList.add(new Dish("4", "咖哩飯", 150));
		dishList.add(new Dish("5", "鰻魚飯", 290));
	}
	

	public void display() {
		for (int dish_index = 0; dish_index < dishList.size(); dish_index++) {
			System.out.println("編號 : " + dishList.get(dish_index).getId() + " 餐點 : "
					+ dishList.get(dish_index).getName() + " 價格 : " + dishList.get(dish_index).getPrice());
		}

	}

	public void showorder() {
		for (int dish_index = 0; dish_index < orderList.size(); dish_index++) {
			System.out.println("客戶: " + Getcustnm(orderList.get(dish_index).getuID()) + " 餐點: "
					+ orderList.get(dish_index).getDishid() +" 號餐 - "+GetDishnm(orderList.get(dish_index).getDishid())
					+" 數量: "+ orderList.get(dish_index).getOrdernum()
					+ " 價格: "	+ orderList.get(dish_index).getOrdertotalprice());
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
		    System.out.println("請輸入您要修改狀態的訂單ID");
		    Scanner sc = new Scanner(System.in);
		    String id = sc.next();
		    String orderid = Getordid(id);
		    if (orderid == null) {
		      System.out.println("沒有當前id的訂單，請檢查輸入");
		    } else {
		      System.out.println("已找到當前id訂單" + orderid); 
				System.out.println("====1.修改餐點，請按1======");
				System.out.println("====2.修改數量，請按2====");
				System.out.println("====0.結束功能，請按0====");
				String m =  sc.next();		
				
				switch (m) {
				case "1": 
					System.out.println("請輸入您要修改的餐點ID");
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
						System.out.println("錯誤！查無此餐點ID");
					}
					break;
				case "2": 
					System.out.println("請輸入您要修改的餐點數量");
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
						System.out.println("錯誤！查無此餐點ID");
					}
					
					break;
				case "0":
					System.out.println("修改訂單結束！");
					break;

				default:
					System.out.println("輸入錯誤，請重新輸入：");
					adminMenu();
				}
		      System.out.println("修改成功了！！！");
		    }

		  }
	

	public void addDish() {
		System.out.println("請輸入您要新增的餐點:(按照:餐點名稱/單價)");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String[] info = str.split("/");
		//
		if (info.length < 2) {
			System.out.println("錯誤請重新輸入！");
			addDish();
		} else {
			int ii = getserialid(Getmaxdishid());
			dishList.add(new Dish(Integer.toString(ii), info[0], Double.parseDouble(info[1])));
			System.out.println("新增成功了");
		}
	}

	public void deleteDish() {
		System.out.println("請輸入您要刪除的餐點ID");
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
			System.out.println("錯誤！查無此餐點ID");
		}
	}

	public void modifyDishPrice() {
		System.out.println("請輸入您要變更價格的餐點ID");
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		String str = sc.next();
		for (Dish index : dishList) {
			if (str.equals(index.getId())) {
				cnt = cnt +1;
				System.out.println("請輸入您要變更的價格");
				String price = sc.next();
				index.setPrice(Double.parseDouble(price));
				break;
			}
		}
		if (cnt == 0) {
			System.out.println("錯誤！查無此餐點ID");
		}
	}	
	
	public void adminMenu() {
		System.out.println("=========修改MENU=======");
		System.out.println("====1.新增餐點，請按1======");
		System.out.println("====2.修改價格，請按2====");
		System.out.println("====3.刪除餐點，請按3==");
		System.out.println("====0.結束功能，請按0====");
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
			System.out.println("修改MENU結束！");
			break;

		default:
			System.out.println("輸入錯誤，請重新輸入：");
			adminMenu();
		}
	}

	private void orderSystem() {
		System.out.println("--------------------");
		System.out.println("1.1 您進入點餐介面");
		boolean orderDrinkSystem = true;

		// 姓名
		System.out.print("請輸入顧客姓名(離開請按0):");
		Scanner scanner = new Scanner(System.in);
		String ls_custnm = scanner.next();
		if ("0".equals(ls_custnm)) {
			System.out.println("取消點餐");
			orderDrinkSystem = false;
			return;
		}
		// 電話
		System.out.print("請輸入顧客電話(離開請按0):");
		String ls_tel = scanner.next();
		if ("0".equals(ls_tel)) {
			System.out.println("取消點餐");
			orderDrinkSystem = false;
			return;
		}
//		// 外帶還內用
//		System.out.print("外帶請按1，內用請按2(離開請按0):");
//		String ls_takeaway = scanner.next();
//		if ("0".equals(ls_takeaway)) {
//			System.out.println("取消點餐");
//			orderDrinkSystem = false;
//		}
		String ls_custid = Getnum();
		System.out.println("以下是MENU，請根據下方清單進行點選");
		display();
		while (orderDrinkSystem) {
			System.out.print("請選擇餐點編號(結束請按0):");
			String ls_dishid = scanner.next();
			if ("0".equals(ls_dishid)) {
				System.out.println("結束點餐");
				orderDrinkSystem = false;
				break;
			}

			boolean isTheProdNumber = false;
			for (int key = 0; key < dishList.size(); key++) {
//				System.out.println("key:" + key + "-" + dishList.get(key).getId());
				if (ls_dishid.equals(dishList.get(key).getId())) {
					System.out.println("輸入餐點編號:" + dishList.get(key).getId() + " " + dishList.get(key).getName());
					isTheProdNumber = true;
					break;
				}
			}
			if (!isTheProdNumber) {
				System.out.println("請選擇正確的餐點");
				continue;
			}

			System.out.print("請輸入餐點的數量(離開請按0):");
			int num = scanner.nextInt();
			if (num == 0) {
				System.out.println("結束點餐");
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
	private String name; // 菜名
	private double price; // 價格

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
		return "餐點 : " + getName() + " 價格 : " + getPrice();
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
		return "訂單明細 [訂單編號=" + OrderID + ", 餐點=" + dishid + ", 數量=" + Ordernum + ", 客戶編號=" + uID + ", 金額="
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
		return "客戶清單 [客戶編號=" + cusID + ", 客戶姓名=" + cusname + ", 客戶電話=" + custel + "]";
	}

}