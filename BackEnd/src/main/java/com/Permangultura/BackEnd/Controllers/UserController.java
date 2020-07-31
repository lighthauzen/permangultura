package com.Permangultura.BackEnd.Controllers;

import com.Permangultura.BackEnd.Modelos.Usuario;
import com.Permangultura.BackEnd.Repositorios.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private boolean logado = false;

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Usuario> lista = usuarioRepository.findAll();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{email}/{senha}")
    public ResponseEntity getUsuarioESenha(@PathVariable("email") String email, @PathVariable("senha") String senha ){
        List usuario;
        usuario = usuarioRepository.findOneByEmailAndSenha(email, senha);
        if(!logado) {
            if (usuario.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                logado = true;
                return ResponseEntity.ok("login realizado com sucesso");
            }
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping("/logoff")
    public ResponseEntity logoff() {
        if(logado) {
            logado = false;
            return ResponseEntity.ok("Você foi deslogado");
        } else {
            return ResponseEntity.ok("Não existe usuario logado");
        }
    }

    @PostMapping
    public ResponseEntity createGamer(@RequestBody Usuario newUsuario){
        this.usuarioRepository.save(newUsuario);
        return created(null).build();
    }


    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
