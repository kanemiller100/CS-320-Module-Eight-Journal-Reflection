/**
 * A simple Contact object that enforces:
 *  • contactId (non-null, ≤10 chars, cannot be changed)
 *  • firstName (non-null, ≤10 chars)
 *  • lastName (non-null, ≤10 chars)
 *  • phone (non-null, exactly 10 digits)
 *  • address (non-null, ≤30 chars)
 *
 * Any violation throws IllegalArgumentException.
 */
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructor for Contact. Immediately validates each field.
     *
     * @param contactId  unique ID (non-null, ≤10 chars)
     * @param firstName  first name (non-null, ≤10 chars)
     * @param lastName   last name (non-null, ≤10 chars)
     * @param phone      phone number (non-null, exactly 10 digits)
     * @param address    address (non-null, ≤30 chars)
     */
    public Contact(String contactId,
                   String firstName,
                   String lastName,
                   String phone,
                   String address) {
        // Validate contactId first (cannot be updated later)
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException(
                "Contact ID must be non-null and at most 10 characters."
            );
        }
        this.contactId = contactId;

        // Delegate validation for other fields to their setters
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Updates first name (non-null, ≤10 chars).
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException(
                "First name must be non-null and at most 10 characters."
            );
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Updates last name (non-null, ≤10 chars).
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException(
                "Last name must be non-null and at most 10 characters."
            );
        }
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * Updates phone (non-null, exactly 10 digits).
     */
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException(
                "Phone must be non-null and exactly 10 digits."
            );
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Updates address (non-null, ≤30 chars).
     */
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException(
                "Address must be non-null and at most 30 characters."
            );
        }
        this.address = address;
    }
}
