import React, { useState } from "react";
import NssMenuSignUp from "./NssMenuSignUp";
import { Transition } from "@headlessui/react";
import NssButtonChevronMini from "../NssButtonChevronMini";
import { useNavigate } from "react-router-dom";
import NSSMenuEnter from "./NSSMenuEnter";
import NssMenuAdd from "./NssMenuAdd";

function NssMenu() {
  const [isShowing, setIsShowing] = useState(false);
  const navigate = useNavigate();

  const onSignUp = () => {
    navigate("/signup");
  };

  const onActivationCode = () => {
    navigate("/approve");
  };

  const onCreateWebsite = () => {
    navigate("/create-website");
  };

  return (
    <div className="relative">
      <div className="flex justify-between">
        <NssButtonChevronMini
          onClick={() => setIsShowing((isShowing) => !isShowing)}
          selected={isShowing}
        >
          {" "}
          {/* needs this for some reason, otherwise it won't render */}
          Menu
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
            <div className="border bg-nss-300 rounded shadow-lg text-white">
              <NssMenuSignUp onClick={onSignUp} label="Sign Up" />
              <NSSMenuEnter onClick={onActivationCode} label="Approval Code" />
              <NssMenuAdd onClick={onCreateWebsite} label="Create Website" />
            </div>
          </div>
        </div>
      </Transition>
    </div>
  );
}
export default NssMenu;
