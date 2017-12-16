package ufo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UfoSightingServiceImpl implements UfoSightingService {

	public static final String fileName = "D:\\rajat\\projects\\RecruitmentExcercise-master\\src\\main\\resources\\ufo_awesome.tsv";
	@Override
	public List<String> getAllSightings() {
		List<String> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			list = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> search(int yearSeen, int monthSeen) {
		List<String> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			list = stream
					.filter(line -> line.startsWith(String.valueOf(yearSeen)+String.valueOf(monthSeen)))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}