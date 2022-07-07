package com.example.praktika.service;

import com.example.praktika.domain.Balloon;
import com.example.praktika.exceptionhandler.exception.NotFoundException;
import com.example.praktika.repository.BalloonRepository;
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
    void openValve() throws NotFoundException {
        balloonService.openValve(2);
    }

    @Test
    void setSubstance() throws NotFoundException {
        balloonService.setSubstance(2,"Водород", 56.6);
    }

    @Test
    void save() {
        balloonService.save(new Balloon(5,47,"Водород", 56.6));
    }

    @Test
    void findAllBalloons() {
        final List<Balloon> balloons = balloonService.findAllBalloons();
        balloons.forEach(System.out::println);

    }

    @Test
    void deleteById() throws NotFoundException {
        balloonService.deleteById(51345);
    }

    @Test
    void update() throws NotFoundException {
        balloonService.update(2,new Balloon(2,4,"Водород", 566.6));
    }

    @Test
    void getBalloonBySerial() {
        final Balloon balloon = balloonService.getBalloonBySerial(2);
        System.out.println(balloon);
    }
}