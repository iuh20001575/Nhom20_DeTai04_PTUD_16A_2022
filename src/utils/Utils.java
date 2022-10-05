package utils;

import java.awt.Color;

public class Utils {
	public static final Color primaryColor = new Color(140, 177, 180);
	public static final Color secondaryColor = new Color(203, 239, 255);
	public static final Color phongCho = getRGBA(4, 191, 173, 0.85f);
	public static final Color phongTrong = new Color(125, 214, 112, 191);
	public static final Color phongDangSuDung = getRGBA(242, 68, 68, 0.85f);
	public static final int width = 1086;
	public static final int height = 573;

	public static Color getRGBA(int r, int g, int b, float a) {
		return new Color(r, g, b, Math.round(a * 255));
	}
}
