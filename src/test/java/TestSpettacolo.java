import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestSpettacolo {
    Spettacolo sp = new Spettacolo(5);

    @Test
    public void testLibero1() {
        assertTrue(sp.libero());
    }

    @Test
    public void testLibero2() {
        sp.prenota("Vlad", "12345");
        sp.prenota("Anna", "22345");
        sp.prenota("Laura", "32345");
        sp.prenota("John", "42345");
        sp.prenota("Sara", "52345");
        assertFalse(sp.libero());
    }

    @Test
    public void testTrova() {
        sp.prenota("Vlad", "1234598765");
        sp.prenota("Anna", "2234598765");
        sp.prenota("Laura", "3234598765");
        sp.prenota("John", "4234598765");
        sp.prenota("Sara", "5234598765");
        sp.prenota("Mario", "6234598765");
        assertEquals(1, sp.trova("Mario", "6234598765"));
        assertEquals(0, sp.trova("Anna", "2234598765"));
        assertEquals(-1, sp.trova("Daniela", "7234598765"));
    }

    @Test
    public void testDisdici() {
        sp.prenota("Vlad", "1234598765");
        sp.prenota("Anna", "2234598765");
        sp.prenota("Laura", "3234598765");
        sp.prenota("Mario", "6234598765");
        sp.prenota("John", "4234598765");
        sp.prenota("Sara", "5234598765");
        sp.prenota("Mario", "6234598765");

        Cliente[] clienti = {new Cliente("Vlad", "1234598765"),
                             new Cliente("Anna", "2234598765"),
                             new Cliente("Laura", "3234598765"),
                             new Cliente("Mario", "6234598765"),
                             new Cliente("John", "4234598765")};
        ArrayList<Cliente> att = new ArrayList<>();
        att.add(new Cliente("Sara", "5234598765"));
        att.add(new Cliente("Mario", "6234598765"));
        for (int i = 0; i < clienti.length; i++) {
            assertEquals(clienti[i].getNome(), sp.getPrenotazioni()[i].getNome());
            assertEquals(clienti[i].getNumero(), sp.getPrenotazioni()[i].getNumero());
        }

        for (int i = 0; i < att.size(); i++) {
            assertEquals(att.get(i).getNome(), sp.getAttesa().get(i).getNome());
            assertEquals(att.get(i).getNumero(), sp.getAttesa().get(i).getNumero());
        }

        att.remove(new Cliente("Mario", "6234598765"));
        sp.disdici("Mario", "6234598765");
        for (int i = 0; i < att.size(); i++) {
            assertEquals(att.get(i).getNome(), sp.getAttesa().get(i).getNome());
            assertEquals(att.get(i).getNumero(), sp.getAttesa().get(i).getNumero());
        }
    }

    @Test
    public void testIncompleto() {
        sp.prenota("Vlad", "1234598765");
        sp.prenota("Anna", "2234598765");
        sp.prenota("Laura", "3234598765");
        sp.prenota("Mario", "6234598765");
        sp.prenota("John", "4234598765");
        sp.prenota("Sara", "5234598765");
        sp.prenota("Mario", "6234598765");

        assertTrue(sp.incompleto());

        sp.disdici("Mario", "6234598765");

        assertFalse(sp.incompleto());
    }
}
