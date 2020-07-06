package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private Issue issue1 = new Issue(1, "Bug1", 1, false, "Smith", new HashSet<String>(Arrays.asList("bug", "waiting-for-feedback")), new HashSet<String>(Arrays.asList("Bob")));
    private Issue issue2 = new Issue(2, "Bug17", 12, true, "Monroe", new HashSet<String>(Arrays.asList("bug", "blocked")), new HashSet<String>(Arrays.asList("John")));
    private Issue issue3 = new Issue(3, "Error", 7, true, "Vachovsky", new HashSet<String>(Arrays.asList("invalid", "diagnostics")), new HashSet<String>(Arrays.asList("Antony")));
    private Issue issue4 = new Issue(4, "Bug1", 3, true, "Smith", new HashSet<String>(Arrays.asList("bug", "execution", "jupiter")), new HashSet<String>(Arrays.asList("Antony")));
    private Issue issue5 = new Issue(5, "Support", 2, false, "Hopkins", new HashSet<String>(Arrays.asList("works-as-designed", "Platform")), new HashSet<String>(Arrays.asList("David")));

    @BeforeEach
    void SetUp() {
        manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue4));
        List<Issue> actual = manager.filterByAuthor("Smith");

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoFilterByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of());
        List<Issue> actual = manager.filterByAuthor("Ivan");

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
        List<Issue> actual = manager.filterByLabel(Set.of("bug"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoFilterByLabel() {
        List<Issue> expected = new ArrayList<>(List.of());
        List<Issue> actual = manager.filterByLabel(Set.of("surprise"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> expected = new ArrayList<>(List.of(issue3, issue4));
        List<Issue> actual = manager.filterByAssignee(Set.of("Antony"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByNewest() {
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue5, issue4, issue3, issue2));
        List<Issue> actual = manager.sortByNewest();

        assertEquals(expected, actual);
    }
}
