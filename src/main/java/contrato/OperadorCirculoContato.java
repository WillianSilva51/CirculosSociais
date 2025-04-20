package contrato;

import model.Circulo;
import model.Contato;
import exceptions.CirculoNotFoundException;
import exceptions.ContatoNotFoundException;

import java.util.List;

public interface OperadorCirculoContato {

    /**
     * Adiciona um contato a um circulo
     * @param idContato identificao do contato
     * @param idCirculo identificao do do circulo
     * @return true se o contato for adicionado ao circulo,
     * false, se o circulo ja estiver cheio
     * @throws CirculoNotFoundException caso o circulo informado nao exista
     * @throws ContatoNotFoundException caso o contato informado nao exista
     */
    boolean adicionarContatoAoCirculo(String idContato, String idCirculo)
            throws CirculoNotFoundException, ContatoNotFoundException;

    /**
     * Remove um contato de um circulo
     * @param idContato identificao do contato
     * @param idCirculo identificao do circulo
     * @return true caso o contato seja removido,
     * false se o contato nao estiver contido no circulo
     * @throws CirculoNotFoundException caso o circulo informado nao exista
     * @throws ContatoNotFoundException caso o contato informado nao exista
     */
    boolean removerContatoDoCirculo(String idContato, String idCirculo)
            throws CirculoNotFoundException, ContatoNotFoundException;

    /**
     * Retorna a lista de contatos ordenas por nome contido em um circulo
     * @param id do circulo
     * @return a lista de contato contido no circulo ordenado pelo nome
     * @throws CirculoNotFoundException caso o circulo informado nao exista
     */
    List<Contato> recuperarContatosDoCirculo(String id) throws CirculoNotFoundException;

    /**
     * Retorna a lista de circulos cujo o contato pertence
     * @param id do contato
     * @return a lista de circulo que contem o contato ordenado pelo nome
     * @throws ContatoNotFoundException caso o contato nao exista
     */
    List<Circulo> recuperarCirculosDoContato(String id) throws ContatoNotFoundException;

    /**
     * Retorna a lista de circulo ordenados pelo nome que os
     * dois contatos possuem em comum
     * @param idContato1 identificador de um contato
     * @param idContato2 identificador do outro contato
     * @return a lista de circulos em comum ordenados pelo nome
     * @throws ContatoNotFoundException caso algum dos contatos nao existam
     */
    List<Circulo> getCirculosEmComum(String idContato1, String idContato2) throws ContatoNotFoundException;

}
