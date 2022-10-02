package entity;

public class TaiKhoan {
	private String maTaiKhoan;
	private String matKhau;
	
	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String maTaiKhoan, String matKhau) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.matKhau = matKhau;
	}
	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", matKhau=" + matKhau + "]";
	}
	
	
}
