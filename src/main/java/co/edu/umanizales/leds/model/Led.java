package co.edu.umanizales.leds.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Led {
    int id;
    boolean state;
    LocalTime dateOn;
    LocalTime dateOff;
}
