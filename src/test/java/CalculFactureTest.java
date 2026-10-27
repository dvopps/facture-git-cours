import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalculFactureTest {
    @Test
    void sousTotalMultiplieLePrixParLaQuantite() {
        // Arrange : préparer les données
        int quantite = 3;

        // Act : appeler la méthode testée
        int sousTotal = CalculFacture.calculerSousTotal(quantite);

        // Assert : vérifier le résultat attendu
        assertEquals(60, sousTotal);
    }

    @Test
    void totalAjouteLaLivraisonEtRetireLeRabais() {
        assertEquals(55, CalculFacture.calculerTotal(60));
    }
}
