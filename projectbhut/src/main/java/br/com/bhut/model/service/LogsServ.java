package br.com.bhut.model.service;

import br.com.bhut.model.dto.CarsResponseDto;

import java.util.List;

public interface LogsServ {

    List<CarsResponseDto> findCars();

}
