package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int count = 0;
        double score = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
                count++;
            }
        }
        return score / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> scoreByPupil = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            scoreByPupil.add(new Label(pupil.name(), score / pupils.size()));
        }
        return scoreByPupil;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> scoreBySubject = new LinkedHashMap<>();
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                int scoreSubject = scoreBySubject.getOrDefault(subject.name(), 0);
                String nameSubject = subject.name();
                scoreSubject += subject.score();
                scoreBySubject.put(nameSubject, scoreSubject);
            }
        }
        for (String nameSubject : scoreBySubject.keySet()) {
            int score = scoreBySubject.get(nameSubject) /  pupils.size();
            result.add(new Label(nameSubject, score));
        }
        return result;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> scoreByPupil = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            scoreByPupil.add(new Label(pupil.name(), score));
        }
        scoreByPupil.sort(Comparator.naturalOrder());
        return scoreByPupil.get(scoreByPupil.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> scoreBySubject = new LinkedHashMap<>();
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                int scoreSubject = scoreBySubject.getOrDefault(subject.name(), 0);
                String nameSubject = subject.name();
                scoreSubject += subject.score();
                scoreBySubject.put(nameSubject, scoreSubject);
            }
        }
        for (String nameSubject : scoreBySubject.keySet()) {
            int score = scoreBySubject.get(nameSubject);
            result.add(new Label(nameSubject, score));
        }
        result.sort(Comparator.naturalOrder());
        return result.get(result.size() - 1);
    }
}