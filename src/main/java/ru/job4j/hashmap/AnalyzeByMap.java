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
        pupils.forEach(pupil -> pupil.subjects().forEach(subject ->
                scoreBySubject.merge(subject.name(), subject.score(), Integer::sum)));
        for (String nameSubject : scoreBySubject.keySet()) {
            int score = scoreBySubject.get(nameSubject) /  pupils.size();
            result.add(new Label(nameSubject, score));
        }
        return result;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> scoreByPupil = new ArrayList<>();
        Map<String, Integer> scoreBySubject = new LinkedHashMap<>();
        pupils.forEach(pupil -> pupil.subjects().forEach(subject ->
                scoreBySubject.merge(subject.name(), subject.score(), Integer::sum)));
        for (Pupil pupil : pupils) {
            double score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            scoreByPupil.add(new Label(pupil.name(), score));
        }
        Label result = new Label(null, 0);
        for (Label label : scoreByPupil) {
            if (label.compareTo(result) > 0) {
                result = label;
            }
        }
        return result;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> scoreBySubject = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        pupils.forEach(pupil -> pupil.subjects().forEach(subject ->
                scoreBySubject.merge(subject.name(), subject.score(), Integer::sum)));
        for (String nameSubject : scoreBySubject.keySet()) {
            int score = scoreBySubject.get(nameSubject);
            labels.add(new Label(nameSubject, score));
        }
        Label result = new Label(null, 0);
        for (Label label : labels) {
            if (label.compareTo(result) > 0) {
                result = label;
            }
        }
        return result;
    }
}