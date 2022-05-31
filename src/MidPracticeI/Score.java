package MidPracticeI;

public class Score {
	private String student_no;
	private Integer math ;
	private Integer english ;
	private Integer chinese ;
	public String getStudent_no() {
		return student_no;
	}
	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}
 
	public Integer getMath() {
		return math;
	}
	public void setMath(Integer math) {
		this.math = math;
	}
	public Integer getEnglish() {
		return english;
	}
	public void setEnglish(Integer english) {
		this.english = english;
	}
	public Integer getChinese() {
		return chinese;
	}
	public void setChinese(Integer chinese) {
		this.chinese = chinese;
	}
	public Score(String student_no, Integer math, Integer english, Integer chinese) {
		super();
		this.student_no = student_no;
		this.math = math;
		this.english = english;
		this.chinese = chinese;
	}
}
