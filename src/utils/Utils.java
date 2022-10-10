package utils;

import java.awt.Color;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

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

	public static Color getRGBA(int r, int g, int b, float a) {
		return new Color(r, g, b, Math.round(a * 255));
	}

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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dates[] = date.split("/");
		for (int i = 0; i < 2; i++)
			if (dates[i].length() == 1)
				dates[i] = '0' + dates[i];
		if (dates[2].length() == 2)
			dates[2] = "19" + dates[2];
		return LocalDate.parse(String.format("%s/%s/%s", dates[0], dates[1], dates[2]), dtf);
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

	public static String formatTienTe(double soTien) {
		Locale locale = new Locale("vi", "vn");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(soTien);
	}

	public static double convertStringToTienTe(String tien) {
		return Double.parseDouble(tien.substring(0, tien.length() - 2));
	}
}
