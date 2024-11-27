package ec.diners.com.application.commands.user.password;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record PasswordChangeCommand(PasswordChangeHeaderCommand dinHeader,
                                    PasswordChangeBodyCommand dinBody
) implements Command<Response<PasswordChangeCommandResponse>> {

    public record PasswordChangeHeaderCommand(
            String aplicacionId,
            String canalId,
            String uuid,
            String sesionId,
            String portalId,
            String ip,
            String horaTransaccion,
            String nivelTrace,
            String nombreServicio,
            String llaveSimetrica,
            PaginationCommand paginado,
            String usuario
    ) {
    }

    public record PasswordChangeBodyCommand(
            String clave,
            String nuevaClave,
            String usuarioBiometrico,
            String tipoIngreso,
            String perfil,
            String codigoUnicoAplicacion
    ) {
    }
    public record PaginationCommand (
            int cantRegistros,
            int numTotalPag,
            int numPagActual
    ) {
    }

}
