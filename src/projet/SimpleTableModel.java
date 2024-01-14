package projet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
public class SimpleTableModel extends AbstractTableModel {
public String[] colNames = { "Cin", "Nom", "Prenom", "Adresse" };
public Class[] colTypes = { Integer.class, String.class, String.class,

String.class };
private Vector<Etudiant> etudiants;
public SimpleTableModel(Vector<Etudiant> etudiants) {
super();
this.etudiants = etudiants;
}
public int getColumnCount() {
return colNames.length;
}
public int getRowCount() {
return etudiants.size();
}
public void setValueAt(Object value, int row, int col) { Etudiant
etudiant = (Etudiant) (etudiants.elementAt(row)); switch
(col) {
case 0:etudiant.setCin((Integer) value);break;
case 1:etudiant.setNom((String) value);break;
case 2:etudiant.setPrenom((String) value);break;
case 3:etudiant.setAdresse((String) value);break;
}
}
public String getColumnName(int col) {
return colNames[col];
}
public Class getColumnClass(int col) {
return colTypes[col];
}
public Object getValueAt(int row, int col) {
Etudiant etudiant = (Etudiant) (etudiants.elementAt(row));
switch (col) {
case 0:return etudiant.getCin();
case 1:return etudiant.getNom();
case 2:return etudiant.getPrenom();
case 3:return etudiant.getAdresse();
}
return new String();
}
}