import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TemperatureAnalyzer {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://users.metropolia.fi/~jarkkov/temploki.csv");

            try (InputStream inputStream = url.openStream();
                 InputStreamReader streamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                String line;
                boolean headerProcessed = false;
                int ulkoTaloColumnIndex = -1;
                double totalTemperature = 0;
                int count = 0;

                // Date format matches "01.01.2023"
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date targetDate = dateFormat.parse("01.01.2023");

                while ((line = reader.readLine()) != null) {
                    if (!headerProcessed) {
                        // Process the header row to find the index of the 'UlkoTalo' column
                        String[] headers = line.split(";");
                        for (int i = 0; i < headers.length; i++) {
                            if (headers[i].trim().equals("UlkoTalo")) {
                                ulkoTaloColumnIndex = i;
                                break;
                            }
                        }
                        headerProcessed = true;
                        continue;
                    }

                    // Process data rows
                    String[] columns = line.split(";");
                    if (columns.length > ulkoTaloColumnIndex && ulkoTaloColumnIndex != -1) {
                        // Check if the date is January 1st, 2023
                        String dateTime = columns[0].trim();
                        String datePart = dateTime.split(" ")[0]; // Get the date part

                        try {
                            Date currentDate = dateFormat.parse(datePart);
                            if (currentDate.equals(targetDate)) {
                                // Parse the temperature value (Note: European format uses comma as decimal separator)
                                String tempStr = columns[ulkoTaloColumnIndex].trim().replace(',', '.');
                                double temperature = Double.parseDouble(tempStr);
                                totalTemperature += temperature;
                                count++;
                            }
                        } catch (ParseException e) {
                            // Ignore date parsing errors and continue to the next line
                            continue;
                        }
                    }
                }

                if (count > 0) {
                    double averageTemperature = totalTemperature / count;
                    System.out.printf("Average temperature on January 1st, 2023: %.2f°C (based on %d measurements)%n",
                            averageTemperature, count);
                } else {
                    System.out.println("No temperature data found for January 1st, 2023");
                }

            } catch (IOException e) {
                System.err.println("Error while reading data: " + e.getMessage());
            }

        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Date parsing error during setup: " + e.getMessage());
        }
    }
}