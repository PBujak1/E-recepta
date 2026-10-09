# E-recepta

**E-recepta** is a desktop application that supports prescription and medication management by a physician, while also giving patients access to their own prescription data. It is built as a JavaFX client backed by a relational database.

> ⚠️ This is a student / portfolio project and is **not** connected to Poland's official CSIOZ e-recepta (P1) system. It is an independent application inspired by the concept of electronic prescriptions.

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [1. Clone the repository](#1-clone-the-repository)
  - [2. Set up the database](#2-set-up-the-database)
  - [3. Run the application](#3-run-the-application)
- [Project Structure](#project-structure)
- [Building a Distributable](#building-a-distributable)
- [Related Projects](#related-projects)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [Author](#author)

## Features

- 👨‍⚕️ **Physician view** — issue and manage prescriptions and medications for patients.
- 🧑‍🦱 **Patient view** — patients can view their own prescription history and data, and manage appointments.
- 💊 **Medication management** — track prescribed drugs tied to individual patients, including a fast-prescription mode.
- 📅 **Appointment scheduling** — patients can book appointments and review their appointment history.
- ⚙️ **Doctor settings** — physicians can update their own profile/account data.
- 🗄️ **Persistent storage** — all data is stored in a relational database (see `e_recepta.sql`).

## Screenshots

### 1. Login View
Entry point of the application, where users authenticate before being routed to either the patient or the doctor workspace.

<img width="1920" height="1080" alt="login_panel" src="https://github.com/user-attachments/assets/0d749674-0527-4bb2-876f-f26853239a28" />

### 2. Patient View
The patient's dashboard, giving access to their personal data, prescription history, and appointment tools.

<img width="1920" height="1080" alt="pacjent_view" src="https://github.com/user-attachments/assets/d2ed6110-b546-49ac-be55-d2ca3332a69d" />

#### 2.1 Making an Appointment
Patients can schedule a new appointment with a physician directly from this view.

<img width="1920" height="1080" alt="wizyta_view" src="https://github.com/user-attachments/assets/65b8c35b-3842-4829-b87d-2b26bed46866" />

#### 2.2 Appointment History
A chronological list of the patient's past and upcoming appointments.

<img width="1920" height="1080" alt="historia_wizyt" src="https://github.com/user-attachments/assets/4d91f80c-09d0-4063-8ee2-6ae986de4f32" />

### 3. Doctor View
The physician's dashboard, providing access to patient lookup, prescription tools, and account settings.

<img width="1920" height="1080" alt="lekarz_view" src="https://github.com/user-attachments/assets/f418d5db-570c-4792-bd95-d20c9222b811" />

#### 3.1 Fast Prescription
After looking up a patient by PESEL number, the doctor can quickly issue a prescription by adding medications directly from a search/selection list — designed for routine, low-friction prescribing.

<img width="1920" height="1080" alt="lekarz_pacjent_view" src="https://github.com/user-attachments/assets/6daa7f9d-9467-4074-a765-aa2259e23b45" />

#### 3.2 Full Prescription Form
A more detailed prescription-issuing view for cases that require additional information beyond the fast-prescription flow (e.g. dosage, instructions, multiple medications).

<img width="1920" height="1080" alt="wypisanie_recept" src="https://github.com/user-attachments/assets/313690f4-527b-4cd9-93e8-0f78ec5a628e" />

#### 3.3 Settings and Doctor Data
Lets the physician review and update their own profile information (e.g. contact details, credentials).

<img width="1920" height="1080" alt="ustawienia_view" src="https://github.com/user-attachments/assets/f90f119a-6f10-4e1c-855d-c12f1013248d" />

> The remaining screens (e.g. patient/medication search, editing records) follow the same design and interaction patterns shown above.

## Tech Stack

| Layer | Technology |
|---|---|
| UI | [JavaFX](https://openjfx.io/) (`javafx-controls`, `javafx-fxml`) |
| UI components | [ControlsFX](https://github.com/controlsfx/controlsfx), [BootstrapFX](https://github.com/kordamp/bootstrapfx), [Ikonli](https://kordamp.org/ikonli/), [FontAwesomeFX](https://bitbucket.org/Jerady/fontawesomefx) |
| Language | Java 24 (compiled with `--enable-preview`) |
| Build tool | Maven (with Maven Wrapper) |
| Database | SQL (schema/dump provided in `e_recepta.sql`) |
| Testing | JUnit 5 |

## Prerequisites

- **JDK 24** or newer
- **Maven** (or use the bundled `mvnw` / `mvnw.cmd` wrapper — no local Maven install required)
- A running SQL database server (e.g. MySQL/MariaDB) to import `e_recepta.sql` into
- An IDE with JavaFX support is recommended (e.g. IntelliJ IDEA — the repo includes `.idea` project files)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/PBujak1/E-recepta.git
cd E-recepta
```

### 2. Set up the database

Create a database and import the provided SQL dump:

```bash
mysql -u <user> -p <database_name> < e_recepta.sql
```

Then update your database connection settings (host, port, credentials, database name) in the application's configuration/connection class before running the app.

> If you're using a different RDBMS than MySQL, adjust the import command and JDBC driver dependency in `pom.xml` accordingly.

> 🖥️ The local server application that the client connects to for database access is maintained in a separate repository: **[E-recepta-Database](https://github.com/PBujak1/E-recepta-Database)**. Set that up and have it running before starting the client below.

### 3. Run the application

Using the Maven Wrapper:

```bash
# Linux / macOS
./mvnw clean javafx:run

# Windows
mvnw.cmd clean javafx:run
```

This uses the `javafx-maven-plugin` configuration in `pom.xml`, which launches `com.example.erecepta.HelloApplication`.

## Project Structure

```
E-recepta/
├── .mvn/wrapper/       # Maven wrapper files
├── src/main/           # Application source code (Java + FXML/CSS resources)
├── e_recepta.sql       # Database schema / seed data
├── pom.xml             # Maven project configuration
├── mvnw / mvnw.cmd      # Maven wrapper scripts
└── README.md
```

## Building a Distributable

You can package a runnable image using the JavaFX Maven plugin:

```bash
./mvnw clean javafx:jlink
```

This produces a self-contained application image (see the `jlink`-related configuration in `pom.xml`).

## Related Projects

- **[E-recepta-Database](https://github.com/PBujak1/E-recepta-Database)** — the local server application that provides database access for this client.

## Roadmap

- [ ] Add authentication / role-based access (physician vs. patient)
- [ ] Add unit and integration tests
- [ ] Document the database schema

## Contributing

Contributions, issues, and feature requests are welcome. Feel free to open a pull request or an issue.

## Authors

**Piotr Bujak** ([@PBujak1](https://github.com/PBujak1))
**Bober Weronika**
