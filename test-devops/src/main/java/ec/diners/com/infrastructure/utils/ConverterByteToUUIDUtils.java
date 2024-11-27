package ec.diners.com.infrastructure.utils;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.UUID;

public class ConverterByteToUUIDUtils {

    private ConverterByteToUUIDUtils(){
    }

    @NotNull
    public static UUID convertByteToUUID(byte[] bytes) {
        ByteBuffer bufferId = ByteBuffer.wrap(bytes);
        long mostSignificantBits = bufferId.getLong();
        long leastSignificantBits = bufferId.getLong();
        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    @NotNull
    public static byte[] convertUUIDToByteArray(UUID uuid) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return buffer.array();
    }
}
