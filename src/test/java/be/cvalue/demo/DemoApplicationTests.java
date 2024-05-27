package be.cvalue.demo;

import be.cvalue.demo.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerSettingsRepository settingsRepository;

	@Test
	void testCustomer() {
		var customer = new Customer();
		customer.setId(new CustomerId("123"));
		customer.setName(new Name("first","last"));
		customer.setEmail1(new Email1("foo1@bar.be"));
		customer.setEmail2(new Email2("foo2@bar.be"));
		customerRepository.save(customer);

		assertThat(customerRepository.findByEmail1(new Email1("foo1@bar.be"))).isPresent();
		assertThat(customerRepository.findByEmail2(new Email2("foo2@bar.be"))).isPresent();
		assertThat(customerRepository.findByEmail2("foo2%")).isPresent();


		assertThatRuntimeException().isThrownBy(() -> customerRepository.findByEmail1("foo1%"));

		assertThat(customerRepository.findByLastName("last")).isPresent();
		assertThat(customerRepository.findById(new CustomerId("123"))).isPresent();

	}

	@Test
	void testCustomerSettings() {

		var customer = new Customer();
		customer.setId(new CustomerId("123"));
		customer.setName(new Name("first","last"));
		customer.setEmail1(new Email1("foo1@bar.be"));
		customer.setEmail2(new Email2("foo2@bar.be"));
		customerRepository.save(customer);

		var settings = new CustomerSettings();
		settings.setId(new CustomerSettingsId(UUID.randomUUID()));
		var complexSettings = new ComplexSettings("foo","bar", List.of(true, false));
		settings.setSettings(complexSettings);

		settings.setCustomer(customer);
		settingsRepository.save(settings);

		assertThat(settingsRepository.findByCustomerId(customer.getId())).isPresent();
	}

	@Test
	public void zonderTx() {
		var customer = new Customer();
		customer.setId(new CustomerId("123"));
		customer.setEmail1(new Email1("foo1@bar.be"));
		customer.setEmail2(new Email2("foo2@bar.be"));
		customer.setName(new Name("first","last"));
		customerRepository.save(customer);

		var a = customerRepository.findById(new CustomerId("123")).get();
		var b = customerRepository.findAll().get(0);

		assertThat(a).isNotSameAs(b);
	}

	@Test
	@Transactional
	public void metTx() {
		var customer = new Customer();
		customer.setId(new CustomerId("123"));
		customer.setEmail1(new Email1("foo1@bar.be"));
		customer.setEmail2(new Email2("foo2@bar.be"));
		customer.setName(new Name("first","last"));
		customerRepository.save(customer);

		var a = customerRepository.findById(new CustomerId("123")).get();
		var b = customerRepository.findAll().get(0);

		assertThat(a).isSameAs(b);
	}
}
