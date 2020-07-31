//package com.Permangultura.BackEnd.Controllers;
//
//import com.Permangultura.BackEnd.Modelos.Clima;
//import com.Permangultura.BackEnd.Repositorios.ClimaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.ResponseEntity.created;
//
//@RestController
//@RequestMapping("/clima")
//public class ClimaController {
//
//    @Autowired
//    private ClimaRepository repository;
//
//    @GetMapping
//    public ResponseEntity listarTodos() {
//        List<Clima> lista = repository.findAll();
//        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
//    }
//
//    @PostMapping
//    public ResponseEntity createGamer(@RequestBody Clima newClima){
//        this.repository.save(newClima);
//        return created(null).build();
//    }
//}
