import model.Circulo;
import model.Contato;
import exceptions.CirculoNotFoundException;
import exceptions.ContatoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ContactsCircleRelationsTest {

	private static final String AMIGOS = "amigos";
	private static final String TRABALHO = "trabalho";
	private static final String FAMILIA = "familia";
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
	private Circulo familia, trabalho, amigos;
	private Contato james, jose, mario, ana, joaquim;

	@BeforeEach
	public void setUp() {
		familia = new Circulo(FAMILIA, 3);
		trabalho = new Circulo(TRABALHO, 3);
		amigos = new Circulo(AMIGOS, 2);

		james = new Contato(JAMES, JAMES_EMAIL);
		jose = new Contato(JOSE, JOSE_EMAIL);
		mario = new Contato(MARIO, MARIO_EMAIL);
		ana = new Contato(ANA, ANA_EMAIL);
		joaquim = new Contato(JOAQUIM, JOAQUIM_EMAIL);

		gcont = new GContatos();
	}


	@Test
	public void adicionarContatoCirculoExistente() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarContato(JAMES, JAMES_EMAIL);
		assertTrue(gcont.adicionarContatoAoCirculo(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertEquals(1, gcont.getCirculo(FAMILIA).getNumeroDeContatos(), "Numero de contatos no circulo errado");
		assertEquals(Arrays.asList(familia), gcont.recuperarCirculosDoContato(JAMES), "Lista de circulos do contato esta errada");
		assertEquals(Arrays.asList(james), gcont.recuperarContatosDoCirculo("familia"), "Lista de contatos de um circulo esta errada");
	}

	@Test
	public void adicionarContatoInexistenteCirculoExistente() {
		gcont.criarCirculo(FAMILIA, 3);

		assertThrows(ContatoNotFoundException.class, () -> gcont.adicionarContatoAoCirculo(JAMES, FAMILIA));
		assertEquals(0, gcont.getCirculo(FAMILIA).getNumeroDeContatos(), "Numero de contatos no circulo errado");

	}

	@Test
	public void adicionarContatoCirculoInexistente() throws CirculoNotFoundException, Exception {
		gcont.criarContato(JAMES, JAMES_EMAIL);

		CirculoNotFoundException e = assertThrows(CirculoNotFoundException.class, () -> gcont.adicionarContatoAoCirculo(JAMES, FAMILIA));
		assertTrue(e.getCirculoNaoEncontrado() == FAMILIA, "A excecao nao retornou o id do circulo que nao existe");

		assertEquals(gcont.getCirculo(FAMILIA), null, "Circulo nao existente");
		assertEquals(Collections.EMPTY_LIST, gcont.recuperarCirculosDoContato(JAMES), "Contato nao esta em nenhum circulo");
	}

	@Test
	public void adicionarContatoDuplicadoCirculoExistente() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);
		assertFalse(gcont.adicionarContatoAoCirculo(JAMES, FAMILIA), "Contato ja esta no circulo");
		assertEquals(1, gcont.getCirculo(FAMILIA).getNumeroDeContatos(), "Numero de contatos no circulo errado");
		assertEquals(Arrays.asList(familia), gcont.recuperarCirculosDoContato(JAMES), "Lista de circulos do contato esta errada");
		assertEquals(Arrays.asList(james), gcont.recuperarContatosDoCirculo("familia"), "Lista de contatos de um circulo esta errada");
	}

	@Test
	public void adicionarAlemDoLimite() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);

		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.criarContato(JOSE, JOSE_EMAIL);
		gcont.criarContato(ANA, ANA_EMAIL);
		gcont.criarContato(JOAQUIM, JOAQUIM_EMAIL);

		assertTrue(gcont.adicionarContatoAoCirculo(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.adicionarContatoAoCirculo(JOSE, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.adicionarContatoAoCirculo(ANA, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertFalse(gcont.adicionarContatoAoCirculo(JOAQUIM, FAMILIA), "Limite do circulo atingido");
	}

	@Test
	public void adicionarContatoVariosCirculos() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 2);

		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.criarContato(MARIO, MARIO_EMAIL);
		gcont.criarContato(JOSE, JOSE_EMAIL);

		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);
		gcont.adicionarContatoAoCirculo(MARIO, FAMILIA);
		gcont.adicionarContatoAoCirculo(JOSE, FAMILIA);

		assertTrue(gcont.adicionarContatoAoCirculo(JAMES, AMIGOS), "Contato deve ser adicionado ao circulo");

		assertEquals(Arrays.asList(amigos, familia), gcont.recuperarCirculosDoContato(JAMES), "Lista de circulos do contato esta errada");

		assertEquals(Arrays.asList(james, jose, mario), gcont.recuperarContatosDoCirculo(FAMILIA), "Lista de contatos de um circulo esta errada");
	}

	@Test
	public void removendoContatoDoCirculo() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 2);

		gcont.criarContato(JAMES, JAMES_EMAIL);

		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);
		gcont.adicionarContatoAoCirculo(JAMES, AMIGOS);

		assertTrue(gcont.removerContatoDoCirculo(JAMES, AMIGOS), "Contato deve ser removido ao circulo");

		assertEquals(Arrays.asList(familia), gcont.recuperarCirculosDoContato(JAMES), "Remocao de contato errada");
	}

	@Test
	public void removendoContatoInexistenteDoCirculo() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);

		ContatoNotFoundException e = assertThrows(ContatoNotFoundException.class, () -> gcont.removerContatoDoCirculo("margarida", FAMILIA));
		assertTrue(e.getContatoNaoEncontrado() == "margarida", "A excecao nao retornou o id do contato que nao existe");
		assertEquals(1, gcont.getCirculo(FAMILIA).getNumeroDeContatos(), "Numero de contatos no circulo errado");
	}

	@Test
	public void removendoContatoDoCirculoInexistente() {
		CirculoNotFoundException e = assertThrows(CirculoNotFoundException.class, () -> gcont.removerContatoDoCirculo(JAMES, FAMILIA));
		assertTrue(e.getCirculoNaoEncontrado() == FAMILIA, "A excecao nao retornou o id do circulo que nao existe");
	}

	@Test
	public void removendoCirculoQuePossuiContatos() throws CirculoNotFoundException, ContatoNotFoundException {

		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 2);
		gcont.criarCirculo(TRABALHO, 3);

		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.criarContato(MARIO, MARIO_EMAIL);
		gcont.criarContato(JOSE, JOSE_EMAIL);
		gcont.criarContato(ANA, ANA_EMAIL);
		gcont.criarContato(JOAQUIM, JOAQUIM_EMAIL);

		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);
		gcont.adicionarContatoAoCirculo(MARIO, FAMILIA);
		gcont.adicionarContatoAoCirculo(JOSE, FAMILIA);

		gcont.adicionarContatoAoCirculo(JAMES, TRABALHO);
		gcont.adicionarContatoAoCirculo(JOAQUIM, TRABALHO);
		gcont.adicionarContatoAoCirculo(ANA, TRABALHO);

		gcont.adicionarContatoAoCirculo(JAMES, AMIGOS);

		assertTrue(gcont.removerCirculo(FAMILIA), "O circulo deve ser removido");

		assertEquals(Arrays.asList(amigos, trabalho), gcont.recuperarCirculosDoContato(JAMES), "Lista de circulos do contato esta errada");
		
		assertEquals(Collections.EMPTY_LIST, gcont.recuperarCirculosDoContato(JOSE), "Lista de circulos do contato esta errada");
		assertEquals(null, gcont.getCirculo("familia"), "Circulo nao existe mais");

	}
	
	@Test
	public void removendoContatosQueEstaEmCirculos() throws CirculoNotFoundException, ContatoNotFoundException {

		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 2);
		gcont.criarCirculo(TRABALHO, 3);

		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.criarContato(JOSE, JOSE_EMAIL);
		gcont.criarContato(ANA, ANA_EMAIL);
		gcont.criarContato(MARIO, MARIO_EMAIL);
		gcont.criarContato(JOAQUIM, JOAQUIM_EMAIL);

		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);
		gcont.adicionarContatoAoCirculo(MARIO, FAMILIA);
		gcont.adicionarContatoAoCirculo(JOSE, FAMILIA);

		gcont.adicionarContatoAoCirculo(JAMES, TRABALHO);
		gcont.adicionarContatoAoCirculo(JOAQUIM, TRABALHO);
		gcont.adicionarContatoAoCirculo(ANA, TRABALHO);

		gcont.adicionarContatoAoCirculo(JAMES, AMIGOS);

		assertTrue(gcont.removerContato(JAMES), "O contato deve ser removido");

		assertEquals(Arrays.asList(jose, mario), gcont.recuperarContatosDoCirculo("familia"), "A lista de contatos do circulo esta errada");
		assertEquals(Arrays.asList(ana, joaquim), gcont.recuperarContatosDoCirculo("trabalho"), "A lista de contatos do circulo esta errada");
		assertEquals(Collections.EMPTY_LIST, gcont.recuperarContatosDoCirculo("amigos"), "A lista de contatos do circulo esta errada");

		ContatoNotFoundException e = assertThrows(ContatoNotFoundException.class, () -> gcont.recuperarCirculosDoContato(JAMES));
		assertTrue(e.getContatoNaoEncontrado() ==  JAMES, "A excecao nao retornou o id do contato que nao existe");

		assertEquals(null, gcont.getContato(JAMES));

	}

	@Test
	public void circulosEmComum() throws CirculoNotFoundException, ContatoNotFoundException {
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 2);
		gcont.criarCirculo(TRABALHO, 3);

		gcont.criarContato(JAMES, JAMES_EMAIL);
		gcont.criarContato(MARIO, MARIO_EMAIL);
		gcont.criarContato(JOSE, JOSE_EMAIL);
		gcont.criarContato(ANA, ANA_EMAIL);
		gcont.criarContato(JOAQUIM, JOAQUIM_EMAIL);

		gcont.adicionarContatoAoCirculo(JAMES, FAMILIA);
		gcont.adicionarContatoAoCirculo(MARIO, FAMILIA);

		gcont.adicionarContatoAoCirculo(JAMES, TRABALHO);
		gcont.adicionarContatoAoCirculo(JOAQUIM, TRABALHO);
		gcont.adicionarContatoAoCirculo(ANA, TRABALHO);

		gcont.adicionarContatoAoCirculo(JAMES, AMIGOS);
		gcont.adicionarContatoAoCirculo(MARIO, AMIGOS);

		assertEquals(Arrays.asList(trabalho), gcont.getCirculosEmComum(JAMES, ANA));
		assertEquals(Collections.EMPTY_LIST, gcont.getCirculosEmComum(JAMES, JOSE));
		assertEquals(Arrays.asList(amigos, familia), gcont.getCirculosEmComum(JAMES, MARIO));

	}

}
