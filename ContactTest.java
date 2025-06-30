import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Contact.java
 *
 * We check:
 * 1) Valid construction
 * 2) Every invalid case for each field
 * 3) Proper behavior of setters (valid + invalid)
 */
class ContactTest {

    @Test
    void validContactCreation() {
        Contact c = new Contact(
            "ABC123",           // ID (valid, 6 chars)
            "John",             // firstName
            "Doe",              // lastName
            "1234567890",       // phone (exactly 10 digits)
            "123 Main St, City" // address (under 30 chars)
        );
        assertEquals("ABC123", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St, City", c.getAddress());
    }

    // ---- Contact ID tests ----

    @Test
    void contactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "Jane", "Doe", "0123456789", "456 Elm St")
        );
    }

    @Test
    void contactIdCannotBeLongerThan10() {
        String tooLong = "ABCDEFGHIJK"; // 11 chars
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(tooLong, "Jane", "Doe", "0123456789", "456 Elm St")
        );
    }

    // ---- First Name tests ----

    @Test
    void firstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", null, "Doe", "0123456789", "789 Oak St")
        );
    }

    @Test
    void firstNameCannotBeLongerThan10() {
        String tooLongFirst = "ABCDEFGHIJK"; // 11 chars
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", tooLongFirst, "Doe", "0123456789", "789 Oak St")
        );
    }

    // ---- Last Name tests ----

    @Test
    void lastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", null, "0123456789", "789 Oak St")
        );
    }

    @Test
    void lastNameCannotBeLongerThan10() {
        String tooLongLast = "ABCDEFGHIJK"; // 11 chars
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", tooLongLast, "0123456789", "789 Oak St")
        );
    }

    // ---- Phone tests ----

    @Test
    void phoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", "Doe", null, "789 Oak St")
        );
    }

    @Test
    void phoneMustBeExactly10Digits() {
        // 9 digits
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", "Doe", "123456789", "789 Oak St")
        );
        // 11 digits
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", "Doe", "12345678901", "789 Oak St")
        );
        // Non-digit characters
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", "Doe", "12345abcde", "789 Oak St")
        );
    }

    // ---- Address tests ----

    @Test
    void addressCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", "Doe", "0123456789", null)
        );
    }

    @Test
    void addressCannotBeLongerThan30() {
        String tooLongAddress = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // 31 chars
        assertTrue(tooLongAddress.length() > 30);
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID123", "Jane", "Doe", "0123456789", tooLongAddress)
        );
    }

    // ---- Test setters (valid + invalid) ----

    @Test
    void setFirstNameValid() {
        Contact c = new Contact("ID1", "A", "B", "1112223333", "Addr");
        c.setFirstName("NewName");
        assertEquals("NewName", c.getFirstName());
    }

    @Test
    void setFirstNameInvalid() {
        Contact c = new Contact("ID2", "A", "B", "1112223333", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () ->
            c.setFirstName("TooLongFirstName")
        );
    }

    @Test
    void setLastNameValid() {
        Contact c = new Contact("ID3", "A", "B", "1112223333", "Addr");
        c.setLastName("NewLast");
        assertEquals("NewLast", c.getLastName());
    }

    @Test
    void setLastNameInvalid() {
        Contact c = new Contact("ID4", "A", "B", "1112223333", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () ->
            c.setLastName("TooLongLastName")
        );
    }

    @Test
    void setPhoneValid() {
        Contact c = new Contact("ID5", "A", "B", "1112223333", "Addr");
        c.setPhone("0987654321");
        assertEquals("0987654321", c.getPhone());
    }

    @Test
    void setPhoneInvalid() {
        Contact c = new Contact("ID6", "A", "B", "1112223333", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));           // too short
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345abcde"));  // non-digits
    }

    @Test
    void setAddressValid() {
        Contact c = new Contact("ID7", "A", "B", "1112223333", "Addr");
        c.setAddress("New Address 123");
        assertEquals("New Address 123", c.getAddress());
    }

    @Test
    void setAddressInvalid() {
        Contact c = new Contact("ID8", "A", "B", "1112223333", "Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
        String tooLongAddr = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // 31 chars
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(tooLongAddr));
    }
}
