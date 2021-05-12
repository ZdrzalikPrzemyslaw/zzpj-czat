package tech.pzjswpooks.zzpj.chat.api.utils;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Formatter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class PropertiesLoaderIT {


    private static Network network = Network.newNetwork();

    // TODO: 12.05.2021 Przy konfiguracji kontenera występuje problem,
    //  baza danych działa na local host na moim komputerze, a docker jej nei widzi D :
    
    @Container
    private static GenericContainer serviceOne = new GenericContainer(
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
            .waitingFor(Wait.forHttp("/zzpj/api/example/ready").forPort(8080).forStatusCode(200));

    private URL serviceURL;

    private WebTarget target;

    public PropertiesLoaderIT() throws MalformedURLException, URISyntaxException {
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
