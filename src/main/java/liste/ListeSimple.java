package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne la taille actuelle de la liste.
     *
     * @return Le nombre total d'éléments dans la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément au tout début de la liste (en tête).
     *
     * @param element La valeur (de type entier) du nouveau noeud à ajouter.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la première occurrence d'un élément spécifique en le remplaçant par une nouvelle valeur.
     *
     * @param element        L'élément à rechercher dans la liste.
     * @param nouvelleValeur La nouvelle valeur à assigner au premier noeud correspondant.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie absolument toutes les occurrences d'un élément en les remplaçant par une nouvelle valeur.
     *
     * @param element        L'élément à rechercher dans l'ensemble de la liste.
     * @param nouvelleValeur La nouvelle valeur à assigner à tous les noeuds correspondants.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence d'un élément donné de la liste.
     * La taille de la liste est décrémentée si la suppression réussit.
     *
     * @param element L'élément à trouver et à supprimer de la liste.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences d'un élément donné dans la liste.
     *
     * @param element L'élément à supprimer (de type int).
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Méthode récursive interne servant à supprimer toutes les occurrences d'un élément 
     * à partir d'un noeud spécifique. La taille (size) est actualisée au fil de la suppression.
     *
     * @param element L'élément à supprimer.
     * @param tete    Le noeud à partir duquel commencer l'inspection récursive.
     * @return Le noeud réévalué (qui peut servir de suite de la liste après suppression).
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Récupère l'avant-dernier noeud de la liste.
     *
     * @return L'avant-dernier noeud, ou {@code null} si la liste est vide ou ne contient qu'un seul élément.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre de tous les éléments de la liste (le premier devient le dernier, etc.).
     * L'opération modifie la structure de la liste directement sur place.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Cherche et retourne le noeud situé juste avant un noeud cible spécifié.
     * Note : Cette méthode suppose que le noeud cible existe dans la liste et qu'il n'est pas la tête.
     *
     * @param r Le noeud cible dont on veut trouver le précédent.
     * @return Le noeud précédant le noeud cible {@code r}.
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange physiquement les positions de deux noeuds dans la liste.
     * La liste est réorganisée de sorte que {@code r1} prenne la place de {@code r2} et vice versa.
     *
     * @param r1 Le premier noeud à échanger.
     * @param r2 Le second noeud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}