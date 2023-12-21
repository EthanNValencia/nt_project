import axios from "axios";

const authUrl = "http://localhost:8765/security-service/api/v1/public/";
const privateUrl = "http://localhost:8765/oesa-service/api/v1/auth/npt";
const errorReportingUrl =
  "http://localhost:8765/error-service/api/v1/public/error/";

export async function register(user) {
  const registerUrl = authUrl + "/register";
  const requestBody = {
    ...user,
  };
  try {
    const response = await axios.post(registerUrl, requestBody);
    return response.data.token;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function approveApprovalCodeApi(approvalCode) {
  const url = authUrl + "/register/approve/" + approvalCode;
  try {
    const response = await axios.post(url);
    return response.data.token;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function authenticate(user) {
  const authenticationUrl = authUrl + "/authenticate";
  const requestBody = {
    ...user,
  };
  try {
    const response = await axios.post(authenticationUrl, requestBody);
    return response.data.token;
  } catch (error) {
    handleErrorReporting(error);
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
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function getFaqs(token) {
  const adminFaqUrl = privateUrl + "/faqs/";

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
  const url = privateUrl + "/employees/" + id;

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.delete(url, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function updateEmployeeApi(employee, token) {
  const url = privateUrl + "/employees/";
  console.log("Updating: " + JSON.stringify(employee));
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.put(url, employee, { headers });
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

export async function adminGetServices(token) {
  const url = privateUrl + "/services/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    console.log("Token: " + token);
    const response = await axios.get(url, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function updateServiceApi(service, token) {
  const url = privateUrl + "/services/";
  console.log("Updating: " + JSON.stringify(service));
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.put(url, service, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function serviceAddTextApi(position, service, token) {
  const url = privateUrl + "/services/texts/" + position;
  console.log("Updating: " + JSON.stringify(service));
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.post(url, service, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminPutServices(services, token) {
  const url = privateUrl + "/services/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.put(url, services, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminDeleteService(id, token) {
  const url = privateUrl + "/services/" + id;

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.delete(url, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminGetOffices(token) {
  const url = privateUrl + "/offices/";

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.get(url, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminPostOffice(office, token) {
  const url = privateUrl + "/offices/";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.post(url, office, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminCreateNewOffice(token) {
  const url = privateUrl + "/offices/create";
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  try {
    const response = await axios.get(url, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function adminDeleteOffice(officeId, token) {
  const url = privateUrl + "/offices/" + officeId;

  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  try {
    const response = await axios.delete(url, { headers });
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
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
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectWebsiteService() {
  const url = "http://localhost:8765/website-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectFaqsService() {
  const url = "http://localhost:8765/faqs-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectErrorService() {
  const url = "http://localhost:8765/error-service/api/v1/public/health";
  try {
    const response = await axios.get(url);
    return validateResponse(response.data);
  } catch (error) {
    return false;
  }
}

export async function connectOesaService() {
  const url = "http://localhost:8765/oesa-service/api/v1/public/health";
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
