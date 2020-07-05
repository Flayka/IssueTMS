package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private String name;
    private int date;
    private boolean isOpen;
    private String author;
    private Set<String> label;
    private Set<String> assignee;

    @Override
    public int compareTo(Issue o) {
        return date - o.date;
    }
}
