package Class7;

public class Dish {
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
