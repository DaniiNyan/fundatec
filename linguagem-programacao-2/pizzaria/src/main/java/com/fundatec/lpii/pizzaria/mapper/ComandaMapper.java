package com.fundatec.lpii.pizzaria.mapper;

import com.fundatec.lpii.pizzaria.api.v1.dto.ComandaInputDto;
import com.fundatec.lpii.pizzaria.api.v1.dto.ComandaOutputDto;
import com.fundatec.lpii.pizzaria.entity.Comanda;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComandaMapper {

    public Comanda mapearComanda(ComandaInputDto comandaInputDto) {
        return new Comanda(comandaInputDto.getCliente());
    }

    public ComandaOutputDto mapearComandaOutputDto(Comanda comanda) {
        return new ComandaOutputDto(comanda.getCliente());
    }

    public List<ComandaOutputDto> mapearComandaOutputDto(List<Comanda> comandas) {
        List<ComandaOutputDto> comandasOutputDto = new ArrayList<>();
        for (Comanda comanda : comandas) {
            comandasOutputDto.add(mapearComandaOutputDto(comanda));
        }
        return comandasOutputDto;
    }
}
