package com.inventorycontrol.http.controller.interfaces;

import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.ShippingCompanyRequest;
import com.inventorycontrol.http.dto.response.ShippingCompanyResponse;
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

@Tag(name = "Transportadora")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/shipping_company")
@Validated
public interface IShippingCompanyController {

    @Operation(summary = "Salva uma nova transportadora.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso a transportadora seja armazenada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "number": "Número",
                                              "district": "Bairro",
                                              "zipCode": "31640-200",
                                              "cnpj": "85 9 1111-2222",
                                              "subscription": "18.436.184/0001-00",
                                              "contact": "Número de inscrição",
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
                                                "type": "http://api/inventorycontrol/shipping_company/create",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a transportadora já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:41:33",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/shipping_company/create",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Transportadora já está armazenada no sistema."
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
                                                 "path": "http://api/inventorycontrol/shipping_company/create",
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
                    Dados para salvar uma nova transportadora.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                              "name": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "number": "Número",
                                              "district": "Bairro",
                                              "zipCode": "31640-200",
                                              "cnpj": "85 9 1111-2222",
                                              "subscription": "18.436.184/0001-00",
                                              "contact": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
                                    }
                                    """
                    )
            )
    )
    @PostMapping("/create")
    ResponseEntity<ShippingCompanyResponse> save(@RequestBody ShippingCompanyRequest request);

    @Operation(summary = "Atualiza a transportadora por ID.", description = """
            Atualiza a transportadora passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a transportadora seja alterada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "number": "Número",
                                              "district": "Bairro",
                                              "zipCode": "31640-200",
                                              "cnpj": "85 9 1111-2222",
                                              "subscription": "18.436.184/0001-00",
                                              "contact": "Número de inscrição",
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
                    description = "Caso o ID enviado na URL não seja de nenhuma cidade.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/shipping_company/update/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Transportadora não está armazenada no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a tranportadora já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:42:53",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/shipping_company/update/{uuid}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe uma transportadora armazenado com esse código."
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
                                                "type": "http://api/inventorycontrol/shipping_company/update/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/shipping_company/update/{uuid}",
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
                    Dados para atualizar uma transportadora.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                              "name": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "number": "Número",
                                              "district": "Bairro",
                                              "zipCode": "31640-200",
                                              "cnpj": "85 9 1111-2222",
                                              "subscription": "18.436.184/0001-00",
                                              "contact": "Número de inscrição",
                                              "telephone": "85 9 1111-2222",
                                              "cityListIds": [
                                                "3047caa5-621e-4c42-89ee-e6b7c2f1b16b"
                                              ]
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/update/{uuid}")
    ResponseEntity<ShippingCompanyResponse> update(@PathVariable UUID uuid, @RequestBody ShippingCompanyRequest request);


    @Operation(summary = "Consulta todos as transportadoras.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "number": "Número",
                                              "district": "Bairro",
                                              "zipCode": "31640-200",
                                              "cnpj": "85 9 1111-2222",
                                              "subscription": "18.436.184/0001-00",
                                              "contact": "Número de inscrição",
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
                                                "type": "http://api/inventorycontrol/shipping_company/findAll",
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
                                                 "path": "http://api/inventorycontrol/shipping_company/findAll",
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
    ResponseEntity<List<ShippingCompanyResponse>> findAll();


    @Operation(summary = "Consulta a transportadora por ID.", description = """
            Consulta a transportadora passando o ID na URL.
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
                                              "shippingCompanyId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Nome do Fornecedor",
                                              "address": "Endereço",
                                              "number": "Número",
                                              "district": "Bairro",
                                              "zipCode": "31640-200",
                                              "cnpj": "85 9 1111-2222",
                                              "subscription": "18.436.184/0001-00",
                                              "contact": "Número de inscrição",
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
                    description = "Caso o ID enviado na URL não seja de nenhuma transportadora.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/shipping_company/findById/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Transportadora não está armazenada no sistema."
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
                                                "type": "http://api/inventorycontrol/shipping_company/findById/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/shipping_company/findById/{uuid}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })

    @GetMapping("/{uuid}")
    ResponseEntity<ShippingCompanyResponse> findById(@PathVariable UUID uuid);


    @Operation(summary = "Deleta uma transportadora por ID.", description = """
            Deleta a transportadora passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a transportadora seja deletada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Transportadora deletada com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma transportadora.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/shipping_company/delete/{uuid}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Tansportadora não armazenada no sistema."
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
                                                "type": "http://api/inventorycontrol/shipping_company/delete/{uuid}",
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
                                                 "path": "http://api/inventorycontrol/shipping_company/delete/{uuid}",
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
    ResponseEntity<MessageError> delete(@PathVariable String uuid);
}
