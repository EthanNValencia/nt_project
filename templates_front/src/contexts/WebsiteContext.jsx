import React, { useContext, useState } from "react";
import { createContext } from "react";
import { getWebsite } from "../axios/api";

export const WebsiteContext = createContext();

export const useData = () => useContext(WebsiteContext);

export function WebsiteProvider({ children }) {
  const [website, setWebsite] = useState({});

  const fetchWebsite = async () => {
    try {
      const websiteData = await getWebsite();
      setWebsite(websiteData);
      return true;
    } catch (error) {
      console.error("Error fetching website:", error);
    }
  };

  useEffect(() => {
    fetchWebsite();
  }, []);

  return (
    <WebsiteContext.Provider
      value={{
        website,
      }}
    >
      {children}
    </WebsiteContext.Provider>
  );
}
