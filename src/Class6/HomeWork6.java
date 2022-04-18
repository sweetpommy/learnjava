package Class6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Collections;
import java.util.HashSet;

public class HomeWork6 {
	public static void main(String args[]) {
		System.out.println("=====HomeWork6_Q1======");
		Q6_1 q6_1 = new Q6_1();
		q6_1.DoMap();
		System.out.println("=====HomeWork6_Q2======");
		Q6_2 q6_2 = new Q6_2();
		q6_2.DoList();
		System.out.println("=====HomeWork6_Q3======");
		Q6_3 q6_3 = new Q6_3();
		q6_3.lotto_number();

		System.out.println("=====HomeWork6_Q4======");
		Q6_4 q6_4 = new Q6_4();
		q6_4.getmember_order_info();

		System.out.println("=====HomeWork6_Q5======");
		Q6_5 q6_5 = new Q6_5();
		q6_5.getdata();
	}
}

class Q6_1 {
	Map<String, Member> member = new HashMap<String, Member>();

	public void DoMap() {
		member.put("C1", new Member("C1", "�Ŧ�"));
		member.put("C2", new Member("C2", "��ۣ"));
		member.put("C3", new Member("C3", "�p��"));

		this.print_member("--INIT DATA--");

		for (String key : member.keySet()) {
			if ("C1".equals(member.get(key).getNo())) {
				member.get(key).setName("����");
			}
		}
		this.print_member("--C1���m�W��令����--");
		for (String key : member.keySet()) {
			if ("C2".equals(member.get(key).getNo())) {
				member.remove("C2", member.get(key));

			}
		}
		member.put("C4", new Member("C4", "�Ѥ�"));
		this.print_member("--C2�w�Q����ϥΡA�s�W�@�ӷs���|�����--");
		System.out.println();
	}

	public void print_member(String as_print) {
		System.out.println(as_print);
		for (String key : member.keySet()) {
			System.out.println("no = " + member.get(key).getNo() + " , name = " + member.get(key).getName());
		}
	}
}

class Q6_2 {
	List<Member> members = new ArrayList<Member>();

	public void DoList() {
		members.add(new Member("C1", "�Ŧ�"));
		members.add(new Member("C2", "��ۣ"));
		members.add(new Member("C3", "�p��"));
		this.print_member1("--INIT DATA--");

		for (Member index : members) {
			if ("C1".equals(index.getNo())) {
				index.setName("����");
			}
		}
		this.print_member1("--C1���m�W��令����--");
		members.removeIf(e -> e.getNo().contains("C2"));
//	    for (Member index : members) {
//	        if("C2".equals(index.getNo())){
//	          members.remove(index);
//	          break;
//	        }
//	      }
		members.add(new Member("C4", "�Ѥ�"));
		this.print_member1("--C2�w�Q����ϥΡA�s�W�@�ӷs���|�����--");
		System.out.println();
	}

	public void print_member1(String as_print) {
		System.out.println(as_print);
		for (Member index : members) {
			System.out.println("no = " + index.getNo() + " , name = " + index.getName());
		}
	}

}

class Q6_3 {
	public void lotto_number() {
		for (int index = 1; index <= 10; index++) {
			Set<String> set = new HashSet<String>();
			do {
				String num = String.valueOf((int) (Math.random() * 42) + 1);
				if (Integer.parseInt(num) < 10) {
					num = "0" + num;
				}
				set.add(num);
			} while (set.size() < 6);
			System.out.println("��" + index + "��:" + set.toString());
		}
		System.out.println();
	}
}

class Q6_4 {
	Map<String, OrderInfo> order_list = new TreeMap<String, OrderInfo>();
	Map<String, Member> members = new TreeMap<String, Member>();
	TreeMap<String, Member_ord> cust_ordamt = new TreeMap<String, Member_ord>();
	// ���ǱƧ�
	TreeMap<Integer, Member_ord> cust_ordamt2 = new TreeMap<Integer, Member_ord>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
//			return o1.compareTo(o2); 
			return o2 - o1;
		}
	});

	public void getmember_order_info() {
		this.new_member();
		this.new_order();
		this.list_Buy();
		this.GetAvgCost();
		this.List_Order();
		System.out.println();
	}

	public void list_Buy() {
		System.out.println("1.�d�߷|���R���ӫ~");
		for (String member_index : members.keySet()) {
			for (String order_index : order_list.keySet()) {
				if (member_index.equals(order_list.get(order_index).getNo())) {
					System.out.println("�s��:" + member_index + ",�m�W:" + members.get(member_index).getName() + ",�q�渹�X:"
							+ order_list.get(order_index).getNo() + ",�q�ʤ��e:"
							+ order_list.get(order_index).getOrderDesc() + ",���B:"
							+ order_list.get(order_index).get_Amt());
				}
			}
		}
		System.out.println();
	}

	private void GetAvgCost() {
		System.out.println("2.�����C�����O���B");
		for (String member_index : members.keySet()) {
			System.out.print("�s��:" + member_index + ",�m�W:" + members.get(member_index).getName());
			float total_amt = 0.0f;
			int count = 0;
			for (String order_index : order_list.keySet()) {
				if (member_index.equals(order_list.get(order_index).getNo())) {
					count++;
					total_amt = total_amt + order_list.get(order_index).get_Amt();
				}
			}
			cust_ordamt.put(member_index, new Member_ord(members.get(member_index).getNo(), (int) total_amt));
			cust_ordamt2.put((int) total_amt, new Member_ord(members.get(member_index).getNo(), (int) total_amt));
			System.out.printf(",�������O���B: %.2f", total_amt / count);
			System.out.println(" ");
		}
		System.out.println();
	}

	private void List_Order() {
		System.out.println("3.���O�`���B����C�Ƨ�");
		for (int index : cust_ordamt2.keySet()) {

			System.out.println("�s�� = " + cust_ordamt2.get(index).getNo() + " , �m�W = "
					+ members.get(cust_ordamt2.get(index).getNo()).getName() + " , ���O�`���B = "
					+ cust_ordamt2.get(index).get_Amt());
		}
		System.out.println();
		System.out.println("4.�̷Ӯ��O�`���B�C�찪�Ƨ�");

		for (String index : cust_ordamt.keySet()) {
			System.out.println("�s�� = " + cust_ordamt.get(index).getNo() + " , �m�W = " + members.get(index).getName()
					+ " , ���O�`���B = " + cust_ordamt.get(index).get_Amt());
		}
	}

	public void new_member() {
		members.put("C1", new Member("C1", "�pQ"));
		members.put("C2", new Member("C2", "�p�}"));
		members.put("C3", new Member("C3", "�d�z"));
	}

	public void new_order() {
		order_list.put("O001", new OrderInfo("O001", "C1", "��A", 789));
		order_list.put("O002", new OrderInfo("O002", "C1", "3C", 1999));
		order_list.put("O003", new OrderInfo("O003", "C2", "�C��", 1899));
		order_list.put("O004", new OrderInfo("O004", "C2", "�O�i�~", 3300));
		order_list.put("O005", new OrderInfo("O005", "C3", "��v��", 14999));
	}
}

class Q6_5 {
	List<Member> member_1 = new ArrayList<Member>();
	List<OrderInfo> infos = new ArrayList<OrderInfo>();
	List<Member_ord> memord = new ArrayList<Member_ord>();

	public void getdata() {
		this.new_member();
		this.new_order();
		this.Member_Buy();
		this.AvgCost();
		this.List_Order();
	}

	private void Member_Buy() {
		System.out.println("1.�d�߷|���R���ӫ~");
		for (int memindex = 0; memindex < member_1.size(); memindex++) {
			for (int ord_index = 0; ord_index < infos.size(); ord_index++) {
				if (member_1.get(memindex).getNo().equals(infos.get(ord_index).getNo())) {
					System.out.println("�s��:" + member_1.get(memindex).getNo() + " , �m�W:" + member_1.get(memindex).getName()
							+ " , �q�渹�X:" + infos.get(ord_index).getNo() + " , �q�ʤ��e:" + infos.get(ord_index).getOrderDesc()
							+ " , ���B:" + infos.get(ord_index).get_Amt());
				}
			}
		}
		System.out.println();
	}

	private void AvgCost() {
		System.out.println("2.�����C�����O���B");
		for (Member memindex : member_1) {
			System.out.print("�s��:" + memindex.getNo() + " , �m�W:" + memindex.getName());
			float totalAmout = 0;
			float count = 0;
			for (OrderInfo orderInfo : infos) {
				if (memindex.getNo().equals(orderInfo.getNo())) {
					count++;
					totalAmout = totalAmout + orderInfo.get_Amt();
				}
			}
			float avgAmount = totalAmout / count;
			memord.add(new Member_ord(memindex.getNo(), (int) totalAmout));
			System.out.printf(" , �������O���B : %.2f", avgAmount);
			System.out.println();
		}
		System.out.println();
	}

	private void List_Order() {
		System.out.println("3.���O�`���B����C�Ƨ�");
		Collections.sort(memord, new Member_ordSort_desc());
		for (int index = 0; index < memord.size(); index++) {
			System.out.println("�s�� = " + memord.get(index).getNo() + ",�m�W = " + member_1.get(index).getName()
					+ " , ���O�`���B = " + memord.get(index).get_Amt());
		}
		System.out.println();
		System.out.println("4.�̷Ӯ��O�`���B�C�찪�Ƨ�");
		Collections.sort(memord, new Member_ordSort_asc());
		for (int index = 0; index < memord.size(); index++) {
			System.out.println("�s�� = " + memord.get(index).getNo() + ",�m�W = " + member_1.get(index).getName()
					+ " , ���O�`���B = " + memord.get(index).get_Amt());
		}
	}

	public void new_member() {
		member_1.add(new Member("C1", "�pQ"));
		member_1.add(new Member("C2", "�p�}"));
		member_1.add(new Member("C3", "�d�z"));
	}

	public void new_order() {
		infos.add(new OrderInfo("O001", "C1", "��A", 789));
		infos.add(new OrderInfo("O002", "C1", "3C", 1999));
		infos.add(new OrderInfo("O003", "C2", "�C��", 1899));
		infos.add(new OrderInfo("O004", "C2", "�O�i�~", 3300));
		infos.add(new OrderInfo("O005", "C3", "��v��", 14999));
	}
}

class Member_ordSort_desc implements Comparator<Member_ord> {
	public int compare(Member_ord a, Member_ord b) {
		return b.get_Amt() - a.get_Amt();
	}
}

class Member_ordSort_asc implements Comparator<Member_ord> {
	public int compare(Member_ord a, Member_ord b) {
		return a.get_Amt() - b.get_Amt();
	}
}

class Member {
	private String no;
	private String name;

	public Member(String no, String name) {
		this.setNo(no);
		this.setName(name);
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Member_ord {
	private String no;
	private int amount;

	public Member_ord(String no, int amount) {
		this.setNo(no);
		this.set_Amt(amount);
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int get_Amt() {
		return amount;
	}

	public void set_Amt(int amount) {
		this.amount = amount;
	}

}

class OrderInfo {
	private String orderNo;
	private String no;
	private String orderDesc;
	private int amount;

	public OrderInfo(String orderNo, String no, String orderDesc, int amount) {
		setOrderNo(orderNo);
		setNo(no);
		setOrderDesc(orderDesc);
		set_Amt(amount);
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public int get_Amt() {
		return amount;
	}

	public void set_Amt(int amount) {
		this.amount = amount;
	}
}