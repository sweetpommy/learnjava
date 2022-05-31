package MidPracticeI;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * �m���D1
 * </pre>
 */
public class MidPracticeI {
	public static void main(String[] args) {
    new mi_1().exec(10);
    System.out.println("-----------");
    new mi_2().exec(5);
    System.out.println("-----------");
		new mi_3().exec();
	}
}

/**
 * <pre>
 �Шϥ�for�j��A�m�ߦL�X�H�U�Ϯ�
   
   *       *
    *     *
     *   *
      * *
       *
      * *
     *   *
    *     *
   *       *
 * </pre>
 */
class mi_1 {
	public void exec(int size) {
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (i == j || j + i == size) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}
}

/**
 * <pre>
 * 1. �������ͤ@�հ}�C�A�̭��\�񥿾�� 0~5
 * 2. ���ͤ@�ӥؼе��סA�üƵ��׬O 1~9 ����
 * 3. �Q�βĤ@�հ}�C�A�H����Ӥ����ۥ[�A�|����ؼе��סA�æL�X��Ӱ}�C��m
 * </pre>
 */
class mi_2 {
	public void exec(int size) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i <= size; i++) {
			numbers.add(i);
		}
		System.out.println("�}�C: " + numbers);
		int count = 0;
		int num1 = Ran_Num(size * 2 - 1); // ���ͤ@��1~9 �����üƥؼе���
		System.out.println("�üƵ���: " + num1 );
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (num1 == (numbers.get(i) + numbers.get(j))) {
					count++;
					System.out.println("�զX " + count + " =>�Ĥ@�Ӿ�ƪ�Index="+i  + ",�ĤG�Ӿ�ƪ�Index=" +j+",�Ĥ@�Ӿ�ƪ���="+ numbers.get(i)+",�ĤG�Ӿ�ƪ���="+ numbers.get(j));
				}
			}
		}
	}

	Random random = new Random();

	public int Ran_Num(int a) {
		int random_int = random.nextInt(a) + 1;
		return random_int;
	}
}

/**
 * <pre>
 * �ǥ͸��
 * �Ǹ�  �m�W
 * S1    �p��
 * S2    �p��
 * S3    �p��
 * 
 * ���Z���
 * �Ǹ�  �ƾ�  �^��  ���
 * S1     80   100    87
 * S2     99    94    78
 * S3     55    79    77
 * 
 * �Хηs�W�ǥͻP���Z���O(Class)
 * �]�w�C��ǥ͸�ƥH�γ]�w�U�즨�Z��ƫ�
 * 
 * 1.�����ڥξǸ��d�߾ǥͦU�쪺���Z
 * 2.�C��ǥͪ��������Z
 * 3.�U�쪺�������Z
 * 4.�C��̰�����
 * 5.�C��̧C����
 * </pre>
 */
class mi_3 { 
	Map<Integer, Students> students_list = new TreeMap<Integer, Students>();
	Map<Integer, Score> score_list = new TreeMap<Integer, Score>();
	int stdCount = 0;

	public void exec() {
		InitData();
		System.out.println("1.�Ǹ��d�߾ǥͦU�쪺���Z");
		Query_Score();
		System.out.println();
		System.out.println("2.�C��ǥͪ��������Z");
		Query_StudentAvgScore();
		System.out.println();
		System.out.println("3.�U�쪺�������Z");
		Subject_AvgScore();
		System.out.println();
		System.out.println("4.�C��̰�����");
		GetHighestScore();
		System.out.println();
		System.out.println("5.�C��̧C����");
		GetLowestScore();
		System.out.println();
	}

	public void InitData() {

		students_list.put(1, new Students("S1", "�p��"));
		students_list.put(2, new Students("S2", "�p��"));
		students_list.put(3, new Students("S3", "�p��"));

		score_list.put(1, new Score("S1", 80, 100, 87));
		score_list.put(2, new Score("S2", 99, 94, 78));
		score_list.put(3, new Score("S3", 55, 79, 77));
	}

	public void Query_Score() {

		for (int i : students_list.keySet()) {
			for (int j : score_list.keySet()) {
				if (students_list.get(i).getStudent_no().equals(score_list.get(j).getStudent_no())) {
					System.out.println("�Ǹ�:" + students_list.get(i).getStudent_no() + ", " + "�m�W:"
							+ students_list.get(i).getStudent_name() + ", " + "�ƾ�:" + score_list.get(j).getMath()
							+ ", " + "�^��:" + score_list.get(j).getEnglish() + ", " + "���:"
							+ score_list.get(j).getChinese());
				}
			}
		}
	}

	public void Query_StudentAvgScore() {
		DecimalFormat format1 = new DecimalFormat("###,###.00");//�w�]���榡
		for (int i : students_list.keySet()) {
			for (int j : score_list.keySet()) {
				if (students_list.get(i).getStudent_no().equals(score_list.get(j).getStudent_no())) {
					System.out.println("�Ǹ�:" + students_list.get(i).getStudent_no() + ", " + "�m�W:"
							+ students_list.get(i).getStudent_name() + ", " + "��������:"
							+format1.format( Double.valueOf(score_list.get(j).getMath() + score_list.get(j).getEnglish()
									+ score_list.get(j).getChinese()) / 3  ));
				}
			}
		}
	}

	public void Subject_AvgScore() {
		double math_sum = 0.0, english_sum = 0.0,chinese_sum = 0.0;
		DecimalFormat format1 = new DecimalFormat("###,###.00");//�w�]���榡
		for (int key : score_list.keySet()) {
			math_sum += score_list.get(key).getMath();
			english_sum += score_list.get(key).getEnglish();
			chinese_sum += score_list.get(key).getChinese();
		}

		System.out.println("�ƾǥ�������:" + format1.format(math_sum / 3));
		System.out.println("�^�奭������:" + format1.format(english_sum / 3));
		System.out.println("��奭������:" + format1.format(chinese_sum / 3));
	}

	public void GetHighestScore() {
		int max_math = 0, max_english = 0, max_chinese = 0;

		for (int key : score_list.keySet()) {
			if (max_math < score_list.get(key).getMath()) {
				max_math = score_list.get(key).getMath();
			}
			if (max_english < score_list.get(key).getEnglish()) {
				max_english = score_list.get(key).getEnglish();
			}
			if (max_chinese < score_list.get(key).getChinese()) {
				max_chinese = score_list.get(key).getChinese();
			}
		}

		System.out.println("�ƾǳ̰�����:" + max_math);
		System.out.println("�^��̰�����:" + max_english);
		System.out.println("���̰�����:" + max_chinese);
	}

	public void GetLowestScore() {
		int min_math = 0, min_english = 0, min_chinese = 0;

		min_math = score_list.get(1).getMath();
		min_english = score_list.get(1).getEnglish();
		min_chinese = score_list.get(1).getChinese();

		for (int key : score_list.keySet()) {
			if (min_math > score_list.get(key).getMath()) {
				min_math = score_list.get(key).getMath();
			}
			if (min_english > score_list.get(key).getEnglish()) {
				min_english = score_list.get(key).getEnglish();
			}
			if (min_chinese > score_list.get(key).getChinese()) {
				min_chinese = score_list.get(key).getChinese();
			}
		}

		System.out.println("�ƾǳ̧C����:" + min_math);
		System.out.println("�^��̧C����:" + min_english);
		System.out.println("���̧C����:" + min_chinese);
	}

}