module hu.petrik.lotto.lottoproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.lotto.lottoproject to javafx.fxml;
    exports hu.petrik.lotto.lottoproject;
}