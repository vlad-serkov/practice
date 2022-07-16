package com.vladserkov.practice.repository;

import com.vladserkov.practice.domain.Balloon;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class BalloonRepository {
    private final String PATH_TO_FILE = "src/main/resources/data.csv";

    public void save(Balloon balloon) {
        File csvOutputFile = new File(PATH_TO_FILE);
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile, true))) {
            pw.println(convertToCSV(balloon));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Balloon> findAll() {

        Reader in;
        try {
            in = new FileReader(PATH_TO_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Balloon> list = new ArrayList<>();
        Iterable<CSVRecord> records;
        try {
            records = CSVFormat.DEFAULT.parse(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (CSVRecord record : records) {
            list.add(new Balloon(
                    Integer.parseInt(record.get(0)),
                    Double.parseDouble(record.get(1)),
                    record.get(2),
                    Double.parseDouble(record.get(3))
            ));
        }
        return list;
    }

    public void deleteById(int serial) {
        final List<Balloon> listAfter = findAll()
                .stream()
                .filter(balloon -> balloon.getSerial() != serial)
                .collect(Collectors.toList());
        saveAllByOverwritingFile(listAfter);
    }

    public void update(Balloon balloon) {
        final List<Balloon> balloons = findAll();
        for (int i = 0; i < balloons.size(); i++) {
            if (balloons.get(i).getSerial() == balloon.getSerial()) {
                balloons.set(i, balloon);
                break;
            }
        }
        saveAllByOverwritingFile(balloons);
    }

    public Balloon getById(int serial) {
        final List<Balloon> all = findAll();
        for (Balloon balloon : all
        ) {
            if (balloon.getSerial() == serial) return balloon;
        }
        throw new IllegalStateException("НЕВОЗМОЖНО");
    }

    public boolean selectExistBalloonById(int id) {
        final List<Balloon> all = findAll();
        for (Balloon balloon : all
        ) {
            if (balloon.getSerial() == id) return true;
        }
        return false;
    }

    private void saveAllByOverwritingFile(List<Balloon> list) {
        File csvOutputFile = new File(PATH_TO_FILE);
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile, false))) {
            for (Balloon balloon : list
            ) {
                pw.println(convertToCSV(balloon));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertToCSV(Balloon balloon) {

        String[] data = new String[]{
                String.valueOf(balloon.getSerial()),
                String.valueOf(balloon.getCapacity()),
                balloon.getSubstance(),
                String.valueOf(balloon.getPressure())
        };

        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
