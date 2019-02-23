# RESTful web service

Implementation of a RESTful web service that permits to store and retrieve the NFV/SDN information and call an Orchestration service like Verifoo or Verigraph.

# Configuration

The used software has been [IntelliJ IDEA ULTIMATE EDITION](https://www.jetbrains.com/idea/).

### Before starting

- Donwload  SE Development Kit (JDK), version 1.8, see [Download Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
- Donwload Glassfish application server, version 4.1.1, see [Download Glassfish](https://download.oracle.com/glassfish/4.1.1/promoted/index.html).

### Configure the GlassFish server in IntelliJ IDEA

1. Open the *Settings / Preferences Dialog* choosing **File | Settings** for Windows and Linux or **IntelliJ IDEA | Preferences** for macOS, and click Application Servers under Build, Execution, Deployment. 
2. On the **Application Servers** page that opens, click **+** icons general add svg above the central pane and choose GlassFish Server from the list.

    ![server](https://user-images.githubusercontent.com/25306548/53284175-b54acb80-3750-11e9-802e-110a892fa318.PNG)

3. Specify the GlassFish Server installation folder in the GlassFish Home field. Type the path to it manually or click browseButton and choose the installation folder in the dialog box that opens.

### Configure the JDK

1. Choose *File | Project Structure* on the main menu.
2. In the Project Structure Dialog that opens, choose **SDKs** under the **Platform Settings**.
3. On the **SDKs** page that opens, click **+** icons general add svg above the central pane and choose **JDK**.

    ![jdk](https://user-images.githubusercontent.com/25306548/53284171-a5cb8280-3750-11e9-84e4-d9c6cc98b285.PNG)

4. Specify the installation folder of the **Java SE Development Kit (JDK)** to use. Type the path manually or click browseButton and choose the installation folder in the dialog that opens.

### Create Project

1. Click Create **New Project** on the Welcome screen, or choose *File | New | Project* on the main menu. The New Project wizard opens.
2. On the page of the Wizard:
    1. Select Java Enterprise.
    2. From the **Project SDK list**, select the **JDK 1.8**.
    3. From the **Application Server** drop-down list, choose **GlassFish 4.1.1**.
    4. From the **JavaEE Version** drop down list, choose **JavaEE 7**.
    5. In the Additional Libraries and Frameworks area, select the **Web Application** and **RESTful Web Service** checkboxes. 
    6. Choose the **Download option** in the area below the Additional Libraries and Frameworks list (the area is displayed only after you have selected the Web Application and RESTful Web Service checkboxes.).
    7. Click **Next**.
    
      ![server](https://user-images.githubusercontent.com/25306548/53284244-c9db9380-3751-11e9-86d7-753984de0627.PNG)
    
    8. Specify the name for your new project.
    9. Click **Finsh**.

Click Finish and wait while IntelliJ IDEA is creating the project. 

# Issues

- IntelliJ 13.1 and Glassfish 4.1 on MacOS X:

  To force using Java 8 JDK, I edited /Applications/IntelliJ IDEA 13.app/Contents/Info.plist - JVMOptions > JVMVersion from 1.8*,1.8+ to 1.8*
