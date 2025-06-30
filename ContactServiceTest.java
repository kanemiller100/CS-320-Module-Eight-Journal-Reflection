import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ContactService.java
 *
 * We cover:
 * 1) addContact (valid, null, duplicate-IDs)
 * 2) deleteContact (valid, not-found, null ID)
 * 3) updateFirstName / updateLastName / updatePhone / updateAddress
 *    (valid changes + invalid inputs + nonexistent-ID)
 * 4) getContact to verify correct storage
 */
class ContactServiceTest {

    private ContactService service;
    private Contact alice;
    private Contact bob;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        alice = new Contact("ID001", "Alice", "Smith", "1234567890", "100 First Ave");
        bob   = new Contact("ID002", "Bob",   "Jones", "0987654321", "200 Second St");
    }

    @Test
    void addContactSuccess() {
        service.addContact(alice);
        assertSame(alice, service.getContact("ID001"),
                   "After adding Alice, getContact(ID001) should return that same instance");
    }

    @Test
    void addContactNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void addContactDuplicateIdThrows() {
        service.addContact(alice);
        // Create a new Contact with the same ID "ID001"
        Contact alsoAlice = new Contact("ID001", "Clone", "Smith", "1112223333", "300 Third Rd");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(alsoAlice));
    }

    @Test
    void deleteContactSuccess() {
        service.addContact(alice);
        service.deleteContact("ID001");
        assertNull(service.getContact("ID001"),
                   "After deletion, getContact(ID001) should be null");
    }

    @Test
    void deleteContactNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("NOPE"));
    }

    @Test
    void deleteContactNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
    }

    @Test
    void updateFirstNameSuccess() {
        service.addContact(alice);
        service.updateFirstName("ID001", "Marcy");
        assertEquals("Marcy", service.getContact("ID001").getFirstName());
    }

    @Test
    void updateFirstNameInvalidThrows() {
        service.addContact(alice);
        // Null new name
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("ID001", null)
        );
        // Too long new name
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("ID001", "NameThatIsTooLong")
        );
    }

    @Test
    void updateFirstNameNonexistentIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("NO_ID", "Zoe")
        );
    }

    @Test
    void updateLastNameSuccess() {
        service.addContact(alice);
        service.updateLastName("ID001", "Brown");
        assertEquals("Brown", service.getContact("ID001").getLastName());
    }

    @Test
    void updateLastNameInvalidThrows() {
        service.addContact(alice);
        assertThrows(IllegalArgumentException.class, () ->
            service.updateLastName("ID001", null)
        );
        assertThrows(IllegalArgumentException.class, () ->
            service.updateLastName("ID001", "LastNameWayTooLong")
        );
    }

    @Test
    void updatePhoneSuccess() {
        service.addContact(alice);
        service.updatePhone("ID001", "5555555555");
        assertEquals("5555555555", service.getContact("ID001").getPhone());
    }

    @Test
    void updatePhoneInvalidThrows() {
        service.addContact(alice);
        // too short
        assertThrows(IllegalArgumentException.class, () ->
            service.updatePhone("ID001", "123")
        );
        // non-digit
        assertThrows(IllegalArgumentException.class, () ->
            service.updatePhone("ID001", "abcdefghij")
        );
    }

    @Test
    void updateAddressSuccess() {
        service.addContact(alice);
        service.updateAddress("ID001", "500 Fifth Blvd");
        assertEquals("500 Fifth Blvd", service.getContact("ID001").getAddress());
    }

    @Test
    void updateAddressInvalidThrows() {
        service.addContact(alice);
        // null address
        assertThrows(IllegalArgumentException.class, () ->
            service.updateAddress("ID001", null)
        );
        // too long
        String tooLongAddr = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // 31 chars
        assertThrows(IllegalArgumentException.class, () ->
            service.updateAddress("ID001", tooLongAddr)
        );
    }

    @Test
    void updateNonexistentContactThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updateLastName("MISSING", "Test")
        );
    }

    @Test
    void multipleContactsManageCorrectly() {
        service.addContact(alice);
        service.addContact(bob);
        // Both should exist independently
        assertSame(bob, service.getContact("ID002"));
        assertSame(alice, service.getContact("ID001"));

        // Delete Alice; Bob should remain
        service.deleteContact("ID001");
        assertNull(service.getContact("ID001"));
        assertNotNull(service.getContact("ID002"));
    }
}
