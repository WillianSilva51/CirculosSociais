package contrato;

import model.Contato;

import java.util.List;


public interface ContatosManager {
	
    /**
     * Adiciona um contato no repositorio de contatos
     * @param id O nome do contato. Deve ser unico
     * @param email O email do contato
     * @return true caso o contato seja adicionado,
     * false se um contato com o mesmo id ja existir
     */
    boolean criarContato(String id, String email);

    /**
     * Retorna a lista com todos os contatos ORDENADOS POR NOME
     * @return a lista de contatos
     */
    List<Contato> getTodosContatos();
    
    /**
     * Atualiza o email do contato idenficado pelo id
     * @param contato com o id e novo email
     * @return true caso o contato seja atualizado,
     * false se o contato com nao existir
     */
    boolean atualizarContato(Contato contato);

    
    /**
     * Remove um contato
     * @param id identificador do contato a ser removido
     * @return true caso o contato seja removido,
     * false se o contato nao existir
     */
    boolean removerContato(String id);

    /**
     * Retorna um contato
     * @param id do contato a ser recuperado
     * @return contato caso ele exista,
     * null se nenhum contato com o id informado for encontrado
     */
    Contato getContato(String id);
    
    /**
     * Retorna o numero de contatos cadastrados
     * @return o numero de contatos
     */
    int getNumeroDeContatos();

    /**
     * Marca um contato como favorito
     * @param idContato identificador do contato
     * @return true caso o contato seja marcado,
     * false se o contato nao existir
     */
    boolean favoritar(String idContato);

    /**
     * Faz um contato deixar de ser favorito
     * @param idContato identificador do contato
     * @return true caso contato deixar de ser favorito,
     * false se o contato nao existir
     */
    boolean desfavoritar(String idContato);


    /**
     * Verifica se um contato e favorito
     * @param id identificador do contato
     * @return true se o contato for favorito,
     * false caso contrario
     */
    boolean eFavorito(String id);

    /**
     * Pega a lista de todos os favoritos
     * @return a lista de favoritos
     */
    List<Contato> getFavoritos();

}
