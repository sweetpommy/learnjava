package lesson1;

public class HelloWorld {

public static void main(String[] args) {
Bc bc = new Bc();
bc.setName("Human");
bc.setWeight(100);
System.out.println(bc.getWeight());
System.out.println(bc.getName());
System.out.println(bc.toString());
}

}

class Bc {

private int weight;

private String name;

public int getWeight() {
return weight;
}

public void setWeight(int weight) {
this.weight = weight;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

// toString �O�Ӧܩ� java.lang.Object����k
// �p�G�ڭ̹�o�Ӥ�k�A�ݭn�ﵽ�٬O���g��
// �ڥi�H�мg(��g)�ڭ̷Q�n�����G
public String toString(){
return "this bc's name = " + name + ", weight = " + weight;
}
}


