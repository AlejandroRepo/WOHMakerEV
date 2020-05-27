# WOHMakerEV

World of Horror custom event creation tool.

Download link:  <b>[Download WOHMaker 1.1](WOHMaker1.1.zip?raw=true)</b>

![wohmakert](wohmaker.png)

**Version 1.1:**
- Improved overall visuals and layout for better legibility
- Fixed most ui issues
- Corrected items and spell lists
- removed global location (currently not working in woh)


Features:

    Create, read, edit and save WOH's event files (.ito) easily.
    All current (0.9.14) features implemented (extra_rewards, itempools, global, wavy art, spells...).
    OnScreen help and tips, reward autocompletion and warning messages (text length, image resolution) to lower chance of errors.
    Automatic pathing for files and images. Choose the pic you want for your event and the program will put it were it must.

Requirements:

    Windows OS, preferably WINDOWS 10.
    1366x768 minimum resolution.

Known issues:
    
    [Minor] Loading an .ito with a Curse, Injury or Ally reward type will not update the text on the combobox accordingly. 
    [Minor] Even if option A is set as "story" by default, it will allow user to access the "failure" tab until refreshed.
  
TO DO:
    
    Polish the UI. Make it rezisable and adjust better to different resolutions.
    Make it so sta/rea/doom/funds rewards take only integers as value.
    Implement new features as they are being provided by the game.
    Provide a proper windows executable.
    Provide launcher for linux/macOS.

Technical:
    
    Written in Java 11, JavaFX 14.0.1. Compiled, ran and released as jLink image through javafx-maven-plugin.

Credits and thanks:

    Silver font which is used in certain parts of the UI is the work of PoppyWOrks (https://poppyworks.itch.io/silver).

    Thanks to: 
    Mythical, for providing asset lists and general help.
    Throdax, for coding advices and collaboration.
