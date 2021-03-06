package com.mikael.cursomc.resources;

import java.net.URI;

import javax.websocket.server.PathParam;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {
    
    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){
        Category obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody Category categoria){
        categoria = service.adicionar(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(categoria.getId()).toUri(); //Pega a URI do novo recurso que foi inserido no POST
        return ResponseEntity.created(uri).build(); //Retorna o HTTP Status 201 retornando a nova URI que passamos na linha anterior.
    }
}