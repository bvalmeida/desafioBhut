package br.com.bhut.controller;

import br.com.bhut.model.dto.CarsResponseDto;
import br.com.bhut.model.dto.LogsDto;
import br.com.bhut.model.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class LogsController {

    @Autowired
    private LogsService logsService;

    @GetMapping(value = "listCars")
    public @ResponseBody ResponseEntity<List<CarsResponseDto>> findCars(){
        List<CarsResponseDto> carsResponseDto = this.logsService.findCars();
        return new ResponseEntity<List<CarsResponseDto>>(carsResponseDto, HttpStatus.OK);
    }

    @GetMapping(value = "logs")
    public @ResponseBody ResponseEntity<List<LogsDto>> findLogs(){
        List<LogsDto> logsDtoList = this.logsService.findLogs();
        return new ResponseEntity<List<LogsDto>>(logsDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "createCar")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveCar(@RequestBody CarsResponseDto carsResponseDto){
        this.logsService.saveCar(carsResponseDto);
    }


}
