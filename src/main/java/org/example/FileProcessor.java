package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileProcessor {
    public static void main(String[] args) {
        try {
            List<Integer> input1 = readIntegersFromFile("input1.txt");
            List<Integer> input2 = readIntegersFromFile("input2.txt");

            List<Integer> merged = mergeContents(input1, input2);
            writeIntegersToFile("merged.txt", merged);

            List<Integer> common = findCommonIntegers(input1, input2);
            writeIntegersToFile("common.txt", common);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        }
    }

    public static List<Integer> readIntegersFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    public static List<Integer> mergeContents(List<Integer> input1, List<Integer> input2) {
        List<Integer> merged = new ArrayList<>(input1);
        merged.addAll(input2);
        return merged;
    }

    public static List<Integer> findCommonIntegers(List<Integer> input1, List<Integer> input2) {
        return input1.stream()
                .filter(input2::contains)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void writeIntegersToFile(String filename, List<Integer> integers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Integer integer : integers) {
                writer.write(integer.toString());
                writer.newLine();
            }
        }
    }
}
