package org.asciicerebrum.mydndgame.logappender;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Taken from
 * http://giordano.scalzo.biz/2009/10/21/no-more-excuses-junit-testing-log-messages/.
 *
 * @author species8472
 */
public class RecordingAppender extends AppenderSkeleton {

    private static final List<String> messages = new ArrayList<String>();
    private static final RecordingAppender appender = new RecordingAppender();

    private RecordingAppender() {
        super();
    }

    public static RecordingAppender appender(PatternLayout patternLayout) {
        appender.setLayout(patternLayout);
        appender.clear();
        return appender;
    }

    protected void append(LoggingEvent event) {
        messages.add(layout.format(event));
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }

    public static String[] messages() {
        return (String[]) messages.toArray(new String[messages.size()]);
    }

    private void clear() {
        messages.clear();
    }
}
