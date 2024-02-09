import "./App.css";
import "./Calendar.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AboutUs from "./routes2/AboutUs";
import Home from "./routes2/Home";
import ContactUs from "./routes2/ContactUs";
import FrequentlyAskedQuestions from "./routes2/FrequentlyAskedQuestions";
import Cart from "./routes2/Cart";
import Header from "./components/Header";
import { AuthProvider } from "./contexts/AuthContext";
import { CartProvider } from "./contexts/CartContext";
import Footer from "./components/Footer";
import NotFound from "./routes2/NotFound";
import Products from "./routes2/Products";
import Complete from "./routes2/Complete";

function App() {
  return (
    <div className="">
      <BrowserRouter>
        <WebsiteContext>
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
        </WebsiteContext>
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
