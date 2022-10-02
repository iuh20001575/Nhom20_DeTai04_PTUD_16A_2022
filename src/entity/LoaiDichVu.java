package entity;

public class LoaiDichVu {
	private String maLoaiDichVu;
	private String tenLoaiDichVu;
	
	public String getMaLoaiDichVu() {
		return maLoaiDichVu;
	}
	public void setMaLoaiDichVu(String maLoaiDichVu) {
		this.maLoaiDichVu = maLoaiDichVu;
	}
	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}
	public void setTenLoaiDichVu(String tenLoaiDichVu) {
		this.tenLoaiDichVu = tenLoaiDichVu;
	}
	
	
	public LoaiDichVu() {
		super();
	}
	public LoaiDichVu(String maLoaiDichVu, String tenLoaiDichVu) {
		super();
		this.maLoaiDichVu = maLoaiDichVu;
		this.tenLoaiDichVu = tenLoaiDichVu;
	}
	@Override
	public String toString() {
		return "LoaiDichVu [maLoaiDichVu=" + maLoaiDichVu + ", tenLoaiDichVu=" + tenLoaiDichVu + "]";
	}
	
	
}
