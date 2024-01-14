package projet;

import java.util.Vector;

public class TestGestionEtudiant {
    public static void main(String[] args) {
        GestionEtudiant gestionEtudiant = new GestionEtudiant();

        Vector<Etudiant> etudiants = gestionEtudiant.getEtudiants();
        System.out.println("Liste des étudiants :");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }

        int cin = 3;
        Etudiant etudiant = gestionEtudiant.getEtudiantByCin(cin);
        if (etudiant != null) {
            System.out.println("Etudiant avec le CIN " + cin + " : " + etudiant);
        } else {
            System.out.println("Aucun étudiant avec le CIN " + cin + " trouvé.");
        }

        Etudiant nouvelEtudiant = new Etudiant(5, "Garcia", "Lucas", "987 Rue E");
        boolean ajoutReussi = gestionEtudiant.addEtudiant(nouvelEtudiant);
        if (ajoutReussi) {
            System.out.println("Nouvel étudiant ajouté avec succès : " + nouvelEtudiant);
        } else {
            System.out.println("Échec de l'ajout de l'étudiant. Un étudiant avec le même CIN existe déjà.");
        }

        Etudiant etudiantAMettreAJour = gestionEtudiant.getEtudiantByCin(4);
        if (etudiantAMettreAJour != null) {
            etudiantAMettreAJour.setAdresse("555 Rue F");
            boolean miseAJourReussie = gestionEtudiant.updateEtudiant(etudiantAMettreAJour);
            if (miseAJourReussie) {
                System.out.println("Mise à jour de l'étudiant réussie : " + etudiantAMettreAJour);
            } else {
                System.out.println("Échec de la mise à jour de l'étudiant.");
            }
        } else {
            System.out.println("Aucun étudiant avec le CIN 4 trouvé pour la mise à jour.");
        }

        int cinASupprimer = 2;
        boolean suppressionReussie = gestionEtudiant.deleteEtudiant(cinASupprimer);
        if (suppressionReussie) {
            System.out.println("Suppression de l'étudiant avec le CIN " + cinASupprimer + " réussie.");
        } else {
            System.out.println("Échec de la suppression de l'étudiant avec le CIN " + cinASupprimer + ".");
        }
    }
}
