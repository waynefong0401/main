package seedu.billboard.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.billboard.commons.util.StringUtil;

/**
 * Tests that a {@code Expense}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Expense> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Expense expense) {
//        return keywords.stream()
//                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getName().fullName, keyword));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getDescription().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
