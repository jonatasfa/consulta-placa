package br.com.i2assessoria.consultaplaca.repository;

import br.com.i2assessoria.consultaplaca.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Veiculo findByPlaca(String placa);
}
