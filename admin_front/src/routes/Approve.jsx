import React, { useState, useContext } from "react";
import NssInputText from "../nss/NssInputText";
import NssButtonSave from "../nss/NssButtonSave";
import ApiError from "../components/ApiError";
import { useNavigate } from "react-router-dom";
import NssButtonBack from "../nss/NssButtonBack";
import { AuthContext } from "../contexts/context";

const Response = (props) => {
  const { response } = props;
  return <div>{response}</div>;
};

function Approve() {
  const [approvalCode, setApprovalCode] = useState("");
  const [hasApiError, setHasApiError] = useState(false);
  const [response, setResponse] = useState("");
  const navigate = useNavigate();
  const authContext = useContext(AuthContext);

  const onHandleApprovalCode = async () => {
    try {
      const data = await authContext.approveApprovalCode(approvalCode);
      // console.log("Response data: " + data);
      setHasApiError(false);
      setResponse(
        "User with approval code " + approvalCode + " has been activated."
      );
    } catch (error) {
      setResponse(error.response.data);
      setHasApiError(true);
      setResponse(
        "User with approval code " + approvalCode + " has not been activated."
      );
    }
  };

  const onChangeApprovalCode = (val) => {
    setApprovalCode(val);
  };

  const onToLoginPage = () => {
    navigate("/login");
  };

  const disabledSubmit = () => {
    if (!approvalCode) {
      return true;
    }
  };

  return (
    <div>
      <div>
        <div className="flex justify-center">Enter Approval Code</div>
        <div>
          <div className="px-4 py-1">
            <div className="text-xs font-extrabold">Approval Code</div>
            <NssInputText
              value={approvalCode}
              onChange={onChangeApprovalCode}
              id="approvalCode"
              placeholder="Enter approval code"
              type="text"
            />
          </div>
        </div>
        <div className="flex gap-2 justify-center">
          <NssButtonSave
            onClick={onHandleApprovalCode}
            label="Submit"
            disabled={disabledSubmit()}
          />
          <NssButtonBack onClick={onToLoginPage} label="Login Page" />
        </div>
      </div>
      <div>{hasApiError ? <ApiError response={response} /> : <></>}</div>
      <div>
        {response && !hasApiError ? <Response response={response} /> : <></>}
      </div>
    </div>
  );
}

export default Approve;
