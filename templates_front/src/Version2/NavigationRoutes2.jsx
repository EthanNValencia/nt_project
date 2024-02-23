import React from "react";
import Home from "./Home";
import AboutUs from "./AboutUs";
import Complete from "./Complete";
import ContactUs from "./ContactUs";
import FrequentlyAskedQuestions from "./FrequentlyAskedQuestions";
import Products from "./Products";
import Cart from "./Cart";
import NotFound from "./NotFound";

function NavigationRoutes2() {
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
          path="completion"
          element={
            <WebSiteWrap>
              <Complete />
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
          path="faqs"
          element={
            <WebSiteWrap>
              <FrequentlyAskedQuestions />
            </WebSiteWrap>
          }
        />
        <Route
          path="products"
          element={
            <WebSiteWrap>
              <Products />
            </WebSiteWrap>
          }
        />
        <Route
          path="cart"
          element={
            <WebSiteWrap>
              <Cart />
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
  const style = {
    minHeight: "85vh",
  };
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

export default NavigationRoutes2;
