# RESTful web service

Implementation of a RESTful web service that permits to store and retrieve the NFV/SDN information. 

Different methos allow you to manage the whole structure and also the sub-structure. There is the possibility to **add**, **remove**, **modify** hosts, graphs, vnfd and so on. Check [documentation](https://github.com/netgroup-polito/nfv-data-model/blob/master/data-format/doc/DP2_NFV_Data_Models.pdf), section 5, for more details.

# Overview

- [Configuration](https://github.com/netgroup-polito/nfv-data-model#configuration)
	- [IntelliJ IDEA](https://github.com/netgroup-polito/nfv-data-model#intellij-idea)
	- [Eclipse](https://github.com/netgroup-polito/nfv-data-model#eclipse)
- [Classes Configuration](https://github.com/netgroup-polito/nfv-data-model#classes-configuration)
	- [Application Class](https://github.com/netgroup-polito/nfv-data-model#application-class)
	- [Client Class](https://github.com/netgroup-polito/nfv-data-model#client-class)
	- [ClientTest Class](https://github.com/netgroup-polito/nfv-data-model#clienttest-class)
- [Test](https://github.com/netgroup-polito/nfv-data-model#test)
	- JUnit test
	- Client test
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

        git clone https://github.com/netgroup-polito/nfv-data-model.git

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

        git clone https://github.com/netgroup-polito/nfv-data-model.git

2. Rewrite all the file folder of your project with the new one downloaded.
3. Insert libs dependancy inside the project (to fix class import errors).
    1. Right click on the project and click on **Properties**.
    2. From **Java Build Path** select **Add jARs**.
    3. Select all jARs inside **lib** folder and click **OK**.
    4. **Apply** and then **OK** to close

# Classes Configuration

### Application Class

Keep attention on the fact that every time you add a new service or general class that perform some useful stuffs (XML and JSON validator for example) you must "register" this class in the MyApplication class. You have to add the new class in the defined hash set of classes in order to make that class available.

For example, if you want to add newClass.java, then:

        h.add(newClass.class);

### Client Class

You need to specify the name of your project in the URL in which the client will perform the test. So, make sure that `XXXXXXX`, inside the Client constructor, will be replaced the name of your project or the client will not work properly.

If the name of the project is `rest_nfv`, then:

	baseUrl = "http://localhost:8080/rest_nfv/nfv";
            
Note that in **IntelliJ IDEA** is also present the `_war_exploded`, then:

	baseUrl = "http://localhost:8080/rest_nfv_war_exploded/nfv";

### ClientTest Class

Make sure the base URL containts the URL of your project or the test will not work properly:

	private String baseUrl = "http://localhost:8080/project_name";
	
Note that in **IntelliJ IDEA** is also present the `_war_exploded`, then:

	private String baseUrl = "http://localhost:8080/project_name_war_exploded";

# Test

Two ways to perform test:
- [JUnit test](https://github.com/netgroup-polito/nfv-data-model/tree/master/test)

	It will perform nine kind of different tests:
	
	- The first one will add a wrong **NFV** ([wrong_nfv.xml](https://github.com/netgroup-polito/nfv-data-model/blob/master/test/assertion/wrong_nfv.xml)) and then, the expected status code will be *400 Bad Requeset*
		
	- The second one will add a correct **NFV** ([ok_nfv.xml](https://github.com/netgroup-polito/nfv-data-model/blob/master/test/assertion/ok_nfv.xml)) and then, the expected status code will be *201 Created*

	- The third one will perform a delete of the **NFV** and will assert a *204 No Content*

	- The fourth one will add a wrong **PNI** ([wrong_pni.xml](https://github.com/netgroup-polito/nfv-data-model/blob/master/test/assertion/wrong_pni.xml)) and then, the expected status code will be *400 Bad Requeset*

	- The fifth one will add a correct **PNI** ([ok_pni.xml](https://github.com/netgroup-polito/nfv-data-model/blob/master/test/assertion/ok_pni.xml)) and then, the expected status code will be *201 Created*

	- The sixth one will add a wrong **NS** ([wrong_ns.xml](https://github.com/netgroup-polito/nfv-data-model/blob/master/test/assertion/wrong_ns.xml)) and then, the expected status code will be *400 Bad Requeset*

	- The seventh one will add a correct **NS** ([ok_ns.xml](https://github.com/netgroup-polito/nfv-data-model/blob/master/test/assertion/ok_ns.xml)) and then, the expected status code will be *201 Created*

	- The eighth one will perform a delete of the **PNI** and will assert a *204 No Content*

	- The ninth one will perform a delete of the **NS** and will assert a *204 No Content*

- [Client test](https://github.com/netgroup-polito/nfv-data-model/tree/master/src/it/polito/dp2/rest/nfv/client)

	It is a resource that can be called by performing a GET to path `http://localhost:8080/project_name/nfv/test`. 
	
	The resource will call a test service and a lot of insertion will be performed. To be sure that each insertion is correct the Client class will check for each insertion if the status code is the expected one or not: if yes it will log the insertion, otherwise it will throw an exception.

# Issues

- IntelliJ 13.1 and Glassfish 4.1 on MacOS X:

    To force using Java 8 JDK, edite `/Applications/IntelliJ IDEA 13.app/Contents/Info.plist` - JVMOptions > JVMVersion from 1.8*,1.8+ to 1.8*

- Glassfish does not recognize JDK (*Error: glassfish requires java se version 6. your jdk is version 0*)

    Check `glassfish\config\asenv.bat` and edit it: `set AS_JAVA=JDK_PATH`

- Java Path

        export JAVA_HOME="JAVA_PATH"
