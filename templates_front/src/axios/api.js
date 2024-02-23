import { loadStripe } from "@stripe/stripe-js";
import axios from "axios";

const publicUrl = "http://localhost:8765/oesa-service/api/v1/public/egi";
const authUrl = "http://localhost:8765/security-service/api/v1/public";
const errorReportingUrl =
  "http://localhost:8765/error-service/api/v1/public/error/";
const faqsUrl = "http://localhost:8765/faqs-service/api/v1/public/egi/faqs/";
const employeeApiUrl = publicUrl + "/employees/";
const specialtyApiUrl = publicUrl + "/services";
const appointmentApiUrl = publicUrl + "/appointment/";
const officeApiUrl = publicUrl + "/office/";
const stripeUrl = "http://localhost:8765/stripe-service/api/v1/public";

// http://localhost:8765/stripe-service/api/v1/public/published-key

export async function postFaq(message) {
  const requestBody = {
    questionIsAnswered: false,
    question: message,
    answer: null,
  };
  try {
    const response = await axios.post(faqsUrl, requestBody);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error creating post:", error);
    throw error;
  }
}

export async function getAnsweredFAQs() {
  try {
    const response = await axios.get(faqsUrl);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error creating post:", error);
    throw error;
  }
}

// This is the endpoint used to get the employees in the about us.
export async function getEmployees() {
  try {
    const response = await axios.get(employeeApiUrl);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error creating post:", error);
    throw error;
  }
}

export async function findMyMatch(services) {
  try {
    const requestBody = services;
    const response = await axios.post(
      employeeApiUrl + "services/",
      requestBody
    );
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching therapist matches:", error);
    throw error;
  }
}

export async function getServices() {
  try {
    const response = await axios.get(specialtyApiUrl);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error fetching data:", error);
    throw error;
  }
}

export async function getWebsite(companyUrl) {
  const url =
    "http://localhost:8765/website-service/api/v1/public/" +
    companyUrl +
    "/website/";
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    throw error;
  }
}

export async function postAppointment(appointment) {
  const requestBody = {
    ...appointment,
  };
  try {
    const response = await axios.post(appointmentApiUrl, requestBody);
    return response.data;
  } catch (error) {
    handleErrorReporting(error);
    // console.error("Error creating post:", error);
    throw error;
  }
}

export async function getOffices() {
  try {
    const response = await axios.get(officeApiUrl);
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

export async function getPublishedKey() {
  const url = stripeUrl + "/key";
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    return false;
  }
}

// /create-payment-intent

export async function getClientSecret() {
  const url = stripeUrl + "/create-payment-intent";
  try {
    const response = await axios.post(url);
    return response.data;
  } catch (error) {
    return false;
  }
}

export async function makePayment(cart) {
  const publishedKey = await getPublishedKey();
  console.log(cart);

  const stripe = await loadStripe(publishedKey);

  const url = stripeUrl + "/create-checkout-session";

  const requestBody = [...cart];

  console.log(requestBody);
  try {
    const response = await axios.post(url, requestBody);
    const session = await response.json();
    const result = stripe.redirectToCheckout({
      sessionId: session.id,
    });
  } catch (error) {
    return false;
  }
}
