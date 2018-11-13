package org.sdrc.scpsassam.util;

import com.p6spy.engine.spy.appender.MultiLineFormat;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

public class PrettySqlMultiLineFormat extends MultiLineFormat {
    private static final Formatter formatter;

    static {
        formatter = new BasicFormatterImpl();
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {

        return super.formatMessage(connectionId, now, elapsed, category, formatter.format(prepared), formatter.format(sql));
    }
}