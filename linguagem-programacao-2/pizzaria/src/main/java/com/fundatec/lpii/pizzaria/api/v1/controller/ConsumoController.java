package com.fundatec.lpii.pizzaria.api.v1.controller;

import com.fundatec.lpii.pizzaria.entity.Consumo;
import com.fundatec.lpii.pizzaria.service.ConsumoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ConsumoController {

    private ConsumoService consumoService;

    public ConsumoController(ConsumoService consumoService) {
        this.consumoService = consumoService;
    }

    @GetMapping("/v1/consumos")
    @ApiOperation(value = "Lista todos os consumos",
            notes = "Lista todos os consumos do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consumos listados com sucesso", response = Consumo.class),
    })
    public ResponseEntity<?> getConsumos() {
        return ResponseEntity.status(HttpStatus.OK).body(consumoService.getAll());
    }

    @PostMapping("/v1/consumos")
    @ApiOperation(value = "Incluir consumo",
            notes = "Inclui um consumo no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consumo incluído com sucesso", response = Consumo.class),
    })
    public ResponseEntity<?> incluirConsumo(@RequestBody Consumo consumo) {
        consumoService.salvar(consumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumo);
    }
}
