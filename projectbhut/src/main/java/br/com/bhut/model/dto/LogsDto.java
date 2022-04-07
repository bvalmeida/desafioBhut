package br.com.bhut.model.dto;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class LogsDto {

    private Long id;
    private LocalDateTime dataHora;
    private String carId;
}
