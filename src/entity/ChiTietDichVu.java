package entity;

import java.util.Objects;

public class ChiTietDichVu {
	private DichVu dichVu;
	private ChiTietDatPhong chiTietDatPhong;
	private int soLuong;

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public ChiTietDatPhong getChiTietDatPhong() {
		return chiTietDatPhong;
	}

	public void setChiTietDatPhong(ChiTietDatPhong chiTietDatPhong) {
		this.chiTietDatPhong = chiTietDatPhong;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chiTietDatPhong, dichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDichVu other = (ChiTietDichVu) obj;
		return Objects.equals(chiTietDatPhong, other.chiTietDatPhong) && Objects.equals(dichVu, other.dichVu);
	}

	@Override
	public String toString() {
		return "ChiTietDichVu [dichVu=" + dichVu + ", chiTietDatPhong=" + chiTietDatPhong + ", soLuong=" + soLuong
				+ "]";
	}

	public ChiTietDichVu(DichVu dichVu, ChiTietDatPhong chiTietDatPhong, int soLuong) {
		super();
		this.dichVu = dichVu;
		this.chiTietDatPhong = chiTietDatPhong;
		this.soLuong = soLuong;
	}

	public ChiTietDichVu(DichVu dichVu, ChiTietDatPhong chiTietDatPhong) {
		super();
		this.dichVu = dichVu;
		this.chiTietDatPhong = chiTietDatPhong;
	}

	public ChiTietDichVu() {
		super();
	}
}
