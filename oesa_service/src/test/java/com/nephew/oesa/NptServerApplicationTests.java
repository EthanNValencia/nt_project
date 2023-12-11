package com.nephew.oesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList; 
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nephew.oesa.entities.Appointment;
import com.nephew.oesa.entities.Company;
import com.nephew.oesa.entities.FAQs;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.OfficeDailySchedule;
import com.nephew.oesa.entities.OfficeSocialMediaProfile;
import com.nephew.oesa.entities.Services;
import com.nephew.oesa.entities.employee.BiographicalText;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.employee.EmployeeDailySchedule;
import com.nephew.oesa.entities.employee.EmployeeSocialMediaProfile;
import com.nephew.oesa.entities.employee.InformationalText;
import com.nephew.oesa.entities.text.TextType;
import com.nephew.oesa.entities.website.Website;
import com.nephew.oesa.entities.website.WebsiteSocialMediaProfile;
import com.nephew.oesa.repositories.AppointmentRepository;
import com.nephew.oesa.repositories.CompanyDailyScheduleRepository;
import com.nephew.oesa.repositories.CompanyRepository;
import com.nephew.oesa.repositories.EmployeeDailyScheduleRepository;
import com.nephew.oesa.repositories.EmployeeRepository;
import com.nephew.oesa.repositories.EmployeeSocialMediaProfileRepository;
import com.nephew.oesa.repositories.FAQsRepository;
import com.nephew.oesa.repositories.OfficeRepository;
import com.nephew.oesa.repositories.OfficeSocialMediaProfileRepository;
import com.nephew.oesa.repositories.ServiceRepository;
import com.nephew.oesa.repositories.WebsiteRepository;
import com.nephew.oesa.repositories.WebsiteSocialMediaProfileRepository;
import com.nephew.oesa.services.EmployeeService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
class NptServiceApplicationTests {

	@Autowired
	private ServiceRepository servicesRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private CompanyDailyScheduleRepository companyDailyScheduleRepository;

	@Autowired
	private EmployeeDailyScheduleRepository employeeDailyScheduleRepository;

	@Autowired
	private EmployeeSocialMediaProfileRepository employeeSocialMediaProfileRepository;

	@Autowired
	private OfficeSocialMediaProfileRepository officeSocialMediaProfileRepository;

	@Autowired
	private WebsiteRepository websiteRepository;

	@Autowired
	private WebsiteSocialMediaProfileRepository websiteSocialMediaProfileRepository;
	
	@Autowired
	private FAQsRepository faqsRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CompanyRepository companyRepository;

	private final String HEAD_AND_NECK = "Head & Neck";
	private final String SHOULDERS = "Shoulders";
	private final String ELBOWS = "Elbows";
	private final String WRISTS = "Wrists";
	private final String MID_BACK = "Mid-Back";
	private final String LOWER_BACK = "Lower-Back";
	private final String HIP = "Hips";
	private final String KNEES = "Knees";
	private final String FOOT_AND_ANKLE = "Foot & Ankle";
	private final String BALANCE = "Balance";
	private final String VESTIBULAR_REHAB = "Vestibular Rehab";
	private final String MESSAGE_THERAPY = "Massage Therapy";
	
	private final String PROFILE_FACEBOOK = "www.facebook.com";

	private String img1 = "/src/assets/1-300x300.png?raw=true";
	private String img2 = "/src/assets/2-300x300.png?raw=true";
	private String img3 = "/src/assets/3-300x300.png?raw=true";
	private String img4 = "/src/assets/4-300x300.png?raw=true";
	private String img5 = "/src/assets/5-300x300.png?raw=true";
	private String img6 = "/src/assets/6-300x300.png?raw=true";
	private String img7 = "/src/assets/7-300x300.png?raw=true";
	private String img8 = "/src/assets/8-300x300.png?raw=true";
	private String img9 = "/src/assets/9-300x300.png?raw=true";

	private final String MELISSA_FIRST_NAME = "Melissa";
	private final String MELISSA_LAST_NAME = "Meiste";
	
	private final long COMPANY_ID = 1L;
	private final long WEBSITE_ID = 1L;
	
	private final String COMPANY_URL = "npt";
	
	@Order(100)
	@Test
	void createNptCompany() {
		if(companyRepository.findById(COMPANY_ID).isEmpty()) {
			Company company = new Company();
			company.setCompanyName("Nephew Physical Therapy");
			company.setCompanyAcronym(COMPANY_URL);
			company.setCompanyUrl(COMPANY_URL);
			company.setId(COMPANY_ID);
			companyRepository.save(company);
		}
	}
	
	@Order(115)
	@Test
	void createServices() {
		if (servicesRepo.findByName(HEAD_AND_NECK).isEmpty()) {
			servicesRepo.save(new Services(HEAD_AND_NECK));
		}
		if (servicesRepo.findByName(SHOULDERS).isEmpty()) {
			servicesRepo.save(new Services(SHOULDERS));
		}
		if (servicesRepo.findByName(ELBOWS).isEmpty()) {
			servicesRepo.save(new Services(ELBOWS));
		}
		if (servicesRepo.findByName(WRISTS).isEmpty()) {
			servicesRepo.save(new Services(WRISTS));
		}
		if (servicesRepo.findByName(MID_BACK).isEmpty()) {
			servicesRepo.save(new Services(MID_BACK));
		}
		if (servicesRepo.findByName(LOWER_BACK).isEmpty()) {
			servicesRepo.save(new Services(LOWER_BACK));
		}
		if (servicesRepo.findByName(HIP).isEmpty()) {
			servicesRepo.save(new Services(HIP));
		}
		if (servicesRepo.findByName(KNEES).isEmpty()) {
			servicesRepo.save(new Services(KNEES));
		}
		if (servicesRepo.findByName(FOOT_AND_ANKLE).isEmpty()) {
			servicesRepo.save(new Services(FOOT_AND_ANKLE));
		}
		if (servicesRepo.findByName(BALANCE).isEmpty()) {
			servicesRepo.save(new Services(BALANCE));
		}
		if (servicesRepo.findByName(VESTIBULAR_REHAB).isEmpty()) {
			servicesRepo.save(new Services(VESTIBULAR_REHAB));
		}
		if (servicesRepo.findByName(MESSAGE_THERAPY).isEmpty()) {
			servicesRepo.save(new Services(MESSAGE_THERAPY));
		}
	}

	@Order(116)
	@Test
	void verifyHeadAndNeckService() {
		Services service = servicesRepo.findByName(HEAD_AND_NECK).get();
		assertEquals(HEAD_AND_NECK, service.getName());
	}

	@Order(117)
	@Test
	void verifyShouldersService() {
		Services service = servicesRepo.findByName(SHOULDERS).get();
		assertEquals(SHOULDERS, service.getName());
	}

	@Order(118)
	@Test
	void verifyElbowsService() {
		Services service = servicesRepo.findByName(ELBOWS).get();
		assertEquals(ELBOWS, service.getName());
	}

	@Order(119)
	@Test
	void verifyWristsService() {
		Services service = servicesRepo.findByName(WRISTS).get();
		assertEquals(WRISTS, service.getName());
	}

	@Order(120)
	@Test
	void verifyMidBackService() {
		Services service = servicesRepo.findByName(MID_BACK).get();
		assertEquals(MID_BACK, service.getName());
	}

	@Order(121)
	@Test
	void verifyLowerBackService() {
		Services service = servicesRepo.findByName(LOWER_BACK).get();
		assertEquals(LOWER_BACK, service.getName());
	}

	@Order(122)
	@Test
	void verifyHipService() {
		Services service = servicesRepo.findByName(HIP).get();
		assertEquals(HIP, service.getName());
	}

	@Order(123)
	@Test
	void verifyKneesService() {
		Services service = servicesRepo.findByName(KNEES).get();
		assertEquals(KNEES, service.getName());
	}

	@Order(124)
	@Test
	void verifyFootAndAnkleService() {
		Services service = servicesRepo.findByName(FOOT_AND_ANKLE).get();
		assertEquals(FOOT_AND_ANKLE, service.getName());
	}

	@Order(125)
	@Test
	void verifyBalanceService() {
		Services service = servicesRepo.findByName(BALANCE).get();
		assertEquals(BALANCE, service.getName());
	}

	@Order(126)
	@Test
	void verifyVestibularRehabService() {
		Services service = servicesRepo.findByName(VESTIBULAR_REHAB).get();
		assertEquals(VESTIBULAR_REHAB, service.getName());
	}

	@Order(127)
	@Test
	void verifyMessageTherapyService() {
		Services service = servicesRepo.findByName(MESSAGE_THERAPY).get();
		assertEquals(MESSAGE_THERAPY, service.getName());
	}
	
	@Order(128)
	@Test
	void createWebsite() {
		if (websiteRepository.findById(1L).isEmpty()) {
			Website website = new Website();
			website.setId(WEBSITE_ID);
			website.setName("Nephew Physical Therapy");
			website.setUrl(""); 
			website = websiteRepository.save(website);
			WebsiteSocialMediaProfile profile = new WebsiteSocialMediaProfile();
			profile.setWebsite(website);
			websiteSocialMediaProfileRepository.save(profile);
			
			Company company = companyRepository.findById(COMPANY_ID).get();
			company.setWebsite(website);
			company.assignIdToChildren();
			companyRepository.save(company);
		}
	}
	
	@Order(139)
	@Test
	void createOffice() {
		Optional<Office> officeOpt = officeRepository.findById(1L);

		if (officeOpt.isEmpty()) {
			Office nptOffice = new Office();
			nptOffice.setOfficeId(1L);
			nptOffice.setStreet("12723 N Bellwood Dr");
			nptOffice.setUnit("STE 10");
			nptOffice.setCity("Holland");
			nptOffice.setState("MI");
			nptOffice.setZip("49424");
			nptOffice.setPhone("1-616-796-9391");
			nptOffice.setFax("1-888-714-4474");
			nptOffice.setEmail("info@nephewpt.com");
			nptOffice.setIntroduction("You may always visit our office, located on Holland’s north side");
			nptOffice.setMapUrl("https://www.google.com/maps?q=+12723+N+Bellwood+Dr.,+Suite+10+Holland,+MI+49424");
			nptOffice.setAcceptingWalkIns(true);
			nptOffice = officeRepository.save(nptOffice);
			Set<OfficeDailySchedule> schedule = new HashSet<>();
			schedule.add(new OfficeDailySchedule("Mon", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(nptOffice.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Tue", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(nptOffice.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Wed", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(nptOffice.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Thu", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(nptOffice.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Fri", LocalTime.of(13, 0, 0), LocalTime.of(15, 0, 0),
					new Office(nptOffice.getOfficeId())));
			companyDailyScheduleRepository.saveAll(schedule);
			OfficeSocialMediaProfile officeSocialMediaProfile = new OfficeSocialMediaProfile();
			officeSocialMediaProfile.setOffice(nptOffice);
			officeSocialMediaProfileRepository.save(officeSocialMediaProfile);
			
			Company company = companyRepository.findById(COMPANY_ID).get();
			company.getOffices().add(nptOffice);
			company.assignIdToChildren();
			companyRepository.save(company);
		}
	}

	@Order(140)
	@Test
	void createMelissa() throws Exception {
		if (employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).isEmpty()) {
			Employee melissa = new Employee();
			melissa.setId(1L);
			melissa.setFirstName(MELISSA_FIRST_NAME);
			melissa.setMiddleName("M");
			melissa.setLastName(MELISSA_LAST_NAME);
			melissa.setImg(img1);
			melissa.setRole_id("MSPT");
			melissa.setRole("Physical Therapist");
			melissa.setMeta("CEO and Owner of Nephew Physical Therapy.");
			melissa.setEmail("mmm@mail.com");
			melissa.setWorkPhone("1-123-123-1234");
			melissa.setPersonalPhone("1-123-123-1234");
			melissa = employeeRepo.save(melissa);
		}
	}

	@Order(141)
	@Test
	void populateMelissaChildEntities() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);

		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepo.findAllExcept(VESTIBULAR_REHAB).get();
		Optional<Employee> melissaOptional = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME,
				MELISSA_LAST_NAME);
		if (melissaOptional.isPresent()) {
			Employee melissa = melissaOptional.get();
			melissa.setOffice(new Office(office.getOfficeId()));
			melissa.getServices().addAll(services);
			EmployeeSocialMediaProfile socialMediaProfile = new EmployeeSocialMediaProfile();
			socialMediaProfile.setFacebook(PROFILE_FACEBOOK);
			melissa.setProfile(socialMediaProfile);
			socialMediaProfile.setEmployee(melissa);
			melissa = employeeRepo.save(melissa);
		} else {
			fail("Melissa failed to load. This test failed.");
		}
	}

	private final String TEST_APPOINTMENT_FNAME = "Ethan";
	private final String TEST_APPOINTMENT_LNAME = "Nephew";
	private final String TEST_APPOINTMENT_EMAIL = "ejnephew@yahoo.com";
	private final String TEST_APPOINTMENT_PN = "1-616-566-4966";
	private final String TEST_APPOINTMENT_NOTES = "Test notes.";

	@Order(142)
	@Test
	void generateTestAppointmentsForMelissa() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		List<Appointment> oldAppointments = appointmentRepo.findAllByEmail(TEST_APPOINTMENT_EMAIL).get();
		appointmentRepo.deleteAllInBatch(oldAppointments);
		LocalDateTime currentDate = LocalDateTime.now();
		// int day = currentDate.getDayOfMonth();
		int day = 12;
		int month = currentDate.getMonthValue();
		int year = currentDate.getYear();

		List<Appointment> appointments = new ArrayList<>();
		for (int i = 7; i < 11; i++) {
			LocalDateTime beginTime = LocalDateTime.of(year, month, day, i, 00);
			LocalDateTime endTime = LocalDateTime.of(year, month, day, i + 1, 00);
			Appointment appointment = new Appointment();
			appointment.setEmail(TEST_APPOINTMENT_EMAIL);
			appointment.setPhoneNumber(TEST_APPOINTMENT_PN);
			appointment.setFirstName(TEST_APPOINTMENT_FNAME);
			appointment.setLastName(TEST_APPOINTMENT_LNAME);
			appointment.setNotes(TEST_APPOINTMENT_NOTES);
			appointment.setBeginTime(beginTime);
			appointment.setEndTime(endTime);
			appointment.setEmployee(new Employee(melissa.getId()));
			appointments.add(appointment);
		}

		LocalDateTime beginTime = LocalDateTime.of(year, month, day, 13, 30);
		LocalDateTime endTime = LocalDateTime.of(year, month, day, 14, 30);
		Appointment appointment = new Appointment();
		appointment.setEmail(TEST_APPOINTMENT_EMAIL);
		appointment.setPhoneNumber(TEST_APPOINTMENT_PN);
		appointment.setFirstName(TEST_APPOINTMENT_FNAME);
		appointment.setLastName(TEST_APPOINTMENT_LNAME);
		appointment.setNotes(TEST_APPOINTMENT_NOTES);
		appointment.setBeginTime(beginTime);
		appointment.setEndTime(endTime);
		appointment.setEmployee(new Employee(melissa.getId()));
		appointments.add(appointment);

		appointmentRepo.saveAll(appointments);
	}

	@Order(143)
	@Test
	void generateScheduleForMelissa() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();

		EmployeeDailySchedule mon = new EmployeeDailySchedule("Mon", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0));
		EmployeeDailySchedule tue = new EmployeeDailySchedule("Tue", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0));
		EmployeeDailySchedule wed = new EmployeeDailySchedule("Wed", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0));
		EmployeeDailySchedule thu = new EmployeeDailySchedule("Thu", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0));
		EmployeeDailySchedule fri = new EmployeeDailySchedule("Fri", LocalTime.of(13, 0, 0), LocalTime.of(15, 0, 0));

		mon.setEmployee(melissa);
		tue.setEmployee(melissa);
		wed.setEmployee(melissa);
		thu.setEmployee(melissa);
		fri.setEmployee(melissa);

		melissa.getSchedule().add(mon);
		melissa.getSchedule().add(tue);
		melissa.getSchedule().add(wed);
		melissa.getSchedule().add(thu);
		melissa.getSchedule().add(fri);

		employeeRepo.save(melissa);
	}
	
	@Order(144)
	@Test
	void generateBiographicalParagraphsForMelissa() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		
		BiographicalText textOne = new BiographicalText();
		textOne.setType(TextType.PARAGRAPH);
		textOne.setText("This is test biographical paragraph 1 for " + MELISSA_FIRST_NAME + ".");
		textOne.setPosition(1);
		BiographicalText textTwo = new BiographicalText();
		textTwo.setType(TextType.IMAGE);
		textTwo.setImageUrl("https://dfstudio-d420.kxcdn.com/wordpress/wp-content/uploads/2019/06/digital_camera_photo-1080x675.jpg");
		textTwo.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		textTwo.setPosition(2);
		BiographicalText textThree = new BiographicalText();
		textThree.setType(TextType.IMAGE);
		textThree.setText("This is just text. It will be changed later.");
		textThree.setPosition(3);
		
		textOne.setEmployee(melissa);
		textTwo.setEmployee(melissa);
		textThree.setEmployee(melissa);
		
		melissa.getBiographicalTexts().add(textOne);
		melissa.getBiographicalTexts().add(textTwo);
		melissa.getBiographicalTexts().add(textThree);
		
		employeeRepo.save(melissa);
	}
	
	@Order(145)
	@Test
	void generateInformationalParagraphsForMelissa() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		
		InformationalText textOne = new InformationalText();
		textOne.setType(TextType.PARAGRAPH);
		textOne.setText("This is test informational paragraph 1 for " + MELISSA_FIRST_NAME + ".");
		textOne.setPosition(1);
		InformationalText textTwo = new InformationalText();
		textTwo.setType(TextType.PARAGRAPH);
		textTwo.setText("This is test informational paragraph 2 for " + MELISSA_FIRST_NAME + ".");
		textTwo.setPosition(2);
		InformationalText textThree = new InformationalText();
		textThree.setType(TextType.PARAGRAPH);
		textThree.setText("This is test informational paragraph 3 for " + MELISSA_FIRST_NAME + ".");
		textThree.setPosition(3);
		InformationalText textFour = new InformationalText();
		textFour.setType(TextType.QUOTE);
		textFour.setText("This is test informational quote 4 for " + MELISSA_FIRST_NAME + ".");
		textFour.setPosition(4);
		
		textOne.setEmployee(melissa);
		textTwo.setEmployee(melissa);
		textThree.setEmployee(melissa);
		textFour.setEmployee(melissa);
		
		melissa.getInformationalTexts().add(textOne);
		melissa.getInformationalTexts().add(textTwo);
		melissa.getInformationalTexts().add(textThree);
		melissa.getInformationalTexts().add(textFour);
		
		employeeRepo.save(melissa);
		
	}
	
	private final String CHRISTINE_FIRST_NAME = "Christine";
	private final String CHRISTINE_LAST_NAME = "Byington";
	
	@Order(150)
	@Test
	void createChristine() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepo.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepo.findByFirstNameAndLastName(CHRISTINE_FIRST_NAME, CHRISTINE_LAST_NAME).isEmpty()) {
			Employee christine = new Employee();
			christine.setFirstName("Christine");
			christine.setLastName("Byington");
			christine.setImg(img6);
			christine.setRole_id("PT");
			christine.setRole("Lead Physical Therapist");
			christine.setMeta("Team member since 2016.");
			christine.setOffice(new Office(office.getOfficeId()));
			christine = employeeRepo.save(christine);
			christine.assignSpecialtiesWithEmployeeId(services);
			christine.generateScheduleFromOffice(office.getSchedule());
			employeeRepo.save(christine);
		}
	}
	
	@Order(160)
	@Test
	void createJenna() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepo.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepo.findByFirstNameAndLastName("Jenna", "Schra").isEmpty()) {
			Employee jenna = new Employee();
			jenna.setFirstName("Jenna");
			jenna.setLastName("Schra");
			jenna.setImg(img4);
			jenna.setRole_id("DPT");
			jenna.setRole("Physical Therapist");
			jenna.setMeta("Team member since 2020.");
			jenna.setOffice(new Office(office.getOfficeId()));
			jenna = employeeRepo.save(jenna);
			jenna.assignSpecialtiesWithEmployeeId(services);
			jenna.generateScheduleFromOffice(office.getSchedule());
			jenna = employeeRepo.save(jenna);
		}
	}

	@Order(170)
	@Test
	void createNaomi() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepo.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepo.findByFirstNameAndLastName("Naomi", "Stafford").isEmpty()) {
			Employee naomi = new Employee();
			naomi.setFirstName("Naomi");
			naomi.setLastName("Stafford");
			naomi.setImg(img9);
			naomi.setRole_id("PTA");
			naomi.setRole("Physical Therapy Assistant");
			naomi.setMeta("Team member since 2018.");
			naomi.setOffice(new Office(office.getOfficeId()));
			naomi = employeeRepo.save(naomi);
			naomi.assignSpecialtiesWithEmployeeId(services);
			naomi.generateScheduleFromOffice(office.getSchedule());
			naomi = employeeRepo.save(naomi);

		}
	}

	@Order(180)
	@Test
	void createCaroline() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepo.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepo.findByFirstNameAndLastName("Caroline", "Packard").isEmpty()) {
			Employee caroline = new Employee();
			caroline.setFirstName("Caroline");
			caroline.setLastName("Packard");
			caroline.setImg(img2);
			caroline.setRole_id("DPT");
			caroline.setRole("Physical Therapist");
			caroline.setMeta("Team member since 2015.");
			caroline.setOffice(new Office(office.getOfficeId()));
			caroline = employeeRepo.save(caroline);
			caroline.assignSpecialtiesWithEmployeeId(services);
			caroline.generateScheduleFromOffice(office.getSchedule());
			caroline = employeeRepo.save(caroline);

		}
	}

	@Order(190)
	@Test
	void createJoan() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = new ArrayList<>();
		services.add(servicesRepo.findServicesByName(VESTIBULAR_REHAB).get());

		if (employeeRepo.findByFirstNameAndLastName("Joan", "Kroeze").isEmpty()) {
			Employee joan = new Employee();
			joan.setFirstName("Joan");
			joan.setLastName("Kroeze");
			joan.setImg(img7);
			joan.setRole_id("MPT");
			joan.setRole("Physical Therapist");
			joan.setMeta("Team member since 2009.");
			joan.setOffice(officeOpt.get());
			joan.setOffice(new Office(office.getOfficeId()));
			joan = employeeRepo.save(joan);
			joan.assignSpecialtiesWithEmployeeId(services);
			joan.generateScheduleFromOffice(office.getSchedule());
			joan = employeeRepo.save(joan);

		}
	}

	@Order(1100)
	@Test
	void createBrittany() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();

		if (employeeRepo.findByFirstNameAndLastName("Brittany", "Marsh").isEmpty()) {
			Employee brittany = new Employee();
			brittany.setFirstName("Brittany");
			brittany.setLastName("Marsh");
			brittany.setImg(img3);
			brittany.setRole_id(null);
			brittany.setRole("Front Desk Manager");
			brittany.setMeta("Team member since 2020.");
			brittany = employeeRepo.save(brittany);
			brittany.setOffice(new Office(office.getOfficeId()));
			brittany.generateScheduleFromOffice(office.getSchedule());
			employeeRepo.save(brittany);

		}
	}

	@Order(1110)
	@Test
	void createRachel() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();

		if (employeeRepo.findByFirstNameAndLastName("Rachel", "Nephew").isEmpty()) {
			Employee rachel = new Employee();
			rachel.setFirstName("Rachel");
			rachel.setLastName("Nephew");
			rachel.setImg(img5);
			rachel.setRole_id(null);
			rachel.setRole("Marketing, Relationship Development");
			rachel.setMeta("Rejoined Team in 2018.");
			rachel = employeeRepo.save(rachel);
			rachel.setOffice(new Office(office.getOfficeId()));
			rachel.generateScheduleFromOffice(office.getSchedule());
			employeeRepo.save(rachel);
		}
	}

	@Order(1120)
	@Test
	void createAmber() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepo.findAllExcept(VESTIBULAR_REHAB).get();

		if (employeeRepo.findByFirstNameAndLastName("Amber", "Johnson").isEmpty()) {
			Employee amber = new Employee();
			amber.setFirstName("Amber");
			amber.setLastName("Johnson");
			amber.setImg(img8);
			amber.setRole_id("MSPT, PRN");
			amber.setRole("Physical Therapist");
			amber.setMeta("Team member since 2018.");
			amber.setOffice(new Office(office.getOfficeId()));
			amber = employeeRepo.save(amber);
			amber.assignSpecialtiesWithEmployeeId(services);
			amber.generateScheduleFromOffice(office.getSchedule());
			amber = employeeRepo.save(amber);
		}
	}
	
	@Order(1900)
	@Test
	void initFaqs() {
		FAQs faq1 = new FAQs();
		faq1.setQuestion("When is my payment due?");
		faq1.setAnswer("When appointment is made.");
		faq1.setQuestionIsAnswered(true);
		FAQs faq2 = new FAQs();
		faq2.setQuestion("Do I meed to be concerned about my Credit Card security?");
		faq2.setAnswer("Your Card info is stored in our HIPAA compliant software. Once saved, we can only see the last 4 digits.");
		faq2.setQuestionIsAnswered(true);
		FAQs faq3 = new FAQs();
		faq3.setQuestion("Do I meed to be concerned about my Credit Card security?");
		faq3.setAnswer("Your Card info is stored in our HIPAA compliant software. Once saved, we can only see the last 4 digits.");
		faq3.setQuestionIsAnswered(true);
		FAQs faq4 = new FAQs();
		faq4.setQuestion("Will this process thru my insurance?");
		faq4.setAnswer("Nope.");
		faq4.setQuestionIsAnswered(true);
		FAQs faq5 = new FAQs();
		faq5.setQuestion("What is your Cancellation Policy?");
		faq5.setAnswer("Caroline has your time slot reserved JUST FOR YOU. Sorry, no refunds for cancellations.");
		faq5.setQuestionIsAnswered(true);
		FAQs faq6 = new FAQs();
		faq6.setQuestion("What virtual meeting platform do you use for appointments?");
		faq6.setAnswer("ZOOM. We’ll email you a link after scheduling. Come to appointment ready with all your questions!");
		faq6.setQuestionIsAnswered(true);
		faqsRepository.save(faq1);
		faqsRepository.save(faq2);
		faqsRepository.save(faq3);
		faqsRepository.save(faq4);
		faqsRepository.save(faq5);
		faqsRepository.save(faq6);
		
		Company company = companyRepository.findById(COMPANY_ID).get();
		company.getFaqs().add(faq1);
		company.getFaqs().add(faq2);
		company.getFaqs().add(faq3);
		company.getFaqs().add(faq4);
		company.getFaqs().add(faq5);
		company.getFaqs().add(faq6);
		company.assignIdToChildren();
		companyRepository.save(company);
	}

	@Order(2000)
	@Test
	void testFindEmployeeByNameShouldBePresent() {
		Optional<Employee> melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME);
		assertTrue(melissa.isPresent());
	}

	@Order(2001)
	@Test
	void testFindSpecialityByNameShouldBePresent() {
		Optional<Services> specialty = servicesRepo.findByName(BALANCE);
		assertTrue(specialty.isPresent());
	}

	@Order(2002)
	@Test
	void testSpecialityShouldNotBeNull() {
		Optional<Services> servicesOptional = servicesRepo.findServicesByName(BALANCE);
		Services specialty = servicesOptional.get();
		assertNotNull(specialty);
	}

	@Order(2003)
	@Test
	void testAllSpecialityShouldNotBeNull() {
		Optional<List<Services>> servicesOptional = servicesRepo.findAllServices();
		assertNotNull(servicesOptional.get());
	}

	@Order(2004)
	@Test
	void testMelissaAppointmentsShouldBeGreaterThanZero() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertTrue(melissa.getAppointments().size() > 0);
	}

	@Order(2005)
	@Test
	void test() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		System.out.println(melissa.getOffice());
	}

	@Order(2006)
	@Test
	void testEmployeeAppointmentsShouldNotBeNull() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getAppointments());
	}

	@Order(2007)
	@Test
	void updateServicesFromMelissaShouldEqual3() {
		Set<Long> serviceIds = new HashSet<>();
		serviceIds.add(1L);
		serviceIds.add(2L);
		serviceIds.add(3L);
		Employee melissa = new Employee();
		try {
			melissa = employeeService.updateEmployeeServices(1L, serviceIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(3, melissa.getServices().size());
	}

	@Order(2008)
	@Test
	void updateServicesFromMelissaShouldEqual4() {
		Set<Long> serviceIds = new HashSet<>();
		serviceIds.add(1L);
		serviceIds.add(2L);
		serviceIds.add(3L);
		serviceIds.add(4L);

		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();

		List<Services> services = servicesRepo.findAllById(serviceIds);

		melissa.convertServicesListToSet(services);

		melissa = employeeService.updateEmployee(melissa);

		assertEquals(4, melissa.getServices().size());
	}

	@Order(2009)
	@Test
	void testMelissaProfileShouldNotBeNull() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getProfile());
	}

	@Order(2010)
	@Test
	void testMelissaDailyScheduleSizeShouldBe5() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(5, melissa.getSchedule().size());
	}
	
	@Order(2011)
	@Test
	void testMelissaBiographicalTextShouldNotBeNull() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getBiographicalTexts());
	}

	@Order(2012)
	@Test
	void testMelissaBiographicalTextSizeShouldBe3() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(3, melissa.getBiographicalTexts().size());
	}
	
	@Order(2013)
	@Test
	void testMelissaInformationalTextShouldNotBeNull() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getInformationalTexts());
	}

	@Order(2014)
	@Test
	void testMelissaInformationalTextSizeShouldBe3() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(4, melissa.getInformationalTexts().size());
	}
	
	@Order(2015)
	@Test
	void testMelissaModifyBiographicalTexts() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int size = melissa.getBiographicalTexts().size() - 1;
		melissa.getBiographicalTexts().get(size).setText(MELISSA_FIRST_NAME);
		employeeRepo.save(melissa);
		assertEquals(3, melissa.getBiographicalTexts().size());
	}
	
	@Order(2016)
	@Test
	void testMelissaModifyBiographicalTextsShouldEqual() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int size = melissa.getBiographicalTexts().size() - 1;
		assertEquals(MELISSA_FIRST_NAME, melissa.getBiographicalTexts().get(size).getText());
	}
	
	@Order(2017)
	@Test
	void testMelissaModifyBiographicalTextsShouldEqualSize() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		System.out.println(melissa.getBiographicalTexts());
		assertEquals(3, melissa.getBiographicalTexts().size());
	}
	
	@Order(2018)
	@Test
	void testMelissaProfileFacebookShouldEqual() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(PROFILE_FACEBOOK, melissa.getProfile().getFacebook());
	}
	
	@Order(2019)
	@Test
	void testMelissaLastElementIsParagraph() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int middleElement = melissa.getBiographicalTexts().size() - 1;
		assertEquals(TextType.IMAGE, melissa.getBiographicalTexts().get(middleElement).getType());
	}
	
	@Order(2020)
	@Test
	void testMelissaChangeTextToQuoteDoesNotThrow() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int middleElement = melissa.getBiographicalTexts().size() - 1;
		melissa.getBiographicalTexts().get(middleElement).setType(TextType.QUOTE);
		employeeRepo.save(melissa);
	}
	
	@Order(2021)
	@Test
	void testMelissaLastElementIsQuote() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int middleElement = melissa.getBiographicalTexts().size() - 1;
		assertEquals(TextType.QUOTE, melissa.getBiographicalTexts().get(middleElement).getType());
	}
	
	@Order(2022)
	@Test
	void testMelissaVerifyThatYoutubeProfileIsEmpty() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals("", melissa.getProfile().getYoutube());
	}
	
	@Order(2023)
	@Test
	void testMelissaChangeProfileYoutube() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		melissa.getProfile().setYoutube("www.youtube.com");
		employeeRepo.save(melissa);
	}
	
	@Order(2024)
	@Test
	void testMelissaProfileYoutubeShouldEqual() {
		Employee melissa = employeeRepo.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals("www.youtube.com", melissa.getProfile().getYoutube());
	}
	
	@Order(2100)
	@Test
	void testChristineScheduleShouldBe5() {
		Employee christine = employeeRepo.findByFirstNameAndLastName(CHRISTINE_FIRST_NAME, CHRISTINE_LAST_NAME).get();
		assertEquals(5, christine.getSchedule().size());
	}
	
	@Order(2600)
	@Test
	void officeShouldBeNpt() {
		List<Office> offices = officeRepository.findByCompanyUrl(COMPANY_URL);
		assertEquals(COMPANY_URL, offices.get(0).getCompany().getCompanyUrl());
	}
	
	@Order(2601)
	@Test
	void officesShouldBeGreaterThanZero() {
		List<Office> offices = officeRepository.findByCompanyUrl(COMPANY_URL);
		assertTrue(offices.size() > 0);
	}

	@Order(2700)
	@Test
	void employeesShouldBeNpt() {
		List<Employee> employees = employeeRepo.findByCompanyUrl(COMPANY_URL);
		assertEquals(COMPANY_URL, employees.get(0).getOffice().getCompany().getCompanyUrl());
	}
	
	@Order(2701)
	@Test
	void employeesShouldBeGreaterThanZero() {
		List<Employee> employees = employeeRepo.findByCompanyUrl(COMPANY_URL);
		assertTrue(employees.size() > 0);
	}
	
	@Order(2705)
	@Test
	void employeesShouldContainMelissa() {
		List<Employee> employees = employeeRepo.findByCompanyUrl(COMPANY_URL);
		Employee melissa = new Employee();
		for(Employee employee: employees) {
			if(employee.getFirstName().equals(MELISSA_FIRST_NAME) && employee.getLastName().equals(MELISSA_LAST_NAME)) {
				melissa = employee;
			}
		}
		assertEquals(MELISSA_FIRST_NAME, melissa.getFirstName());
		assertEquals(MELISSA_LAST_NAME, melissa.getLastName());
	}
	
	@Order(2800)
	@Test
	void websiteShouldBeNpt() {
		Website website = websiteRepository.findByCompanyUrl(COMPANY_URL);
		assertEquals(COMPANY_URL, website.getCompany().getCompanyUrl());
	}
	
	@Order(2801)
	@Test
	void websiteShouldHaveWebsiteId() {
		Website website = websiteRepository.findByCompanyUrl(COMPANY_URL);
		assertEquals(WEBSITE_ID, website.getId());
	}
	
}
