package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyIssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    @Test
    void shouldNoFilterByAuthor() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.filterByAuthor("Jane");

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoFilterByLabel() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.filterByLabel(Set.of("bug"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoFilterByAssignee() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.filterByAssignee(Set.of("Tomas"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldNoSortByNewest() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = manager.sortByNewest();

        assertEquals(expected, actual);
    }
}
