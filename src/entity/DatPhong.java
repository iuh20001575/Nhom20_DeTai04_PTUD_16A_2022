package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class DatPhong {
	private String maDatPhong;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private LocalDate ngayDatPhong;
	private LocalTime gioDatPhong;
	private LocalDate ngayNhanPhong;
	private LocalTime gioNhanPhong;
	private TrangThaiDatPhong trangThai;

	public String getMaDatPhong() {
		return maDatPhong;
	}

	public void setMaDatPhong(String maDatPhong) {
		this.maDatPhong = maDatPhong;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public LocalDate getNgayDatPhong() {
		return ngayDatPhong;
	}

	public void setNgayDatPhong(LocalDate ngayDatPhong) {
		this.ngayDatPhong = ngayDatPhong;
	}

	public LocalTime getGioDatPhong() {
		return gioDatPhong;
	}

	public void setGioDatPhong(LocalTime gioDatPhong) {
		this.gioDatPhong = gioDatPhong;
	}

	public LocalDate getNgayNhanPhong() {
		return ngayNhanPhong;
	}

	public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
		this.ngayNhanPhong = ngayNhanPhong;
	}

	public LocalTime getGioNhanPhong() {
		return gioNhanPhong;
	}

	public void setGioNhanPhong(LocalTime gioNhanPhong) {
		this.gioNhanPhong = gioNhanPhong;
	}

	public TrangThaiDatPhong getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiDatPhong trangThai) {
		this.trangThai = trangThai;
	}

	public DatPhong() {
		super();
	}

	public DatPhong(String maDatPhong) {
		super();
		this.maDatPhong = maDatPhong;
	}

	public DatPhong(String maDatPhong, KhachHang khachHang, NhanVien nhanVien, LocalDate ngayDatPhong,
			LocalTime gioDatPhong, LocalDate ngayNhanPhong, LocalTime gioNhanPhong, TrangThaiDatPhong trangThai) {
		super();
		this.maDatPhong = maDatPhong;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayDatPhong = ngayDatPhong;
		this.gioDatPhong = gioDatPhong;
		this.ngayNhanPhong = ngayNhanPhong;
		this.gioNhanPhong = gioNhanPhong;
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "DatPhong [maDatPhong=" + maDatPhong + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", ngayDatPhong=" + ngayDatPhong + ", gioDatPhong=" + gioDatPhong + ", ngayNhanPhong=" + ngayNhanPhong
				+ ", gioNhanPhong=" + gioNhanPhong + ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDatPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatPhong other = (DatPhong) obj;
		return Objects.equals(maDatPhong, other.maDatPhong);
	}

}
