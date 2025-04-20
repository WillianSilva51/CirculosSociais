package model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Circulo implements Comparable<Circulo> {
	private final String id;
	private int limite;
	private final Set<Contato> contatos = new TreeSet<>();

	public Circulo(String id, int limite) {
		this.id = id;
		setLimite(limite);
	}

	public boolean isContatoInCirculo(Contato contato) {
		return contatos.contains(contato);
	}

	public String getId() {
		return id;
	}

	public int getLimite() {
		return limite;
	}

	public boolean setLimite(int limite) {
		if (limite > 0) {
			this.limite = limite;
			return true;
		}
		return false;
	}

	public int getNumeroDeContatos() {
		return contatos.size();
	}

	public boolean adicionarContato(Contato contato) {
		if (getNumeroDeContatos() >= limite || isContatoInCirculo(contato)) {
			return false;
		}
		return contatos.add(contato);
	}

	public boolean removerContato(Contato contato) {
		if (!isContatoInCirculo(contato)) {
			return false;
		}

		return contatos.remove(contato);
	}

	public void limparContatos() {
		contatos.clear();
	}

	public List<Contato> getContatos() {
		return List.copyOf(contatos);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limite;
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
		Circulo other = (Circulo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
        return limite == other.limite;
    }

	@Override
	public int compareTo(Circulo circulo) {
		return id.compareTo(circulo.id);
	}

	@Override
	public String toString() {
		return id;
	}
}
