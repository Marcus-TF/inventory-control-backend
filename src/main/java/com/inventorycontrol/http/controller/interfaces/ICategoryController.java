package com.inventorycontrol.http.controller.interfaces;

import com.inventorycontrol.http.dto.message.MessageError;
import com.inventorycontrol.http.dto.request.CategoryRequest;
import com.inventorycontrol.http.dto.response.CategoryResponse;
import com.inventorycontrol.model.CategoryModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Tag(name = "Categorias")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/category")
@Validated
public interface ICategoryController {

    @Operation(summary = "Salva uma nova categoria.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso a categoria seja armazenada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "categoryName": "Salgado"
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
                                                "type": "http://api/inventorycontrol/category",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a categoria já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:41:33",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/category",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Categoria já está armazenada no sistema."
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
                                                 "path": "http://api/inventorycontrol/category",
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
                    Dados para salvar uma nova categoria.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "categoryName": "Salgado"
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryRequest categoryRequest);

    @Operation(summary = "Atualiza a categoria por ID.", description = """
            Atualiza a categoria passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a categoria seja alterada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "categoryId": "3047caa5-621e-4c42-89ee-e6b7c2f1b16b",
                                              "categoryName": "Salgados"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma categoria.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/category/{categoryId}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Categoria não está armazenada no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a categoria já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 12:42:53",
                                                 "status": 400,
                                                 "type": "http://api/inventorycontrol/category/{categoryId}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe uma categoria armazenada com esse código."
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
                                                "type": "http://api/inventorycontrol/category/{categoryId}",
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
                                                 "path": "http://api/inventorycontrol/category/{categoryId}",
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
                    Dados para atualizar uma categoria.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "categoryName": "Salgados"
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryResponse> update(@RequestBody @Valid CategoryRequest categoryRequest, @PathVariable UUID categoryId);


    @Operation(summary = "Consulta todas as categorias.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "categoryId":"c04338f2-a08b-49e0-a546-3f51d2aad2cb",
                                              "categoryName": "Salgados",
                                              "_links": {
                                              "self": {
                                                  "href": "http://api/inventorycontrol/category/c04338f2-a08b-49e0-a546-3f51d2aad2cb"
                                              }
                                            }
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
                                                "type": "http://api/inventorycontrol/category/findAll",
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
                                                 "path": "http://api/inventorycontrol/category/findAll",
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
    ResponseEntity<CollectionModel<CategoryModel>> findAll();


    @Operation(summary = "Consulta a categoria por ID.", description = """
            Consulta a categoria passando o ID na URL.
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
                                          "categoryId": "c04338f2-a08b-49e0-a546-3f51d2aad2cb",
                                          "categoryName": "Salgados",
                                          "_links": {
                                              "self": {
                                                  "href": "http://api/inventorycontrol/category/c04338f2-a08b-49e0-a546-3f51d2aad2cb"
                                              }
                                          }
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma categoria.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/category/{categoryId}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Categoria não está salva no sistema."
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
                                                "type": "http://api/inventorycontrol/category/{categoryId}",
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
                                                 "path": "http://api/inventorycontrol/category/{categoryId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryResponse> findById(@PathVariable UUID categoryId);


    @Operation(summary = "Deleta uma categoria por ID.", description = """
            Deleta a categoria passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso o categoria seja deletada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Categoria deletada com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma categoria.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "23-05-2022 11:56:30",
                                                 "status": 404,
                                                 "type": "http://api/inventorycontrol/category/{categoryId}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Categoria não armazenada no sistema."
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
                                                "type": "http://api/inventorycontrol/category/{categoryId}",
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
                                                 "path": "http://api/inventorycontrol/category/{categoryId}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping(path = "/{categoryId}")
    ResponseEntity<MessageError> delete(@PathVariable UUID categoryId);
}
