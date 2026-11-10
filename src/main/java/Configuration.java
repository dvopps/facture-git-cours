import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.Properties;

public class Configuration {
    private final Map<String, String> variables;
    private final Properties parametres;
    private final Properties fichiers = new Properties();
    private final String environnement;

    Configuration(Map<String, String> variables, Properties parametres) {
        this.variables = variables;
        this.parametres = parametres;
        charger("application.properties");
        String env = lire("app.env");
        this.environnement = (env == null) ? "dev" : env;
        if (!environnement.equals("dev")) {
            charger("application-" + environnement + ".properties");
        }
    }

    static Configuration depuisLeSysteme() {
        return new Configuration(System.getenv(), System.getProperties());
    }

    String environnement() {
        return environnement;
    }

    String lire(String cle) {
        String parametre = parametres.getProperty(cle);
        if (parametre != null) {
            return parametre;
        }
        String variable = variables.get(nomDeVariable(cle));
        if (variable != null) {
            return variable;
        }
        return fichiers.getProperty(cle);
    }

    String lireObligatoire(String cle) {
        String valeur = lire(cle);
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalStateException("Valeur obligatoire manquante : " + cle
                    + " (variable d'environnement " + nomDeVariable(cle) + ")");
        }
        return valeur;
    }

    static String nomDeVariable(String cle) {
        return cle.toUpperCase().replace('.', '_');
    }

    private void charger(String fichier) {
        try (InputStream entree = Configuration.class.getClassLoader().getResourceAsStream(fichier)) {
            if (entree == null) {
                throw new IllegalStateException("Fichier de configuration introuvable : " + fichier);
            }
            fichiers.load(entree);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
