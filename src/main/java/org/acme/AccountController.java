package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * AccountController jest klasą, która obsługuje żądania HTTP związane z kontami.
 * UWAGA: Aby uruchomić aplikację, należy wejść do zakładki Maven, następnie prompt (Execute Maven Goal) i wybrać opcję quarkus:dev.
 */
@Path("/accounts")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Tag(name = "get", description = "Pobierz dane o koncie/kontach")
    @GET
    public Response getAllAccounts() {
        return Response.ok(accountService.getAllAccounts()).build();
    }

    @Tag(name = "get", description = "Pobierz dane o koncie/kontach")
    @GET
    @Path("/{name}")
    public Response getAccount(
            @Parameter(description = "Nazwa konta, którą chcesz pobrać.", required = true, example = "Bob")
            @PathParam("name") String name) {
        return Response.ok(accountService.getAccount(name)).build();
    }
    @Tag(name = "post", description = "Dodaj nowe konto")
    @POST
    @Operation(
            summary = "Dodaj nowe konto",
            description = "Dodaj nowe konto do listy kont.",
            operationId = "addAccount"
    )
    @APIResponse(responseCode = "200", description = "Konto zostało dodane.")
    @APIResponse(responseCode = "400", description = "Niepoprawne dane konta.")
    public Response addAccount(Account account) {
        return Response.ok(accountService.addAccount(account)).build();
    }
}
