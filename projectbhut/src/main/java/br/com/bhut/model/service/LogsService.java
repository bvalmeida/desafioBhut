package br.com.bhut.model.service;

import br.com.bhut.model.dto.CarsResponseDto;
import br.com.bhut.model.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
