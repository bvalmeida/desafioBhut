package br.com.bhut.model.service;

import br.com.bhut.model.dto.CarsResponseDto;
import br.com.bhut.model.dto.LogsDto;
import br.com.bhut.model.entity.LogsBhut;
import br.com.bhut.model.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LogsService implements LogsServ{

    private static final String URL = "http://api-test.bhut.com.br:3000/api/cars";

    @Autowired
    private LogsRepository logsRepository;


    @Override
    public List<CarsResponseDto> findCars() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CarsResponseDto[]> response = restTemplate.getForEntity(URL, CarsResponseDto[].class);
        CarsResponseDto[] carsResponseDtos = response.getBody();
        List<CarsResponseDto> carsResponseDtoList = Arrays.asList(carsResponseDtos);
        return carsResponseDtoList;
    }

    @Override
    public void saveCar(CarsResponseDto carsResponseDto) {

        LocalDateTime dateTime = LocalDateTime.now();

        WebClient webClient = WebClient.create();

        webClient
                .post()
                .uri(URL)
                .body(BodyInserters.fromValue(carsResponseDto))
                .retrieve()
                .bodyToMono(CarsResponseDto.class);

        LogsBhut logsBhut = new LogsBhut();
        logsBhut.setDataHora(dateTime);
        logsBhut.setCarId(carsResponseDto.get_id());

        this.logsRepository.save(logsBhut);
    }

    @Override
    public List<LogsDto> findLogs() {

        List<LogsDto> logsDtosRetorno = new ArrayList<>();
        List<LogsBhut> logsBhutList = this.logsRepository.findAll();

        for(LogsBhut logsBhut : logsBhutList){
            LogsDto logsDto = new LogsDto();
            logsDto.setId(logsBhut.getId());
            logsDto.setCarId(logsBhut.getCarId());
            logsDto.setDataHora(logsBhut.getDataHora());
            logsDtosRetorno.add(logsDto);
        }

        return logsDtosRetorno;
    }
}
