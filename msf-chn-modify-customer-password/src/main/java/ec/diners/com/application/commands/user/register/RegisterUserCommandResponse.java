package ec.diners.com.application.commands.user.register;

public record RegisterUserCommandResponse(
      String message,
      Boolean isSuccess
) {
}
