import React, { useContext, useState } from "react";
import { createContext } from "react";
import { authenticate, validate, validateAction } from "../axios/api";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

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
      console.error("Error fetching data:", error);
      setAuth(false);
      throw error;
    }
  };

  const registerNewAccount = async (newUser, type) => {
    try {
      await register(newUser, type);
    } catch (error) {
      console.error("Error fetching data:", error);
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
      console.error("Error fetching data:", error);
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
      console.error("Error fetching data:", error);
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
      console.error("Error fetching data:", error);
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
