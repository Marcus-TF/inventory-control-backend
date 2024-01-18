package com.inventorycontrol.http.controller.interfaces;

import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.EntryItemRequest;
import com.inventorycontrol.http.dto.response.EntryItemResponse;
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

@Tag(name = "Entrada de Item")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/entryItem")
@Validated
public interface IEntryItemController {

    @Operation(summary = "Salva um novo item de entrada.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o item de entrada seja armazenado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryItemId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "batch": "Lote 3",
                                              "amount": 4,
                                              "value": 10.00,
                                              "entryId": "ad007867-bd42-4c9e-86da-28286e872043",
                                              "productId": "ad007867-bd42-4c9e-86da-28286e872043"
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
                                                "type": "http://api/inventorycontrol/entryItem/create",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso o item de entrada já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:41:33",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/entryItem/create",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Item de entrada já está armazenado no sistema."
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
                                                 "path": "http://api/inventorycontrol/entryItem/create",
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
                    Dados para salvar um novo item de entrada.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "batch": "Lote 3",
                                              "amount": 4,
                                              "value": 10.00,
                                              "entryId": "ad007867-bd42-4c9e-86da-28286e872043",
                                              "productId": "ad007867-bd42-4c9e-86da-28286e872043"
                                            }
                                    """
                    )
            )
    )
    @PostMapping("/create")
    ResponseEntity<EntryItemResponse> save(@RequestBody EntryItemRequest request);

    @Operation(summary = "Atualiza o item de entrada por ID.", description = """
            Atualiza o item de entrada passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso o item de entrada seja alterado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryItemId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "batch": "Lote 3",
                                              "amount": 4,
                                              "value": 10.00,
                                              "entryId": "ad007867-bd42-4c9e-86da-28286e872043",
                                              "productId": "ad007867-bd42-4c9e-86da-28286e872043"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum item de entrada.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/entryItem/update/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Item de entrada não está armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso o item de entrada já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:42:53",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/entryItem/update/{uuid}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe um item de entrada armazenado com esse código."
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
                                                "type": "http://api/inventorycontrol/entryItem/update/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/entryItem/update/{uuid}",
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
                    Dados para atualizar um item de entrada.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "batch": "Lote 3",
                                              "amount": 4,
                                              "value": 10.00,
                                              "entryId": "ad007867-bd42-4c9e-86da-28286e872043",
                                              "productId": "ad007867-bd42-4c9e-86da-28286e872043"
                                            }
                                    """
                    )
            )
    )
    @PutMapping("/update/{uuid}")
    ResponseEntity<EntryItemResponse> update(@PathVariable UUID uuid, @RequestBody EntryItemRequest request);


    @Operation(summary = "Consulta todos os itens de entrada.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "entryItemId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "batch": "Lote 3",
                                              "amount": 4,
                                              "value": 10.00
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
                                                "type": "http://api/inventorycontrol/entryItem/findAll",
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
                                                 "path": "http://api/inventorycontrol/entryItem/findAll",
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
    ResponseEntity<List<EntryItemResponse>> findAll();


    @Operation(summary = "Consulta o item de entrada por ID.", description = """
            Consulta o item de entrada passando o ID na URL.
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
                                              "entryItemId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "batch": "Lote 3",
                                              "amount": 4,
                                              "value": 10.00
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum item de entrada.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/entryItem/findById/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Item de entrada não está armazenado no sistema."
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
                                                "type": "http://api/inventorycontrol/entryItem/findById/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/entryItem/findById/{uuid}",
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
    ResponseEntity<EntryItemResponse> findById(@PathVariable UUID uuid);


    @Operation(summary = "Deleta um item de entrada por ID.", description = """
            Deleta o item de entrada passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso o item de entrada seja deletado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Item de entrada deletado com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum item de entrada.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/entryItem/delete/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Item de entrada não armazenado no sistema."
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
                                                "type": "http://api/inventorycontrol/entryItem/delete/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/entryItem/delete/{uuid}",
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
