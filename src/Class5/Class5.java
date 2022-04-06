package Class5;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Class5 {
  public static void main(String[] args) {
	MyStringBuffer myStringBuffer =  new MyStringBuffer();
	myStringBuffer.testCreateSpeed();
	myStringBuffer.testAppendSpeed();
	myStringBuffer.testThreadBuffer();
//
	System.out.println("--------------------");
	System.out.println("�`�Φr���k");
	// String��k indexOf �i�H�X�r��Φr���X�{����m
	// �Y�S���X�{�ɡA�|�^��-1
	// �i�H�ΨӧP�_�@�Ӧr�ꤺ�A�O�_���X�{����r
	// indexOf test
	System.out.println("X�X�{����m => " + "XXXX".indexOf("X"));
	System.out.println("�ťդ]�i�H�䴩 => " + "XX X".indexOf(" "));
	System.out.println("���s�b�� => " + "XX1X".indexOf("2"));
	// replace ���N��r
	System.out.println("XXXX".replace("X", "*"));
//    
//    
//	System.out.println("--------------------");
//	System.out.println("�`��Date��k");
//	MyDate date = new MyDate();
//	date.showCommonDate();
//	date.showFormatTime();
//    
//	System.out.println("--------------------");
//	System.out.println("�`��BigDecimal��k");
//	MyBigDecimal bigDecimal = new MyBigDecimal();
//	bigDecimal.showCompute("1", "2");
//	bigDecimal.trick();
//    
//	System.out.println("--------------------");
	System.out.println("�`��Calendar��k");
	MyCalendar calendar = new MyCalendar();
	calendar.defaultSet();
	calendar.testBetween();
	calendar.testBetweenTwo();
  }
}


/**
 * <pre>
 * String vs StringBuffer vs StringBuilder �t��
 *
 * String
 * [Good]
 *  ��A���r��ܤ��ܰʮ�
 *  ��¥ͦ��@�Ӧr��ϥή�
 *  �t�׷|��StringBuffer�PStringBuilder�٧�
 *  
 * [Bad]
 *  �p�G��A�ݭn��L�W�[�δ�֦r�ꤺ�e��
 *  �bJava�̭���곣�|�ͦ��@�ӷs��String����
 *  �i�O���e�����e�ٷ|�s�bJVM�̭�
 *  �p�G�L�׼W�[��֡AJVM��������GC(�۰ʩU���^������)
 *  �]�|�Q�ҰʡA�i��|�ɭP�į�C��
 *  
 * StringBuffer
 * [Good]
 *  ��A���r��ܸg�`�ܰʮɡA�i�H�Ҽ{�ϥ�StringBuffer
 *  �]���bJava�ͦ�����ɡA�ڭ̹�StringBuffer������
 *  �i��W�[�ΧR����r�A���|�ާ@��P�@��StringBuffer����
 *  �۹��Java�O����t��A�|���C�\�h
 *  
 *  Java.lang.StringBuffer ��������w�����i�ܦr��.
 *  ������ String ���r��A������ק�A
 *  �i�N�r�Ŧ�w�İϦw���a�Ω�h�Ӱ����
 *  
 *  ��ڭ̭n�ഫ��String����ɡA�u�n�ϥ�toString�Y�i�ഫ
 *  
 *  �L�O�@�ӦP�B������A�|���ݨC�Ӱ�������槹��
 *  �۹�B�z�|����C
 *  
 * [Bad]
 *  �Y�u�O��¥ͦ��@�ӥi�H�ϥΪ��r���
 *  �t�׷|��StringBuilder�PString�ٺC
 *
 * StringBuilder
 * [Good]
 *  StringBuilder�۹��StringBuffer�A�u��b��@������ϥ�
 *  �򥻤W�ާ@��StringBuffer�j�h�@�P�A���O�t�פW�|��StringBuffer
 *  �\�h
 *  
 * [Bad]
 *  �L�O�@�ӫD�P�B������A�C�Ӱ��������|�۰ʦۤv�ۦ�
 *  ���O�h������ɡA�ϥΨä��w��
 *  
 *  �r��ͦ��t��
 *  String > StringBuilder > StringBuffer
 *  
 *  �g�`�ܰʡA�ק�t��
 *  StringBuilder > StringBuffer > String
 *
 *  �h������ϥ�
 *  StringBuffer
 *
 * </pre>
 */
class MyStringBuffer {
 
  public void testCreateSpeed(){
	int timer = 100000000;
	Date date = new Date();
	for(int index = 0 ; index < timer ; index++){
  	String srting = "";
	}
	System.out.println("String �ͦ��t�� : "+ (new Date().getTime() - date.getTime())/ 1000.0 + "s");
	Date date2 = new Date();
	for(int index = 0 ; index < timer ; index++){
  	new StringBuffer("");
	}
	System.out.println("StringBuffer �ͦ��t�� : "+ (new Date().getTime() - date2.getTime())/ 1000.0  + "s");
	Date date3 = new Date();
	for(int index = 0 ; index < timer ; index++){
  	new StringBuilder("");
	}
	System.out.println("StringBuilder �ͦ��t�� : "+ (new Date().getTime() - date3.getTime())/ 1000.0  + "s");
  }
 
  public void testAppendSpeed(){
	int timer = 10000;
	Date date = new Date();
	String srting = "";
	for(int index = 0 ; index < timer ; index++){
  	srting = srting + String.valueOf(index);
	}
	System.out.println("String �W�[�r��A�B�z�t�� : "+ (new Date().getTime() - date.getTime())/ 1000.0 + "s");
	Date date2 = new Date();
	StringBuffer buffer = new StringBuffer("");
	for(int index = 0 ; index < timer ; index++){
  	buffer.append(index);
	}
	System.out.println("StringBuffer �W�[�r��A�B�z�t�� : "+ (new Date().getTime() - date2.getTime())/ 1000.0  + "s");
	Date date3 = new Date();
	StringBuilder builder = new StringBuilder("");
	for(int index = 0 ; index < timer ; index++){
  	builder.append(index);
	}
	System.out.println("StringBuilder �W�[�r��A�B�z�t�� : "+ (new Date().getTime() - date3.getTime())/ 1000.0  + "s");
  }
 
  public void testThreadBuffer(){
	int timer = 10;
	Date date = new Date();
	String string = "";
	for(int index = 0 ; index < 10 ; index ++){
  	new MyStringThread(string , timer).run();
	}
	System.out.println("String Thread�B�z�t�� : "+ (new Date().getTime() - date.getTime())/ 1000.0 + "s");
	Date date2 = new Date();
	StringBuffer stringBuffer = new StringBuffer();
	for(int index = 0 ; index < 10 ; index ++){
  	new MyStringBufferThread(stringBuffer , timer).run();
	}
	System.out.println("StringBuffer Thread�B�z�t�� : "+ (new Date().getTime() - date2.getTime())/ 1000.0  + "s");
	Date date3 = new Date();
	StringBuilder stringBuilder = new StringBuilder("");
	for(int index = 0 ; index < 10 ; index ++){
  	new MyStringBuilderThread(stringBuilder , timer).run();
	}
	System.out.println("StringBuilder Thread�B�z�t�� : "+ (new Date().getTime() - date3.getTime())/ 1000.0  + "s");
  }
 
}

class MyStringThread extends Thread implements Runnable{
 
  private String string ;
  private int timer;
 
  public MyStringThread(String string , int timer){
	this.string = string;
	this.timer = timer;
  }
 
  public void testAppendSpeed(){
	Date date = new Date();
	for(int index = 0 ; index < timer ; index++){
  	string = string + String.valueOf(index);
	}
	string = string + " | ";
	System.out.println("srting = " + string);
	terminate();
  }
 
  public void run() {
	testAppendSpeed();
  }
 
  private boolean isContinue = true;

  public void terminate() {
	isContinue = false;
	interrupt();
  }
 
}

class MyStringBufferThread extends Thread implements Runnable{
 
  private StringBuffer buffer;
  private int timer;
 
  public MyStringBufferThread(StringBuffer buffer , int timer){
	this.buffer = buffer;
	this.timer = timer;
  }
 
  public void testAppendSpeed(){
	Date date2 = new Date();
	for(int index = 0 ; index < timer ; index++){
  	buffer.append(index);
	}
	buffer.append(" | ");
	System.out.println("buffer = " + buffer);
	terminate();
  }
  public void run() {
	testAppendSpeed();
  }
 
  private boolean isContinue = true;

  public void terminate() {
	isContinue = false;
	interrupt();
  }
 
}

class MyStringBuilderThread extends Thread implements Runnable{
 
  private StringBuilder builder;
  private int timer;
 
  public MyStringBuilderThread(StringBuilder builder , int timer){
	this.builder = builder;
	this.timer = timer;
  }
 
  public void testAppendSpeed(){
	Date date3 = new Date();
	for(int index = 0 ; index < timer ; index++){
  	builder.append(index);
	}
	builder.append(" | ");
	System.out.println("builder = " + builder);
	terminate();
  }
 
  public void run() {
	testAppendSpeed();
  }
 
  private boolean isContinue = true;

  public void terminate() {
	isContinue = false;
	interrupt();
  }
 
}

/**
 * <pre>
 * Java���ت��ɶ�����
 * ���s����Java�ܦh��k�Q��������ĳ�ϥ�
 * �ҥH�ڭ̥u²�椶�Ф@�Ǳ`�����Y�i
 *
 * �ȳѤU�C�|�ӬO�`�Ϊ���k
 *  after(Date when) : �ˬd�������ɶ��O�_�b������� (true/false)   
 *  before(Date when) : �ˬd�������ɶ��O�_�b������e (true/false)   
 *  getTime() : �Ǧ^�� 1970/1/1 �H�Ӥ��@���
 *  setTime(int ms) : �N�ɶ��]�w�� 1970/1/1 �ᤧ�@���
 *  toString() : �� Date �����ন�r
 * </pre>
 */
class MyDate {
  public void showCommonDate(){
	Date now = new Date();                	//�إߥثe�ɶ��� Date ����
	System.out.println(now.getTime());   //��X 1396313616224
	System.out.println(System.currentTimeMillis());  //��X 1396313616224
	System.out.println(now.toString());   //��X Tue Apr 01 08:53:36 CST 2014
	Date before=new Date(1);             	//�إ߮ɶ��W�O�� 1ms �� Date ���� (�� 1970/1/1)
	System.out.println(before.before(now));	//��X true
	System.out.println(now.before(before));	//��X false
	System.out.println(before.after(now)); 	//��X false
	System.out.println(now.after(before)); 	//��X true
	before.setTime(4396267507921L);        	//���������
	System.out.println(now.after(before)); 	//��X false
  }
 
  public void showFormatTime(){
	//  �����榡�ƿ�X�{�b�ɶ�����k
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	Date current = new Date();
	System.out.println(sdFormat.format(current));
  }
}


/**
 * <pre>
 * �p�G�ڭ̭n�ϥΩ�n���Ʀr���ưȤW
 * ���ڭ̷|��ĳ�Өϥ�BigDecimal�ӹ�@�\��
 * �L�]���򥻪��[����P������\��
 * ���O�󬰺�T
 * </pre>
 */
class MyBigDecimal{
 
  public void showCompute(String num1 , String num2){
	System.out.println(num1 + " + " + num2 + " = " + new BigDecimal(num1).add(new BigDecimal(num2)) );
	System.out.println(num1 + " - " + num2 + " = " + new BigDecimal(num1).subtract(new BigDecimal(num2)) );
	System.out.println(num1 + " * " + num2 + " = " + new BigDecimal(num1).multiply(new BigDecimal(num2)) );
	System.out.println(num1 + " / " + num2 + " = " + new BigDecimal(num1).divide(new BigDecimal(num2)) );
	System.out.println(num1 + " = " + num2 + " = " + new BigDecimal(num1).compareTo(new BigDecimal(num2)) );
  }
 
  // ��l�Ƴ���
  public void trick(){
	System.out.println("1.25959 float  => " + new BigDecimal(1.25959));    
	System.out.println("1.25959 Double => " + new BigDecimal(new Double(1.25959)));
	System.out.println("1.25959 String => " + new BigDecimal("1.25959"));
  }
 
}


/**
 * <pre>
 * Calendar
 * �OJava�@�ɸ̡A�ާ@�ɶ����u��
 * ���P��Date
 * Calendar�i�H��@���[��B��
 * �H�U�ڭ̴N�m�ߧa
 * </pre>
 */
class MyCalendar {
 
  public void defaultSet(){
	Calendar c = Calendar.getInstance();
	c.set(2017, 11, 12);                   	// �]�w�����2017�~11��12��A
	System.out.println("��l�ɶ�:" + format(c));         	// �bCalendar���O��������s���O��0~11

	c.set(Calendar.YEAR, 2013);            	// �N�~�令2013�~
	System.out.println("�ק�~��:" + format(c));
    
	c.set(Calendar.MONTH, Calendar.JANUARY);  // �N����令1��
	System.out.println("�ק���:" + format(c));
    
	c.set(Calendar.DAY_OF_MONTH , 31);     	// �N��令31��
	System.out.println("�ק���:" + format(c));
    
	// �ɶ��O24�p�ɨ�
	c.set(Calendar.HOUR_OF_DAY, 18);       	// �Nhour�令�U�Ȥ��I
	System.out.println("�ק�ɶ�:" + format(c));                	 
    
	c.set(Calendar.AM_PM, Calendar.PM);    	// �Nhour�令�U�Ȥ��I
	System.out.println("�ק�ɶ�:" + format(c));
    
	c.set(Calendar.HOUR, 6);
	System.out.println("�ק�ɶ�:" + format(c));
    
  }
 
  // calendar�i�H�૬��date
  // �èϥ�SimpleDateFormat
  // �i��ɶ����榡��
  private String format(Calendar calendar){
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	Date date = calendar.getTime();
	return df.format(date);
  }
 
  // �ɶ��������
  public void testBetween(){
	Calendar birth = Calendar.getInstance();
	birth.set(1990, Calendar.MAY, 26);
	Calendar now = Calendar.getInstance();
	System.out.println(daysBetween(birth, now));
	System.out.println(daysBetween(birth, now)); // ��� 0�H
  }
 
  // �p�G�ǰe���󫬺ACalendar�A�b�o��k�ק諸�ȡA���|�Q�v�T��
  private long daysBetween(Calendar begin, Calendar end) {
	long daysBetween = 0;
	while(begin.before(end)) {
    	begin.add(Calendar.DAY_OF_MONTH, 1);
    	daysBetween++;
	}
	return daysBetween;
  }
 
  public void testBetweenTwo(){
	Calendar birth = Calendar.getInstance();
	birth.set(1986, Calendar.FEBRUARY, 6);
	Calendar now = Calendar.getInstance();
	System.out.println(daysBetweenTwo(birth, now) + "��");
	System.out.println(daysBetweenTwo(birth, now) + "��");
  }
 
  private long daysBetweenTwo(Calendar begin, Calendar end) {
	Calendar calendar = (Calendar) begin.clone(); // �ƻs
	long daysBetween = 0;
	while(calendar.before(end)) {
    	calendar.add(Calendar.DAY_OF_MONTH, 1);
    	daysBetween++;
	}
	return daysBetween;
  }
 
 
}


//StringBuffer & StringBuilder Thread un safe 

//package Class5;

//public class StringTest {
//
//  public static void main(String[] args) {
//	new MyStringTestOne().exec();
//	new MyStringTestTwo().exec();
//  }
//}
//
//class MyStringTestOne{
//  static boolean running = true;
//  public void exec(){
//
//  	StringBuilder sb = new StringBuilder();
//  	System.out.println("before , StringBuilder = " + sb.toString());
//  	new Thread(new Runnable() { public void run() {
//    	while (running) sb.append("test");
//  	}}).start();
//  	System.out.println("start, StringBuilder = " + sb.toString());
//  	try {
//    	Thread.sleep(10);
//  	} catch (InterruptedException e) {
//    	e.printStackTrace();
//  	}
//  	for (int i = 0; i < 10; i++) {
//    	sb.append("hello");
//  	}
//  	running = false;
//  	System.out.println("StringBuilder , replace = " + sb.toString().replace("test", ""));
//  	System.out.println("StringBuilder , end = " + sb.toString());
//  	System.out.println("---------------------------------------------------------------");
//  }
//}
//
//class MyStringTestTwo{
//  static boolean running = true;
//  public void exec(){
//
//  	StringBuffer sb = new StringBuffer();
//  	System.out.println("before , StringBuffer = " + sb.toString());
//  	new Thread(new Runnable() { public void run() {
//    	while (running) sb.append("test");
//  	}}).start();
//  	System.out.println("start, StringBuffer = " + sb.toString());
//  	try {
//    	Thread.sleep(10);
//  	} catch (InterruptedException e) {
//    	e.printStackTrace();
//  	}
//  	for (int i = 0; i < 10; i++) {
//    	sb.append("hello");
//  	}
//  	running = false;
//  	System.out.println("StringBuffer , replace = " + sb.toString().replace("test", ""));
//  	System.out.println("StringBuffer , end = " + sb.toString());
//  	System.out.println("---------------------------------------------------------------");
//  }
//}
