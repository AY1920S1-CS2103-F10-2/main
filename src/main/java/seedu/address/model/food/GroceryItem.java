package seedu.address.model.food;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a grocery item.
 */
public class GroceryItem extends Food {

    private final ExpiryDate expiryDate;
    private final Set<Tag> tags = new HashSet<>();

    public GroceryItem(Name name, Amount amount, ExpiryDate expiryDate, Set<Tag> tags) {
        super(name, amount);
        this.expiryDate = expiryDate;
        this.tags.addAll(tags);
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isSameFood(Food otherFood) {
        if (otherFood instanceof GroceryItem) {
            return false;
        }

        if (otherFood == this) {
            return true;
        }

        return otherFood != null
                && otherFood.getName().equals(getName())
                && ((GroceryItem) otherFood).getExpiryDate().equals(getExpiryDate());
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isEmpty() {
        return Amount.getValue(getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getAmount(), this.getExpiryDate(), this.getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name:")
                .append(getName())
                .append(" Amount:")
                .append(getAmount())
                .append(" ExpiryDate:")
                .append(getExpiryDate())
                .append(" Tags:");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
