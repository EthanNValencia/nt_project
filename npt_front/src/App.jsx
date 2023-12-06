// import "./App.css";
import "./Calendar.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Everything from "./dev-components/Everything";
import AboutUs from "./routes/AboutUs";
import Home from "./routes/Home";
import ContactUs from "./routes/ContactUs";
import FrequentlyAskedQuestions from "./routes/FrequentlyAskedQuestions";
import Header from "./components/Header";
import { UserProvider, AuthProvider } from "./contexts/context";
import RequestName from "./routes/appointment-process/1_RequestName";
import SelectCategory from "./routes/appointment-process/2_SelectCategory";
import TherapistPairing from "./routes/appointment-process/3_TherapistPairing";
import Footer from "./components/Footer";
import ContactInformation from "./routes/appointment-process/4_ContactInformation";
import Notes from "./routes/appointment-process/5_Notes";
import Services from "./routes/Services";
import Resources from "./routes/Resources";
import NotFound from "./routes/NotFound";

function App() {
  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <UserProvider>
            <Routes>
              <Route
                path="/"
                element={
                  <WSW>
                    <Home />
                  </WSW>
                }
              />
              <Route
                path="about"
                element={
                  <WSW>
                    <AboutUs />
                  </WSW>
                }
              />
              <Route
                path="contact"
                element={
                  <WSW>
                    <ContactUs />
                  </WSW>
                }
              />
              <Route
                path="services"
                element={
                  <WSW>
                    <Services />
                  </WSW>
                }
              />
              <Route
                path="faqs"
                element={
                  <WSW>
                    <FrequentlyAskedQuestions />
                  </WSW>
                }
              />
              <Route
                path="resources"
                element={
                  <WSW>
                    <Resources />
                  </WSW>
                }
              />
              <Route
                path="request-name"
                element={
                  <WSW>
                    <RequestName />
                  </WSW>
                }
              />
              <Route
                path="category"
                element={
                  <WSW>
                    <SelectCategory />
                  </WSW>
                }
              />
              <Route
                path="pairing"
                element={
                  <WSW>
                    <TherapistPairing />
                  </WSW>
                }
              />
              <Route
                path="contact-information"
                element={
                  <WSW>
                    <ContactInformation />
                  </WSW>
                }
              />
              <Route
                path="notes"
                element={
                  <WSW>
                    <Notes />
                  </WSW>
                }
              />
              <Route
                path="dev"
                element={
                  <WSW>
                    <Everything />
                  </WSW>
                }
              />
              <Route
                path="*"
                element={
                  <WSW>
                    <NotFound />
                  </WSW>
                }
              />
            </Routes>
          </UserProvider>
        </AuthProvider>
      </BrowserRouter>
    </>
  );
}

function WSW({ children }) {
  // WSW stands for WebSiteWrap, it is meant to wrap all the routes in the NPT website.
  // I will make a different wrapper for the admin panel.
  return (
    <div>
      <div className="p-1 min-h-screen h-screen w-screen">
        <div className="mx-auto border rounded-lg shadow-md">
          <Header />
          <div className="p-4">{children}</div>
          <Footer />
        </div>
      </div>
    </div>
  );
}

export default App;
