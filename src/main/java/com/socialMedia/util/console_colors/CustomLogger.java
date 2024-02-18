package com.socialMedia.util.console_colors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomLogger {

	public void info(String message) {
		log.info(Color.GREEN + message + Color.RESET);
	}

	public void error(String message) {
		log.error(Color.RED + message + Color.RESET);
	}
}
