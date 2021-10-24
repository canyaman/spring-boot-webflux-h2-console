package spring.boot.actuator.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.boot.web.server.Ssl
import org.springframework.util.Assert
import org.springframework.util.StringUtils
import java.net.InetAddress

/**
 * Properties for the management server (e.g. port and path settings).
 *
 * @author Dave Syer
 * @author Stephane Nicoll
 * @author Vedran Pavic
 * @since 2.0.0
 * @see ServerProperties
 */
@ConfigurationProperties(prefix = "management.server", ignoreUnknownFields = true)
class ManagementServerProperties {
    /**
     * Returns the management port or `null` if the
     * [server port][ServerProperties.getPort] should be used.
     * @return the port
     * @see .setPort
     */
    /**
     * Sets the port of the management server, use `null` if the
     * [server port][ServerProperties.getPort] should be used. Set to 0 to use a
     * random port or set to -1 to disable.
     * @param port the port
     */
    /**
     * Management endpoint HTTP port (uses the same port as the application by default).
     * Configure a different port to use management-specific SSL.
     */
    var port: Int? = null

    /**
     * Network address to which the management endpoints should bind. Requires a custom
     * management.server.port.
     */
    var address: InetAddress? = null

    /**
     * Management endpoint base path (for instance, `/management`). Requires a custom
     * management.server.port.
     */
    var basePath = ""
        set(basePath) {
            field = cleanBasePath(basePath)
        }
    val servlet = Servlet()

    @NestedConfigurationProperty
    var ssl: Ssl? = null

    private fun cleanBasePath(basePath: String): String {
        var candidate = StringUtils.trimWhitespace(basePath)
        if (StringUtils.hasText(candidate)) {
            if (!candidate.startsWith("/")) {
                candidate = "/$candidate"
            }
            if (candidate.endsWith("/")) {
                candidate = candidate.substring(0, candidate.length - 1)
            }
        }
        return candidate
    }

    /**
     * Servlet properties.
     */
    class Servlet {
        /**
         * Return the context path with no trailing slash (i.e. the '/' root context is
         * represented as the empty string).
         * @return the context path (no trailing slash)
         */
        /**
         * Set the context path.
         * @param contextPath the context path
         */
        /**
         * Management endpoint context-path (for instance, `/management`). Requires a
         * custom management.server.port.
         */
        @get:Deprecated(
            """since 2.4.0 for removal in 2.6.0 in favor of
		  {@link ManagementServerProperties#getBasePath()}""")
        @get:DeprecatedConfigurationProperty(replacement = "management.server.base-path")
        @set:Deprecated("""since 2.4.0 for removal in 2.6.0 in favor of
		  {@link ManagementServerProperties#setBasePath(String)}""")
        var contextPath = ""
            set(contextPath) {
                Assert.notNull(contextPath, "ContextPath must not be null")
                field = cleanContextPath(contextPath)
            }

        private fun cleanContextPath(contextPath: String): String {
            return if (StringUtils.hasText(contextPath) && contextPath.endsWith("/")) {
                contextPath.substring(0, contextPath.length - 1)
            } else contextPath
        }
    }
}