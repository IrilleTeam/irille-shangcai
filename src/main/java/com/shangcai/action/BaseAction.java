package com.shangcai.action;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.irille.core.controller.EntityAction;
import com.irille.core.controller.Writeable;
import com.irille.core.repository.orm.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseAction<T extends Entity, R extends Serializable> extends EntityAction<T, R> implements Writeable {

	protected int start = 0;
	protected int limit = 15;

	static {
		writer.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}

}
