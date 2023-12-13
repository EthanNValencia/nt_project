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
import com.nephew.oesa.entities.States;
import com.nephew.oesa.entities.employee.BiographicalText;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.employee.EmployeeDailySchedule;
import com.nephew.oesa.entities.employee.EmployeeSocialMediaProfile;
import com.nephew.oesa.entities.employee.InformationalText;
import com.nephew.oesa.entities.services.ServiceText;
import com.nephew.oesa.entities.services.Services;
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

import jakarta.persistence.EntityNotFoundException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
class NptServiceApplicationTests {

	@Autowired
	private ServiceRepository servicesRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

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
	
	private final String TEST_APPOINTMENT_FNAME = "Ethan";
	private final String TEST_APPOINTMENT_LNAME = "Nephew";
	private final String TEST_APPOINTMENT_EMAIL = "ejnephew@yahoo.com";
	private final String TEST_APPOINTMENT_PN = "1-616-566-4966";
	private final String TEST_APPOINTMENT_NOTES = "Test notes.";

	private final long MELISSA_ID = 1L;
	private final String MELISSA_FIRST_NAME = "Melissa";
	private final String MELISSA_LAST_NAME = "Meiste";
	
	private final long COMPANY_ID = 1L;
	private final long WEBSITE_ID = 1L;
	private final long OFFICE_ID = 1L;
	private final long WEBSITE_PROFILE_ID = 1L;
	private final long OFFICE_PROFILE_ID = 1L;
	
	private final String COMPANY_URL = "npt";
	private final String COMPANY_NAME = "Nephew Physical Therapy";
	
	private final String HEAD_AND_NECK_TEXT = "This is test text for the head and neck service.";
	
	private final String WEBSITE_NAME = "Nephew Physical Therapy";
	private final String WEBSITE_URL = "/";
	private final String WEBSITE_PROFILE_YOUTUBE = "https://www.youtube.com/channel/UCP0_SudzP9_KQQKsUXOJPRg";
	private final String WEBSITE_PROFILE_FACEBOOK = "https://www.facebook.com/nephewpt";
	private final String WEBSITE_PROFILE_INSTAGRAM = "https://www.instagram.com/nephewpt/";
	private final String WEBSITE_PROFILE_YELP = "https://www.yelp.com/biz/nephew-physical-therapy-holland";
	
	private Website findWebsiteById(long id) {
		return websiteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Website not found."));
	}
	
	private Optional<Website> findWebsiteOptionalById(long id) {
	    return websiteRepository.findById(id);
	}
	
	private Company findCompanyById(long id) {
		return companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found."));
	}
	
	private Optional<Company> findCompanyOptionalById(long id) {
	    return companyRepository.findById(id);
	}
	
	private WebsiteSocialMediaProfile findWebsiteProfileById(long id) {
		return websiteSocialMediaProfileRepository.findById(WEBSITE_ID).orElseThrow(() -> new EntityNotFoundException("Website profile not found."));
	}
	
	private Optional<WebsiteSocialMediaProfile> findWebsiteProfileOptionalById(long id) {
	    return websiteSocialMediaProfileRepository.findById(id);
	}
	
	private Employee findEmployeeById(long id) {
		return employeeRepository.findById(WEBSITE_ID).orElseThrow(() -> new EntityNotFoundException("Employee not found."));
	}
	
	private Optional<Employee> findEmployeeOptionalById(long id) {
	    return employeeRepository.findById(id);
	}
	
	private Services findServiceByName(String name) {
		return servicesRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Service not found."));
	}
	
	private Optional<Services> findServiceOptionalByName(String name) {
	    return servicesRepository.findByName(name);
	}
	
	private Office findOfficeById(long id) {
		return officeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Office not found."));
	}
	
	private Website save(Website website) {
		return websiteRepository.save(website);
	}
	
	private Company save(Company company) {
		return companyRepository.save(company);
	}
	
	private WebsiteSocialMediaProfile save(WebsiteSocialMediaProfile profile) {
		return websiteSocialMediaProfileRepository.save(profile);
	}
	
	private Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	private OfficeSocialMediaProfile save(OfficeSocialMediaProfile profile) {
		return officeSocialMediaProfileRepository.save(profile);
	}
	
	private EmployeeSocialMediaProfile save(EmployeeSocialMediaProfile profile) {
		return employeeSocialMediaProfileRepository.save(profile);
	}
	
	private Office save(Office office) {
		return officeRepository.save(office);
	}
	
	@Order(100)
	@Test
	void createNptCompany() {
		if(findCompanyOptionalById(COMPANY_ID).isEmpty()) {
			Company company = new Company();
			company.setCompanyName(COMPANY_NAME);
			company.setCompanyAcronym(COMPANY_URL);
			company.setCompanyUrl(COMPANY_URL);
			company.setId(COMPANY_ID);
			save(company);
		}
	}
	
	@Order(150)
	@Test
	void createServices() {
		if (findServiceOptionalByName(HEAD_AND_NECK).isEmpty()) {
			Services headAndNeck = new Services(HEAD_AND_NECK);
			ServiceText serviceTextOne = new ServiceText();
			serviceTextOne.setType(TextType.PARAGRAPH);
			serviceTextOne.setText(HEAD_AND_NECK_TEXT);
			serviceTextOne.setPosition(1);
			serviceTextOne.setService(headAndNeck);
			headAndNeck.getServiceTexts().add(serviceTextOne);
			servicesRepository.save(headAndNeck);
		}
		if (findServiceOptionalByName(SHOULDERS).isEmpty()) {
			servicesRepository.save(new Services(SHOULDERS));
		}
		if (findServiceOptionalByName(ELBOWS).isEmpty()) {
			servicesRepository.save(new Services(ELBOWS));
		}
		if (findServiceOptionalByName(WRISTS).isEmpty()) {
			servicesRepository.save(new Services(WRISTS));
		}
		if (findServiceOptionalByName(MID_BACK).isEmpty()) {
			servicesRepository.save(new Services(MID_BACK));
		}
		if (findServiceOptionalByName(LOWER_BACK).isEmpty()) {
			servicesRepository.save(new Services(LOWER_BACK));
		}
		if (findServiceOptionalByName(HIP).isEmpty()) {
			servicesRepository.save(new Services(HIP));
		}
		if (findServiceOptionalByName(KNEES).isEmpty()) {
			servicesRepository.save(new Services(KNEES));
		}
		if (findServiceOptionalByName(FOOT_AND_ANKLE).isEmpty()) {
			servicesRepository.save(new Services(FOOT_AND_ANKLE));
		}
		if (findServiceOptionalByName(BALANCE).isEmpty()) {
			servicesRepository.save(new Services(BALANCE));
		}
		if (findServiceOptionalByName(VESTIBULAR_REHAB).isEmpty()) {
			servicesRepository.save(new Services(VESTIBULAR_REHAB));
		}
		if (findServiceOptionalByName(MESSAGE_THERAPY).isEmpty()) {
			servicesRepository.save(new Services(MESSAGE_THERAPY));
		}
	}

	@Order(160)
	@Test
	void verifyHeadAndNeckService() {
		Services service = findServiceByName(HEAD_AND_NECK);
		assertEquals(HEAD_AND_NECK, service.getName());
	}

	@Order(170)
	@Test
	void verifyShouldersService() {
		Services service = findServiceByName(SHOULDERS);
		assertEquals(SHOULDERS, service.getName());
	}

	@Order(180)
	@Test
	void verifyElbowsService() {
		Services service = findServiceByName(ELBOWS);
		assertEquals(ELBOWS, service.getName());
	}

	@Order(190)
	@Test
	void verifyWristsService() {
		Services service = findServiceByName(WRISTS);
		assertEquals(WRISTS, service.getName());
	}

	@Order(200)
	@Test
	void verifyMidBackService() {
		Services service = findServiceByName(MID_BACK);
		assertEquals(MID_BACK, service.getName());
	}

	@Order(210)
	@Test
	void verifyLowerBackService() {
		Services service = findServiceByName(LOWER_BACK);
		assertEquals(LOWER_BACK, service.getName());
	}

	@Order(220)
	@Test
	void verifyHipService() {
		Services service = findServiceByName(HIP);
		assertEquals(HIP, service.getName());
	}

	@Order(230)
	@Test
	void verifyKneesService() {
		Services service = findServiceByName(KNEES);
		assertEquals(KNEES, service.getName());
	}

	@Order(240)
	@Test
	void verifyFootAndAnkleService() {
		Services service = findServiceByName(FOOT_AND_ANKLE);
		assertEquals(FOOT_AND_ANKLE, service.getName());
	}

	@Order(250)
	@Test
	void verifyBalanceService() {
		Services service = findServiceByName(BALANCE);
		assertEquals(BALANCE, service.getName());
	}

	@Order(260)
	@Test
	void verifyVestibularRehabService() {
		Services service = findServiceByName(VESTIBULAR_REHAB);
		assertEquals(VESTIBULAR_REHAB, service.getName());
	}

	@Order(270)
	@Test
	void verifyMessageTherapyService() {
		Services service = findServiceByName(MESSAGE_THERAPY);
		assertEquals(MESSAGE_THERAPY, service.getName());
	}
	
	@Order(280)
	@Test
	void createWebsite() {
		if (findWebsiteOptionalById(WEBSITE_ID).isEmpty()) {
			Website website = new Website();
			website.setId(WEBSITE_ID);
			website.setName(WEBSITE_NAME);
			website.setUrl(WEBSITE_URL); 
			save(website);
		}
	}
	
	@Order(285)
	@Test
	void createWebsiteProfile() {
		if (findWebsiteProfileOptionalById(WEBSITE_ID).isEmpty()) {
			WebsiteSocialMediaProfile profile = new WebsiteSocialMediaProfile();
			profile.setId(WEBSITE_PROFILE_ID);
			profile.setYoutube(WEBSITE_PROFILE_YOUTUBE);
			profile.setFacebook(WEBSITE_PROFILE_FACEBOOK);
			profile.setInstagram(WEBSITE_PROFILE_INSTAGRAM);
			profile.setYelp(WEBSITE_PROFILE_YELP);
			save(profile);
		}
	}
	
	@Order(288)
	@Test
	void attachWebsiteAndProfile() {
	    Website website = findWebsiteById(WEBSITE_ID);
	    WebsiteSocialMediaProfile profile = findWebsiteProfileById(WEBSITE_PROFILE_ID);
	    profile.setWebsite(website);
		website.setProfile(profile);
		save(website);
	}
	
	@Order(290)
	@Test
	void verifyWebsiteProfileYoutube() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_YOUTUBE, website.getProfile().getYoutube());
	}
	
	@Order(291)
	@Test
	void verifyWebsiteProfileFacebook() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_FACEBOOK, website.getProfile().getFacebook());
	}
	
	@Order(292)
	@Test
	void verifyWebsiteProfileInstagram() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_INSTAGRAM, website.getProfile().getInstagram());
	}
	
	@Order(293)
	@Test
	void verifyWebsiteProfileYelp() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_YELP, website.getProfile().getYelp());
	}
	
	@Order(294)
	@Test
	void verifyWebsiteName() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_NAME, website.getName());
	}
	
	@Order(295)
	@Test
	void verifyWebsiteProfileUrl() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_URL, website.getUrl());
	}
	
	@Order(300)
	@Test
	void attachWebsiteToCompany() {
		Website website = findWebsiteById(WEBSITE_ID);
		Company company = findCompanyById(COMPANY_ID);
		company.setWebsite(website);
		company.assignIdToChildren();
		save(company);
		save(website);
	}
	
	@Order(301)
	@Test
	void verifyCompanyWebsiteIsNotNull() {
		Company company = findCompanyById(COMPANY_ID);
		assertNotNull(company.getWebsite());
	}
	
	@Order(305)
	@Test
	void saveCompanyDoesNotRemoveWebsite() {
		Company company = findCompanyById(COMPANY_ID);
		company.assignIdToChildren();
		save(company);
		Website website = findWebsiteById(WEBSITE_ID);
		assertNotNull(website.getCompany());
	}
	
	@Order(350)
	@Test
	void createOffice() {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			Office office = new Office();
			office.setOfficeId(OFFICE_ID);
			office.setStreet("12723 N Bellwood Dr");
			office.setUnit("STE 10");
			office.setCity("Holland");
			office.setState(States.MI);
			office.setZip("49424");
			office.setPhone("1-616-796-9391");
			office.setFax("1-888-714-4474");
			office.setEmail("info@nephewpt.com");
			office.setIntroduction("You may always visit our office, located on Holland’s north side");
			office.setMapUrl("https://www.google.com/maps?q=+12723+N+Bellwood+Dr.,+Suite+10+Holland,+MI+49424");
			office.setAcceptingWalkIns(true);
			office = save(office);
			
			Set<OfficeDailySchedule> schedule = new HashSet<>();
			schedule.add(new OfficeDailySchedule("Mon", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(office.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Tue", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(office.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Wed", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(office.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Thu", LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0),
					new Office(office.getOfficeId())));
			schedule.add(new OfficeDailySchedule("Fri", LocalTime.of(13, 0, 0), LocalTime.of(15, 0, 0),
					new Office(office.getOfficeId())));
			companyDailyScheduleRepository.saveAll(schedule);
		}
	}
	
	@Order(355)
	@Test
	void attachOfficeToCompany() {
		Office office = findOfficeById(OFFICE_ID);
		Company company = findCompanyById(COMPANY_ID);
		office.setCompany(company);
		company.getOffices().add(office);
		save(company);
		save(office);
	}
	
	@Order(360)
	@Test
	void attachProfileToOffice() {
		Office office = findOfficeById(OFFICE_ID);
		OfficeSocialMediaProfile profile = new OfficeSocialMediaProfile();
		profile.setId(OFFICE_PROFILE_ID);
		profile.setFacebook(PROFILE_FACEBOOK);
		profile.setOffice(office);
		office.setOfficeSocialMedialProfile(profile);
		save(office);
	}
	
	@Order(370)
	@Test
	void officeShouldHaveCompany() {
		Office office = findOfficeById(OFFICE_ID);
		assertEquals(COMPANY_ID, office.getCompany().getId());
	}
	
	@Order(371)
	@Test
	void officeProfileFacebookShouldEqual() {
		Office office = findOfficeById(OFFICE_ID);
		assertEquals(PROFILE_FACEBOOK, office.getOfficeSocialMedialProfile().getFacebook());
	}

	@Order(400)
	@Test
	void createMelissa() throws Exception {
		if (findEmployeeOptionalById(MELISSA_ID).isEmpty()) {
			Employee melissa = new Employee();
			melissa.setId(MELISSA_ID);
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
			save(melissa);
		}
	}

	@Order(410)
	@Test
	void populateMelissaChildEntities() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);

		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		
		Office office = officeOpt.get();
		
		List<Services> services = servicesRepository.findAllExcept(VESTIBULAR_REHAB).get();
		if (findEmployeeOptionalById(MELISSA_ID).isPresent()) {
			Employee melissa = findEmployeeById(MELISSA_ID);
			melissa.setOffice(new Office(office.getOfficeId()));
			melissa.getServices().addAll(services);
			EmployeeSocialMediaProfile socialMediaProfile = new EmployeeSocialMediaProfile();
			socialMediaProfile.setFacebook(PROFILE_FACEBOOK);
			melissa.setProfile(socialMediaProfile);
			socialMediaProfile.setEmployee(melissa);
			save(melissa);
		} else {
			fail("Melissa failed to load. This test failed.");
		}
	}

	@Order(420)
	@Test
	void generateTestAppointmentsForMelissa() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		List<Appointment> oldAppointments = appointmentRepository.findAllByEmail(TEST_APPOINTMENT_EMAIL).get();
		appointmentRepository.deleteAllInBatch(oldAppointments);
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

		appointmentRepository.saveAll(appointments);
	}

	@Order(430)
	@Test
	void generateScheduleForMelissa() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();

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

		save(melissa);
	}
	
	@Order(440)
	@Test
	void generateBiographicalParagraphsForMelissa() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		
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
		
		save(melissa);
	}
	
	@Order(450)
	@Test
	void generateInformationalParagraphsForMelissa() {
		Employee melissa = findEmployeeById(MELISSA_ID);
		
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
		
		save(melissa);
		
	}
	
	private final String CHRISTINE_FIRST_NAME = "Christine";
	private final String CHRISTINE_LAST_NAME = "Byington";
	
	@Order(500)
	@Test
	void createChristine() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepository.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepository.findByFirstNameAndLastName(CHRISTINE_FIRST_NAME, CHRISTINE_LAST_NAME).isEmpty()) {
			Employee christine = new Employee();
			christine.setFirstName("Christine");
			christine.setLastName("Byington");
			christine.setImg(img6);
			christine.setRole_id("PT");
			christine.setRole("Lead Physical Therapist");
			christine.setMeta("Team member since 2016.");
			christine.setOffice(new Office(office.getOfficeId()));
			christine = save(christine);
			christine.assignSpecialtiesWithEmployeeId(services);
			christine.generateScheduleFromOffice(office.getSchedule());
			save(christine);
		}
	}
	
	@Order(600)
	@Test
	void createJenna() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepository.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepository.findByFirstNameAndLastName("Jenna", "Schra").isEmpty()) {
			Employee jenna = new Employee();
			jenna.setFirstName("Jenna");
			jenna.setLastName("Schra");
			jenna.setImg(img4);
			jenna.setRole_id("DPT");
			jenna.setRole("Physical Therapist");
			jenna.setMeta("Team member since 2020.");
			jenna.setOffice(new Office(office.getOfficeId()));
			jenna = save(jenna);
			jenna.assignSpecialtiesWithEmployeeId(services);
			jenna.generateScheduleFromOffice(office.getSchedule());
			jenna = save(jenna);
		}
	}

	@Order(700)
	@Test
	void createNaomi() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepository.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepository.findByFirstNameAndLastName("Naomi", "Stafford").isEmpty()) {
			Employee naomi = new Employee();
			naomi.setFirstName("Naomi");
			naomi.setLastName("Stafford");
			naomi.setImg(img9);
			naomi.setRole_id("PTA");
			naomi.setRole("Physical Therapy Assistant");
			naomi.setMeta("Team member since 2018.");
			naomi.setOffice(new Office(office.getOfficeId()));
			naomi = save(naomi);
			naomi.assignSpecialtiesWithEmployeeId(services);
			naomi.generateScheduleFromOffice(office.getSchedule());
			naomi = save(naomi);

		}
	}

	@Order(800)
	@Test
	void createCaroline() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = servicesRepository.findAllExcept(VESTIBULAR_REHAB).get();
		if (employeeRepository.findByFirstNameAndLastName("Caroline", "Packard").isEmpty()) {
			Employee caroline = new Employee();
			caroline.setFirstName("Caroline");
			caroline.setLastName("Packard");
			caroline.setImg(img2);
			caroline.setRole_id("DPT");
			caroline.setRole("Physical Therapist");
			caroline.setMeta("Team member since 2015.");
			caroline.setOffice(new Office(office.getOfficeId()));
			caroline = save(caroline);
			caroline.assignSpecialtiesWithEmployeeId(services);
			caroline.generateScheduleFromOffice(office.getSchedule());
			caroline = save(caroline);

		}
	}

	@Order(900)
	@Test
	void createJoan() throws Exception {
		Optional<Office> officeOpt = officeRepository.findById(1L);
		if (officeOpt.isEmpty()) {
			throw new Exception("Office with id of 1 was not found.");
		}
		Office office = officeOpt.get();
		List<Services> services = new ArrayList<>();
		services.add(findServiceByName(VESTIBULAR_REHAB));

		if (employeeRepository.findByFirstNameAndLastName("Joan", "Kroeze").isEmpty()) {
			Employee joan = new Employee();
			joan.setFirstName("Joan");
			joan.setLastName("Kroeze");
			joan.setImg(img7);
			joan.setRole_id("MPT");
			joan.setRole("Physical Therapist");
			joan.setMeta("Team member since 2009.");
			joan.setOffice(officeOpt.get());
			joan.setOffice(new Office(office.getOfficeId()));
			joan = save(joan);
			joan.assignSpecialtiesWithEmployeeId(services);
			joan.generateScheduleFromOffice(office.getSchedule());
			joan = save(joan);

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

		if (employeeRepository.findByFirstNameAndLastName("Brittany", "Marsh").isEmpty()) {
			Employee brittany = new Employee();
			brittany.setFirstName("Brittany");
			brittany.setLastName("Marsh");
			brittany.setImg(img3);
			brittany.setRole_id(null);
			brittany.setRole("Front Desk Manager");
			brittany.setMeta("Team member since 2020.");
			brittany = save(brittany);
			brittany.setOffice(new Office(office.getOfficeId()));
			brittany.generateScheduleFromOffice(office.getSchedule());
			save(brittany);

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

		if (employeeRepository.findByFirstNameAndLastName("Rachel", "Nephew").isEmpty()) {
			Employee rachel = new Employee();
			rachel.setFirstName("Rachel");
			rachel.setLastName("Nephew");
			rachel.setImg(img5);
			rachel.setRole_id(null);
			rachel.setRole("Marketing, Relationship Development");
			rachel.setMeta("Rejoined Team in 2018.");
			rachel = save(rachel);
			rachel.setOffice(new Office(office.getOfficeId()));
			rachel.generateScheduleFromOffice(office.getSchedule());
			save(rachel);
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
		List<Services> services = servicesRepository.findAllExcept(VESTIBULAR_REHAB).get();

		if (employeeRepository.findByFirstNameAndLastName("Amber", "Johnson").isEmpty()) {
			Employee amber = new Employee();
			amber.setFirstName("Amber");
			amber.setLastName("Johnson");
			amber.setImg(img8);
			amber.setRole_id("MSPT, PRN");
			amber.setRole("Physical Therapist");
			amber.setMeta("Team member since 2018.");
			amber.setOffice(new Office(office.getOfficeId()));
			amber = save(amber);
			amber.assignSpecialtiesWithEmployeeId(services);
			amber.generateScheduleFromOffice(office.getSchedule());
			amber = save(amber);
		}
	}
	
	@Order(1900)
	@Test
	void createFaqs() {
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
		
		Company company = findCompanyById(COMPANY_ID);
		company.getFaqs().add(faq1);
		company.getFaqs().add(faq2);
		company.getFaqs().add(faq3);
		company.getFaqs().add(faq4);
		company.getFaqs().add(faq5);
		company.getFaqs().add(faq6);
		company.assignIdToChildren();
		save(company);
	}

	@Order(2000)
	@Test
	void testFindEmployeeByNameShouldBePresent() {
		Optional<Employee> melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME);
		assertTrue(melissa.isPresent());
	}

	@Order(2001)
	@Test
	void testFindSpecialityByNameShouldBePresent() {
		Optional<Services> specialty = findServiceOptionalByName(BALANCE);
		assertTrue(specialty.isPresent());
	}

	@Order(2002)
	@Test
	void testServiceBalanceShouldEqual() {
		Services service = findServiceByName(BALANCE);
		assertEquals(BALANCE, service.getName());
	}

	@Order(2003)
	@Test
	void testAllServicesShouldNotBeNull() {
		Optional<List<Services>> servicesOptional = servicesRepository.findAllServices();
		assertNotNull(servicesOptional.get());
	}

	@Order(2004)
	@Test
	void testMelissaAppointmentsShouldBeGreaterThanZero() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertTrue(melissa.getAppointments().size() > 0);
	}

	@Order(2005)
	@Test
	void test() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		System.out.println(melissa.getOffice());
	}

	@Order(2006)
	@Test
	void testEmployeeAppointmentsShouldNotBeNull() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
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

		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();

		List<Services> services = servicesRepository.findAllById(serviceIds);

		melissa.convertServicesListToSet(services);

		melissa = employeeService.updateEmployee(melissa);

		assertEquals(4, melissa.getServices().size());
	}

	@Order(2009)
	@Test
	void testMelissaProfileShouldNotBeNull() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getProfile());
	}

	@Order(2010)
	@Test
	void testMelissaDailyScheduleSizeShouldBe5() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(5, melissa.getSchedule().size());
	}
	
	@Order(2011)
	@Test
	void testMelissaBiographicalTextShouldNotBeNull() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getBiographicalTexts());
	}

	@Order(2012)
	@Test
	void testMelissaBiographicalTextSizeShouldBe3() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(3, melissa.getBiographicalTexts().size());
	}
	
	@Order(2013)
	@Test
	void testMelissaInformationalTextShouldNotBeNull() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertNotNull(melissa.getInformationalTexts());
	}

	@Order(2014)
	@Test
	void testMelissaInformationalTextSizeShouldBe3() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(4, melissa.getInformationalTexts().size());
	}
	
	@Order(2015)
	@Test
	void testMelissaModifyBiographicalTexts() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int size = melissa.getBiographicalTexts().size() - 1;
		melissa.getBiographicalTexts().get(size).setText(MELISSA_FIRST_NAME);
		save(melissa);
		assertEquals(3, melissa.getBiographicalTexts().size());
	}
	
	@Order(2016)
	@Test
	void testMelissaModifyBiographicalTextsShouldEqual() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int size = melissa.getBiographicalTexts().size() - 1;
		assertEquals(MELISSA_FIRST_NAME, melissa.getBiographicalTexts().get(size).getText());
	}
	
	@Order(2017)
	@Test
	void testMelissaModifyBiographicalTextsShouldEqualSize() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		System.out.println(melissa.getBiographicalTexts());
		assertEquals(3, melissa.getBiographicalTexts().size());
	}
	
	@Order(2018)
	@Test
	void testMelissaProfileFacebookShouldEqual() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals(PROFILE_FACEBOOK, melissa.getProfile().getFacebook());
	}
	
	@Order(2019)
	@Test
	void testMelissaLastElementIsParagraph() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int middleElement = melissa.getBiographicalTexts().size() - 1;
		assertEquals(TextType.IMAGE, melissa.getBiographicalTexts().get(middleElement).getType());
	}
	
	@Order(2020)
	@Test
	void testMelissaChangeTextToQuoteDoesNotThrow() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int middleElement = melissa.getBiographicalTexts().size() - 1;
		melissa.getBiographicalTexts().get(middleElement).setType(TextType.QUOTE);
		save(melissa);
	}
	
	@Order(2021)
	@Test
	void testMelissaLastElementIsQuote() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		int middleElement = melissa.getBiographicalTexts().size() - 1;
		assertEquals(TextType.QUOTE, melissa.getBiographicalTexts().get(middleElement).getType());
	}
	
	@Order(2022)
	@Test
	void testMelissaVerifyThatYoutubeProfileIsEmpty() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals("", melissa.getProfile().getYoutube());
	}
	
	@Order(2023)
	@Test
	void testMelissaChangeProfileYoutube() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		melissa.getProfile().setYoutube("www.youtube.com");
		save(melissa);
	}
	
	@Order(2024)
	@Test
	void testMelissaProfileYoutubeShouldEqual() {
		Employee melissa = employeeRepository.findByFirstNameAndLastName(MELISSA_FIRST_NAME, MELISSA_LAST_NAME).get();
		assertEquals("www.youtube.com", melissa.getProfile().getYoutube());
	}
	
	@Order(2100)
	@Test
	void testChristineScheduleShouldBe5() {
		Employee christine = employeeRepository.findByFirstNameAndLastName(CHRISTINE_FIRST_NAME, CHRISTINE_LAST_NAME).get();
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
		List<Employee> employees = employeeRepository.findByCompanyUrl(COMPANY_URL);
		assertEquals(COMPANY_URL, employees.get(0).getOffice().getCompany().getCompanyUrl());
	}
	
	@Order(2701)
	@Test
	void employeesShouldBeGreaterThanZero() {
		List<Employee> employees = employeeRepository.findByCompanyUrl(COMPANY_URL);
		assertTrue(employees.size() > 0);
	}
	
	@Order(2705)
	@Test
	void employeesShouldContainMelissa() {
		List<Employee> employees = employeeRepository.findByCompanyUrl(COMPANY_URL);
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
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(COMPANY_URL, website.getCompany().getCompanyUrl());
	}
	
	@Order(2801)
	@Test
	void websiteShouldHaveWebsiteId() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_ID, website.getId());
	}
	
	@Order(3000)
	@Test
	void serviceShouldContainText() {
		Services headAndShoulders = findServiceOptionalByName(HEAD_AND_NECK).get();
		assertEquals(HEAD_AND_NECK_TEXT, headAndShoulders.getServiceTexts().get(0).getText());
	}
	
	@Order(3500)
	@Test
	void createSecondOfficeShouldNotThrow() {
		Office office = new Office();
		OfficeSocialMediaProfile profile = new OfficeSocialMediaProfile();
		office.setOfficeSocialMedialProfile(profile);
		officeRepository.save(office);
	}
	
}
