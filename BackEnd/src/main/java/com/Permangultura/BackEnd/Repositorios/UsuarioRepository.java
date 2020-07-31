package com.Permangultura.BackEnd.Repositorios;

import com.Permangultura.BackEnd.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findOneByEmailAndSenha(String email, String senha);
}
