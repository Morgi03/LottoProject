package hu.petrik.lotto.lottoproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.Duration;
import java.time.LocalDateTime;
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
            rendez();
        } else {
            veletlenSzamokMegjelenitese();
        }
    }

    private void veletlenSzamokMegjelenitese() {
        LocalDateTime indulasIdopontja = LocalDateTime.now();
        sorsolGomb.setDisable(true);
        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    int velelenSzam = RND.nextInt(90) + 1;
                    sorsoltSzamLabel.setText(String.valueOf(velelenSzam));
                    LocalDateTime aktualisIdopont = LocalDateTime.now();
                    Duration elteltido = Duration.between(indulasIdopontja,aktualisIdopont);
                    if (elteltido.getSeconds() >= 2){
                        myTimer.cancel();
                        sorsol();
                        sorsolGomb.setDisable(false);
                    }
                });
            }
        };
        myTimer.schedule(task, 0, 100);
    }

    private void sorsol() {
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

    private void rendez() {
        Collections.sort(sorsoltszamokLista);
        szamokFrissiteseListaAlapjan();
        sorsoltszamokLista.clear();
        sorsolGomb.setText("Sorsol");
    }

    private void szamokFrissiteseListaAlapjan() {
        sorsoltSzamok.getChildren().clear();
        for (int szam : sorsoltszamokLista) {
            sorsoltSzamok.getChildren().add(new Label(String.valueOf(szam)));
        }
    }
}