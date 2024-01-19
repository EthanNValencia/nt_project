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
import Complete from "./routes/Complete";

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
                path="completion"
                element={
                  <WSW>
                    <Complete />
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
  const style = {
    minHeight: "85vh",
  };

  // WSW stands for WebSiteWrap, it is meant to wrap all the routes in the NPT website.
  return (
    <div className="bg-egi-30">
      <div className="mx-auto h-full">
        <div className="h-full font-montserrat">
          <div>
            <Header />
            <div style={style}>
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
