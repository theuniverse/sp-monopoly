package com.iiiss.spring.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * Monopoly - com.iiiss.spring.common.BaseBean.java
 * 
 * Copyright (C) 2011  Mingye Cheng <Cheng, Mingye at GMAIL>
 * 
 * BaseBean.java is part of <iiiStudio Template Framework> project.
 * This project is hosted at <http://iiiss-template-ssh.googlecode.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
@MappedSuperclass
public abstract class BaseBean implements IBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long key;

	public Long getKey() {
		return key;
	}
}
