import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Configuration de l'application")
class ConfigurationTest {

    private static final Properties AUCUN_PARAMETRE = new Properties();

    @Test
    @DisplayName("Sans APP_ENV, on est en développement")
    void sansAppEnvOnEstEnDeveloppement() {
        Configuration config = new Configuration(Map.of(), AUCUN_PARAMETRE);

        assertEquals("dev", config.environnement());
        assertEquals("http://localhost:8080/factures", config.lire("compta.url"));
    }

    @Test
    @DisplayName("Le fichier de l'environnement remplace le fichier par défaut")
    void fichierDeLEnvironnementRemplaceLeDefaut() {
        Configuration config = new Configuration(Map.of("APP_ENV", "prod"), AUCUN_PARAMETRE);

        assertEquals("https://compta.example.com/factures", config.lire("compta.url"));
        assertEquals("10", config.lire("compta.delai.secondes"));
    }

    @Test
    @DisplayName("Une valeur absente du fichier de l'environnement vient du fichier par défaut")
    void valeurAbsenteVientDuDefaut() {
        Configuration config = new Configuration(Map.of("APP_ENV", "test"), AUCUN_PARAMETRE);

        assertEquals("30", config.lire("compta.delai.secondes"));
    }

    @Test
    @DisplayName("Une variable d'environnement l'emporte sur les fichiers")
    void variableLEmporteSurLesFichiers() {
        Map<String, String> variables = Map.of("APP_ENV", "prod", "COMPTA_URL", "http://autre/factures");

        Configuration config = new Configuration(variables, AUCUN_PARAMETRE);

        assertEquals("http://autre/factures", config.lire("compta.url"));
    }

    @Test
    @DisplayName("Un paramètre -D l'emporte sur une variable d'environnement")
    void parametreLEmporteSurLaVariable() {
        Properties parametres = new Properties();
        parametres.setProperty("compta.url", "http://parametre/factures");

        Configuration config = new Configuration(Map.of("COMPTA_URL", "http://variable/factures"), parametres);

        assertEquals("http://parametre/factures", config.lire("compta.url"));
    }

    @Test
    @DisplayName("Un secret manquant est refusé")
    void secretManquantEstRefuse() {
        Configuration config = new Configuration(Map.of(), AUCUN_PARAMETRE);

        assertThrows(IllegalStateException.class, () -> config.lireObligatoire("compta.cle"));
    }

    @Test
    @DisplayName("Un environnement inconnu est refusé")
    void environnementInconnuEstRefuse() {
        assertThrows(IllegalStateException.class,
                () -> new Configuration(Map.of("APP_ENV", "prdo"), AUCUN_PARAMETRE));
    }
}
