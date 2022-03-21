package Class3;

import java.util.Random;

public class HomeWork3 {
	public static void main(String args[]) {
//		String name;
//		int age;

		System.out.println("=====HomeWork3_Q1======");		
		q3_1 Q3_1 = new q3_1();
		Q3_1.Forloop();
		
		q3_2 Q3_2 = new q3_2();
		System.out.println("=====HomeWork3_Q2==FOR====");		
		Q3_2.usingFor();
		System.out.println("=====HomeWork3_Q2==WHILE====");		
		Q3_2.usingWhile();

		q3_3 Q3_3 = new q3_3();
		System.out.println("=====HomeWork3_Q3======");
		Q3_3.year_check();

		q3_4 Q3_4 = new q3_4();
		System.out.println("=====HomeWork3_Q4======");
		Q3_4.score_check();

		q3_5 Q3_5 = new q3_5();
		System.out.println("=====HomeWork3_Q5======");
		Q3_5.initCards();
	}
}

class q3_1 {
	public void Forloop() {
		int i, j;
		String ls_string1;
		ls_string1 = "*";
//		System.out.println("<pre>");
		for (i = 1; i < 5; i++) {
			System.out.println(ls_string1);
			ls_string1 = ls_string1 + "*";
		}
		System.out.println(ls_string1);
		for (j = 5; j > 1; j--) {
			// System.out.println(ls_string1.length());
			System.out.println(ls_string1.substring(0, j - 1));
		}
//		System.out.println("</pre>");
	}
}

class q3_2 {

	public void usingFor() {
		int i, j;
		// String ls_space= " ";
		for (i = 1; i <= 9; i++) {
			for (j = 1; j <= 9; j++) {
				System.out.print(i + "*" + j + "=" + (i * j));
				System.out.print("\t"); // �j�Ů�
			}
			System.out.println();
		}
	}

	public void usingWhile() {
		int i = 1;
		int j = 1;
		while (j < 10) {
			i = 1;
			while (i < 10) {
				System.out.print(j + "*" + i + "=" + j * i);
				if (i < 9) {
					System.out.print("\t"); // �j�Ů�
				}
				i++; // => i = i + 1;
			}
			System.out.println();
			j++;
		}
	}
}

/**
 * 3-3. �Шϥ�for�j��A���Τ@�Ӿ�Ƥ@���}�C�x�s1900~2022�~��A�P�_���X�~�O�|�~? //�p�G�~���i�H�Q 400
 * �㰣�A�h���M�O�|�~�F�t�~�A�p�G�~���i�H�Q 4 �㰣�A���O����Q 100 �㰣�A�h�]�O�|�~
 */
class q3_3 {
	public void year_check() {
		int size, ls_year_b, ls_year_e;
		ls_year_b = 1900;
		ls_year_e = 2022;
		size = ls_year_e - ls_year_b + 1;
		int year[] = new int[size];
		for (int index = 0; index < year.length; index++) {
			year[index] = ls_year_b + index;
			boolean lb_yearcheck = false;
			if ((year[index] % 4 == 0 && year[index] % 100 != 0) || (year[index] % 400 == 0)) {
				lb_yearcheck = true;
			} else {
				lb_yearcheck = false;
			}
			System.out.println(" Year " + year[index] + "�O�|�~?" + lb_yearcheck);
		}
	}
}

class q3_4 {
	public void score_check() {
		int size;
		size = 3;
		String name[] = new String[size];
		int math[] = new int[size];
		int chn[] = new int[size];
		int eng[] = new int[size];
		double sum[] = new double[size];
		double mathsum = 0, chnsum = 0, engsum = 0;

		for (int index = 0; index < size; index++) {
			switch (index) {
			case 0:
				name[index] = "�p��";
				math[index] = 95;
				chn[index] = 80;
				eng[index] = 77;
				sum[index] = (math[index] + chn[index] + eng[index]);
				break;
			case 1:
				name[index] = "�p��";
				math[index] = 55;
				chn[index] = 87;
				eng[index] = 89;
				sum[index] = (math[index] + chn[index] + eng[index]);
				break;
			case 2:
				name[index] = "�p��";
				math[index] = 71;
				chn[index] = 78;
				eng[index] = 88;
				sum[index] = (math[index] + chn[index] + eng[index]);
				break;
			default:
				System.out.println("WTF??");
				break;
			}
			System.out.println(name[index] + " ��������:" + String.format("%.02f", sum[index] / 3));
			mathsum = mathsum + math[index];
			chnsum = chnsum + chn[index];
			engsum = engsum + eng[index];
		}
		System.out.println("�ƾǥ�������:" + String.format("%.02f", (mathsum / 3)));
		System.out.println("��奭������:" + String.format("%.02f", (chnsum / 3)));
		System.out.println("�^�奭������:" + String.format("%.02f", (engsum / 3)));
	}
}

// 3-5.���@��52�i���J�P(���t���P)�A�����ڶi��o�P�ʧ@�A�åB�o���|�ӤH(������)�C
class q3_5 {
	String[] colorArray;
	String[] numberArray;
	String[] cards;

	// ���
	public void initColorArray() {
		colorArray = new String[4];
		colorArray[0] = "����";
		colorArray[1] = "���";
		colorArray[2] = "�R��";
		colorArray[3] = "�®�";
	}

	// �Ʀr
	public void initNumberArray() {
		numberArray = new String[13];
		numberArray[0] = "A";
		numberArray[1] = "2";
		numberArray[2] = "3";
		numberArray[3] = "4";
		numberArray[4] = "5";
		numberArray[5] = "6";
		numberArray[6] = "7";
		numberArray[7] = "8";
		numberArray[8] = "9";
		numberArray[9] = "10";
		numberArray[10] = "J";
		numberArray[11] = "Q";
		numberArray[12] = "K";
	}

	// �m��3 : �нm�ߵo��l�G���}�C�A��J52�i�P
	public void initCards() {
		this.initColorArray();
		this.initNumberArray();
		cards = new String[52];
		int cnt= 0,value1 = 0,value2 = 0;
		String s1;
		Random random = new Random();       
		s1 = " ";
		for (int index = 0; index < colorArray.length; index++) {
			for (int tag = 0; tag < numberArray.length; tag++) {			
				value1 = random.nextInt(colorArray.length);
				value2 = random.nextInt(numberArray.length);			  
				
				while  (s1.contains(colorArray[value1] + numberArray[value2])) {
					value1 = random.nextInt(colorArray.length);
					value2 = random.nextInt(numberArray.length);				
				}
				cards[cnt] = colorArray[value1] + numberArray[value2];	
				s1 = s1+ (colorArray[value1] + numberArray[value2]) +" ";
				System.out.print(cards[cnt] + "\t");
				cnt ++; 
			}
			System.out.print("\n");
		}
		 
	}
}
