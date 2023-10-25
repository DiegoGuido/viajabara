package com.mx.viajabara.Controller;

import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.ServiceImpl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping(value = "/")
        public List<Cliente> getAll(){
        return clienteService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(name = "id") Long id){
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null){
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Boolean> saveCliente(@RequestBody Cliente cliente){
        Boolean created = clienteService.saveOrUpdateClient(cliente);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable(name = "id") Long id){
        Boolean deleted = clienteService.deleteCliente(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping(value = "/login/{username}/{clave}")
    public ResponseEntity<Boolean> login(@PathVariable(name = "username") String username, @PathVariable(value = "clave") String clave){
        Boolean login = clienteService.login(username, clave);
        return ResponseEntity.ok(login);
    }
}
