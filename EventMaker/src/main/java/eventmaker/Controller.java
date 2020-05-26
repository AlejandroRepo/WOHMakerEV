package eventmaker;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Pair;

public class Controller implements Initializable {

    List<String> locationsList = Arrays.asList("global", "downtown", "apartment", "school", "hospital", "seaside", "forest", "mansion", "schoolhospital", "seasideforest", "village", "atorasu", "athyola","gozu","ithotu");
    List<String> checksList = Arrays.asList("story", "strength", "dexterity", "perception", "knowledge", "luck", "charisma","funds1","funds2");
    List<String> itemList = Arrays.asList("STEAK KNIFE", "CAMERA", "NICE RING", "KENDO HELMET", "SEWING KIT",
        "BACKPACK", "WINE BOTTLE", "CIGARETTES", "SMALL CANDLE", "CARPENTER HAMMER", "HOLY CANDLE",
        "MUMMIFIED HEART", "LIBRARY NOTES", "POLICE REVOLVER", "FLASHLIGHT", "PAINKILLER", "BLUE GEM",
        "LONG PIG STEAK", "LUMP OF FLESH", "RITUAL MASK", "GRIMORIE", "RITUAL DAGGER", "HUMAN SKULL", "GLASS EYE",
        "KATANA", "BONE SAW", "CURSED DOLL", "EMPTY BOTTLE", "SLUDGE BOTTLE", "MILK BOTTLE", "RITUAL ROBE",
        "CHAMPAGNE", "LOST TAPES", "DUST OF SEEING", "PRESCRIPTION PILLS", "STALKER'S MASK", "BASEBALL BAT",
        "FOREIGN CIGARETTES", "LIBRARY BOOK", "PANCAKES", "ARMY KNIFE", "ELDRITCH AMULET", "CURSED CARTRIDGE",
        "CURIOUS STATUETTE", "POCKET KNIFE", "SCALPEL", "LUCKY EARRINGS", "GHOST DUST", "HUNTING RIFLE",
        "HAPPI COAT", "TAIYAKI", "WOODEN BAT", "MAP", "PRESCRIPTION", "BRANCH", "COMPASS", "ENERGY DRINK", "SALT",
        "PRAYER BEADS", "BROKEN BOTTLE", "CAN OF ACID", "TINY KEY", "GOBLET", "SMELLY MEAT", "CROWBAR", "FIRE AXE",
        "KARUKOSA MASK", "ANCIENT RING", "SHOVEL", "BANDAGE", "CURSED SCISSORS", "TORCH", "CRESTFALLEN MASK",
        "MEAT CLEAVER", "BLACK HAIR", "SEWING KIT HAIR", "TOME OF ROT", "FORBIDDEN BOTANY", "DOG TREATS",
        "GRUESOME TOTEM", "PAIN MEDICATION", "EXPERIMENTAL DRUG", "EXTRA AMMO", "SPORT RIFLE", "WATER BOTTLE",
        "MEDICAL KIT", "OLD SHOTGUN", "ROCK RING", "PATINA RING", "DEMON MASK", "DIY FLAMETHROWER");

    List<String> spellList = Arrays.asList("MIND DRAIN", "REGENERATION", "SKIN PEEL", "MULTIPLY WOUND",
        "RITUAL OF KNOPHA", "INVISIBILITY", "ABOLISH", "ABSORB", "THIRD EYE", "FLAME OF ITHOTU", "SEAL OF SAVVESH",
        "SEAL OF BRAMEL", "THREAD OF FATE", "ANCESTRAL STRENGTH", "ENTTHRALLMENT", "AWAKEN", "BINDING AGONY",
        "WITNESS CURSE", "SHADOW SHROUD", "MEND", "VOID", "CAUTERIZE", "MEMORY EXTRACT", "ASHEN CONTRACT",
        "MIDASU TOCH", "FLESH REGROWTH", "EXPEL EVIL", "GROW TEETH", "BRAIN WORMS", "TIME WARP", "CLEANSING RITUAL",
        "TURN BEAST");

    List<String> rewardsList = Arrays.asList("none", "experience", "stamina", "reason", "doom", "funds", "item", "itempool", "injury" , "curse", "spell", "ally");
    List<String> extraRewardsList = Arrays.asList("none", "experience", "stamina", "reason", "doom");
    List<String> itemPools = Arrays.asList("mask", "book", "ring", "poor", "magicitem","hardwareshop","vendingshop","dogshop","pharmacyshop","hideout");
    List<String> visualEffectsList = Arrays.asList("none", "whiteflash", "bloodsplat");

    Image eventArt;

    List<TextField> txtExtraRewards = new ArrayList<>();
    List<ComboBox<String>> comboRewards = new ArrayList<>();
    List<ComboBox<String>> comboChecks = new ArrayList<>();
    List<ComboBox<String>> comboVisual = new ArrayList<>();
    List<ComboBox<String>> comboExtraRewards = new ArrayList<>();
    List<Label> lblWarnings = new ArrayList<>();
    List<TextField> txtRewardList = new ArrayList<>();

    @FXML
    TextField textTitle;

    @FXML
    TextField textDesc;

    @FXML

    TextField txtContact;

    @FXML

    TextField txtAuthor;

    @FXML
    TextArea textFlav;

    @FXML
    ComboBox<String> cmbLocation;

    @FXML
    Label lblTooLong;

    @FXML
    Label lblImgWarn;

    @FXML
    ImageView imgArt;

    @FXML
    ComboBox<String> comboCheckA;

    @FXML
    ComboBox<String> comboCheckB;

    @FXML
    ComboBox<String> comboCheckC;

    @FXML
    ComboBox<String> cmbRewardsA;

    @FXML
    ComboBox<String> cmbRewardsAF;

    @FXML
    ComboBox<String> cmbExtraRewardsA;

    @FXML
    ComboBox<String> cmbExtraRewardsAF;

    @FXML
    ComboBox<String> cmbExtraRewardsB;

    @FXML
    ComboBox<String> cmbExtraRewardsBF;

    @FXML
    ComboBox<String> cmbExtraRewardsC;

    @FXML
    ComboBox<String> cmbExtraRewardsCF;

    @FXML
    ComboBox<String> cmbRewardsBF;

    @FXML
    ComboBox<String> cmbRewardsCF;

    @FXML
    ComboBox<String> cmbRewardsB;

    @FXML
    ComboBox<String> cmbRewardsC;

    @FXML
    ComboBox<String> cmbVisualA;

    @FXML
    ComboBox<String> cmbVisualB;

    @FXML
    ComboBox<String> cmbVisualC;

    @FXML
    ComboBox<String> cmbVisualAF;

    @FXML
    ComboBox<String> cmbVisualBF;

    @FXML
    ComboBox<String> cmbVisualCF;

    @FXML
    Button btnSaveUser;

    @FXML
    Button btnLoadPic;

    @FXML
    TextField txtPic;

    @FXML
    Button btnExit;

    @FXML
    Tab tabFailureA;

    @FXML
    Tab tabFailureB;

    @FXML
    CheckBox chkWavy;

    @FXML
    Tab tabFailureC;

    @FXML
    Slider sldWavy;

    @FXML
    CheckBox chkBigArt;

    @FXML
    Label lblWavyVal;

    @FXML
    ComboBox<String> cmbOptions;

    @FXML
    VBox optionA;

    @FXML
    VBox optionB;

    @FXML
    VBox optionC;

    @FXML
    Button btnLoadIto;

    @FXML
    AnchorPane helpArea;

    @FXML
    TextArea helpAreatxt;

    @FXML
    TextField txtOptionA;

    @FXML
    TextField txtOptionB;

    @FXML
    TextField txtOptionC;

    @FXML
    ImageView imgGuiArt;

    @FXML
    ImageView imgGuiArt2;

    @FXML
    ImageView imgGuiArt3;

    @FXML
    ImageView imgGuiArt4;

    @FXML
    ImageView imgGuiArt5;

    @FXML
    TextArea txtFailureA;

    @FXML
    TextArea txtFailureB;

    @FXML
    TextArea txtFailureC;

    @FXML
    TextArea txtSuccessA;

    @FXML
    TextArea txtSuccessB;

    @FXML
    TextArea txtSuccessC;

    @FXML
    TextField txtRewardA;

    @FXML
    TextField txtRewardAF;

    @FXML
    TextField txtRewardB;

    @FXML
    TextField txtRewardBF;

    @FXML
    TextField txtRewardC;

    @FXML
    TextField txtRewardCF;

    @FXML
    TextField txtExtraRewardA;

    @FXML
    TextField txtExtraRewardB;

    @FXML
    TextField txtExtraRewardC;

    @FXML
    TextField txtExtraRewardCF;

    @FXML
    TextField txtExtraRewardAF;

    @FXML
    TextField txtExtraRewardBF;

    @FXML
    Button btnSaveIto;

    @FXML
    ImageView btnHideHelp;

    @FXML
    Hyperlink linkDiscord;

    @FXML
    Hyperlink linkRepo;

    @FXML
    ImageView lowerBand;

    @FXML
    Button btnCLear;

    @FXML
    Label lblAS;

    @FXML
    Label lblAF;

    @FXML
    Label lblBS;

    @FXML
    Label lblBD;

    @FXML
    Label lblCS;

    @FXML
    Label lblCF;

    @FXML
    ImageView band1;

    @FXML
    ImageView band2;

    @FXML
    VBox root;

    Dialog imageViewDialog = new Dialog();

    String currentImage = "";

    static BooleanProperty smallScreenMode = new SimpleBooleanProperty(false);

    boolean forceFileRefresh = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Font.loadFont(Controller.class.getResource("/Silver.ttf").toExternalForm(), 38);
        comboRewards.addAll(Arrays.asList(cmbRewardsA,cmbRewardsAF,cmbRewardsB,cmbRewardsBF,cmbRewardsC,cmbRewardsCF));
        comboExtraRewards.addAll(Arrays.asList(cmbExtraRewardsA,cmbExtraRewardsAF,cmbExtraRewardsB,cmbExtraRewardsBF,cmbExtraRewardsC,cmbExtraRewardsCF));
        txtRewardList.addAll(Arrays.asList(txtRewardA,txtRewardAF,txtRewardB,txtRewardBF,txtRewardC,txtRewardCF));
        comboVisual.addAll(Arrays.asList(cmbVisualA,cmbVisualAF,cmbVisualB,cmbVisualBF,cmbVisualC,cmbVisualCF));
        comboChecks.addAll(Arrays.asList(comboCheckA,comboCheckB,comboCheckC));
        lblWarnings.addAll(Arrays.asList(lblAS,lblAF,lblBS, lblBD,lblCS,lblCF));
        txtExtraRewards.addAll(Arrays.asList(txtExtraRewardA,txtExtraRewardAF,txtExtraRewardB,txtExtraRewardBF,txtExtraRewardC,txtExtraRewardCF));

        smallScreenMode.addListener(inv -> {
            band1.setManaged(false);
            band1.setVisible(false);
            band2.setVisible(false);
            band2.setManaged(false);
        });

        btnCLear.setGraphic(new ImageView(new Image(Controller.class.getResource("/erase.png").toExternalForm())));
        btnLoadPic.setGraphic(new ImageView(new Image("/load.png")));
        btnSaveUser.setGraphic(new ImageView("/save.png"));

        txtPic.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> evt.consume());

        readPrefs();

        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        installHandlers();
        installListeners();
        implementHelpSystem();
    }

    /**
     * Listens to things happening and does things
     */

    void installListeners(){

        textFlav.lengthProperty().addListener((ob,old,newValue) -> lblTooLong.setVisible(newValue.intValue()>350));
        comboCheckA.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> tabFailureA.setDisable(newValue.equals("story")));
        comboCheckB.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> tabFailureB.setDisable(newValue.equals("story")));
        comboCheckC.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> tabFailureC.setDisable(newValue.equals("story")));

        cmbOptions.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> {
            optionB.setDisable(newValue.equals("1"));
            optionC.setDisable(newValue.equals("1") || newValue.equals("2"));
        });

        cmbOptions.getSelectionModel().select(0);


        ArrayList<Pair<Label,TextArea>> tempWarnList = new ArrayList<>();
        ArrayList<TextArea> optionStrings = new ArrayList<>(Arrays.asList(txtSuccessA,txtFailureA,txtSuccessB,txtFailureB,txtSuccessC,txtFailureC));

        for (int i = 0; i <comboRewards.size(); i++){
            tempWarnList.add(new Pair(lblWarnings.get(i),optionStrings.get(i)));
        }

        tempWarnList.forEach(pair -> pair.getValue().textProperty().addListener((ob,old,newValue) -> {
            try {
                pair.getKey().setVisible(newValue.length() > 290);
            }catch (NullPointerException e){

            }
            }));

        chkWavy.selectedProperty().addListener((ob,old,newValue) -> {
            sldWavy.setDisable(!newValue);
            lblWavyVal.setDisable(!newValue);
        });
        sldWavy.valueProperty().addListener((ob,old,newValue) -> lblWavyVal.setText(String.format("%.1f", newValue)));


        btnExit.setOnAction(evt -> System.exit(0));
        txtAuthor.setOnKeyReleased(evt -> btnSaveUser.setDisable(false));
        txtContact.setOnKeyReleased(evt -> btnSaveUser.setDisable(false));



        // listen to selected type of rewards and refreshes autocompletion lists for all related widgets
        installAutoComplete();

        // Image dialog

        imgArt.sceneProperty().addListener(inv -> {
            imageViewDialog.initOwner(imgArt.getScene().getWindow());
            imageViewDialog.getDialogPane().getButtonTypes().setAll(ButtonType.CLOSE);
            imageViewDialog.initModality(Modality.NONE);
            imageViewDialog.setResizable(false);
            imageViewDialog.initStyle(StageStyle.UTILITY);
            imageViewDialog.getDialogPane().setStyle("-fx-background-color: black;");
        });
    }

    File prefs = Paths.get(System.getProperty("user.home"), "WOHMaker", "prefs.dat").toFile();


    void installAutoComplete(){
        // listen to selected type of rewards and refreshes autocompletion lists for all related widgets
        ArrayList<Pair<ComboBox<String>,TextField>> temp = new ArrayList<>();

        for (int i = 0; i <comboRewards.size(); i++){
            temp.add(new Pair<>(comboRewards.get(i),txtRewardList.get(i)));
        }

        temp.forEach(pair -> pair.getKey().getSelectionModel().selectedItemProperty().addListener(comb -> {
            final String selectedItem = pair.getKey().getSelectionModel().getSelectedItem();
            TextField txtField = pair.getValue();

            txtField.setDisable(false);

            if (txtField.getText().equals("random")) txtField.setText(" ");

            if (selectedItem.equals("item"))
                refreshAutocompletion(txtField, "items");
            else if (selectedItem.equals("spell"))
                refreshAutocompletion(txtField, "spells");
            else if (selectedItem.equals("itempool")){
                refreshAutocompletion(txtField, "itempool");
            }
            else if (new ArrayList(Arrays.asList("injury","curse","ally")).contains(selectedItem)) {
                refreshAutocompletion(txtField,"none");
                txtField.setDisable(true);
                txtField.setText("random");
            } else {
                refreshAutocompletion(txtField,"none");
            }
        }));
    }

    /**
     * Recursively clears input on every control
     * @param startPoint
     */

    void clearCOntrols(Parent startPoint){
        startPoint.getChildrenUnmodifiable().forEach(child -> {
            if (child instanceof TextField){
                ((TextField)child).clear();
            } else if (child instanceof ComboBox) {
                ((ComboBox) child).getSelectionModel().select(0);
            }
            else if (child instanceof TextArea){
                ((TextArea)child).clear();
            }
            else if (child instanceof CheckBox){
                ((CheckBox)child).setSelected(false);
            }
            else if (child instanceof Parent){
                clearCOntrols((Parent)child);
            }
        }
        );
    }

    /**
     * Implements button logic
     */


    void installHandlers() {

        btnSaveUser.setOnAction(evt -> {
            final File author = Paths.get(System.getProperty("user.home"), "WOHMaker", "author.txt").toFile();
            if (author.exists())
                author.delete();

            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(author))) {
                writer.write(txtAuthor.getText() + System.lineSeparator() + txtAuthor.getText());
                btnSaveUser.setDisable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnCLear.setOnAction(evt -> {

            clearCOntrols(txtSuccessA.getScene().getRoot());

            imgArt.setImage(new Image("/admirer.png"));
            eventArt=new Image("/admirer.png");
            final File author = Paths.get(System.getProperty("user.home"), "WOHMaker", "author.txt").toFile();

            List<String> authorData = null;
            try {
                authorData = Files.readAllLines(Paths.get(author.toURI()));
                txtAuthor.setText(authorData.get(0));
                txtContact.setText(authorData.get(1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnLoadPic.setOnAction(evt -> {

            FileChooser fileChooser = null;
            File img = null;

            txtRewardList.forEach(txt -> {
                if (txt.getText().equals("random")) txt.setText(" ");
            });

            try {
                fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home"),"AppData", "Local", "wohgame").toFile());
                fileChooser.getExtensionFilters()
                    .setAll(new FileChooser.ExtensionFilter("Image files", "*.png", "*.bmp", "*.gif"));
                fileChooser.setTitle("Select custom event art");
                img = fileChooser.showOpenDialog(btnLoadPic.getScene().getWindow());

            } catch (Exception e) {
                fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
                fileChooser.setTitle("WOH Folder couldn't be located, please browser manually.");
                img = fileChooser.showOpenDialog(btnLoadPic.getScene().getWindow());
            }

            if (!Objects.isNull(img)) {
                txtPic.setText(img.getName());
                imgArt.setImage(new Image(img.toURI().toString()));
                eventArt = imgArt.getImage();
                doImageCalculations();
            }
        });

        imgArt.setOnMouseClicked(evt -> {
            if (!imageViewDialog.isShowing()) {
                imageViewDialog.setHeaderText(txtPic.getText());
                final ImageView imageView = new ImageView(imgArt.getImage());
                imageView.setFitHeight(700);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(false);
                VBox content = new VBox(5);
                content.setAlignment(Pos.CENTER_RIGHT);
                content.getChildren().add(imageView);
                imageViewDialog.getDialogPane().setContent(content);
                imageViewDialog.show();
            } else {
                imageViewDialog.getDialogPane().requestFocus();
            }
        });

        linkDiscord.setOnAction(evt -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop()
                        .browse(new URI("https://discord.com/channels/324155954059018240/690549472542851132"));
                } else {
                    // Ubuntu
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("/usr/bin/firefox -new-window "
                        + "https://discord.com/channels/324155954059018240/690549472542851132");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });


        linkRepo.setOnAction(evt -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop()
                        .browse(new URI("https://github.com/AlejandroRepo/WOHMakerEV"));
                } else {
                    // Ubuntu
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("/usr/bin/firefox -new-window "
                        + "https://github.com/AlejandroRepo/WOHMakerEV");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });


        btnSaveIto.setOnAction(evt -> {

            FileChooser fileChooser = null;
            File ito = null;

            txtRewardList.forEach(txt -> {
                if (txt.getText().equals("random")) txt.setText(" ");
            });

            try {
                fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home"),"AppData", "Local", "wohgame").toFile());
                fileChooser.getExtensionFilters().setAll((new FileChooser.ExtensionFilter("ITO files","*.ito")));
                fileChooser.setTitle("Save inside 'Custom', 'sandbox' or 'test'!");
                ito = fileChooser.showSaveDialog(btnLoadPic.getScene().getWindow());

            } catch (Exception e) {
                fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
                fileChooser.setTitle("WOH Folder couldn't be located, please browser manually.");
                ito = fileChooser.showSaveDialog(btnLoadPic.getScene().getWindow());
            }
            if (!Objects.isNull(ito)) {
                try {
                    boolean success = saveIto(ito);
                    Alert alert = new Alert(success? AlertType.INFORMATION: AlertType.ERROR);
                        alert.setTitle("WOHMaker");
                        alert.setHeaderText(success? "Event saved" : "Error");
                        alert.setContentText(success? "Event was stored sucessfully to "+ito.getAbsolutePath(): "Couldn't save event");
                        alert.getButtonTypes().setAll(ButtonType.OK);
                        alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnHideHelp.setOnMouseClicked(evt -> {
            helpArea.setVisible(!helpArea.isVisible());
            helpAreatxt.setVisible(!helpArea.isVisible());
            savePrefs("enableHelp", helpArea.isVisible());
        });

        btnLoadIto.setOnAction(evt -> {

            FileChooser fileChooser = null;
            File ito = null;

            try {
                fileChooser = new FileChooser();
                fileChooser.setInitialDirectory( Paths.get(System.getProperty("user.home"), "AppData", "Local", "wohgame").toFile());
                fileChooser.getExtensionFilters().setAll((new FileChooser.ExtensionFilter("ITO files", "*.ito")));
                fileChooser.setTitle("Select custom event file");
                ito = fileChooser.showOpenDialog(btnLoadPic.getScene().getWindow());
            }catch (Exception e){
                fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
                fileChooser.setTitle("WOH Folder couldn't be located, please browser manually.");
                ito = fileChooser.showSaveDialog(btnLoadPic.getScene().getWindow());
            }

            if (!Objects.isNull(ito)) {
                loadIto(ito);
            }
        });
    }

    /**
     * Creates prefs file
     */

    void createPrefs(){
        prefs = Paths.get(System.getProperty("user.home"), "WOHMaker", "prefs.dat").toFile();
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(prefs))) {
            writer.write("enableHelp=true"+System.lineSeparator() +
                "forceRefresh=false"+System.lineSeparator() +
                "enableArts=true"+System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads prefs file from disk
     */

    void readPrefs(){
        if (prefs.exists()) {
            try {
                List<String> strings = Files.readAllLines(prefs.toPath());
                strings.forEach(str -> {
                    if (str.contains("enableHelp")) helpArea.setVisible(str.equals("enableHelp=true"));
                    if (str.contains("enableHelp")) helpAreatxt.setVisible(str.equals("enableHelp=true"));
                    if (str.contains("forceRefresh")) forceFileRefresh=str.equals("forceRefresh=true");
                    if (str.contains("enableArts")) {
                        imgGuiArt.setVisible(str.equals("enableArts=true"));
                        imgGuiArt2.setVisible(str.equals("enableArts=true"));
                        imgGuiArt3.setVisible(str.equals("enableArts=true"));
                        imgGuiArt4.setVisible(str.equals("enableArts=true"));
                        imgGuiArt5.setVisible(str.equals("enableArts=true"));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createPrefs();
        }
    }

    /**
     * Writes prefs file
     */

    private void savePrefs(String property, boolean state) {

        if (prefs.exists()) {
            try {
                List<String> strings = Files.readAllLines(prefs.toPath());
                for (int i = 0; i < strings.size(); i++) {
                    if (strings.get(i).contains(property+"="+!state)) {
                        strings.set(i, property+"="+state);
                        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(prefs))) {
                            strings.forEach(str -> {
                                try {
                                    writer.write(str+ System.lineSeparator());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createPrefs();
            savePrefs(property,state);
        }
    }

    /**
     * Updates the autocompletion list depending on the type of rewards
     */

     void refreshAutocompletion(TextField target, String type) {
         SuggestionProvider suggestionProvider = null;

         if (target.getUserData()==null) {
             suggestionProvider = SuggestionProvider.create(new ArrayList<>());
             target.setUserData(suggestionProvider);
         }
             suggestionProvider = (SuggestionProvider) target.getUserData();
             suggestionProvider.clearSuggestions();
             suggestionProvider.addPossibleSuggestions(type.equals("items") ? itemList : type.equals("spells") ? spellList : type.equals("itempool")? itemPools : new ArrayList());
             new AutoCompletionTextFieldBinding(target, suggestionProvider);

     }

    /**
     * Ticks/unticks the big art check depending on image res and warns about resolution not corresponding to woh sizes.
     */

    void doImageCalculations(){

        if ((eventArt.getWidth()==195 && eventArt.getHeight()==164)){
            lblImgWarn.setVisible(false);
            chkBigArt.setIndeterminate(false);
            chkBigArt.setSelected(false);
            imgArt.setFitWidth(130);
            imgArt.setTranslateY(40);
            imgArt.setTranslateX(0);
        } else if ((eventArt.getWidth()==506 && eventArt.getHeight()==220)){
            lblImgWarn.setVisible(false);
            chkBigArt.setIndeterminate(false);
            chkBigArt.setSelected(true);
            imgArt.setFitWidth(175);
            imgArt.setTranslateY(0);
            imgArt.setTranslateX(-10);
        } else {
            lblImgWarn.setVisible(true);
            chkBigArt.setSelected(false);
            chkBigArt.setIndeterminate(true);
            imgArt.setFitWidth(150);
            imgArt.setTranslateY(0);
            imgArt.setTranslateX(0);
            lblImgWarn.setText("Image resolution doesn't seem correct");
        }
    }

    // STRINGS

    private static final String REWARDINFO = "Autocompletion is your friend.";
    private static final String TESTINFO = "'Story' means  auto-success. 'Funds'succeed if player owns at least 1/2 funds, and removes them.";
    private static final String VISUALINFO = "Visual effect which will be triggered when this outcome is reached.";
    private static final String EXTRAREWARDSINFO = "Please use integers.";

    /**
     * Listens to mouse hover to fill the help panel.
     */

    void implementHelpSystem(){

        textTitle.hoverProperty().addListener((ob,old,newValue) -> {if (newValue) helpAreatxt.setText("Shown on the upper-left part of the screen and mod menu.");});
        textDesc.hoverProperty().addListener(inv -> helpAreatxt.setText("Shown only in the mod menu. \nSmall! around 25 chars maximum."));
        textFlav.hoverProperty().addListener(inv -> helpAreatxt.setText("Around 350 chars max. \nLine breaks will be respected."));
        txtAuthor.hoverProperty().addListener(inv -> helpAreatxt.setText("Shown in the lower-left part of the event screen, and mod menu."));
        txtContact.hoverProperty().addListener(inv -> helpAreatxt.setText("Not shown in-game. A way for old god panstasz to contact you shall it be necessary."));
        txtSuccessA.hoverProperty().addListener(inv -> helpAreatxt.setText("Outcome text."));
        txtSuccessB.hoverProperty().addListener(inv -> helpAreatxt.setText("Outcome text."));
        txtSuccessC.hoverProperty().addListener(inv -> helpAreatxt.setText("Outcome text."));
        linkRepo.hoverProperty().addListener(inv -> helpAreatxt.setText("Feel free to do forks and throw pull requests."));
        linkDiscord.hoverProperty().addListener(inv -> helpAreatxt.setText("WOH community awaits virgin blood..."));
        txtFailureA.hoverProperty().addListener(inv -> helpAreatxt.setText("Text shown if failing the check for option A."));
        txtFailureB.hoverProperty().addListener(inv -> helpAreatxt.setText("Text shown if failing the check for option B."));
        txtFailureC.hoverProperty().addListener(inv -> helpAreatxt.setText("Text shown if failing the check for option C."));
        cmbOptions.hoverProperty().addListener(inv -> helpAreatxt.setText("Number of choices presented to the player in this event."));
        cmbLocation.hoverProperty().addListener(inv -> helpAreatxt.setText("God-dependant locations are global."));
        chkWavy.hoverProperty().addListener(inv -> helpAreatxt.setText("If enabled, the art will undulate at the given speed."));
        sldWavy.hoverProperty().addListener(inv -> helpAreatxt.setText("The higher, the quickers the wavy animation will be"));
        chkBigArt.hoverProperty().addListener(inv -> helpAreatxt.setText("Big arts take the whole event screen. Will be selected automatically when loading a pic."));
        btnLoadIto.hoverProperty().addListener(inv -> helpAreatxt.setText("Loads an already created event and parses its contents."));
        btnSaveUser.hoverProperty().addListener(inv -> helpAreatxt.setText("Persists user/contact information so it's filled automatically when loading this app."));
        btnExit.hoverProperty().addListener(inv -> helpAreatxt.setText("Closes the app, any unsaved change will be lost!"));
        btnLoadPic.hoverProperty().addListener(inv -> helpAreatxt.setText("Select the art to display in the event. Small events are 195x164, while big ones are 506x2020. Avoid using other resolutions."));
        btnSaveIto.hoverProperty().addListener(inv -> helpAreatxt.setText("Saves the event to disk as an .ito file. Put them in custom, sandbox or test sub-folders."));

        txtExtraRewards.forEach(txt -> txt.hoverProperty().addListener(inv -> helpAreatxt.setText(EXTRAREWARDSINFO)));
        txtRewardList.forEach(txt -> txt.hoverProperty().addListener(inv -> helpAreatxt.setText(REWARDINFO)));
        comboExtraRewards.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpAreatxt.setText(EXTRAREWARDSINFO)));
        comboChecks.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpAreatxt.setText(TESTINFO)));
        comboRewards.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpAreatxt.setText(REWARDINFO)));
        comboVisual.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpAreatxt.setText(VISUALINFO)));
    }

    static void setSmallScreenMode(boolean state){
        smallScreenMode.setValue(state);
    }

    /**
     * Charges the comboboxes with valid Strings.
     * @throws IOException if files cannot be accessed
     */

    public void loadData() throws IOException {

        final File wohMaker = Paths.get(System.getProperty("user.home"), "WOHMaker").toFile();
        if (!wohMaker.exists()) wohMaker.mkdir();

        // reads author data, or generates one

        final File author = Paths.get(System.getProperty("user.home"), "WOHMaker", "author.txt").toFile();
        if (!author.exists() || forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(author))) {
                writer.write(System.getProperty("user.name") + System.lineSeparator() + "@" + System.getProperty("user.name"));
            }
        }

        List<String> authorData = Files.readAllLines(Paths.get(author.toURI()));
        txtAuthor.setText(authorData.get(0));
        txtContact.setText(authorData.get(1));

        // reads valid locations from disk. If no file is present, generates one with currently known strings.

        final File locations = Paths.get(System.getProperty("user.home"), "WOHMaker", "locations.txt").toFile();
        if (!locations.exists() || forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(locations))) {
                for (String s: locationsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else{
            locationsList = Files.readAllLines(Paths.get(locations.toURI()));
        }

        cmbLocation.getItems().addAll(locationsList);
        cmbLocation.getSelectionModel().select(0);

        // reads valid stat checks from disk. If no file is present, generates one with currently known strings.
        final File statchecks = Paths.get(System.getProperty("user.home"), "WOHMaker", "statchecks.txt").toFile();
        if (!statchecks.exists() || forceFileRefresh){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(statchecks))) {
                for (String s: checksList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            checksList = Files.readAllLines(Paths.get(statchecks.toURI()));
        }

        comboCheckA.getItems().addAll(checksList);
        comboCheckB.getItems().addAll(checksList);
        comboCheckC.getItems().addAll(checksList);
        comboCheckA.getSelectionModel().select(0);
        comboCheckB.getSelectionModel().select(0);
        comboCheckC.getSelectionModel().select(0);

        // reads valid rewards from disk. If no file is present, generates one with currently known strings.
        final File rewards = Paths.get(System.getProperty("user.home"), "WOHMaker", "rewards.txt").toFile();
        if (!rewards.exists() || forceFileRefresh){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(rewards))) {
                for (String s: rewardsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            rewardsList = Files.readAllLines(Paths.get(rewards.toURI()));
        }

        // reads spells data, or generates one

        final File spells = Paths.get(System.getProperty("user.home"), "WOHMaker", "spells.txt").toFile();
        if (!spells.exists() || forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(spells))) {
                for (String s : spellList) {
                    writer.write(s + System.lineSeparator());
                }
            }
        } else {
            spellList = Files.readAllLines(Paths.get(spells.toURI()));
        }

        comboRewards.forEach(cmb -> {
            cmb.getItems().addAll(rewardsList);
            cmb.getSelectionModel().select(0);
        });

        // reads valid itemlist from disk. If no file is present, generates one with currently known strings.
        final File items = Paths.get(System.getProperty("user.home"), "WOHMaker", "itemlist.txt").toFile();
        if (!items.exists() || forceFileRefresh){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(items))) {
                for (String s: itemList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            itemList = Files.readAllLines(Paths.get(items.toURI()));
        }

        // reads valid extra rewards from disk. If no file is present, generates one with currently known strings.
        final File extraRewards = Paths.get(System.getProperty("user.home"), "WOHMaker", "extrarewards.txt").toFile();
        if (!extraRewards.exists() || forceFileRefresh){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(extraRewards))) {
                for (String s: extraRewardsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            extraRewardsList = Files.readAllLines(Paths.get(extraRewards.toURI()));
        }

        comboExtraRewards.forEach(cmb -> {
            cmb.getItems().addAll(extraRewardsList);
            cmb.getSelectionModel().select(0);
        });

        // reads valid visual effects from disk. If no file is present, generates one with currently known strings.
        final File visualEffects = Paths.get(System.getProperty("user.home"), "WOHMaker", "visualeffects.txt").toFile();
        if (!visualEffects.exists() || forceFileRefresh){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(visualEffects))) {
                for (String s: visualEffectsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            visualEffectsList = Files.readAllLines(Paths.get(visualEffects.toURI()));
        }

        comboVisual.forEach(cmb -> {
            cmb.getItems().addAll(visualEffectsList);
            cmb.getSelectionModel().select(0);
        });

        cmbOptions.getItems().addAll("1","2","3");

        savePrefs("forceRefresh",false);
        readPrefs();
    }

    void loadIto(File ito){

        try {
            List<String> strings = Files.readAllLines(ito.toPath());
            for (String s: strings){
                String substring;
                try {
                    if (s.contains("\"")) {
                        substring = s.substring(s.indexOf('"')+1, s.lastIndexOf('"'));

                    if (s.startsWith("name")) textTitle.setText(substring);
                    if (s.startsWith("location")) cmbLocation.getSelectionModel().select(substring);
                    if (s.startsWith("options")) cmbOptions.getSelectionModel().select(substring);
                    if (s.startsWith("about")) textDesc.setText(substring);
                    if (s.startsWith("flavor")) textFlav.setText(substring.replace("#", "\n"));
                    if (s.startsWith("wavy_art")) chkWavy.setSelected(substring.equals("1"));
                    if (s.startsWith("wavy_speed")) sldWavy.setValue(Double.parseDouble(substring));
                    if (s.startsWith("optiona")) txtOptionA.setText(substring.replace("#", "\n"));
                    if (s.startsWith("optionb")) txtOptionB.setText(substring.replace("#", "\n"));
                    if (s.startsWith("optionc")) txtOptionC.setText(substring.replace("#", "\n"));
                    if (s.startsWith("testa")) comboCheckA.getSelectionModel().select(substring);
                    if (s.startsWith("testb")) comboCheckB.getSelectionModel().select(substring);
                    if (s.startsWith("testc")) comboCheckC.getSelectionModel().select(substring);
                    if (s.startsWith("successa")) txtSuccessA.setText(substring.replace("#", "\n"));
                    if (s.startsWith("successb")) txtSuccessB.setText(substring.replace("#", "\n"));
                    if (s.startsWith("successc")) txtSuccessC.setText(substring.replace("#", "\n"));
                    if (s.startsWith("failurea")) txtFailureA.setText(substring.replace("#", "\n"));
                    if (s.startsWith("failureb")) txtFailureB.setText(substring.replace("#", "\n"));
                    if (s.startsWith("failurec")) txtFailureC.setText(substring.replace("#", "\n"));
                    if (s.startsWith("author")) txtAuthor.setText(substring);
                    if (s.startsWith("contact")) txtContact.setText(substring);
                    if (s.startsWith("winprizea")) cmbRewardsA.getSelectionModel().select(substring);
                    if (s.startsWith("winprizeb")) cmbRewardsB.getSelectionModel().select(substring);
                    if (s.startsWith("winprizec")) cmbRewardsC.getSelectionModel().select(substring);
                    if (s.startsWith("failprizea")) cmbRewardsAF.getSelectionModel().select(substring);
                    if (s.startsWith("failprizeb")) cmbRewardsBF.getSelectionModel().select(substring);
                    if (s.startsWith("failprizec")) cmbRewardsCF.getSelectionModel().select(substring);
                    if (s.startsWith("extra_winprizea")) cmbExtraRewardsA.getSelectionModel().select(substring);
                    if (s.startsWith("extra_winprizeb")) cmbExtraRewardsB.getSelectionModel().select(substring);
                    if (s.startsWith("extra_winprizec")) cmbExtraRewardsC.getSelectionModel().select(substring);
                    if (s.startsWith("extra_failprizea")) cmbExtraRewardsAF.getSelectionModel().select(substring);
                    if (s.startsWith("extra_failprizeb")) cmbExtraRewardsBF.getSelectionModel().select(substring);
                    if (s.startsWith("extra_failprizec")) cmbExtraRewardsCF.getSelectionModel().select(substring);
                    if (s.startsWith("winnumbera")) txtRewardA.setText(substring);
                    if (s.startsWith("winnumberb")) txtRewardB.setText(substring);
                    if (s.startsWith("winnumberc")) txtRewardC.setText(substring);
                    if (s.startsWith("failnumbera")) txtRewardAF.setText(substring);
                    if (s.startsWith("failnumberb")) txtRewardBF.setText(substring);
                    if (s.startsWith("failnumberc")) txtRewardCF.setText(substring);
                    if (s.startsWith("extra_failnumbera")) txtExtraRewardAF.setText(substring);
                    if (s.startsWith("extra_failnumberb")) txtExtraRewardBF.setText(substring);
                    if (s.startsWith("extra_failnumberc")) txtExtraRewardCF.setText(substring);
                    if (s.startsWith("extra_winnumbera")) txtExtraRewardA.setText(substring);
                    if (s.startsWith("extra_winnumberb")) txtExtraRewardB.setText(substring);
                    if (s.startsWith("extra_winnumberc")) txtExtraRewardC.setText(substring);
                    if (s.startsWith("wineffecta")) cmbVisualA.getSelectionModel().select(substring);
                    if (s.startsWith("wineffectb")) cmbVisualB.getSelectionModel().select(substring);
                    if (s.startsWith("wineffectc")) cmbVisualC.getSelectionModel().select(substring);
                    if (s.startsWith("faileffecta")) cmbVisualAF.getSelectionModel().select(substring);
                    if (s.startsWith("faileffectb")) cmbVisualBF.getSelectionModel().select(substring);
                    if (s.startsWith("faileffectc")) cmbVisualCF.getSelectionModel().select(substring);
                    if (s.startsWith("image")) {
                        currentImage = substring;
                        imgArt.setImage(new Image(ito.getParentFile().toURI().toString().replace(".ito","").concat(File.separator).concat(substring)));
                        txtPic.setText(substring);
                        eventArt = imgArt.getImage();
                        doImageCalculations();
                    }
                    }
                }catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean saveIto(File ito) throws IOException {
    try{
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(ito.toPath(), Charset.defaultCharset())) {

            System.out.println(Paths.get(ito.getParentFile().toPath().toString(),txtPic.getText()));
            Paths.get(ito.getParentFile().toPath().toString(),txtPic.getText()).toFile().mkdirs();
            ImageIO.write(SwingFXUtils.fromFXImage(imgArt.getImage(), null), "png", Paths.get(ito.getParentFile().toPath().toString(),txtPic.getText()).toFile());


            bufferedWriter.write("[event]" + System.lineSeparator());
            bufferedWriter.write(
                "name=\"" + textTitle.getText() + "\"" + System.lineSeparator() +
                    "location=\"" + cmbLocation.getSelectionModel().getSelectedItem() + "\"" + System.lineSeparator() +
                    "about=\"" + textDesc.getText() + "\"" + System.lineSeparator() +
                    "author=\"" + txtAuthor.getText() + "\"" + System.lineSeparator() +
                    "contact=\"" + txtContact.getText() + "\"" + System.lineSeparator()+System.lineSeparator() +
                    "flavor=\"" + textFlav.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                    "image=\"" + txtPic.getText() +"\"" +System.lineSeparator()+
                        "big=\"" + (chkBigArt.isSelected() ? "1" : "0") + "\"" + System.lineSeparator() +
                            "wavy_art=\"" + (chkWavy.isSelected() ? "1" : "0") + "\"" + System.lineSeparator() +
                            "wavy_speed=\"" + String.format("%.1f", sldWavy.getValue()) + "\"" + System.lineSeparator()
                            +
                            "options=\"" + cmbOptions.getSelectionModel().getSelectedItem() + "\"" + System
                            .lineSeparator() + System.lineSeparator() + System.lineSeparator() +

                            "optiona=\"" + txtOptionA.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "testa=\"" + comboCheckA.getSelectionModel().getSelectedItem() + "\"" + System
                            .lineSeparator() + System.lineSeparator() +
                            "successa=\"" + txtSuccessA.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "winprizea=\"" + (cmbRewardsA.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbRewardsA.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "winnumbera=\"" + txtRewardA.getText() + "\"" + System.lineSeparator() +
                            "extra_winprizea=\"" + (
                            cmbExtraRewardsA.getSelectionModel().getSelectedItem().equals("none") ? ""
                                : cmbExtraRewardsA.getSelectionModel().getSelectedItem()) + "\"" + System
                            .lineSeparator() +
                            "extra_winnumbera=\"" + txtExtraRewardA.getText() + "\"" + System.lineSeparator() +
                            "wineffecta=\"" + (cmbVisualA.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbVisualA.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() + System
                            .lineSeparator() +
                            "failurea=\"" + txtFailureA.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "failprizea=\"" + (cmbRewardsAF.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbRewardsAF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "failnumbera=\"" + txtRewardAF.getText() + "\"" + System.lineSeparator() +
                            "extra_failprizea=\"" + (
                            cmbExtraRewardsAF.getSelectionModel().getSelectedItem().equals("none") ? ""
                                : cmbExtraRewardsAF.getSelectionModel().getSelectedItem()) + "\"" + System
                            .lineSeparator() +
                            "extra_failnumbera=\"" + txtExtraRewardAF.getText() + "\"" + System.lineSeparator() +
                            "faileffecta=\"" + (cmbVisualAF.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbVisualAF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()
                            + System.lineSeparator() + System.lineSeparator() +

                            "optionb=\"" + txtOptionB.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "testb=\"" + comboCheckB.getSelectionModel().getSelectedItem() + "\"" + System
                            .lineSeparator() + System.lineSeparator() +
                            "successb=\"" + txtSuccessB.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "winprizeb=\"" + (cmbRewardsB.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbRewardsB.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "winnumberb=\"" + txtRewardB.getText() + "\"" + System.lineSeparator() +
                            "extra_winprizeb=\"" + (
                            cmbExtraRewardsB.getSelectionModel().getSelectedItem().equals("none") ? ""
                                : cmbExtraRewardsB.getSelectionModel().getSelectedItem()) + "\"" + System
                            .lineSeparator() +
                            "extra_winnumberb=\"" + txtExtraRewardB.getText() + "\"" + System.lineSeparator() +
                            "wineffectb=\"" + (cmbVisualB.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbVisualB.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() + System
                            .lineSeparator() +
                            "failureb=\"" + txtFailureB.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "failprizeb=\"" + (cmbRewardsBF.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbRewardsBF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "failnumberb=\"" + txtRewardBF.getText() + "\"" + System.lineSeparator() +
                            "extra_failprizeb=\"" + (
                            cmbExtraRewardsBF.getSelectionModel().getSelectedItem().equals("none") ? ""
                                : cmbExtraRewardsBF.getSelectionModel().getSelectedItem()) + "\"" + System
                            .lineSeparator() +
                            "extra_failnumberb=\"" + txtExtraRewardBF.getText() + "\"" + System.lineSeparator() +
                            "faileffectb=\"" + (cmbVisualBF.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbVisualBF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()
                            + System.lineSeparator() + System.lineSeparator() +

                            "optionc=\"" + txtOptionC.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "testc=\"" + comboCheckC.getSelectionModel().getSelectedItem() + "\"" + System
                            .lineSeparator() + System.lineSeparator() +
                            "successc=\"" + txtSuccessC.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "winprizec=\"" + (cmbRewardsC.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbRewardsC.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "winnumberc=\"" + txtRewardC.getText() + "\"" + System.lineSeparator() +
                            "extra_winprizec=\"" + (
                            cmbExtraRewardsC.getSelectionModel().getSelectedItem().equals("none") ? ""
                                : cmbExtraRewardsC.getSelectionModel().getSelectedItem()) + "\"" + System
                            .lineSeparator() +
                            "extra_winnumberc=\"" + txtExtraRewardC.getText() + "\"" + System.lineSeparator() +
                            "wineffectc=\"" + (cmbVisualC.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbVisualC.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() + System
                            .lineSeparator() +
                            "failurec=\"" + txtFailureC.getText().replace("\n", "#") + "\"" + System.lineSeparator() +
                            "failprizec=\"" + (cmbRewardsCF.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbRewardsCF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "failnumberc=\"" + txtRewardCF.getText() + "\"" + System.lineSeparator() +
                            "extra_failprizec=\"" + (
                            cmbExtraRewardsCF.getSelectionModel().getSelectedItem().equals("none") ? ""
                                : cmbExtraRewardsCF.getSelectionModel().getSelectedItem()) + "\"" + System
                            .lineSeparator() +
                            "extra_failnumberc=\"" + txtExtraRewardCF.getText() + "\"" + System.lineSeparator() +
                            "faileffectc=\"" + (cmbVisualCF.getSelectionModel().getSelectedItem().equals("none") ? ""
                            : cmbVisualCF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()
                            + System.lineSeparator() +

                            "--Made with WOHMaker--=" + System.lineSeparator());
            }

        return true;

        }catch(Exception e){
        e.printStackTrace();
        return false;
        }
    }
}
