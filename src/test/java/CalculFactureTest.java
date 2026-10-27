import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Calcul d'une facture")
class CalculFactureTest {
    @Test
    @DisplayName("Le sous-total multiplie le prix unitaire par la quantité")
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

    @ParameterizedTest(name = "sous-total {0} $ : livraison {1} $")
    @CsvSource({
        "99, 5",
        "100, 0",
        "101, 0"
    })
    @DisplayName("La livraison est gratuite à partir de 100 $")
    void livraisonGratuiteAPartirDuSeuil(int sousTotal, int fraisAttendus) {
        assertEquals(fraisAttendus, CalculFacture.calculerFraisLivraison(sousTotal));
    }

    @Test
    @DisplayName("Une quantité nulle est refusée")
    void quantiteNulleEstRefusee() {
        assertThrows(IllegalArgumentException.class,
                () -> CalculFacture.calculerSousTotal(0));
    }

}
