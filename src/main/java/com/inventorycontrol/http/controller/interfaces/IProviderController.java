package com.inventorycontrol.http.controller.interfaces;

import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ProviderRequest;
import com.inventorycontrol.http.dto.response.ProviderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Fornecedores")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/provider")
@Validated
public interface IProviderController {

    @Operation(summary = "Salva um novo fornecedor.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o fornecedor seja armazenado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "providerName": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "num": "Número",
                                              "district": "Bairro",
                                              "cep": "31640-200",
                                              "contact": "85 9 1111-2222",
                                              "cnpj": "18.436.184/0001-00",
                                              "insc": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
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
                                                "type": "http://api/inventorycontrol/provider",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso o fornecedor já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:41:33",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/provider",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Fornecedor já está armazenado no sistema."
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
                                                 "path": "http://api/inventorycontrol/provider",
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
                    Dados para salvar um novo fornecedor.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                              "providerName": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "num": "Número",
                                              "district": "Bairro",
                                              "cep": "31640-200",
                                              "contact": "85 9 1111-2222",
                                              "cnpj": "18.436.184/0001-00",
                                              "insc": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<ProviderResponse> save(@RequestBody @Valid ProviderRequest providerRequest);

    @Operation(summary = "Atualiza o fornecedor por ID.", description = """
            Atualiza o fornecedor passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso o fornecedor seja alterado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "cityId": "3047caa5-621e-4c42-89ee-e6b7c2f1b16b",
                                              "providerName": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "num": "Número",
                                              "district": "Bairro",
                                              "cep": "31640-200",
                                              "contact": "85 9 1111-2222",
                                              "cnpj": "18.436.184/0001-00",
                                              "insc": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum fornecerdor.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/provider/{providerId}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Fornecedor não está armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso o fornecedor já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:42:53",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/provider/{providerId}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe um fornecedor armazenado com esse código."
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
                                                "type": "http://api/inventorycontrol/provider/{providerId}",
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
                                                 "path": "http://api/inventorycontrol/provider/{providerId}",
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
                    Dados para atualizar um fornecedor.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                              "providerName": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "num": "Número",
                                              "district": "Bairro",
                                              "cep": "31640-200",
                                              "contact": "85 9 1111-2222",
                                              "cnpj": "18.436.184/0001-00",
                                              "insc": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{providerId}")
    ResponseEntity<ProviderResponse> update(@RequestBody @Valid ProviderRequest providerRequest, @PathVariable String providerId);


    @Operation(summary = "Consulta todos os fornecedores.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "providerName": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "num": "Número",
                                              "district": "Bairro",
                                              "cep": "31640-200",
                                              "contact": "85 9 1111-2222",
                                              "cnpj": "18.436.184/0001-00",
                                              "insc": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
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
                                                "type": "http://api/inventorycontrol/provider/all",
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
                                                 "path": "http://api/inventorycontrol/provider/all",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/all")
    ResponseEntity<List<ProviderResponse>> findAll();


    @Operation(summary = "Consulta o fornecedor por ID.", description = """
            Consulta o fornecedor passando o ID na URL.
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
                                              "providerName": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "num": "Número",
                                              "district": "Bairro",
                                              "cep": "31640-200",
                                              "contact": "85 9 1111-2222",
                                              "cnpj": "18.436.184/0001-00",
                                              "insc": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum fornecedor.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/provider/{providerId}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Fornecedor não está salvo no sistema."
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
                                                "type": "http://api/inventorycontrol/provider/{providerId}",
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
                                                 "path": "http://api/inventorycontrol/provider/{providerId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{providerId}")
    ResponseEntity<ProviderResponse> findById(@PathVariable String providerId);


    @Operation(summary = "Deleta um fornecedor por ID.", description = """
            Deleta o fornecedor passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso o fornecedor seja deletado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Fornecedor deletado com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum fornecedor.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/provider/{providerId}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Fornecedor não armazenado no sistema."
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
                                                "type": "http://api/inventorycontrol/provider/{providerId}",
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
                                                 "path": "http://api/inventorycontrol/provider/{providerId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping(path = "/{providerId}")
    ResponseEntity<MessageError> delete(@PathVariable String providerId);
}
