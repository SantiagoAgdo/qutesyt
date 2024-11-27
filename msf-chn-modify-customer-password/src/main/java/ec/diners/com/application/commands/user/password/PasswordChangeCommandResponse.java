package ec.diners.com.application.commands.user.password;

public record PasswordChangeCommandResponse(
        PasswordChangeHeaderCommandResponse dinHeader,
        PasswordChangeBodyCommandResponse dinBody,
        PasswordChangeErrorCommandResponse dinError
) {
    public record PasswordChangeHeaderCommandResponse(
            String aplicacionId,
            String canalId,
            String sesionId,
            String dispositivo,
            String idioma,
            String portalId,
            String uuid,
            String ip,
            String horaTransaccion,
            String llaveSimetrica,
            String usuario,
            PaginationCommandResponse paginado,
            String tags
    ) {
    }

    public record PasswordChangeBodyCommandResponse(
            String descripcionTransaccion,
            String codigoTransaccion,
            String fechaTransaccion,
            String horaTransaccion) {
    }

    public record PasswordChangeErrorCommandResponse(
            String tipo,
            String fecha,
            String origen,
            String codigo,
            String codigoErrorProveedor,
            String mensaje,
            String detalle
    ) {
    }

    public record PaginationCommandResponse(
            int cantRegistros,
            int numTotalPag,
            int numPagActual
    ) {
    }
}
