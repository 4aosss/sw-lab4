package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(3);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void constructor_InitializesTorpedoCount() {
        TorpedoStore store = new TorpedoStore(5);

        assertEquals(5, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_AllTorpedoes() {
        TorpedoStore store = new TorpedoStore(3);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_FailureDoesNotConsumeTorpedoes() {
        // A failure rate of 1.0 guarantees firing failure.
        TorpedoStore store = new TorpedoStore(3, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_ZeroTorpedoesThrowsException() {
        TorpedoStore store = new TorpedoStore(3);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_NegativeNumberThrowsException() {
        TorpedoStore store = new TorpedoStore(3);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_MoreTorpedoesThanAvailableThrowsException() {
        TorpedoStore store = new TorpedoStore(2);

        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void emptyStore_IsEmpty() {
        TorpedoStore store = new TorpedoStore(0);

        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }
}