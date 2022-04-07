package br.com.bhut.model.entity;

import br.com.bhut.model.dto.LogsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogsBhut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "data_hora")
    private LocalDateTime dataHora;

    @JoinColumn(name = "car_id")
    private String carId;

    public static LogsBhut converteDtoToEntity(LogsDto logsDto){
        return LogsBhut.builder()
                .dataHora(logsDto.getDataHora())
                .carId(logsDto.getCarId())
                .build();
    }

    public static LogsDto converteEntityToDto(LogsBhut logsBhut){
        return LogsDto.builder()
                .id(logsBhut.getId())
                .dataHora(logsBhut.getDataHora())
                .carId(logsBhut.getCarId())
                .build();
    }

}
