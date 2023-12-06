import "./Calendar.css";
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import { useContext, useEffect } from "react";
import Header from "./components/Header";
import { UserProvider, AuthProvider } from "./contexts/context";
import Admin from "./routes/Admin";
import Footer from "./components/Footer";
import Login from "./routes/Login";
import Options from "./routes/Options";
import { AuthContext } from "./contexts/context";

const PrivateRoute = ({ children }) => {
  const navigate = useNavigate();
  const authContext = useContext(AuthContext);
  const isAuthenticated = authContext.auth;

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
    }
  }, [isAuthenticated]);

  return children;
};

function App() {
  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <UserProvider>
            <Routes>
              {/* <Route path="*" element={<Navigate to="login" replace />} /> */}

              <Route
                path="admin"
                element={
                  <PrivateRoute>
                    <APW>
                      <Admin />
                    </APW>
                  </PrivateRoute>
                }
              />
              <Route
                path="login"
                element={
                  <PrivateRoute>
                    <LW>
                      <Login />
                    </LW>
                  </PrivateRoute>
                }
              />
              <Route
                path="options"
                element={
                  <PrivateRoute>
                    <LW>
                      <Options />
                    </LW>
                  </PrivateRoute>
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

function APW({ children }) {
  // Admin Panel Wrap
  return (
    <div className="bg-nss-20 h-screen w-screen">
      <div className="bg-nss-20 flex">
        <div className="bg-nss-21 border shadow-md w-screen border-nss-1">
          <div className="p-1">{children}</div>
        </div>
      </div>
    </div>
  );
}

function LW({ children }) {
  // Login Wrap
  return (
    <div>
      <div className="bg-nss-20 h-screen w-screen flex items-center justify-center">
        <div className="bg-nss-21 mx-auto border rounded-lg shadow-md w-fit border-nss-1">
          <div className="p-2">{children}</div>
        </div>
      </div>
    </div>
  );
}

export default App;
