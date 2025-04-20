import model.Contato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTests {

	private static final String JOAQUIM_EMAIL = "joaquim@ufc.br";
	private static final String JOAQUIM = "joaquim";
	private static final String ANA_EMAIL = "ana@ufc.br";
	private static final String ANA = "ana";
	private static final String MARIO_EMAIL = "mario@ufc.br";
	private static final String MARIO = "mario";
	private static final String JOSE_EMAIL = "jose@ufc.br";
	private static final String JOSE = "jose";
	private static final String JAMES_EMAIL = "james@ufc.com";
	private static final String JAMES = "james";
	private GContatos gcont;
	private Contato james, jose, mario, ana, joaquim;

	@BeforeEach
	public void setUp() {
		james = new Contato(JAMES, JAMES_EMAIL);
		jose = new Contato(JOSE, JOSE_EMAIL);
		mario = new Contato(MARIO, MARIO_EMAIL);
		ana = new Contato(ANA, ANA_EMAIL);
		joaquim = new Contato(JOAQUIM, JOAQUIM_EMAIL);

		gcont = new GContatos();
	}

	@Test
	public void adicionarContato() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
	}

	@Test
	public void adicionarContatoDuplicado() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertFalse(gcont.criarContato(JAMES, "jesus2@ufc.com"), "Contato com id duplicado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
	}

	@Test
	public void removendoContato() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada" );
		assertTrue(gcont.removerContato(JAMES), "Contato deve ser removido");
		assertEquals(0, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
	}

	@Test
	public void removendoContatoInexistente() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
		assertFalse(gcont.removerContato("ramiro"), "Contato nao cadastrado nao pode ser removido");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
	}

	@Test
	public void recuperandoContato() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
		Contato james = gcont.getContato(JAMES);
		assertEquals(james, this.james, "Contato recuperado diferente do buscado");
	}

	@Test
	public void recuperandoContatoInexistene() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");
		assertEquals(null, gcont.getContato("ramiro"), "Contato nao existente");
	}

	@Test
	public void recuperandoTodosOsContatos() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.criarContato(ANA, ANA_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.criarContato(JOSE, JOSE_EMAIL), "Contato valido, deve ser adicionado");

		assertEquals(Arrays.asList(ana, james, jose), gcont.getTodosContatos(), "Lista de contatos errada");
	}

	@Test
	public void atualizandoContato() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumeroDeContatos(), "Quantidade de contatos errada");

		james.setEmail("novo@ufc.br");

		assertTrue(gcont.atualizarContato(james), "Contato valido, deve ser atualizado");
		Contato james = gcont.getContato(JAMES);
		assertEquals(this.james, james, "Contato nao foi atualizado corretamente");
	}

	@Test
	public void atualizandoInexistente() {
		assertFalse(gcont.atualizarContato(james), "Contato nao existente, logo nao pode ser atualizado");
	}

	@Test
	public void favoritandoUmContato() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.favoritar(JAMES), "Contato deve ser marcado como favorito");
		assertTrue(gcont.eFavorito(JAMES), "Contato esta na lista de favoritos");
		assertFalse(gcont.eFavorito(ANA), "Contato nao esta na lista de favoritos");

	}

	@Test
	public void favoritandoUmContatoInexistente() {
		assertFalse(gcont.favoritar(JAMES), "Contato nao existe");
		assertFalse(gcont.eFavorito(JAMES), "Contato nao esta na lista de favoritos");
	}

	@Test
	public void desfavoritandoUmContato() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.favoritar(JAMES), "Contato deve ser marcado como favorito");
		assertTrue(gcont.eFavorito(JAMES), "Contato esta na lista de favoritos");

		assertTrue(gcont.desfavoritar(JAMES), "Contato nao removido dos favoritos");
		assertFalse(gcont.eFavorito(JAMES), "Contato nao esta na lista de favoritos");
	}

	@Test
	public void desfavoritandoUmContatoInexistente() {
		assertFalse(gcont.desfavoritar(JAMES), "Contato nao existe");
		assertFalse(gcont.eFavorito(JAMES), "Contato nao esta na lista de favoritos");
	}

	@Test
	public void recuperandoTodosOsFavoritos() {
		assertTrue(gcont.criarContato(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.criarContato(MARIO, MARIO_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.criarContato(ANA, ANA_EMAIL), "Contato valido, deve ser adicionado");

		assertTrue(gcont.favoritar(JAMES), "Contato deve ser marcado como favorito");
		assertTrue(gcont.favoritar(ANA), "Contato deve ser marcado como favorito");
		assertTrue(gcont.favoritar(MARIO), "Contato deve ser marcado como favorito");

		assertTrue(gcont.eFavorito(ANA), "O contato esta na lista de favoritos");
		assertFalse(gcont.eFavorito(JOSE), "O contato nao esta na lista de favoritos");

		assertEquals(Arrays.asList(ana, james, mario), gcont.getFavoritos(), "Lista de favoritos errada");

		assertTrue(gcont.desfavoritar(ANA), "Contato deve ser removido dos favoritos");

		assertEquals(Arrays.asList(james, mario), gcont.getFavoritos(), "Remocao de favoritos errada");

		assertTrue(gcont.removerContato(MARIO), "Contato deve ser removido dos favoritos");

		assertEquals(Arrays.asList(james), gcont.getFavoritos(), "Remocao de favoritos errada");

	}
}
