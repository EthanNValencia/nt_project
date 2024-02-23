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
import { WebsiteContext, WebsiteProvider } from "./contexts/WebsiteContext";
import { useContext } from "react";

function App() {
  return (
    <>
      <BrowserRouter>
        <WebsiteProvider>
          <AuthProvider>
            <CartProvider>
              <Navigation />
            </CartProvider>
          </AuthProvider>
        </WebsiteProvider>
      </BrowserRouter>
    </>
  );
}

function Navigation() {
  const websiteContext = useContext(WebsiteContext);
  return <></>;
}

export default App;
