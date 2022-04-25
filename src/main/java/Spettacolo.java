
import lombok.Data;

import java.util.*;
@Data
public class Spettacolo {
    private Cliente[] prenotazioni;
    private int n_prenotazione;
    private ArrayList<Cliente> attesa = new ArrayList<>();

    public Spettacolo(int n) {
        this.prenotazioni = new Cliente[n];
    }

    public boolean libero() {
        for (Cliente cl: this.prenotazioni) {
            if (cl == null) {
                return true;
            }
        }

        return false;
    }

    public int trova(String nome, String tel) {
        if (this.attesa.contains(new Cliente(nome, tel))) {
            return 1;
        }

        for (Cliente cl : this.prenotazioni) {
            if (Objects.equals(cl.getNome(), nome) && Objects.equals(cl.getNumero(), tel)) {
                return 0;
            }
        }
        return -1;
    }

    public void prenota(String nome, String tel) {
        if (libero()) {
            int n = Arrays.asList(this.prenotazioni).indexOf(null);
            this.prenotazioni[n] = new Cliente(nome, tel);
        } else {
            this.attesa.add(new Cliente(nome, tel));
        }
    }

    public Cliente trovaCliente(String nome, String tel, String place) {
        if (Objects.equals(place, "prenotazioni")) {
            for (Cliente cl : this.prenotazioni) {
                if (Objects.equals(cl.getNome(), nome) && Objects.equals(cl.getNumero(), tel)) {
                    return cl;
                }
            }
            return null;
        } else if (Objects.equals(place, "attesa")) {
            for (Cliente cl : this.attesa) {
                if (Objects.equals(cl.getNome(), nome) && Objects.equals(cl.getNumero(), tel)) {
                    return cl;
                }
            }
            return null;
        } else {
            return null;
        }
    }


    public void disdici(String nome, String tel) {
        int ind = -1;
        Cliente[] up = new Cliente[this.prenotazioni.length - 1];
        if (trova(nome, tel) == 0) {
            for (int i = 0; i < this.prenotazioni.length; i++) {
                if (Objects.equals(this.prenotazioni[i].getNome(), nome) && Objects.equals(this.prenotazioni[i].getNumero(), tel)) {
                    if (attesa.size() > 0) {
                        up[i] = attesa.get(0);
                        attesa.remove(0);
                    }
                } else {
                    up[i] = this.prenotazioni[i];
                }
            }
            this.prenotazioni = up;
        } else if (trova(nome, tel) == 1) {
            attesa.remove(trovaCliente(nome, tel, "attesa"));
        } else {
            System.out.println("Cliente non presente!");
        }
    }

    public Set<Cliente> clienti() {
        Set<Cliente> clients = new HashSet<>();
        clients.addAll(Arrays.asList(this.prenotazioni));
        clients.addAll(this.attesa);

        return clients;
    }

    public boolean incompleto() {
        for (Cliente cl: clienti()) {
            if (trovaCliente(cl.getNome(), cl.getNumero(), "prenotazioni") != null &&
                trovaCliente(cl.getNome(), cl.getNumero(), "attesa") != null) {
                return true;
            }
        }

        return false;
    }
}
