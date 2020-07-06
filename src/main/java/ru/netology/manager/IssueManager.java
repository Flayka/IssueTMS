package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue item) {
        repository.add(item);
    }

    public void remove(Issue item) {
        repository.remove(item);
    }

    public List<Issue> getAll() {
        return repository.getAll();
    }

    public boolean addAll(List<Issue> items) {
        return repository.addAll(items);
    }

    public List<Issue> filterByAuthor(String author) {
        Predicate<String> byAuthor = Predicate.isEqual(author);
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.getAll())
            if (byAuthor.test(item.getAuthor())) {
                issues.add(item);
            }
        return issues;
    }

    public List<Issue> filterByLabel(Set<String> label) {
        Predicate<Set<String>> byLabel = t -> t.containsAll(label);
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.getAll())
            if (byLabel.test(item.getLabel())) {
                issues.add(item);
            }
        return issues;
    }

    public List<Issue> filterByAssignee(Set<String> assigne) {
        Predicate<Set<String>> byAssignee = t -> t.containsAll(assigne);
        List<Issue> issues = new ArrayList<>();
        for (Issue item : repository.getAll())
            if (byAssignee.test((item.getAssignee()))) {
                issues.add(item);
            }
        return issues;
    }

    public List<Issue> sortByNewest() {
        List<Issue> issues = new ArrayList<>();
        Comparator comparator = Comparator.naturalOrder();
        issues.addAll(repository.getAll());
        issues.sort(comparator);
        return issues;
    }
}
