package exceptions;

import java.io.Serial;

public class CirculoNotFoundException extends EntidadeNotFound{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -7331861696548215622L;

	private final String circuloNaoEncontrado;

	public CirculoNotFoundException(String circuloId) {
		super("Circulo " +  circuloId + " nao foi encontrado");
		circuloNaoEncontrado = circuloId;
	}

	public String getCirculoNaoEncontrado() {
		return circuloNaoEncontrado;
	}
}
