package org.asciicerebrum.neocortexengine.infrastructure;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Helper class for managing the ioc container's application context.
 *
 * @author species8472
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    /**
     * The application context of the ioc container.
     */
    private static ApplicationContext ctx = null;

    /**
     * Retrieves the global application context.
     *
     * @return the application context.
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * @param ctxInput set context from static context.
     */
    private static void setCtx(final ApplicationContext ctxInput) {
        ctx = ctxInput;
    }

    /**
     * @param ctxInput the application context to set by the ioc container.
     */
    @Override
    public final void setApplicationContext(final ApplicationContext ctxInput) {
        setCtx(ctxInput);
    }
}
