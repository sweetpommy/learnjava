package Class7;
/**
 * <pre> 
 * �A�ת���ɦV�ǲ�
 * 
 * ����ɦV����شN�O
 * ��X�X��ƪ����P������
 * �ç�����ưȡA���ɨ������W��@��
 * �j�a�����ӵ۰򥻳W�d��
 * �|�j�T��֤����n�����е{���X
 * 
 * Extends(�~��)
 * �~�ӴN�O�A�������Ҧ��@���A���ަn�a
 * 
 * Encapsulation(�ʸ�)
 * �N���\�U�I��@�ˡA�W�ӴN�O�@�D�Ʋz
 * �@��ȤH�N�|ı�o�i�H�Y�N�n�F
 * ���|�h�L���F�ѫ��ԲӮƲz��
 * �Ʋz���O�A�N�浹���a�B�z!
 * �t�d�Y�N��F
 * 
 * Polymorphism(�h��)
 * �N��j���N�O
 * �@�ɳo��j�A�����ܦh
 * �j�a�Ʋz�覡�N�|�t��
 * �N�|���h�˩ʥX�{�F
 * �N�|���U�a������
 * �Y�Y�Y!!
 * 
 * </pre>
 */
public class Class7 {
  public static void main(String[] args) {
    // �׹��l
//    Modifier modifier = new Modifier();
//    modifier.iAmPublic = "iAmPublic";
//    modifier.iAmProtected = "iAmProtected";
//    modifier.iAmNotModifier = "iAmNotModifier";
//    System.out.println("iAmPublic = " + modifier.iAmPublic);
//    System.out.println("iAmProtected = " + modifier.iAmProtected);
//    System.out.println("iAmNotModifier = " + modifier.iAmNotModifier);
    
    // �~��
//    ONE_PIECE ONE_PIECE = new �|��();
//    System.out.println("ONE_PIECE.�j���_ = " + ONE_PIECE.�j���_);
//    �|�� �|�� = null;
//    // Java �� instanceof �B��l (instanceof operator) 
//    // ���լY�@����O�_���t�@���O (class) ���l���O (subclass) 
//    if(ONE_PIECE instanceof �|��){
//      �|�� = (�|��) ONE_PIECE;
//    }
//    System.out.println("�|��.�j���_ = " + �|��.�j���_);
//    �|��.serach();
    
    // �~�ӡA��H�A�h���A�ʸ�
//    Food cake = new Cake();
//    cake.setName("�����J�|");
//    cake.setRaw("�ѯ��A���o�A�A���A�����A�ͩվ��A�N�c");
//    cake.cooking();
//    Food tea = new Tea();
//    tea.setName("����");
//    tea.setRaw("�x���Q�K��");
//    tea.cooking();
    
    // Overloading �h��
//    new Overloading().exec();
    role test =new stupid("10000","20" );
    test.attack();
    test.special();
    test.getLevel();
    test.getLife();
    System.out.println(test.getLevel());
    System.out.println(test.getLife());
    
  }
}

/**
 * <pre>
 *  abstract�׹��l�A��b���O���e�A�N������H���O
 *  �ڭ̵L�k�����ϥΩ�H�������ͪ���ӨϥΡA
 *  �����n���H�~�ӥL����� 
 * </pre>
 */
abstract class ONE_PIECE {
  // �غc�l�u������
  public ONE_PIECE(){
    System.out.println("�G�ƶ}�l�F!!");
  }
  public String �j���_ = "???";
  
}

class �|�� extends ONE_PIECE{
  //�غc�l�u������
  public �|��(){
    System.out.println("��!!!!!!!");
  }
  public void serach(){
    System.out.println("���Фj�j�A�٨S�e��...");
  }
}

/** 
 * <pre> 
 * Interface(����)
 * �L���s�b�N�O�A�ڥu�i������
 * �ӶǤJ�����ƻP���Ӧ^�Ǫ��Ʋz
 * �ܩ�Ʋz�覡�A���j�a��@��
 * �ۦ�o��~
 * </pre>
 */
interface FoodAction{
  public void setRaw(String raw);
  public String getRaw();
  public void cooking();
}

// ��H���O �J�|�A�~�ӤF�����A��@�F�Ʋz�L�{
// ������ݭn�⹳���O����]�O
// �����O�@�Ӥj�����O�A�i�H�γo�Ӥ覡
// �w�q�X�j�a�ۦP�������A�ѤU���Q�n�~�Ӫ����O
// �ۤv���X�Q�n���F��Y�i
abstract class Food implements FoodAction{
  protected String raw;
  private String name;
  // �ʸ�name(�W�r)�ت��O�A���i�H���~���H�N���W�r
  // �H�K�Q�������~�Хܤ��W
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
}
class Cake extends Food {

  @Override
  public void setRaw(String raw) {
    this.raw = raw;
  }

  @Override
  public String getRaw() {
    return raw;
  }

  @Override
  public void cooking() {
    System.out.println("�{�b�n�����Ʋz�O : " + getName());
    System.out.println("���� : " + getRaw());
    System.out.println("Step 1 : �J��+�}���ܿ}��");
    System.out.println("Step 2 : �[�J�F�Ԫo �դ�");
    System.out.println("Step 3 : �[�J�G��&������� ");
    System.out.println("Step 4 : �����V�X�L�z�[�J");
    System.out.println("etc...");
  }

}

// ���O ���A�~�ӤF�����A��@�F�Ʋz�L�{
class Tea extends Food {

  @Override
  public void setRaw(String raw) {
    this.raw = raw;
  }

  @Override
  public String getRaw() {
    return raw;
  }

  @Override
  public void cooking() {
    System.out.println("�{�b�n�����Ʋz�O : " + getName());
    System.out.println("���� : " + getRaw());
    System.out.println("Step 1 : ����");
    System.out.println("Step 2 : �����S�L������A�˱�");
    System.out.println("Step 3 : ��J����");
    System.out.println("Step 4 : �[�J�����A²��L�R��˱�");
    System.out.println("Step 5 : �A���[�J�����A�̷ӿ@�׵���1~3������A�Y�i");
  }
  
}

/**
 * <pre>
 * Overloading 
 * �h�����γ~�O
 * ��k�W�٫O���@�P
 * ���O�������ѼƤ��P�Ӥw
 * �̱`�ϥΨ쪺�N�O
 * System.out ����
 * </pre>
 */
class Overloading{
  public void exec(){
    sleep(1000);
    sleep(2000.0f);
  }
  
  private void sleep(int time){
    System.out.println("�Φh�[ = " + time + "s");
  }
  
  private void sleep(float time){
    System.out.println("�Φh�[ = " + time  + "s");
  }
}


// �m��
// �C�������\�h"����"
// ���⦳��q�A����
// ���⦳�T��¾�~
// �Ԥh�B�]�k�v�B�}�b��
// �C�ӤH���|"�򥻧���" �H��"�S��ޯ�"
// �]�p�X�������O�P����
// �åB�����ڥ��~�ӡA��H�A�h���A�ʸˤ覡�]�p


abstract class role implements Action{  //�ⶵ���O
 private String life;
 private String level;
 
public String getLife() {
  return life;
}
public void setLife(String life) {
  this.life = life;
}
public String getLevel() {
  return level;
}
public void setLevel(String level) {
  this.level = level;
}
  
}

interface Action{  //���ۦP�\�श��
  public void attack();
  public void special();
 
}



class warrior extends role {

  public warrior(String life,String level){
    setLife("999");
    setLevel("100");
  }
  @Override
  public void attack() {
    
    System.out.println("�޵P����");
  }

  @Override
  public void special() {
    System.out.println("�޵P�z��");
    
  }
  
}

class magic extends role {

  public magic(String life,String level){
    setLife("300");
    setLevel("100");
  }
  @Override
  public void attack() {
    
    System.out.println("�]�k����");
  }

  @Override
  public void special() {
    System.out.println("�Q�U��S����");
    
  }
  
}


class knife extends role {
	  public knife(String life,String level){
		    setLife(life); //��l�ƭ�
		    setLevel(level);
		  }
		  @Override
		  public void attack() {
		    
		    System.out.println("A++++");
		  }

		  @Override
		  public void special() {
		    System.out.println("level----");
		    
		  }
}

class stupid extends role {
	  public stupid(String life,String level){
		    setLife(life); //��l�ƭ�
		    setLevel(level);
		  }
		  @Override
		  public void attack() {
		    
		    System.out.println("���q����");
		  }

		  @Override
		  public void special() {
		    System.out.println("�H�K��");
		    
		  }
}

