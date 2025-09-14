module com.bluds.atividadegalao {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.bluds.atividadegalao to javafx.fxml;
    exports com.bluds.atividadegalao;
}