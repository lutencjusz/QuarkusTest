package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @GET
    public Response getAllAccounts() {
        return Response.ok(accountService.getAllAccounts()).build();
    }

    @GET
    @Path("/{name}")
    public Response getAccount(@PathParam("name") String name) {
        return Response.ok(accountService.getAccount(name)).build();
    }

    @POST
    public Response addAccount(Account account) {
        return Response.ok(accountService.addAccount(account)).build();
    }
}
