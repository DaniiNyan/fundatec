package com.fundatec.lpii.pizzaria.unit;

import com.fundatec.lpii.pizzaria.api.v1.dto.MesaOutputDto;
import com.fundatec.lpii.pizzaria.entity.Mesa;
import com.fundatec.lpii.pizzaria.mapper.MesaMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MesaMapperTest {

    @Test
    public void deveMapearMesaParaMesaOutputDto() {
        Mesa mesa = new Mesa("A");
        MesaMapper mesaMapper = new MesaMapper();

        MesaOutputDto mesaOutputDto = mesaMapper.mapearMesaOutputDto(mesa);
        assertEquals(mesa.getId(), mesaOutputDto.getId());
        assertEquals(mesa.getIdentificador(), mesaOutputDto.getIdentificador());
    }
}
