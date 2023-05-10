package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.controller.DTO.LedDTO;
import co.edu.umanizales.leds.controller.DTO.ResponseDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/leds")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getLeds().getLeds(), null), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(@RequestBody LedDTO ledDTO) {
        Led newLed = new Led(ledDTO.getId(), false, null, null);
        listDEService.getLeds().add(newLed);

        return new ResponseEntity<>(new ResponseDTO(200, "El led ha sido agregado", null), HttpStatus.OK);
    }
    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStartLed(@RequestBody LedDTO ledDTO) {
        Led newLed = new Led(ledDTO.getId(), false, null, null);
        listDEService.getLeds().addToStart(newLed);
        return new ResponseEntity<>(new ResponseDTO(200, "El led ha sido agregado al inicio", null), HttpStatus.OK);
    }

    @GetMapping(path = "/resetleds")
    public ResponseEntity<ResponseDTO> resetLeds() {
        listDEService.getLeds().resetLeds();
        return new ResponseEntity<>(new ResponseDTO(200, "Los leds han sido reiniciado", null), HttpStatus.OK);
    }






}
