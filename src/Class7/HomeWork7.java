package Class7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
 

public class HomeWork7 {

	public static void main(String[] args) {
		System.out.println("=====HomeWork======");
		orddish orddish = new orddish();
		orddish.exec();
	}
}

class orddish {
//	List<Dish> dishList = new ArrayList<Dish>();
//	List<Order> orderList = new ArrayList<Order>();
//	List<Cust> custList = new ArrayList<Cust>();

	Map<String, OrderInfo> orderinfo = new TreeMap<String, OrderInfo>();
	Map<String, Dish> dishes = new TreeMap<String, Dish>();
	List<Order_detail> orderdet = new ArrayList<Order_detail>();

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
				showorder(); // DisplayOrder(" ");
				break;
			case 3:
				adminMenu();
				break;
			case 4:
				order_statistics() ;
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
		OrderInfo order_list = new OrderInfo();
//		orderdet.clear();
		String ls_ordid = Integer.toString(Getmaxid("ORINFO") + 1);
		order_list.setName(ls_custnm);
		order_list.setMobile(ls_tel);
		order_list.setOrderNo(ls_ordid);

		System.out.println("以下是MENU，請根據下方清單進行點選");
		ShowMenu();
		int cnt = 0;
		double total_amt = 0;
		while (orderDrinkSystem) {
			
			System.out.print("請選擇餐點編號(結束請按0):");
			String ls_dishid = scanner.next();
			if ("0".equals(ls_dishid)) {
				System.out.println("結束點餐");

				orderDrinkSystem = false;
				break;
			}
			boolean isTheProdNumber = false;

			if (GetDishes(ls_dishid) != " ") {
				System.out.println("輸入餐點編號:" + dishes.get(GetDishes(ls_dishid)).getId() + " "
						+ dishes.get(GetDishes(ls_dishid)).getName());
				isTheProdNumber = true;
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
			cnt = cnt +1;
			orderdet.add(new Order_detail(ls_ordid, Integer.toString(cnt), ls_dishid, dishes.get(GetDishes(ls_dishid)).getName(), num, dishes.get(GetDishes(ls_dishid)).getPrice()));
			 
			total_amt = total_amt + (num * dishes.get(GetDishes(ls_dishid)).getPrice());
		}
		order_list.setList(orderdet);
		order_list.setTotal_amt(total_amt);
		orderinfo.put(ls_ordid, order_list);
		DisplayOrder(ls_ordid);
	}

	public void setdish() {
		dishes.put("1", new Dish("1", "排骨飯", 180));
		dishes.put("2", new Dish("2", "雞腿飯", 220));
		dishes.put("3", new Dish("3", "蝦捲飯", 130));
		dishes.put("4", new Dish("4", "咖哩飯", 150));
		dishes.put("5", new Dish("5", "鰻魚飯", 290));
	}

	public void ShowMenu() {
		for (String index : dishes.keySet()) {
			if (index.equals(dishes.get(index).getId())) {
				System.out.println(
						"編號:" + index + "  餐點:" + dishes.get(index).getName() + "  價格:" + dishes.get(index).getPrice());
			}
		}
	}

	public void showorder() {
		int cnt = 0;
		System.out.println("請輸入您要查詢的訂單ID");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		String orderid = Getid("ORINFO", id);
		if (" ".equals(orderid)) {
			System.out.println("沒有當前id的訂單，請檢查輸入");
		} else {
			System.out.println("已找到當前id訂單" + orderid);
			DisplayOrder(orderid);
		}
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

	public String GetDishnm(String id) {
		String ls_return = " ";
		for (String index : dishes.keySet()) {
			if (id.equals(dishes.get(index).getId())) {
				ls_return = dishes.get(index).getName();
			}
		}

		return ls_return;
	}

	public String GetDishes(String id) {
		String ls_return = " ";
		for (String index : dishes.keySet()) {
			if (id.equals(dishes.get(index).getId())) {
				ls_return = dishes.get(index).getId();
			}
		}
		return ls_return;
	}

	public int Getmaxid(String type) {
		int i = 0;

		switch (type) {
		case "DISH":
			for (String index : dishes.keySet()) {
				if (Integer.valueOf(dishes.get(index).getId()) > i) {
					i = Integer.valueOf(dishes.get(index).getId());
				}
			}
			break;
		case "ORINFO":
			for (String index : orderinfo.keySet()) {
				if (Integer.valueOf(orderinfo.get(index).getOrderNo()) > i) {
					i = Integer.valueOf(orderinfo.get(index).getOrderNo());
				}
			}
			break;
		case "ORDET":
			for (int key = 0; key < orderdet.size(); key++) {
				int id = Integer.valueOf(orderdet.get(key).getOrdno());
				if (i < id) {
					i = id;
				}
			}

			break;
		default:
			System.out.println("錯誤");
		}

		return i;
	}

	public String Getid(String type, String id) {
		String ll_retun = " ";

		switch (type) {
		case "DISH":
			for (String index : dishes.keySet()) {
				if (id.equals(dishes.get(index).getId())) {
					ll_retun = dishes.get(index).getId();
				}
			}
			break;
		case "ORINFO":
			for (String index : orderinfo.keySet()) {
				if (id.equals(orderinfo.get(index).getOrderNo())) {
					ll_retun = orderinfo.get(index).getOrderNo();
				}
			}
			break;
		case "ORDET":
			for (int key = 0; key < orderdet.size(); key++) {
				if (id.equals(orderdet.get(key).getOrdno())) {
					ll_retun = orderdet.get(key).getOrdno();
				}
			}

			break;
		default:
			System.out.println("錯誤");
		}

		return ll_retun;
	}

	public void DisplayOrder(String id) {
		if (id.equals(" ")) {
			for (String order_index : orderinfo.keySet()) {
				System.out.print("訂單編號:" + orderinfo.get(order_index).getOrderNo() + ",姓名:"
						+ orderinfo.get(order_index).getName() + ",電話:" + orderinfo.get(order_index).getMobile());
				for (int key = 0; key < orderdet.size(); key++) {
					if (orderinfo.get(order_index).getOrderNo().equals(orderdet.get(key).getOrdno())) {
//						System.out.println("訂單編號:" + orderinfo.get(order_index).getOrderNo() + ",姓名:"
//								+ orderinfo.get(order_index).getName() + ",電話:" + orderinfo.get(order_index).getMobile() 
//								+"," +orderdet.get(key).toString());						
						System.out.print("," + orderdet.get(key).toString() + " ");
					}
				}
				System.out.println();
			}

		} else {
			for (String order_index : orderinfo.keySet()) {
				if (id.equals(orderinfo.get(order_index).getOrderNo())) {
					System.out.print("訂單編號:" + orderinfo.get(order_index).getOrderNo() + ",姓名:"
							+ orderinfo.get(order_index).getName() + ",電話:" + orderinfo.get(order_index).getMobile());
					for (int key = 0; key < orderdet.size(); key++) {
						if (orderinfo.get(order_index).getOrderNo().equals(orderdet.get(key).getOrdno())) {
//							System.out.println("訂單編號:" + orderinfo.get(order_index).getOrderNo() + ",姓名:"
//									+ orderinfo.get(order_index).getName() + ",電話:" + orderinfo.get(order_index).getMobile() 
//									+"," +orderdet.get(key).toString());
							System.out.print("," + orderdet.get(key).toString() + " ");
						}
					}
					System.out.println();
				}
			}
		}
	}


	public void changeOrderValue() {
		int cnt = 0;
		System.out.println("請輸入您要修改狀態的訂單ID");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		String orderid = Getid("ORINFO", id);
		if (" ".equals(orderid)) {
			System.out.println("沒有當前id的訂單，請檢查輸入");
		} else {
			DisplayOrder(orderid);
			System.out.println("請輸入您要修改的餐點序號:");
			String serial = sc.next();

			for (int key = 0; key < orderdet.size(); key++) {
				if (orderid.equals(orderdet.get(key).getOrdno()) && serial.equals(orderdet.get(key).getSerial()) ) {
					System.out.println("====1.修改餐點，請按1======");
					System.out.println("====2.修改數量，請按2====");
					System.out.println("====0.結束功能，請按0====");
					String m = sc.next();

					switch (m) {
					case "1":
						
						System.out.println("請輸入您要修改的新餐點ID:");
						cnt = 0;
						String str = sc.next(); 
						id = Getid("DISH", str);
						if (" ".equals(id)) {
							System.out.println("錯誤！查無此餐點ID");
						}
						else
						{							 
							orderdet.get(key).setProdId(id); 
							orderdet.get(key).setPrice(dishes.get(id).getPrice());
							orderdet.get(key).setAmount(orderdet.get(key).getNum() * dishes.get(id).getPrice());
						}
			 
						break;
					case "2":
						System.out.println("請輸入您要修改的餐點數量");
						cnt = 0;
						int num = sc.nextInt();
						orderdet.get(key).setNum(num);	
						orderdet.get(key).setAmount(num * orderdet.get(key).getPrice());
						break;
					case "0":
						
						System.out.println("修改訂單結束！");
						break;

					default:
						System.out.println("輸入錯誤，請重新輸入：");
						adminMenu();
					}
					resettotalamt(orderid);
					System.out.println("修改成功了！！！");
				}

			}
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
			int ii = Getmaxid("DISH") + 1;
			dishes.put(Integer.toString(ii), new Dish(Integer.toString(ii), info[0], Double.parseDouble(info[1])));
			System.out.println("新增成功了");
		}
	}

	public void deleteDish() {
		System.out.println("請輸入您要刪除的餐點ID");
		int cnt = 0;
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		for (String key : dishes.keySet()) {
			if (str.equals(dishes.get(key).getId())) {
				dishes.remove(key);
				cnt++;
				break;
			}
		}
		if (cnt == 0) {
			System.out.println("錯誤！查無此餐點ID");
		}
		System.out.println("已刪除餐點ID:" + str);
	}

	public void modifyDishPrice() {
		System.out.println("請輸入您要變更價格的餐點ID");
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		String str = sc.next();
		for (String key : dishes.keySet()) {
			if (str.equals(dishes.get(key).getId())) {

				System.out.println("請輸入您要變更的價格");
				str = sc.next();
				dishes.get(key).setPrice(Double.valueOf(str));
				;
				cnt++;
				break;
			}
		}
		if (cnt == 0) {
			System.out.println("錯誤！查無此餐點ID");
		}
	}

	public void resettotalamt(String orderno) { 
		double total_amt = 0 ;
		for (String order_index : orderinfo.keySet()) {
			if (orderno.equals(orderinfo.get(order_index).getOrderNo())) {
				 
				for (int key = 0; key < orderdet.size(); key++) {
					if (orderinfo.get(order_index).getOrderNo().equals(orderdet.get(key).getOrdno())) {
						total_amt = total_amt + orderdet.get(key).getAmount();
					}
				}
				orderinfo.get(order_index).setTotal_amt(total_amt);
				System.out.println();
			}
		}
	}	
	
	public void order_statistics() {
		System.out.println("=========銷售統計查詢=======");
		System.out.println("====1.餐點銷售總金額，請按1======");
		System.out.println("====2.餐點銷售量，請按2===="); 
		System.out.println("====0.結束功能，請按0====");
		Scanner scanner = new Scanner(System.in);
		String m = scanner.next();
		switch (m) {
		case "1": 
			Order_Rpt("1");
			order_statistics();
			break;
		case "2":
			Order_Rpt("2");
			order_statistics();
			break;
		case "0":
			System.out.println("查詢結束！");
			break;

		default:
			System.out.println("輸入錯誤，請重新輸入：");
			order_statistics();
		}
	}
	
	private void Order_Rpt(String ichoose) { 
		double final_price = 0;
		switch (ichoose) {
		case "1":  
			for (String order_index : orderinfo.keySet()) {
				final_price = final_price + orderinfo.get(order_index).getTotal_amt();
					
				} 
			System.out.println("今日銷售總金額："+final_price);
			break;
		case "2": 
			System.out.println("請輸入您要查詢銷量的餐點ID");
			Scanner sc = new Scanner(System.in);
			int cnt = 0;
			int ll_num = 0;
			String str = sc.next();
			for (String key : dishes.keySet()) {
				if (str.equals(dishes.get(key).getId())) {
					cnt++;
					for (int key_det = 0; key_det < orderdet.size(); key_det++) {
						if (dishes.get(key).getId().equals(orderdet.get(key_det).getProdId())) {
							ll_num = ll_num + orderdet.get(key_det).getNum();
						}
					}			
					
					break;
				}
			}
			if (cnt == 0) {
				System.out.println("錯誤！查無此餐點ID");
				return;
			}	
			System.out.println(str+"號餐今日銷售總數："+ll_num);
			break; 
		default:
			System.out.println("輸入錯誤，請重新輸入：");
			break; 
	}
	}
}
  