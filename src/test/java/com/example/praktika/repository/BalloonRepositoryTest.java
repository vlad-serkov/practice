package com.example.praktika.repository;

import com.example.praktika.domain.Balloon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class BalloonRepositoryTest {

    @Test
    void save() throws IOException {
        BalloonRepository balloonRepository = new BalloonRepository();
        balloonRepository.save(new Balloon(2,24,"Alina",90));
    }

    @Test
    void findAll() throws IOException {
        BalloonRepository balloonRepository = new BalloonRepository();
        final List<Balloon> all = balloonRepository.findAll();
        for (Balloon balloon: all
             ) {
            System.out.println(balloon);
        }
    }
    @Test
    void deleteById() {
        BalloonRepository balloonRepository = new BalloonRepository();
         balloonRepository.deleteById(1);
    }
    @Test
    void update() {
        BalloonRepository balloonRepository = new BalloonRepository();

        balloonRepository.update(new Balloon(2,21,"Vlad",90));
    }
    @Test
    void getById() {
        BalloonRepository balloonRepository = new BalloonRepository();
        final Balloon balloon = balloonRepository.getById(2);
        System.out.println(balloon);
    }

    @Test
    void select() {
        BalloonRepository balloonRepository = new BalloonRepository();
        final boolean b = balloonRepository.selectExistBalloonById(2);
        System.out.println(b);
    }

    @Test
    void convertToCSV() {
    }


    @Test
    void escapeSpecialCharacters() {
    }
}