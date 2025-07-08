package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.stock.StockReqDTO;
import com.logonedigital.Nnam.dto.stock.StockResDTO;
import com.logonedigital.Nnam.entities.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
//@Configuration
public interface StockMapper {
<<<<<<< HEAD
    @Mapping(source = "idStock", target = "idStock")
=======
    //Mapping(source = "id", target = "idStock")
>>>>>>> Michael
    StockResDTO toDTO(Stock stock);
    Stock toEntity(StockReqDTO dto);
    List<StockResDTO> toDtoList(List<Stock> stock);
}
