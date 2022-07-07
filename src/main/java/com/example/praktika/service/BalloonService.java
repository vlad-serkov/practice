package com.example.praktika.service;

import com.example.praktika.domain.Balloon;
import com.example.praktika.exceptionhandler.exception.NotFoundException;
import com.example.praktika.repository.BalloonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BalloonService {
    private BalloonRepository balloonRepository;

    public String openValve(int serial) throws NotFoundException {
        if (!balloonRepository.selectExistBalloonById(serial)) {
            throw new NotFoundException(String.format("Balloon with serial: %s doesn't exist", serial));
        }
        Balloon balloon = balloonRepository.getById(serial);
        balloon.setPressure(0);
        balloon.setSubstance("Oxygen");
        balloonRepository.update(balloon);
        return String.format("Balloon with serial: %s now has Pressure: 0 , Substance: Oxygen", serial);
    }

    public String setSubstance(int serial, String substance, double pressure) throws NotFoundException {
        if (!balloonRepository.selectExistBalloonById(serial)) {
            throw new NotFoundException(String.format("Balloon with serial: %s doesn't exist", serial));
        }
        final Balloon balloon = balloonRepository.getById(serial);
        if(balloon.getPressure()!=0) {
            throw new NotFoundException(String.format("Balloon with serial: %s is already has Substance: %s with Pressure: %s", serial, balloon.getSubstance(), balloon.getPressure()));
        }

        balloon.setSubstance(substance);
        balloon.setPressure(pressure);
        balloonRepository.update(balloon);
        return String.format("Balloon with serial: %s now has Pressure: %s , Substance: %s", serial, balloon.getSubstance(), balloon.getPressure());
    }


    public String save(Balloon balloon) {
        if (balloonRepository.selectExistBalloonById(balloon.getSerial())) {
            throw new IllegalStateException(String.format("Balloon with serial: %s is already exist", balloon.getSerial()));
        }
        balloonRepository.save(balloon);
        return String.format("Balloon with serial: %s created", balloon.getSerial());
    }

    public List<Balloon> findAllBalloons() {
        return balloonRepository.findAll();
    }

    public String deleteById(int serial) throws NotFoundException {
        if (!balloonRepository.selectExistBalloonById(serial)) {
            // throw new IllegalStateException(String.format("Balloon with serial: %s doesn't exist", serial));
            throw new NotFoundException(String.format("Balloon with serial: %s doesn't exist", serial));
        }
        balloonRepository.deleteById(serial);
        return String.format("Balloon with serial: %s deleted", serial);
    }

    public String update(int serial,Balloon balloon) throws NotFoundException {
        if (!balloonRepository.selectExistBalloonById(serial)) {
            throw new NotFoundException(String.format("Balloon with serial: %s doesn't exist", serial));
        }
        if (balloon.getSerial()!= serial) {
            throw new NotFoundException(String.format("You can't change serial", balloon.getSerial()));
        }
        balloonRepository.update(balloon);
        return String.format("Balloon with serial: %s updated", balloon.getSerial());
    }

    public Balloon getBalloonBySerial(int serial) {
        if (!balloonRepository.selectExistBalloonById(serial)) {
            throw new IllegalStateException(String.format("Balloon with serial: %s doesn't exist", serial));
        }
        return balloonRepository.getById(serial);
    }


}
