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
		OrderInfo order_list = new OrderInfo();
//		orderdet.clear();
		String ls_ordid = Integer.toString(Getmaxid("ORINFO") + 1);
		order_list.setName(ls_custnm);
		order_list.setMobile(ls_tel);
		order_list.setOrderNo(ls_ordid);

		System.out.println("�H�U�OMENU�A�ЮھڤU��M��i���I��");
		ShowMenu();
		int cnt = 0;
		double total_amt = 0;
		while (orderDrinkSystem) {
			
			System.out.print("�п���\�I�s��(�����Ы�0):");
			String ls_dishid = scanner.next();
			if ("0".equals(ls_dishid)) {
				System.out.println("�����I�\");

				orderDrinkSystem = false;
				break;
			}
			boolean isTheProdNumber = false;

			if (GetDishes(ls_dishid) != " ") {
				System.out.println("��J�\�I�s��:" + dishes.get(GetDishes(ls_dishid)).getId() + " "
						+ dishes.get(GetDishes(ls_dishid)).getName());
				isTheProdNumber = true;
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
		dishes.put("1", new Dish("1", "�ư���", 180));
		dishes.put("2", new Dish("2", "���L��", 220));
		dishes.put("3", new Dish("3", "������", 130));
		dishes.put("4", new Dish("4", "�@����", 150));
		dishes.put("5", new Dish("5", "������", 290));
	}

	public void ShowMenu() {
		for (String index : dishes.keySet()) {
			if (index.equals(dishes.get(index).getId())) {
				System.out.println(
						"�s��:" + index + "  �\�I:" + dishes.get(index).getName() + "  ����:" + dishes.get(index).getPrice());
			}
		}
	}

	public void showorder() {
		int cnt = 0;
		System.out.println("�п�J�z�n�d�ߪ��q��ID");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		String orderid = Getid("ORINFO", id);
		if (" ".equals(orderid)) {
			System.out.println("�S����eid���q��A���ˬd��J");
		} else {
			System.out.println("�w����eid�q��" + orderid);
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
			System.out.println("���~");
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
			System.out.println("���~");
		}

		return ll_retun;
	}

	public void DisplayOrder(String id) {
		if (id.equals(" ")) {
			for (String order_index : orderinfo.keySet()) {
				System.out.print("�q��s��:" + orderinfo.get(order_index).getOrderNo() + ",�m�W:"
						+ orderinfo.get(order_index).getName() + ",�q��:" + orderinfo.get(order_index).getMobile());
				for (int key = 0; key < orderdet.size(); key++) {
					if (orderinfo.get(order_index).getOrderNo().equals(orderdet.get(key).getOrdno())) {
//						System.out.println("�q��s��:" + orderinfo.get(order_index).getOrderNo() + ",�m�W:"
//								+ orderinfo.get(order_index).getName() + ",�q��:" + orderinfo.get(order_index).getMobile() 
//								+"," +orderdet.get(key).toString());						
						System.out.print("," + orderdet.get(key).toString() + " ");
					}
				}
				System.out.println();
			}

		} else {
			for (String order_index : orderinfo.keySet()) {
				if (id.equals(orderinfo.get(order_index).getOrderNo())) {
					System.out.print("�q��s��:" + orderinfo.get(order_index).getOrderNo() + ",�m�W:"
							+ orderinfo.get(order_index).getName() + ",�q��:" + orderinfo.get(order_index).getMobile());
					for (int key = 0; key < orderdet.size(); key++) {
						if (orderinfo.get(order_index).getOrderNo().equals(orderdet.get(key).getOrdno())) {
//							System.out.println("�q��s��:" + orderinfo.get(order_index).getOrderNo() + ",�m�W:"
//									+ orderinfo.get(order_index).getName() + ",�q��:" + orderinfo.get(order_index).getMobile() 
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
		System.out.println("�п�J�z�n�ק窱�A���q��ID");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		String orderid = Getid("ORINFO", id);
		if (" ".equals(orderid)) {
			System.out.println("�S����eid���q��A���ˬd��J");
		} else {
			DisplayOrder(orderid);
			System.out.println("�п�J�z�n�ק諸�\�I�Ǹ�:");
			String serial = sc.next();

			for (int key = 0; key < orderdet.size(); key++) {
				if (orderid.equals(orderdet.get(key).getOrdno()) && serial.equals(orderdet.get(key).getSerial()) ) {
					System.out.println("====1.�ק��\�I�A�Ы�1======");
					System.out.println("====2.�ק�ƶq�A�Ы�2====");
					System.out.println("====0.�����\��A�Ы�0====");
					String m = sc.next();

					switch (m) {
					case "1":
						
						System.out.println("�п�J�z�n�ק諸�s�\�IID:");
						cnt = 0;
						String str = sc.next(); 
						id = Getid("DISH", str);
						if (" ".equals(id)) {
							System.out.println("���~�I�d�L���\�IID");
						}
						else
						{							 
							orderdet.get(key).setProdId(id); 
							orderdet.get(key).setPrice(dishes.get(id).getPrice());
							orderdet.get(key).setAmount(orderdet.get(key).getNum() * dishes.get(id).getPrice());
						}
			 
						break;
					case "2":
						System.out.println("�п�J�z�n�ק諸�\�I�ƶq");
						cnt = 0;
						int num = sc.nextInt();
						orderdet.get(key).setNum(num);	
						orderdet.get(key).setAmount(num * orderdet.get(key).getPrice());
						break;
					case "0":
						
						System.out.println("�ק�q�浲���I");
						break;

					default:
						System.out.println("��J���~�A�Э��s��J�G");
						adminMenu();
					}
					resettotalamt(orderid);
					System.out.println("�ק令�\�F�I�I�I");
				}

			}
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
			int ii = Getmaxid("DISH") + 1;
			dishes.put(Integer.toString(ii), new Dish(Integer.toString(ii), info[0], Double.parseDouble(info[1])));
			System.out.println("�s�W���\�F");
		}
	}

	public void deleteDish() {
		System.out.println("�п�J�z�n�R�����\�IID");
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
			System.out.println("���~�I�d�L���\�IID");
		}
		System.out.println("�w�R���\�IID:" + str);
	}

	public void modifyDishPrice() {
		System.out.println("�п�J�z�n�ܧ���檺�\�IID");
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		String str = sc.next();
		for (String key : dishes.keySet()) {
			if (str.equals(dishes.get(key).getId())) {

				System.out.println("�п�J�z�n�ܧ󪺻���");
				str = sc.next();
				dishes.get(key).setPrice(Double.valueOf(str));
				;
				cnt++;
				break;
			}
		}
		if (cnt == 0) {
			System.out.println("���~�I�d�L���\�IID");
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
		System.out.println("=========�P��έp�d��=======");
		System.out.println("====1.�\�I�P���`���B�A�Ы�1======");
		System.out.println("====2.�\�I�P��q�A�Ы�2===="); 
		System.out.println("====0.�����\��A�Ы�0====");
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
			System.out.println("�d�ߵ����I");
			break;

		default:
			System.out.println("��J���~�A�Э��s��J�G");
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
			System.out.println("����P���`���B�G"+final_price);
			break;
		case "2": 
			System.out.println("�п�J�z�n�d�߾P�q���\�IID");
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
				System.out.println("���~�I�d�L���\�IID");
				return;
			}	
			System.out.println(str+"���\����P���`�ơG"+ll_num);
			break; 
		default:
			System.out.println("��J���~�A�Э��s��J�G");
			break; 
	}
	}
}
  