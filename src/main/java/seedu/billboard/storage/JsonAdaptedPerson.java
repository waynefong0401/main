package seedu.billboard.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.billboard.commons.exceptions.IllegalValueException;
import seedu.billboard.model.person.*;
import seedu.billboard.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Expense}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

//    private final String name;
//    private final String phone;
//    private final String email;
//    private final String address;
    private final String description;
    private final String amount;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

//    /**
//     * Constructs a {@code JsonAdaptedPerson} with the given expense details.
//     */
//    @JsonCreator
//    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
//            @JsonProperty("email") String email, @JsonProperty("address") String address,
//            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
////        this.name = name;
////        this.phone = phone;
////        this.email = email;
////        this.address = address;
//        this.description = description;
//        this.amount = amount;
//        if (tagged != null) {
//            this.tagged.addAll(tagged);
//        }
//    }

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given expense details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("description") String description, @JsonProperty("amount") String amount,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.description = description;
        this.amount = amount;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Expense source) {
//        name = source.getName().fullName;
//        phone = source.getPhone().value;
//        email = source.getEmail().value;
//        address = source.getAddress().value;
        description = source.getDescription().description;
        amount = source.getAmount().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Expense toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);
        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);
//        if (name == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
//        }
//        if (!Name.isValidName(name)) {
//            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
//        }
//        final Name modelName = new Name(name);
//
//        if (phone == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
//        }
//        if (!Phone.isValidPhone(phone)) {
//            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
//        }
//        final Phone modelPhone = new Phone(phone);
//
//        if (email == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
//        }
//        if (!Email.isValidEmail(email)) {
//            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
//        }
//        final Email modelEmail = new Email(email);
//
//        if (address == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
//        }
//        if (!Address.isValidAddress(address)) {
//            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
//        }
//        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
//        return new Expense(modelName, modelPhone, modelEmail, modelAddress, modelTags);
        return new Expense(modelDescription, modelAmount, modelTags);
    }

}
