package projet;
import java.util.Vector;

public class GestionEtudiant {
    private Vector<Etudiant> etudiants;

    public GestionEtudiant() {
        this.etudiants = new Vector<>();

        etudiants.add(new Etudiant(1, "Dupont", "Jean", "123 Rue A"));
        etudiants.add(new Etudiant(2, "Martin", "Emma", "456 Rue B"));
        etudiants.add(new Etudiant(3, "Dubois", "Pierre", "789 Rue C"));
        etudiants.add(new Etudiant(4, "Leclerc", "Sophie", "321 Rue D"));
    }

    public Vector<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Etudiant getEtudiantByCin(int cin) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getCin().equals(cin)) {
                return etudiant;
            }
        }
        return null; 
    }

    public boolean addEtudiant(Etudiant e) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getCin().equals(e.getCin())) {
                return false; 
            }
        }
        etudiants.add(e);
        return true; 
    }

    public boolean updateEtudiant(Etudiant e) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getCin().equals(e.getCin())) {
                etudiants.set(i, e);
                return true; 
            }
        }
        return false; 
    }

    public boolean deleteEtudiant(int cin) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getCin() == cin) {
                etudiants.remove(etudiant);
                return true; 
            }
        }
        return false; 
    }

}
