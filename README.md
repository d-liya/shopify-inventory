<div id="top"></div>



<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">Inventory Management App</h3>

  <p align="center">
     Inventory tracking web application for a logistics company
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#the-challenge">The Challenge</a></li>
        <ul>
          <li><a href="#requirements">Requirements</a></li>
        </ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project was created as a part of the Shopify summer backend Challenge. You can find more details about the Challenge below

<p align="right">(<a href="#top">back to top</a>)</p>

### The Challenge

Build an inventory tracking web application for a logistics company. We are looking for a web application that meets the requirements listed below, along with one additional feature, with the options also listed below. 

#### Requirements:

* Basic CRUD Functionality. You should be able to:
* Create inventory items
* Edit Them
* Delete Them
* View a list of them
* Push a button export product data to a CSV (This is the extra feature)


### Built With

* Server
  * Java
  * Spring Boot
  * MySQL

* Client
  * JavaScript 
  * React.js
  * Tailwind CSS


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

#### Server

Below are the prerequisites to run the spring boot server. Offical installation guide can be found [here](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html)  if needed.
This shows how to install necessary technologies using homebrew for OSX. Home brew can be installed pasting below link in the terminal. 
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)
```

* java 8+
* Maven 3.5+ (build tool)
* Tomcat 9.0 (Serverleet Container)

* Check the java version
  ```
  java -version
  ```
* Install Maven (For OSX)
  ```
  brew install maven
  ```
* Install Tomcat (For OSX)
  ```
  brew install tomcat
  ```

#### Client 

* node 
  ```
  brew install node
  ```

### Installation


1. Clone the repo
   ```
   git clone https://github.com/d-liya/shopify-inventory.git
   ```
   
2. Navigate to the client. Install NPM packages
   ```
   npm install
   ```  

3. I am using an online hosted SQL server, I have provided a password for the SQL server password with the application Or can create one in this [website](https://www.freesqldatabase.com/register/)
  - If using the server password given, replace PASSWORD_HERE with the provided password in the application.properties. 
    "application.properties" can be found in server -> src -> main -> resources -> application.properties
  - If using a new database, will have to replace the url, username and password.

4. Run the server using 
  ```
  mvn spring-boot:run
  ```
  Run the client using
  ```
  npm start
  ```
<p align="right">(<a href="#top">back to top</a>)</p>






<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



