package com.iiiss.spring.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import monopoly.core.daos.IUserDao;
import monopoly.impl.controllers.UserController;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class UserControllerTest extends BaseControllerTest {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private UserController userController;

	@Before
	public void setUp() {
		assertNotNull(userDao);
		assertNotNull(userController);
	}

	@Test
	public void testRegister1() {
		final String username1 = "testUsername1";
		final String password1 = "testPassword1";

		String path;

		// empty username & password
		path = userController.register(null, null, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username or Password is not specified.");

		// empty username
		path = userController.register(null, password1, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username or Password is not specified.");

		// empty password
		path = userController.register(username1, null, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username or Password is not specified.");
	}

	@Test
	public void testRegister2() {
		final String username2 = "testUsername2";
		final String password2 = "testPassword2";

		userDao.createUser(username2, password2);

		String path = userController.register(username2, password2, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username already exists.");
	}

	@Test
	public void testRegister3() {
		final String username3 = "testUsername3";
		final String password3 = "testPassword3";

		String path = userController.register(username3, password3, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertNullMessage(model, username3, password3);
	}

	@Test
	public void testLogin1() {
		final String username4 = "testUsername4";
		final String password4 = "testPassword4";

		String path;

		// empty username & password
		path = userController.login(null, null, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username or Password is empty.");

		// empty username
		path = userController.login(null, password4, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username or Password is empty.");

		// empty password
		path = userController.login(username4, null, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Username or Password is empty.");
	}

	@Test
	public void testLogin2() {
		String path;

		final String username5 = "testUsername5";
		final String password5 = "testPassword5";

		// user not exists
		path = userController.login(username5, password5, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Authentication failed.");

		// password mismatch
		userDao.createUser(username5, password5);

		path = userController.login(username5, "abc", model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertMessageOnly(model, "Authentication failed.");
	}

	@Test
	public void testLogin3() {
		final String username6 = "testUsername6";
		final String password6 = "testPassword6";

		userDao.createUser(username6, password6);

		String path = userController.login(username6, password6, model);
		assertEquals(UserController.RESULT_PAGE, path);
		this.assertNullMessage(model, username6, password6);
	}

	private void assertMessageOnly(Model model, String msg) {
		String message = (String) model.asMap().get("message");
		assertEquals(msg, message);
		assertTrue(null == model.asMap().get("username"));
		assertTrue(null == model.asMap().get("password"));
	}

	private void assertNullMessage(Model model, String username, String password) {
		assertTrue(null == model.asMap().get("message"));
		assertEquals(username, model.asMap().get("username"));
		assertEquals(password, model.asMap().get("password"));
	}
}
