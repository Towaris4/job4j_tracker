package ru.job4j.queue;

import java.util.Deque;

public class ReconstructPhrase {

    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        Deque<Character> evenElements = this.evenElements;
        StringBuilder stringBuilder = new StringBuilder();
        int size = evenElements.size();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                stringBuilder.append(evenElements.poll());
            } else {
                evenElements.poll();
            }
        }
        return stringBuilder.toString();
    }

    private String getDescendingElements() {
        Deque<Character> descendingElements = this.descendingElements;
        StringBuilder stringBuilder = new StringBuilder();
        int size = descendingElements.size();
        for (int i = 0; i < size; i++) {
                stringBuilder.append(descendingElements.pollLast());
            }
        return stringBuilder.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}