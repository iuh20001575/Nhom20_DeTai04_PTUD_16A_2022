package entity;

import java.util.Objects;

public class DichVu {
	private String donViTinh;
	private double giaMua;
	private LoaiDichVu loaiDichVu;
	private String maDichVu;
	private int soLuong;
	private String tenDichVu;
	private boolean trangThaiXoa;

	public DichVu() {
		super();
	}

	public DichVu(String maDichVu) {
		super();
		this.maDichVu = maDichVu;
	}

	public DichVu(String maDichVu, String tenDichVu, int soLuong, String donViTinh, LoaiDichVu loaiDichVu,
			double giaMua, boolean trangThaiXoa) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.loaiDichVu = loaiDichVu;
		this.giaMua = giaMua;
		this.trangThaiXoa = trangThaiXoa;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDichVu, other.maDichVu);
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public double getGiaBan() {
		return getGiaMua() * 1.5;
	}

	public double getGiaMua() {
		return giaMua;
	}

	public LoaiDichVu getLoaiDichVu() {
		return loaiDichVu;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public boolean getTrangThaiXoa() {
		return trangThaiXoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDichVu);
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public void setGiaMua(double giaMua) {
		this.giaMua = giaMua;
	}

	public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public void setTrangThaiXoa(boolean trangThaiXoa) {
		this.trangThaiXoa = trangThaiXoa;
	}

	@Override
	public String toString() {
		return "DichVu [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", soLuong=" + soLuong + ", donViTinh="
				+ donViTinh + ", loaiDichVu=" + loaiDichVu + ", giaMua=" + giaMua + ", trangThaiXoa=" + trangThaiXoa
				+ "]";
	}

}
