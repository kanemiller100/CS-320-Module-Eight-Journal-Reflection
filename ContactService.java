import java.util.HashMap;
import java.util.Map;

/**
 * A simple in-memory ContactService. Supports:
 *  • addContact(Contact)      → must have unique ID (no duplicates, no null)
 *  • deleteContact(String)    → remove by ID (throws if not found)
 *  • update…()                → change firstName, lastName, phone, address by ID
 */
public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new Contact.
     * Throws if:
     *  • contact is null
     *  • ID already exists
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID already exists: " + id);
        }
        contacts.put(id, contact);
    }

    /**
     * Deletes an existing Contact by its ID.
     * Throws if the ID is null or not found.
     */
    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }
        contacts.remove(contactId);
    }

    /**
     * Updates the first name of the Contact with the given ID.
     */
    public void updateFirstName(String contactId, String newFirstName) {
        Contact c = findExisting(contactId);
        c.setFirstName(newFirstName);
    }

    /**
     * Updates the last name of the Contact with the given ID.
     */
    public void updateLastName(String contactId, String newLastName) {
        Contact c = findExisting(contactId);
        c.setLastName(newLastName);
    }

    /**
     * Updates the phone number of the Contact with the given ID.
     */
    public void updatePhone(String contactId, String newPhone) {
        Contact c = findExisting(contactId);
        c.setPhone(newPhone);
    }

    /**
     * Updates the address of the Contact with the given ID.
     */
    public void updateAddress(String contactId, String newAddress) {
        Contact c = findExisting(contactId);
        c.setAddress(newAddress);
    }

    /**
     * Returns the Contact object for the given ID, or null if not found.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    /**
     * Helper: if the ID is null or not in our map, we throw.
     */
    private Contact findExisting(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }
        return contacts.get(contactId);
    }
}
