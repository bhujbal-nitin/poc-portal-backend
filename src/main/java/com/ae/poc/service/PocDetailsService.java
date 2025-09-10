package com.ae.poc.service;

import java.util.Optional;

import com.ae.poc.entity.PocDetails;

public interface PocDetailsService {

	PocDetails savePoc(PocDetails poc);

	Optional<PocDetails> getPocById(Integer id);

}
