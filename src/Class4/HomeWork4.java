package Class4;

import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {
	public static void main(String args[]) {

//		6
		System.out.println("=====HomeWork4_Q1======");
//		Q4_1 q4_1 = new Q4_1();
//		q4_1.sortNumber("S");
//		q4_1.sortNumber("M");	
		
//		System.out.println("=====HomeWork4_Q2======");
//		Q4_2 q4_2 = new Q4_2();	
//		q4_2.Compute();
		
//		System.out.println("=====HomeWork4_Q3======");
		Q4_3 q4_3 = new Q4_3();	
		q4_3.Month_List_Days();	
		
//		System.out.println("=====HomeWork4_Q4======");
//		Q4_4 q4_4 = new Q4_4();	
//		q4_4.play_game();
		System.out.println("=====HomeWork4 END======");
	}
}


class Q4_1 {
	int NumA[] = { 100, 10, 7, 78, 87, 45, 32, 11, 10 };

	public void print(String arg_sort) {
		System.out.print("Sort By-" + (arg_sort  == "S" ? "SmalltoMax" : "MaxToSmall")+":");
		for (int i = 0; i < NumA.length; i++) {				
			if (i == NumA.length - 1) {				 
				System.out.print(NumA[i]);
			} else {
				System.out.print(NumA[i] + ",");
			}
		}
		System.out.print("\n");
	}

	public void sortNumber(String arg_sort) {
		for (int i = 0; i < NumA.length; i++) {
			for (int j = 0; j < NumA.length - 1; j++) {
				if ((NumA[j] > NumA[j + 1]) && arg_sort =="S") {
					swapNumber(j, j + 1);
				}
				
				if ((NumA[j] < NumA[j + 1]) && arg_sort =="M") {
					swapNumber(j, j + 1);
				}
			}

		}
		print(arg_sort);
	}

	public void swapNumber(int beforeIndex, int footNum) {
		int temp = NumA[footNum];
		NumA[footNum] = NumA[beforeIndex];
		NumA[beforeIndex] = temp;
	}  
}

class Q4_2 {
	
	double ld_num1 ,ld_num2  ;
	String ls_op = " ";
	Scanner scanner = new Scanner(System.in);
	
	public void inputNum() {

		System.out.print("�п�J�Ĥ@�ӼƦr:");
		ld_num1 = scanner.nextFloat();
	    System.out.printf("�Ĥ@�ӼƦr: "+ String.format("%.02f", ld_num1) ) ;
		System.out.println();
		
		System.out.print("�п�J�ĤG�ӼƦr:");
		ld_num2 = scanner.nextFloat();
	    System.out.printf("�ĤG�ӼƦr:"+ String.format("%.02f", ld_num2) ) ;
		System.out.println();
		
		System.out.println("�п�J�B��Ÿ�( + or - or * or / ):"); 
		ls_op = scanner.next();
		
	}
	
	public void  Compute() {
		
		String ls_stop = "X";
		System.out.println("�п�J��ӼƦr�A�ÿ��+-*/�i��B��");
		
		while( !(ls_stop.equals("N")) ) {
			inputNum();
			switch (ls_op) {
			case "+":
				System.out.println("�[�k: " + ld_num1  + "+" +  ld_num2 + "=" + (ld_num1 + ld_num2));
				break;
			case "-":
				System.out.println("��k: " + ld_num1  + "-" +  ld_num2 + "=" + (ld_num1 - ld_num2));
				break;
			case "*":
				System.out.println("���k: " + ld_num1  + "*" +  ld_num2 + "=" + (ld_num1 * ld_num2));
				break;
			case "/":
				System.out.println("���k: " + ld_num1  + "/" +  ld_num2 + "=" + String.format("%.02f", ld_num1 / ld_num2) );
				break;
			
			}		

			System.out.println();
			System.out.println("�~�����?�A�Y�n�����п�JN�C�~�����п�JX���~��C");
			ls_stop = scanner.next(); 
			
		}

		System.out.println("END");		
		
	}
}

class Q4_3 {

	public void Month_List_Days() {

		int li_days = 0;
		for (int y = 2007; y <= 2022; y++) {

			System.out.println(y);
			for (int m = 1; m <= 12; m++) {

				System.out.printf(m + "��:");
				switch (m) {
				case 2:
					li_days = (((y % 4) == 0 && (y % 100) != 0) || (y % 400) == 0 ? 29 : 28);
					break;				
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					li_days = 31;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					li_days = 30;
					break;
				}

				for (int i = 1; i <= li_days; i++) {
					if (i <  li_days)
						System.out.print(i +   ", " );
					else
						System.out.print(i  );	
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}


class Q4_4 {
	
	int beg_Num = 1;
	int end_Num = 100;
	int focusNum = 0;
	int Num_input = 0;
	Random random = new Random();   
	public void Ran_Num() {
		focusNum = random.nextInt(end_Num-beg_Num +1);
		
	}

	public void inputNum() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("�п�J�Ʀr:");
		Num_input = scanner.nextInt();		
	}
	public void checkNum() {
		
		if (focusNum == Num_input) {
			beg_Num = Num_input;
			end_Num = Num_input;			
		}else {

			if (Num_input > beg_Num && Num_input < focusNum) {
				beg_Num = Num_input;
			}else if( Num_input < end_Num && Num_input > focusNum) {
				end_Num = Num_input;
			}
		}
	}
	
	public void play_game() {
		
		int li_time = 0;
		String insNumStr = " ";
		
		System.out.println("�п�J1~100���@�ӼƦr�A�Y�q���C������~");
		Ran_Num(); //���o�H����
//		System.out.println(focusNum);
		
		while(focusNum != Num_input ) {			
			inputNum();
			li_time ++;			
			
			if (focusNum != Num_input) { 
				insNumStr = insNumStr + Num_input+ ","; 
				System.out.println("�q���Ʀr = " + Num_input + ", �q�F " + insNumStr + "�@"+li_time+"��");				
				checkNum();
				System.out.println("Sorry! �Ʀr�d��b " + beg_Num + "~" + end_Num);
			}else {
				System.out.println( li_time + " ���A�q���K�X : " + focusNum);	
				break;
			}
			
		};
			
	}
	
	
}
