package ohtu.kivipaperisakset;

public class Tekoaly {
    private int siirto = 0;
    private String[] siirrot = {"k", "p", "s"};

    public String annaSiirto() {
        return siirrot[siirto++ % 3];
    }
}
