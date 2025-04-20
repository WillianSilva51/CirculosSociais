package exceptions;

import java.io.Serial;

public class ContatoNotFoundException extends EntidadeNotFound{
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1600335032524758018L;

	private final String contatoNaoEncontrado;

	public ContatoNotFoundException(String contatoId) {
		super("Cliente " + contatoId + " nao encotrado");
		contatoNaoEncontrado = contatoId;
	}

	public String getContatoNaoEncontrado() {
		return contatoNaoEncontrado;
	}
}
