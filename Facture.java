public class Facture {
    public static void main(String[] args) {
        String client = "Client démonstration";
        int prixUnitaire = 20;
        int quantite = 3;
		int fraisLivraison = 5;
		int rabais = 10;
        int sousTotal = prixUnitaire * quantite;
        int total = sousTotal + fraisLivraison - rabais;


        System.out.println("Client : " + client);
		System.out.println(quantite + " article(s) à " + prixUnitaire + " $");
		System.out.println("Sous-total : " + sousTotal + " $");
        System.out.println("Total : " + total + " $");
    }
}
