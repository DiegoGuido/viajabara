package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.ServiceImpl.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping(value = "/")
        public ResponseEntity<List<Cliente>> getAll() throws Exception{
        try{
            List<Cliente> clientes = clienteService.getAll();
            if (!clientes.isEmpty()) {
                return new ResponseEntity<>(clientes, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new Exception();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(name = "id") Long id) throws Exception{
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null){
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> saveCliente(@RequestBody @Valid ClienteDTO cliente) throws Exception {
        Boolean created = clienteService.saveClient(cliente);
        if (created) {
            return new ResponseEntity<>("Registro exitoso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo realizar el registro de usuario.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<String> updateCliente(@PathVariable(name = "id") Long id, @RequestBody @Valid ClienteDTO clienteDTO) throws Exception{
        Boolean created = clienteService.updateClient(id, clienteDTO);
        if (created) {
            return new ResponseEntity<>("Actualizacion exitosa.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo realizar la actualizacion de usuario.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable(name = "id") Long id) throws Exception{
        Boolean deleted = clienteService.deleteCliente(id);
        return new ResponseEntity<>(deleted,HttpStatus.OK);
    }


}
