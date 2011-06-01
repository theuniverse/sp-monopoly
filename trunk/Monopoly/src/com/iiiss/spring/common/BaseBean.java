package com.iiiss.spring.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseBean implements IBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long key;

	public Long getKey() {
		return key;
	}
}
