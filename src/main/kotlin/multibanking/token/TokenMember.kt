package multibanking.token

import io.token.proto.common.alias.AliasProtos
import io.token.tpp.Member
import io.token.tpp.TokenClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TokenMember {

    @Autowired
    private lateinit var tokenClient: TokenClient

    fun createMember(email: String): Member {
        val alias = AliasProtos.Alias.newBuilder()
            .setType(AliasProtos.Alias.Type.EMAIL)
            .setValue(email)
            .build()

        return tokenClient.createMemberBlocking(alias)
    }
}
