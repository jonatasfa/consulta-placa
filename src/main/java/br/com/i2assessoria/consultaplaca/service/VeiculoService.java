package br.com.i2assessoria.consultaplaca.service;

import br.com.i2assessoria.consultaplaca.entity.Log;
import br.com.i2assessoria.consultaplaca.entity.Veiculo;
import br.com.i2assessoria.consultaplaca.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final UsuarioService usuarioService;
    private final LogService logService;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository, UsuarioService usuarioService, LogService logService){
        this.veiculoRepository = veiculoRepository;
        this.usuarioService = usuarioService;
        this.logService = logService;
    }

    public Veiculo findByPlaca(String placa){

        Veiculo veiculo = this.veiculoRepository.findByPlaca(placa);

        if (veiculo != null){
            logService.saveLog(veiculo);
        }

        return veiculo;
    }

    public void removeAll(){
        this.veiculoRepository.deleteAll();
    }

    public void insertAll(List<Veiculo> veiculoList){
        this.veiculoRepository.saveAll(veiculoList);
    }
}
