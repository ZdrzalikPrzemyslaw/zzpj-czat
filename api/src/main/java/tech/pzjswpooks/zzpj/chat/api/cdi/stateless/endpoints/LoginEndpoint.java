package tech.pzjswpooks.zzpj.chat.api.cdi.stateless.endpoints;

import tech.pzjswpooks.zzpj.chat.api.payloads.request.AuthenticationRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.JWTResponseDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.MessageResponseDto;
import tech.pzjswpooks.zzpj.chat.api.security.JwtUtils;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Interceptors(LogInterceptor.class)
@DenyAll
@Path("login")
public class LoginEndpoint {

    private final IdentityStoreHandler identityStoreHandler;

    private final JwtUtils jwtUtils;

    @Inject
    public LoginEndpoint(IdentityStoreHandler identityStoreHandler, JwtUtils jwtUtils) {
        this.identityStoreHandler = identityStoreHandler;
        this.jwtUtils = jwtUtils;
    }

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        CredentialValidationResult credentialValidationResult = identityStoreHandler.validate(authenticationRequestDTO.toCredential());
        if (credentialValidationResult.getStatus() != CredentialValidationResult.Status.VALID) {
            // TODO: 09.05.2021 Poprawić odpowiedź
            return Response.status(Response.Status.UNAUTHORIZED).entity(new MessageResponseDto("Login Password Incorrect")).build();
        }
        return Response.ok().entity(
                new JWTResponseDTO(credentialValidationResult.getCallerPrincipal().getName(),
                credentialValidationResult.getCallerGroups(),
                jwtUtils.generateJwtTokenForUser(credentialValidationResult.getCallerPrincipal().getName()))).build();
    }

}
