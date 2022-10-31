package entity;

public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private int soLuong;
	private String donViTinh;
	private LoaiDichVu loaiDichVu;
	private double giaMua;

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public LoaiDichVu getLoaiDichVu() {
		return loaiDichVu;
	}

	public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}

	public double getGiaMua() {
		return giaMua;
	}

	public void setGiaMua(double giaMua) {
		this.giaMua = giaMua;
	}
	
	public double getGiaBan() {
		return getGiaMua() * 1.5;
	}

	public DichVu() {
		super();
	}

	public DichVu(String maDichVu) {
		super();
		this.maDichVu = maDichVu;
	}

	public DichVu(String maDichVu, String tenDichVu, int soLuong, String donViTinh, LoaiDichVu loaiDichVu,
			double giaMua) {	
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.loaiDichVu = loaiDichVu;
		this.giaMua = giaMua;
	}

	@Override
	public String toString() {
		return "DichVu [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", soLuong=" + soLuong + ", donViTinh="
				+ donViTinh + ", loaiDichVu=" + loaiDichVu + ", giaMua=" + giaMua + "]";
	}

}
