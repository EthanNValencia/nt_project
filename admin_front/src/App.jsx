import "./Calendar.css";
import "./App.css";
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import { useContext, useEffect } from "react";
import { UserProvider, AuthProvider } from "./contexts/context";
import Admin from "./routes/Admin";
import Login from "./routes/Login";
import Options from "./routes/Options";
import SignUp from "./routes/SignUp";
import Approve from "./routes/Approve";
import { AuthContext } from "./contexts/context";
import CreateWebsite from "./routes/CreateWebsite";

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
                  <LW>
                    <Login />
                  </LW>
                }
              />
              <Route
                path="approve"
                element={
                  <LW>
                    <Approve />
                  </LW>
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
              <Route
                path="signup"
                element={
                  <LW>
                    <SignUp />
                  </LW>
                }
              />
              <Route
                path="create-website"
                element={
                  <APW>
                    <CreateWebsite />
                  </APW>
                }
              />
              <Route
                path="*"
                element={
                  <LW>
                    <Login />
                  </LW>
                }
              />
            </Routes>
          </UserProvider>
        </AuthProvider>
      </BrowserRouter>
    </>
  );
}

function APW({ children }) {
  // Admin Panel Wrap
  return (
    <div className="bg-nss-20 h-screen w-screen font-ubuntu">
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
    <div className="font-ubuntu">
      <div className="bg-nss-20 min-h-screen w-screen flex items-center justify-center">
        <div className="bg-nss-21 mx-auto border rounded-lg shadow-md w-fit border-nss-1">
          <div className="p-2">{children}</div>
        </div>
      </div>
    </div>
  );
}

export default App;
