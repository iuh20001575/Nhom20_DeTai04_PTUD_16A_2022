package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private String cccd;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private boolean gioiTinh;
	private Tinh tinh;
	private Quan quan;
	private Phuong phuong;
	private String diaChiCuThe;
	private ChucVu chucVu;
	private double luong;
	private TaiKhoan taiKhoan;
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
			TaiKhoan taiKhoan, TrangThai trangThai) {
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
		this.taiKhoan = taiKhoan;
		this.trangThai = trangThai;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Tinh getTinh() {
		return tinh;
	}

	public void setTinh(Tinh tinh) {
		this.tinh = tinh;
	}

	public Quan getQuan() {
		return quan;
	}

	public void setQuan(Quan quan) {
		this.quan = quan;
	}

	public Phuong getPhuong() {
		return phuong;
	}

	public void setPhuong(Phuong phuong) {
		this.phuong = phuong;
	}

	public String getDiaChiCuThe() {
		return diaChiCuThe;
	}

	public void setDiaChiCuThe(String diaChiCuThe) {
		this.diaChiCuThe = diaChiCuThe;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", cccd=" + cccd + ", soDienThoai="
				+ soDienThoai + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", tinh=" + tinh + ", quan="
				+ quan + ", phuong=" + phuong + ", diaChiCuThe=" + diaChiCuThe + ", chucVu=" + chucVu + ", luong="
				+ luong + ", taiKhoan=" + taiKhoan + ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
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

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	public static enum TrangThai {
		DangLam, NghiLam
	}

	public static enum ChucVu {
		NhanVien, QuanLy
	}

	public static String convertTrangThaiToString(TrangThai trangThai) {
		if (trangThai.equals(TrangThai.DangLam))
			return "Đang làm";
		return "Nghỉ làm";
	}

	public static TrangThai convertStringToTrangThai(String trangThai) {
		if (trangThai.equals("Đang làm"))
			return TrangThai.DangLam;
		return TrangThai.NghiLam;
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
}
