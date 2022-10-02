package entity;

import java.time.LocalDate;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String cccd;
	private LocalDate ngaySinh;
	private GioiTinh gioiTinh;
	private String soDienThoai;
	private String tinh;
	private String quan;
	private String phuong;
	private String diaChiCuThe;
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
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
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
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
	
	
	public KhachHang() {
		super();
	}
	public KhachHang(String maKhachHang, String hoTen, String cccd, LocalDate ngaySinh, GioiTinh gioiTinh,
			String soDienThoai, String tinh, String quan, String phuong, String diaChiCuThe) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.cccd = cccd;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.tinh = tinh;
		this.quan = quan;
		this.phuong = phuong;
		this.diaChiCuThe = diaChiCuThe;
	}
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", cccd=" + cccd + ", ngaySinh="
				+ ngaySinh + ", gioiTinh=" + gioiTinh + ", soDienThoai=" + soDienThoai + ", tinh=" + tinh + ", quan="
				+ quan + ", phuong=" + phuong + ", diaChiCuThe=" + diaChiCuThe + "]";
	}
	
	
}
