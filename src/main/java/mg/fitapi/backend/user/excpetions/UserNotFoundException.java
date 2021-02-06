package mg.fitapi.backend.user.excpetions;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String message) {
    super("User" + message + "nie znaleziony w bazie danych");
  }
}
