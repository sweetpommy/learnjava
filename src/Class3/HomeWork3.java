package Class3;

public class HomeWork3 {
	public static void main(String args[]) {
		String name;
		int age;

		System.out.println("=====HomeWork3_Q1======");
		
		q3_1 Q3_1 = new q3_1();
		Q3_1.Forloop();
		System.out.println("=====HomeWork3_Q2======");
		q3_2 Q3_2 = new q3_2();
		Q3_2.usingFor();
	}
}

/**
 * <pre>
  3-1.請使用for迴圈，練習印出以下圖案
 *
 **
 ***
 ****
 *****
 ****
 ***
 **
 *
 * </pre>
 */
class q3_1 {
	public void Forloop() {
		int i,j;
		String ls_string1;
		ls_string1 = "*";
		System.out.println("<pre>");
		for (i = 1; i < 5; i++) {
			System.out.println(ls_string1);
			ls_string1 = ls_string1 +"*";
		}
		System.out.println(ls_string1);
		for (j = 5; j >1; j--) {
			//System.out.println(ls_string1.length()); 
			System.out.println(ls_string1.substring(0, j-1));
		}
		System.out.println("</pre>");
	}
}

class q3_2{
	  
	  public void usingFor(){
	    int i,j;
	    //String ls_space= " ";
		for (i = 1; i <= 9; i++) {
			for (j = 1; j <= 9; j++) {
				System.out.print(i+"*"+j+"="+(i*j)); 
				System.out.print("\t");    // 大空格
			}
			System.out.println();
		}	    
	  }
	  
	  public void usingWhile(){
	    
	  }
	}