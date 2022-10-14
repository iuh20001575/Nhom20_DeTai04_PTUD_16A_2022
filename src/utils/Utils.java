package utils;

import java.awt.Color;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static final Color primaryColor = new Color(140, 177, 180);
	public static final Color secondaryColor = new Color(203, 239, 255);
	public static final Color phongCho = getRGBA(4, 191, 173, 0.85f);
	public static final Color phongTrong = new Color(125, 214, 112, 191);
	public static final Color phongDangSuDung = getRGBA(242, 68, 68, 0.85f);
	public static final Color lineTextField = new Color(149, 166, 248);
	public static final Color labelTextField = new Color(150, 150, 150);
	public static final int width = 1086;
	public static final int height = 573;

	/**
	 * Get màu RGBA
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 * @return
	 */
	public static Color getRGBA(int r, int g, int b, float a) {
		return new Color(r, g, b, Math.round(a * 255));
	}

	/**
	 * Get màu RGBA
	 * 
	 * @param color
	 * @param alpha
	 * @return
	 */
	public static Color getOpacity(Color color, float alpha) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		int a = color.getAlpha();

		return new Color(r, g, b, Math.round(a * alpha));
	}

	/**
	 * Định dạng Date
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(LocalDate date) {
		return String.format("%s/%s/%d", date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth(),
				date.getMonthValue() < 10 ? "0" + date.getMonthValue() : date.getMonthValue(), date.getYear());
	}

	/**
	 * Chuyển String sang LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate getLocalDate(String date) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date.contains("/") ? "dd/MM/yyyy" : "dd-MM-yyyy");
		String dates[] = date.split(date.contains("/") ? "/" : "-");
		for (int i = 0; i < 2; i++)
			if (dates[i].length() == 1)
				dates[i] = '0' + dates[i];
		if (dates[2].length() == 2)
			dates[2] = "19" + dates[2];
		return LocalDate
				.parse(String.format(date.contains("/") ? "%s/%s/%s" : "%s-%s-%s", dates[0], dates[1], dates[2]), dtf);
	}

	/**
	 * Chuyển Date sang LocalDate
	 * 
	 * @param dateToConvert
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static LocalDate convertDateToLocalDate(Date dateToConvert) {
		LocalDate localDate = LocalDate.of(dateToConvert.getYear() + 1900, dateToConvert.getMonth() + 1,
				dateToConvert.getDate());
		return localDate;
	}

	/**
	 * Chuyển LocalDate sang Date của SQL
	 * 
	 * @param localDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static java.sql.Date convertLocalDateToDate(LocalDate localDate) {
		return new java.sql.Date(
				new Date(localDate.getYear() - 1900, localDate.getMonthValue() - 1, localDate.getDayOfMonth())
						.getTime());
	}

	/**
	 * Format kiểu tiền tệ
	 * 
	 * @param soTien
	 * @return
	 */
	public static String formatTienTe(double soTien) {
		Locale locale = new Locale("vi", "vn");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(soTien);
	}

	/**
	 * Chuyển tiền tệ kiểu chuỗi sang double
	 * 
	 * @param tien
	 * @return
	 */
	public static double convertStringToTienTe(String tien) {
		return Double.parseDouble(tien.substring(0, tien.length() - 2));
	}

	/**
	 * Kiểm tra một chuỗi có thể là số hay không?
	 * 
	 * @param numString chuỗi cần kiểm tra
	 * @return true nếu là số
	 */
	public static boolean isInteger(String numString) {
		try {
			Integer.parseInt(numString);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * Kiểm tra số điện thoại có hợp lệ không? Số điện thoại bắt đầu bằng số 0 và 9
	 * chữ số
	 * 
	 * @param soDienThoai số điện thoại cần kiểm tra
	 * @return true nếu số điện thoại hợp lệ
	 */
	public static boolean isSoDienThoai(String soDienThoai) {
		Pattern pattern = Pattern.compile("0[0-9]{9}");
		Matcher matcher = pattern.matcher(soDienThoai);
		return matcher.matches();
	}
}
