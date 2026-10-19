
package assigment;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageIDLength() {
        Message message = new Message(
                1, "+27718693002", "Hello Raisibe");

        assertTrue(message.getMessageID().matches("\\d{10}"));
    }

    @Test
    public void testUniqueMessageIDs() {
        Message first = new Message(
                1, "+27718693002", "Hello");

        Message second = new Message(
                2, "+27718693002", "Hi");

        assertNotEquals(first.getMessageID(),
                second.getMessageID());
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                1, "+27718693002", "Hello");

        assertTrue(message.checkrecipientNum());
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                1, "0718693002", "Hello");

        assertFalse(message.checkrecipientNum());
    }

    @Test
    public void testMessageLength() {
        Message valid = new Message(
                1, "+27718693002", "A".repeat(250));

        Message invalid = new Message(
                2, "+27718693002", "A".repeat(251));

        assertTrue(valid.checkmessageLength());
        assertFalse(invalid.checkmessageLength());
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                1, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        String expected = message.getMessageID()
                .substring(0, 2) + ":1:HITONIGHT";

        assertEquals(expected, message.createMessageHash());
    }

    @Test
    public void testEmptyMessageHash() {
        Message message = new Message(
                1, "+27718693002", "   ");

        assertEquals("", message.createMessageHash());
    }

    @Test
    public void testSendMessage() {
        int before = Message.returnTotalMessages();

        Message message = new Message(
                1, "+27718693002", "Hello Mike");

        assertEquals("Message successfully sent.",
                message.sentMessage());

        assertEquals(before + 1,
                Message.returnTotalMessages());
    }

    @Test
    public void testDisregardMessage() {
        int before = Message.returnTotalMessages();

        Message message = new Message(
                1, "+27718693002", "Hello");

        assertEquals("Message disregarded.",
                message.disregardMessage());

        assertEquals(before,
                Message.returnTotalMessages());
    }

    @Test
    public void testCannotHandleTwice() {
        Message message = new Message(
                1, "+27718693002", "Hello");

        message.sentMessage();

        assertEquals(
                "This message has already been handled.",
                message.sentMessage());
    }
}
