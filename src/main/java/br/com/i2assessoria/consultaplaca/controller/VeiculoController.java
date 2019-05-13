package br.com.i2assessoria.consultaplaca.controller;

import br.com.i2assessoria.consultaplaca.entity.Veiculo;
import br.com.i2assessoria.consultaplaca.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("v1")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("consulta/{placa}")
    public Veiculo findPlaca(@PathVariable("placa") String placa, HttpServletRequest request) {

        return veiculoService.findByPlaca(placa);
    }
}
