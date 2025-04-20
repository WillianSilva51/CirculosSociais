import model.Circulo;
import model.Contato;
import contrato.*;
import exceptions.CirculoNotFoundException;
import exceptions.ContatoNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GContatos implements ContatosManager, CirculosManager, OperadorCirculoContato {
	private final Map<String, Circulo> circulos = new TreeMap<>();
	private final Map<String, Contato> contatos = new TreeMap<>();
	private final Map<String, Contato> favoritos = new TreeMap<>();

	private boolean existeCirculo(String id) {
		return circulos.containsKey(id);
	}

	private boolean existeContato(String id) {
		return contatos.containsKey(id);
	}

	private Circulo getCirculoOrThrow(String id) throws CirculoNotFoundException {
		if (!existeCirculo(id)) {
			throw new CirculoNotFoundException(id);
		}
		return circulos.get(id);
	}

	private Contato getContatoOrThrow(String id) throws ContatoNotFoundException {
		if (!existeContato(id)) {
			throw new ContatoNotFoundException(id);
		}
		return contatos.get(id);
	}

	@Override
	public boolean criarCirculo(String id, int limite) {
		if (!existeCirculo(id)) {
			circulos.put(id, new Circulo(id, limite));
			return true;
		}
		return false;
	}

	@Override
	public boolean atualizarCirculo(Circulo circulo) {
		if (existeCirculo(circulo.getId())) {
			return circulos.get(circulo.getId()).setLimite(circulo.getLimite());
		}
		return false;
	}

	@Override
	public Circulo getCirculo(String idCirculo) {
		return circulos.get(idCirculo);
	}

	@Override
	public List<Circulo> getTodosCirculos() {
		return List.copyOf(circulos.values());
	}

	@Override
	public boolean removerCirculo(String idCirculo) {
		if (existeCirculo(idCirculo)) {
			circulos.get(idCirculo).limparContatos();
			circulos.remove(idCirculo);
			return true;
		}
		return false;
	}

	@Override
	public int getNumeroDeCirculos() {
		return circulos.size();
	}

	@Override
	public boolean criarContato(String id, String email) {
		if (!existeContato(id)) {
			contatos.put(id, new Contato(id, email));
			return true;
		}
		return false;
	}

	@Override
	public List<Contato> getTodosContatos() {
		return List.copyOf(contatos.values());
	}

	@Override
	public boolean atualizarContato(Contato contato) {
		if (existeContato(contato.getId())) {
			contatos.get(contato.getId()).setEmail(contato.getEmail());
			return true;
		}
		return false;
	}

	@Override
	public boolean removerContato(String id) {
		if (existeContato(id)) {
			circulos.values().forEach(c -> c.removerContato(contatos.get(id)));
			desfavoritar(id);
			contatos.remove(id);
			return true;
		}
		return false;
	}

	@Override
	public Contato getContato(String id) {
		return contatos.get(id);
	}

	@Override
	public int getNumeroDeContatos() {
		return contatos.size();
	}

	@Override
	public boolean favoritar(String idContato) {
		if (existeContato(idContato)) {
			favoritos.put(idContato, contatos.get(idContato));
			return true;
		}
		return false;
	}

	@Override
	public boolean desfavoritar(String idContato) {
		if (existeContato(idContato) && eFavorito(idContato)) {
			favoritos.remove(idContato);
			return true;
		}
		return false;
	}

	@Override
	public boolean eFavorito(String id) {
		return favoritos.containsKey(id);
	}

	@Override
	public List<Contato> getFavoritos() {
		return List.copyOf(favoritos.values());
	}

	@Override
	public boolean adicionarContatoAoCirculo(String idContato, String idCirculo)
			throws CirculoNotFoundException, ContatoNotFoundException {
		Circulo circulo = getCirculoOrThrow(idCirculo);
		Contato contato = getContatoOrThrow(idContato);

		return circulo.adicionarContato(contato);
	}

	@Override
	public boolean removerContatoDoCirculo(String idContato, String idCirculo)
			throws CirculoNotFoundException, ContatoNotFoundException {
		Circulo circulo = getCirculoOrThrow(idCirculo);
		Contato contato = getContatoOrThrow(idContato);

		return circulo.removerContato(contato);
	}

	@Override
	public List<Contato> recuperarContatosDoCirculo(String id) throws CirculoNotFoundException {
		if (!existeCirculo(id)) {
			throw new CirculoNotFoundException(id);
		}
		return circulos.get(id).getContatos();
	}

	@Override
	public List<Circulo> recuperarCirculosDoContato(String id) throws ContatoNotFoundException {
		Contato contato = getContatoOrThrow(id);

		return circulos.values().stream().filter(c -> c.isContatoInCirculo(contato)).toList();
	}

	@Override
	public List<Circulo> getCirculosEmComum(String idContato1, String idContato2) throws ContatoNotFoundException {
		Contato contato1 = getContatoOrThrow(idContato1);
		Contato contato2 = getContatoOrThrow(idContato2);

		return circulos.values().stream().filter(c -> c.isContatoInCirculo(contato1)
				&& c.isContatoInCirculo(contato2)).toList();
	}
}
