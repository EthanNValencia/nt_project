import React, { useState, useContext } from "react";
import NssInputText from "../nss/NssInputText";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/context";
import ApiError from "../components/ApiError";
import NssButtonSave from "../nss/NssButtonSave";
import NssButtonBack from "../nss/NssButtonBack";
import Options from "../admin/Options";
import { ROLES, ROLES_ARR } from "../admin/Objects";

function Response(props) {
  const { response } = props;
  return <div>{response}</div>;
}

function SignUp() {
  const [password, setPassword] = useState("password");
  const [username, setUsername] = useState("test@yahoo.com");
  const [firstName, setFirstName] = useState("Ethan");
  const [lastName, setLastName] = useState("Nephew");
  const [serviceName, setServiceName] = useState("test-service");
  const navigate = useNavigate();
  const authContext = useContext(AuthContext);
  const [hasApiError, setHasApiError] = useState(false);
  const [role, setRole] = useState(ROLES.USER);
  const [hasResponse, setHasResponse] = useState(false);
  const [response, setResponse] = useState("");

  const onHandleRegistration = async () => {
    try {
      const newUser = {
        email: username,
        password: password,
        firstName: firstName,
        lastName: lastName,
        serviceName: serviceName,
        role: role,
      };
      const apiResponse = await authContext.registerNewAccount(newUser);
      setResponse(apiResponse);
      setHasApiError(false);
    } catch (error) {
      setResponse(
        "Something went wrong. Your sign up request did not succeed. Please try again."
      );
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

  const onToLoginPage = () => {
    navigate("/login");
  };

  const onChangeRole = (newRole) => {
    setRole(newRole);
  };

  return (
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
            onToLoginPage
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
          <Options
            selected={role}
            selectOptions={ROLES_ARR}
            name={"Select Role"}
            selectedChange={onChangeRole}
          />
        </div>
      </div>
      <div className="flex justify-center gap-2">
        <NssButtonSave
          onClick={onHandleRegistration}
          label="Submit"
          // disabled={!servicesOnline}
        />
        <NssButtonBack onClick={onToLoginPage} label="Login Page" />
      </div>
      <div>{hasApiError ? <ApiError /> : <></>}</div>
      <div>{hasResponse ? <Response response={response} /> : <></>}</div>
    </div>
  );
}

export default SignUp;
