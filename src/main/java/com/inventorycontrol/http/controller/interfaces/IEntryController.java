package com.inventorycontrol.http.controller.interfaces;

import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.EntryRequest;
import com.inventorycontrol.http.dto.response.EntryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Entrada")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/entry")
@Validated
public interface IEntryController {

    @Operation(summary = "Salva uma nova entrada.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso a entrada seja armazenada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "requestDate": dd/MM/yyyy HH:MM,
                                              "entryDate": dd/MM/yyyy HH:MM,
                                              "total": 10.00,
                                              "shipping": "envio",
                                              "invoiceNumber": "número da fatura",
                                              "tax": "taxa",
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "23-05-2022 17:38:25",
                                                "status": 403,
                                                "type": "http://api/inventorycontrol/entry/create",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a entrada já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:41:33",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/entry/create",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Entrada já está armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://api/inventorycontrol/entry/create",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = """
                    Dados para salvar uma nova entrada.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "requestDate": dd/MM/yyyy HH:MM,
                                              "entryDate": dd/MM/yyyy HH:MM,
                                              "total": 10.00,
                                              "shipping": "envio",
                                              "invoiceNumber": "número da fatura",
                                              "tax": "taxa",
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a"
                                            }
                                    """
                    )
            )
    )
    @PostMapping("/create")
    ResponseEntity<EntryResponse> save(@RequestBody EntryRequest request);

    @Operation(summary = "Atualiza a entrada por ID.", description = """
            Atualiza a entrada passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a entrada seja alterada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "requestDate": dd/MM/yyyy HH:MM,
                                              "entryDate": dd/MM/yyyy HH:MM,
                                              "total": 10.00,
                                              "shipping": "envio",
                                              "invoiceNumber": "número da fatura",
                                              "tax": "taxa",
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma entrada.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/entry/update/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Entrada não está armazenada no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a entrada já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:42:53",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/entry/update/{uuid}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe uma entrada armazenada com esse código."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "23-05-2022 17:38:25",
                                                "status": 403,
                                                "type": "http://api/inventorycontrol/entry/update/{uuid}",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://api/inventorycontrol/entry/update/{uuid}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = """
                    Dados para atualizar uma entrada.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "requestDate": dd/MM/yyyy HH:MM,
                                              "entryDate": dd/MM/yyyy HH:MM,
                                              "total": 10.00,
                                              "shipping": "envio",
                                              "invoiceNumber": "número da fatura",
                                              "tax": "taxa",
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a"
                                            }
                                    """
                    )
            )
    )
    @PutMapping("/update/{uuid}")
    ResponseEntity<EntryResponse> update(@PathVariable UUID uuid, @RequestBody EntryRequest request);


    @Operation(summary = "Consulta todos as entradas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "requestDate": dd/MM/yyyy HH:MM,
                                              "entryDate": dd/MM/yyyy HH:MM,
                                              "total": 10.00,
                                              "shipping": "envio",
                                              "invoiceNumber": "número da fatura",
                                              "tax": "taxa"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "23-05-2022 17:38:25",
                                                "status": 403,
                                                "type": "http://api/inventorycontrol/entry/findAll",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://api/inventorycontrol/entry/findAll",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/findAll")
    ResponseEntity<List<EntryResponse>> findAll();


    @Operation(summary = "Consulta a entrada por ID.", description = """
            Consulta a entrada passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "requestDate": dd/MM/yyyy HH:MM,
                                              "entryDate": dd/MM/yyyy HH:MM,
                                              "total": 10.00,
                                              "shipping": "envio",
                                              "invoiceNumber": "número da fatura",
                                              "tax": "taxa"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma entrada.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/entry/findById/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Entrada não está armazenada no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "23-05-2022 17:38:25",
                                                "status": 403,
                                                "type": "http://api/inventorycontrol/entry/findById/{uuid}",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://api/inventorycontrol/entry/findById/{uuid}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })

    @GetMapping("/findById/{uuid}")
    ResponseEntity<EntryResponse> findById(@PathVariable UUID uuid);


    @Operation(summary = "Deleta a entrada por ID.", description = """
            Deleta entrada passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a entrada seja deletado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Entrada deletado com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma entrada.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/entry/delete/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Entrada não armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "23-05-2022 17:38:25",
                                                "status": 403,
                                                "type": "http://api/inventorycontrol/entry/delete/{uuid}",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://api/inventorycontrol/entry/delete/{uuid}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<MessageError> delete(@PathVariable UUID uuid);
}
