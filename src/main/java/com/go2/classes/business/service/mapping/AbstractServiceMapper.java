package com.go2.classes.business.service.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;

public abstract class AbstractServiceMapper {

	protected abstract ModelMapper getModelMapper();
	
	protected <I, O> O map(I input, Class<O> outputClass) {
		return getModelMapper().map(input, outputClass);
	}

	protected <I, O> void map(I input, O output) {
		getModelMapper().map(input, output);
	}
		
	protected <I, O> List<O> map(Collection<I> inputs, Class<O> outputClass) {
		List<O> outputs = new ArrayList<O>();
		for(I input : inputs) {
			O output = map(input, outputClass);
			outputs.add(output);
		}
		return outputs;
	}

	protected <I, O> void map(Collection<I> inputs, Collection<O> outputs, Class<O> outputClass) {
		for(I input : inputs) {
			O output = map(input, outputClass);
			outputs.add(output);
		}
	}
}
