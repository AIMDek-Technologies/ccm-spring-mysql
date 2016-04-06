/*
 * Copyright (c) 2014-2015 AIMDek Technologies Private Limited. All Rights Reserved.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     AIMDek Technologies Private Limited - initial API and implementation
 */

package com.aimdek.ccm.dao.impl.test;

import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_USER_ROLE;
import static com.aimdek.ccm.util.CCMConstant.ROLE_CUSTOMER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.JPATestConfig;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.UserRepository;

/**
 * The Class UsersDaoImplTest.
 *
 * @author aimdek.team
 */
@ContextConfiguration(classes = { JPATestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDaoImplTest extends BasicAbstractGenericDaoImplTest<User, String> {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The user list. */
	private List<User> userList = new ArrayList<User>();

	/** The user id. */
	private long userId;

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/**
	 * Initialize.
	 */
	@Before
	public void initialize() {

		User user = new User();
		user.setBirthDate(new Date());
		user.setCreatedBy(1);
		user.setCustomerId(1000);
		user.setEmail("test.test@aimdek.com");
		user.setFirstName("Test0");
		user.setLastName("Test0");
		user.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user.setPhoneNumber("(111)-111-1111");
		user.setRole("CUSTOMER");
		userRepository.save(user);
		userId = user.getId();

		User user1 = new User();
		user1.setBirthDate(new Date());
		user1.setCreatedBy(1);
		user1.setCustomerId(1001);
		user1.setEmail("test1.test1@aimdek.com");
		user1.setFirstName("Test1");
		user1.setLastName("Test1");
		user1.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user1.setPhoneNumber("(222)-222-2222");
		user1.setRole("CUSTOMER");
		userList.add(user1);

		User user2 = new User();
		user2.setBirthDate(new Date());
		user2.setCreatedBy(1);
		user2.setCustomerId(1002);
		user2.setEmail("test2.test2@aimdek.com");
		user2.setFirstName("Test2");
		user2.setLastName("Test2");
		user2.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user2.setPhoneNumber("(333)-333-3333");
		user2.setRole("CUSTOMER");
		userList.add(user2);

		User user3 = new User();
		user3.setBirthDate(new Date());
		user3.setCreatedBy(1);
		user3.setCustomerId(1003);
		user3.setEmail("test3.test3@aimdek.com");
		user3.setFirstName("Test3");
		user3.setLastName("Test3");
		user3.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user3.setPhoneNumber("(444)-444-4444");
		user3.setRole("CUSTOMER");
		userList.add(user3);

		User user4 = new User();
		user4.setBirthDate(new Date());
		user4.setCreatedBy(1);
		user4.setCustomerId(1004);
		user4.setEmail("test4.test4@aimdek.com");
		user4.setFirstName("Test4");
		user4.setLastName("Test4");
		user4.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user4.setPhoneNumber("(555)-555-5555");
		user4.setRole("CUSTOMER");
		userList.add(user4);

		User user5 = new User();
		user5.setBirthDate(new Date());
		user5.setCreatedBy(1);
		user5.setCustomerId(1005);
		user5.setEmail("test5.test5@aimdek.com");
		user5.setFirstName("Test5");
		user5.setLastName("Test5");
		user5.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user5.setPhoneNumber("(666)-666-6666");
		user5.setRole("CUSTOMER");
		userList.add(user5);

		User user6 = new User();
		user6.setBirthDate(new Date());
		user6.setCreatedBy(1);
		user6.setCustomerId(1006);
		user6.setEmail("test6.test6@aimdek.com");
		user6.setFirstName("Test6");
		user6.setLastName("Test6");
		user6.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user6.setPhoneNumber("(777)-777-7777");
		user6.setRole("CUSTOMER");
		userList.add(user6);

		User user7 = new User();
		user7.setBirthDate(new Date());
		user7.setCreatedBy(1);
		user7.setCustomerId(1007);
		user7.setEmail("test7.test7@aimdek.com");
		user7.setFirstName("Test7");
		user7.setLastName("Test7");
		user7.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user7.setPhoneNumber("(888)-888-8888");
		user7.setRole("CUSTOMER");
		userList.add(user7);

		User user8 = new User();
		user8.setBirthDate(new Date());
		user8.setCreatedBy(1);
		user8.setCustomerId(1008);
		user8.setEmail("test8.test8@aimdek.com");
		user8.setFirstName("Test8");
		user8.setLastName("Test8");
		user8.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user8.setPhoneNumber("(999)-999-9999");
		user8.setRole("CUSTOMER");
		userList.add(user8);

		User user9 = new User();
		user9.setBirthDate(new Date());
		user9.setCreatedBy(1);
		user9.setCustomerId(1009);
		user9.setEmail("test9.test9@aimdek.com");
		user9.setFirstName("Test9");
		user9.setLastName("Test9");
		user9.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user9.setPhoneNumber("(000)-000-0000");
		user9.setRole("CUSTOMER");
		userList.add(user9);

		userRepository.save(userList);

	}

	/**
	 * Test save user.
	 */
	@Test
	public void testSaveUser() {

		User user = new User();
		user.setBirthDate(new Date());
		user.setCreatedBy(1);
		user.setCustomerId(2000);
		user.setEmail("tapan.parikh@aimdek.com");
		user.setFirstName("Tapan");
		user.setLastName("Parikh");
		user.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user.setPhoneNumber("(942)-824-5892");
		user.setRole("CUSTOMER");

		userRepository.save(user);

		long size = userRepository.count();

		assertEquals(size, 11);

	}

	/**
	 * Test retrieve by email.
	 */
	@Test
	public void testRetrieveByEmail() {

		String email = "test1.test1@aimdek.com";
		User user = userRepository.findByEmail(email);

		assertEquals(user.getEmail(), email);

	}

	/**
	 * Test fail retrieve by email.
	 */
	@Test
	public void testFailRetrieveByEmail() {

		String email = "abhed.dekavadiya@aimdek.com";
		User user = userRepository.findByEmail(email);
		assertNull(user);
	}

	/**
	 * Test retrieve by user id.
	 */
	@Test
	public void testRetrieveByUserId() {

		long id = userId;

		User user = userRepository.findById(id);

		assertEquals(user.getId(), id);

	}

	/**
	 * Test fail retrieve by user id.
	 */
	@Test
	public void testFailRetrieveByUserId() {

		long id = -1;

		User user = userRepository.findById(id);

		assertNull(user);

	}

	/**
	 * Test retrieve limited user.
	 */
	@Test
	public void testRetrieveLimitedUser() {

		int limit = 10;
		int start = 5;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(builder.equal(root.get(FIELDCONSTANT_USER_ROLE), ROLE_CUSTOMER));

		List<User> user = entityManager.createQuery(query).setFirstResult(start).setMaxResults(limit).getResultList();

		assertEquals(user.size(), 5);
	}

	/**
	 * Test delete user.
	 */
	@Test
	public void testDeleteUser() {

		User user = userRepository.findById(userId);

		long id = userId;

		userRepository.remove(user);

		User testUser = userRepository.findById(id);

		assertNull(testUser);
	}

	/**
	 * Test user like.
	 */
	@Test
	public void testUserLike() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(builder.like(root.<String> get("lastName"), "test%"));
		List<User> user = entityManager.createQuery(query).getResultList();

		assertEquals(10, user.size());
	}
}
