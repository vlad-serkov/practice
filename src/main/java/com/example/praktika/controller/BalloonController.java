package com.example.praktika.controller;

import com.example.praktika.domain.Balloon;
import com.example.praktika.exceptionhandler.exception.NotFoundException;
import com.example.praktika.service.BalloonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("balloons")
public class BalloonController {
    private BalloonService balloonService;


    @GetMapping
    public List<Balloon> findAllBalloons() {
        return balloonService.findAllBalloons();
    }


    @PostMapping
    public String save(@RequestBody Balloon balloon) {
        return balloonService.save(balloon);
    }

    @DeleteMapping("/{serial}")
    public String deleteById(@PathVariable int serial) throws NotFoundException {
        return balloonService.deleteById(serial);
    }
    @PutMapping("/{serial}")
    public String update(@PathVariable int serial, @RequestBody Balloon balloon ) throws NotFoundException {
        return balloonService.update(serial, balloon);
    }

    @GetMapping("/{serial}")
    public Balloon getBalloonBySerial(@PathVariable int serial) {
        return balloonService.getBalloonBySerial(serial);
    }

    @PutMapping("/{serial}/open-valve")
    public String openValve(@PathVariable int serial) throws NotFoundException {
        return balloonService.openValve(serial);
    }

    @PutMapping("/{serial}/set-substance")
    public String setSubstance(@PathVariable int serial, @RequestBody Map<String,String> map) throws NotFoundException {
        return balloonService.setSubstance(serial, map.get("substance"), Double.valueOf(map.get("pressure")));
    }


    }










