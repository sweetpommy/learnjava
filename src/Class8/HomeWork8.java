package Class8;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import java.text.DecimalFormat;

public class HomeWork8 {
	public static void main(String[] args) {
		Q8_1 q8_1 = new Q8_1();
		q8_1.doq8_2();
	}
}

class Q8_1 {
	public void doq8() {
		try {
			int num = 0;
			num = 1 / 0;
		} catch (Exception e) {
			// Exception �O�Ҧ����~�ƥ�Object�@��
			// �Ҧ����~�ƥ��~�ӨӦܩ� Exception
			e.printStackTrace();
			System.out.println("�o�Ϳ��~!! ���k���i���H0 ");
		} finally {
			// try catch����U
			// �i�H�ϥ�finally����@���̫᪺�B�z
			// �Y�ϥΨ�L�A�i�H���@�Ǧ������ʧ@
			System.out.println("�i�H�gLOG���i�o�gLOG~");
		}
	}

	public void doq8_2() {
		NumberFormat formatter = new DecimalFormat("#0.000");

		double mathnum = 0;
		mathnum = 1 / 3.0;
		System.out.println(" �|�L���`���p�ơA�u�榡�ƨ�p���I�ĤT�� " + formatter.format(mathnum));

	}
}
class Q8_2 {
	
	List<Animal> animal_all = new ArrayList<Animal>();
	
	public void run_race() {
		this.new_animal(); 
	}
	
	public void new_animal() { 
		animal_all.add(new Animal("AA", 3.0,300));
	}
}

class Animal {
	
	   String name;
	   double speed;
	   int sleepTime;  
	   double runTotalTime;
	   double relaxTotalTime;
	   double totalTime;

public Animal(String name, double speed, int sleepTime ) {
 this.name = name;
 this.speed = speed;
 this.sleepTime = sleepTime; 
 this.totalTime = 0;
}

public String getName() {
 return name;
}

public void setName(String name) {
 this.name = name;
}

public double getSpeed() {
 return speed;
}

public void setSpeed(double speed) {
 this.speed = speed;
}

public int getSleepTime() {
 return sleepTime;
}

public void setSleepTime(int sleepTime) {
 this.sleepTime = sleepTime;
}

public double getRunTotalTime() {
 return runTotalTime;
}

public void setRunTotalTime(double runTotalTime) {
 this.runTotalTime = runTotalTime;
}

public double getRelaxTotalTime() {
 return relaxTotalTime;
}

public void setRelaxTotalTime(double relaxTotalTime) {
 this.relaxTotalTime = relaxTotalTime;
}

public double getTotalTime() {
 return totalTime;
}

public void setTotalTime(double totalTime) {
 this.totalTime = totalTime;
}
}