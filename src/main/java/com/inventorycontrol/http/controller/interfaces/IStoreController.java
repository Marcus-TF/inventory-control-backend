package com.inventorycontrol.http.controller.interfaces;

import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.StoreRequest;
import com.inventorycontrol.http.dto.response.StoreResponse;
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

@Tag(name = "Lojas")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/store")
@Validated
public interface IStoreController {

    @Operation(summary = "Salva uma nova loja.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso a loja seja armazenado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "zipCode": 4,
                                              "address": 10.00,
                                              "number": 135,
                                              "district": "Bairro",
                                              "telephone": "11 23333-4444",
                                              "subscription": "Descrição",
                                              "cnpj": "16546056/0001",
                                              "cityId": "3141da11-d991-4d69-a0b3-822fbdca30be"
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
                                                "type": "http://api/inventorycontrol/store/create",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a loja já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:41:33",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/store/create",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Loja já está armazenada no sistema."
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
                                                 "path": "http://api/inventorycontrol/store/create",
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
                    Dados para salvar um nova loja.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "name": "Loja Teste",
                                              "zipCode": 4,
                                              "address": 10.00,
                                              "number": 135,
                                              "district": "Bairro",
                                              "telephone": "11 23333-4444",
                                              "subscription": "Descrição",
                                              "cnpj": "16546056/0001",
                                              "cityId": "3141da11-d991-4d69-a0b3-822fbdca30be"
                                            }
                                    """
                    )
            )
    )
    @PostMapping("/create")
    ResponseEntity<StoreResponse> save(@RequestBody StoreRequest request);

    @Operation(summary = "Atualiza a loja por ID.", description = """
            Atualiza a loja passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a loja seja alterado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "zipCode": 4,
                                              "address": 10.00,
                                              "number": 135,
                                              "district": "Bairro",
                                              "telephone": "11 23333-4444",
                                              "subscription": "Descrição",
                                              "cnpj": "16546056/0001",
                                              "cityId": "3141da11-d991-4d69-a0b3-822fbdca30be"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma loja.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/store/update/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Loja não está armazenada no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a loja já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:42:53",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/store/update/{uuid}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe uma loja armazenada com esse código."
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
                                                "type": "http://api/inventorycontrol/store/update/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/store/update/{uuid}",
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
                    Dados para atualizar uma loja.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "name": "Loja Teste",
                                              "zipCode": 4,
                                              "address": 10.00,
                                              "number": 135,
                                              "district": "Bairro",
                                              "telephone": "11 23333-4444",
                                              "subscription": "Descrição",
                                              "cnpj": "16546056/0001",
                                              "cityId": "3141da11-d991-4d69-a0b3-822fbdca30be"
                                            }
                                    """
                    )
            )
    )
    @PutMapping("/update/{uuid}")
    ResponseEntity<StoreResponse> update(@PathVariable UUID uuid, @RequestBody StoreRequest request);


    @Operation(summary = "Consulta todas as lojas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "zipCode": 4,
                                              "address": 10.00,
                                              "number": 135,
                                              "district": "Bairro",
                                              "telephone": "11 23333-4444",
                                              "subscription": "Descrição",
                                              "cnpj": "16546056/0001"
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
                                                "type": "http://api/inventorycontrol/store/findAll",
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
                                                 "path": "http://api/inventorycontrol/store/findAll",
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
    ResponseEntity<List<StoreResponse>> findAll();


    @Operation(summary = "Consulta a loja por ID.", description = """
            Consulta a loja passando o ID na URL.
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
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "zipCode": 4,
                                              "address": 10.00,
                                              "number": 135,
                                              "district": "Bairro",
                                              "telephone": "11 23333-4444",
                                              "subscription": "Descrição",
                                              "cnpj": "16546056/0001"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma loja.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/store/findById/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Loja não está armazenada no sistema."
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
                                                "type": "http://api/inventorycontrol/store/findById/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/store/findById/{uuid}",
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
    ResponseEntity<StoreResponse> findById(@PathVariable UUID uuid);


    @Operation(summary = "Deleta uma loja por ID.", description = """
            Deleta a loja passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a loja seja deletada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Loja deletada com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma loja.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/store/delete/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Loja não armazenada no sistema."
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
                                                "type": "http://api/inventorycontrol/store/delete/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/store/delete/{uuid}",
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
