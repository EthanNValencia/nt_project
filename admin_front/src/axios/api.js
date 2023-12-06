import axios from "axios";

const authUrl = "http://localhost:8765/security-service/api/v1/public";
const privateUrl = "http://localhost:8765/npt-service/api/v1/auth";
const errorReportingUrl =
  "http://localhost:8765/error-service/api/v1/public/error/";

export async function register(user, type) {
  const registerUrl = authUrl + "/register/" + type;
  const requestBody = {
    ...user,
  };
  try {
    const response = await axios.post(registerUrl, requestBody);
    return response.data.token;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function authenticate(user) {
  // http://localhost:8765/security-service/api/v1/public/authenticate
  const authenticationUrl = authUrl + "/authenticate";
  const requestBody = {
    ...user,
  };
  try {
    const response = await axios.post(authenticationUrl, requestBody);
    return response.data.token;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function validate(token) {
  const validationUrl = authUrl + "/validate/" + token;
  try {
    const response = await axios.get(validationUrl);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function validateAction(token, action) {
  const registerUrl = authUrl + "/validate-action/" + token;
  const requestBody = {
    ...action,
  };
  try {
    const response = await axios.post(registerUrl, requestBody);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminGetEmployees(token) {
  const adminEmployeeUrl = privateUrl + "/employees/";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.get(adminEmployeeUrl, { headers });
    // console.log(JSON.stringify(response.data));
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminGetUnansweredQuestions(token) {
  const adminFaqUrl = privateUrl + "/faqs/get-all";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    console.log("Token: " + token);
    const response = await axios.get(adminFaqUrl, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

async function handleErrorReporting(error) {
  try {
    await reportError(error);
  } catch (reportingError) {
    console.error(
      "An error occurred while attempting to report the error:",
      reportingError
    );
  }
}

export async function reportError(error) {
  const requestBody = {
    ...error,
  };
  try {
    await axios.post(errorReportingUrl, requestBody);
  } catch (error) {
    console.log("An error occured while trying to report the error: " + error);
    throw error;
  }
}

export async function deleteFaqApi(id, token) {
  const deleteFaqUrl = privateUrl + "/faqs/" + id;

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.delete(deleteFaqUrl, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function updateFaqApi(faq, token) {
  const updateFaqUrl = privateUrl + "/faqs/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.put(updateFaqUrl, faq, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminGetWebsite(token) {
  const adminWebsiteUrl = privateUrl + "/website/";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.get(adminWebsiteUrl, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminUpdateWebsite(website, token) {
  const adminWebsiteUrl = privateUrl + "/website/";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.post(adminWebsiteUrl, website, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    console.error("Error updating website:", error);
    throw error;
  }
}

export async function adminCreatePage(website, token) {
  const adminWebsiteUrl = privateUrl + "/website/pages";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.post(adminWebsiteUrl, website, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminCreateParagraph(page, token) {
  const adminWebsiteUrl = privateUrl + "/website/pages/paragraph";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.post(adminWebsiteUrl, page, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminNotifyAppointment(appointment, token) {
  const adminNotifyUrl = privateUrl + "/notify/";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.put(adminNotifyUrl, appointment, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

// /notify/email

export async function adminNotifyByEmailAppointment(
  appointment,
  emailHtml,
  token
) {
  const adminNotifyUrl = privateUrl + "/notify/email/";

  console.log(emailHtml);

  const emailDto = {
    to: appointment.email,
    from: null,
    subject: "This is an email test.",
    html: emailHtml,
  };
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.post(adminNotifyUrl, emailDto, {
      headers,
    });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function deleteEmployeeApi(id, token) {
  // http://localhost:8765/npt-service/api/v1/auth/faqs/60
  const deleteEmployeeUrl = privateUrl + "/employees/" + id;

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.delete(deleteEmployeeUrl, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function updateEmployeeApi(employee, token) {
  const updateEmployeeUrl = privateUrl + "/employees/";
  console.log("Updating: " + JSON.stringify(employee));
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.put(updateEmployeeUrl, employee, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function employeeAddBiographicalText(position, employee, token) {
  const url = privateUrl + "/employees/biographical/" + position;
  console.log("Updating: " + JSON.stringify(employee));
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.post(url, employee, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function employeeAddInformationalText(position, employee, token) {
  const url = privateUrl + "/employees/informational/" + position;
  console.log("Updating: " + JSON.stringify(employee));
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.post(url, employee, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

// /services/

export async function adminGetServices(token) {
  const servicesUrl = privateUrl + "/services/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    console.log("Token: " + token);
    const response = await axios.get(servicesUrl, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

// adminPutServices

export async function adminPutServices(services, token) {
  const servicesUrl = privateUrl + "/services/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.put(servicesUrl, services, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminDeleteService(id, token) {
  const servicesUrl = privateUrl + "/services/" + id;

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.delete(servicesUrl, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminGetOffices(token) {
  const servicesUrl = privateUrl + "/offices/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    //console.log("Token: " + token);
    const response = await axios.get(servicesUrl, { headers });
    //console.log(JSON.stringify(response.data));
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function adminPostOffice(office, token) {
  const servicesUrl = privateUrl + "/offices/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    //console.log("Token: " + token);
    const response = await axios.post(servicesUrl, office, { headers });
    //console.log(JSON.stringify(response.data));
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

const validateResponse = (data) => {
  if (data == "ok") {
    return true;
  }
  return false;
};

export async function connectSecurityService() {
  const url = "http://localhost:8765/security-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    // console.log(response.data);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectErrorService() {
  const url = "http://localhost:8765/error-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    // console.log(response.data);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectNptService() {
  const url = "http://localhost:8765/npt-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectEmailService() {
  const url = "http://localhost:8765/email-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectSmsService() {
  const url = "http://localhost:8765/sms-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectGatewayService() {
  const url = "http://localhost:8765/api-gateway/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectNamingServer() {
  const url = "http://localhost:8765/naming-server/api/v1/public/health"; // This won't work.
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

// naming-server
