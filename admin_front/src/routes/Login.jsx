import React, { useState, useContext, useEffect, useRef } from "react";
import NssInputText from "../nss/NssInputText";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/context";
import ApiError from "../components/ApiError";
import NssButtonEnter from "../nss/NssButtonEnter";
import NssButtonSignUp from "../nss/NssButtonSignUp";
import {
  connectSecurityService,
  connectErrorService,
  connectOesaService,
  connectEmailService,
  connectSmsService,
  connectGatewayService,
  connectFaqsService,
} from "../axios/api";
import NssButtonChevronMini from "../nss/NssButtonChevronMini";
import { Transition } from "@headlessui/react";
import NssMenu from "../nss/menu/NssMenu";

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
  const [oesa, setOesa] = useState(false);
  const [email, setEmail] = useState(false);
  const [sms, setSms] = useState(false);
  const [gateway, setGateway] = useState(false);
  const [naming, setNaming] = useState(false);
  const [faqs, setFaqs] = useState(false);
  const [connected, setConnected] = useState(false);
  const intervalTime = useRef(2000);
  const [isShowing, setIsShowing] = useState(false);

  async function checkFaqs() {
    try {
      const data = await connectFaqsService();
      setFaqs(data);
    } catch (error) {
      setFaqs(false);
    }
  }

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

  async function checkOesa() {
    try {
      const data = await connectOesaService();
      setOesa(data);
    } catch (error) {
      setOesa(false);
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
      oesa: ${oesa}
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
      checkOesa();
      checkEmail();
      checkSms();
      checkGateway();
      checkFaqs();
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
    setNaming(security || error || oesa || email || sms || gateway || faqs);
  }, [security, error, oesa, email, sms, gateway, faqs]);

  useEffect(() => {
    setConnected(
      security && error && oesa && email && sms && gateway && naming && faqs
    );
  }, [security, error, oesa, email, sms, gateway, naming, faqs]);

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
              <ConnectIcons name={"oesa"} status={oesa} />
              <ConnectIcons name={"email"} status={email} />
              <ConnectIcons name={"gateway"} status={gateway} />
              <ConnectIcons name={"naming"} status={naming} />
              <ConnectIcons name={"faqs"} status={faqs} />
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
  const [auth, setAuth] = useState(false);
  const navigate = useNavigate();
  const authContext = useContext(AuthContext);
  const [hasApiError, setHasApiError] = useState(false);
  const [servicesOnline, setServicesOnline] = useState(false);

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
    navigate("/signup");
  };

  const onChangePassword = (val) => {
    setPassword(val);
  };

  const onChangeUsername = (val) => {
    setUsername(val);
  };

  return (
    <div className="">
      <div>
        <div className="flex justify-center">Login</div>
        <div>
          <div className="px-4">
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
        <div className="flex justify-between">
          <ConnectStatus setServicesOnline={setServicesOnline} />
          <NssMenu />
        </div>
      </div>
      <div>{hasApiError ? <ApiError /> : <></>}</div>
    </div>
  );
}

export default Login;
