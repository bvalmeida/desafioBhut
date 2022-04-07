package br.com.bhut.controller;

import br.com.bhut.model.dto.CarsResponseDto;
import br.com.bhut.model.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class LogsController {

    @Autowired
    private LogsService logsService;

    @GetMapping(value = "listCars")
    public @ResponseBody ResponseEntity<List<CarsResponseDto>> findCars(){
        List<CarsResponseDto> carsResponseDto = logsService.findCars();
        return new ResponseEntity<List<CarsResponseDto>>(carsResponseDto, HttpStatus.OK);
    }


}
