package entity;

public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private int soLuongKhach;
	private TrangThaiPhong trangThai;
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
	public TrangThaiPhong getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiPhong trangThai) {
		this.trangThai = trangThai;
	}
	
	
	public Phong() {
		super();
	}
	public Phong(String maPhong, LoaiPhong loaiPhong, int soLuongKhach, TrangThaiPhong trangThai) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.soLuongKhach = soLuongKhach;
		this.trangThai = trangThai;
	}
	
	
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", soLuongKhach=" + soLuongKhach
				+ ", trangThai=" + trangThai + "]";
	}
	
	
}
