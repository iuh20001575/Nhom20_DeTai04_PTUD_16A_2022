package entity;

import java.util.Objects;

public class Phong implements Comparable<Phong> {
	public static enum TrangThai {
		DaDat, DangThue, PhongTam, Trong
	}

	public static TrangThai convertStringToTrangThai(String trangThai) {
		if (trangThai.equals("Đã đặt"))
			return TrangThai.DaDat;
		if (trangThai.equals("Đang thuê"))
			return TrangThai.DangThue;
		if (trangThai.equals("Phòng tạm"))
			return TrangThai.PhongTam;
		return TrangThai.Trong;
	}

	public static String convertTrangThaiToString(TrangThai trangThai) {
		if (trangThai.equals(TrangThai.DaDat))
			return "Đã đặt";
		if (trangThai.equals(TrangThai.DangThue))
			return "Đang thuê";
		if (trangThai.equals(TrangThai.PhongTam))
			return "Phòng tạm";
		return "Trống";
	}

	private LoaiPhong loaiPhong;

	private String maPhong;

	private int soLuongKhach;

	private TrangThai trangThai;

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

	@Override
	public int compareTo(Phong o) {
		// TODO Auto-generated method stub
		return this.getMaPhong().compareTo(o.getMaPhong());
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

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public int getSoLuongKhach() {
		return soLuongKhach;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public void setSoLuongKhach(int soLuongKhach) {
		this.soLuongKhach = soLuongKhach;
	}

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", soLuongKhach=" + soLuongKhach
				+ ", trangThai=" + trangThai + "]";
	}

}
