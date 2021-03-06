package br.com.bhut.model.service;

import br.com.bhut.model.dto.CarsResponseDto;
import br.com.bhut.model.dto.LogsDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LogsServ {

    List<CarsResponseDto> findCars();
    Mono<CarsResponseDto> saveCar(CarsResponseDto carsResponseDto);
    List<LogsDto> findLogs();

}
