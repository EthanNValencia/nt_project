import "./App.css";
import "./Calendar.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AboutUs from "./routes/AboutUs";
import Home from "./routes/Home";
import ContactUs from "./routes/ContactUs";
import FrequentlyAskedQuestions from "./routes/FrequentlyAskedQuestions";
import Header from "./components/Header";
import { UserProvider, AuthProvider } from "./contexts/context";
import Footer from "./components/Footer";
import Services from "./routes/Services";
import NotFound from "./routes/NotFound";
import Products from "./routes/Products";

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
                path="products"
                element={
                  <WSW>
                    <Products />
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
  return (
    <div className="bg-egi-70">
      <div className="mx-auto h-full">
        <div className="border rounded-lg shadow-md h-full font-montserrat">
          <div>
            <Header />
            <div>
              <div>{children}</div>
            </div>
            <Footer />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
