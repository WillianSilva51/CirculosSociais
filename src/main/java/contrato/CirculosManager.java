package contrato;

import model.Circulo;

import java.util.List;


public interface CirculosManager {

    /**
     * Adiciona um círculo
     * @param id do circulo. Deve ser unico
     * @param limite define o maximo de contatos que esse circulo pode conter
     * @return true caso o contato seja adicionado,
     * false se já existir um círculo com o mesmo 'id'
     */
    boolean criarCirculo(String id, int limite);
    
    /**
     * Atualiza o limite do circulo
     * @param circulo com o mesmo identifador e novo limite
	 * @return true caso o circulo seja atualizado,
     * false se o circulo com nao existir
     */
    boolean atualizarCirculo(Circulo circulo);

    /**
     * Retorna um círculo
     * @param idCirculo do circulo a ser recuperado
     * @return circulo caso ele exista,
     * null se nenhum circulo com o id informado for encontrado
     */
    Circulo getCirculo(String idCirculo);
    
    /**
     * @return a lista dos círculos ordenados pelo nome
     */
    List<Circulo> getTodosCirculos();
    
    /**
     * Remove um círculo
     * @param idCirculo identificador do circulo a ser removido
     * @return true caso o circulo seja removido,
     * false se o circulo nao existir
     */
    boolean removerCirculo(String idCirculo);
    
    /**
     * @return o numero de circulos cadastrados
     */
    int getNumeroDeCirculos();

}
