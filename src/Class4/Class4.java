package Class4;

import java.util.Random;
import java.util.Scanner;

public class Class4 {
  public static void main(String[] args) {
	    new MySwapNum().swapNumber(100, 10);
	    new MyOtherIfElse().isDivide2(2);
//	    new MyOtherIfElse().isDivide235(31);
	    
	    
//	    new MySystemIn().whileInput();
	    
	    System.out.println("MyNumberSwtichCase");
	    new MySwtichCase().MyNumberSwtichCase(1);
	    new MySwtichCase().MyNumberSwtichCase(0);
	    new MySwtichCase().MyNumberSwtichCase(3);
	    new MySwtichCase().MyStringSwitchCase("QQ");
	    new MySwtichCase().MyStringSwitchCase("Tom");
	    System.out.println("MyRandom");
	    new MyRandom().random(30);
  }
}

// �洫
class MySwapNum{
  public void swapNumber(int headNum , int footNum){
    System.out.println("�洫�e : " + headNum + " , " + footNum);
    int temp = headNum;
    headNum = footNum;
    footNum = temp;
    System.out.println("�洫�� : " + headNum + " , " + footNum);
  }
}


// �T���B��
class MyOtherIfElse {
  public void isDivide2(int num){
    System.out.println("num = " + num +"�O�_�i�Q2�㰣" + (num % 2 == 0 ? true : false));
  }
  
  public void isTom(String str){
    System.out.println("Name is " + str +". is Tom ? " + ("Tom".equals(str) ? true : false) );
  }
  
  // �m�ߡA�ǤJ�@�ӼƦr�A�^�ǬO�_�i�H�Q 2 3 5�㰣�A�ШϥΤT���B��
  public void isdivide235(int num) {
	    System.out.println("Year = " + num +"�O�_�O�|�~?" + (num % 400 == 0 ? true : (num % 4 == 0 && num % 100 != 0 ? true : false)));	  
  }
  
}


class MySystemIn {
  public void input(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please input your name: ");
    System.out.printf("Hello! %s!", scanner.next());
  }
  
  // �m�� �Шϥ�While�L�a�j��A���ϥΪ̫����J��r�A�����J0�A�����{��

  
}

class MySwtichCase {
  public void MyNumberSwtichCase(int num){
    switch (num) {
      case 0:
        System.out.println("Hello world");
        break;
      default:
        System.out.println("WTF??");
        break;
    }
  }
  
  public void MyStringSwitchCase(String str){
    switch (str) {
      case "Tom":
      case "ABC":    	  
        System.out.println("WelCome Back Admin , " + str);
        break;
      default:
        System.out.println("Hi , " + str);
        break;
    }
  }
}


class MyRandom {
  public void random(int size){
    Random ran = new Random();
    String str = "";
    for (int i = 1; i < size; i++) {
      str += Integer.toString(ran.nextInt(size)+1);
      if(i<size-1){
        str += ",";
      }
    }
    System.out.println(str);
  }
}


class MyBreakContinue{
  public void test1(){
    for(int i = 0 ; i < 10 ; i++){
      if(i % 2 == 0){
        continue;
      }
      if(i%7 == 0){
        break;
      }
      System.out.println(" i = " + i );
    }
  }
}