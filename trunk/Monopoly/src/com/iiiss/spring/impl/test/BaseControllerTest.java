package com.iiiss.spring.impl.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:monopoly/WEB-INF/root-context.xml",
		"file:monopoly/WEB-INF/dispatcher-context.xml" })
public class BaseControllerTest {

	protected Model model = new ExtendedModelMap();

}
