package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SingleIssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private Issue issue1 = new Issue(1, "Bug1", 1, false, "Smith", new HashSet<String>(Arrays.asList("bug", "waiting-for-feedback")), new HashSet<String>(Arrays.asList("Bob")));

    @BeforeEach
    void SetUp() {
        manager.addAll(List.of(issue1));
    }

    @Test
    void shouldSingleFilterByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.filterByAuthor("Smith");

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoSingleFilterByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of());
        List<Issue> actual = manager.filterByAuthor("Ivan");

        assertEquals(expected, actual);
    }

    @Test
    void shouldSingleFilterByLabel() {
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.filterByLabel(Set.of("bug"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoSingleFilterByLabel() {
        List<Issue> expected = new ArrayList<>(List.of());
        List<Issue> actual = manager.filterByLabel(Set.of("surprise"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldSingleFilterByAssignee() {
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.filterByAssignee(Set.of("Bob"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoSingleFilterByAssignee() {
        List<Issue> expected = new ArrayList<>(List.of());
        List<Issue> actual = manager.filterByAssignee(Set.of("Tamara"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldSingleSortByNewest() {
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.sortByNewest();

        assertEquals(expected, actual);
    }

}