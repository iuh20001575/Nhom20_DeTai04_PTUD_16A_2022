package entity;

import java.util.Objects;

public class Phuong {
	private String id;
	private String phuong;
	private Quan quan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhuong() {
		return phuong;
	}

	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}

	public Quan getQuan() {
		return quan;
	}

	public void setQuan(Quan quan) {
		this.quan = quan;
	}

	public Phuong(String id, String phuong, Quan quan) {
		super();
		setId(id);
		setPhuong(phuong);
		setQuan(quan);
	}

	public Phuong(String id) {
		super();
		setId(id);
	}

	public Phuong() {
		super();
	}

	@Override
	public String toString() {
		return "Phuong [id=" + id + ", phuong=" + phuong + ", quan=" + quan + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phuong other = (Phuong) obj;
		return Objects.equals(id, other.id);
	}

}
