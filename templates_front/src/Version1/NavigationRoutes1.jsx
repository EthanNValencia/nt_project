import "./Calendar.css";
import { Route, Routes } from "react-router-dom";
import AboutUs from "./AboutUs";
import Home from "./Home";
import ContactUs from "./ContactUs";
import FrequentlyAskedQuestions from "./FrequentlyAskedQuestions";
import Header from "./components/Header";
import { UserProvider, AuthProvider } from "./contexts/context";
import RequestName from "./appointment-process/1_RequestName";
import SelectCategory from "./appointment-process/2_SelectCategory";
import TherapistPairing from "./appointment-process/3_TherapistPairing";
import Footer from "./components/Footer";
import ContactInformation from "./appointment-process/4_ContactInformation";
import Notes from "./appointment-process/5_Notes";
import Services from "./Services";
import Resources from "./Resources";
import NotFound from "./NotFound";

function NavigationRoutes1() {
  return (
    <>
      <Routes>
        <Route
          path="/"
          element={
            <WebSiteWrap>
              <Home />
            </WebSiteWrap>
          }
        />
        <Route
          path="about"
          element={
            <WebSiteWrap>
              <AboutUs />
            </WebSiteWrap>
          }
        />
        <Route
          path="contact"
          element={
            <WebSiteWrap>
              <ContactUs />
            </WebSiteWrap>
          }
        />
        <Route
          path="services"
          element={
            <WebSiteWrap>
              <Services />
            </WebSiteWrap>
          }
        />
        <Route
          path="faqs"
          element={
            <WebSiteWrap>
              <FrequentlyAskedQuestions />
            </WebSiteWrap>
          }
        />
        <Route
          path="resources"
          element={
            <WebSiteWrap>
              <Resources />
            </WebSiteWrap>
          }
        />
        <Route
          path="request-name"
          element={
            <WebSiteWrap>
              <RequestName />
            </WebSiteWrap>
          }
        />
        <Route
          path="category"
          element={
            <WebSiteWrap>
              <SelectCategory />
            </WebSiteWrap>
          }
        />
        <Route
          path="pairing"
          element={
            <WebSiteWrap>
              <TherapistPairing />
            </WebSiteWrap>
          }
        />
        <Route
          path="contact-information"
          element={
            <WebSiteWrap>
              <ContactInformation />
            </WebSiteWrap>
          }
        />
        <Route
          path="notes"
          element={
            <WebSiteWrap>
              <Notes />
            </WebSiteWrap>
          }
        />
        <Route
          path="*"
          element={
            <WebSiteWrap>
              <NotFound />
            </WebSiteWrap>
          }
        />
      </Routes>
    </>
  );
}

function WebSiteWrap({ children }) {
  return (
    <div className="font-lato bg-white">
      <div className="py-1 w-11/12 mx-auto h-full">
        <div className="border rounded-lg shadow-md h-full">
          <div>
            <Header />
            <div>
              <div className="p-2">{children}</div>
            </div>
            <Footer />
          </div>
        </div>
      </div>
    </div>
  );
}

export default NavigationRoutes1;
