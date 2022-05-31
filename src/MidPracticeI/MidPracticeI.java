package MidPracticeI;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 練習題1
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
 請使用for迴圈，練習印出以下圖案
   
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
 * 1. 首先產生一組陣列，裡面擺放正整數 0~5
 * 2. 產生一個目標答案，亂數答案是 1~9 之間
 * 3. 利用第一組陣列，隨機兩個元素相加，會等於目標答案，並印出兩個陣列位置
 * </pre>
 */
class mi_2 {
	public void exec(int size) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i <= size; i++) {
			numbers.add(i);
		}
		System.out.println("陣列: " + numbers);
		int count = 0;
		int num1 = Ran_Num(size * 2 - 1); // 產生一個1~9 之間亂數目標答案
		System.out.println("亂數答案: " + num1 );
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (num1 == (numbers.get(i) + numbers.get(j))) {
					count++;
					System.out.println("組合 " + count + " =>第一個整數的Index="+i  + ",第二個整數的Index=" +j+",第一個整數的值="+ numbers.get(i)+",第二個整數的值="+ numbers.get(j));
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
 * 學生資料
 * 學號  姓名
 * S1    小天
 * S2    小忍
 * S3    小玉
 * 
 * 成績資料
 * 學號  數學  英文  國文
 * S1     80   100    87
 * S2     99    94    78
 * S3     55    79    77
 * 
 * 請用新增學生與成績類別(Class)
 * 設定每位學生資料以及設定各科成績資料後
 * 
 * 1.請幫我用學號查詢學生各科的成績
 * 2.每位學生的平均成績
 * 3.各科的平均成績
 * 4.每科最高分數
 * 5.每科最低分數
 * </pre>
 */
class mi_3 { 
	Map<Integer, Students> students_list = new TreeMap<Integer, Students>();
	Map<Integer, Score> score_list = new TreeMap<Integer, Score>();
	int stdCount = 0;

	public void exec() {
		InitData();
		System.out.println("1.學號查詢學生各科的成績");
		Query_Score();
		System.out.println();
		System.out.println("2.每位學生的平均成績");
		Query_StudentAvgScore();
		System.out.println();
		System.out.println("3.各科的平均成績");
		Subject_AvgScore();
		System.out.println();
		System.out.println("4.每科最高分數");
		GetHighestScore();
		System.out.println();
		System.out.println("5.每科最低分數");
		GetLowestScore();
		System.out.println();
	}

	public void InitData() {

		students_list.put(1, new Students("S1", "小天"));
		students_list.put(2, new Students("S2", "小忍"));
		students_list.put(3, new Students("S3", "小玉"));

		score_list.put(1, new Score("S1", 80, 100, 87));
		score_list.put(2, new Score("S2", 99, 94, 78));
		score_list.put(3, new Score("S3", 55, 79, 77));
	}

	public void Query_Score() {

		for (int i : students_list.keySet()) {
			for (int j : score_list.keySet()) {
				if (students_list.get(i).getStudent_no().equals(score_list.get(j).getStudent_no())) {
					System.out.println("學號:" + students_list.get(i).getStudent_no() + ", " + "姓名:"
							+ students_list.get(i).getStudent_name() + ", " + "數學:" + score_list.get(j).getMath()
							+ ", " + "英文:" + score_list.get(j).getEnglish() + ", " + "國文:"
							+ score_list.get(j).getChinese());
				}
			}
		}
	}

	public void Query_StudentAvgScore() {
		DecimalFormat format1 = new DecimalFormat("###,###.00");//預設的格式
		for (int i : students_list.keySet()) {
			for (int j : score_list.keySet()) {
				if (students_list.get(i).getStudent_no().equals(score_list.get(j).getStudent_no())) {
					System.out.println("學號:" + students_list.get(i).getStudent_no() + ", " + "姓名:"
							+ students_list.get(i).getStudent_name() + ", " + "平均分數:"
							+format1.format( Double.valueOf(score_list.get(j).getMath() + score_list.get(j).getEnglish()
									+ score_list.get(j).getChinese()) / 3  ));
				}
			}
		}
	}

	public void Subject_AvgScore() {
		double math_sum = 0.0, english_sum = 0.0,chinese_sum = 0.0;
		DecimalFormat format1 = new DecimalFormat("###,###.00");//預設的格式
		for (int key : score_list.keySet()) {
			math_sum += score_list.get(key).getMath();
			english_sum += score_list.get(key).getEnglish();
			chinese_sum += score_list.get(key).getChinese();
		}

		System.out.println("數學平均分數:" + format1.format(math_sum / 3));
		System.out.println("英文平均分數:" + format1.format(english_sum / 3));
		System.out.println("國文平均分數:" + format1.format(chinese_sum / 3));
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

		System.out.println("數學最高分數:" + max_math);
		System.out.println("英文最高分數:" + max_english);
		System.out.println("國文最高分數:" + max_chinese);
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

		System.out.println("數學最低分數:" + min_math);
		System.out.println("英文最低分數:" + min_english);
		System.out.println("國文最低分數:" + min_chinese);
	}

}