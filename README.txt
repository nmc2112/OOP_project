How to run the program?
Prepare:
 - Inteliji 2021.3
 - JDK 17
 - JavaFx 
 
Start program
 - Import our project files in intelliJ: ...\OOP_project 
 - Run "Main" class

Thank you

How to install JavaFx
Download the appropriate JavaFX SDK for your operating system and unzip it to a desired location.
    1. Create a JavaFX project
     Create a JavaFX project Provide a name to the project, like HelloFX, and a location. When the project opens, the JavaFX classes are not recognized. 
 
    2. Set JDK 17
     Go to File -> Project Structure -> Project, and set the project SDK to 17. You can also set the language level to 11. Set JDK 11 
 
    3. Create a library
     Go to File -> Project Structure -> Libraries and add the JavaFX 17 SDK as a library to the project. Point to the lib folder of the JavaFX SDK. 
     Once the library is applied, the JavaFX classes will be recognized by the IDE.
     Warning: If you run now the project it will compile but you will get this error:
     Error: JavaFX runtime components are missing, and are required to run this application
     This error is shown since the Java 11 launcher checks if the main class extends javafx.application.Application. If that is the case, it is required to have the javafx.graphics module on the module-path.

    4. Add VM options
     To solve the issue, click on Run -> Edit Configurations... and add these VM options:
     --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.fxml
     Note that the default project created by IntelliJ uses FXML, so javafx.fxml is required along with javafx.controls. If your project uses other modules, you will need to add them as well. 
     Click apply and close the dialog.