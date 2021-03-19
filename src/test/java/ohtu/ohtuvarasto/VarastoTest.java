package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;
    Varasto varasto6;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 0.0);
        varasto3 = new Varasto(0.0);
        varasto4 = new Varasto(0, 0.0);
        varasto5 = new Varasto(0, 15);
        varasto6 = new Varasto(0, -2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenLisaysEiTeeMuutosta() {
        varasto.lisaaVarastoon(-2);
        // varastossa pitäisi olla tilaa 10
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ylisuuriLisaysYlijaamaMeneeHukkaan() {
        varasto.lisaaVarastoon(12);
        // varastossa pitäisi olla tilaa 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaNegatiivinenPalauttaaNollan() {
        varasto.otaVarastosta(-2);
        // varastossa pitäisi olla tilaa 10
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaYlisuuriOttoEiVedaMiinukselle() {
        varasto.otaVarastosta(12);
        // varastossa pitäisi olla tilaa 0
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    

    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(8);
        // varastossa pitäisi olla tilaa 2
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }
}