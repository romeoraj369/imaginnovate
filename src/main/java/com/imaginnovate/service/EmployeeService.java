package com.imaginnovate.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.imaginnovate.entity.BatchErrors;
import com.imaginnovate.entity.BatchProcess;
import com.imaginnovate.entity.Employee;
import com.imaginnovate.entity.PhoneNumber;
import com.imaginnovate.repository.BatchErrorRepo;
import com.imaginnovate.repository.BatchProcessRepo;
import com.imaginnovate.repository.EmployeeRepo;
import com.imaginnovate.utils.EntityCounter;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private BatchErrorRepo batchErrorRepo;

	@Autowired
	private BatchProcessRepo batchProcessRepo;

//	@Scheduled(cron = "0 30 11 * * ?")
	@Scheduled(initialDelay = 0, timeUnit = TimeUnit.MICROSECONDS)
	public void process() {
		EntityCounter.resetCounts();

		long insertCounter = 0;
		long updateCounter = 0;
		long errorCounter = 0;
		String filePath = "D:\\Eclipse\\Workspace\\Ec-2\\ImagInnovate\\employee.csv";
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		Long eId = null;
		try (CSVReader reader = new CSVReader(new FileReader(file))) {

			BatchProcess beginPro = new BatchProcess("Employee Upsert batch process", Timestamp.from(Instant.now()));
			batchProcessRepo.save(beginPro);
			System.out.println(String.valueOf(beginPro.toString()));

			CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader).withType(Employee.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<Employee> employees = csvToBean.parse();
			for (Employee e : employees) {
				eId = e.getEmployeeId();
				try {
					String n = e.getPhoneNumber();
					Set<PhoneNumber> numbers = new HashSet<>();
					if (n.contains(",")) {
						String[] ns = n.split(",");
						for (String s : ns) {
							PhoneNumber pn = new PhoneNumber(e.getEmployeeId(), s);
							numbers.add(pn);
						}
					} else
						numbers.add((new PhoneNumber(e.getEmployeeId(), n)));

					e.setPhoneNumbers(numbers);
					if (employeeRepo.existsById(e.getEmployeeId())) {
						employeeRepo.save(e);
						updateCounter++;
					} else {
						employeeRepo.save(e);
						insertCounter++;
					}
				} catch (Exception ignore) {
					errorCounter++;
					BatchErrors err = new BatchErrors(Timestamp.from(Instant.now()), "employee.csv",
							"Error processing Employee ID: " + eId);
					err = batchErrorRepo.save(err);
					System.out.println(String.valueOf(err.toString()));

				}
			}
			String processedName = getFormattedFileName();
//			file.renameTo(new File("D:\\Eclipse\\Workspace\\Ec-2\\ImagInnovate\\" + processedName));
			updateCsvFileName(file, "D:\\Eclipse\\Workspace\\Ec-2\\ImagInnovate\\" + processedName);
			// employeeRepo.saveAll(csvToBean.parse());
			BatchProcess endProcess = new BatchProcess("employee.csv", Timestamp.from(Instant.now()), processedName,
					insertCounter, updateCounter, errorCounter);
			endProcess =	batchProcessRepo.save(endProcess);
			System.out.println(String.valueOf(endProcess.toString()));

		} catch (IOException ignore) {
			ignore.printStackTrace();

		}
	}

	private String getFormattedFileName() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy-HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		return String.format("processed-employees-%s.csv", now.format(formatter));
	}

	private void updateCsvFileName(File oldFile, String newFilePath) throws FileNotFoundException, IOException {

		File newFile = new File(newFilePath);

		try (BufferedReader reader = new BufferedReader(new FileReader(oldFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {

			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
		}
	}

}
