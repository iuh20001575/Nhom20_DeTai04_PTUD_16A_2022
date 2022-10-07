package entity;

import java.util.Objects;

public class Quan {
	private String id;
	private String quan;
	private Tinh tinh;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public Tinh getTinh() {
		return tinh;
	}

	public void setTinh(Tinh tinh) {
		this.tinh = tinh;
	}

	public Quan(String id, String quan, Tinh tinh) {
		super();
		setId(id);
		setQuan(quan);
		setTinh(tinh);
	}

	public Quan(String id) {
		super();
		setId(id);
	}

	public Quan() {
		super();
	}

	@Override
	public String toString() {
		return "Quan [id=" + id + ", quan=" + quan + ", tinh=" + tinh + "]";
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
		Quan other = (Quan) obj;
		return Objects.equals(id, other.id);
	}

}
