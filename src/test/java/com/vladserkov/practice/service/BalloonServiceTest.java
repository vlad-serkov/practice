package com.vladserkov.practice.service;

import com.vladserkov.practice.domain.Balloon;
import com.vladserkov.practice.exceptionhandler.exception.SerialNotFoundException;
import com.vladserkov.practice.repository.BalloonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class BalloonServiceTest {
    BalloonService balloonService;

    @BeforeEach
    void init() {
        balloonService = new BalloonService(new BalloonRepository());
    }

    @Test
    void openValve() throws SerialNotFoundException {
        balloonService.openValve(2);
    }

    @Test
    void setSubstance() throws SerialNotFoundException {
        balloonService.setSubstance(2,"Водород", 56.6);
    }

    @Test
    void save() throws SerialNotFoundException {
        balloonService.save(new Balloon(5,47,"Водород", 56.6));
    }

    @Test
    void findAllBalloons() {
        final List<Balloon> balloons = balloonService.findAllBalloons();
        balloons.forEach(System.out::println);

    }

    @Test
    void deleteById() throws SerialNotFoundException {
        balloonService.deleteById(51345);
    }

    @Test
    void update() throws SerialNotFoundException {
        balloonService.update(2,new Balloon(2,4,"Водород", 566.6));
    }

    @Test
    void getBalloonBySerial() throws SerialNotFoundException {
        final Balloon balloon = balloonService.getBalloonBySerial(2);
        System.out.println(balloon);
    }
}