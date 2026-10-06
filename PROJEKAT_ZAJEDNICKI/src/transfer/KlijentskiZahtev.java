/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Darko
 */
public class KlijentskiZahtev implements Serializable {
    private int operacije;
    private Object parametar;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(int operacije, Object parametar) {
        this.operacije = operacije;
        this.parametar = parametar;
    }

    public int getOperacije() {
        return operacije;
    }

    public void setOperacije(int operacije) {
        this.operacije = operacije;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
    
}
