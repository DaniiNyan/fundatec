package com.fundatec.lpii.pizzaria.mapper;

import com.fundatec.lpii.pizzaria.api.v1.dto.MesaInputDto;
import com.fundatec.lpii.pizzaria.api.v1.dto.MesaOutputDto;
import com.fundatec.lpii.pizzaria.entity.Mesa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MesaMapper {

    public MesaOutputDto mapearMesaOutputDto(Mesa mesa) {
        return new MesaOutputDto(mesa.getId(), mesa.getIdentificador());
    }

    public List<MesaOutputDto> mapearMesaOutputDto(List<Mesa> mesas) {
        List<MesaOutputDto> listaMesas = new ArrayList<>();
        for (Mesa mesa : mesas) {
            listaMesas.add(mapearMesaOutputDto(mesa));
        }
        return listaMesas;
    }

    public Mesa mapearMesa(MesaInputDto mesaInputDto) {
        return new Mesa(mesaInputDto.getIdentificador());
    }
}
