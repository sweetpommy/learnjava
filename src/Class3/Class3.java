package Class3;

public class Class3 {
  public static void main(String[] args) {
	System.out.println("�r�����P�j��p�����ɥR");
	System.out.println("new String(\"Tom\") == new String(\"Tom\")  => " + new MyString().equalsString());
	System.out.println("new String(\"Tom\").equals(new String(\"Tom\")) => " + new MyString().equalsString2());
	System.out.println("1>2  =>" + (1>2));
	System.out.println("1>=2 =>" + (1>=2));
	System.out.println("1<=2 =>" + (1<=2));
	System.out.println("1<2 =>" + (1<2));
	System.out.println("1==2 =>" + (1==2));
	System.out.println("if else");
	MyIfElseIfElse myIfElseIfElse = new MyIfElseIfElse();
	System.out.println("10�|�Q 2 �㰣 ? => "+ myIfElseIfElse.isDivideBy2(10));
	System.out.println("��Ƭ۰��A���|���l��=>" + (3/10));
	System.out.println("2,3,5�㰣="+myIfElseIfElse.isDivideBy235(30));
	// for�j��
	System.out.println("for�j��");
	MyFor for1 = new MyFor();
	for1.testForOne(10);
	for1.testForOne();
	System.out.println("while�j��");
	MyWhile myWhile = new MyWhile();
	myWhile.testWhile();
	System.out.println("doWhile�j��");
	MyDoWhile doWhile = new MyDoWhile();
	doWhile.testDoWhile();
	doWhile.doWhile();
	System.out.println("�G���}�C");
	MyArray a = new MyArray();
	a.arrayMutil();
	System.out.println("�o�P");
	a.initCards();
  }
}


class MyString {
  public boolean equalsString(){
	return new String("Tom") == new String("Tom");
  }
 
  public boolean equalsString2(){
	return new String("Tom").equals(new String("Tom"));
  }
 
}


class MyIfElseIfElse {
  public boolean isDivideBy2(int num) {
	System.out.println("isDivideBy2 result = " + (num % 2));
	if ((num % 2) == 0) {
  	return true;
	} else {
  	return false;
	}
  }
 
  public boolean isDivideBy235(int num) {
	System.out.println(num);
	if ((num % 2 == 0) && (num % 3 == 0) && (num % 5 == 0)) {
  	return true;
	} else {
  	return false;
	}
  }
    
}


class MyFor {

  // �L�X 0~10
  public int[] testForOne(int size){
	// ��l���׬�10���@���}�C
	int[] intArray = new int[size];
	// ��l�� ; true or false ; �ܼƭȥ[�δ�
	for(int index = 0 ; index < intArray.length ; index++){
  	intArray[index] = index;
  	System.out.println("index = " + index + ", testArray = " + intArray[index]);
	}
	System.out.println();
	return intArray;
  }
 
  // 1*1=1 , 1*2=2 ...... 1*9=9
  public void testForOne(){
	System.out.println("�D�� :�@1*1=1 , 1*2=2 ...... 1*9=9");
	int sum[]=new int[10];
	for(int i=1;i<10;i++){
  	sum[i]=i*1;	 
  	System.out.print("1*"+ i +"="+sum[i]);
  	if(i<9){
    	System.out.print(",");
  	}
	}
	System.out.println();
  }
 
}


class MyWhile {
  public void testWhile(){
	System.out.println("�D�� :�@1*1=1 , 1*2=2 ...... 1*9=9");
	int i = 1 ;
	while(i<10){
  	System.out.print("1*"+ i +"="+1*i);
  	if(i<9){
    	System.out.print(",");
  	}
  	i++; //=> i = i + 1;
	}
	System.out.println();
  }
}

class MyDoWhile {
  public void testDoWhile(){
	System.out.println("�D�� :�@1*1=1 , 1*2=2 ...... 1*9=9");
	int i = 1 ;
	do{
  	System.out.print("1*"+ i +"="+1*i);
  	if(i<9){
    	System.out.print(",");
  	}
  	i++; //=> i = i + 1;
	} while(i<10);
	System.out.println();
  }
 
  // �m�� : �Х� do while �g�E�E���k��
  public void doWhile() {
	int i, j;
	int sum;
	i = 1;
	do {
  	j = 1;
  	do {
    	sum = i * j;
    	System.out.print(i + "*" + j + "=" + sum);
    	System.out.print("\t");
    	j++;
  	} while (j < 10);
  	System.out.println();
  	i++;
	} while (i < 10);
 
}
 
 
}

class MyArray {
  int[] oneArray;
  int[][] twoArray;
 
  String[] colorArray;
  String[] numberArray;
  String[][] cards;
 
  public void initOneArray(int size){
	oneArray = new int[size];
	for (int i = 0; i < size; i++) {
  	oneArray[i] = i;
	}
  }
 
  // �m��1 : �Шϥ����h�j��A��1~9���G���}�C
  public void initTwoArray(){
	twoArray = new int [9][9];//0-8
	for (int i = 1; i < twoArray.length+1; i++) {
  	for (int j = 1; j < twoArray[i-1].length+1; j++) {
    	twoArray[i-1][j-1]=j;
  	}
	}
  }
 
  // �m��2 : �G���}�C��l�����A�а}�C�����Ʀr
  // �L�X�E�E���k��
  public void arrayMutil() {
	twoArray = new int [9][9];//0-8
	for (int i = 0; i < twoArray.length; i++) {
  	for (int j = 0; j < twoArray[i].length; j++) {
    	twoArray[i][j]=j+1;
    	twoArray[j][i]=i+1;
    	System.out.print(twoArray[j][i] + "*" + twoArray[i][j] + "=" + twoArray[j][i]*twoArray[i][j] + "\t");
  	}
  	System.out.println();
	}
    
  }
 
  // ���
  public void initColorArray(){
	colorArray = new String [4];
	colorArray[0] = "����";
	colorArray[1] = "���";
	colorArray[2] = "�R��";
	colorArray[3] = "�®�";
  }
 
  // �Ʀr
  public void initNumberArray(){
	numberArray = new String [13];
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
  public void initCards(){
	this.initColorArray();
	this.initNumberArray();
	cards = new String[4][13];
	for(int index = 0 ; index < colorArray.length ; index++){
  	for (int tag = 0; tag < numberArray.length; tag++) {
    	cards[index][tag] = colorArray[index] + numberArray[tag];
    	System.out.print(cards[index][tag] + "\t");
  	}
  	System.out.print("\n");
	}
  }
 
 
}
