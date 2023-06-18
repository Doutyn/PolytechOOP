package com.example.finaltask.lab4.src;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translate {
    private Map<String, String> dataset;

    public Translate() {
        this.dataset = new HashMap<String, String>();
    }

    public int checker(final String key) {
        int returned = -1;
        final String temp = key;
        List<String> checker = List.of(temp.split(" "));
        for (int i = 0; i < checker.size(); i++) {
            returned++;
        }
        return returned;
    }
    public String translate(final String key) {
        String value = key.toLowerCase().trim();
        if (this.dataset.containsKey(value)) {
            return this.dataset.get(value);
        } else {
            return key;
        }
    }

    public String translateAll(final String text) {
        StringBuilder result = new StringBuilder();
        List<String> keys = List.of(text.split(" "));
        boolean add = false;
        for (int i = 0; i < keys.size(); i++) {
            for (int j = keys.size(); j > i; j--) {
                String postfix = String.join(" ", keys.subList(i, j));
                String t = translate(postfix);
                if (!t.equals(postfix)) {
                    result.append(t + " ");
                    i += checker(postfix);
                    add = true;
                    break;
                }
            }
            if (add) {
                add = false;
            } else {
                result.append(keys.get(i) + " ");
            }
        }
        return result.toString();
    }

    public void readDataset(BufferedReader reader) throws FileReadException {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                this.readLine(line.toLowerCase().trim());
            }
        } catch (Exception e) {
            throw new FileReadException(e.getMessage());
        }
    }

    private void readLine(final String line) throws InvalidFileFormatException {
        String[] sline = line.split(" \\| ");
        if (sline.length != 2) {
            throw new InvalidFileFormatException("Invalid format. Rewrite!");
        }
        String key = sline[0];
        String value = sline[1];
        if (key == null || value == null || key.isEmpty() || value.isEmpty()) {
            throw new InvalidFileFormatException("Invalid format. Rewrite!");
        }
        this.dataset.put(key.toLowerCase().trim(), value.toLowerCase().trim());
    }
}
