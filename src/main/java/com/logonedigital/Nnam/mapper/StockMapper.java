package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.stock.StockReqDTO;
import com.logonedigital.Nnam.dto.stock.StockResDTO;
import com.logonedigital.Nnam.entities.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockResDTO toDTO(Stock stock);
    Stock toEntity(StockReqDTO dto);
}
