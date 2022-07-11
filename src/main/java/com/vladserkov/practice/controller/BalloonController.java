package com.vladserkov.practice.controller;


import com.vladserkov.practice.domain.Balloon;
import com.vladserkov.practice.exceptionhandler.exception.SerialNotFoundException;
import com.vladserkov.practice.service.BalloonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("balloons")
public class BalloonController  {
    private BalloonService balloonService;


    @GetMapping
    public List<Balloon> findAllBalloons() {
        return balloonService.findAllBalloons();
    }


    @PostMapping
    public String save(@RequestBody Balloon balloon) throws SerialNotFoundException {
        return balloonService.save(balloon);
    }

    @DeleteMapping("/{serial}")
    public String deleteById(@PathVariable int serial) throws SerialNotFoundException {
        return balloonService.deleteById(serial);
    }
    @PutMapping("/{serial}")
    public String update(@PathVariable int serial, @RequestBody Balloon balloon ) throws SerialNotFoundException {
        return balloonService.update(serial, balloon);
    }

    @GetMapping("/{serial}")
    public Balloon getBalloonBySerial(@PathVariable int serial) {
        return balloonService.getBalloonBySerial(serial);
    }

    @PutMapping("/{serial}/open-valve")
    public String openValve(@PathVariable int serial) throws SerialNotFoundException {
        return balloonService.openValve(serial);
    }

    @PutMapping("/{serial}/set-substance")
    public String setSubstance(@PathVariable int serial, @RequestBody Map<String,String> map) throws SerialNotFoundException {
        return balloonService.setSubstance(serial, map.get("substance"), Double.parseDouble(map.get("pressure")));
    }

    @GetMapping("/information")
    public String getInformation() {
        return String.format("Проект выполнил Вадислав Серков группа УВА-211. Количество сущностей: %s", balloonService.getBalloonsCount());
    }


    }










