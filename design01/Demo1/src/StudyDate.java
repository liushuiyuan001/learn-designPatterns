import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudyDate {
	public static void main(String[] args) {
//		testDate();
		testLocalDateTime();
	}
	
	public static void testDate() {
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.toString());
		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.getDay());
		System.out.println(date.getDate());
		System.out.println(date.toGMTString());
		System.out.println(date.toLocaleString());
		
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(date));
		
		//获取当前时间
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DAY_OF_MONTH);
		int hh = c.get(Calendar.HOUR_OF_DAY);
		int mm = c.get(Calendar.MINUTE);
		int ss = c.get(Calendar.SECOND);
		int ms = c.get(Calendar.MILLISECOND);
		System.out.println(y + "-" + m + "-" + d + "-" + hh + "-" + mm + "-" + ss + "-" + ms);
		
		Calendar c1 = Calendar.getInstance();
		//清除所有
		c1.clear();
		c1.set(Calendar.YEAR, 2019);
		c1.set(Calendar.MONTH,9);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c1.getTime()));
		
		TimeZone tzDefault = TimeZone.getDefault();
		TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00");
		TimeZone tzNY = TimeZone.getTimeZone("America/New_York");
		System.out.println(tzDefault.getID());
		System.out.println(tzGMT9.getID());
		System.out.println(tzNY.getID());
		System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));
		
		Calendar c2 = Calendar.getInstance();
		c2.clear();
		c2.set(2021, Calendar.OCTOBER, 20, 8,15, 0);
		c2.add(Calendar.MONTH,5);
		c2.add(Calendar.DAY_OF_MONTH,5);
		c2.add(Calendar.HOUR_OF_DAY, -2);
		// 显示时间
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf1.format(c2.getTime()));
		
		Calendar c3 = Calendar.getInstance();
		c3.clear();
		c3.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		c3.set(2019,10,20, 8, 15, 0);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		System.out.println(df.format(c3.getTime()));
		
		
	}
	
	public static void testLocalDateTime() {
		LocalDate d = LocalDate.now();
		LocalTime t = LocalTime.now();
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(d);
		System.out.println(t);
		System.out.println(dt);
		
		// 自定义格式化
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(dtf.format(LocalDateTime.now()));
		
		// 自定义格式解析
		LocalDateTime dt2 = LocalDateTime.parse("2021-11-30 15:16:17", dtf);
		System.out.println(dt2);
		
		ZonedDateTime zdt = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm ZZZ");
		System.out.println(formatter.format(zdt));
		
		DateTimeFormatter zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
		System.out.println(zhFormatter.format(zdt));
		
		DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("E, MMM/dd/yyyy HH:mm", Locale.US);
		System.out.println(usFormatter.format(zdt));
		
		ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
		ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
		System.out.println(zbj);
		System.out.println(zny);
		
		LocalDateTime ldt = LocalDateTime.of(2021,10,17,17,16,17);
		ZonedDateTime zbj1 = ldt.atZone(ZoneId.systemDefault());
		ZonedDateTime zny1 = ldt.atZone(ZoneId.of("America/New_York"));
		System.out.println(zbj1);
		System.out.println(zny1);
		
		ZonedDateTime zbj2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
		ZonedDateTime zny2 = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
		System.out.println(zbj2);
		System.out.println(zny2);
	}
}
