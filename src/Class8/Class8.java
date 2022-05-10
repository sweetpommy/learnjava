package Class8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Class8 {
  public static void main(String[] args) {
    // �C�|
//    System.out.println(Month.one);
//    Hellworld helloWorld = Hellworld.un;
//    ChooseMonth.un.ChooseMonth();
//    ChooseMonth.un.ChooseMonth_s(Month.five);
    // �ҥ~����
//    new MyTryCatch().exec();
//    
//    // Thread
//    new MyThread().doNormal();
//    new MyThread().doTheadJoin();
    new MyThread().doList();
  }
}

 

/**
 * <pre>
 * �C�|2
 * 
 * �o�O�@�ӯS�O���g�k
 * �ڭ̥i�H�ΦC�|���S��
 * ���ͤ@�Ӫ���Өϥ�
 * �u�I�O�A�b�Y�ǳ��X�U
 * �i�H�T�O���ͪ�����u�|���@��
 * 
 * </pre>
 */
enum Hellworld {
  un;
  Hellworld(){
    System.out.println("HelleWorld");
  }
}

/**
 * �C�|3
 * �ϥΦbswitch case
 */
enum ChooseMonth {
  un;
  private ChooseMonth(){
  }
  public void ChooseMonth(){
    Month chooseMonth = Month.one;
    switch (chooseMonth) {
      case one:
        System.out.println("1��");
        break;
      case two:
        System.out.println("2��");
        break;  
      case three:
        System.out.println("3��");
        break;
      case four:
        System.out.println("4��");
        break;
      case five:
        System.out.println("5��");
        break;
      case six:
        System.out.println("6��");
        break;  
      case seven:
        System.out.println("7��");
        break;
      case eight:
        System.out.println("8��");
        break;
      case nine:
        System.out.println("9��");
        break;
      case ten:
        System.out.println("10��");
        break;  
      case eleven:
        System.out.println("11��");
        break;
      case twelve:
        System.out.println("12��");
        break;
      default:
          
    }
  }
  
  public void ChooseMonth_s(Month chooseMonth){ 
	    switch (chooseMonth) {
	      case one:
	        System.out.println("1��");
	        break;
	      case two:
	        System.out.println("2��");
	        break;  
	      case three:
	        System.out.println("3��");
	        break;
	      case four:
	        System.out.println("4��");
	        break;
	      case five:
	        System.out.println("5��");
	        break;
	      case six:
	        System.out.println("6��");
	        break;  
	      case seven:
	        System.out.println("7��");
	        break;
	      case eight:
	        System.out.println("8��");
	        break;
	      case nine:
	        System.out.println("9��");
	        break;
	      case ten:
	        System.out.println("10��");
	        break;  
	      case eleven:
	        System.out.println("11��");
	        break;
	      case twelve:
	        System.out.println("12��");
	        break;
	      default:
	          
	    }
	  }
}


/**
 * <pre>
 *  �ҥ~����
 *  �{������A�Y�O�X�{
 *  �i��|�L�k�x���X�����ɭԮ�
 *  �аȥ��ϥ�try catch���B�z
 *  �H�K�{�����_��A�L�k����
 * </pre>
 */
class MyTryCatch{
  public void exec(){
    try{
      System.out.println("�����k => EmptyClass qq = null ");
      EmptyClass qq = null;
      qq.name="";
    } catch (Exception e){
      // Exception �O�Ҧ����~�ƥ�Object�@��
      // �Ҧ����~�ƥ��~�ӨӦܩ� Exception
      e.printStackTrace();
      System.out.println("�o�Ϳ��~�A����!!");
    } finally {
      // try catch����U
      // �i�H�ϥ�finally����@���̫᪺�B�z
      // �Y�ϥΨ�L�A�i�H���@�Ǧ������ʧ@
      System.out.println("����~");
    }
  }
}

class EmptyClass{
  public String name;
}


/**
 * <pre>
 * Thread �����
 * �Ʊ��A�`�O������
 * ���������ɭԡA�N�ݭn�H����Share�u�@
 * �U�ۤ��u�ۤv�n�������d��
 * �j�a�U�ۧ����ۤv���ƥ�
 * �o���u�ɶ����A�̧֧������ؼ�
 * 
 * �򥻤�k��
 * run()        ����
 * getName()    �ثe��������W��
 * getState()   �ثe����������A
 * join()       ���ݤW�@�Ӱ����򧹦���A�A����
 * interrupt()  �C��Thread���w��@���_��k
 *              �Y���槹��A�Фų������L
 *              �O����|�z�����A��
 * 
 * </pre>
 */
class MyThread {

  // ��²�檺�򥻰����
  public void doNormal(){
    GoGo t1 = new GoGo();
    GoGo t2 = new GoGo();
    t1.setName("T1");
    t2.setName("T2");
    t1.start();
    t2.start();
    // ���o�ثe������ƶq
    System.out.println(Thread.activeCount()); 
  }
  
  // Thread join �d��
  public void doTheadJoin(){

    System.out.println("Thread A ����");

    Thread threadB = new Thread(new Runnable() {
      public void run() {
        try {
          System.out.println("Thread B �}�l..");
          for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("Thread B ����..");
          }
          System.out.println("Thread B �Y�N����..");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    threadB.start();

    try {
      // Thread B �[�J Thread A
      threadB.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Thread A ����");

  }
  
  // �hThread ²��d��
  public void doList(){
    List<GoGo> gogoLand = new ArrayList<GoGo>();
    // �ڷs�W10��Thread��List�̭�
    for(int index = 0 ; index < 10 ; index++){
      GoGo go = new GoGo();
      go.setName("Thread : " + Integer.toString(index+1));
      gogoLand.add(go);
    }
    // �ڱҰ�10��Thread
    for(GoGo go : gogoLand){
      // start���ɭԡA�|�h�I�srun��k�A����@�ǳB�z
      go.start();
      System.out.println("start => " + go.getName()  + " , status : " + go.getState());
    }
    // ���o�ثe������ƶq
    System.out.println("runing number = " + Thread.activeCount()); 
    System.out.println("----------------------------");
    for(GoGo go : gogoLand){
      go.interrupt();
      System.out.println(go.getName()  + " , status : " + go.getState());
    }
    System.out.println("runing end number = " + Thread.activeCount()); 
  }
  
}

// ��Thread 
class GoGo extends Thread {
  // ���w��@run��k�A�N��n���檺�ʧ@
  public void run() {
    // ���@�ǨƱ���
    try {
      int sleepTime = new Random().nextInt(2000)+1;
      System.out.println(Thread.currentThread().getName() + " , ��:" + sleepTime + "ms");
      // ���ئ۰ʵ��Ծ���A���O�d�@��
      Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  } 
  @Override
  // �i���}�n�ߺD�A������n�����L
  public void interrupt() {
    super.interrupt();
  }
}