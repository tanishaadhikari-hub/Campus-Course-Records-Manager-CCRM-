# Special Requirements

- Java 17 or higher is required to compile and run the application.
- No external libraries are required; all code uses standard Java APIs.
- Place CSV files (students.csv, courses.csv) in the repository root or specify the absolute file path when importing.
- If running on a system with restricted file permissions, ensure you have read/write access to folders used for import, export, or backup.
- For file backup and directory size utilities, Java NIO.2 is used; full paths are recommended for Windows users.
- The CLI requires text input from the console. No GUI is provided.
- No JVM preview features are used; disable preview mode in your IDE.

## Automated Evaluation Testing

- Main executable: `edu.ccrm.cli.MainMenu`
- All menu features are available interactively; ensure sample CSV files are present for import tests
- For full testing, use the sample records included above.
