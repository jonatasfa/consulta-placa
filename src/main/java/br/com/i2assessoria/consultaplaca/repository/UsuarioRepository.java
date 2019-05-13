package br.com.i2assessoria.consultaplaca.repository;

import br.com.i2assessoria.consultaplaca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
