package com.fundatec.lpii.pizzaria.api.v1.controller;

import com.fundatec.lpii.pizzaria.api.v1.dto.ComandaInputDto;
import com.fundatec.lpii.pizzaria.api.v1.dto.ComandaOutputDto;
import com.fundatec.lpii.pizzaria.api.v1.dto.MesaInputDto;
import com.fundatec.lpii.pizzaria.api.v1.dto.MesaOutputDto;
import com.fundatec.lpii.pizzaria.entity.Comanda;
import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.mapper.ComandaMapper;
import com.fundatec.lpii.pizzaria.mapper.MesaMapper;
import com.fundatec.lpii.pizzaria.service.ComandaService;
import com.fundatec.lpii.pizzaria.service.MesaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MesaController {

    private MesaService mesaService;
    private ComandaService comandaService;
    private MesaMapper mesaMapper;
    private ComandaMapper comandaMapper;

    public MesaController(MesaService mesaService, ComandaService comandaService, MesaMapper mesaMapper, ComandaMapper comandaMapper) {
        this.mesaService = mesaService;
        this.comandaService = comandaService;
        this.mesaMapper = mesaMapper;
        this.comandaMapper = comandaMapper;
    }

    @GetMapping("/v1/mesas")
    @ApiOperation(value = "Listar todas as mesas",
            notes = "Lista todas as mesas cadastradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mesas retornadas com sucesso", response = MesaOutputDto.class),
    })
    public ResponseEntity<?> getMesas() {
        List<Mesa> listaMesas = mesaService.getAll();
        List<MesaOutputDto> listaMesasOutputDto = mesaMapper.mapearMesaOutputDto(listaMesas);
        return ResponseEntity.status(HttpStatus.OK).body(listaMesasOutputDto);
    }


    @PostMapping("/v1/mesas")
    @ApiOperation(value = "Incluir uma mesa",
            notes = "Inclui uma mesa no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mesas incluída com sucesso", response = MesaOutputDto.class),
    })
    public ResponseEntity<?> incluirMesa(@RequestBody MesaInputDto mesaInputDto) {
        Mesa mesa = mesaMapper.mapearMesa(mesaInputDto);
        mesa = mesaService.adiciona(mesa);
        MesaOutputDto mesaOutputDto = mesaMapper.mapearMesaOutputDto(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaOutputDto);
    }


    @GetMapping("/v1/mesas/{id}")
    @ApiOperation(value = "Lista todas as comandas da mesas",
            notes = "Lista todas as comandas de uma mesa.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comandas listadas com sucesso", response = ComandaOutputDto.class),
    })
    public ResponseEntity<?> listarComandasDeUmaMesa(@PathVariable(value = "id") Long idMesa) {
        List<Comanda> listaComandas = mesaService.encontra(idMesa).getComandas();
        List<ComandaOutputDto> listaComandasOutpuDto = comandaMapper.mapearComandaOutputDto(listaComandas);
        return ResponseEntity.status(HttpStatus.OK).body(listaComandasOutpuDto);
    }

    @PatchMapping("/v1/mesas/{id}")
    @ApiOperation(value = "Incluir comanda em uma mesa",
            notes = "Inclui uma comanda em uma mesa do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comanda incluída com sucesso", response = MesaOutputDto.class),
    })
    public ResponseEntity<?> incluirComandaEmUmaMesa(@PathVariable(value = "id") Long idMesa, @RequestBody ComandaInputDto comandaInputDto) {
        Comanda comanda = comandaMapper.mapearComanda(comandaInputDto);
        comanda = mesaService.incluiComanda(idMesa, comanda);
        ComandaOutputDto comandaOutputDto = comandaMapper.mapearComandaOutputDto(comanda);

        return ResponseEntity.status(HttpStatus.OK.value()).body(comandaOutputDto);
    }
}
