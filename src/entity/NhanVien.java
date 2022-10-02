package entity;

import java.time.LocalDate;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private String cccd;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private GioiTinh gioiTinh;
	private String tinh;
	private String quan;
	private String phuong;
	private String diaChiCuThe;
	private ChucVu chucVu;
	private double luong;
	private TaiKhoan taiKhoan;
	private TrangThaiNhanVien trangThai;
	
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
	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getTinh() {
		return tinh;
	}
	public void setTinh(String tinh) {
		this.tinh = tinh;
	}
	public String getQuan() {
		return quan;
	}
	public void setQuan(String quan) {
		this.quan = quan;
	}
	public String getPhuong() {
		return phuong;
	}
	public void setPhuong(String phuong) {
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
	public TrangThaiNhanVien getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiNhanVien trangThai) {
		this.trangThai = trangThai;
	}
	
	
	public NhanVien() {
		super();
	}
	public NhanVien(String maNhanVien, String hoTen, String cccd, String soDienThoai, LocalDate ngaySinh, GioiTinh gioiTinh,
			String tinh, String quan, String phuong, String diaChiCuThe, ChucVu chucVu, double luong, TaiKhoan taiKhoan,
			TrangThaiNhanVien trangThai) {
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
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", cccd=" + cccd + ", soDienThoai="
				+ soDienThoai + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", tinh=" + tinh + ", quan="
				+ quan + ", phuong=" + phuong + ", diaChiCuThe=" + diaChiCuThe + ", chucVu=" + chucVu + ", luong="
				+ luong + ", taiKhoan=" + taiKhoan + ", trangThai=" + trangThai + "]";
	}
	
	
}
