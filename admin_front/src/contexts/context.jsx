import React, { useContext, useState } from "react";
import { createContext } from "react";
import { authenticate, validate, validateAction, register } from "../axios/api";

export const UserContext = createContext();

export const useUser = () => useContext(UserContext);

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export function UserProvider({ children }) {
  const [user, setUser] = useState({
    firstName: null,
    lastName: null,
  });

  const [services, setServices] = useState([]);

  const [selectedEmployee, setSelectedEmployee] = useState([]);

  const [appointment, setAppointment] = useState({
    employeeFirstName: null,
    employeeMiddleName: null,
    employeeLastName: null,
    appointmentFirstName: null,
    appointmentLastName: null,
    appointmentEmail: null,
    appointmentPhoneNumber: null,
    appointmentBeginTime: null,
    appointmentEndTime: null,
    appointmentNotes: null,
    serviceName: null,
  });

  const navigateAppointment = (navigate) => {
    // console.log("appointment: " + JSON.stringify(appointment));
    if (
      appointment.appointmentFirstName == null ||
      appointment.appointmentLastName == null
    ) {
      navigate("/request-name");
      return;
    }
    if (appointment.serviceName == null) {
      navigate("/category");
      return;
    }
    if (
      appointment.employeeFirstName == null ||
      appointment.employeeLastName == null ||
      appointment.appointmentBeginTime == null ||
      appointment.appointmentEndTime == null
    ) {
      navigate("/pairing");
      return;
    }
    if (
      appointment.appointmentEmail == null &&
      appointment.appointmentPhoneNumber == null
    ) {
      navigate("/contact-information");
      return;
    }
  };

  function setAppointmentName(firstName, lastName) {
    const updateAppointment = {
      ...appointment,
      appointmentFirstName: firstName,
      appointmentLastName: lastName,
    };
    setAppointment(updateAppointment);
  }

  function setAppointmentServiceName(name) {
    const updateAppointment = { ...appointment, serviceName: name };
    setAppointment(updateAppointment);
  }

  function setEmployeeName(firstName, middleName, lastName) {
    const updateAppointment = {
      ...appointment,
      employeeFirstName: firstName,
      employeeMiddleName: middleName,
      employeeLastName: lastName,
    };
    setAppointment(updateAppointment);
  }

  function setAppointmentTimes(beginTime, endTime) {
    const updateAppointment = {
      ...appointment,
      appointmentBeginTime: beginTime,
      appointmentEndTime: endTime,
    };
    setAppointment(updateAppointment);
  }

  function setAppointmentPhoneAndOrEmail(phone, email) {
    const updateAppointment = {
      ...appointment,
      appointmentEmail: email,
      appointmentPhoneNumber: phone,
    };
    setAppointment(updateAppointment);
  }

  function setNote(note) {
    const updateAppointment = { ...appointment, appointmentNotes: note };
    setAppointment(updateAppointment);
  }

  function filloutAppointment() {}

  function printUserContext() {
    console.log("--- Begin User Context ---");
    console.log("user: " + JSON.stringify(user));
    console.log("services: " + JSON.stringify(services));
    console.log("appointment: " + JSON.stringify(appointment));
    console.log("--- End User Context ---");
  }

  function setUserName(firstName, lastName) {
    setUser({
      firstName: firstName,
      lastName: lastName,
    });
  }

  function setSelectedService(service) {
    const array = []; // Your array
    array.push(service);
    setServices(array);
  }

  return (
    <UserContext.Provider
      value={{
        user,
        setUserName,
        services,
        setSelectedService,
        selectedEmployee,
        setSelectedEmployee,
        setAppointmentName,
        setAppointmentServiceName,
        setEmployeeName,
        setAppointmentTimes,
        printUserContext,
        setAppointmentPhoneAndOrEmail,
        appointment,
        setNote,
        navigateAppointment,
      }}
    >
      {children}
    </UserContext.Provider>
  );
}

export function AuthProvider({ children }) {
  const [auth, setAuth] = useState(false);
  const [token, setToken] = useState("");

  const authenticateCredentials = async ({ email, password }) => {
    const userCred = { email: email, password: password };
    try {
      const authToken = await authenticate(userCred);
      setToken(authToken);
      console.log("authToken: " + authToken);
      setAuth(true);
      return true;
    } catch (error) {
      console.error("Error authenticating credentials:", error);
      setAuth(false);
      throw error;
    }
  };

  const registerNewAccount = async (newUser) => {
    try {
      await register(newUser);
    } catch (error) {
      console.error("Error creating new account:", error);
      throw error;
    }
  };

  const validateUserRoute = async () => {
    const userAction = { role: "USER" };
    try {
      const validated = await validateAction(token, userAction);
      if (validated) {
        setAuth(true);
      }
    } catch (error) {
      console.error("Error validating route:", error);
      setAuth(false);
      throw error;
    }
  };

  const validateAdminRoute = async () => {
    const adminAction = { role: "ADMIN" };
    try {
      const validated = await validateAction(token, adminAction);
      if (validated) {
        setAuth(true);
      }
    } catch (error) {
      console.error("Error validating admin route:", error);
      setAuth(false);
      throw error;
    }
  };

  const validateToken = async () => {
    try {
      const validated = await validate(token);
      if (validated) {
        setAuth(true);
      }
    } catch (error) {
      console.error("Error validating token:", error);
      setAuth(false);
      throw error;
    }
  };

  return (
    <AuthContext.Provider
      value={{
        token,
        auth,
        validateUserRoute,
        validateAdminRoute,
        authenticateCredentials,
        registerNewAccount,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
