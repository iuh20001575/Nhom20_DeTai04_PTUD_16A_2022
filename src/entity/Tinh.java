package entity;

import java.util.Objects;

public class Tinh {
	private String id;
	private String tinh;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTinh() {
		return tinh;
	}

	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	public Tinh(String id, String tinh) {
		super();
		setId(id);
		setTinh(tinh);
	}

	public Tinh(String id) {
		super();
		setId(id);
	}

	public Tinh() {
		super();
	}

	@Override
	public String toString() {
		return "Tinh [id=" + id + ", tinh=" + tinh + "]";
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
		Tinh other = (Tinh) obj;
		return Objects.equals(id, other.id);
	}

}
