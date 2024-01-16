import "./App.css";
import "./Calendar.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AboutUs from "./routes/AboutUs";
import Home from "./routes/Home";
import ContactUs from "./routes/ContactUs";
import FrequentlyAskedQuestions from "./routes/FrequentlyAskedQuestions";
import Cart from "./routes/Cart";
import Header from "./components/Header";
import { AuthProvider } from "./contexts/AuthContext";
import { CartProvider } from "./contexts/CartContext";
import Footer from "./components/Footer";
import NotFound from "./routes/NotFound";
import Products from "./routes/Products";

function App() {
  return (
    <div className="">
      <BrowserRouter>
        <AuthProvider>
          <CartProvider>
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
                path="cart"
                element={
                  <WSW>
                    <Cart />
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
          </CartProvider>
        </AuthProvider>
      </BrowserRouter>
    </div>
  );
}

function WSW({ children }) {
  // WSW stands for WebSiteWrap, it is meant to wrap all the routes in the NPT website.
  return (
    <div className="bg-egi-30">
      <div className="mx-auto h-full">
        <div className="h-full font-montserrat">
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
