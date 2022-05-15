package Class7;

public class Dish {
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
