package smarthome.model.readers;

import smarthome.io.ui.UtilsUI;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Reading;
import smarthome.model.Sensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class CSVReading implements FileReaderReadings {
    String commaDelimiter = ",";
    Path filePathAndName;
    GAList gaList;

    public CSVReading(GAList gaList) {
        this.gaList = gaList;
    }

    public CSVReading() {
    }

    public void importData(Path filePathAndName,GAList gaList) throws FileNotFoundException {
        this.filePathAndName = filePathAndName;
        this.gaList=gaList;
        List<String[]> result = extractData(filePathAndName);
        loadF(result);
    }

    public List<String[]> extractData(Path filePathAndName) throws FileNotFoundException {
        this.filePathAndName = filePathAndName;
        List<String[]> dataToImport = new ArrayList<>();
        File file = new File(filePathAndName.toString());
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(this.commaDelimiter);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] fields = line.split(this.commaDelimiter);
            dataToImport.add(fields);
        }
        scanner.close();
        return dataToImport;
    }

    public void loadF(List<String[]> data) {
        for (GeographicalArea ga : gaList.getGAList()) {
            for (String[] field : data) {
                String sensorID = field[0];
                for (Sensor sensor : ga.getSensorListInGA().getSensorList())
                    if (sensorID.equals(sensor.getId())) {

                        String dateAndTimeString = field[1];
                        Calendar readingDate = UtilsUI.parseDateToImportReadings(dateAndTimeString);

                        double readingValue = parseDouble(field[2]);
                        String unit = field[3];

                        Reading reading = new Reading(readingValue, readingDate, unit);

                        if (readingDate.after(sensor.getStartDate()))
                            sensor.getReadingList().addReading(reading);
                    }
            }

        }
    }
}
