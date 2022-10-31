package entity;

import java.time.LocalTime;

public class ChiTietDatPhong {
	private DatPhong datPhong;
	private Phong phong;
	private LocalTime gioVao;
	private LocalTime gioRa;

	public DatPhong getDatPhong() {
		return datPhong;
	}

	public void setDatPhong(DatPhong datPhong) {
		this.datPhong = datPhong;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public LocalTime getGioVao() {
		return gioVao;
	}

	public void setGioVao(LocalTime gioVao) {
		this.gioVao = gioVao;
	}

	public LocalTime getGioRa() {
		return gioRa;
	}

	public void setGioRa(LocalTime gioRa) {
		this.gioRa = gioRa;
	}

	public ChiTietDatPhong() {
		super();
	}
	
	public ChiTietDatPhong(DatPhong datPhong) {
		super();
		this.datPhong = datPhong;
	}

	public ChiTietDatPhong(DatPhong datPhong, Phong phong, LocalTime gioVao) {
		super();
		this.datPhong = datPhong;
		this.phong = phong;
		this.gioVao = gioVao;
	}

	public ChiTietDatPhong(DatPhong datPhong, Phong phong, LocalTime gioVao, LocalTime gioRa) {
		super();
		this.datPhong = datPhong;
		this.phong = phong;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
	}

	@Override
	public String toString() {
		return "ChiTietDatPhong [datPhong=" + datPhong + ", phong=" + phong + ", gioVao=" + gioVao + ", gioRa=" + gioRa
				+ "]";
	}

}
