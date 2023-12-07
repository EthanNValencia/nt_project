import React, { useState, useContext, useEffect, useRef } from "react";
import NssInputText from "../nss/NssInputText";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/context";
import ApiError from "../components/ApiError";
import NssButtonSave from "../nss/NssButtonSave";
import NssButtonEnter from "../nss/NssButtonEnter";
import NssButtonSignUp from "../nss/NssButtonSignUp";
import NssButtonBack from "../nss/NssButtonBack";
import NssCheckbox from "../nss/NssCheckBox";
import {
  connectSecurityService,
  connectErrorService,
  connectNptService,
  connectEmailService,
  connectSmsService,
  connectGatewayService,
} from "../axios/api";
import NssButtonChevronMini from "../nss/NssButtonChevronMini";
import { Transition } from "@headlessui/react";

const ConnectIcons = (props) => {
  const { status, connected, name } = props;

  return (
    <div className="flex justify-between">
      <div className="text-xs">{name}</div>
      <div className="flex gap-1 border shadow-md border-white bg-black px-1.5 py-1 rounded-md w-fit">
        {status ? (
          <div className="flex gap-1">
            <div className="border-4 border-slate-600 rounded-lg"></div>
            <div className="border-4 border-green-600 rounded-lg"></div>
          </div>
        ) : (
          <div className="flex gap-1">
            <div className="border-4 border-red-600 rounded-lg animate-pulse"></div>
            <div className="border-4 border-slate-600 rounded-lg"></div>
          </div>
        )}
      </div>
    </div>
  );
};

function ConnectStatus(props) {
  const { setServicesOnline } = props;
  const [security, setSecurity] = useState(false);
  const [error, setError] = useState(false);
  const [npt, setNpt] = useState(false);
  const [email, setEmail] = useState(false);
  const [sms, setSms] = useState(false);
  const [gateway, setGateway] = useState(false);
  const [naming, setNaming] = useState(false);
  const [connected, setConnected] = useState(false);
  const intervalTime = useRef(2000);
  const [isShowing, setIsShowing] = useState(false);

  async function checkSecurity() {
    try {
      const data = await connectSecurityService();
      setSecurity(data);
    } catch (error) {
      setSecurity(false);
    }
  }

  async function checkError() {
    try {
      const data = await connectErrorService();
      setError(data);
    } catch (error) {
      setError(false);
    }
  }

  async function checkNpt() {
    try {
      const data = await connectNptService();
      setNpt(data);
    } catch (error) {
      setNpt(false);
    }
  }

  async function checkEmail() {
    try {
      const data = await connectEmailService();
      setEmail(data);
    } catch (error) {
      setEmail(false);
    }
  }

  async function checkSms() {
    try {
      const data = await connectSmsService();
      setSms(data);
    } catch (error) {
      setSms(false);
    }
  }

  async function checkGateway() {
    try {
      const data = await connectGatewayService();
      setGateway(data);
    } catch (error) {
      setGateway(false);
    }
  }

  const printStatuses = () => {
    console.log(`Security: ${security}
      Error: ${error}
      Npt: ${npt}
      Sms: ${sms}
      Gateway: ${gateway}
      Naming: ${naming}
      Connected: ${connected}
  `);
  };

  useEffect(() => {
    const performChecks = () => {
      checkSecurity();
      checkError();
      checkNpt();
      checkEmail();
      checkSms();
      checkGateway();
    };

    const startInterval = () => {
      setTimeout(() => {
        performChecks();
        if (!connected) {
          startInterval();
        } else {
          intervalTime.current = 20000;
          startInterval();
        }
      }, intervalTime.current);
    };
    startInterval();
  }, []);

  useEffect(() => {
    setNaming(security || error || npt || email || sms || gateway);
  }, [security, error, npt, email, sms, gateway]);

  useEffect(() => {
    setConnected(security && error && npt && email && sms && gateway && naming);
  }, [security, error, npt, email, sms, gateway, naming]);

  useEffect(() => {
    setServicesOnline(connected);
  }, [connected]);

  return (
    <div className="relative">
      <div className="flex justify-between">
        <NssButtonChevronMini
          onClick={() => setIsShowing((isShowing) => !isShowing)}
          selected={isShowing}
        >
          {" "}
          {/* needs this for some reason, otherwise it won't render */}
          <ConnectIcons status={connected} />
        </NssButtonChevronMini>
      </div>
      <Transition
        show={isShowing}
        enter="transition-opacity duration-500"
        enterFrom="opacity-0"
        enterTo="opacity-100"
        leave="transition-opacity duration-500"
        leaveFrom="opacity-100"
        leaveTo="opacity-0"
      >
        <div>
          <div className="absolute top-7 left-0 h-32 w-32">
            <div className="border bg-nss-300 rounded-md shadow-lg p-1 text-white">
              <ConnectIcons name={"security"} status={security} />
              <ConnectIcons name={"error"} status={error} />
              <ConnectIcons name={"npt"} status={npt} />
              <ConnectIcons name={"email"} status={email} />
              <ConnectIcons name={"gateway"} status={gateway} />
              <ConnectIcons name={"naming"} status={naming} />
            </div>
          </div>
        </div>
      </Transition>
    </div>
  );
}

function Login() {
  const [password, setPassword] = useState("password");
  const [username, setUsername] = useState("ejnephew@yahoo.com");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [serviceName, setServiceName] = useState("");
  const [createAccount, setCreateAccount] = useState(false);
  const [auth, setAuth] = useState(false);
  const [user, setUser] = useState(false);
  const [admin, setAdmin] = useState(false);
  const navigate = useNavigate();
  const authContext = useContext(AuthContext);
  const [hasApiError, setHasApiError] = useState(false);
  const [servicesOnline, setServicesOnline] = useState(false);

  const handleUserChange = () => {
    setUser(!user);
  };

  const handleAdminChange = () => {
    setAdmin(!admin);
  };

  const onLogin = async () => {
    try {
      const userCred = { email: username, password: password };
      const authenticated = await authContext.authenticateCredentials(userCred);
      setAuth(authenticated);
      setHasApiError(false);
      if (authenticated) {
        navigate("/options");
      }
    } catch (error) {
      setHasApiError(true);
    }
  };

  const onSignUp = () => {
    setCreateAccount(!createAccount);
  };

  const handleRegistration = async () => {
    try {
      const newUser = {
        email: username,
        password: password,
        firstName: firstName,
        lastName: lastName,
        serviceName: serviceName,
      };
      await authContext.registerNewAccount(newUser, "admin");
      setHasApiError(false);
    } catch (error) {
      setHasApiError(true);
    }
  };

  const onChangePassword = (val) => {
    setPassword(val);
  };

  const onChangeUsername = (val) => {
    setUsername(val);
  };

  const onChangeFirstName = (val) => {
    setFirstName(val);
  };

  const onChangeLastName = (val) => {
    setLastName(val);
  };

  const onChangeServiceName = (val) => {
    setServiceName(val);
  };

  return (
    <div className="">
      {!createAccount ? (
        <div>
          <div className="flex justify-center">Login</div>
          <div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">Username/Email</div>
              <NssInputText
                value={username}
                onChange={onChangeUsername}
                id="username"
                placeholder="Enter username"
                type="text"
              />
            </div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">Password</div>
              <NssInputText
                value={password}
                onChange={onChangePassword}
                id="password"
                placeholder="Enter password"
                type="password"
              />
            </div>
          </div>
          <div className="flex justify-center gap-4 pt-2">
            <NssButtonEnter
              onClick={onLogin}
              label="Login"
              // disabled={!servicesOnline}
            />
          </div>
          <div className="flex py-2 justify-center">
            <div className="flex justify-center gap-4 pt-2 pr-2">
              No account? Sign up here:
            </div>
            <NssButtonSignUp onClick={onSignUp} label="Sign Up" />
          </div>

          {auth ? <div>Authenticated!</div> : <></>}
          <ConnectStatus setServicesOnline={setServicesOnline} />
        </div>
      ) : (
        <div>
          <div className="flex justify-center">Create Account</div>
          <div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">Username/Email</div>
              <NssInputText
                value={username}
                onChange={onChangeUsername}
                id="username"
                placeholder="Enter username"
                type="text"
              />
            </div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">Password</div>
              <NssInputText
                value={password}
                onChange={onChangePassword}
                id="password"
                placeholder="Enter password"
                type="password"
              />
            </div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">First name</div>
              <NssInputText
                value={firstName}
                onChange={onChangeFirstName}
                id="firstname"
                placeholder="Enter first name"
                type="text"
              />
            </div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">Last name</div>
              <NssInputText
                value={lastName}
                onChange={onChangeLastName}
                id="lastname"
                placeholder="Enter last name"
                type="text"
              />
            </div>
            <div className="px-4 py-1">
              <div className="text-xs font-extrabold">Company name</div>
              <NssInputText
                value={serviceName}
                onChange={onChangeServiceName}
                id="service"
                placeholder="Enter company name"
                type="text"
              />
            </div>
            <div className="flex justify-center py-2 gap-2 accent-nss-300">
              <NssCheckbox
                label="User"
                value={user}
                onChange={handleUserChange}
              />
              <NssCheckbox
                label="Admin"
                value={admin}
                onChange={handleAdminChange}
              />
            </div>
          </div>
          <div className="flex justify-center gap-2">
            <NssButtonSave
              onClick={handleRegistration}
              label="Submit"
              // disabled={!servicesOnline}
            />
            <NssButtonBack onClick={onSignUp} label="Login Page" />
          </div>
        </div>
      )}
      <div>{hasApiError ? <ApiError /> : <></>}</div>
    </div>
  );
}

export default Login;