package multibanking.token

import io.token.security.UnsecuredFileSystemKeyStore
import io.token.tpp.TokenClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Paths
import io.token.TokenClient.TokenCluster.SANDBOX

@Configuration
class TokenApi {

    @Bean
    fun buildTokenClient(): TokenClient {
        return TokenClient.builder()
            .withKeyStore(UnsecuredFileSystemKeyStore(keys.toFile()))
            .connectTo(SANDBOX)
            .build()
    }

    companion object {
        private val keys = Files.createDirectories(Paths.get("./keys"))
    }
}
