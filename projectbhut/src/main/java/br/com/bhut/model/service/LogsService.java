package br.com.bhut.model.service;

import br.com.bhut.model.dto.CarsResponseDto;
import br.com.bhut.model.dto.LogsDto;
import br.com.bhut.model.entity.LogsBhut;
import br.com.bhut.model.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.Duration;
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
    public Mono<CarsResponseDto> saveCar(CarsResponseDto carsResponseDto) {

        LocalDateTime dateTime = LocalDateTime.now();

        WebClient webClient = WebClient.create();

        Mono<CarsResponseDto> webClientRetorn = webClient.post()
                .uri(URL)
                .body(Mono.just(carsResponseDto), CarsResponseDto.class)
                .retrieve()
                .bodyToMono(CarsResponseDto.class)
                .timeout(Duration.ofMillis(10_000));

        LogsBhut logsBhut = new LogsBhut();
        logsBhut.setDataHora(dateTime);
        this.logsRepository.save(logsBhut);

        return webClientRetorn;
    }

    @Override
    public List<LogsDto> findLogs() {

        List<LogsDto> logsDtosRetorno = new ArrayList<>();
        List<LogsBhut> logsBhutList = this.logsRepository.findAll();

        for(LogsBhut logsBhut : logsBhutList){
            LogsDto logsDto = new LogsDto();
            logsDto.setId(logsBhut.getId());
            logsDto.setDataHora(logsBhut.getDataHora());
            logsDtosRetorno.add(logsDto);
        }

        return logsDtosRetorno;
    }
}
