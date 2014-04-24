package com.googlecode.goodsample.rabbitmq.spring;

import java.io.Serializable;

public class Pick implements Serializable {
	private static final long serialVersionUID = -6476421678352650993L;
	private String id;
	private String photo;

	public Pick(String id, String photo) {
		super();
		this.id = id;
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public String getPhoto() {
		return photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pick other = (Pick) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pick [id=" + id + ", photo=" + photo + "]";
	}
}
