# RESTful web service

Implementation of a RESTful web service that permits to store and retrieve the NFV/SDN information. 

Different methos allow you to manage the whole structure and also the sub-structure. There is the possibility to **add**, **remove**, **modify** hosts, graphs, vnfd and so on. Check [documentation](https://github.com/NFV-Architecture/data-format/blob/master/doc/DP2_NFV_Data_Models.pdf), section 5, for more details.

# Menù

- [Configuration](https://github.com/netgroup-polito/nfv-data-model#configuration)
    - [IntelliJ IDEA](https://github.com/netgroup-polito/nfv-data-model#intellij-idea)
    - [Eclipse](https://github.com/netgroup-polito/nfv-data-model#eclipse)
- [Classes Configuration](https://github.com/netgroup-polito/nfv-data-model#classes-configuration)
- [Issues](https://github.com/netgroup-polito/nfv-data-model#issues)

# Configuration

## IntelliJ IDEA

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

### Import the project

1. Clone the project

        git clone https://github.com/NFV-Architecture/RESTful-web-service.git

2. Change the name folder of **WebContent** into **web**.
3. Rewrite all the file folder of your project with the new one downloaded.
4. Insert libs dependancy inside the project (to fix class import errors).
    1. Click File from the toolbar
    2. Project Structure (CTRL + SHIFT + ALT + S on Windows/Linux, ⌘ + ; on Mac OS X)
    3. Select Modules at the left panel
        1. in Dependencies tab '+' → Java
        2. Browse the folder lib of the Project
        3. Apply and then OK to close

## Eclipse

The used software has been [Eclipse Neon 2](https://www.eclipse.org/downloads/packages/release/neon/2)

### Before starting

- Donwload  SE Development Kit (JDK), at least version 1.8, see [Download Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
- Donwload Apache Tomcat, version 8.5, see [Download Tomcat](https://tomcat.apache.org/download-80.cgi).

### Create Project and configure Tomcat

1. Click *File > New > Other*.
2. On the page of the Wizard:
    1. Search for **Dynamic Web Project**.
    2. Set the **Project name**.
    3. On **Target runtime** select **Apache Tomcat v8.5**.
    4. From **Dynamic web module version** select **3.1**.
    5. Click **Finish**.
    
### Import the project

1. Clone the project

        git clone https://github.com/NFV-Architecture/RESTful-web-service.git

2. Rewrite all the file folder of your project with the new one downloaded.
3. Insert libs dependancy inside the project (to fix class import errors).
    1. Right click on the project and click on **Properties**.
    2. From **Java Build Path** select **Add jARs**.
    3. Select all jARs inside **lib** folder and click **OK**.
    4. **Apply** and then **OK** to close

# Classes Configuration

### Application Class

Keep attention on the fact that every time you add a new service, resource or general class you must "register" this class in the MyApplication class. You have to add the new class in the defined hash set of classes in order to make that class available.

For example, if you want to add newClass.java, then:

        h.add(newClass.class);

### Client Class

You need to specify the name of your project in the URL in which the client will perform the test. So, make sure that `XXXXXXX`, inside the Client constructor, will be replaced the name of your project or the client will not work properly.

If the name of the project is `rest_nfv`, then:

	baseUrl = "http://localhost:8080/rest_nfv/nfv";
            
Note that in **IntelliJ IDEA** is also present the `_war_exploded`, then:

	baseUrl = "http://localhost:8080/rest_nfv_war_exploded/nfv";

# Issues

- IntelliJ 13.1 and Glassfish 4.1 on MacOS X:

    To force using Java 8 JDK, edite `/Applications/IntelliJ IDEA 13.app/Contents/Info.plist` - JVMOptions > JVMVersion from 1.8*,1.8+ to 1.8*

- Glassfish does not recognize JDK (*Error: glassfish requires java se version 6. your jdk is version 0*)

    Check `glassfish\config\asenv.bat` and edit it: `set AS_JAVA=PATH_TO_JDK`

- Java Path

        export JAVA_HOME="PATH_TO_JAVA"
