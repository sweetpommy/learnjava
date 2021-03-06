package Class5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HommeWork5 {
	public static void main(String args[]) throws ParseException {

		System.out.println("=====HomeWork5_Q1_1======");
		Q5_1 q5_1 = new Q5_1();
		q5_1.print_string_replace();
		q5_1.print_stringbuffer_replace();
		q5_1.print_stringbuilder_replace();
		System.out.println("=====HomeWork5_Q1_2======");
		q5_1.print_string_add();
		q5_1.print_stringbuffer_append();
		q5_1.print_stringbuilder_append();
		System.out.println("=====HomeWork5_Q2======");
		Q5_2 q5_2 = new Q5_2();
		q5_2.print_caldays();
		System.out.println("=====HomeWork5_Q3======");
		Q5_3 q5_3 = new Q5_3();
		q5_3.year_Calendar(2022);
		System.out.println("=====HomeWork5_Q4======");
		Q5_4 q5_4 = new Q5_4();
		q5_4.Compute();
		System.out.println("=====HomeWork5 END======");
	}
}

class Q5_1 {

	public void print_string_replace() {
		String arg_sort = "I have a pen.";
		System.out.println("Original String:" + arg_sort);
		if (arg_sort.indexOf("pen") != -1) {
			System.out.println("After Replace String:" + arg_sort.replace("pen", "apple"));
			System.out.print("\n");
		}
	}

	public void print_stringbuffer_replace() {
		StringBuffer buff = new StringBuffer("I have a pen.");
		System.out.println("Original Stringbuffer:" + buff);
		if (buff.indexOf("pen") != -1) {
			buff.replace(buff.indexOf("pen"), buff.indexOf("pen") + 3, "apple");
			System.out.println("After Replace Stringbuffer:" + buff);
		}
		System.out.print("\n");
	}

	public void print_stringbuilder_replace() {
		StringBuilder str = new StringBuilder("I have a pen.");

		System.out.println("Original Stringbuilder:" + str.toString());
		StringBuilder strReturn = str.replace(str.indexOf("pen"), str.indexOf("pen") + 3, "apple");
		System.out.println("After Replace Stringbuilder:" + strReturn.toString());
		System.out.print("\n");
	}

	public void print_string_add() {
		String arg_s1 = "I have a pen.";
		String arg_s2 = "I have a apple.";
		System.out.println("Original String1:" + arg_s1);
		System.out.println("Original String2:" + arg_s2);
		System.out.println("Concat String" + arg_s1.replace(".", ",") + String.valueOf(arg_s2.toCharArray()));
		System.out.print("\n");

	}

	public void print_stringbuffer_append() {
		StringBuffer buff1 = new StringBuffer("I have a pen. ");
		System.out.println("Original Stringbuff1:" + buff1);
		StringBuffer buff2 = new StringBuffer("I have a apple.");
		System.out.println("Original Stringbuff2:" + buff2);
		if (buff1.indexOf(".") != -1) {
			buff1.replace(buff1.indexOf("."), buff1.indexOf(".") + 1, ",");
		}
		buff1.append(buff2);
		System.out.println("Append Stringbuff:" + buff1);
		System.out.print("\n");
	}

	public void print_stringbuilder_append() {
		StringBuilder str1 = new StringBuilder("I have a pen. ");
		System.out.println("Original Stringbuilder1:" + str1);

		StringBuilder str2 = new StringBuilder("I have a apple.");
		System.out.println("Original Stringbuilder2:" + str2);
		if (str1.indexOf(".") != -1) {
			str1.replace(str1.indexOf("."), str1.indexOf(".") + 1, ",");
		}
		str1.append(str2);
		// print the StringBuilder after appending
		System.out.println("Append Stringbuilder: " + str1);
		System.out.print("\n");
	}
}

class Q5_2 {

	public void print_caldays() {

		Calendar now = Calendar.getInstance(); // ????????????????????????
//		int month = now.get(Calendar.MONTH) + 1; //????????? - ????????????0?????????+1????????????
		System.out.println("????????????: " + format(now));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date1 = dateFormat.parse(ls_date); 

		Calendar anotherDate = Calendar.getInstance();
//        anotherDate.setTime(date1);
		anotherDate.set(2022, 4, 6); // ??????2022???5???6???,????????????0?????????+1??????????????????????????????
		System.out.println("???" + dateFormat.format(anotherDate.getTime()) + "?????? "
				+ differentDays(anotherDate.getTime(), now.getTime()) + "???");

		System.out.print("\n");
	}

	private String format(Calendar calendar) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
		Date date = calendar.getTime();
		return df.format(date);
	}

	public static int differentDays(Date anotherDate, Date currentDate) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(anotherDate);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(currentDate);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) 
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) 
				{
					timeDistance += 366;
				} else // 
				{
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else 
		{
//            System.out.println("day1: " + cal1.getTime()+ day1);
//            System.out.println("day2: " +cal2.getTime()+ day2 );            
			return day2 - day1;
		}
	}
}

class Q5_3 {
	public void year_Calendar(int year) throws ParseException {
		for (int i = 1; i <= 12; i++) {
			System.out.println(year + "???" + i + "???");
			this.printWeekTitle();
			this.printCalendar(2022, i);
		}

	}

	public void printCalendar(int year, int month) throws ParseException {
		String monthStr; // ?????????????????????????????????yyyyMMdd?????????
		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = month + ""; // ???????????????????????????????????????
		}
		String yearMonthStr = year + monthStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendarEnd = Calendar.getInstance();
		Calendar calendarStart = Calendar.getInstance();
		// ???????????????????????????????????????????????????
		int monthDays = getMonthLastDay(year, month);
		// ?????????date??????
		String dateStartStr = yearMonthStr + "01";
		// ?????????date??????
		String dateEndStr = yearMonthStr + monthDays;
		Date startDate = sdf.parse(dateStartStr);
		Date endDate = sdf.parse(dateEndStr);
		calendarStart.setTime(startDate);
		calendarEnd.setTime(endDate);
		// ??????????????????????????????
		int weeks = calendarEnd.get(Calendar.WEEK_OF_MONTH);
		// ??????????????????????????????????????????????????????????????????1?????????????????????2
		int dayOfWeek = calendarStart.get(Calendar.DAY_OF_WEEK);
		int day = 1;
		// ??????????????????????????????????????????????????????
		for (int i = 1; i <= 7; i++) {
			if (i >= dayOfWeek) {
				System.out.print(" " + day + "\t");
				day++;
			} else {
				System.out.print("\t");
			}
		}
		System.out.println();
		// ???????????????????????????????????????
		for (int week = 1; week < weeks; week++) {
			for (int i = 1; i <= 7; i++) {
				if (day > monthDays) {
					break;
				}
				if (day < 10) {
					System.out.print(" " + day + "\t");
				} else {
					System.out.print(day + "\t");
				}
				day++;
			}
			System.out.println();
		}
	}

	public int getMonthLastDay(int year, int month) {
		int monthDay;
		int[][] day = { { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			// ??????
			monthDay = day[1][month];
		} else {
			monthDay = day[0][month];
		}
		return monthDay;
	}

	public void printWeekTitle() {
		System.out.println(" ???" + "\t" + " ???" + "\t" + " ???" + "\t" + " ???" + "\t" + " ???" + "\t" + " ???" + "\t" + " ???");
	}

}

class Q5_4 {
	double ld_num1, ld_num2;
	String ls_num1, ls_num2;
	String ls_op = " ";
	private static Pattern pattern = Pattern.compile("-?[0-9]+(\\\\.[0-9]+)?");
	Scanner scanner = new Scanner(System.in);

	public void inputNum() {

		System.out.print("????????????????????????:");
		ls_num1 = scanner.next();

		while (!(isNumber(ls_num1))) {
			System.out.println("????????????,????????????");
			ls_num1 = scanner.next();
		}

		System.out.printf("???????????????: " + ls_num1);
		System.out.println();

		System.out.print("????????????????????????:");
		ls_num2 = scanner.next();
		while (!(isNumber(ls_num2))) {
			System.out.println("????????????,????????????");
			ls_num2 = scanner.next();
		}

		System.out.printf("???????????????:" + ls_num2);
		System.out.println();

		System.out.println("?????????????????????( + or - or * or / ):");
//		String ls_s1 = "+or-or*or/";
		ls_op = scanner.next();
		while ("+-*/".indexOf(ls_op) < 0) {
			System.out.println("????????????,????????????");
			ls_op = scanner.next();
		}
	}

	public void Compute() {

		String ls_stop = "X";
		System.out.println("?????????????????????????????????+-*/????????????");

		while (!(ls_stop.equals("N"))) {
			inputNum();
			switch (ls_op) {
			case "+":
				System.out.println(
						"??????: " + ls_num1 + "+" + ls_num2 + "=" + new BigDecimal(ls_num1).add(new BigDecimal(ls_num2)));
				break;
			case "-":
				System.out.println("??????: " + ls_num1 + "-" + ls_num2 + "="
						+ new BigDecimal(ls_num1).subtract(new BigDecimal(ls_num2)));
				break;
			case "*":
				System.out.println("??????: " + ls_num1 + "*" + ls_num2 + "="
						+ new BigDecimal(ls_num1).multiply(new BigDecimal(ls_num2)));
				break;
			case "/":
				System.out.println("??????: " + ls_num1 + "/" + ls_num2 + "=" + new BigDecimal(ls_num1)
						.divide(new BigDecimal(ls_num2), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				break;
			default:
				System.out.println("????????????,????????????");
				ls_op = scanner.next();
				break;

			}

			System.out.println();
			System.out.println("?????????????????????????????????????N????????????????????????X????????????");
			ls_stop = scanner.next();

			while ("N,X".indexOf(ls_stop) < 0) {
				System.out.println("????????????,????????????");
				ls_stop = scanner.next();
			}

		}

		System.out.println("END");

	}

	private static boolean isNumber(String str) {
		// ??????Matcher??????????????????
		Matcher m = pattern.matcher(str);
		// ???????????????????????? m.matches() ???????????? true ????????? false
		return m.matches();
	}
}