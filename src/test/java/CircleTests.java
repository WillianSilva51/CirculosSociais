import java.util.Arrays;


import model.Circulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CircleTests {
	private static final String AMIGOS = "amigos";
	private static final String TRABALHO = "trabalho";
	private static final String FAMILIA = "familia";
	
	private GContatos gcont;
	private Circulo familia, trabalho, amigos;
	
	@BeforeEach
    public void setUp() {
        familia = new Circulo(FAMILIA, 3);
        trabalho = new Circulo(TRABALHO, 3);
        amigos = new Circulo(AMIGOS, 1);

        gcont = new GContatos();
    }
	
	@Test
	public void adicionarCirculos(){
		assertTrue(gcont.criarCirculo(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.criarCirculo(AMIGOS, 1), "O circulo deve ser adicionado");
		assertTrue(gcont.criarCirculo(TRABALHO, 3), "O circulo deve ser adicionado");
		
		assertEquals(3, gcont.getNumeroDeCirculos(), "Todos os 3 circulos devem ser adiconados");
	}
	
	@Test
	public void adicionarCirculoLimiteInvalido(){
		assertTrue(gcont.criarCirculo(TRABALHO, 3), "O circulo deve ser adicionado");
		assertFalse(gcont.criarCirculo(TRABALHO, -5), "Circulo com limite menor ou igual a zero nao deve ser adicionado");
		
		assertEquals(1, gcont.getNumeroDeCirculos(), "Apenas um ciruclo devia ter sido adicionado");
	}
	
	@Test
	public void adicionarCirculoDuplicado(){
		assertTrue(gcont.criarCirculo(TRABALHO, 3), "O circulo deve ser adicionado");
		assertFalse(gcont.criarCirculo(TRABALHO, 5), "Circulo com id duplicado nao deve ser adicionado");
		
		assertEquals(1, gcont.getNumeroDeCirculos(), "Apenas um ciruclo devia ter sido adicionado");
	}
	
	@Test
	public void buscandoCirculoExistente(){
		assertTrue(gcont.criarCirculo(FAMILIA, 3), "O circulo deve ser adicionado");
		assertEquals(gcont.getCirculo(FAMILIA), familia, "O circulo recuperado nao foi procurado");
	}
	
	@Test
	public void buscandoCirculoInexistente(){
		assertEquals(gcont.getCirculo("inimigos"), null, "Circulo nao existente");
	}
	
	@Test
	public void recuperandoTodosOsCirculos(){
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 1);
		gcont.criarCirculo(TRABALHO, 3);
		
		assertEquals(Arrays.asList(amigos, familia, trabalho), gcont.getTodosCirculos(), "Lista de circulos incorreta");
	}
	
	@Test
	public void removendoCirculoExistente(){
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 1);
		gcont.criarCirculo(TRABALHO, 3);
		
		assertTrue(gcont.removerCirculo(AMIGOS), "Circulo nao removido");
		assertEquals(2, gcont.getNumeroDeCirculos(), "Quantidade de circulos errada");
		assertEquals(Arrays.asList(familia, trabalho), gcont.getTodosCirculos(), "Lista de circulos incorreta");
	}
	
	@Test
	public void removendoCirculoInexistente(){
		gcont.criarCirculo(FAMILIA, 3);
		gcont.criarCirculo(AMIGOS, 1);
		gcont.criarCirculo(TRABALHO, 3);
		
		assertFalse(gcont.removerCirculo("inimigos"), "Circulo nao existe, logo nao pode ser removido");
		assertEquals(3, gcont.getNumeroDeCirculos(), "Quantidade de circulos errada");
		assertEquals(Arrays.asList(amigos, familia, trabalho), gcont.getTodosCirculos(), "Lista de circulos incorreta");
	}
	
	@Test
	public void atualizandoCirculoExistente(){
		gcont.criarCirculo(FAMILIA, 3);
		assertTrue(gcont.atualizarCirculo(new Circulo(FAMILIA, 4)), "O circulo deve ser atualizado");
		assertEquals(gcont.getCirculo(FAMILIA), new Circulo(FAMILIA, 4), "O circulo nao foi atualizado corretamente");
	}
	
	@Test
	public void atualizandoCirculoLimiteInvalido(){
		gcont.criarCirculo(FAMILIA, 3);
		assertFalse(gcont.atualizarCirculo(new Circulo(FAMILIA, 0)), "O circulo possui limite invalido");
	}
	
	@Test
	public void atualizandoCirculoInexistente(){
		assertFalse(gcont.atualizarCirculo(new Circulo("inimigos", 4)), "Circulo nao existente");
		assertEquals(gcont.getCirculo(FAMILIA), null, "Circulo nao foi cadastrado");
	}
}
