package contrato;

import exceptions.EntidadeNotFound;

import java.util.List;

public interface Gerenciavel<T> {

  boolean criar(T t);
  boolean remover(T t);
  boolean remover(String identificador);
  T get(String identificador) throws EntidadeNotFound;
  boolean atualizar(T t);
  List<T> getTodos();
  int getQuantidade();
}
