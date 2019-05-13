package br.com.i2assessoria.consultaplaca.service;

import br.com.i2assessoria.consultaplaca.entity.Log;
import br.com.i2assessoria.consultaplaca.entity.Veiculo;
import br.com.i2assessoria.consultaplaca.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public LogService(LogRepository logRepository, UsuarioService usuarioService){
        this.logRepository = logRepository;
        this.usuarioService = usuarioService;
    }

    public Log save(Log log){
        return this.logRepository.save(log);
    }

    public void saveLog(Veiculo veiculo){
        Log newLog = new Log();
        newLog.setUsuario(usuarioService.getUsuarioLogado());
        newLog.setVeiculo(veiculo);
        newLog.setDt_consulta(new Date());
        this.save(newLog);
    }
}
