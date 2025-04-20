package exceptions;

public abstract class EntidadeNotFound extends Exception {
  public EntidadeNotFound(String message) {
    super(message);
  }
}
