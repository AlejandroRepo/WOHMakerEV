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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import javafx.util.StringConverter;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;

public class Controller  extends VBox implements Initializable {

    List<String> locationsList = Arrays.asList("downtown", "apartment", "school", "hospital", "seaside", "forest",
            "mansion", "schoolhospital", "seasideforest", "village", "atorasu", "athyola", "gozu", "ithotu");

    List<String> checksList = Arrays.asList(STORY, "strength", "dexterity", "perception", "knowledge", "luck",
            "charisma", "funds1", "funds2");

    List<String> itemList = Arrays.asList("STEAK KNIFE", "CAMERA", "NICE RING", "KENDO HELMET", "SEWING KIT",
            "BACKPACK", "WINE BOTTLE", "CIGARETTES", "SMALL CANDLE", "CARPENTER HAMMER", "HOLY CANDLE",
            "MUMMIFIED HEART", "LIBRARY NOTES", "POLICE REVOLVER", "FLASHLIGHT", "PAINKILLER", "BLUE GEM",
            "LONG PIG STEAK", "LUMP OF FLESH", "RITUAL MASK", "GRIMOIRE", "RITUAL DAGGER", "HUMAN SKULL", "GLASS EYE",
            "KATANA", "CURSED DOLL", "EMPTY BOTTLE", "BOTTLE [SLUDGE]", "BOTTLE [MILK]", "RITUAL ROBE",
            "CHAMPAGNE", "LOST TAPES", "DUST OF SEEING", "PRESCRIPTION PILLS", "STALKER'S MASK", "BASEBALL BAT",
            "FOREIGN CIGARETTES", "LIBRARY BOOK", "PANCAKES", "ARMY KNIFE", "ELDRITCH AMULET", "CURSED CARTRIDGE",
            "CURIOUS STATUETTE", "POCKET KNIFE", "SCALPEL", "LUCKY EARRINGS", "CORPSE DUST", "HUNTING RIFLE",
            "HAPPI COAT", "TAIYAKI", "WOODEN BAT", "MAP", "PRESCRIPTION", "BRANCH", "COMPASS", "ENERGY DRINK", "SALT",
            "PRAYER BEADS", "BROKEN BOTTLE", "CAN OF ACID", "TINY KEY", "GOBLET", "SMELLY MEAT", "CROWBAR", "FIRE AXE",
            "KARUKOSA MASK", "ANCIENT RING", "SHOVEL", "BANDAGE", "CURSED SCISSORS", "TORCH", "CRESTFALLEN MASK",
            "MEAT CLEAVER", "BLACK HAIR", "SEWING KIT [HAIR]", "TOME OF ROT", "FATAL FLORA", "DOG TREATS",
            "GRUESOME TOTEM", "PAIN MEDICATION", "EXPERIMENTAL DRUG", "EXTRA AMMO", "SPORT RIFLE", "BOTTLE [WATER]",
            "MEDICAL KIT", "OLD SHOTGUN", "ROCK RING", "PATINA RING", "DEMON MASK", "DIY FLAMETHROWER");

    List<String> spellList = Arrays.asList("MIND DRAIN", "REGENERATION", "SKIN PEEL", "MULTIPLY WOUND",
            "RITUAL OF KNOPHA", "INVISIBILITY", "ABOLISH", "ABSORB", "THIRD EYE", "FLAME OF ITHOTU", "SEAL OF SAVVESH",
            "SEAL OF BRAMEL", "THREAD OF FATE", "ANCESTRAL STRENGTH", "ENTTHRALLMENT", "AWAKEN", "BINDING AGONY",
            "WITNESS CURSE", "SHADOW SHROUD", "MEND", "VOID", "CAUTERIZE", "MEMORY EXTRACT", "ASHEN CONTRACT",
            "MIDASU TOCH", "FLESH REGROWTH", "EXPEL EVIL", "GROW TEETH");

    List<String> rewardsList = Arrays.asList("none", "experience", "stamina", "reason", "doom", "funds", "item",
            "itempool", "injury", "curse", "spell", "ally");




    List<String> extraRewardsList = Arrays.asList("none", "experience", "stamina", "reason", "doom");

    List<String> itemPools = Arrays.asList("mask", "book", "ring", "poor", "magicitem", "hardwareshop", "vendingshop",
            "dogshop", "pharmacyshop", "hideout");

    List<String> visualEffectsList = Arrays.asList("none", "whiteflash", "bloodsplat");

    public static final String VERSION = "1.6";

    public static final String APPNAME = "WOHMaker";

    public static final String USERHOME = System.getProperty("user.home");

    public static final String STORY = "story";

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
    Label lblVersion;

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
    ImageView showWarn;

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
    ImageView imgBack;

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
    ImageView imgLoc;

    @FXML
    Hyperlink linkDiscord;

    @FXML
    Hyperlink linkRepo;

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
    ImageView btnResetPic;

    @FXML
    Label lblCS;

    @FXML
    Label lblCF;

    @FXML
    CheckBox checkUserFolder;

    @FXML
    Tab tabSuccessA;

    @FXML
    HBox boxImg;

    @FXML
    Tab tabSuccessB;

    @FXML
    ImageView madTeacher;

    @FXML
    Tab tabSuccessC;

    @FXML
    ImageView btnRandomChars;

    @FXML
    ImageView band1;

    @FXML
    ImageView band2;

    @FXML
    StackPane stack1;

    @FXML
    StackPane stack2;

    @FXML
    VBox root;

    Dialog<Object> imageViewDialog = new Dialog<>();

    String currentImage = "";

    static BooleanProperty smallScreenMode = new SimpleBooleanProperty(false);

    boolean forceFileRefresh = false;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {

        Font.loadFont(Controller.class.getResource("/Silver.ttf").toExternalForm(), 10);

        prepareUI();

        this.readPrefs();

        try {
            this.loadData();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        this.refreshChars();
        this.installHandlers();
        this.installListeners();
        this.implementHelpSystem();

        path.addListener(inv -> {
            for (final String s : path.get()) {
                if (new File(s).exists()) {
                    this.loadIto(new File(s));
                }
            }
        });
    }


    Thread rotatingThread;

    private void prepareUI() {

        stack1.prefWidthProperty().bind(root.widthProperty());
        stack2.prefWidthProperty().bind(root.widthProperty());
        boxImg.widthProperty().addListener((ob,old,newValue) -> {
            imgArt.setFitWidth(newValue.floatValue()>50? newValue.floatValue() : 50);
        });

        btnCLear.setGraphic(new ImageView(new Image(Controller.class.getResource("/refresh2.png").toExternalForm())));
//
//        rotatingThread = new Thread(() -> {
//            while (true) {
//                Platform.runLater(() -> {
//                    madTeacher.setOpacity(0.65);
//                    madTeacher.setImage(new Image(Controller.class.getResource("/help3.png").toExternalForm()));
//                });
//                try {
//                    Thread.sleep(150);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Platform.runLater(() -> {
//                    madTeacher.setOpacity(1);
//                    madTeacher.setImage(new Image(Controller.class.getResource("/help2.png").toExternalForm()));
//                });
//                try {
//                    Thread.sleep(1000 * (new Random().nextInt(6)));
//                } catch (InterruptedException end) {
//                    Thread.interrupted();
//                }
//            }});
//        rotatingThread.setDaemon(true);
//        rotatingThread.start();



        List<TextArea> textAreasToAdjust = Arrays.asList(textFlav, txtSuccessA,txtSuccessB,txtSuccessC,txtFailureA,txtFailureB,txtFailureC);
        textAreasToAdjust.forEach(ta -> ta.widthProperty().addListener((ob, old, newValue) -> {
            if (newValue.floatValue()>ta.getPrefWidth()){
                ta.lookup(".content").setTranslateX(((newValue.floatValue() - ta.getPrefWidth()) /2));
            } else {
                ta.lookup(".content").setTranslateX(0);
            }
        }));

        textFlav.widthProperty().addListener((ob,old,newValue) -> {
            if (newValue.floatValue()>textFlav.getPrefWidth()){
                textFlav.lookup(".content").setTranslateX(((newValue.floatValue() - textFlav.getPrefWidth()) /2));
            } else {
                textFlav.lookup(".content").setTranslateX(0);
            }
        });

        this.comboRewards.addAll(Arrays.asList(this.cmbRewardsA, this.cmbRewardsAF, this.cmbRewardsB, this.cmbRewardsBF,
                        this.cmbRewardsC, this.cmbRewardsCF));
        this.comboExtraRewards.addAll(Arrays.asList(this.cmbExtraRewardsA, this.cmbExtraRewardsAF,
                this.cmbExtraRewardsB, this.cmbExtraRewardsBF,
                this.cmbExtraRewardsC, this.cmbExtraRewardsCF));
        this.txtRewardList.addAll(Arrays.asList(this.txtRewardA, this.txtRewardAF, this.txtRewardB, this.txtRewardBF,
                this.txtRewardC, this.txtRewardCF));
        this.comboVisual.addAll(Arrays.asList(this.cmbVisualA, this.cmbVisualAF, this.cmbVisualB, this.cmbVisualBF,
                this.cmbVisualC, this.cmbVisualCF));
        this.comboChecks.addAll(Arrays.asList(this.comboCheckA, this.comboCheckB, this.comboCheckC));
        this.lblWarnings.addAll(Arrays.asList(this.lblAS, this.lblAF, this.lblBS, this.lblBD, this.lblCS, this.lblCF,
                this.lblImgWarn, this.lblTooLong));
        this.txtExtraRewards.addAll(
                Arrays.asList(this.txtExtraRewardA, this.txtExtraRewardAF, this.txtExtraRewardB, this.txtExtraRewardBF,
                        this.txtExtraRewardC, this.txtExtraRewardCF));

        this.lblVersion.setText(VERSION);

        this.imgBack.setManaged(false);

        this.btnLoadPic.setGraphic(new ImageView(new Image("/load.png")));
        this.btnSaveUser.setGraphic(new ImageView("/save.png"));

        this.txtPic.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
    }

    void refreshChars() {

        int dice;
        final List<ImageView> chars = Arrays.asList(this.imgGuiArt, this.imgGuiArt2, this.imgGuiArt3, this.imgGuiArt4);

        for (int i = 1; i < chars.size() + 1; i++) {
            dice = new Random().nextInt(5);
            chars.get(i - 1)
                .setImage(new Image(
                        dice == 0 ? "/art" + i + ".png"
                                : dice == 1 ? "/art" + i + "b.png"
                                        : dice == 2 ? "/art" + i + "c.png"
                                                : dice == 3 ? "/art" + i + "d.png"
                                                        : "/art" + i + "e.png"));

        }

    }

    static ObjectProperty<String[]> path = new SimpleObjectProperty<>();

    static void setOpenWithPath(final String[] args) {
        path.set(args);
    }

    /**
     * Listens to things happening and does things
     */

    void installListeners() {

        this.textFlav.lengthProperty()
            .addListener((ob, old, newValue) -> this.lblTooLong.setVisible(newValue.intValue() > 350));
        this.comboCheckA.getSelectionModel()
            .selectedItemProperty()
            .addListener((ob, old, newValue) -> this.tabFailureA.setDisable(newValue.equals(STORY)));
        this.comboCheckB.getSelectionModel()
            .selectedItemProperty()
            .addListener((ob, old, newValue) -> this.tabFailureB.setDisable(newValue.equals(STORY)));
        this.comboCheckC.getSelectionModel()
            .selectedItemProperty()
            .addListener((ob, old, newValue) -> this.tabFailureC.setDisable(newValue.equals(STORY)));

        this.cmbOptions.getSelectionModel().selectedItemProperty().addListener((ob, old, newValue) -> {
            this.optionB.setDisable(newValue.equals("1"));
            this.optionC.setDisable(newValue.equals("1") || newValue.equals("2"));
        });

        smallScreenMode.addListener((ob, old, newValue) -> {
            this.band1.setManaged(!newValue);
            this.band1.setVisible(!newValue);
            this.band2.setVisible(!newValue);
            this.band2.setManaged(!newValue);
        });

        this.cmbLocation.getSelectionModel()
            .selectedItemProperty()
            .addListener((ob, old, newValue) -> {
                this.imgLoc.setImage(
                        new Image(Controller.class.getClassLoader().getResourceAsStream("icon" + newValue + ".png")));
            });

        this.cmbOptions.getSelectionModel().select(0);

        this.txtPic.textProperty().addListener((ob, old, newValue) -> this.btnResetPic.setDisable(newValue.isEmpty()));

        final ArrayList<Pair<Label, TextArea>> tempWarnList = new ArrayList<>();
        final ArrayList<TextArea> optionStrings = new ArrayList<>(
                Arrays.asList(this.txtSuccessA, this.txtFailureA, this.txtSuccessB, this.txtFailureB, this.txtSuccessC,
                        this.txtFailureC));

        for (int i = 0; i < this.comboRewards.size(); i++) {
            tempWarnList.add(new Pair<>(this.lblWarnings.get(i), optionStrings.get(i)));
        }

        tempWarnList.forEach(pair -> pair.getValue().textProperty().addListener((ob, old, newValue) -> {
            pair.getKey().setVisible(newValue.length() > 300);
            if (pair.getKey().isVisible()) {
                this.showWarn.setImage(new Image("/warnings.png"));
            }
        }));

        this.chkWavy.selectedProperty().addListener((ob, old, newValue) -> {
            this.sldWavy.setDisable(!newValue);
            this.lblWavyVal.setDisable(!newValue);
        });
        this.sldWavy.valueProperty()
            .addListener((ob, old, newValue) -> this.lblWavyVal.setText(String.format("%.1f", newValue.floatValue())));

        this.btnExit.setOnAction(evt -> System.exit(0));
        this.txtAuthor.setOnKeyReleased(evt -> this.btnSaveUser.setDisable(false));
        this.txtContact.setOnKeyReleased(evt -> this.btnSaveUser.setDisable(false));

        // listen to selected type of rewards and refreshes autocompletion lists for all related widgets
        this.setRewardsBehaviour();
        this.setExtraRewardsBehaviour();

        // Image dialog

        this.imgArt.sceneProperty().addListener(inv -> {
            this.imageViewDialog.initOwner(this.imgArt.getScene().getWindow());
            this.imageViewDialog.getDialogPane().getButtonTypes().setAll(ButtonType.CLOSE);
            this.imageViewDialog.initModality(Modality.NONE);
            this.imageViewDialog.setResizable(false);
            this.imageViewDialog.initStyle(StageStyle.UTILITY);
            this.imageViewDialog.getDialogPane().setStyle("-fx-background-color: black;");
        });
    }

    File prefs = Paths.get(USERHOME, APPNAME, "prefs.dat").toFile();


    void setRewardsBehaviour() {
        // listen to selected type of rewards and refreshes autocompletion lists for all related widgets
        final ArrayList<Pair<ComboBox<String>, TextField>> temp = new ArrayList<>();

        for (int i = 0; i < this.comboRewards.size(); i++) {
            temp.add(new Pair<>(this.comboRewards.get(i), this.txtRewardList.get(i)));
        }

        temp.forEach(pair -> pair.getKey().getSelectionModel().selectedItemProperty().addListener(comb -> {
            final String selectedItem = pair.getKey().getSelectionModel().getSelectedItem();
            final TextField txtField = pair.getValue();

            txtField.setDisable(false);
            txtField.setTextFormatter(null);

            if (txtField.getText().equals("random")) {
                txtField.setText(" ");
            }
            this.refreshAutocompletion(txtField, "none");

            if (selectedItem.equals("item")) {
                txtField.clear();
                this.refreshAutocompletion(txtField, "items");
            } else if (selectedItem.equals("spell")) {
                this.refreshAutocompletion(txtField, "spells");
                txtField.clear();
            } else if (selectedItem.equals("itempool")) {
                this.refreshAutocompletion(txtField, "itempool");
                txtField.clear();
            } else if (new ArrayList<>(Arrays.asList("injury", "curse", "ally")).contains(selectedItem)) {
                this.refreshAutocompletion(txtField, "none");
                txtField.setDisable(true);
                txtField.setText("random");
            } else if (selectedItem.equals("none")) {
                txtField.setDisable(true);
                txtField.clear();
                this.refreshAutocompletion(txtField, "none");
            } else {
                this.refreshAutocompletion(txtField, "none");
                txtField.setTextFormatter(new TextFormatter<Integer>(new StringConverter<Integer>() {
                    @Override
                    public String toString(final Integer integer) {
                        return integer == null ? "0" : String.valueOf(integer);
                    }

                    @Override
                    public Integer fromString(final String s) {
                        try {
                            return Integer.parseInt(s);
                        } catch (final NumberFormatException e) {
                            return 0;
                        }
                    }
                }));
            }
        }));
    }

    void setExtraRewardsBehaviour() {

        final ArrayList<Pair<ComboBox<String>, TextField>> temp = new ArrayList<>();

        for (int i = 0; i < this.comboExtraRewards.size(); i++) {
            temp.add(new Pair<>(this.comboExtraRewards.get(i), this.txtExtraRewards.get(i)));
        }

        temp.forEach(pair -> pair.getKey().getSelectionModel().selectedItemProperty().addListener(comb -> {
            final String selectedItem = pair.getKey().getSelectionModel().getSelectedItem();
            final TextField txtField = pair.getValue();

            txtField.setDisable(selectedItem.equals("none"));

            if (selectedItem.equals("none")) {
                txtField.setTextFormatter(null);
                txtField.clear();
            } else {
                // Integer converter for extra rewards

                txtField.setTextFormatter(new TextFormatter<Integer>(new StringConverter<>() {
                    @Override
                    public String toString(final Integer integer) {
                        return integer == null ? "0" : String.valueOf(integer);
                    }

                    @Override
                    public Integer fromString(final String s) {
                        try {
                            return Integer.parseInt(s);
                        } catch (final NumberFormatException e) {
                            return 0;
                        }
                    }
                }));
            }

        }));
    }

    /**
     * Recursively clears input on every control
     */

    void clearCOntrols(final Parent startPoint) {
        startPoint.getChildrenUnmodifiable().forEach(child -> {
            if (child instanceof TextField) {
                ((TextField) child).clear();
            } else if (child instanceof ComboBox) {
                ((ComboBox) child).getSelectionModel().select(0);
            } else if (child instanceof TextArea) {
                ((TextArea) child).clear();
            } else if (child instanceof CheckBox && !child.equals(this.checkUserFolder)) {
                ((CheckBox) child).setSelected(false);
            } else if (child instanceof Parent) {
                this.clearCOntrols((Parent) child);
            }
        });
    }

    /**
     * Implements button logic
     */

    Path imgPath = Paths.get(USERHOME, "AppData", "Local", "wohgame");

    Path filePath = Paths.get(USERHOME, "AppData", "Local", "wohgame");


    void installHandlers() {

        this.btnSaveUser.setOnAction(evt -> {
            final File author = Paths.get(USERHOME, APPNAME, "author.txt").toFile();
            if (author.exists()) {
                author.delete();
            }

            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(author))) {
                writer.write(this.txtAuthor.getText() + System.lineSeparator() + this.txtContact.getText());
                this.btnSaveUser.setDisable(true);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        this.showWarn.setOnMouseClicked(evt -> {

            this.lblWarnings.forEach(lbl -> {
                if (!Objects.isNull(lbl)) {
                    lbl.setVisible(false);
                }
            });

            this.showWarn.setImage(new Image("/warningsb.png"));
        });

        this.btnCLear.setOnAction(evt -> {

            this.clearCOntrols(this.txtSuccessA.getScene().getRoot());

            this.imgArt.setImage(new Image("/admirer.png"));
            this.eventArt = new Image("/admirer.png");
            final File author = Paths.get(USERHOME, "WOHMaker", "author.txt").toFile();

            List<String> authorData = null;
            try {
                authorData = Files.readAllLines(Paths.get(author.toURI()));
                this.txtAuthor.setText(authorData.get(0));
                this.txtContact.setText(authorData.get(1));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        this.btnResetPic.setOnMouseClicked(evt -> {
            this.imgArt.setImage(new Image("/admirer.png"));
            this.txtPic.clear();
            this.eventArt = this.imgArt.getImage();
            this.chkBigArt.setSelected(false);
            this.chkBigArt.setIndeterminate(false);
            this.sldWavy.setValue(0.5);
            this.chkWavy.setSelected(false);
        });

        this.btnRandomChars.setOnMouseClicked(evt -> this.refreshChars());

        this.checkUserFolder.setOnAction(evt -> {
            if (this.checkUserFolder.isSelected()
                    && (this.txtPic.getText().contains("/") || this.txtPic.getText().contains("\\"))) {
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(APPNAME);
                alert.setHeaderText("Are you sure...?");
                alert.setContentText("The image route '" + this.txtPic.getText() + "' already includes a folder!\n\n"
                        + "This will add yet another folder with your username and will probably "
                        + "lead to unwanted results.\n\n Please Make sure this is what you want.");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        });

        this.btnLoadPic.setOnAction(evt -> {

            FileChooser fileChooser = null;
            File img = null;

            this.txtRewardList.forEach(txt -> {
                if (txt.getText().equals("random")) {
                    txt.setText(" ");
                }
            });

            try {
                fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(this.imgPath.toFile());
                fileChooser.getExtensionFilters()
                    .setAll(new FileChooser.ExtensionFilter("Image files", "*.png", "*.bmp", "*.gif"));
                fileChooser.setTitle("Select custom event art");
                img = fileChooser.showOpenDialog(this.btnLoadPic.getScene().getWindow());

            } catch (final Exception e) {
                fileChooser.setInitialDirectory(Paths.get(USERHOME).toFile());
                fileChooser.setTitle(FOLDERNOTFOUND);
                img = fileChooser.showOpenDialog(this.btnLoadPic.getScene().getWindow());
            }

            if (!Objects.isNull(img)) {
                this.txtPic.setText(img.getName());
                this.imgArt.setImage(new Image(img.toURI().toString()));
                this.eventArt = this.imgArt.getImage();
                this.doImageCalculations();
                this.imgPath = Paths.get(img.getParentFile().toURI());
            }
        });

        this.imgArt.setOnMouseClicked(evt -> {
            if (!this.imageViewDialog.isShowing()) {
                this.imageViewDialog.setHeaderText(this.txtPic.getText());
                final ImageView imageView = new ImageView(this.imgArt.getImage());
                imageView.setFitHeight(Screen.getPrimary().getBounds().getHeight()
                        - (Screen.getPrimary().getBounds().getHeight() * 0.4));
                imageView.setPreserveRatio(true);
                imageView.setSmooth(false);
                final VBox content = new VBox(5);
                content.setAlignment(Pos.CENTER);;
                content.setPadding(new Insets(15));
                content.getChildren().add(imageView);
                content.getStyleClass().add("imgDiag");
                content.getStylesheets().add("styles.css");
                content.setPrefSize(imageView.getFitWidth()+20,imageView.getFitHeight()+20);
                this.imageViewDialog.getDialogPane().setContent(content);
                this.imageViewDialog.show();
            } else {
                this.imageViewDialog.getDialogPane().requestFocus();
            }
        });

        this.linkDiscord.setOnAction(evt -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop()
                        .browse(new URI("https://discordapp.com/invite/bEPAtBY"));
                } else {
                    // Ubuntu
                    final Runtime runtime = Runtime.getRuntime();
                    runtime.exec("/usr/bin/firefox -new-window "
                            + "https://discordapp.com/invite/bEPAtBY");
                }
            } catch (final IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        this.linkRepo.setOnAction(evt -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop()
                        .browse(new URI("https://github.com/AlejandroRepo/WOHMakerEV"));
                } else {
                    // Ubuntu
                    final Runtime runtime = Runtime.getRuntime();
                    runtime.exec("/usr/bin/firefox -new-window "
                            + "https://github.com/AlejandroRepo/WOHMakerEV");
                }
            } catch (final IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        this.btnSaveIto.setOnAction(evt -> {

            this.txtRewardList.forEach(txt -> txt.requestFocus()); // forces a pass of the integer formatter before
                                                                   // saving
            this.txtExtraRewards.forEach(txt -> txt.requestFocus());

            FileChooser fileChooser = null;
            File ito = null;

            this.txtRewardList.forEach(txt -> {
                if (txt.getText().equals("random")) {
                    txt.setText(" ");
                }
            });

            try {
                fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(
                        Paths.get(USERHOME, "AppData", "Local", "wohgame").toFile());
                fileChooser.getExtensionFilters().setAll((new FileChooser.ExtensionFilter("ITO files", "*.ito")));
                fileChooser.setTitle("Save inside 'Custom', 'sandbox' or 'test'!");
                ito = fileChooser.showSaveDialog(this.btnLoadPic.getScene().getWindow());

            } catch (final Exception e) {
                fileChooser.setInitialDirectory(Paths.get(USERHOME).toFile());
                fileChooser.setTitle(FOLDERNOTFOUND);
                ito = fileChooser.showSaveDialog(this.btnLoadPic.getScene().getWindow());
            }
            if (!Objects.isNull(ito)) {
                final boolean success = this.saveIto(ito);
                final Alert alert = new Alert(success ? AlertType.INFORMATION : AlertType.ERROR);
                alert.setTitle(APPNAME);
                alert.setHeaderText(success ? "Event saved" : "Error");
                alert.setContentText(success ? "Event was stored sucessfully to " + ito.getAbsolutePath()
                        : "Couldn't save event. ");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();
            }
        });


        this.btnHideHelp.setOnMouseClicked(evt -> {
            this.helpArea.setVisible(!this.helpArea.isVisible());
            this.helpAreatxt.setVisible(!this.helpAreatxt.isVisible());
            this.imgBack.setVisible(!this.helpAreatxt.isVisible());
            this.savePrefs("enableHelp", this.helpArea.isVisible());
        });

        this.btnLoadIto.setOnAction(evt -> {

            FileChooser fileChooser = null;
            File ito = null;

            try {
                fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(this.filePath.toFile());
                fileChooser.getExtensionFilters().setAll((new FileChooser.ExtensionFilter("ITO files", "*.ito")));
                fileChooser.setTitle("Select custom event file");
                ito = fileChooser.showOpenDialog(this.btnLoadPic.getScene().getWindow());
            } catch (final Exception e) {
                fileChooser.setInitialDirectory(Paths.get(USERHOME).toFile());
                fileChooser.setTitle(FOLDERNOTFOUND);
                ito = fileChooser.showSaveDialog(this.btnLoadPic.getScene().getWindow());
            }

            if (!Objects.isNull(ito)) {
                this.loadIto(ito);
                this.filePath = Paths.get(ito.getParentFile().toURI());
            }
        });
    }

    /**
     * Creates prefs file
     */

    void createPrefs() {
        this.prefs = Paths.get(USERHOME, APPNAME, "prefs.dat").toFile();
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(this.prefs))) {
            writer.write("enableHelp=true" + System.lineSeparator() +
                    "forceRefresh=false" + System.lineSeparator() +
                    "enableArts=true" + System.lineSeparator());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads prefs file from disk
     */

    void readPrefs() {
        if (this.prefs.exists()) {
            try {
                final List<String> strings = Files.readAllLines(this.prefs.toPath());
                strings.forEach(str -> {
                    if (str.contains("enableHelp")) {
                        this.helpArea.setVisible(str.equals("enableHelp=true"));
                        this.imgBack.setVisible(!str.equals("enableHelp=true"));
                        this.helpAreatxt.setVisible(str.equals("enableHelp=true"));
                    }
                    if (str.contains("forceRefresh=true")) {
                        this.forceFileRefresh = true;
                    }
                    if (str.contains("enableArts")) {
                        this.imgGuiArt.setVisible(str.equals("enableArts=true"));
                        this.imgGuiArt2.setVisible(str.equals("enableArts=true"));
                        this.imgGuiArt3.setVisible(str.equals("enableArts=true"));
                        this.imgGuiArt4.setVisible(str.equals("enableArts=true"));
                        this.imgGuiArt5.setVisible(str.equals("enableArts=true"));
                    }
                });
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.createPrefs();
        }
    }

    /**
     * Writes prefs file
     */

    private void savePrefs(final String property, final boolean state) {

        if (this.prefs.exists()) {
            try {
                final List<String> strings = Files.readAllLines(this.prefs.toPath());
                for (int i = 0; i < strings.size(); i++) {
                    if (strings.get(i).contains(property + "=" + !state)) {
                        strings.set(i, property + "=" + state);
                        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(this.prefs))) {
                            strings.forEach(str -> {
                                try {
                                    writer.write(str + System.lineSeparator());
                                } catch (final IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.createPrefs();
            this.savePrefs(property, state);
        }
    }

    /**
     * Updates the autocompletion list depending on the type of rewards
     */

    void refreshAutocompletion(final TextField target, final String type) {

        SuggestionProvider suggestionProvider = null;

        if (target.getUserData() == null) {
            suggestionProvider = SuggestionProvider.create(new ArrayList<>());
        } else {
            suggestionProvider = (SuggestionProvider) ((Pair) target.getUserData()).getValue();
        }
        suggestionProvider.clearSuggestions();
        suggestionProvider.addPossibleSuggestions(type.equals("items") ? this.itemList
                : type.equals("spells") ? this.spellList
                        : type.equals("itempool") ? this.itemPools : new ArrayList<>());
        if (target.getUserData() != null) {
            ((AutoCompletionTextFieldBinding) ((Pair) target.getUserData()).getKey()).dispose();
        }

        final Pair<AutoCompletionTextFieldBinding, SuggestionProvider> autoCompletion = new Pair(
                new AutoCompletionTextFieldBinding(target, suggestionProvider), suggestionProvider);
        target.setUserData(autoCompletion);

    }

    /**
     * Ticks/unticks the big art check depending on image res and warns about resolution not
     * corresponding to woh sizes.
     */

    void doImageCalculations() {

        if ((this.eventArt.getWidth() == 195 && this.eventArt.getHeight() == 164)) {
            this.lblImgWarn.setVisible(false);
            this.chkBigArt.setIndeterminate(false);
            this.chkBigArt.setSelected(false);
        } else if ((this.eventArt.getWidth() == 506 && this.eventArt.getHeight() == 220)) {
            this.lblImgWarn.setVisible(false);
            this.chkBigArt.setIndeterminate(false);
            this.chkBigArt.setSelected(true);
        } else {
            this.lblImgWarn.setVisible(true);
            this.chkBigArt.setSelected(false);
            this.chkBigArt.setIndeterminate(true);
            if (this.eventArt.isError()) {
                this.lblImgWarn.setText("Image couldn't be found!");
                this.imgArt.setImage(new Image("/notfound.png"));
            } else {
                this.lblImgWarn.setText("IMG resolution doesn't seem correct.");
            }
        }
    }

    // STRINGS

    private static final String REWARDINFO = "Autocompletion is your friend.";

    private static final String TESTINFO = "'Story' means  auto-success. 'Funds'succeed if player owns at least 1/2 funds, and removes them.";

    private static final String VISUALINFO = "Visual effect which will be triggered when this outcome is reached.";

    private static final String EXTRAREWARDSINFO = "Please use integers.";

    private static final String OPTIONSINFO = "Use # for line breaks.";

    private static final String FOLDERNOTFOUND = "WOH Folder couldn't be located, please browser manually.";

    private static final String OUTCOMETEXT = "Text layout is roughly the same as in woh. \nLine breaks will be respected.";

    /**
     * Listens to mouse hover to fill the help panel.
     */

    void implementHelpSystem() {

        this.textTitle.hoverProperty().addListener((ob, old, newValue) -> {
            if (newValue) {
                this.helpAreatxt.setText("Shown on the upper-left part of the screen and mod menu.");
            }
        });

        this.textDesc.hoverProperty()
            .addListener(
                    inv -> this.helpAreatxt.setText("Shown only in the mod menu. \nSmall! around 25 chars maximum."));

        this.textFlav.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText(
                        "Positioning'll be roughly the same as in woh. \nLine breaks will be respected."));

        this.txtAuthor.hoverProperty()
            .addListener(
                    inv -> this.helpAreatxt.setText("Shown in the lower-left part of the event screen, and mod menu."));

        this.txtContact.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Not shown in-game. A way for old god panstasz to contact you shall it be necessary."));
        this.txtSuccessA.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OUTCOMETEXT));
        this.txtSuccessB.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OUTCOMETEXT));
        this.txtSuccessC.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OUTCOMETEXT));
        this.txtFailureA.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OUTCOMETEXT));
        this.txtFailureB.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OUTCOMETEXT));
        this.txtFailureC.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OUTCOMETEXT));
        this.imgArt.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Click on the pic to show at higher scale."));
        this.txtPic.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("The route and name which will appear on the .ito file."));
        this.linkRepo.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Feel free to contribute or report issues."));
        this.btnResetPic.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Resets the pic. Default pic will display the location art instead."));
        this.linkDiscord.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("WOH community awaits virgin blood..."));
        this.btnHideHelp.hoverProperty().addListener(inv -> this.helpAreatxt.setText("Hides this help window."));
        this.btnCLear.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Clears the content of every field."));
        this.cmbOptions.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Number of choices presented to the player in this event."));
        this.cmbLocation.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Event will be added to this location deck. God-dependant locations are global."));
        this.checkUserFolder.hoverProperty()
            .addListener((ob, old, newValue) -> {
                if (newValue) {
                    this.helpAreatxt
                        .setText("If enabled, the image will be saved to a subfolder\nnamed after the 'author' field.");
                    this.txtAuthor.setStyle("-fx-background-color: #ff40ff");
                } else {
                    this.txtAuthor.setStyle("-fx-background-color: white");
                }
            });
        this.btnRandomChars.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Randomize ornamental arts."));
        this.chkWavy.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("If enabled, the art will undulate at the given speed."));
        this.sldWavy.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("The higher, the quickers the wavy animation will be"));
        this.chkBigArt.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Big arts take the whole event screen. Will be selected automatically when loading a pic."));
        this.txtOptionA.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OPTIONSINFO));
        this.txtOptionB.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OPTIONSINFO));
        this.txtOptionC.hoverProperty().addListener(inv -> this.helpAreatxt.setText(OPTIONSINFO));
        this.btnLoadIto.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Loads an already created event and parses its contents."));
        this.btnSaveUser.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Persists user/contact information so it's filled automatically when loading this app."));
        this.btnExit.hoverProperty()
            .addListener(inv -> this.helpAreatxt.setText("Closes the app, any unsaved change will be lost!"));
        this.btnLoadPic.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Small events are 195x164, while big ones are 506x2020. Avoid using other resolutions."));
        this.btnSaveIto.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Saves the event to disk as an .ito file. Put them in custom, sandbox or test sub-folders."));

        this.showWarn.hoverProperty()
            .addListener(inv -> this.helpAreatxt
                .setText("Dismisses the warnings... \nbut they will be back."));

        this.lblWarnings.forEach(lbl -> lbl.visibleProperty().addListener((ob, old, newValue) -> {
            if (newValue) {
                this.showWarn.setImage(new Image("/warnings.png"));
            }
        }));

        this.txtExtraRewards
            .forEach(txt -> txt.hoverProperty().addListener(inv -> this.helpAreatxt.setText(EXTRAREWARDSINFO)));
        this.txtRewardList.forEach(txt -> txt.hoverProperty().addListener(inv -> this.helpAreatxt.setText(REWARDINFO)));
        this.comboExtraRewards
            .forEach(cmb -> cmb.hoverProperty().addListener(inv -> this.helpAreatxt.setText(EXTRAREWARDSINFO)));
        this.comboChecks.forEach(cmb -> cmb.hoverProperty().addListener(inv -> this.helpAreatxt.setText(TESTINFO)));
        this.comboRewards.forEach(cmb -> cmb.hoverProperty().addListener(inv -> this.helpAreatxt.setText(REWARDINFO)));
        this.comboVisual.forEach(cmb -> cmb.hoverProperty().addListener(inv -> this.helpAreatxt.setText(VISUALINFO)));
    }

    static void setSmallScreenMode(final boolean state) {
        smallScreenMode.setValue(state);
    }

    /**
     * Charges the comboboxes with valid Strings.
     * @throws IOException if files cannot be accessed
     */

    public void loadData() throws IOException {

        final File wohMaker = Paths.get(USERHOME, APPNAME).toFile();
        if (!wohMaker.exists()) {
            wohMaker.mkdir();
        }

        // reads author data, or generates one

        final File author = Paths.get(USERHOME, APPNAME, "author.txt").toFile();

        if (!author.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(author))) {
                writer.write(System.getProperty("user.name") + System.lineSeparator() + "@"
                        + System.getProperty("user.name"));
            }
        }

        final List<String> authorData = Files.readAllLines(Paths.get(author.toURI()));
        this.txtAuthor.setText(authorData.get(0));
        this.txtContact.setText(authorData.get(1));

        // reads valid locations from disk. If no file is present, generates one with currently known
        // strings.

        final File locations = Paths.get(USERHOME, APPNAME, "locations.txt").toFile();
        if (!locations.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(locations))) {
                for (final String s : this.locationsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.locationsList = Files.readAllLines(Paths.get(locations.toURI()));
        }

        this.cmbLocation.getItems().addAll(this.locationsList);
        this.cmbLocation.getSelectionModel().select(0);

        // reads valid stat checks from disk. If no file is present, generates one with currently known
        // strings.
        final File statchecks = Paths.get(USERHOME, APPNAME, "statchecks.txt").toFile();
        if (!statchecks.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(statchecks))) {
                for (final String s : this.checksList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.checksList = Files.readAllLines(Paths.get(statchecks.toURI()));
        }

        this.comboCheckA.getItems().addAll(this.checksList);
        this.comboCheckB.getItems().addAll(this.checksList);
        this.comboCheckC.getItems().addAll(this.checksList);
        this.comboCheckA.getSelectionModel().select(0);
        this.comboCheckB.getSelectionModel().select(0);
        this.comboCheckC.getSelectionModel().select(0);

        // reads valid rewards from disk. If no file is present, generates one with currently known strings.
        final File rewards = Paths.get(USERHOME, APPNAME, "rewards.txt").toFile();
        if (!rewards.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(rewards))) {
                for (final String s : this.rewardsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.rewardsList = Files.readAllLines(Paths.get(rewards.toURI()));
        }

        // reads spells data, or generates one

        final File spells = Paths.get(USERHOME, APPNAME, "spells.txt").toFile();
        if (!spells.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(spells))) {
                for (final String s : this.spellList) {
                    writer.write(s + System.lineSeparator());
                }
            }
        } else {
            this.spellList = Files.readAllLines(Paths.get(spells.toURI()));
        }

        this.comboRewards.forEach(cmb -> {
            cmb.getItems().addAll(this.rewardsList);
            cmb.getSelectionModel().select(0);
        });

        // reads valid itemlist from disk. If no file is present, generates one with currently known
        // strings.
        final File items = Paths.get(USERHOME, APPNAME, "itemlist.txt").toFile();
        if (!items.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(items))) {
                for (final String s : this.itemList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.itemList = Files.readAllLines(Paths.get(items.toURI()));
        }

        // reads valid extra rewards from disk. If no file is present, generates one with currently known
        // strings.
        final File extraRewards = Paths.get(USERHOME, APPNAME, "extrarewards.txt").toFile();
        if (!extraRewards.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(extraRewards))) {
                for (final String s : this.extraRewardsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.extraRewardsList = Files.readAllLines(Paths.get(extraRewards.toURI()));
        }

        this.comboExtraRewards.forEach(cmb -> {
            cmb.getItems().addAll(this.extraRewardsList);
            cmb.getSelectionModel().select(0);
        });

        // reads valid visual effects from disk. If no file is present, generates one with currently known
        // strings.
        final File visualEffects = Paths.get(USERHOME, APPNAME, "visualeffects.txt").toFile();
        if (!visualEffects.exists() || this.forceFileRefresh) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(visualEffects))) {
                for (final String s : this.visualEffectsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            this.visualEffectsList = Files.readAllLines(Paths.get(visualEffects.toURI()));
        }

        this.comboVisual.forEach(cmb -> {
            cmb.getItems().addAll(this.visualEffectsList);
            cmb.getSelectionModel().select(0);
        });

        this.cmbOptions.getItems().addAll("1", "2", "3");

        this.savePrefs("forceRefresh", false);
        this.readPrefs();
    }

    /**
     * Implementation for "Open with WOHMaker" and dragging the .ito over the exe. If scene hasn't ben
     * created yet, wait for it.
     */

    void loadIto(final File ito) {

        if (this.cmbOptions.getScene() == null) {
            this.cmbOptions.sceneProperty().addListener(inv -> {
                this.clearCOntrols(this.cmbOptions.getScene().getRoot());
                this.actuallyLoadIto(ito);
            });

        } else {
            this.clearCOntrols(this.cmbOptions.getScene().getRoot());
            this.actuallyLoadIto(ito);
        }

        this.fixRandomCombos();
    }

    /**
     * Called after an ito is loaded to put the "random" on the comboboxes if necessary. TODO: do this
     * in a non-retarded way
     */

    void fixRandomCombos() {

        final List<String> aux = Arrays.asList("injury", "curse", "ally");
        if (aux.contains(this.cmbRewardsA.getSelectionModel().getSelectedItem())) {
            this.txtRewardA.setText("random");
        }
        if (aux.contains(this.cmbRewardsB.getSelectionModel().getSelectedItem())) {
            this.txtRewardB.setText("random");
        }
        if (aux.contains(this.cmbRewardsC.getSelectionModel().getSelectedItem())) {
            this.txtRewardC.setText("random");
        }

        if (aux.contains(this.cmbRewardsAF.getSelectionModel().getSelectedItem())) {
            this.txtRewardAF.setText("random");
        }
        if (aux.contains(this.cmbRewardsBF.getSelectionModel().getSelectedItem())) {
            this.txtRewardBF.setText("random");
        }
        if (aux.contains(this.cmbRewardsCF.getSelectionModel().getSelectedItem())) {
            this.txtRewardCF.setText("random");
        }
    }

    /**
     * Parsing of ito file.
     */


    void actuallyLoadIto(final File ito) {

        try {
            final List<String> strings = Files.readAllLines(ito.toPath());
            for (final String s : strings) {
                String substring;
                try {
                    if (s.contains("\"")) {
                        substring = s.substring(s.indexOf('"') + 1, s.lastIndexOf('"'));
                        if (substring.contains("´")) {
                            substring = substring.replaceAll("´", "`");
                        }

                        if (s.startsWith("name")) {
                            this.textTitle.setText(substring);
                        }
                        if (s.startsWith("location")) {
                            this.cmbLocation.getSelectionModel().select(substring);
                        }
                        if (s.startsWith("options")) {
                            this.cmbOptions.getSelectionModel().select(substring);
                        }
                        if (s.startsWith("about")) {
                            this.textDesc.setText(substring);
                        }
                        if (s.startsWith("flavor")) {
                            this.textFlav.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("wavy_art")) {
                            this.chkWavy.setSelected(substring.equals("1"));
                        }
                        if (s.startsWith("wavy_speed")) {
                            this.sldWavy.setValue(Double.parseDouble(substring.replace(',', '.')));
                        }
                        if (s.startsWith("optiona")) {
                            this.txtOptionA.setText(substring);
                        }
                        if (s.startsWith("optionb")) {
                            this.txtOptionB.setText(substring);
                        }
                        if (s.startsWith("optionc")) {
                            this.txtOptionC.setText(substring);
                        }
                        if (s.startsWith("testa")) {
                            this.comboCheckA.getSelectionModel().select(substring);
                            if (substring.equals(STORY)) {
                                this.tabSuccessA.getTabPane().getSelectionModel().select(0);
                            }
                        }
                        if (s.startsWith("testb")) {
                            this.comboCheckB.getSelectionModel().select(substring);
                            if (substring.equals(STORY)) {
                                this.tabSuccessB.getTabPane().getSelectionModel().select(0);
                            }
                        }
                        if (s.startsWith("testc")) {
                            this.comboCheckC.getSelectionModel().select(substring);
                            if (substring.equals(STORY)) {
                                this.tabSuccessC.getTabPane().getSelectionModel().select(0);
                            }
                        }
                        if (s.startsWith("successa")) {
                            this.txtSuccessA.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("successb")) {
                            this.txtSuccessB.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("successc")) {
                            this.txtSuccessC.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("failurea")) {
                            this.txtFailureA.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("failureb")) {
                            this.txtFailureB.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("failurec")) {
                            this.txtFailureC.setText(substring.replace("#", "\n"));
                        }
                        if (s.startsWith("author")) {
                            this.txtAuthor.setText(substring);
                        }
                        if (s.startsWith("contact")) {
                            this.txtContact.setText(substring);
                        }

                        if (s.startsWith("winnumbera")) {
                            this.txtRewardA.setText(substring);
                        }
                        if (s.startsWith("winnumberb")) {
                            this.txtRewardB.setText(substring);
                        }
                        if (s.startsWith("winnumberc")) {
                            this.txtRewardC.setText(substring);
                        }
                        if (s.startsWith("failnumbera")) {
                            this.txtRewardAF.setText(substring);
                        }
                        if (s.startsWith("failnumberb")) {
                            this.txtRewardBF.setText(substring);
                        }
                        if (s.startsWith("failnumberc")) {
                            this.txtRewardCF.setText(substring);
                        }
                        if (s.startsWith("extra_failnumbera")) {
                            this.txtExtraRewardAF.setText(substring);
                        }
                        if (s.startsWith("extra_failnumberb")) {
                            this.txtExtraRewardBF.setText(substring);
                        }
                        if (s.startsWith("extra_failnumberc")) {
                            this.txtExtraRewardCF.setText(substring);
                        }
                        if (s.startsWith("extra_winnumbera")) {
                            this.txtExtraRewardA.setText(substring);
                        }
                        if (s.startsWith("extra_winnumberb")) {
                            this.txtExtraRewardB.setText(substring);
                        }
                        if (s.startsWith("extra_winnumberc")) {
                            this.txtExtraRewardC.setText(substring);
                        }

                        if (s.startsWith("winprizea")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbRewardsA.getSelectionModel().select(0);
                            } else {
                                this.cmbRewardsA.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("winprizeb")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbRewardsB.getSelectionModel().select(0);
                            } else {
                                this.cmbRewardsB.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("winprizec")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbRewardsC.getSelectionModel().select(0);
                            } else {
                                this.cmbRewardsC.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("failprizea")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbRewardsAF.getSelectionModel().select(0);
                            } else {
                                this.cmbRewardsAF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("failprizeb")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbRewardsBF.getSelectionModel().select(0);
                            } else {
                                this.cmbRewardsBF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("failprizec")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbRewardsCF.getSelectionModel().select(0);
                            } else {
                                this.cmbRewardsCF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("extra_winprizea")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbExtraRewardsA.getSelectionModel().select(0);
                            } else {
                                this.cmbExtraRewardsA.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("extra_winprizeb")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbExtraRewardsB.getSelectionModel().select(0);
                            } else {
                                this.cmbExtraRewardsB.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("extra_winprizec")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbExtraRewardsC.getSelectionModel().select(0);
                            } else {
                                this.cmbExtraRewardsC.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("extra_failprizea")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbExtraRewardsAF.getSelectionModel().select(0);
                            } else {
                                this.cmbExtraRewardsAF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("extra_failprizeb")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbExtraRewardsBF.getSelectionModel().select(0);
                            } else {
                                this.cmbExtraRewardsBF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("extra_failprizec")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbExtraRewardsCF.getSelectionModel().select(0);
                            } else {
                                this.cmbExtraRewardsCF.getSelectionModel().select(substring);
                            }
                        }

                        if (s.startsWith("wineffecta")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbVisualA.getSelectionModel().select(0);
                            } else {
                                this.cmbVisualA.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("wineffectb")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbVisualB.getSelectionModel().select(0);
                            } else {
                                this.cmbVisualB.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("wineffectc")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbVisualC.getSelectionModel().select(0);
                            } else {
                                this.cmbVisualC.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("faileffecta")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbVisualAF.getSelectionModel().select(0);
                            } else {
                                this.cmbVisualAF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("faileffectb")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbVisualBF.getSelectionModel().select(0);
                            } else {
                                this.cmbVisualBF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("faileffectc")) {
                            if (substring.isEmpty() || substring.isBlank()) {
                                this.cmbVisualCF.getSelectionModel().select(0);
                            } else {
                                this.cmbVisualCF.getSelectionModel().select(substring);
                            }
                        }
                        if (s.startsWith("image")) {
                            this.currentImage = substring;
                            this.imgArt.setImage(new Image(ito.getParentFile()
                                .toURI()
                                .toString()
                                .replace(".ito", "")
                                .concat(File.separator)
                                .concat(substring)));

                            this.txtPic.setText(substring);
                            this.eventArt = this.imgArt.getImage();
                            this.doImageCalculations();
                            if (this.txtPic.getText().contains("\\") || this.txtPic.getText().contains("/")) {
                                this.checkUserFolder.setSelected(false);
                            }
                        }
                    }

                    this.setRewardsBehaviour();
                    this.setExtraRewardsBehaviour();

                } catch (final StringIndexOutOfBoundsException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(APPNAME);
            alert.setHeaderText("Error");
            alert.setContentText("Couldn't load " + ito.getAbsolutePath() + System.lineSeparator() +
                    "Error message: " + e.getMessage());
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    boolean saveIto(final File ito) {
        try {
            try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(ito.toPath(),
                    Charset.defaultCharset())) {

                if ((!this.txtPic.getText().isEmpty() && !this.txtPic.getText().isBlank())
                        && Paths.get(ito.getParentFile().toPath().toString(), this.checkUserFolder.isSelected()
                                ? this.txtAuthor.getText() + File.separator + this.txtPic.getText()
                                : this.txtPic.getText())
                            .toFile()
                            .mkdirs()) {
                    ImageIO.write(SwingFXUtils.fromFXImage(this.imgArt.getImage(), null), "png",
                            Paths
                                .get(ito.getParentFile().toPath().toString(),
                                        this.checkUserFolder.isSelected()
                                                ? this.txtAuthor.getText() + File.separator + this.txtPic.getText()
                                                : this.txtPic.getText())
                                .toFile());
                }

                final int numOptions = Integer.parseInt(this.cmbOptions.getSelectionModel().getSelectedItem());

                bufferedWriter.write("[event]" + System.lineSeparator());

                bufferedWriter.write(
                        "name=\"" + (this.textTitle.getText().isEmpty() ? " " : this.textTitle.getText()) + "\""
                                + System.lineSeparator() +
                                "location=\"" + this.cmbLocation.getSelectionModel().getSelectedItem() + "\""
                                + System.lineSeparator() +
                                "about=\"" + (this.textDesc.getText().isEmpty() ? " " : this.textDesc.getText()) + "\""
                                + System.lineSeparator() +
                                "author=\"" + (this.txtAuthor.getText().isEmpty() ? " " : this.txtAuthor.getText())
                                + "\"" + System.lineSeparator() +
                                "contact=\"" + (this.txtContact.getText().isEmpty() ? " " : this.txtContact.getText())
                                + " \"" + System.lineSeparator()
                                + System.lineSeparator() +
                                "flavor=\""
                                + (this.textFlav.getText().isEmpty() ? " " : this.textFlav.getText().replace("\n", "#"))
                                + "\"" + System.lineSeparator()
                                +
                                "image=\""
                                + (this.txtPic.getText().isEmpty() ? " "
                                        : this.checkUserFolder.isSelected()
                                                ? this.txtAuthor.getText() + File.separator
                                                        + this.txtPic.getText()
                                                : this.txtPic.getText())
                                + "\""
                                + System.lineSeparator() +
                                "big=\"" + (this.chkBigArt.isSelected() ? "1" : "0") + "\"" + System.lineSeparator() +
                                "wavy_art=\"" + (this.chkWavy.isSelected() ? "1" : "0") + "\"" + System.lineSeparator()
                                +
                                "wavy_speed=\"" + String.format("%.1f", this.sldWavy.getValue()).replace(',', '.')
                                + "\""
                                + System.lineSeparator()
                                +
                                "options=\"" + this.cmbOptions.getSelectionModel().getSelectedItem() + "\"" + System
                                    .lineSeparator()
                                + System.lineSeparator() + System.lineSeparator() +

                                "optiona=\"" + (this.txtOptionA.getText().isEmpty() ? " " : this.txtOptionA.getText())
                                + "\""
                                + System.lineSeparator() +
                                "testa=\"" + this.comboCheckA.getSelectionModel().getSelectedItem() + "\"" + System
                                    .lineSeparator()
                                + System.lineSeparator() +
                                "successa=\""
                                + (this.txtSuccessA.getText().isEmpty() ? " "
                                        : this.txtSuccessA.getText().replace("\n", "#"))
                                + "\""
                                + System.lineSeparator() +
                                "winprizea=\""
                                + (this.cmbRewardsA.getSelectionModel().getSelectedItem().equals("none") ? " "
                                        : this.cmbRewardsA.getSelectionModel().getSelectedItem())
                                + "\"" + System.lineSeparator() +
                                "winnumbera=\"" + this.txtRewardA.getText() + "\"" + System.lineSeparator() +
                                "extra_winprizea=\""
                                + (this.cmbExtraRewardsA.getSelectionModel().getSelectedItem().equals("none") ? " "
                                        : this.cmbExtraRewardsA.getSelectionModel().getSelectedItem())
                                + "\"" + System
                                    .lineSeparator()
                                +
                                "extra_winnumbera=\""
                                + (this.txtExtraRewardA.getText().isEmpty() ? " " : this.txtExtraRewardA.getText())
                                + "\"" + System.lineSeparator() +
                                "wineffecta=\""
                                + (this.cmbVisualA.getSelectionModel().getSelectedItem().equals("none") ? " "
                                        : this.cmbVisualA.getSelectionModel().getSelectedItem())
                                + "\"" + System.lineSeparator()
                                + (this.comboCheckA.getSelectionModel().getSelectedItem().equals(STORY) ? " "
                                        : "failurea=\""
                                                + (this.txtFailureA.getText().isEmpty() ? " "
                                                        : this.txtFailureA.getText().replace("\n", "#"))
                                                + "\""
                                                + System.lineSeparator() +
                                                "failprizea=\""
                                                + (this.cmbRewardsAF.getSelectionModel()
                                                    .getSelectedItem()
                                                    .equals("none") ? " "
                                                            : this.cmbRewardsAF.getSelectionModel().getSelectedItem())
                                                + "\"" + System.lineSeparator() +
                                                "failnumbera=\"" + this.txtRewardAF.getText() + "\""
                                                + System.lineSeparator() +
                                                "extra_failprizea=\""
                                                + (this.cmbExtraRewardsAF.getSelectionModel()
                                                    .getSelectedItem()
                                                    .equals("none")
                                                            ? " "
                                                            : this.cmbExtraRewardsAF.getSelectionModel()
                                                                .getSelectedItem())
                                                + "\"" + System
                                                    .lineSeparator()
                                                +
                                                "extra_failnumbera=\""
                                                + (this.txtExtraRewardAF.getText().isEmpty() ? " "
                                                        : this.txtExtraRewardAF.getText())
                                                + "\"" + System.lineSeparator()
                                                +
                                                "faileffecta=\""
                                                + (this.cmbVisualAF.getSelectionModel().getSelectedItem().equals("none")
                                                        ? " "
                                                        : this.cmbVisualAF.getSelectionModel().getSelectedItem())
                                                + "\"" + System.lineSeparator())
                                + System.lineSeparator() + System.lineSeparator() +

                                (numOptions > 1 ? "optionb=\""
                                        + (this.txtOptionB.getText().isEmpty() ? " " : this.txtOptionB.getText()) + "\""
                                        + System.lineSeparator() +
                                        "testb=\"" + this.comboCheckB.getSelectionModel().getSelectedItem() + "\""
                                        + System
                                            .lineSeparator()
                                        + System.lineSeparator() +
                                        "successb=\""
                                        + (this.txtSuccessB.getText().isEmpty() ? " "
                                                : this.txtSuccessB.getText().replace("\n", "#"))
                                        + "\""
                                        + System.lineSeparator() +
                                        "winprizeb=\""
                                        + (this.cmbRewardsB.getSelectionModel().getSelectedItem().equals("none") ? " "
                                                : this.cmbRewardsB.getSelectionModel().getSelectedItem())
                                        + "\"" + System.lineSeparator() +
                                        "winnumberb=\"" + this.txtRewardB.getText() + "\"" + System.lineSeparator() +
                                        "extra_winprizeb=\""
                                        + (this.cmbExtraRewardsB.getSelectionModel().getSelectedItem().equals("none")
                                                ? " "
                                                : this.cmbExtraRewardsB.getSelectionModel().getSelectedItem())
                                        + "\"" + System
                                            .lineSeparator()
                                        +
                                        "extra_winnumberb=\""
                                        + (this.txtExtraRewardB.getText().isEmpty() ? " "
                                                : this.txtExtraRewardB.getText())
                                        + "\""
                                        + System.lineSeparator()
                                        + "wineffectb=\""
                                        + (this.cmbVisualB.getSelectionModel().getSelectedItem().equals("none") ? " "
                                                : this.cmbVisualB.getSelectionModel().getSelectedItem())
                                        + "\"" + System.lineSeparator() + System.lineSeparator()
                                        + (this.comboCheckB.getSelectionModel().getSelectedItem().equals(STORY) ? ""
                                                : "failureb=\""
                                                        + (this.txtFailureB.getText().isEmpty() ? " "
                                                                : this.txtFailureB.getText().replace("\n", "#"))
                                                        + "\""
                                                        + System.lineSeparator() +
                                                        "failprizeb=\""
                                                        + (this.cmbRewardsBF.getSelectionModel()
                                                            .getSelectedItem()
                                                            .equals("none") ? " "
                                                                    : this.cmbRewardsBF.getSelectionModel()
                                                                        .getSelectedItem())
                                                        + "\"" + System.lineSeparator() +
                                                        "failnumberb=\"" + this.txtRewardBF.getText() + "\""
                                                        + System.lineSeparator() +
                                                        "extra_failprizeb=\""
                                                        + (this.cmbExtraRewardsBF.getSelectionModel()
                                                            .getSelectedItem()
                                                            .equals("none")
                                                                    ? " "
                                                                    : this.cmbExtraRewardsBF.getSelectionModel()
                                                                        .getSelectedItem())
                                                        + "\"" + System
                                                            .lineSeparator()
                                                        +
                                                        "extra_failnumberb=\""
                                                        + (this.txtExtraRewardBF.getText().isEmpty() ? " "
                                                                : this.txtExtraRewardBF.getText())
                                                        + "\""
                                                        + System.lineSeparator() +
                                                        "faileffectb=\""
                                                        + (this.cmbVisualBF.getSelectionModel()
                                                            .getSelectedItem()
                                                            .equals("none") ? " "
                                                                    : this.cmbVisualBF.getSelectionModel()
                                                                        .getSelectedItem())
                                                        + "\"" + System.lineSeparator() + System.lineSeparator())
                                        : "")
                                +
                                (numOptions > 2 ? "optionc=\""

                                        + (this.txtOptionC.getText().isEmpty() ? " " : this.txtOptionC.getText()) + "\""
                                        + System.lineSeparator() +
                                        "testc=\"" + this.comboCheckC.getSelectionModel().getSelectedItem() + "\""
                                        + System
                                            .lineSeparator()
                                        + System.lineSeparator() +
                                        "successc=\""
                                        + (this.txtSuccessC.getText().isEmpty() ? " "
                                                : this.txtSuccessC.getText().replace("\n", "#"))
                                        + "\""
                                        + System.lineSeparator() +
                                        "winprizec=\""
                                        + (this.cmbRewardsC.getSelectionModel().getSelectedItem().equals("none") ? " "
                                                : this.cmbRewardsC.getSelectionModel().getSelectedItem())
                                        + "\"" + System.lineSeparator() +
                                        "winnumberc=\"" + this.txtRewardC.getText() + "\"" + System.lineSeparator() +
                                        "extra_winprizec=\""
                                        + (this.cmbExtraRewardsC.getSelectionModel().getSelectedItem().equals("none")
                                                ? " "
                                                : this.cmbExtraRewardsC.getSelectionModel().getSelectedItem())
                                        + "\"" + System
                                            .lineSeparator()
                                        +
                                        "extra_winnumberc=\""
                                        + (this.txtExtraRewardC.getText().isEmpty() ? " "
                                                : this.txtExtraRewardC.getText())
                                        + "\""
                                        + System.lineSeparator() +
                                        "wineffectc=\""
                                        + (this.cmbVisualC.getSelectionModel().getSelectedItem().equals("none") ? " "
                                                : this.cmbVisualC.getSelectionModel().getSelectedItem())

                                        + (this.comboCheckC.getSelectionModel().getSelectedItem().equals(STORY) ? ""
                                                : "\"" + System.lineSeparator() + System.lineSeparator() +
                                                        "failurec=\""
                                                        + (this.txtFailureC.getText().isEmpty() ? " "
                                                                : this.txtFailureC.getText().replace("\n", "#"))
                                                        + "\""
                                                        + System.lineSeparator() +
                                                        "failprizec=\""
                                                        + (this.cmbRewardsCF.getSelectionModel()
                                                            .getSelectedItem()
                                                            .equals("none") ? " "
                                                                    : this.cmbRewardsCF.getSelectionModel()
                                                                        .getSelectedItem())
                                                        + "\"" + System.lineSeparator() +
                                                        "failnumberc=\""
                                                        + (this.txtRewardCF.getText().isEmpty() ? " "
                                                                : this.txtRewardCF.getText())
                                                        + "\"" + System.lineSeparator() +
                                                        "extra_failprizec=\""
                                                        + (this.cmbExtraRewardsCF.getSelectionModel()
                                                            .getSelectedItem()
                                                            .equals("none")
                                                                    ? " "
                                                                    : this.cmbExtraRewardsCF.getSelectionModel()
                                                                        .getSelectedItem())
                                                        + "\"" + System
                                                            .lineSeparator()
                                                        +
                                                        "extra_failnumberc=\""
                                                        + (this.txtExtraRewardCF.getText().isEmpty() ? " "
                                                                : this.txtExtraRewardCF.getText())
                                                        + "\""
                                                        + System.lineSeparator() +
                                                        "faileffectc=\""
                                                        + (this.cmbVisualCF.getSelectionModel()
                                                            .getSelectedItem()
                                                            .equals("none") ? " "
                                                                    : this.cmbVisualCF.getSelectionModel()
                                                                        .getSelectedItem()))
                                        + "\"" + System.lineSeparator() : " ")
                                + System.lineSeparator() + System.lineSeparator() +

                                "--Made with " + APPNAME + " " + VERSION + " --=" + System.lineSeparator());
            }

            return true;

        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
