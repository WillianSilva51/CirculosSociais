package model;

public class Contato implements Comparable<Contato> {
	private final String id;
	private String email;

	public Contato(String id, String email) {
		this.id = id;
		setEmail(email);
	}

	public void setEmail(String email) {
		if (!email.isBlank()) {
			this.email = email;
		}
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Contato other = (Contato) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (email == null) {
            return other.email == null;
        } else return email.equals(other.email);
    }

	@Override
	public int compareTo(Contato contato) {
		return id.compareTo(contato.id);
	}

	@Override
	public String toString() {
		return id;
	}
}
