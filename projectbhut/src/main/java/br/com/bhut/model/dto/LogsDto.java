package br.com.bhut.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LogsDto {

    private Long id;
    private LocalDateTime dataHora;
    private String carId;
}
