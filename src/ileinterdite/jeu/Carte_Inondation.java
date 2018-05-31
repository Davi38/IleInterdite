package ileinterdite.jeu;

public class Carte_Inondation {

    private Piece_liste piece;

    Carte_Inondation(Piece_liste pc) {
        piece = pc;
    }

    public Piece_liste getPiece() {
        return piece;
    }

}
