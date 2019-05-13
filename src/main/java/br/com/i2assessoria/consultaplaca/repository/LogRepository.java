package br.com.i2assessoria.consultaplaca.repository;

import br.com.i2assessoria.consultaplaca.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
