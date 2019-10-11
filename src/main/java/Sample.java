import io.token.proto.common.alias.AliasProtos;
import io.token.security.UnsecuredFileSystemKeyStore;
import io.token.tpp.Member;
import io.token.tpp.TokenClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.token.TokenClient.TokenCluster.SANDBOX;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class Sample {

    public static void createMember() throws Exception {

        Path keys = Files.createDirectories(Paths.get("./keys"));
        TokenClient tokenClient = TokenClient.builder()
                .withKeyStore(new UnsecuredFileSystemKeyStore(keys.toFile()))
                .connectTo(SANDBOX)
                .build();

        AliasProtos.Alias alias = AliasProtos.Alias.newBuilder()
                .setType(AliasProtos.Alias.Type.EMAIL)
                .setValue(randomAlphabetic(10).toLowerCase()
                        + "+noverify@example.com")
                .build();

        Member newMember = tokenClient.createMemberBlocking(alias);
        System.out.println(newMember);
        Member memberBlocking = tokenClient.getMemberBlocking(newMember.memberId());
        System.out.println(memberBlocking);

    }
}
