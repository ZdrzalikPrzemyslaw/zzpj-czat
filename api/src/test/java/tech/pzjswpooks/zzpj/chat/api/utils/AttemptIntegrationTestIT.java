package tech.pzjswpooks.zzpj.chat.api.utils;


import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Formatter;


@Testcontainers
public class AttemptIntegrationTestIT {


    private static final Network network = Network.newNetwork();

    // TODO: 12.05.2021 Przy konfiguracji kontenera występuje problem,
    //  baza danych działa na local host na moim komputerze, a docker jej nei widzi D :

    @Container
    private static final GenericContainer serviceOne = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payara/micro:5.2021.1-jdk11")
                            .copy("zzpj.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("zzpj.war", Path.of("target", "api-0.0.1-SNAPSHOT.war"))
    )
            .withNetwork(network)
            .withExposedPorts(8080, 4848, 6900)
            .waitingFor(Wait.forHttp("/api/api/readiness").forPort(8080).forStatusCode(200));

    private final URL serviceURL;

    private final WebTarget target;

    public AttemptIntegrationTestIT() throws MalformedURLException, URISyntaxException {
        Formatter formatter = new Formatter();
        String stringURL = formatter.format("http://localhost:%d/api/api", serviceOne.getMappedPort(8080)).toString();
        serviceURL = new URL(stringURL);
        Client client = ClientBuilder.newClient();
        target = client.target(serviceURL.toURI());
    }


//    @Test
//    public void getConfirmationJwtSecret() {
//        Response getResponse = target.path("example").path("ready")
//                .request(MediaType.APPLICATION_JSON)
//                .get();
//
//        System.out.println(getResponse);
//    }
//
//    @Test
//    public void getConfirmationJwtExpiration() {
//    }
}
