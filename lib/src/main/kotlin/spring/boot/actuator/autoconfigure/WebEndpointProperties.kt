package spring.boot.actuator.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.util.Assert
import org.springframework.util.StringUtils

/**
 * Configuration properties for web management endpoints.
 *
 * @author Madhura Bhave
 * @author Phillip Webb
 * @since 2.0.0
 */
@ConfigurationProperties(prefix = "management.endpoints.web")
class WebEndpointProperties {
    val exposure = Exposure()

    /**
     * Base path for Web endpoints. Relative to the servlet context path
     * (server.servlet.context-path) or WebFlux base path (spring.webflux.base-path) when
     * the management server is sharing the main server port. Relative to the management
     * server base path (management.server.base-path) when a separate management server
     * port (management.server.port) is configured.
     */
    var basePath = "/actuator"
        set(basePath) {
            Assert.isTrue(basePath.isEmpty() || basePath.startsWith("/"), "Base path must start with '/' or be empty")
            field = cleanBasePath(basePath)
        }

    /**
     * Mapping between endpoint IDs and the path that should expose them.
     */
    val pathMapping: Map<String, String> = LinkedHashMap()
    val discovery = Discovery()

    private fun cleanBasePath(basePath: String): String {
        return if (StringUtils.hasText(basePath) && basePath.endsWith("/")) {
            basePath.substring(0, basePath.length - 1)
        } else basePath
    }

    class Exposure {
        /**
         * Endpoint IDs that should be included or '*' for all.
         */
        var include: Set<String> = LinkedHashSet()

        /**
         * Endpoint IDs that should be excluded or '*' for all.
         */
        var exclude: Set<String> = LinkedHashSet()
    }

    class Discovery {
        /**
         * Whether the discovery page is enabled.
         */
        var isEnabled = true
    }
}