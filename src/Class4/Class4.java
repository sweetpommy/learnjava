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

// 交換
class MySwapNum{
  public void swapNumber(int headNum , int footNum){
    System.out.println("交換前 : " + headNum + " , " + footNum);
    int temp = headNum;
    headNum = footNum;
    footNum = temp;
    System.out.println("交換後 : " + headNum + " , " + footNum);
  }
}


// 三元運算
class MyOtherIfElse {
  public void isDivide2(int num){
    System.out.println("num = " + num +"是否可被2整除" + (num % 2 == 0 ? true : false));
  }
  
  public void isTom(String str){
    System.out.println("Name is " + str +". is Tom ? " + ("Tom".equals(str) ? true : false) );
  }
  
  // 練習，傳入一個數字，回傳是否可以被 2 3 5整除，請使用三元運算
  public void isdivide235(int num) {
	    System.out.println("Year = " + num +"是否是閏年?" + (num % 400 == 0 ? true : (num % 4 == 0 && num % 100 != 0 ? true : false)));	  
  }
  
}


class MySystemIn {
  public void input(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please input your name: ");
    System.out.printf("Hello! %s!", scanner.next());
  }
  
  // 練習 請使用While無窮迴圈，讓使用者持續輸入文字，直到輸入0，結束程式

  
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