package refactoring.day2.resource;

import java.util.Date;

public class Message {
	private String reason;

	public Message() {
	}

	public Message(String reason) {
		this.reason = reason;
	}

	public void setReason(String reason) {
	}

	public void setDate(Date date) {
	}
	
	public String getReason() {
		return reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		Message other = (Message)obj;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}
}
