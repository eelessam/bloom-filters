package com.eelessam.bloomfilters;

import java.util.List;

class BloomFilter {

    private int[] arrayBits;

    BloomFilter(List<String> stringsToLoadInToFilter) {
        createBitmap(stringsToLoadInToFilter.size());
        loadInToFilter(stringsToLoadInToFilter);
    }

    boolean inDict(String word) {
        int hashOneValue = hashOne(word);
        int hashTwoValue = hashTwo(word);
        return arrayBits[hashOneValue] == 1 && arrayBits[hashTwoValue] == 1;
    }

    private void createBitmap(int sizeOfElementsToStore) {
        arrayBits = new int[sizeOfElementsToStore * 10];
        for (int i = 0; i < arrayBits.length; i++) {
            arrayBits[0] = 0;
        }
    }

    private void loadInToFilter(List<String> words) {
        words.forEach(this::addToBitmap);
    }

    private void addToBitmap(String word) {
        arrayBits[hashOne(word)] = 1;
        arrayBits[hashTwo(word)] = 1;
    }

    private int hashOne(String word) {
        return Math.abs(word.hashCode()) % arrayBits.length;
    }

    private int hashTwo(String word) {
        return Math.abs(word.hashCode() * 2) % arrayBits.length;
    }

}
