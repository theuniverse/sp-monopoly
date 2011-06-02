package com.iiiss.spring.impl.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/*
 * Monopoly - com.iiiss.spring.impl.test.BaseControllerTest.java
 * 
 * Copyright (C) 2011  Mingye Cheng <Cheng, Mingye at GMAIL>
 * 
 * BaseControllerTest.java is part of <iiiStudio Template Framework> project.
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:monopoly/WEB-INF/root-context.xml",
		"file:monopoly/WEB-INF/dispatcher-context.xml" })
public class BaseControllerTest {

	protected Model model = new ExtendedModelMap();

}
