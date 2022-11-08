package hu.petrik.lotto.lottoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.*;

public class LottoController {

    @FXML
    private Button sorsolGomb;
    @FXML
    private Label sorsoltSzamLabel;
    @FXML
    private HBox sorsoltSzamok;
    private static final Random RND = new Random();
    private final List<Integer> sorsoltszamokLista = new ArrayList<>();

    @FXML
    public void sorsolClick(ActionEvent actionEvent) {
        if (sorsolGomb.getText().equals("Rendez")) {
            Collections.sort(sorsoltszamokLista);
            szamokFrissiteseListaAlapjan();
            sorsoltszamokLista.clear();
            sorsolGomb.setText("Sorsol");
        } else {
            int sorsoltSzam = RND.nextInt(90) + 1;
            while (sorsoltszamokLista.contains(sorsoltSzam)) {
                sorsoltSzam = RND.nextInt(90) + 1;
            }
            sorsoltszamokLista.add(sorsoltSzam);
            sorsoltSzamLabel.setText(String.valueOf(sorsoltSzam));
            szamokFrissiteseListaAlapjan();
            if (sorsoltszamokLista.size() == 5) {
                sorsolGomb.setText("Rendez");
            }
        }
    }

    private void szamokFrissiteseListaAlapjan() {
        sorsoltSzamok.getChildren().clear();
        for (int szam : sorsoltszamokLista) {
            sorsoltSzamok.getChildren().add(new Label(String.valueOf(szam)));
        }
    }
}