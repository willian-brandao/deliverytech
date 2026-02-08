package com.deliverytech.delivery_api.controller;




//importação de bibliotecas do spring
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//importação da biblioteca do java
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

//importação de bibliotecas do swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//importação de bibliotecas do jakarta
import jakarta.validation.Valid;

//importacao de arquivos de outras pastas
import com.deliverytech.delivery_api.dto.requests.ClienteDTO;
import com.deliverytech.delivery_api.dto.responses.ClienteResponseDTO;
import com.deliverytech.delivery_api.service.ClienteService;







@RestController
@RequestMapping("/clientes")
public class ClienteController {

   private final ClienteService service;
   
   public ClienteController(ClienteService service){
        this.service = service;
   }

   @Operation(summary="Cadastrar novo cliente")
   @ApiResponses(
               value={
                    @ApiResponse(responseCode="201", description="Cliente cadastrado com sucesso."),
                    @ApiResponse(responseCode="400", description="Erro de Validação."),
               }

   )

   // o método invoca o repository salvar no banco de dados e caso seja bem sucedido, ele retorna o código de sucesso
   @PostMapping
   public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteDTO cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(cliente));
   }

   @Operation(summary="Listar Clientes Ativos")
   @ApiResponses(
             value={
               @ApiResponse
             }
   )
   @GetMapping("/listar")
   public List<ClienteResponseDTO> listar(){
        return service.listarAtivos();
   }
   
   @GetMapping("/{id}")
   public ClienteResponseDTO buscar(@PathVariable Long id){
        return service.buscarPorId(id);
   }



   @PutMapping("/{id}/toggle")
   public ResponseEntity<ClienteResponseDTO> toggleAtivo(@PathVariable long id){
     return ResponseEntity.ok(service.toggleAtivo(id));
   }

}


/* 
   @PutMapping("/{id}")
   public Cliente atualizar(@PathVariable long id, @RequestBody Cliente novosDados){
        return service.atualizar(id, novosDados);
   }


primeira versão, antes do DTO
// o método invoca o repository salvar no banco de dados e caso seja bem sucedido, ele retorna o código de sucesso
   @PostMapping
   public ResponseEntity<Cliente> cadastrar( @RequestBody Cliente cliente){
        return ResponseEntity.status(201).body(service.cadastrar(cliente));
   }

   @GetMapping("/listar")
   public List<Cliente> listar(){
        return service.listarAtivos();
   }
   
   @GetMapping("/{id}")
   public Cliente buscar(@PathVariable Long id){
        return service.buscarPorId(id);
   }

   @PutMapping("/{id}")
   public Cliente atualizar(@PathVariable long id, @RequestBody Cliente novosDados){
        return service.atualizar(id, novosDados);
   }

   @PutMapping("/desativar/{id}")
   public ResponseEntity<Void> desativar(@PathVariable long id){
        service.desativar(id);
        return ResponseEntity.noContent().build();

   }

*/