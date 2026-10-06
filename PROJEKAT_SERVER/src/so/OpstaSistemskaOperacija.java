
package so;

public abstract class OpstaSistemskaOperacija {

    public final void izvrsi(Object parametar) throws Exception {
        izvrsiOperaciju(parametar);
    }

    protected abstract void izvrsiOperaciju(Object parametar) throws Exception;
}
