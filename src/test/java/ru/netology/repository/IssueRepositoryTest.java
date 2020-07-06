package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();
    private Issue issue1 = new Issue(1, "Bug1", 1, false, "Smith", new HashSet<String>(Arrays.asList("bug", "waiting-for-feedback")), new HashSet<String>(Arrays.asList("Bob")));
    private Issue issue2 = new Issue(2, "Bug17", 12, true, "Monroe", new HashSet<String>(Arrays.asList("bug", "blocked")), new HashSet<String>(Arrays.asList("John")));
    private Issue issue3 = new Issue(3, "Error", 7, true, "Vachovsky", new HashSet<String>(Arrays.asList("invalid", "diagnostics")), new HashSet<String>(Arrays.asList("Antony")));
    private Issue issue4 = new Issue(4, "Bug1", 3, true, "Smith", new HashSet<String>(Arrays.asList("bug", "execution", "jupiter")), new HashSet<String>(Arrays.asList("Antony")));
    private Issue issue5 = new Issue(5, "Support", 2, false, "Hopkins", new HashSet<String>(Arrays.asList("works-as-designed", "Platform")), new HashSet<String>(Arrays.asList("David")));

    @BeforeEach
    void SetUp() {
        repository.addAll(List.of(issue1, issue2, issue3, issue4, issue5));
    }

    @Test
    void shouldFindAllOpen() {
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue3, issue4));
        List<Issue> actual = repository.findAllOpen();

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllClose() {
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue5));
        List<Issue> actual = repository.findAllClose();

        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenIssueId() {
        repository.openIssueId(5);
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue3, issue4, issue5));
        List<Issue> actual = repository.findAllOpen();

        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssueId() {
        repository.closeIssueId(3);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue5));
        List<Issue> actual = repository.findAllClose();

        assertEquals(expected, actual);
    }
}
