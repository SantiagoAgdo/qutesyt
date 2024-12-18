package ec.diners.com.application.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends StdSerializer<Date> {

    public static final String PATTERN = "dd/MM/yyyy";

    public DateSerializer() {
        this(null);
    }

    public DateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(
            Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        gen.writeString(new SimpleDateFormat(PATTERN).format(value));
    }
}
