package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	public static enum ChucVu {
		NhanVien, QuanLy
	}
	public static enum TrangThai {
		DangLam, NghiLam
	}
	public static String convertChucVuToString(ChucVu chucVu) {
		if (chucVu.equals(ChucVu.NhanVien))
			return "Nhân viên";
		return "Quản lý";
	}
	public static ChucVu convertStringToChucVu(String chucVu) {
		if (chucVu.equals("Nhân viên"))
			return ChucVu.NhanVien;
		return ChucVu.QuanLy;
	}
	public static TrangThai convertStringToTrangThai(String trangThai) {
		if (trangThai.equals("Đang làm"))
			return TrangThai.DangLam;
		return TrangThai.NghiLam;
	}
	public static String convertTrangThaiToString(TrangThai trangThai) {
		if (trangThai.equals(TrangThai.DangLam))
			return "Đang làm";
		return "Nghỉ làm";
	}
	private String cccd;
	private ChucVu chucVu;
	private String diaChiCuThe;
	private boolean gioiTinh;
	private String hoTen;
	private double luong;
	private String maNhanVien;

	private LocalDate ngaySinh;

	private Phuong phuong;

	private Quan quan;

	private String soDienThoai;

	private Tinh tinh;

	private TrangThai trangThai;

	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String hoTen, String cccd, String soDienThoai, LocalDate ngaySinh,
			boolean gioiTinh, Tinh tinh, Quan quan, Phuong phuong, String diaChiCuThe, ChucVu chucVu, double luong,
			TrangThai trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.tinh = tinh;
		this.quan = quan;
		this.phuong = phuong;
		this.diaChiCuThe = diaChiCuThe;
		this.chucVu = chucVu;
		this.luong = luong;
		this.trangThai = trangThai;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	public String getCccd() {
		return cccd;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public String getDiaChiCuThe() {
		return diaChiCuThe;
	}

	public String getHoTen() {
		return hoTen;
	}

	public double getLuong() {
		return luong;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public Phuong getPhuong() {
		return phuong;
	}

	public Quan getQuan() {
		return quan;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public Tinh getTinh() {
		return tinh;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public void setDiaChiCuThe(String diaChiCuThe) {
		this.diaChiCuThe = diaChiCuThe;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public void setPhuong(Phuong phuong) {
		this.phuong = phuong;
	}

	public void setQuan(Quan quan) {
		this.quan = quan;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public void setTinh(Tinh tinh) {
		this.tinh = tinh;
	}

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", cccd=" + cccd + ", soDienThoai="
				+ soDienThoai + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", tinh=" + tinh + ", quan="
				+ quan + ", phuong=" + phuong + ", diaChiCuThe=" + diaChiCuThe + ", chucVu=" + chucVu + ", luong="
				+ luong + ", trangThai=" + trangThai + "]";
	}
}
