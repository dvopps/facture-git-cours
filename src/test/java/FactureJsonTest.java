import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Export JSON d'une facture")
class FactureJsonTest {

    private Gson gson;

    @BeforeEach
    void preparer() {
        gson = new Gson();
    }

    @Test
    @DisplayName("Le JSON contient les montants calculés du client")
    void jsonContientLesMontantsDuClient() {
        String json = Facture.exporterJson("Client fidèle", 6);

        ResumeFacture resume = gson.fromJson(json, ResumeFacture.class);

        assertAll(
                () -> assertEquals("Client fidèle", resume.client),
                () -> assertEquals(6, resume.quantite),
                () -> assertEquals(120, resume.sousTotal),
                () -> assertEquals(110, resume.total));
    }
}
