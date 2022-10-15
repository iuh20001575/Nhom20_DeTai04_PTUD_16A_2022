package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private int soLuongKhach;
	private TrangThai trangThai;

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public int getSoLuongKhach() {
		return soLuongKhach;
	}

	public void setSoLuongKhach(int soLuongKhach) {
		this.soLuongKhach = soLuongKhach;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	public Phong() {
		super();
	}

	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public Phong(String maPhong, LoaiPhong loaiPhong, int soLuongKhach, TrangThai trangThai) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.soLuongKhach = soLuongKhach;
		this.trangThai = trangThai;
	}

	public double getGiaTien() {
		if (loaiPhong.getTenLoai().equals("Phòng thường")) {
			if (soLuongKhach == 5)
				return 50000;
			if (soLuongKhach == 10)
				return 100000;
			return 200000;
		}
		if (soLuongKhach == 5)
			return 100000;
		if (soLuongKhach == 10)
			return 200000;
		return 400000;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", soLuongKhach=" + soLuongKhach
				+ ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	public static String convertTrangThaiToString(TrangThai trangThai) {
		if (trangThai.equals(TrangThai.DaDat))
			return "Đã đặt";
		if (trangThai.equals(TrangThai.DangThue))
			return "Đang thuê";
		return "Trống";
	}

	public static TrangThai convertStringToTrangThai(String trangThai) {
		if (trangThai.equals("Đã đặt"))
			return TrangThai.DaDat;
		if (trangThai.equals("Đang thuê"))
			return TrangThai.DangThue;
		return TrangThai.Trong;
	}

	public static enum TrangThai {
		Trong, DaDat, DangThue
	}
}
