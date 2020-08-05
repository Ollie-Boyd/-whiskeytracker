package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskyByDistillery(){
		Optional<Distillery> distillery3 = distilleryRepository.findById(3L);
		if (distillery3.isPresent()) {
			List<Whisky> found = whiskyRepository.findByDistillery(distillery3.get());
			assertEquals(1, found.size());
		} else {
			fail();
		}
	}

	@Test
	public void canGetWhiskyByAgeAndDistillery(){
		Optional<Distillery> distillery3 = distilleryRepository.findById(3L);
		if (distillery3.isPresent()) {
			List<Whisky> found = whiskyRepository.findByDistilleryAndAge(distillery3.get(), 25);
			assertEquals(1, found.size());
		} else {
			fail();
		}
	}

	@Test
	public void canGetWhiskyByYear(){
		List<Whisky> found = whiskyRepository.findByYear(1991);
		assertEquals(1, found.size());
	}



}
