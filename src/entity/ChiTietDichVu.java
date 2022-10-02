package entity;

public class ChiTietDichVu {
	private DichVu dichVu;
	private DatPhong datPhong;
	private int soLuong;
	public DichVu getDichVu() {
		return dichVu;
	}
	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
	public DatPhong getDatPhong() {
		return datPhong;
	}
	public void setDatPhong(DatPhong datPhong) {
		this.datPhong = datPhong;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
	public ChiTietDichVu() {
		super();
	}
	public ChiTietDichVu(DichVu dichVu, DatPhong datPhong, int soLuong) {
		super();
		this.dichVu = dichVu;
		this.datPhong = datPhong;
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "ChiTietDichVu [dichVu=" + dichVu + ", datPhong=" + datPhong + ", soLuong=" + soLuong + "]";
	}
	
	
}
