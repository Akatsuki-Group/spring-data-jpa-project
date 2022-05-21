package org.example.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
public class MyAuditorAware implements AuditorAware<Integer> {
	@Override
	public Optional<Integer> getCurrentAuditor() {
		return Optional.ofNullable(1);
	}
}