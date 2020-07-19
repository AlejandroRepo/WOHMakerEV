# WOHMakerEV

World of Horror custom event creation tool.

Download links: 

Windows: <b>[Download WOHMaker 1.5b (fixed exe) for Windows](https://github.com/AlejandroRepo/WOHMakerEV/blob/master/WOHMaker1.5b%20fixed%20exe.zip?raw=true)</b> [19/07/2020]

MacOS build: Not currently available

![wohmakert](wohmaker.png)

**Version 1.5.b fixed exe**
- Fixed license-related error on launch.

**Version 1.5b**
- Fixed memory leak in autocompletion bindings for loaded events.
- When saving an event, "failure" related tags won't be written if the check type is "story".
- Added confirmation dialog when selecting "save picture to user folder", if current image is already in a subfolder.

**Version 1.5**
- Added icons for each location.
- Rewards which take integer values will not accept text, and will default to 0.
- Improved behaviour of reward/extra-reward controls.
- Improved layout and fixed some UI issues here and there.

**Version 1.4b:**
- Added option to save the event image to a sepparate user folder.
- Saved events won't cause crashes in macOS when leaving empty fields.
- Improved parsing of ito files (i.e : loading an event with an injury will now display the "random" string, "´" char now supported)
- Other small fixes and tweaks.

**Version 1.4:**
- You can now drag ito files over the exe to load them.
- You can now set ito files to "open with -> WOHMaker" 
- Text for intro and outcomes now looks as it will look in the game.
- Improved demeanor of "hide warnings" button.
- Optimized code and dependencies.

![wohmakert](wohmakerb.png)

**Version 1.3b:**
- Now features a proper windows executable
- Removed white band
- Improved "image not found" img

**Version 1.3:**
- Improved behaviour when loading events.
- Corrected an issue which could prevent pictures to be saved alongside event.
- Better feedback when failing to load image.
- Added button to hide all warnings.
- Improved UI arts, layout and CSS styles.
- Other fixes and tweaks.

**Version 1.2:**
- Corrected an issue which prevented events without art to be saved.
- Improved layout and UI elements.
- Added button to reset art to default.

**Version 1.1:**
- Improved overall visuals and layout for better legibility
- Fixed most ui issues
- Corrected names for items and spells.
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

    [None right now]
    
TO DO:
    
    Make UI responsive.
    Fix macOS specific issues.
    Provide executable for linux.
    Implement new features as they are being provided by the game.
    
Technical:
    
    Written in Java 11, JavaFX 14.0.1. Compiled and through javafx-maven-plugin, released as executable with jar2exe.

Credits and thanks:

    Silver font which is used in certain parts of the UI is the work of PoppyWorks (https://poppyworks.itch.io/silver).

    Thanks to: 
    Mythical, for providing asset lists and general help.
    Throdax, for coding advices and collaboration.
    Chiavica, for helping with macOS test builds.
